package org.topraworld.pos;

import javax.swing.table.DefaultTableModel;

import org.compiere.model.MOrderLine;
import org.compiere.model.MWarehousePrice;
import java.lang.Object;
import java.math.BigDecimal;

public class QueryProduct {
	
	protected void queryProduct(ProductInfor productInfor) {
		
		String query = productInfor.getSearchKey().getText();
		if (query == null || query.length() == 0 || query.length() == 1)
			return;
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		try { 
			Integer.parseInt(query); 
		} catch (Exception e) { 
			allNumber = false;
		}
		
		if(allNumber && query.length() < 4){
			return;
		}
		
		String Value = query;
		String Name = query;
		String UPC = (allNumber ? query : null);
		String SKU = (allNumber ? query : null);
		MWarehousePrice[] results = null;
		
		results = MWarehousePrice.find (productInfor.getPosPanel().getCtx(),
				productInfor.getPosPanel().getPosModel().getPosConf().getM_PriceListCash_ID(), 
				productInfor.getPosPanel().getPosModel().getPosConf().getM_Warehouse_ID(),
				Value, Name, UPC, SKU, null);
		
		DefaultTableModel model = (DefaultTableModel) productInfor.getTbInfo().getModel();
		model.setRowCount(0);
		 
		for(MWarehousePrice r : results){
			
			//non stocked items
			if(r.getQtyOnHand().intValue() == 99999){
				r.setQtyAvailable(r.getQtyOnHand());
			}
			
			BigDecimal notReserved = MOrderLine.getNotReserved(productInfor.getPosPanel().getCtx(), 
					productInfor.getPosPanel().getPosModel().getPosConf().getM_Warehouse_ID(),
					r.getM_Product_ID(), 0, 0);
			
			notReserved = notReserved == null ?new BigDecimal("0"):notReserved;
			model.addRow(new Object[]{r.getM_Product_ID() , r.getValue() , r.getName() ,r.getQtyAvailable().subtract(notReserved), r.get_Value("GENERIC") , r.get_Value("UNITSPERPALLET") ,r.getPriceList()});
		}
	}
}
