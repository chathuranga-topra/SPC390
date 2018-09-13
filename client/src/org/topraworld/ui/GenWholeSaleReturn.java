package org.topraworld.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.compiere.Adempiere;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MDocType;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MQuery;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.swing.CFrame;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.topraworld.model.MCWholeSaleReturn;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

//org.topraworld.ui.GenWholeSaleReturn
public class GenWholeSaleReturn implements FormPanel , ActionListener{

	private int	m_WindowNo = 0;
	private CLogger log = CLogger.getCLogger(getClass());
	private int m_SalesRep_ID = 0;
	private CFrame m_frame;
	private Properties m_ctx = Env.getCtx();
	private Trx trx;
	private MUser user;
	private int AD_Client_ID = 0;
	private int AD_Org_ID = 0;
	private MOrder baseOrder= null;
	
	private JButton btnCut = new JButton();
    private JButton btnCreateLine = new JButton();
    private JButton btnOk = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable tblLines = new JTable();
    private JTextField txtDocNo = new JTextField();
    private JTextField txtTotal = new JTextField();
    private JLabel lblMesssage = new JLabel();
	
	@Override
	public void init(int WindowNo, FormFrame frame) {
		// TODO Auto-generated method stub
		m_frame = frame.getCFrame();
		m_SalesRep_ID = Env.getAD_User_ID(m_ctx);
		m_WindowNo = WindowNo;
		
		//open new transaction
		trx = Trx.get(Trx.createTrxName(Trx.createTrxName()), true);
		user = new MUser(m_ctx ,m_SalesRep_ID ,trx.getTrxName());
		AD_Client_ID = Env.getAD_Client_ID(m_ctx);
		AD_Org_ID = Env.getAD_Org_ID(m_ctx); 
		
		this.initComponents();
	}
	
