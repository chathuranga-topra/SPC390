package org.topraworld.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.apps.ADialog;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CashBalance extends CalloutEngine{
	
	//org.topraworld.callouts.CashBalance.setBalance
	public void setBalance(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		try{
			if(value == null) 
				return ;
			
			BigDecimal cash = new BigDecimal(mTab.getValue("ORDERTYPE").toString());
			BigDecimal grandtotol = new BigDecimal(mTab.getValue("GRANDTOTAL").toString());
			if(cash.compareTo(grandtotol) >= 0){
				
				mTab.setValue("cash_balance", cash.subtract(grandtotol)+"");
				mTab.dataSave(false);
				mTab.dataRefresh();
				
			}else{
				
				mTab.setValue("cash_balance", cash.subtract(grandtotol)+"");
				mTab.dataSave(false);
				mTab.dataRefresh();
				ADialog.error(WindowNo, null, "Can not proceed! Insuffient cash.");
			}
			
		}catch(Exception ex){
			ADialog.error(WindowNo, null, "Process Failed, Invalid Input");
		}
	}
}
