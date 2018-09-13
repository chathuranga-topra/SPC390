package org.compiere.process;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.util.Env;

public class StopMinusQuentity extends CalloutEngine{
	
	//org.compiere.process.StopMinusQuentity.stopMinusQuentity
	public String stopMinusQuentity(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
	
		try{
			if(value == null)
				return"";
			if(mTab.getValue("M_Product_ID") == null)
				return"";
			
			int M_Product_ID = (Integer)mTab.getValue("M_Product_ID");
			MProduct product = MProduct.get (ctx, M_Product_ID);
			Integer M_AttributeSetInstance_ID = 0;
				
			if (Env.isSOTrx(ctx, WindowNo))
			{
				if (product.isStocked())
				{
					BigDecimal QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
					int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo, "M_Warehouse_ID");
					M_AttributeSetInstance_ID = Env.getContextAsInt(ctx, WindowNo, "M_AttributeSetInstance_ID");
					
					@SuppressWarnings("deprecation")
					BigDecimal available = MStorage.getQtyAvailable(M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID, null);
					
					if (available == null)
						available = Env.ZERO;
					if (available.signum() == 0)
					{
						mTab.dataIgnore();
						mTab.dataNew(false);
						GridField gf = mTab.getField("M_Product_ID");
						gf.setError(true);
					}
						
					else if (available.compareTo(QtyOrdered) < 0){
						
						mTab.dataIgnore();
						mTab.dataNew(false);
						GridField gf = mTab.getField("M_Product_ID");
						gf.setError(true);
					}	
					else
					{
						Integer C_OrderLine_ID = (Integer)mTab.getValue("C_OrderLine_ID");
						if (C_OrderLine_ID == null)
							C_OrderLine_ID = new Integer(0);
						BigDecimal notReserved = MOrderLine.getNotReserved(ctx, 
							M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID,
							C_OrderLine_ID.intValue());
						if (notReserved == null)
							notReserved = Env.ZERO;
						BigDecimal total = available.subtract(notReserved);
						if (total.compareTo(QtyOrdered) < 0)
						{	
							mTab.dataIgnore();
							mTab.dataNew(false);
							GridField gf = mTab.getField("M_Product_ID");
							gf.setError(true);
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
}
