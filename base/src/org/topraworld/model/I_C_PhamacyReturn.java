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

/** Generated Interface for C_PhamacyReturn
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_C_PhamacyReturn 
{

    /** TableName=C_PhamacyReturn */
    public static final String Table_Name = "C_PhamacyReturn";

    /** AD_Table_ID=1000001 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name BaseOrderID */
    public static final String COLUMNNAME_BaseOrderID = "BaseOrderID";

	/** Set C_BaseOrder_ID.
	  * Order
	  */
	public void setBaseOrderID (int BaseOrderID);

	/** Get C_BaseOrder_ID.
	  * Order
	  */
	public int getBaseOrderID();

    /** Column name BaseOrderLineID */
    public static final String COLUMNNAME_BaseOrderLineID = "BaseOrderLineID";

	/** Set C_BaseOrderLine_ID.
	  * Sales Order Line
	  */
	public void setBaseOrderLineID (int BaseOrderLineID);

	/** Get C_BaseOrderLine_ID.
	  * Sales Order Line
	  */
	public int getBaseOrderLineID();

    /** Column name BaseQty */
    public static final String COLUMNNAME_BaseQty = "BaseQty";

	/** Set BaseQty.
	  * Quantity
	  */
	public void setBaseQty (BigDecimal BaseQty);

	/** Get BaseQty.
	  * Quantity
	  */
	public BigDecimal getBaseQty();

    /** Column name C_PhamacyReturn_ID */
    public static final String COLUMNNAME_C_PhamacyReturn_ID = "C_PhamacyReturn_ID";

	/** Set C_PhamacyReturn ID	  */
	public void setC_PhamacyReturn_ID (int C_PhamacyReturn_ID);

	/** Get C_PhamacyReturn ID	  */
	public int getC_PhamacyReturn_ID();

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

    /** Column name ReturnOrderID */
    public static final String COLUMNNAME_ReturnOrderID = "ReturnOrderID";

	/** Set ReturnOrderID.
	  * Order
	  */
	public void setReturnOrderID (int ReturnOrderID);

	/** Get ReturnOrderID.
	  * Order
	  */
	public int getReturnOrderID();

    /** Column name ReturnOrderLineID */
    public static final String COLUMNNAME_ReturnOrderLineID = "ReturnOrderLineID";

	/** Set C_ReturnOrderLine_ID.
	  * Sales Order Line
	  */
	public void setReturnOrderLineID (int ReturnOrderLineID);

	/** Get C_ReturnOrderLine_ID.
	  * Sales Order Line
	  */
	public int getReturnOrderLineID();

    /** Column name ReturnQty */
    public static final String COLUMNNAME_ReturnQty = "ReturnQty";

	/** Set ReturnQty.
	  * Quantity
	  */
	public void setReturnQty (BigDecimal ReturnQty);

	/** Get ReturnQty.
	  * Quantity
	  */
	public BigDecimal getReturnQty();

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
