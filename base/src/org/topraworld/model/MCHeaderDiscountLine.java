package org.topraworld.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MCHeaderDiscountLine extends X_C_HeaderDiscountLine{

	private static final long serialVersionUID = -4782588023024625867L;

	public MCHeaderDiscountLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCHeaderDiscountLine(Properties ctx, int C_HeaderDiscountLine_ID,
			String trxName) {
		super(ctx, C_HeaderDiscountLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
