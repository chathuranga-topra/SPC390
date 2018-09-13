package org.compiere.process;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MDocType;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.util.DB;
import org.compiere.util.Env;

//Topra customization
//Chathuranga - 2016/06/4
//Create a database colum name as packqty and type as numaric
//Inventory move window

//org.compiere.process.CalculatePackWiseMovementQty.getMovementQty
public class CalculatePackWiseMovementQty extends CalloutEngine{
	
	public String getMovementQty (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		try{
			int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
			if(M_Product_ID!=0){
				
				BigDecimal packqty = (BigDecimal) mTab.getValue("packqty");
				BigDecimal movementqty;
				if(packqty.compareTo(new BigDecimal(0))!=0){
					String sql = "select dividerate from c_uom_conversion where m_product_id = " +M_Product_ID;
					int i = DB.getSQLValue (mTab.getTrxInfo(), sql);

					//if unit of messure is not available
					if(i>0){
						movementqty = packqty.multiply(new BigDecimal(i));
					}
					else{
						movementqty = packqty;
					}
					mTab.setValue("movementqty" ,movementqty);
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	
	//org.compiere.process.CalculatePackWiseMovementQty.Tranfer
	public void Tranfer (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{	
		if(value == null || mTab.getValue("M_Product_ID") == null) return;
		
		GridTab pt= mTab.getParentTab();
		String C_DOCTYPETARGET_ID = pt.getValue("C_DocType_ID").toString();
		//validate null product
		
		MProduct mProduct = MProduct.get(ctx, Integer.parseInt(mTab.getValue("M_Product_ID").toString()));
		//this is base UOM
		MUOMConversion [] mConversions = MUOMConversion.getProductConversions(ctx, mProduct.get_ID());
		
		BigDecimal dr = new BigDecimal(1);
		BigDecimal enteredQty = new BigDecimal(mTab.getValue("PACKQTY").toString());
		BigDecimal trsQty = new BigDecimal(0);
		
		for(MUOMConversion i : mConversions){
			if(i.getC_UOM_Conversion_ID()!=0){
				dr = i.getDivideRate();
			}
		}
		
		trsQty = enteredQty.multiply(dr);
		mTab.setValue("qtyinternaluse", trsQty);
	}
}
