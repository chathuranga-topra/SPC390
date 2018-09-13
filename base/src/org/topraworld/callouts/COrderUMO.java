package org.topraworld.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_M_InventoryLine;
import org.compiere.model.MDocType;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;

public class COrderUMO extends CalloutEngine{

	//org.topraworld.callouts.COrderUMO.setUMOPack
	//Whole Sale
	public void setUMOPack(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		if(value == null) return;
		GridTab pt= mTab.getParentTab();
		String C_DOCTYPETARGET_ID = pt.getValue("C_DOCTYPETARGET_ID").toString();
		
		//validate only for purchase orders and whole sale cash and whole sale credit, quotation and whole cash and credit return
		if(!(C_DOCTYPETARGET_ID.equals("1000064") || C_DOCTYPETARGET_ID.equals("1000067") || C_DOCTYPETARGET_ID.equals("1000063") || C_DOCTYPETARGET_ID.equals("1000016") || C_DOCTYPETARGET_ID.equals("1000062") ||C_DOCTYPETARGET_ID.equals("1000052") || C_DOCTYPETARGET_ID.equals("1000053"))) 
			return;
		
		MProduct mProduct = MProduct.get(ctx, Integer.parseInt(value.toString()));
		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID");
		int StdPrecision = MPriceList.getStandardPrecision(ctx, M_PriceList_ID);
		//this is base UOM
		MUOMConversion [] mConversions = MUOMConversion.getProductConversions(ctx, mProduct.get_ID());
		int C_UOM_ID = 0;
		
		for(MUOMConversion i : mConversions){
			if(i.getC_UOM_Conversion_ID()!=0){
				C_UOM_ID = i.getC_UOM_To().getC_UOM_ID();
				mTab.setValue("C_UOM_ID", C_UOM_ID);
				mTab.setValue("qtyordered", i.getDivideRate());
				//set price entered
				BigDecimal priceentered = new BigDecimal(mTab.getValue("priceentered").toString()).setScale(StdPrecision, BigDecimal.ROUND_HALF_UP);  
				mTab.setValue("priceentered", priceentered.multiply(i.getDivideRate()));
			}
		}
	}
	
	//org.topraworld.callouts.COrderUMO.setUMOPack_Transfer
	public void setUMOPack_Transfer(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		if(value == null) return;
		
		I_M_InventoryLine line = GridTabWrapper.create(mTab, I_M_InventoryLine.class);
		MDocType mDocType = new MDocType(ctx, line.getM_Inventory().getC_DocType_ID(), mTab.getTrxInfo());
		
		System.out.println("Hello " + mDocType.get_ValueAsBoolean("IS_MINUS_ALLOW"));
		
		MProduct mProduct = MProduct.get(ctx, Integer.parseInt(value.toString()));
		//this is base UOM
		MUOMConversion [] mConversions = MUOMConversion.getProductConversions(ctx, mProduct.get_ID());
		
		for(MUOMConversion i : mConversions){
			if(i.getC_UOM_Conversion_ID()!=0){
				mTab.setValue("C_UOM_ID", i.getC_UOM_To().getC_UOM_ID());
				mTab.setValue("qtyordered", i.getDivideRate());
			}
		}
		
	}
}