	@SuppressWarnings("serial")
	private void initComponents(){
		
		//action listners
		btnCut.addActionListener(this);
		btnCreateLine.addActionListener(this);
		btnOk.addActionListener(this);
		
		txtDocNo.addActionListener(this);
		
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setText("0.00");
		
        tblLines.setModel(new DefaultTableModel(
            new Object [][] {},
            new String []{
                "", 
                "C_OrderLine_ID", 
                "Search key", 
                "Name", 
                "P/S", 
                "UOM", 
                "Price", 
                "Discount", 
                "Entered qty", 
                "Unit qty", 
                "Line net amt"
            }
        ) {
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.Boolean.class, 
                java.lang.Object.class, 
                java.lang.Object.class,
                java.lang.Object.class, 
                java.lang.Object.class, 
                java.lang.Object.class, 
                java.math.BigDecimal.class, //"Price", 
                java.math.BigDecimal.class, //"Discount",
                java.math.BigDecimal.class, //"RCS80000", 
                java.math.BigDecimal.class, //"Entered qty", 
                java.math.BigDecimal.class // "Line net amt"
            };

            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            
            boolean[] canEdit = new boolean [] {
                    true, false, false, false, false, false, false, false, true, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLines.setRowHeight(20);
        tblLines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLines.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblLines);
        if (tblLines.getColumnModel().getColumnCount() > 0) {
            //tblLines.getColumnModel().getColumn(0).setResizable(false);
            //tblLines.getColumnModel().getColumn(0).setPreferredWidth(10);
            //tblLines.getColumnModel().getColumn(0).setMinWidth(10);
            tblLines.getColumnModel().getColumn(1).setMinWidth(0);
            tblLines.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblLines.getColumnModel().getColumn(1).setMaxWidth(0);
            tblLines.getColumnModel().getColumn(3).setPreferredWidth(350);
            /*tblLines.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblLines.getColumnModel().getColumn(11).setMinWidth(0);
            tblLines.getColumnModel().getColumn(11).setMaxWidth(0);*/
        }
        
        tblLines.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
            	if(e != null && e.getColumn() != -1){
            		
            		//changing quantity
            		if(e.getColumn() == 8)
            			changeQuantity(e);
            		//select un select	
            		else if(e.getColumn() == 0)
            			setTotalValue();
            	}
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );

        btnOk.setText("Create Lines");

        btnCut.setText("Close");

        btnCreateLine.setText("View");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Total Value");
        
        lblMesssage.setForeground(Color.RED);
        lblMesssage.setText("Hello Hello");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.m_frame.getContentPane());
        this.m_frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMesssage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCut, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDocNo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 378, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateLine)
                    .addComponent(jLabel1)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCut, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMesssage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            );
        
        this.m_frame.pack();
        this.m_frame.setLocationRelativeTo(null);
	}

	@Override
	public void dispose() {
		
		trx.close();
		trx = null;
		
		this.m_frame.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnCut)){
			
			this.dispose();
			
		}else if(e.getSource().equals(btnCreateLine)){
			
			if(txtDocNo.getText().length() > 0)
				this.createLines();
			
			
		}else if(e.getSource().equals(btnOk)){
			
			if(tblLines.getRowCount() > 0)
				this.createSOLines();
			
		}else if(e.getSource().equals(txtDocNo)){
			this.createLines();
		}
		
	}
	
	private void createSOLines(){
		
		if(baseOrder == null)
			return;
		
		//create return type Sales order
		MOrder reOrder = new MOrder(m_ctx, 0, trx.getTrxName());
		
		MDocType docType = (MDocType) baseOrder.getC_DocTypeTarget();
		int C_DOCTYPERETURN_ID = docType.get_ValueAsInt("C_DOCTYPERETURN_ID");
		
		if(C_DOCTYPERETURN_ID == 0){
			lblMesssage.setText("Error on document type setting! Please contact system administrator");
			ADialog.error(m_WindowNo, this.m_frame, "Error on document type setting", "Please contact system administrator!");
			return;
		}
		
		reOrder.setM_Warehouse_ID(baseOrder.getM_Warehouse_ID());
		reOrder.setIsSOTrx(true);
		reOrder.setC_BPartner_ID(baseOrder.getC_BPartner_ID());
		reOrder.setM_PriceList_ID(baseOrder.getM_PriceList_ID());
		reOrder.setC_DocTypeTarget_ID(C_DOCTYPERETURN_ID);
		reOrder.save(trx.getTrxName());
		reOrder.setSalesRep_ID(m_SalesRep_ID);
		
		MOrderLine rol = null;
		MOrderLine baseLine = null;
		int lineCreated = 0;
		
		for (int row=0; row < tblLines.getRowCount(); row++) {
			
			//get selected rows only
			if(!(boolean) tblLines.getValueAt(row, 0))
				continue;
			
			int C_OrderLine_ID = (int) tblLines.getValueAt(row, 1);
			BigDecimal packQty = (BigDecimal) tblLines.getValueAt(row, 8);
			BigDecimal eachQty = (BigDecimal) tblLines.getValueAt(row, 9);
			
			baseLine = new MOrderLine(m_ctx, C_OrderLine_ID, trx.getTrxName());
			rol = new MOrderLine(reOrder);
			
			MOrderLine.copyValues(baseLine, rol);
			rol.setC_Order_ID(reOrder.get_ID());
			rol.setProcessed(false);
			//set quantity minus
			//rol.setQty(baseLine.getQtyOrdered().multiply(new BigDecimal(-1)));
			rol.setQtyEntered(packQty.multiply(new BigDecimal(-1)));//Pack Quantity
			rol.setQtyOrdered(eachQty.multiply(new BigDecimal(-1)));//each quantity
			
			rol.setQtyDelivered(new BigDecimal(0));
			rol.setQtyInvoiced(new BigDecimal(0));
			
			//copy attribute set instance from M_Tansaction
			int mAttInstaId = this.getAtributeSetInstatnce(baseLine.get_ID());
			rol.setM_AttributeSetInstance_ID(mAttInstaId);
			rol.save(trx.getTrxName());
			
			//create return order reference
			MCWholeSaleReturn wr = new MCWholeSaleReturn(m_ctx, 0, trx.getTrxName());
			wr.setBaseOrderID(baseOrder.get_ID());
			wr.setReturnOrderID(reOrder.get_ID());
			wr.setBaseOrderLineID(baseLine.get_ID());
			wr.setReturnOrderLineID(rol.get_ID());
			wr.setBaseQty(baseLine.getQtyOrdered());
			wr.setReturnQty(rol.getQtyOrdered());
			wr.save(trx.getTrxName());
			
			lineCreated ++;
		}
		
		trx.commit();
		DefaultTableModel model = (DefaultTableModel) tblLines.getModel();
		model.setRowCount(0);
		lblMesssage.setText("Return order created ("+reOrder.getDocumentNo() +") with " + lineCreated + " lines!");
		
		//open retuen window
		
		String sql = "SELECT MAX(AD_WINDOW_ID) FROM AD_Window WHERE NAME = 'Store Return'AND ISACTIVE = 'Y'";
		
		int AD_Window_ID = DB.getSQLValue(trx.getTrxName(), sql);
		String ColumnName = "C_Order_ID";
		int Record_ID = reOrder.get_ID();
		MQuery query = MQuery.getEqualQuery(ColumnName, Record_ID);
		boolean IsSOTrx = true;
		//
		log.info("Zoom to AD_Window_ID=" + AD_Window_ID 
			+ " - " + query + " (IsSOTrx=" + IsSOTrx + ")");
		AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, query))
			return;
		AEnv.addToWindowManager(frame);
		AEnv.showCenterScreen(frame);
		frame = null;
	}
	
	private void createLines(){
		
		//clear table rows
		DefaultTableModel model = (DefaultTableModel) tblLines.getModel();
		model.setRowCount(0);
		lblMesssage.setText("");
		txtTotal.setText("0.00");
		
		String docNo = txtDocNo.getText();
		
		String where = "DOCUMENTNO = ? AND DOCSTATUS IN ('CO' , 'CL') "
			+ "AND AD_CLIENT_ID =  ? AND AD_ORG_ID = ? AND ISSOTRX = 'Y' ";
		
		baseOrder = new Query(m_ctx, MOrder.Table_Name, where, trx.getTrxName())
					.setParameters(docNo , AD_Client_ID , AD_Org_ID).firstOnly();
		//null order validation
		if(baseOrder == null){
			lblMesssage.setText("System can not find entered order no!");
			ADialog.error(m_WindowNo, this.m_frame, "System can not find entered order no!");
			return;
		}
		
		//minus grandtotal
		if(baseOrder.getGrandTotal().intValue() <= 0){
			lblMesssage.setText("System can not find entered order no!");
			ADialog.error(m_WindowNo, this.m_frame, "System can not find entered order no!");
			return;
		}
		
		int linesCreated = 0;
		
		for(MOrderLine baseLine : baseOrder.getLines()){
			
			//validate for already return order
			if(MCWholeSaleReturn.isAlreadyReturned(baseLine)){
				continue;
			}
			//load linis to grid
			model.addRow(new Object[]{
				true , 
				baseLine.get_ID() ,//C_Orderroline_ID
				baseLine.getM_Product().getValue() ,//Search key
				baseLine.getM_Product().getName(),//Name
				baseLine.getM_Product().getUnitsPerPallet() ,//"P/S"
				baseLine.getM_Product().getC_UOM().getName(),//UOM
				baseLine.getPriceEntered(),//Price
				baseLine.getDiscount(),//Discount
				baseLine.getQtyEntered(),//Pack qty
				baseLine.getQtyOrdered(),//Return qty
				baseLine.getLineNetAmt().setScale(2, BigDecimal.ROUND_HALF_UP),//Line net amt
				//wr.get_ID()//C_WholeSaleReturn_ID
			});
			
			linesCreated ++;
		}
		
		this.setTotalValue();
		
		if(linesCreated == 0){
			lblMesssage.setText("Already returned order.");
			ADialog.error(m_WindowNo, this.m_frame, "Already returned order!", "Your are not allowed to return already returned order");
			trx.rollback();
			
		}else if(baseOrder.getLines().length == linesCreated){
			trx.commit();
			lblMesssage.setText("linesCreated : " + linesCreated);
			
		}else if(baseOrder.getLines().length > linesCreated){
			lblMesssage.setText("Already returned order. linesCreated : " + linesCreated);
			ADialog.info(m_WindowNo, this.m_frame, "Already returned order!", "Your are allowed to create only " + linesCreated + " lines.");
		}
	}
	
	private void changeQuantity(TableModelEvent e){
		
		//pack quantity
		if(e.getColumn() == 8 && tblLines.getSelectedColumn() == e.getColumn()){
			
			BigDecimal newQty = (BigDecimal) tblLines.getValueAt(e.getFirstRow() , e.getColumn());
			int C_OrderLine_ID = (int) tblLines.getValueAt(e.getFirstRow() , 1);
			MOrderLine ol = new MOrderLine(m_ctx, C_OrderLine_ID, trx.getTrxName());
			BigDecimal ps = (BigDecimal) tblLines.getValueAt(e.getFirstRow() , 4);//pack size
			
			//zero quantity
			if(newQty.intValue() <= 0){
				tblLines.setValueAt(ol.getQtyEntered(), e.getFirstRow(), e.getColumn());
				ADialog.error(m_WindowNo, this.m_frame, "Quantity Error!", "Return quantity can not be 'ZERO' or 'MINUS'");
				return;
			}
			
			//Return quantity can not be exceeded shipment quantity
			if(newQty.intValue() > ol.getQtyEntered().intValue()){
				tblLines.setValueAt(ol.getQtyEntered(), e.getFirstRow(), e.getColumn());
				ADialog.error(m_WindowNo, this.m_frame, "Quantity Error!", "Return quantity can not be exceeded shipment quantity");
				return;
			}
			
			//change prices and quantities
			BigDecimal unitQuantity = ps.multiply(newQty).setScale(0);
			BigDecimal lineNetAmt = newQty.multiply(ol.getPriceEntered()).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			tblLines.setValueAt(unitQuantity, e.getFirstRow(), 9);
			tblLines.setValueAt(lineNetAmt, e.getFirstRow(), 10);
			
			this.setTotalValue();
		}
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
	
	private void setTotalValue(){
		
		BigDecimal total = new BigDecimal(0.00);
		for (int row=0; row < tblLines.getRowCount(); row++) {
			
			//get selected rows only
			if(!(boolean) tblLines.getValueAt(row, 0))
				continue;
			
			total = total.add((BigDecimal)tblLines.getValueAt(row, 10)) ;
		}
		
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		txtTotal.setText(total.toString());
	}
}
