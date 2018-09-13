package org.topra.modelValidator;

import java.math.BigDecimal;

import org.compiere.model.MClient;
import org.compiere.model.MMovement;
import org.compiere.model.MOrderLine;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;

//org.topra.modelValidator.Validator
public class Validator  implements ModelValidator{

	private int		m_AD_Client_ID = -1;
	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		
		if (client != null)
			m_AD_Client_ID = client.getAD_Client_ID();
		
		engine.addModelChange(MOrderLine.Table_Name, this);
		engine.addDocValidate(MMovement.Table_Name, this);
	}

	@Override
	public int getAD_Client_ID() { return m_AD_Client_ID; }
	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null;}
	@Override
	public String modelChange(PO po, int type) throws Exception {
		
		if(po.get_TableName().equals(MOrderLine.Table_Name)){//whole sale discount setting
		
			WholeSaleDiscount wsDiscount = new WholeSaleDiscount((MOrderLine) po);
			
			if((type == TYPE_AFTER_NEW || type == TYPE_AFTER_DELETE))
				wsDiscount.setDiscount();
			
			else if(po.get_TableName().equals(MOrderLine.Table_Name) && (type == TYPE_AFTER_CHANGE)){
				
				MOrderLine ol = (MOrderLine) po;
				BigDecimal ov = (BigDecimal) ol.get_ValueOld("QtyEntered");
				if(ov.compareTo(ol.getQtyEntered())!=0)
					wsDiscount.setDiscount();
			}
		}
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		
		if(po.get_TableName().equals(MMovement.Table_Name) && timing == TIMING_BEFORE_COMPLETE){
			
			//validate invetory movement minus quantity
			InventoryMovement move = new InventoryMovement();
			move.blockMinusMovement((MMovement) po);
		}
		
		return null;
	}

}
