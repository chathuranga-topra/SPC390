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

/** Generated Model for C_WholeSaleReturn
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_C_WholeSaleReturn extends PO implements I_C_WholeSaleReturn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171028L;

    /** Standard Constructor */
    public X_C_WholeSaleReturn (Properties ctx, int C_WholeSaleReturn_ID, String trxName)
    {
      super (ctx, C_WholeSaleReturn_ID, trxName);
      /** if (C_WholeSaleReturn_ID == 0)
        {
			setC_WholeSaleReturn_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_WholeSaleReturn (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_WholeSaleReturn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BaseOrderID.
		@param BaseOrderID BaseOrderID	  */
	public void setBaseOrderID (int BaseOrderID)
	{
		set_Value (COLUMNNAME_BaseOrderID, Integer.valueOf(BaseOrderID));
	}

	/** Get BaseOrderID.
		@return BaseOrderID	  */
	public int getBaseOrderID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BaseOrderID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BaseOrderLineID.
		@param BaseOrderLineID BaseOrderLineID	  */
	public void setBaseOrderLineID (int BaseOrderLineID)
	{
		set_Value (COLUMNNAME_BaseOrderLineID, Integer.valueOf(BaseOrderLineID));
	}

	/** Get BaseOrderLineID.
		@return BaseOrderLineID	  */
	public int getBaseOrderLineID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BaseOrderLineID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BaseQty.
		@param BaseQty BaseQty	  */
	public void setBaseQty (BigDecimal BaseQty)
	{
		set_Value (COLUMNNAME_BaseQty, BaseQty);
	}

	/** Get BaseQty.
		@return BaseQty	  */
	public BigDecimal getBaseQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BaseQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set C_WholeSaleReturn ID.
		@param C_WholeSaleReturn_ID C_WholeSaleReturn ID	  */
	public void setC_WholeSaleReturn_ID (int C_WholeSaleReturn_ID)
	{
		if (C_WholeSaleReturn_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_WholeSaleReturn_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_WholeSaleReturn_ID, Integer.valueOf(C_WholeSaleReturn_ID));
	}

	/** Get C_WholeSaleReturn ID.
		@return C_WholeSaleReturn ID	  */
	public int getC_WholeSaleReturn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_WholeSaleReturn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ReturnOrderID.
		@param ReturnOrderID ReturnOrderID	  */
	public void setReturnOrderID (int ReturnOrderID)
	{
		set_Value (COLUMNNAME_ReturnOrderID, Integer.valueOf(ReturnOrderID));
	}

	/** Get ReturnOrderID.
		@return ReturnOrderID	  */
	public int getReturnOrderID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReturnOrderID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ReturnOrderLineID.
		@param ReturnOrderLineID ReturnOrderLineID	  */
	public void setReturnOrderLineID (int ReturnOrderLineID)
	{
		set_Value (COLUMNNAME_ReturnOrderLineID, Integer.valueOf(ReturnOrderLineID));
	}

	/** Get ReturnOrderLineID.
		@return ReturnOrderLineID	  */
	public int getReturnOrderLineID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReturnOrderLineID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ReturnQty.
		@param ReturnQty ReturnQty	  */
	public void setReturnQty (BigDecimal ReturnQty)
	{
		set_Value (COLUMNNAME_ReturnQty, ReturnQty);
	}

	/** Get ReturnQty.
		@return ReturnQty	  */
	public BigDecimal getReturnQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ReturnQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}