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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for C_POSConfigiration
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_C_POSConfigiration extends PO implements I_C_POSConfigiration, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171017L;

    /** Standard Constructor */
    public X_C_POSConfigiration (Properties ctx, int C_POSConfigiration_ID, String trxName)
    {
      super (ctx, C_POSConfigiration_ID, trxName);
      /** if (C_POSConfigiration_ID == 0)
        {
			setAD_BarcodePrintFormat_ID (0);
			setAD_ProductListPrintFormat_ID (0);
			setC_BpChild_ID (0);
			setC_BpGeneral_ID (0);
			setC_BpPregnant_ID (0);
			setC_BpSenior_ID (0);
			setC_BpSpecial_ID (0);
			setC_DocTypeMateMov_ID (0);
			setC_DocTypePharmaBil_ID (0);
			setC_DocTypeReturn_ID (0);
			setC_POSConfigiration_ID (0);
			setM_PriceListCard_ID (0);
			setM_PriceListCash_ID (0);
			setM_PriceListSpecial_ID (0);
// 0
			setM_WarehouseSource_ID (0);
			setM_Warehouse_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_POSConfigiration (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_POSConfigiration[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_PrintFormat getAD_BarcodePrintFormat() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintFormat)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFormat.Table_Name)
			.getPO(getAD_BarcodePrintFormat_ID(), get_TrxName());	}

	/** Set Barcode Print Format.
		@param AD_BarcodePrintFormat_ID 
		Data Print Format
	  */
	public void setAD_BarcodePrintFormat_ID (int AD_BarcodePrintFormat_ID)
	{
		if (AD_BarcodePrintFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_BarcodePrintFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_BarcodePrintFormat_ID, Integer.valueOf(AD_BarcodePrintFormat_ID));
	}

	/** Get Barcode Print Format.
		@return Data Print Format
	  */
	public int getAD_BarcodePrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_BarcodePrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_PrintFormat getAD_ProductListPrintFormat() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintFormat)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFormat.Table_Name)
			.getPO(getAD_ProductListPrintFormat_ID(), get_TrxName());	}

	/** Set Product List Print Format.
		@param AD_ProductListPrintFormat_ID 
		Data Print Format
	  */
	public void setAD_ProductListPrintFormat_ID (int AD_ProductListPrintFormat_ID)
	{
		if (AD_ProductListPrintFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_ProductListPrintFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_ProductListPrintFormat_ID, Integer.valueOf(AD_ProductListPrintFormat_ID));
	}

	/** Get Product List Print Format.
		@return Data Print Format
	  */
	public int getAD_ProductListPrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ProductListPrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BpChild() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BpChild_ID(), get_TrxName());	}

	/** Set CHILD.
		@param C_BpChild_ID 
		Identifies a Business Partner
	  */
	public void setC_BpChild_ID (int C_BpChild_ID)
	{
		if (C_BpChild_ID < 1) 
			set_Value (COLUMNNAME_C_BpChild_ID, null);
		else 
			set_Value (COLUMNNAME_C_BpChild_ID, Integer.valueOf(C_BpChild_ID));
	}

	/** Get CHILD.
		@return Identifies a Business Partner
	  */
	public int getC_BpChild_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BpChild_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BpGeneral() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BpGeneral_ID(), get_TrxName());	}

	/** Set GENERAL BP.
		@param C_BpGeneral_ID 
		Identifies a Business Partner
	  */
	public void setC_BpGeneral_ID (int C_BpGeneral_ID)
	{
		if (C_BpGeneral_ID < 1) 
			set_Value (COLUMNNAME_C_BpGeneral_ID, null);
		else 
			set_Value (COLUMNNAME_C_BpGeneral_ID, Integer.valueOf(C_BpGeneral_ID));
	}

	/** Get GENERAL BP.
		@return Identifies a Business Partner
	  */
	public int getC_BpGeneral_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BpGeneral_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BpPregnant() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BpPregnant_ID(), get_TrxName());	}

	/** Set PREGNANT.
		@param C_BpPregnant_ID 
		Identifies a Business Partner
	  */
	public void setC_BpPregnant_ID (int C_BpPregnant_ID)
	{
		if (C_BpPregnant_ID < 1) 
			set_Value (COLUMNNAME_C_BpPregnant_ID, null);
		else 
			set_Value (COLUMNNAME_C_BpPregnant_ID, Integer.valueOf(C_BpPregnant_ID));
	}

	/** Get PREGNANT.
		@return Identifies a Business Partner
	  */
	public int getC_BpPregnant_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BpPregnant_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BpSenior() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BpSenior_ID(), get_TrxName());	}

	/** Set SENIOR.
		@param C_BpSenior_ID 
		Identifies a Business Partner
	  */
	public void setC_BpSenior_ID (int C_BpSenior_ID)
	{
		if (C_BpSenior_ID < 1) 
			set_Value (COLUMNNAME_C_BpSenior_ID, null);
		else 
			set_Value (COLUMNNAME_C_BpSenior_ID, Integer.valueOf(C_BpSenior_ID));
	}

	/** Get SENIOR.
		@return Identifies a Business Partner
	  */
	public int getC_BpSenior_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BpSenior_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BpSpecial() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BpSpecial_ID(), get_TrxName());	}

	/** Set SPECIAL BP.
		@param C_BpSpecial_ID 
		Identifies a Business Partner
	  */
	public void setC_BpSpecial_ID (int C_BpSpecial_ID)
	{
		if (C_BpSpecial_ID < 1) 
			set_Value (COLUMNNAME_C_BpSpecial_ID, null);
		else 
			set_Value (COLUMNNAME_C_BpSpecial_ID, Integer.valueOf(C_BpSpecial_ID));
	}

	/** Get SPECIAL BP.
		@return Identifies a Business Partner
	  */
	public int getC_BpSpecial_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BpSpecial_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocTypeMateMov() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocTypeMateMov_ID(), get_TrxName());	}

	/** Set C_DocTypeMateMov_ID.
		@param C_DocTypeMateMov_ID 
		Target document type for conversing documents
	  */
	public void setC_DocTypeMateMov_ID (int C_DocTypeMateMov_ID)
	{
		if (C_DocTypeMateMov_ID < 1) 
			set_Value (COLUMNNAME_C_DocTypeMateMov_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocTypeMateMov_ID, Integer.valueOf(C_DocTypeMateMov_ID));
	}

	/** Get C_DocTypeMateMov_ID.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypeMateMov_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeMateMov_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocTypePharmaBil() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocTypePharmaBil_ID(), get_TrxName());	}

	/** Set Pharmacy Billing.
		@param C_DocTypePharmaBil_ID 
		Target document type for conversing documents
	  */
	public void setC_DocTypePharmaBil_ID (int C_DocTypePharmaBil_ID)
	{
		if (C_DocTypePharmaBil_ID < 1) 
			set_Value (COLUMNNAME_C_DocTypePharmaBil_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocTypePharmaBil_ID, Integer.valueOf(C_DocTypePharmaBil_ID));
	}

	/** Get Pharmacy Billing.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypePharmaBil_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypePharmaBil_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocTypeReturn() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocTypeReturn_ID(), get_TrxName());	}

	/** Set Customer Teturn.
		@param C_DocTypeReturn_ID 
		Target document type for conversing documents
	  */
	public void setC_DocTypeReturn_ID (int C_DocTypeReturn_ID)
	{
		if (C_DocTypeReturn_ID < 1) 
			set_Value (COLUMNNAME_C_DocTypeReturn_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocTypeReturn_ID, Integer.valueOf(C_DocTypeReturn_ID));
	}

	/** Get Customer Teturn.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypeReturn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeReturn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_POSConfigiration ID.
		@param C_POSConfigiration_ID C_POSConfigiration ID	  */
	public void setC_POSConfigiration_ID (int C_POSConfigiration_ID)
	{
		if (C_POSConfigiration_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_POSConfigiration_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_POSConfigiration_ID, Integer.valueOf(C_POSConfigiration_ID));
	}

	/** Get C_POSConfigiration ID.
		@return C_POSConfigiration ID	  */
	public int getC_POSConfigiration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_POSConfigiration_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList getM_PriceListCard() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
			.getPO(getM_PriceListCard_ID(), get_TrxName());	}

	/** Set Price List Card.
		@param M_PriceListCard_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceListCard_ID (int M_PriceListCard_ID)
	{
		if (M_PriceListCard_ID < 1) 
			set_Value (COLUMNNAME_M_PriceListCard_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceListCard_ID, Integer.valueOf(M_PriceListCard_ID));
	}

	/** Get Price List Card.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceListCard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceListCard_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList getM_PriceListCash() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
			.getPO(getM_PriceListCash_ID(), get_TrxName());	}

	/** Set Price List Cash.
		@param M_PriceListCash_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceListCash_ID (int M_PriceListCash_ID)
	{
		if (M_PriceListCash_ID < 1) 
			set_Value (COLUMNNAME_M_PriceListCash_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceListCash_ID, Integer.valueOf(M_PriceListCash_ID));
	}

	/** Get Price List Cash.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceListCash_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceListCash_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList getM_PriceListSpecial() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList)MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
			.getPO(getM_PriceListSpecial_ID(), get_TrxName());	}

	/** Set Price List Special.
		@param M_PriceListSpecial_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceListSpecial_ID (int M_PriceListSpecial_ID)
	{
		if (M_PriceListSpecial_ID < 1) 
			set_Value (COLUMNNAME_M_PriceListSpecial_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceListSpecial_ID, Integer.valueOf(M_PriceListSpecial_ID));
	}

	/** Get Price List Special.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceListSpecial_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceListSpecial_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_WarehouseSource() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_WarehouseSource_ID(), get_TrxName());	}

	/** Set Source Warehouse.
		@param M_WarehouseSource_ID 
		Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID)
	{
		if (M_WarehouseSource_ID < 1) 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, null);
		else 
			set_Value (COLUMNNAME_M_WarehouseSource_ID, Integer.valueOf(M_WarehouseSource_ID));
	}

	/** Get Source Warehouse.
		@return Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		This is for counters
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return This is for counters
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}