package org.topraworld.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class PackTabletCount extends CalloutEngine{

	//Physical Inventory lines pack tablet convertion 
	//org.topraworld.callouts.PackTabletCount.setPhyInvPackTbl
	public String setPhyInvPackTbl(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		//validate for null pointer exception
		if(value == null) return "";
	
		String m_product_id =  mTab.getValue("m_product_id") == null ?"":mTab.getValue("m_product_id").toString();//
		BigDecimal packqty = new BigDecimal((mTab.get_ValueAsString("PACKQTY").equals("")?"0":mTab.get_ValueAsString("PACKQTY")));
		
		if(!m_product_id.equals("") && packqty.compareTo(new BigDecimal(0))!=0){
			BigDecimal tblqty;
			String sql = "select dividerate from c_uom_conversion where m_product_id = " +m_product_id;
			int i = DB.getSQLValue (mTab.getTrxInfo(), sql);
			
			//if unit of messure is not available
			if(i>0)
				tblqty = packqty.multiply(new BigDecimal(i));
			else
				tblqty = packqty;
			mTab.setValue("qtycount" ,tblqty);
			
		}else
			mTab.setValue("qtycount" ,new BigDecimal(0));
		
		return "";
	}
	
	//Transfer in out
	
}
