package org.topra.modelValidator;

import java.math.BigDecimal;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.util.DB;

public class InventoryMovement {

	
	protected void blockMinusMovement(MMovement movement) throws AdempiereException{
		
		MMovementLine[] lines = movement.getLines(true);
		
		for(MMovementLine  line : lines){
			
			BigDecimal qtyAvailable = getQtyAvailable(line);
			
			if(qtyAvailable.compareTo(line.getMovementQty()) == -1){
				
				String message = "Insuffitient inventory : " + line.getM_Product().getName() + " : "
						+ " Available " + qtyAvailable + " Line No : " + line.getLine();
				throw new AdempiereException(message);
			}
		}
	}
	
	
	private BigDecimal getQtyAvailable(MMovementLine  line){
		
		String sql = "SELECT SUM(QtyOnHand) FROM M_Storage WHERE M_Product_ID=? AND M_Locator_ID=?";
		return DB.getSQLValueBD(line.get_TrxName(), sql, line.getM_Product_ID() , line.getM_Locator_ID());
	}
	
}
