package org.topraworld.pos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class QueryOrders {
	
	private StringBuffer sb; 
	private String DOCSTATUS;
	private int SALESREP_ID;
	private int AD_Org_ID;
	private String trxName;
	private JTable infoTbl;
	
	public QueryOrders(Properties ctx , String trxName , JTable infoTbl){
		
		this.SALESREP_ID = Env.getAD_User_ID(ctx);
		this.AD_Org_ID = Env.getAD_Org_ID(ctx);
		this.trxName = trxName;
		this.infoTbl = infoTbl;
	}
	
	protected void getDrafBill(){
		
		this.DOCSTATUS = "DR";
		this.getData();
	}
	
	protected void getIPBill(){
		
		this.DOCSTATUS = "IP";
		this.getData();
	}
	
	protected void getCompleteBill(){
		
		this.DOCSTATUS = "CO";
		this.getData();
	}
	
	protected void getVoidBill(){
		
		this.DOCSTATUS = "VO";
		this.getData();
	}
	
	private void getData()
	{	
		sb = new StringBuffer("SELECT "
		+ " o.C_Order_ID,"
		+ " ROW_NUMBER () OVER (ORDER BY o.C_Order_ID DESC) AS LineNo,"
		+ " o.Documentno,"
		+ " bp.NAME , "
		+ " dt.NAME AS DocumentType , "
		+ " pl.NAME AS PriceList , "
		+ " COUNT(ol.C_ORDERLINE_ID) As ItemCount, "
		+ " NVL( SUM((ol.QTYORDERED * ol.PRICELIST) - (ol.QTYORDERED * ol.PRICEACTUAL) ), 0) AS Discount, "
		+ " NVL(o.GRANDTOTAL, 0) " 
		+ "	FROM"
		+ " C_DOCTYPE dt , M_PRICELIST pl , C_BPARTNER bp, "
		+ " C_Order o LEFT JOIN C_ORDERLINE ol ON o.C_ORDER_ID = ol.C_ORDER_ID "
		+ " WHERE dt.C_DOCTYPE_ID = o.C_DOCTYPETARGET_ID "
		+ " AND pl.M_PRICELIST_ID = o.M_PRICELIST_ID "
		+ " AND bp.C_BPARTNER_ID = o.C_BPARTNER_ID "
		+ " AND o.DATEORDERED = TO_CHAR(SYSDATE, 'dd-MON-yyyy') "
		+ " AND o.DOCSTATUS = ? "
		+ " AND	o.SALESREP_ID = ? "
		+ " AND o.AD_Org_ID = ? "
		+ " GROUP BY o.C_Order_ID, o.Documentno ,bp.NAME,	dt.NAME, pl.NAME,"
		+ " o.GRANDTOTAL order by o.C_Order_ID desc");
		
		PreparedStatement ps;
		ResultSet rs;
		
		try{
			
			ps = DB.prepareStatement(sb.toString(), trxName);
			ps.setString(1, DOCSTATUS);
			ps.setInt(2, SALESREP_ID);
			ps.setInt(3, AD_Org_ID);
			rs = ps.executeQuery();
			
			DefaultTableModel model = (DefaultTableModel) infoTbl.getModel();
			model.setRowCount(0);
			
			while(rs.next()){
				
				BigDecimal discount = rs.getBigDecimal(8) == null ? new BigDecimal(0.00) : rs.getBigDecimal(8).setScale(2, RoundingMode.CEILING);
				BigDecimal gt = rs.getBigDecimal(9) == null ? new BigDecimal(0.00) : rs.getBigDecimal(9).setScale(2, RoundingMode.CEILING);
										//Integer		//Integer		//String		//String		//String		//String		//String		//Integer			//BigDecimal				
				model.addRow(new Object[]{rs.getInt(1) , rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7) ,discount, gt});
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
