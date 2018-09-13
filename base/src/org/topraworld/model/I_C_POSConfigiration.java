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
package org.topraworld.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_POSConfigiration
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_C_POSConfigiration 
{

    /** TableName=C_POSConfigiration */
    public static final String Table_Name = "C_POSConfigiration";

    /** AD_Table_ID=1000000 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_BarcodePrintFormat_ID */
    public static final String COLUMNNAME_AD_BarcodePrintFormat_ID = "AD_BarcodePrintFormat_ID";

	/** Set Barcode Print Format.
	  * Data Print Format
	  */
	public void setAD_BarcodePrintFormat_ID (int AD_BarcodePrintFormat_ID);

	/** Get Barcode Print Format.
	  * Data Print Format
	  */
	public int getAD_BarcodePrintFormat_ID();

	public org.compiere.model.I_AD_PrintFormat getAD_BarcodePrintFormat() throws RuntimeException;

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_ProductListPrintFormat_ID */
    public static final String COLUMNNAME_AD_ProductListPrintFormat_ID = "AD_ProductListPrintFormat_ID";

	/** Set Product List Print Format.
	  * Data Print Format
	  */
	public void setAD_ProductListPrintFormat_ID (int AD_ProductListPrintFormat_ID);

	/** Get Product List Print Format.
	  * Data Print Format
	  */
	public int getAD_ProductListPrintFormat_ID();

	public org.compiere.model.I_AD_PrintFormat getAD_ProductListPrintFormat() throws RuntimeException;

    /** Column name C_BpChild_ID */
    public static final String COLUMNNAME_C_BpChild_ID = "C_BpChild_ID";

	/** Set CHILD.
	  * Identifies a Business Partner
	  */
	public void setC_BpChild_ID (int C_BpChild_ID);

	/** Get CHILD.
	  * Identifies a Business Partner
	  */
	public int getC_BpChild_ID();

	public org.compiere.model.I_C_BPartner getC_BpChild() throws RuntimeException;

    /** Column name C_BpGeneral_ID */
    public static final String COLUMNNAME_C_BpGeneral_ID = "C_BpGeneral_ID";

	/** Set GENERAL BP.
	  * Identifies a Business Partner
	  */
	public void setC_BpGeneral_ID (int C_BpGeneral_ID);

	/** Get GENERAL BP.
	  * Identifies a Business Partner
	  */
	public int getC_BpGeneral_ID();

	public org.compiere.model.I_C_BPartner getC_BpGeneral() throws RuntimeException;

    /** Column name C_BpPregnant_ID */
    public static final String COLUMNNAME_C_BpPregnant_ID = "C_BpPregnant_ID";

	/** Set PREGNANT.
	  * Identifies a Business Partner
	  */
	public void setC_BpPregnant_ID (int C_BpPregnant_ID);

	/** Get PREGNANT.
	  * Identifies a Business Partner
	  */
	public int getC_BpPregnant_ID();

	public org.compiere.model.I_C_BPartner getC_BpPregnant() throws RuntimeException;

    /** Column name C_BpSenior_ID */
    public static final String COLUMNNAME_C_BpSenior_ID = "C_BpSenior_ID";

	/** Set SENIOR.
	  * Identifies a Business Partner
	  */
	public void setC_BpSenior_ID (int C_BpSenior_ID);

	/** Get SENIOR.
	  * Identifies a Business Partner
	  */
	public int getC_BpSenior_ID();

	public org.compiere.model.I_C_BPartner getC_BpSenior() throws RuntimeException;

    /** Column name C_BpSpecial_ID */
    public static final String COLUMNNAME_C_BpSpecial_ID = "C_BpSpecial_ID";

	/** Set SPECIAL BP.
	  * Identifies a Business Partner
	  */
	public void setC_BpSpecial_ID (int C_BpSpecial_ID);

	/** Get SPECIAL BP.
	  * Identifies a Business Partner
	  */
	public int getC_BpSpecial_ID();

	public org.compiere.model.I_C_BPartner getC_BpSpecial() throws RuntimeException;

    /** Column name C_DocTypeMateMov_ID */
    public static final String COLUMNNAME_C_DocTypeMateMov_ID = "C_DocTypeMateMov_ID";

	/** Set C_DocTypeMateMov_ID.
	  * Target document type for conversing documents
	  */
	public void setC_DocTypeMateMov_ID (int C_DocTypeMateMov_ID);

	/** Get C_DocTypeMateMov_ID.
	  * Target document type for conversing documents
	  */
	public int getC_DocTypeMateMov_ID();

	public org.compiere.model.I_C_DocType getC_DocTypeMateMov() throws RuntimeException;

    /** Column name C_DocTypePharmaBil_ID */
    public static final String COLUMNNAME_C_DocTypePharmaBil_ID = "C_DocTypePharmaBil_ID";

	/** Set Pharmacy Billing.
	  * Target document type for conversing documents
	  */
	public void setC_DocTypePharmaBil_ID (int C_DocTypePharmaBil_ID);

	/** Get Pharmacy Billing.
	  * Target document type for conversing documents
	  */
	public int getC_DocTypePharmaBil_ID();

	public org.compiere.model.I_C_DocType getC_DocTypePharmaBil() throws RuntimeException;

    /** Column name C_DocTypeReturn_ID */
    public static final String COLUMNNAME_C_DocTypeReturn_ID = "C_DocTypeReturn_ID";

	/** Set Customer Teturn.
	  * Target document type for conversing documents
	  */
	public void setC_DocTypeReturn_ID (int C_DocTypeReturn_ID);

	/** Get Customer Teturn.
	  * Target document type for conversing documents
	  */
	public int getC_DocTypeReturn_ID();

	public org.compiere.model.I_C_DocType getC_DocTypeReturn() throws RuntimeException;

    /** Column name C_POSConfigiration_ID */
    public static final String COLUMNNAME_C_POSConfigiration_ID = "C_POSConfigiration_ID";

	/** Set C_POSConfigiration ID	  */
	public void setC_POSConfigiration_ID (int C_POSConfigiration_ID);

	/** Get C_POSConfigiration ID	  */
	public int getC_POSConfigiration_ID();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name M_PriceListCard_ID */
    public static final String COLUMNNAME_M_PriceListCard_ID = "M_PriceListCard_ID";

	/** Set Price List Card.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceListCard_ID (int M_PriceListCard_ID);

	/** Get Price List Card.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceListCard_ID();

	public org.compiere.model.I_M_PriceList getM_PriceListCard() throws RuntimeException;

    /** Column name M_PriceListCash_ID */
    public static final String COLUMNNAME_M_PriceListCash_ID = "M_PriceListCash_ID";

	/** Set Price List Cash.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceListCash_ID (int M_PriceListCash_ID);

	/** Get Price List Cash.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceListCash_ID();

	public org.compiere.model.I_M_PriceList getM_PriceListCash() throws RuntimeException;

    /** Column name M_PriceListSpecial_ID */
    public static final String COLUMNNAME_M_PriceListSpecial_ID = "M_PriceListSpecial_ID";

	/** Set Price List Special.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceListSpecial_ID (int M_PriceListSpecial_ID);

	/** Get Price List Special.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceListSpecial_ID();

	public org.compiere.model.I_M_PriceList getM_PriceListSpecial() throws RuntimeException;

    /** Column name M_WarehouseSource_ID */
    public static final String COLUMNNAME_M_WarehouseSource_ID = "M_WarehouseSource_ID";

	/** Set Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID);

	/** Get Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID();

	public org.compiere.model.I_M_Warehouse getM_WarehouseSource() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * This is for counters
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * This is for counters
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
