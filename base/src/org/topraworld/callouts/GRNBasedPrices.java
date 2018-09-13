package org.topraworld.callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.compiere.apps.ADialog;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.MUOMConversion;

//To create prices based on the GRN price this class in created..
//This class is refered to the "org.compiere.process.M_PriceList_Create" process and made a new field in M_PriceList_Version table
//The field called ""

public class GRNBasedPrices extends CalloutEngine{
	
	private BigDecimal pprice = new BigDecimal("0");
	private BigDecimal UNIT_COST = new BigDecimal("0");
	private BigDecimal UNIT_WS_PRICE = new BigDecimal("0");
	private BigDecimal UNIT_RETAIL_PRICE = new BigDecimal("0");
	private BigDecimal discountvalue = new BigDecimal("0");
	private BigDecimal discount = new BigDecimal("0");
	private BigDecimal unitPprice = new BigDecimal("0");
	private MProduct mProduct;
	private MTax mTax;
	private BigDecimal packsize = new BigDecimal("1");
	
	
	//when changing pack pruchase price
	//org.topraworld.callouts.GRNBasedPrices.set_by_packprice
	public void set_by_packprice(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		if(mTab.getValue("m_product_id") == null && Double.parseDouble(value.toString()) > 0){
			ADialog.error(WindowNo, null,"Please select a product!");
			return;
		}
		
		int C_Tax_ID = mTab.getValue("C_Tax_ID") == null?0:Integer.parseInt(mTab.getValue("C_Tax_ID").toString());
		int M_PRODUCT_ID = mTab.getValue("M_PRODUCT_ID") == null?0:Integer.parseInt(mTab.getValue("M_PRODUCT_ID").toString());
		discount = new BigDecimal(mTab.getValue("DISCOUNT").toString());
		mTax = new MTax(ctx, C_Tax_ID , mTab.getTrxInfo());
		mProduct = new MProduct(ctx, M_PRODUCT_ID , mTab.getTrxInfo());
		MUOMConversion [] mConversions = MUOMConversion.getProductConversions(ctx, mProduct.get_ID());
		
		for(MUOMConversion muomConversion : mConversions){
			packsize = muomConversion.getDivideRate();
		}
		
		pprice = new BigDecimal(value.toString());
		unitPprice = pprice.divide(packsize , 3 , RoundingMode.HALF_UP);
		discountvalue = unitPprice.multiply(discount).divide(new BigDecimal("100") ,3 , RoundingMode.HALF_UP);
		
		UNIT_COST = (unitPprice.subtract(discountvalue)).setScale(3, RoundingMode.HALF_UP);
		UNIT_RETAIL_PRICE = unitPprice.multiply(new BigDecimal(1.15)).setScale(2, RoundingMode.HALF_UP);;
		UNIT_WS_PRICE = unitPprice;
		
		mTab.setValue("UNIT_COST", UNIT_COST);
		mTab.setValue("UNIT_WS_PRICE", UNIT_WS_PRICE);
		mTab.setValue("UNIT_RETAIL_PRICE", UNIT_RETAIL_PRICE);
		
		//update tax category of the product master
		setTaxCategory(this.mProduct , this.mTax);
		
	}
	
	private void setTaxCategory(MProduct mProduct , MTax mTax){
		
		if(mTax.get_ID() > 0 && mProduct.get_ID() > 0){
			mProduct.setC_TaxCategory_ID(mTax.get_ID());
			mProduct.saveEx();
		}
	}
}


