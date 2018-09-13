package org.topraworld.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrder;
import org.compiere.model.Query;

public class MCPhamacyReturn extends X_C_PhamacyReturn{

	public MCPhamacyReturn(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCPhamacyReturn(Properties ctx, int C_PhamacyReturn_ID,
			String trxName) {
		super(ctx, C_PhamacyReturn_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static int getBaseLine(MOrder reOrder, int baseOrderID , int baseOrderLineID){
		
		String where = "AD_Client_ID = ? AND AD_Org_ID = ? AND BASEORDERID = ? AND BASEORDERLINEID = ?";
		
		return new Query(reOrder.getCtx(), X_C_PhamacyReturn.Table_Name, where, reOrder.get_TrxName())
			.setParameters(reOrder.getAD_Client_ID() ,reOrder.getAD_Org_ID() , baseOrderID , baseOrderLineID)
			.firstId();
	}
	
	public static int getReturnLine(MOrder reOrder, int reOrderID , int reOrderLineID){
		
		String where = "AD_Client_ID = ? AND AD_Org_ID = ? AND RETURNORDERID = ? AND RETURNORDERLINEID = ?";
		
		return new Query(reOrder.getCtx(), X_C_PhamacyReturn.Table_Name, where, reOrder.get_TrxName())
			.setParameters(reOrder.getAD_Client_ID() ,reOrder.getAD_Org_ID() , reOrderID , reOrderLineID)
			.firstId();
	}
}
