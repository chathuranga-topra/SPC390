package org.topraworld.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.Query;
import org.compiere.util.DB;

@SuppressWarnings("serial")
public class MCWholeSaleReturn extends X_C_WholeSaleReturn{

	public MCWholeSaleReturn(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCWholeSaleReturn(Properties ctx, int C_WholeSaleReturn_ID,
			String trxName) {
		super(ctx, C_WholeSaleReturn_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static List<MCWholeSaleReturn>  getBaseLines(MOrder reOrder, int baseOrderID , int baseOrderLineID){
		
		String where = "AD_Client_ID = ? AND AD_Org_ID = ? AND BASEORDERID = ? AND BASEORDERLINEID = ?";
		
		List<MCWholeSaleReturn> list = new Query(reOrder.getCtx(), MCWholeSaleReturn.Table_Name, 
			where.toString(), reOrder.get_TrxName())
			.setParameters(reOrder.getAD_Client_ID() ,reOrder.getAD_Org_ID() , baseOrderID , baseOrderLineID)
			.list();
		
		return list;
	}
	
	public static boolean isAlreadyReturned(MOrderLine baseLine){
		
		String sql = "SELECT CASE WHEN COUNT(wsr.BASEORDERLINEID) > 0 THEN 'Y' ELSE 'N' END "
				+ " FROM C_WHOLESALERETURN wsr INNER JOIN C_ORDER o ON wsr.RETURNORDERID = o.C_ORDER_ID "
				+ " WHERE wsr.BASEORDERID = ? AND wsr.BASEORDERLINEID = ? AND o.DOCSTATUS NOT IN ('VO')";
		
		String val = DB.getSQLValueString(baseLine.get_TrxName(), sql, baseLine.getC_Order_ID(), baseLine.getC_OrderLine_ID());
		return val.equals("Y") ? true : false;
	}
}
