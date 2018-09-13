
//Topra customization
//Chathuranga
//2016-06-10
//generate the listprice and unit price based on the pack price

package org.compiere.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class POPackPrice extends CalloutEngine{
	
	public void setListPrice(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		try{
			
			String sql = "select stdprecision from c_currency where c_currency_id  = 313";
			
			int stdprecision = DB.getSQLValue(mTab.getTrxInfo(), sql);
			if(!(stdprecision > 0))
				stdprecision = 4;
			
			BigDecimal packPrice = (BigDecimal) mTab.getValue("PRICEPACK");
			if(packPrice.compareTo(BigDecimal.ZERO) !=0 && packPrice!=null){
				
				BigDecimal qty = (BigDecimal) mTab.getValue("QtyEntered");
				BigDecimal poqty = (BigDecimal) mTab.getValue("QtyOrdered");
				BigDecimal packSize = poqty.divide(qty);
				BigDecimal dicount = (BigDecimal) mTab.getValue("Discount");
				
				dicount =new BigDecimal(1).subtract(dicount.divide(new BigDecimal(100)));
				
				BigDecimal unitperpackPrice =  packPrice.divide(packSize, RoundingMode.valueOf(4));
				//Limit the Presision
				unitperpackPrice = unitperpackPrice.setScale(4, BigDecimal.ROUND_HALF_UP);
				
				mTab.setValue("PriceList", unitperpackPrice);
				mTab.setValue("priceentered", unitperpackPrice);
				mTab.setValue("priceactual", unitperpackPrice.multiply(dicount));
			}
			}catch (Exception ex){
				ex.printStackTrace();
			}
	}	
}
