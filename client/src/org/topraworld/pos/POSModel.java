package org.topraworld.pos;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.form.FormFrame;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.topraworld.model.MCPOSConfigiration;
import org.topraworld.model.MCPhamacyReturn;

public class POSModel {
	
	private int C_Bpartner_ID;
	private int C_DoctypeTar_ID;
	private int M_PriceList_ID;
	private MOrder morder;
	private Trx trx;
	private Properties ctx;
	private POSPanel panel;

	private MCPOSConfigiration posConf;
	private static CLogger 	s_log = CLogger.getCLogger(POSModel.class);
	
	public POSModel(POSPanel panel,Trx trx , Properties m_ctx){
		
		this.trx = trx;
		this.ctx = m_ctx;
		this.panel = panel;
		
		posConf = MCPOSConfigiration.getPOSConfigOrg(ctx, trx.getTrxName());
		if(posConf == null || posConf.get_ID() < 1)
			throw new AdempiereException("Invalid POS setup configuration!");
	}
	
	@SuppressWarnings("deprecation")
	protected void createNewCashOrder() {
		
		//ask user to select type of customer
		new BpTypes(this, "Type1").show();
		
		C_DoctypeTar_ID = posConf.getC_DocTypePharmaBil_ID();
		
		if(C_Bpartner_ID == posConf.getC_BpGeneral_ID())
			M_PriceList_ID = posConf.getM_PriceListCash_ID();
		else
			M_PriceList_ID = posConf.getM_PriceListSpecial_ID();
		
		this.createOrder("B");
	}
	
	@SuppressWarnings("deprecation")
	protected void createNewCardOrder() {
		
		//ask user to select type of customer
		new BpTypes(this, "Type1").show();
		
		//normal order with 0 discount
		if(this.getC_Bpartner_ID() == posConf.getC_BpGeneral_ID())
			M_PriceList_ID = posConf.getM_PriceListCash_ID();
		//order with 3% discount
		else
			M_PriceList_ID = posConf.getM_PriceListCard_ID();
		
		C_DoctypeTar_ID = posConf.getC_DocTypePharmaBil_ID();
		
		this.createOrder("K");
	}
	
	
	protected void createOrder(String paymentRule){
		
		morder = new MOrder(ctx, 0, trx.getTrxName());
		morder.setC_BPartner_ID(C_Bpartner_ID);
		morder.setM_Warehouse_ID(posConf.getM_Warehouse_ID());
		morder.setIsSOTrx(true);
		morder.setC_DocTypeTarget_ID(C_DoctypeTar_ID);
		morder.setPaymentRule(paymentRule);
		morder.setM_PriceList_ID(M_PriceList_ID);
		morder.save(trx.getTrxName());
		trx.commit();
		
		//this two lines were added due to not create correct price list, this is incorrect, should be corrected
		morder.setM_PriceList_ID(M_PriceList_ID);
		morder.setSalesRep_ID(Env.getAD_User_ID(ctx));
		morder.save(trx.getTrxName());
		
		trx.commit();
		//update panel information
		panel.setHeaderInfor(morder);
		panel.setDetailInfo(morder);
	}
	
	protected void createLine(int M_Product_ID , BigDecimal qty){
		
		if(!getOrderDocStatus().equals("DR")){//validate for order status
			new POSError(this.panel.getFrame(), "Processed Order!", "You are not allowed to make changes!");
			return;
		}	
		
		String whereClause = "AND M_Product_ID = " + M_Product_ID;
		int length = morder.getLines(whereClause, "M_Product_ID DESC").length;
		
		if(length == 0){//Already added validation
			
			MOrderLine ol = new MOrderLine(morder);
			ol.setQty(qty);
			ol.setM_Product_ID(M_Product_ID);
			ol.save();
			trx.commit();
			//update panel info
			updateInfor();
			
		}else{
			new POSError(this.panel.getFrame(), "Duplicate line added", "You have already added this product!");
		}
	}
	
