/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.topraworld.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for C_PhamacyReturn
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_C_PhamacyReturn extends PO implements I_C_PhamacyReturn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171011L;

    /** Standard Constructor */
    public X_C_PhamacyReturn (Properties ctx, int C_PhamacyReturn_ID, String trxName)
    {
      super (ctx, C_PhamacyReturn_ID, trxName);
      /** if (C_PhamacyReturn_ID == 0)
        {
			setC_PhamacyReturn_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_PhamacyReturn (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_PhamacyReturn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set C_BaseOrder_ID.
		@param BaseOrderID 
		Order
	  */
	public void setBaseOrderID (int BaseOrderID)
	{
		set_Value (COLUMNNAME_BaseOrderID, Integer.valueOf(BaseOrderID));
	}

	/** Get C_BaseOrder_ID.
		@return Order
	  */
	public int getBaseOrderID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BaseOrderID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_BaseOrderLine_ID.
		@param BaseOrderLineID 
		Sales Order Line
	  */
	public void setBaseOrderLineID (int BaseOrderLineID)
	{
		set_Value (COLUMNNAME_BaseOrderLineID, Integer.valueOf(BaseOrderLineID));
	}

	/** Get C_BaseOrderLine_ID.
		@return Sales Order Line
	  */
	public int getBaseOrderLineID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BaseOrderLineID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BaseQty.
		@param BaseQty 
		Quantity
	  */
	public void setBaseQty (BigDecimal BaseQty)
	{
		set_Value (COLUMNNAME_BaseQty, BaseQty);
	}

	/** Get BaseQty.
		@return Quantity
	  */
	public BigDecimal getBaseQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BaseQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set C_PhamacyReturn ID.
		@param C_PhamacyReturn_ID C_PhamacyReturn ID	  */
	public void setC_PhamacyReturn_ID (int C_PhamacyReturn_ID)
	{
		if (C_PhamacyReturn_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PhamacyReturn_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PhamacyReturn_ID, Integer.valueOf(C_PhamacyReturn_ID));
	}

	/** Get C_PhamacyReturn ID.
		@return C_PhamacyReturn ID	  */
	public int getC_PhamacyReturn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PhamacyReturn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ReturnOrderID.
		@param ReturnOrderID 
		Order
	  */
	public void setReturnOrderID (int ReturnOrderID)
	{
		set_Value (COLUMNNAME_ReturnOrderID, Integer.valueOf(ReturnOrderID));
	}

	/** Get ReturnOrderID.
		@return Order
	  */
	public int getReturnOrderID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReturnOrderID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_ReturnOrderLine_ID.
		@param ReturnOrderLineID 
		Sales Order Line
	  */
	public void setReturnOrderLineID (int ReturnOrderLineID)
	{
		set_Value (COLUMNNAME_ReturnOrderLineID, Integer.valueOf(ReturnOrderLineID));
	}

	/** Get C_ReturnOrderLine_ID.
		@return Sales Order Line
	  */
	public int getReturnOrderLineID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReturnOrderLineID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ReturnQty.
		@param ReturnQty 
		Quantity
	  */
	public void setReturnQty (BigDecimal ReturnQty)
	{
		set_Value (COLUMNNAME_ReturnQty, ReturnQty);
	}

	/** Get ReturnQty.
		@return Quantity
	  */
	public BigDecimal getReturnQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ReturnQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}