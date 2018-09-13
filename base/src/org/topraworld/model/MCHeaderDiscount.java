package org.topraworld.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MCHeaderDiscount extends X_C_HeaderDiscount{

	private static final long serialVersionUID = 1L;

	public MCHeaderDiscount(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCHeaderDiscount(Properties ctx, int C_HeaderDiscount_ID,
			String trxName) {
		super(ctx, C_HeaderDiscount_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public int [] getLines(){
		
		String whereClause = ""
				+ " C_HeaderDiscount_ID = "+this.get_ID()
				+ " AND AD_CLIENT_ID = "+this.getAD_Client_ID()
				+ " AND AD_ORG_ID = "+this.getAD_Org_ID()
				+ " AND ISACTIVE = 'Y'";
		
		return MCHeaderDiscountLine.getAllIDs(MCHeaderDiscountLine.Table_Name, whereClause, this.get_TrxName());
	}
}
