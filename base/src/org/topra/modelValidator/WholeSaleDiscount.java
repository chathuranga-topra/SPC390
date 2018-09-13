package org.topra.modelValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.util.DB;
import org.topraworld.model.MCHeaderDiscount;
import org.topraworld.model.MCHeaderDiscountLine;

public class WholeSaleDiscount{
	
	private MOrderLine mOrderLine;
	
	public WholeSaleDiscount(MOrderLine mOrderLine){
		
		this.mOrderLine = mOrderLine;
	}
	
	private int getC_HEADERDISCOUNT_ID(MOrder mOrder){
		
		String sql = "SELECT C_HEADERDISCOUNT_ID FROM C_HEADERDISCOUNT WHERE C_DOCTYPETARGET_ID = ? AND AD_ORG_ID=? AND ISACTIVE = 'Y'";
		return DB.getSQLValue(mOrder.get_TrxName() , sql, mOrder.getC_DocTypeTarget_ID() , mOrder.getAD_Org_ID());
	}
	//
	private BigDecimal getDiscount(int C_HEADERDISCOUNT_ID , BigDecimal mOrderTotal){
		
		MCHeaderDiscount headerDis = new MCHeaderDiscount(mOrderLine.getCtx(), C_HEADERDISCOUNT_ID, mOrderLine.get_TrxName());
		BigDecimal DISCOUNT = new BigDecimal(0);
		BigDecimal GRANDTOTAL = null;
		int [] lines = headerDis.getLines();
		MCHeaderDiscountLine hdLine = null;
		
		for(int i : lines){
			
			hdLine = new MCHeaderDiscountLine(mOrderLine.getCtx(), i, mOrderLine.get_TrxName());
			GRANDTOTAL = hdLine.getGrandTotal();
			
			if(mOrderTotal.compareTo(GRANDTOTAL) >= 0)
				DISCOUNT = hdLine.getDiscount();
		}
		
		return DISCOUNT;
	}
	
	protected void setDiscount(){
		
		MOrder mOrder = (MOrder) this.mOrderLine.getC_Order();
		int C_HEADERDISCOUNT_ID = this.getC_HEADERDISCOUNT_ID(mOrder);
		if(C_HEADERDISCOUNT_ID <= 0)
			return;
		
		BigDecimal dis;
		BigDecimal discount = getDiscount(C_HEADERDISCOUNT_ID, mOrder.getGrandTotal());
			
		for(MOrderLine line : mOrder.getLines()){
				
			dis = discount;
			dis = dis.divide(new BigDecimal(100));
			dis = new BigDecimal(1).subtract(dis);
			BigDecimal np = line.getPriceList().multiply(dis).setScale(4,RoundingMode.HALF_UP);
			line.setPriceActual(np);
			line.setPriceEntered(np.multiply(line.getProduct().getUnitsPerPallet()));
			line.save();
		}
	}
}