	protected void updateInfor(){
		
		try{
			//set order header information
			MOrder order = new MOrder(ctx, this.morder.get_ID(), trx.getTrxName());
			this.panel.setHeaderInfor(order);
			//set discount and order details
			this.panel.setDetailInfo(order);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void changeBPartner(){
		
		
		if(this.morder != null && !this.morder.getDocStatus().equals("DR")){
			new POSError(this.panel.getFrame(), "Processed Order!", "You are not allowed to make changes!");
			return;
		}
		
		if(this.morder != null && this.morder.getC_DocTypeTarget_ID() == posConf.getC_DocTypeReturn_ID()){
			new POSError(this.panel.getFrame(), "Return Order!", "You are not allowed to make changes!");
			return;
		}
		
		//Business partner can be change only after initiating the order
		if(morder != null){ //&& morder.getLines().length > 0){
			
			new BpTypes(this, "Type1").show();
			
			int M_PriceList_ID = 0;
			//card type
			if(morder.getPaymentRule().equals("K")){
				if(C_Bpartner_ID == this.getPosConf().getC_BpGeneral_ID()){
					M_PriceList_ID = this.getPosConf().getM_PriceListCash_ID();
				}else{
					M_PriceList_ID = this.getPosConf().getM_PriceListCard_ID();
				}
			//cash	
			}else {
				if(C_Bpartner_ID == this.getPosConf().getC_BpGeneral_ID()){
					M_PriceList_ID = this.getPosConf().getM_PriceListCash_ID();
				}else{
					M_PriceList_ID = this.getPosConf().getM_PriceListSpecial_ID();
				}
			}
			
			morder.setM_PriceList_ID(M_PriceList_ID);
			morder.setC_BPartner_ID(C_Bpartner_ID);
			
			//when changing business partner changing prices
			MOrderLine [] lines = morder.getLines(true, "C_Order_ID");
			for(MOrderLine line : lines){
				line.setPrice(M_PriceList_ID);
				line.save();
			}
			
			morder.saveEx();
			trx.commit();
			panel.setHeaderInfor(morder);
			panel.setDetailInfo(morder);
		}
	}
	
	private String getOrderDocStatus(){
		
		String sql ="SELECT DOCSTATUS FROM C_ORDER WHERE C_ORDER_ID = ? AND AD_Org_ID = ? ";
		return DB.getSQLValueString(morder.get_TrxName(), sql, morder.get_ID() , morder.getAD_Org_ID());		
	}
	
	protected void plusQty(int C_OrderLine_ID){
		
		//if(!(getOrderDocStatus().equals("DR") || getOrderDocStatus().equals("IP"))){//validate for order status
		if(!getOrderDocStatus().equals("DR")){//validate for order status
			new POSError(this.panel.getFrame(), "Processed Order!", "You are not allowed to make changes!");
			return;
		}
		
		MOrderLine ol = new MOrderLine(ctx, C_OrderLine_ID, trx.getTrxName());
		//customer return
		if(ol.getC_Order().getC_DocTypeTarget_ID() == this.getPosConf().getC_DocTypeReturn_ID()){
			
			//check base order line amount
			int C_PhamacyReturn_ID = MCPhamacyReturn.getReturnLine((MOrder) ol.getC_Order(), ol.getC_Order().getC_Order_ID(), ol.get_ID());
			MCPhamacyReturn pr = new MCPhamacyReturn(ctx, C_PhamacyReturn_ID, trx.getTrxName());
			//base quantity should lass than or equal return amount
			
			BigDecimal baseQty =  pr.getBaseQty().multiply(new BigDecimal(-1));
			BigDecimal reQty = ol.getQtyOrdered().add(new BigDecimal(-1));
			
			if(baseQty.compareTo(reQty) != 1){
				
				pr.setReturnQty(reQty);
				ol.setQty(reQty);
				pr.save();
				
			}else
				new POSError(this.panel.getFrame(), "Quantity error", "Should be less than or equal with delivered quantity!");
			
		//pharmacy selling	
		}else{
			BigDecimal freeQty = this.getFreeQty(ol.getM_Product_ID());
			if(freeQty.compareTo(new BigDecimal(0)) == 1)
				ol.setQty(ol.getQtyEntered().add(new BigDecimal(1)));
			else
				new POSError(this.panel.getFrame(), "Insufficient Inventory", "0 Available quantity!");
		}	
		
		ol.save(); trx.commit();
		this.updateInfor();//update panel info
	}
	
	protected void minusQty(int C_OrderLine_ID){
		
		//if(!(getOrderDocStatus().equals("DR") || getOrderDocStatus().equals("IP"))){//validate for order status
		if(!getOrderDocStatus().equals("DR")){//validate for order status
			new POSError(this.panel.getFrame(), "Processed Order!", "You are not allowed to make changes!");
			return;
		}
		
		MOrderLine ol = new MOrderLine(ctx, C_OrderLine_ID, trx.getTrxName());
		//customer return 
		if(ol.getC_Order().getC_DocTypeTarget_ID() == this.getPosConf().getC_DocTypeReturn_ID()){
			
			//check base order line amount
			int C_PhamacyReturn_ID = MCPhamacyReturn.getReturnLine((MOrder) ol.getC_Order(), ol.getC_Order().getC_Order_ID(), ol.get_ID());
			MCPhamacyReturn pr = new MCPhamacyReturn(ctx, C_PhamacyReturn_ID, trx.getTrxName());
			//base quantity should lass than or equal return amount
			BigDecimal reQty = ol.getQtyOrdered().add(new BigDecimal(1));
			
			if(reQty.compareTo(new BigDecimal(0)) == -1){
				
				pr.setReturnQty(reQty);
				ol.setQty(reQty);
				pr.save();
				
			}else
				new POSError(this.panel.getFrame(), "Quantity error", "Return quantity should be less than zero!");
			
		}else{//pharmecy selling
			
			BigDecimal orderdQty = ol.getQtyEntered().subtract(new BigDecimal(1));
			if(orderdQty.compareTo(new BigDecimal(0)) == 1)
				ol.setQty(ol.getQtyEntered().subtract(new BigDecimal(1)));
				
			else
				new POSError(this.panel.getFrame(), "Zero ordered quantity", "Ordered quantity should be greater than Zero!");
		}
		
		ol.save(); trx.commit();
		this.updateInfor();
	}
	
	protected void removeLine(int C_OrderLine_ID){
		
		MOrderLine ol = new MOrderLine(ctx, C_OrderLine_ID, trx.getTrxName());
		ol.delete(true);
	}
	
	protected void removeLines(@SuppressWarnings("rawtypes") ArrayList ids){
		
		if(!getOrderDocStatus().equals("DR")){//validate for order status
			new POSError(this.panel.getFrame(), "Processed Order!", "You are not allowed to make changes!");
			return;
		}
		
		//return lines should be removed from C_PharmacyReturn too
		if(morder.getC_DocTypeTarget_ID() == posConf.getC_DocTypeReturn_ID()){
			
			for(Object id : ids){
				
				MOrderLine ol = new MOrderLine(ctx, (Integer) id, trx.getTrxName());
				//remove from the C_PhamacyReturn
				int C_PhamacyReturn_ID = MCPhamacyReturn.getReturnLine((MOrder) ol.getC_Order(), ol.getC_Order_ID(), ol.get_ID());
				
				MCPhamacyReturn pr = new MCPhamacyReturn(ctx, C_PhamacyReturn_ID, trx.getTrxName());
				pr.delete(true);
				removeLine((int)id);
			}
			
		}else{//delete selling orddr
			for(Object id : ids){
				removeLine((int)id);
			}
		}
		
		trx.commit();
		updateInfor();
	}
	
	
	
	protected void printOrder(){
		
		if(this.getOrderDocStatus().equalsIgnoreCase("DR") || this.getOrderDocStatus().equalsIgnoreCase("IP")){
		//if(this.getOrderDocStatus().equalsIgnoreCase("DR")){
			
			boolean isPrinted = false;
			
			isPrinted = ReportCtl.startDocumentPrint(ReportEngine.ORDER, morder.getC_Order_ID(), null, panel.getWindowNo(), true);
			MPrintFormat mPrintFormat = new MPrintFormat(ctx, posConf.getAD_BarcodePrintFormat_ID(), morder.get_TrxName());
			isPrinted = ReportCtl.startDocumentPrint(ReportEngine.ORDER, mPrintFormat, morder.get_ID(), null, panel.getWindowNo(), "");
			
			if(isPrinted){
				morder.setIsPrinted(isPrinted);
				morder.processIt("PR");
				morder.save();
				trx.commit();
				
				this.panel.setHeaderInfor(this.morder);
			}
		}
	}

	public void setC_Bpartner_ID(int C_Bpartner_ID) {
		
		this.C_Bpartner_ID = C_Bpartner_ID;
	}
	
	public int getC_Bpartner_ID() {
		return C_Bpartner_ID;
	}

	public MCPOSConfigiration getPosConf() {
		return posConf;
	}
	
	public POSPanel getPanel() {
		return panel;
	}
	
	public MOrder getMorder(){
		return this.morder;
	}
	
	protected BigDecimal getFreeQty(int M_product_ID){
		
		//no need to check stock in non stocked items
		
		MProduct product = new MProduct(ctx, M_product_ID, trx.getTrxName());
		if(!product.isStocked())
			return new BigDecimal(99999);
		
		BigDecimal qtyAvailable = new BigDecimal(0); 
		String sql = "SELECT QTYAVAILABLE FROM RV_WarehousePrice "
				+ "WHERE M_Product_ID=? AND M_PriceList_Version_ID=? AND M_Warehouse_ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement (sql, trx.getTrxName());
			pstmt.setInt (1, M_product_ID);
			pstmt.setInt(2, getPosConf().getM_PriceListCash_ID());
			pstmt.setInt(3, getPosConf().getM_Warehouse_ID());
			
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				qtyAvailable = rs.getBigDecimal(1);
			rs.close ();
			pstmt.close ();
			pstmt = null;
			
		} catch (Exception e){ s_log.log(Level.SEVERE, sql, e); }
		
		try{
			if (pstmt != null) pstmt.close ();
			pstmt = null;
		}catch (Exception e){ pstmt = null; }
		
		BigDecimal notReserved = MOrderLine.getNotReserved(ctx, 
				morder.getM_Warehouse_ID(),
				M_product_ID, 0, 0);
		
		
		
		notReserved = (notReserved == null) ? new BigDecimal("0"):notReserved;
		
		
		
		return qtyAvailable.subtract(notReserved);
	}
	
	protected void voidOrder(int C_Order_ID){
		
		MOrder order = new MOrder(ctx, C_Order_ID, trx.getTrxName());
		
		if(order.getDocStatus().equals("DR")){
			
			order.setDocStatus("VO");
			order.voidIt();
			order.save();
			trx.commit();
		}else{
			new POSError(this.panel.getFrame(), "Processed Order!", "You are not allowed to make changes!");
		}
	}
	
	 void dispose(FormFrame frame){

		trx.close(); trx = null;
		frame.dispose();
	}
	
	 protected void zoomOut(int C_Order_ID){
		 
		 this.morder = new MOrder(ctx, C_Order_ID, trx.getTrxName());
		 this.updateInfor();
	 }
	 
	 protected boolean changeQty(int C_OrderLine_ID , int oderedQty){
		 
		 boolean status = false;
		 
		 if(oderedQty <= 0){
			 new POSError(this.panel.getFrame(), "Quantity error", "Should be greater than 0!");
			 return status;
		 } 
		 
		 MOrderLine ol = new MOrderLine(ctx, C_OrderLine_ID, trx.getTrxName());
		 
		 if(ol == null || ol.get_ID() == -1) return status;
		 
		//customer return
		 if(ol.getC_Order().getC_DocTypeTarget_ID() == this.posConf.getC_DocTypeReturn_ID()){
			
			//check base order line amount
			int C_PhamacyReturn_ID = MCPhamacyReturn.getReturnLine((MOrder) ol.getC_Order(), ol.getC_Order().getC_Order_ID(), ol.get_ID());
			MCPhamacyReturn pr = new MCPhamacyReturn(ctx, C_PhamacyReturn_ID, trx.getTrxName());
			//base quantity should lass than or equal return amount
			
			BigDecimal baseQty =  pr.getBaseQty().multiply(new BigDecimal(-1));
			BigDecimal reQty = new BigDecimal(oderedQty * -1);
			
			if(baseQty.compareTo(reQty) != 1){
				
				pr.setReturnQty(reQty);
				ol.setQty(reQty);
				pr.save();
				
			}else{
				new POSError(this.panel.getFrame(), "Quantity error", "Should be less than or equal with delivered quantity!");
				return false;
			}
			 
		//Pharmacy Selling
		 }else{
			 
			 int M_Product_ID = ol.getM_Product_ID();
			 BigDecimal freeQty = this.getFreeQty(M_Product_ID);
			 freeQty = freeQty.add(ol.getQtyEntered());
			  
			 if(oderedQty <= freeQty.intValue())
				 ol.setQty(new BigDecimal(oderedQty));
			 else{
				 new POSError(this.panel.getFrame(), "Insufficient Inventory", " Free Quantity : "+ freeQty  + " Ordered Quantity : " + oderedQty);
				 return false;
			 } 
		 }
		 
		 status = ol.save(trx.getTrxName());
		 this.updateInfor();
		 trx.commit();
		 
		 return status;
	 }
	 
	 protected boolean returnOrder(String docNo){
		 
		 boolean status = false;
		 
		 final String whereClause = "DOCUMENTNO = ? AND AD_Org_ID = ? AND ISSOTRX = 'Y' AND DOCSTATUS = 'CO' ";
			MOrder order = new Query(ctx, MOrder.Table_Name, whereClause, null)
			.setParameters(docNo,Env.getAD_Org_ID(ctx))
			.firstOnly();
			
			if(order != null){
				
				//create new order for return
				MOrder reOrder = new MOrder(ctx, 0, trx.getTrxName());
				reOrder.setIsSOTrx(true);
				reOrder.setC_BPartner_ID(order.getC_BPartner_ID());
				reOrder.setM_PriceList_ID(order.getM_PriceList_ID());
				reOrder.setC_DocTypeTarget_ID(this.getPosConf().getC_DocTypeReturn_ID());
				reOrder.save(trx.getTrxName());
				
				MOrderLine [] lines = order.getLines();
				
				for(MOrderLine line : lines){
					
					//validate for already return line
					int i = MCPhamacyReturn.getBaseLine(reOrder, order.get_ID(), line.get_ID());
					
					if(i != -1) continue;
					
					MOrderLine rol = new MOrderLine(reOrder);
					MOrderLine.copyValues(line, rol);
					rol.setC_Order_ID(reOrder.get_ID());
					//set quantity minus
					rol.setQty(line.getQtyEntered().multiply(new BigDecimal(-1)));
					rol.setQtyDelivered(new BigDecimal(0));
					rol.setQtyInvoiced(new BigDecimal(0));
					
					//copy attribute set instance from M_Tansaction
					int mAttInstaId = this.getAtributeSetInstatnce(line.get_ID());
					rol.setM_AttributeSetInstance_ID(mAttInstaId);
					
					rol.save(trx.getTrxName());
					
					//create return order reference
					MCPhamacyReturn pr = new MCPhamacyReturn(ctx, 0, trx.getTrxName());
					pr.setBaseOrderID(order.get_ID());
					pr.setReturnOrderID(reOrder.get_ID());
					pr.setBaseOrderLineID(line.get_ID());
					pr.setReturnOrderLineID(rol.get_ID());
					pr.setBaseQty(line.getQtyOrdered());
					pr.setReturnQty(rol.getQtyEntered());
					pr.save(trx.getTrxName());
				}
				
				trx.commit();
				this.morder = reOrder;
				this.updateInfor();
				
				int countOderine = order.getLines().length;
				int counteturn = reOrder.getLines().length;
				
				if(countOderine != counteturn){
					new POSError(this.panel.getFrame() , "Already returned order" , "You are qualified to return only "+counteturn  + " items");
				}
				
				status = true;
			}else{
				new POSError(this.panel.getFrame(), "Incorrect Order No", "System can not find entered order!");
			}
			
			return status;
	 }
	 
	 private int getAtributeSetInstatnce(int C_ORDERLINE_ID){
		 
		 String sql = "SELECT M_TRANSACTION.M_ATTRIBUTESETINSTANCE_ID FROM M_TRANSACTION  "
	 		+ "INNER JOIN M_INOUTLINE ON M_TRANSACTION.M_INOUTLINE_ID = M_INOUTLINE.M_INOUTLINE_ID "
	 		+ "INNER JOIN C_ORDERLINE ON C_ORDERLINE.C_ORDERLINE_ID = M_INOUTLINE.C_ORDERLINE_ID "
	 		+ "WHERE C_ORDERLINE.C_ORDERLINE_ID = ? "
	 		+ "AND C_ORDERLINE.AD_ORG_ID = M_TRANSACTION.AD_ORG_ID "
	 		+ "AND M_INOUTLINE.AD_ORG_ID = C_ORDERLINE.AD_ORG_ID";
		 
		 return DB.getSQLValue(trx.getTrxName(), sql , C_ORDERLINE_ID);
	 }
	 
	 protected void setPoRefference(String reference){
		 
		 morder.setPOReference(reference);
		 morder.save();
		 trx.commit();
	 }
	 
	 //create new requisition
	 
	 protected MMovement newRequisition(){
		 
		 MMovement mov = new MMovement(ctx, 0 , trx.getTrxName());
		 mov.setC_DocType_ID(this.posConf.getC_DocTypeMateMov_ID());
		 mov.setAD_Org_ID(Env.getAD_Org_ID(ctx));
		 
		 mov.save(trx.getTrxName());
		 return mov;
	 }
	 
	 protected void newRequisitionLine(MMovement movement , int M_Product_ID , BigDecimal packQty){
		 
		 //validate for already added line
		 
		 String WhereClause = "M_MOVEMENT_ID = "+movement.get_ID() + " AND M_Product_ID = " + M_Product_ID + ""
	 		+ " AND AD_Org_ID = " + movement.getAD_Org_ID() + " AND ISACTIVE = 'Y'";
		 
		 int [] i = MMovementLine.getAllIDs(MMovementLine.Table_Name, WhereClause, trx.getTrxName());
		 
		 
		 if(i.length > 0){
			 
			 new POSError(this.panel.getFrame(), "Duplicate product line", "You have already added this product!");
			 
		 }else{
			 MProduct product = new MProduct(ctx, M_Product_ID, trx.getTrxName());
			 MMovementLine line = new MMovementLine(movement);
			 line.setM_Product_ID(M_Product_ID);
			 line.setMovementQty(packQty.multiply(product.getUnitsPerPallet()));
			 line.set_CustomColumn("PackQty", packQty);
			 line.setM_Locator_ID(MLocator.getDefault((MWarehouse) this.getPosConf().getM_WarehouseSource()).get_ID());
			 line.setM_LocatorTo_ID(product.getM_Locator_ID());
			 line.save(trx.getTrxName());
		 }
		 
		 
		 //trx.commit();
	 }
	 
	 protected void removeRequisitionLine(int M_MovementLine_ID){
		 
		 MMovementLine line = new MMovementLine(ctx ,M_MovementLine_ID , trx.getTrxName());
		 line.delete(true, trx.getTrxName());
		 line.save();
		 trx.commit();
	 }
	 
	 protected void changePriceList(int M_PriceList_ID){
		 
		 for(MOrderLine line : this.morder.getLines(true, null)){
			 line.setPrice(M_PriceList_ID);
			 line.save();
		 }
		 trx.commit();
	 }
}
