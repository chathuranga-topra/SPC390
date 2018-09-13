package org.topraworld.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;


public class MCPOSConfigiration extends X_C_POSConfigiration{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MCPOSConfigiration(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCPOSConfigiration(Properties ctx, int C_POSConfigiration_ID,
			String trxName) {
		super(ctx, C_POSConfigiration_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/*public MCPOSConfigiration(int AD){
		
	}*/
	
	public static MCPOSConfigiration getPOSConfigOrg(Properties ctx, String trxName){
		
		String whereClauseFinal = "AD_Client_ID=? AND AD_Org_ID = ? AND ISACTIVE = 'Y'";
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		int AD_Org_ID = Env.getAD_Org_ID(ctx);
		
		 int id = new Query(ctx, MCPOSConfigiration.Table_Name, whereClauseFinal, trxName)
										.setParameters(AD_Client_ID ,AD_Org_ID)
										.setOrderBy(MCPOSConfigiration.COLUMNNAME_C_POSConfigiration_ID)
										.firstIdOnly();
		
		return new MCPOSConfigiration(ctx , id , trxName);
	}
}
