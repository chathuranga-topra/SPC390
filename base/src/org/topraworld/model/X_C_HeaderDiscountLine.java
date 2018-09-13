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

/** Generated Model for C_HeaderDiscountLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_C_HeaderDiscountLine extends PO implements I_C_HeaderDiscountLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170804L;

    /** Standard Constructor */
    public X_C_HeaderDiscountLine (Properties ctx, int C_HeaderDiscountLine_ID, String trxName)
    {
      super (ctx, C_HeaderDiscountLine_ID, trxName);
      /** if (C_HeaderDiscountLine_ID == 0)
        {
			setC_HeaderDiscountLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_HeaderDiscountLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_HeaderDiscountLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set C_HeaderDiscountLine_ID.
		@param C_HeaderDiscountLine_ID C_HeaderDiscountLine_ID	  */
	public void setC_HeaderDiscountLine_ID (int C_HeaderDiscountLine_ID)
	{
		if (C_HeaderDiscountLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_HeaderDiscountLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_HeaderDiscountLine_ID, Integer.valueOf(C_HeaderDiscountLine_ID));
	}

	/** Get C_HeaderDiscountLine_ID.
		@return C_HeaderDiscountLine_ID	  */
	public int getC_HeaderDiscountLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_HeaderDiscountLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_HeaderDiscount_ID.
		@param C_HeaderDiscount_ID C_HeaderDiscount_ID	  */
	public void setC_HeaderDiscount_ID (int C_HeaderDiscount_ID)
	{
		if (C_HeaderDiscount_ID < 1) 
			set_Value (COLUMNNAME_C_HeaderDiscount_ID, null);
		else 
			set_Value (COLUMNNAME_C_HeaderDiscount_ID, Integer.valueOf(C_HeaderDiscount_ID));
	}

	/** Get C_HeaderDiscount_ID.
		@return C_HeaderDiscount_ID	  */
	public int getC_HeaderDiscount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_HeaderDiscount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discount %.
		@param Discount 
		Discount in percent
	  */
	public void setDiscount (BigDecimal Discount)
	{
		set_Value (COLUMNNAME_Discount, Discount);
	}

	/** Get Discount %.
		@return Discount in percent
	  */
	public BigDecimal getDiscount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		set_Value (COLUMNNAME_GrandTotal, GrandTotal);
	}

	/** Get Grand Total.
		@return Total amount of document
	  */
	public BigDecimal getGrandTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GrandTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Operation AD_Reference_ID=205 */
	public static final int OPERATION_AD_Reference_ID=205;
	/**  = = == */
	public static final String OPERATION_Eq = "==";
	/** >= = >= */
	public static final String OPERATION_GtEq = ">=";
	/** > = >> */
	public static final String OPERATION_Gt = ">>";
	/** < = << */
	public static final String OPERATION_Le = "<<";
	/**  ~ = ~~ */
	public static final String OPERATION_Like = "~~";
	/** <= = <= */
	public static final String OPERATION_LeEq = "<=";
	/** |<x>| = AB */
	public static final String OPERATION_X = "AB";
	/** sql = SQ */
	public static final String OPERATION_Sql = "SQ";
	/** != = != */
	public static final String OPERATION_NotEq = "!=";
	/** Set Operation.
		@param Operation 
		Compare Operation
	  */
	public void setOperation (String Operation)
	{

		set_Value (COLUMNNAME_Operation, Operation);
	}

	/** Get Operation.
		@return Compare Operation
	  */
	public String getOperation () 
	{
		return (String)get_Value(COLUMNNAME_Operation);
	}
}