/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.grid;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.StatusBar;
import org.compiere.grid.ed.VButton;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.GridTab;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.model.MConversionRate;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTab;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.ValueNamePair;

import com.Verisign.payment.f;

/**
 *	Display (and process) Payment Options.
 *  <pre>
 *  Payment Rule
 *  -B- Cash          (Date)          -> Cash Entry
 *  -P- Payment Term  (Term)
 *  -S- Check         (Routing, ..)   -> Payment Entry
 *  -K- CreditCard    (No)            -> Payment Entry
 *  -U- ACH Transfer  (Routing)       -> Payment Entry
 *
 *  When processing:
 *  - If an invoice is a S/K/U, but has no Payment Entry, it is changed to P
 *  - If an invoice is B and has no Cash Entry, it is created
 *  - An invoice is "Open" if it is "P" and no Payment
 *
 *  Entry:
 *  - If not processed, an invoice has no Cash or Payment entry
 *  - The entry is created, during "Online" and when Saving
 *
 *  Changes/Reversals:
 *  - existing Cash Entries are reversed and newly created
 *  - existing Payment Entries are not changed and then "hang there" and need to be allocated
 *  </pre>
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VPayment.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1763488 ] Error on cash payment
 * 				<li>BF [ 1789949 ] VPayment: is displaying just "CashNotCreated"
 * @author Michael Judd, Akuna Ltd
 * 				<li>FR [ 2803341 ] Deprecate Cash Journal
 */
public class VPayment extends CDialog
	implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7931457502030396154L;

	/**
	 *	Constructor
	 *
	 *	@param WindowNo	owning window
	 *  @param mTab     owning tab
	 *	@param button	button with access information
	 */
	public VPayment (int WindowNo, GridTab mTab, VButton button)
	{
		super(Env.getWindow(WindowNo), Msg.getMsg(Env.getCtx(), "Payment"), true);
		m_WindowNo = WindowNo;
		m_isSOTrx = "Y".equals(Env.getContext(Env.getCtx(), WindowNo, "IsSOTrx"));
		m_mTab = mTab;
		try
		{
			bDateField = new VDate("DateAcct", false, false, true, DisplayType.Date, "DateAcct");
			jbInit();
			m_initOK = dynInit(button);     //  Null Pointer if order/invoice not saved yet
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "VPayment", ex);
			m_initOK = false;
		}
		//
		AEnv.positionCenterWindow(Env.getWindow(WindowNo), this);
	}	//	VPayment

	/**	Window						*/
	private int                 m_WindowNo = 0;
	/**	Tab							*/
	private GridTab         		m_mTab;

	//	Data from Order/Invoice
	private String              m_DocStatus = null;
	/** Start Payment Rule          */
	private String				m_PaymentRule = "";
	/** Start Payment Term          */
	private int					m_C_PaymentTerm_ID = 0;
	/** Start Acct Date             */
	private Timestamp			m_DateAcct = null;
	/** Start Payment               */
	private int					m_C_Payment_ID = 0;
	private MPayment            m_mPayment = null;
	private MPayment            m_mPaymentOriginal = null;
	/** Start CashBook Line         */
	private int                 m_C_CashLine_ID = 0;
	private MCashLine			m_cashLine = null;
	/** Start CreditCard            */
	private String              m_CCType = "";
	/** Start Bank Account			*/
	private int					m_C_BankAccount_ID = 0;
	/** Start CashBook              */
	private int                 m_C_CashBook_ID = 0;

	/** Is SOTrx					*/
	private boolean				m_isSOTrx = true;

	/** Invoice Currency              */
	private int	 				m_C_Currency_ID = 0;
	private int                 m_AD_Client_ID = 0;
	private boolean				m_Cash_As_Payment = true;
	private int                 m_AD_Org_ID = 0;
	private int                 m_C_BPartner_ID = 0;
	private BigDecimal			m_Amount = Env.ZERO;	//	Payment Amount
	//
	private boolean 			m_initOK = false;
	/** Only allow changing Rule        */
	private boolean             m_onlyRule = false;
	private static Hashtable<Integer,KeyNamePair> s_Currencies = null;	//	EMU Currencies
	
	private boolean				m_needSave = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VPayment.class);

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel northPanel = new CPanel();
	private CPanel centerPanel = new CPanel();
	private FlowLayout northLayout = new FlowLayout();
	private CComboBox paymentCombo = new CComboBox();
	private CLabel paymentLabel = new CLabel();
	private CardLayout centerLayout = new CardLayout();
	private CPanel bPanel = new CPanel();
	private CPanel kPanel = new CPanel();
	
	private GridBagLayout kLayout = new GridBagLayout();
	private CLabel kTypeLabel = new CLabel();
	private CComboBox kTypeCombo = new CComboBox();
	private CLabel kNumberLabel = new CLabel();
	private CTextField kNumberField = new CTextField();
	private CLabel kNameLabel = new CLabel();
	private CTextField kNameField = new CTextField();
	private CLabel kExpLabel = new CLabel();
	private CTextField kExpField = new CTextField();
	private CLabel kApprovalLabel = new CLabel();
	private CTextField kApprovalField = new CTextField();
	private CLabel kAmountLabel = new CLabel();
	private VNumber kAmountField = new VNumber();
	private CPanel tPanel = new CPanel();
	private CLabel tAccountLabel = new CLabel();
	private CComboBox tAccountCombo = new CComboBox();
	private CPanel sPanel = new CPanel();
	private GridBagLayout sPanelLayout = new GridBagLayout();
	private CLabel sNumberLabel = new CLabel();
	private CTextField sNumberField = new CTextField();
	private CLabel sRoutingLabel = new CLabel();
	private CTextField sRoutingField = new CTextField();
	private CLabel sCurrencyLabel = new CLabel();
	private CComboBox sCurrencyCombo = new CComboBox();
	private CLabel bCurrencyLabel = new CLabel();
	private CComboBox bCurrencyCombo = new CComboBox();
	private CPanel pPanel = new CPanel();
	private CLabel pTermLabel = new CLabel();
	private CComboBox pTermCombo = new CComboBox();
	private GridBagLayout bPanelLayout = new GridBagLayout();
	private CLabel bAmountLabel = new CLabel();
	private VNumber bAmountField = new VNumber();
	private CLabel sAmountLabel = new CLabel();
	private VNumber sAmountField = new VNumber();
	private VDate bDateField;
	private CLabel bDateLabel = new CLabel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private CTextField sCheckField = new CTextField();
	private CLabel sCheckLabel = new CLabel();
	private CButton kOnline = new CButton();
	private CButton sOnline = new CButton();
	private CComboBox sBankAccountCombo = new CComboBox();
	private CLabel sBankAccountLabel = new CLabel();
	private GridBagLayout pPanelLayout = new GridBagLayout();
	private CLabel bCashBookLabel = new CLabel();
	private CComboBox bCashBookCombo = new CComboBox();
	private GridBagLayout tPanelLayout = new GridBagLayout();
	private CButton tOnline = new CButton();
	private CLabel kStatus = new CLabel();
	private CTextField tRoutingField = new CTextField();
	private CTextField tNumberField = new CTextField();
	private CLabel tStatus = new CLabel();
	private CLabel tRoutingText = new CLabel();
	private CLabel tNumberText = new CLabel();
	private CLabel sStatus = new CLabel();
	
	//Mixed mode Credit
	private GridBagLayout mixedModeLayout = new GridBagLayout();
	private CPanel mPanel = new CPanel();
	private CLabel mCash = new CLabel();
	private VNumber mCashAmount = new VNumber();
	private CLabel mBank = new CLabel();
	private CLabel mTypeLabel = new CLabel();
	private CComboBox mTypeCombo = new CComboBox();
	private CLabel mNumberLabel = new CLabel();
	private CTextField mNumberField = new CTextField();
	private CLabel mNameLabel = new CLabel();
	private CTextField mNameField = new CTextField();
	private CLabel mExpLabel = new CLabel();
	private CTextField mExpField = new CTextField();
	private CLabel mApprovalLabel = new CLabel();
	private CTextField mApprovalField = new CTextField();
	private CLabel mCardAmountLabel = new CLabel();
	private VNumber mCardAmountField = new VNumber();
	
	//Mixed mode Cash
	private CLabel mCashBookLabel = new CLabel();
	private CComboBox mCashBookCombo = new CComboBox();
	private CComboBox mBankAccountCombo = new CComboBox();
	
	//Mixed mode Cheque
	private CLabel mRoutingLabel = new CLabel();
	private CLabel mChequeAmountLabel = new CLabel();
	private CLabel mBankAccountLabel = new CLabel();
	private CLabel mCheckLabel = new CLabel();
	private CLabel mCurrencyLabel = new CLabel();
	private CTextField mRoutingField = new CTextField();
	private CTextField mCheckField = new CTextField();
	private VNumber mChequeAmountField = new VNumber();
	private CLabel mChqNumberLabel = new CLabel();
	private CTextField mChqNumberField = new CTextField(); 
	
	/**
	 *	Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		getContentPane().add(mainPanel);
		mainPanel.setLayout(mainLayout);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		//
		northPanel.setLayout(northLayout);
		paymentLabel.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.add(paymentLabel, null);
		northPanel.add(paymentCombo, null);
		//
		centerPanel.setLayout(centerLayout);
		//      CreditCard
		kPanel.setLayout(kLayout);
		kNumberField.setPreferredSize(new Dimension(160, 21));
		kNameField.setPreferredSize(new Dimension(160, 21));
		kExpField.setPreferredSize(new Dimension(40, 21));
		kApprovalField.setPreferredSize(new Dimension(120, 21));
		kTypeLabel.setText(Msg.translate(Env.getCtx(), "CreditCardType"));
		kNumberLabel.setText(Msg.translate(Env.getCtx(), "CreditCardNumber"));
		kNameLabel.setText(Msg.translate(Env.getCtx(),"Name"));
		kExpLabel.setText(Msg.getMsg(Env.getCtx(), "Expires"));
		kAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		kStatus.setText(" ");
		
		centerPanel.add(kPanel, "kPanel");
		centerLayout.addLayoutComponent(kPanel, "kPanel");
		kPanel.add(kTypeLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kTypeCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kNumberLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kNumberField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kNameLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
			kPanel.add(kNameField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kExpLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kExpField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kAmountLabel,   new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 5, 0), 0, 0));
		kPanel.add(kAmountField,     new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 5, 5), 0, 0));
		kPanel.add(kStatus, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		//	DircetDebit/Credit
		tPanel.setLayout(tPanelLayout);
		tAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BP_BankAccount_ID"));
		tRoutingField.setColumns(8);
		tNumberField.setColumns(10);
		tRoutingText.setText(Msg.translate(Env.getCtx(), "RoutingNo"));
		tNumberText.setText(Msg.translate(Env.getCtx(), "AccountNo"));
		
		tStatus.setText(" ");
		centerPanel.add(tPanel, "tPanel");
		centerLayout.addLayoutComponent(tPanel, "tPanel");
		tPanel.add(tAccountLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		tPanel.add(tAccountCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		tPanel.add(tRoutingField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		tPanel.add(tNumberField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		tPanel.add(tStatus, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		tPanel.add(tRoutingText, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		tPanel.add(tNumberText, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		
		// Cheque
		sPanel.setLayout(sPanelLayout);
		sBankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		sAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		//sAmountField.setText("");
		sRoutingLabel.setText(Msg.translate(Env.getCtx(), "RoutingNo"));
		sNumberLabel.setText(Msg.translate(Env.getCtx(), "AccountNo"));
		sCheckLabel.setText(Msg.translate(Env.getCtx(), "CheckNo"));
		sCheckField.setColumns(8);
		sCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		sNumberField.setPreferredSize(new Dimension(100, 21));
		sRoutingField.setPreferredSize(new Dimension(70, 21));
		sStatus.setText(" ");
		centerPanel.add(sPanel, "sPanel");
		centerLayout.addLayoutComponent(sPanel, "sPanel");
		
		sPanel.add(sBankAccountLabel,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		sPanel.add(sBankAccountCombo,    new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		sPanel.add(sCurrencyLabel,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sCurrencyCombo,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		sPanel.add(sAmountLabel,   new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 5, 0), 0, 0));
		sPanel.add(sAmountField,     new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 5, 5), 0, 0));
		sPanel.add(sRoutingLabel,   new GridBagConstraints(0, 3, 1, 2, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 2, 0), 0, 0));
		sPanel.add(sRoutingField,    new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		sPanel.add(sNumberLabel,   new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sNumberField,    new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		sPanel.add(sCheckLabel,   new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sCheckField,    new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 0), 0, 0));
		sPanel.add(sStatus,    new GridBagConstraints(0, 7, 3, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		// Cash
		pPanel.setLayout(pPanelLayout);
		pTermLabel.setText(Msg.translate(Env.getCtx(), "C_PaymentTerm_ID"));
		centerPanel.add(pPanel, "pPanel");
		centerLayout.addLayoutComponent(pPanel, "pPanel");
		pPanel.add(pTermLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 5, 2, 0), 0, 0));
		pPanel.add(pTermCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		//
		
		bCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		
		bPanel.setLayout(bPanelLayout);
		bAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		//bAmountField.setText("");
		bDateLabel.setText(Msg.translate(Env.getCtx(), "DateAcct"));
		centerLayout.addLayoutComponent(bPanel, "bPanel");
		centerPanel.add(bPanel, "bPanel");
		
		if (m_Cash_As_Payment){
			sBankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
			bPanel.add(sBankAccountLabel,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
			bPanel.add(sBankAccountCombo,    new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0
					,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));

		} else {
			bCashBookLabel.setText(Msg.translate(Env.getCtx(), "C_CashBook_ID"));
			bPanel.add(bCashBookLabel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
			bPanel.add(bCashBookCombo,  new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0
					,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));

		}
		
		bPanel.add(bCurrencyLabel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		bPanel.add(bCurrencyCombo,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		bPanel.add(bDateLabel,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		bPanel.add(bDateField,  new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		bPanel.add(bAmountLabel,   new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		bPanel.add(bAmountField,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		
		//Mixed Mode  - Credit Card
		mPanel.setLayout(mixedModeLayout);
		mNumberField.setPreferredSize(new Dimension(160, 21));
		mNameField.setPreferredSize(new Dimension(160, 21));
		mExpField.setPreferredSize(new Dimension(40, 21));
		mApprovalField.setPreferredSize(new Dimension(120, 21));
		mTypeLabel.setText("Card Type");
		mNumberLabel.setText(Msg.translate(Env.getCtx(), "CreditCardNumber"));
		mNameLabel.setText(Msg.translate(Env.getCtx(),"Name"));
		mExpLabel.setText(Msg.getMsg(Env.getCtx(), "Expires"));
		mCardAmountLabel.setText("CardAmount");
		//Action Listner
		mCardAmountField.addActionListener(this);
		        
		centerPanel.add(mPanel, "mPanel");
		centerLayout.addLayoutComponent(mPanel, "mPanel");
		
		CLabel CClabel = new CLabel("<HTML><I><B><U>CREDIT CARD</U></B></I></HTML>");
		mPanel.add(CClabel,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		
		mPanel.add(mTypeLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		mPanel.add(mTypeCombo, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		
		mPanel.add(mNumberLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		mPanel.add(mNumberField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(2, 5, 2, 5), 0, 0));
		
		mPanel.add(mNameLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		mPanel.add(mNameField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(2, 5, 2, 5), 0, 0));
		
		mPanel.add(mExpLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		mPanel.add(mExpField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		
		mPanel.add(mCardAmountLabel,   new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 5, 0), 0, 0));
		mPanel.add(mCardAmountField,     new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 5, 5), 0, 0));
		
		//Mixed Mode  - Cash		
		mCash.setText("Cash Amount");
		mBank.setText("Cash Book");
		//Action Listner
		mCashAmount.addActionListener(this);
		
		CLabel CAlabel = new CLabel("<HTML><I><B><U>CASH</U></B></I></HTML>");
		mPanel.add(CAlabel,   new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 6, 2, 0), 0, 0));
		
		mPanel.add(mCash, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		mPanel.add(mCashAmount, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		
		mCashBookLabel.setText(Msg.translate(Env.getCtx(), "C_CashBook_ID"));
		mPanel.add(mBank,  new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		mPanel.add(mCashBookCombo,  new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		
		//Mixed Mode  - Cheque
		
		mBankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		mChequeAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		//sAmountField.setText("");
		mRoutingLabel.setText(Msg.translate(Env.getCtx(), "RoutingNo"));
		mChqNumberLabel.setText(Msg.translate(Env.getCtx(), "AccountNo"));
		mCheckLabel.setText(Msg.translate(Env.getCtx(), "CheckNo"));
		mCheckField.setColumns(8);
		mCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		mChqNumberField.setPreferredSize(new Dimension(100, 21));
		mRoutingField.setPreferredSize(new Dimension(70, 21));
		mChequeAmountField.setPreferredSize(new Dimension(70, 21));
		//Action Listner
		mChequeAmountField.addActionListener(this);
		
		CLabel CHQlabel = new CLabel("<HTML><I><B><U>CHEQUE</U></B></I></HTML>");
		mPanel.add(CHQlabel,   new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 9, 2, 0), 0, 0));
		
		mPanel.add(mBankAccountLabel,   new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		mPanel.add(mBankAccountCombo,    new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		
		mPanel.add(mChequeAmountLabel,   new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 5, 0), 0, 0));
		mPanel.add(mChequeAmountField,     new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 5, 5), 0, 0));
		
		mPanel.add(mRoutingLabel,   new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 2, 0), 0, 0));
		mPanel.add(mRoutingField,    new GridBagConstraints(1, 12, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		
		mPanel.add(mChqNumberLabel,   new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		mPanel.add(mChqNumberField,    new GridBagConstraints(1, 13, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		
		mPanel.add(mCheckLabel,   new GridBagConstraints(0, 14, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		mPanel.add(mCheckField,    new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 0), 0, 0));
		
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
	}	//	jbInit

	
	/**************************************************************************
	 *	Dynamic Init.
	 *		B (Cash)		(Currency)
	 *		K (CreditCard)  Type, Number, Exp, Approval
	 *		L (DirectDebit)	BPartner_Bank
	 *		P (PaymentTerm)	PaymentTerm
	 *		S (Check)		(Currency) CheckNo, Routing
	 *		M Mixed mode(CreditCard , Cash , Checks)
	 *
	 *	Currencies are shown, if member of EMU
	 *  @param button payment type button
	 *  @return true if init OK
	 *  @throws Exception
	 */
	
	private boolean dynInit (VButton button) throws Exception
	{
		m_DocStatus = (String)m_mTab.getValue("DocStatus");
		log.config(m_DocStatus);

		if (m_mTab.getValue("C_BPartner_ID") == null)
		{
			ADialog.error(0, this, "SaveErrorRowNotFound");
			return false;
		}

		//	Is the Trx posted?
	//	String Posted = (String)m_mTab.getValue("Posted");
	//	if (Posted != null && Posted.equals("Y"))
	//		return false;

		//  DocStatus
		m_DocStatus = (String)m_mTab.getValue("DocStatus");
		if (m_DocStatus == null)
			m_DocStatus = "";
		//	Is the Trx closed?		Reversed / Voided / Closed
		if (m_DocStatus.equals("RE") || m_DocStatus.equals("VO") || m_DocStatus.equals("CL"))
			return false;
		//  Document is not complete - allow to change the Payment Rule only
		if (m_DocStatus.equals("CO") || m_DocStatus.equals("WP") )
			m_onlyRule = false;
		else
			m_onlyRule = true;
		//	PO only  Rule
		if (!m_onlyRule		//	Only order has Warehouse
			&& !m_isSOTrx && m_mTab.getValue("M_Warehouse_ID") != null)
			m_onlyRule = true;
		
		centerPanel.setVisible(!m_onlyRule);
		
		//  Amount
		m_Amount = (BigDecimal)m_mTab.getValue("GrandTotal");
		if (!m_onlyRule && m_Amount.compareTo(Env.ZERO) == 0)
		{
			ADialog.error(m_WindowNo, this, "PaymentZero");
			return false;
		}

		bAmountField.setValue(m_Amount);
		sAmountField.setValue(m_Amount);
		kAmountField.setValue(m_Amount);

		/**
		 *	Get Data from Grid
		 */
		m_AD_Client_ID = ((Integer)m_mTab.getValue("AD_Client_ID")).intValue();
		m_Cash_As_Payment = MSysConfig.getBooleanValue("CASH_AS_PAYMENT",true, m_AD_Client_ID);
		m_AD_Org_ID = ((Integer)m_mTab.getValue("AD_Org_ID")).intValue();
		m_C_BPartner_ID = ((Integer)m_mTab.getValue("C_BPartner_ID")).intValue();
		m_PaymentRule = (String)m_mTab.getValue("PaymentRule");
		m_C_Currency_ID = ((Integer)m_mTab.getValue("C_Currency_ID")).intValue();
		m_DateAcct = (Timestamp)m_mTab.getValue("DateAcct");
		if (m_mTab.getValue("C_PaymentTerm_ID") != null)
			m_C_PaymentTerm_ID = ((Integer)m_mTab.getValue("C_PaymentTerm_ID")).intValue();
		//  Existing Payment
		if (m_mTab.getValue("C_Payment_ID") != null)
		{
			m_C_Payment_ID = ((Integer)m_mTab.getValue("C_Payment_ID")).intValue();
			if (m_C_Payment_ID != 0)
			{
				m_mPayment = new MPayment(Env.getCtx(), m_C_Payment_ID, null);
				m_mPaymentOriginal = new MPayment(Env.getCtx(), m_C_Payment_ID, null);	//	full copy
				//  CreditCard
				m_CCType = m_mPayment.getCreditCardType();
				kNumberField.setText(m_mPayment.getCreditCardNumber());
				kNameField.setText(m_mPayment.getA_Name());
				kExpField.setText(m_mPayment.getCreditCardExp(null));
				kApprovalField.setText(m_mPayment.getVoiceAuthCode());
				kStatus.setText(m_mPayment.getR_PnRef());
				kAmountField.setValue(m_mPayment.getPayAmt());
				
				//	if approved/paid, don't let it change
				kTypeCombo.setReadWrite(!m_mPayment.isApproved());
				kNumberField.setReadWrite(!m_mPayment.isApproved());
				kNameField.setReadWrite(!m_mPayment.isApproved());
				kExpField.setReadWrite(!m_mPayment.isApproved());
				kApprovalField.setReadWrite(!m_mPayment.isApproved());
				kOnline.setReadWrite(!m_mPayment.isApproved());
				kAmountField.setReadWrite(!m_mPayment.isApproved());
				//  Check
				m_C_BankAccount_ID = m_mPayment.getC_BankAccount_ID();
				sRoutingField.setText(m_mPayment.getRoutingNo());
				sNumberField.setText(m_mPayment.getAccountNo());
				sCheckField.setText(m_mPayment.getCheckNo());
				sStatus.setText(m_mPayment.getR_PnRef());
				sAmountField.setValue(m_mPayment.getPayAmt());
				//  Transfer
				tRoutingField.setText(m_mPayment.getRoutingNo());
				tNumberField.setText(m_mPayment.getAccountNo());
				tStatus.setText(m_mPayment.getR_PnRef());
				// Cash
				bAmountField.setValue(m_mPayment.getPayAmt());
				
				//Mixed payment
				if(X_C_Order.PAYMENTRULE_Mixed.equals(m_PaymentRule)){
					
					String sqlwhere = "C_Invoice_ID = "+ getInvoiceID(m_mTab.getRecord_ID() , null) + " AND DOCSTATUS = 'CO'";
					int [] pids = MPayment.getAllIDs("C_Payment", sqlwhere, null);
					
					for(int C_Payment_ID : pids){
						
						m_mPayment = new MPayment(Env.getCtx(), C_Payment_ID, null);
						String tenderType = m_mPayment.getTenderType();
						//Cheque	
						if(tenderType.equals("K")){
							mRoutingField.setText(m_mPayment.getRoutingNo());
							mChqNumberField.setText(m_mPayment.getAccountNo());
							mCheckField.setText(m_mPayment.getCheckNo());
							mChequeAmountField.setValue(m_mPayment.getPayAmt());
							//	if approved/paid, don't let it change
							mRoutingField.setReadWrite(!m_mPayment.isApproved());
							mChqNumberField.setReadWrite(!m_mPayment.isApproved());
							mCheckField.setReadWrite(!m_mPayment.isApproved());
							mChequeAmountField.setReadWrite(!m_mPayment.isApproved());
							mBankAccountCombo.setReadWrite(!m_mPayment.isApproved());
							
						//Card	
						}else if(tenderType.equals("C")){
							m_CCType = m_mPayment.getCreditCardType();
							mNumberField.setText(m_mPayment.getCreditCardNumber());
							mNameField.setText(m_mPayment.getA_Name());
							mExpField.setText(m_mPayment.getCreditCardExp(null));
							mCardAmountField.setValue(m_mPayment.getPayAmt());
							
							//	if approved/paid, don't let it change
							mTypeCombo.setReadWrite(!m_mPayment.isApproved());
							mNumberField.setReadWrite(!m_mPayment.isApproved());
							mNameField.setReadWrite(!m_mPayment.isApproved());
							mExpField.setReadWrite(!m_mPayment.isApproved());
							mApprovalField.setReadWrite(!m_mPayment.isApproved());
							mCardAmountField.setReadWrite(!m_mPayment.isApproved());
						}
					}					
					//  Cash
					mCashAmount.setValue(m_Amount.subtract((BigDecimal) mCardAmountField.getValue()).subtract((BigDecimal) mChequeAmountField.getValue()));
					mCashAmount.setReadWrite(!m_mPayment.isApproved());
					mCashBookCombo.setReadWrite(false);
				}
				
			}
		}
		
		if (m_mPayment == null)
		{
			m_mPayment = new MPayment (Env.getCtx (), 0, null);
			m_mPayment.setAD_Org_ID(m_AD_Org_ID);
			m_mPayment.setAmount (m_C_Currency_ID, m_Amount);
		}

		//  Existing Cashbook entry
		m_cashLine = null;
		m_C_CashLine_ID = 0;
		if (m_mTab.getValue("C_CashLine_ID") != null)
		{
			m_C_CashLine_ID = ((Integer)m_mTab.getValue("C_CashLine_ID")).intValue();
			if (m_C_CashLine_ID == 0)
				m_cashLine = null;
			else
			{
				m_cashLine = new MCashLine (Env.getCtx(), m_C_CashLine_ID, null);
				m_DateAcct = m_cashLine.getStatementDate();
				m_C_CashBook_ID = m_cashLine.getCashBook().getC_CashBook_ID();
				bAmountField.setValue(m_cashLine.getAmount()); 
			}
		}

		//	Accounting Date
		bDateField.setValue(m_DateAcct);

		if (s_Currencies == null)
			loadCurrencies();

		//	Is the currency an EMU currency?
		Integer C_Currency_ID = new Integer(m_C_Currency_ID);
		if (s_Currencies.containsKey(C_Currency_ID))
		{
			Enumeration<Integer> en = s_Currencies.keys();
			while (en.hasMoreElements())
			{
				Object key = en.nextElement();
				bCurrencyCombo.addItem(s_Currencies.get(key));
				sCurrencyCombo.addItem(s_Currencies.get(key));
			}
			sCurrencyCombo.addActionListener(this);
			sCurrencyCombo.setSelectedItem(s_Currencies.get(C_Currency_ID));
			bCurrencyCombo.addActionListener(this);
			bCurrencyCombo.setSelectedItem(s_Currencies.get(C_Currency_ID));
		}
		else	//	No EMU Currency
		{
			bCurrencyLabel.setVisible(false);	//	Cash
			bCurrencyCombo.setVisible(false);
			sCurrencyLabel.setVisible(false);	//	Check
			sCurrencyCombo.setVisible(false);
		}

		/**
		 *	Payment Combo
		 */
		if (m_PaymentRule == null)
			m_PaymentRule = "";
		ValueNamePair vp = null;
		HashMap<?, ?> values = button.getValues();
		Object[] a = values.keySet().toArray();
		for (int i = 0; i < a.length; i++)
		{			
            String PaymentRule = (String)a[i];		//	used for Panel selection
			if (X_C_Order.PAYMENTRULE_DirectDebit.equals(PaymentRule)			//	SO
				&& !m_isSOTrx)
				continue;
			else if (X_C_Order.PAYMENTRULE_DirectDeposit.equals(PaymentRule)	//	PO 
				&& m_isSOTrx)
				continue;
                                                
			ValueNamePair pp = new ValueNamePair(PaymentRule, (String)values.get(a[i]));
			paymentCombo.addItem(pp);
			if (PaymentRule.toString().equals(m_PaymentRule))	//	to select
				vp = pp;
		}

		//	Set PaymentRule
		paymentCombo.addActionListener(this);
		if (vp != null)
			paymentCombo.setSelectedItem(vp);

		/**
		 * 	Load Payment Terms
		 */
		String SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_PaymentTerm_ID, Name FROM C_PaymentTerm WHERE IsActive='Y' ORDER BY Name",
			"C_PaymentTerm", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		KeyNamePair kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				pTermCombo.addItem(pp);
				if (key == m_C_PaymentTerm_ID)
					kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException ept)
		{
			log.log(Level.SEVERE, SQL, ept);
		}
		//	Set Selection
		if (kp != null)
			pTermCombo.setSelectedItem(kp);

		/**
		 * 	Load Accounts
		 */
		SQL = "SELECT a.C_BP_BankAccount_ID, NVL(b.Name, ' ')||'_'||NVL(a.AccountNo, ' ') AS Acct "
			+ "FROM C_BP_BankAccount a"
			+ " LEFT OUTER JOIN C_Bank b ON (a.C_Bank_ID=b.C_Bank_ID) "
			+ "WHERE C_BPartner_ID=?"
			+ "AND a.IsActive='Y' AND a.IsACH='Y'";
		kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				tAccountCombo.addItem(pp);
		//			kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException eac)
		{
			log.log(Level.SEVERE, SQL, eac);
		}
		//	Set Selection
		if (kp != null)
			tAccountCombo.setSelectedItem(kp);

		/**
		 *	Load Credit Cards
		 */
		ValueNamePair[] ccs = m_mPayment.getCreditCards();
		vp = null;
		for (int i = 0; i < ccs.length; i++)
		{
			kTypeCombo.addItem(ccs[i]);
			mTypeCombo.addItem(ccs[i]);
			if (ccs[i].getValue().equals(m_CCType))
				vp = ccs[i];
		}
		//	Set Selection
		if (vp != null){
			kTypeCombo.setSelectedItem(vp);
			mTypeCombo.setSelectedItem(vp);
			
		}

		/**
		 *  Load Bank Accounts
		 */
		SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_BankAccount_ID, Name || ' ' || AccountNo, IsDefault "
			+ "FROM C_BankAccount ba"
			+ " INNER JOIN C_Bank b ON (ba.C_Bank_ID=b.C_Bank_ID) "
			+ "WHERE b.IsActive='Y'",
			"ba", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				sBankAccountCombo.addItem(pp);
				mBankAccountCombo.addItem(pp);
				if (key == m_C_BankAccount_ID)
					kp = pp;
				if (kp == null && rs.getString(3).equals("Y"))    //  Default
					kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException ept)
		{
			log.log(Level.SEVERE, SQL, ept);
		}
		//	Set Selection
		if (kp != null){
			sBankAccountCombo.setSelectedItem(kp);
			mBankAccountCombo.setSelectedItem(kp);
		}


		/**
		 *  Load Cash Books
		 */
		SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_CashBook_ID, Name, AD_Org_ID FROM C_CashBook WHERE IsActive='Y'",
			"C_CashBook", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				bCashBookCombo.addItem(pp);
				mCashBookCombo.addItem(pp);
				
				if (key == m_C_CashBook_ID)
					kp = pp;
				if (kp == null && key == m_AD_Org_ID)       //  Default Org
					kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException epc)
		{
			log.log(Level.SEVERE, SQL, epc);
		}
		//	Set Selection
		if (kp != null)
		{
			bCashBookCombo.setSelectedItem(kp);
			mCashBookCombo.setSelectedItem(kp);
			if (m_C_CashBook_ID == 0)
				m_C_CashBook_ID = kp.getKey();  //  set to default to avoid 'cashbook changed' message
		}

		//
		return true;
	}	//	dynInit

	/**
	 *	Init OK to be able to make changes?
	 *  @return true if init OK
	 */
	public boolean isInitOK()
	{
		return m_initOK;
	}	//	isInitOK


	/**
	 *	Fill s_Currencies with EMU currencies
	 */
	private void loadCurrencies()
	{
		s_Currencies = new Hashtable<Integer,KeyNamePair>(12);	//	Currenly only 10+1
		String SQL = "SELECT C_Currency_ID, ISO_Code FROM C_Currency "
			+ "WHERE (IsEMUMember='Y' AND EMUEntryDate<SysDate) OR IsEuro='Y' "
			+ "ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				s_Currencies.put(new Integer(id), new KeyNamePair(id, name));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
	}	//	loadCurrencies


	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		log.fine( "VPayment.actionPerformed - " + e.getActionCommand());
		//	Finish
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (checkMandatory())
			{
				saveChanges (); // cannot recover
				dispose ();
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();

		//	Payment Method Change
		else if (e.getSource() == paymentCombo)
		{
			//	get selection
			ValueNamePair pp = (ValueNamePair)paymentCombo.getSelectedItem();
			if (pp != null)
			{
				String s = pp.getValue().toLowerCase();
				if (X_C_Order.PAYMENTRULE_DirectDebit.equalsIgnoreCase(s))
					s = X_C_Order.PAYMENTRULE_DirectDeposit.toLowerCase();
				s += "Panel";	
				centerLayout.show(centerPanel, s);	//	switch to panel
				//Bojana&Daniel
				//If Invoice is Vendor invoice then Cash has to be created by negative amount
				int C_Invoice_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Invoice_ID");
				MInvoice invoice_tmp = new MInvoice (Env.getCtx(), C_Invoice_ID, null);
				if (! invoice_tmp.isSOTrx())
				{
					bAmountField.setValue(m_Amount.negate());
				}else {
					bAmountField.setValue(m_Amount);
				}
				invoice_tmp = null;
				
			}
		}

		//	Check Currency change
		else if (e.getSource() == sCurrencyCombo)
		{
			KeyNamePair pp = (KeyNamePair)sCurrencyCombo.getSelectedItem();
			BigDecimal amt = MConversionRate.convert(Env.getCtx(),
				m_Amount, m_C_Currency_ID, pp.getKey(), m_AD_Client_ID, m_AD_Org_ID);
			sAmountField.setValue(amt);
		}
		//	Cash Currency change
		else if (e.getSource() == bCurrencyCombo)
		{
			KeyNamePair pp = (KeyNamePair)bCurrencyCombo.getSelectedItem();
			BigDecimal amt = MConversionRate.convert(Env.getCtx(),
				m_Amount, m_C_Currency_ID, pp.getKey(), m_AD_Client_ID, m_AD_Org_ID);
			bAmountField.setValue(amt);
		}

		//  Online
		else if (e.getSource() == kOnline || e.getSource() == sOnline)
			processOnline();
		
		//Mixed Payment Mode
		//Card - Cash
		else if (e.getSource() == mCardAmountField){
			try{
				int C_Order_ID = m_mTab.getRecord_ID();
				MOrder mOrder = new MOrder(Env.getCtx(), C_Order_ID, null);
				
				BigDecimal cardEntered = new BigDecimal(mCardAmountField.getValue() == null ? "0.00":mCardAmountField.getValue().toString());
				if(cardEntered.compareTo(mOrder.getGrandTotal()) > 0){
					mCardAmountField.setValue(mOrder.getGrandTotal());
					mCashAmount.setValue(0);
					mChequeAmountField.setValue(0);
					ADialog.error(0, this, "Invoice amount exceedes payment amounts");
				}else{
					BigDecimal chequeEntered = new BigDecimal(mChequeAmountField.getValue() == null ? "0.00":mChequeAmountField.getValue().toString());
					mCashAmount.setValue(mOrder.getGrandTotal().subtract(cardEntered).subtract(chequeEntered));
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		//Cash - Cheque	
		}else if (e.getSource() == mCashAmount){
			
			int C_Order_ID = m_mTab.getRecord_ID();
			MOrder mOrder = new MOrder(Env.getCtx(), C_Order_ID, null);
			
			BigDecimal cardEntered = new BigDecimal(mCardAmountField.getValue() == null ? "0.00":mCardAmountField.getValue().toString());
			BigDecimal cashEntered = new BigDecimal(mCashAmount.getValue() == null ? "0.00":mCashAmount.getValue().toString());
			
			
			BigDecimal cash_card = cardEntered.add(cashEntered);
			
			if(cash_card.compareTo(mOrder.getGrandTotal()) > 0){
				mCashAmount.setValue(mOrder.getGrandTotal().subtract(cardEntered));
				ADialog.error(0, this, "Invoice amount exceedes payment amounts");
			}else{
				mChequeAmountField.setValue(mOrder.getGrandTotal().subtract(cashEntered).subtract(cardEntered));
			}
		
		//Cheque amount change
		}else if (e.getSource() == mChequeAmountField){
			
			int C_Order_ID = m_mTab.getRecord_ID();
			MOrder mOrder = new MOrder(Env.getCtx(), C_Order_ID, null);
			
			BigDecimal cardEntered = new BigDecimal(mCardAmountField.getValue() == null ? "0.00":mCardAmountField.getValue().toString());
			BigDecimal cashEntered = new BigDecimal(mCashAmount.getValue() == null ? "0.00":mCashAmount.getValue().toString());
			BigDecimal chequeEntered = new BigDecimal(mChequeAmountField.getValue() == null ? "0.00":mChequeAmountField.getValue().toString());
			
			BigDecimal cash_card = cardEntered.add(cashEntered);
			BigDecimal cash_card_chq = cash_card.add(chequeEntered);
			
			if(cash_card_chq.compareTo(mOrder.getGrandTotal()) > 0){
				mChequeAmountField.setValue(mOrder.getGrandTotal().subtract(cash_card));
				ADialog.error(0, this, "Invoice amount exceedes payment amounts");
			}else{
				mCashAmount.setValue(mOrder.getGrandTotal().subtract((chequeEntered.add(cardEntered))));
			}
		}
		//CausedFocusEvent
		
	}	//	actionPerformed

	/**************************************************************************
	 *	Save Changes
	 *	@return true, if Window can exit
	 */
	private boolean saveChanges() {

		// BF [ 1920179 ] perform the save in a trx's context.
		final boolean[] success = new boolean[] { false };
		final TrxRunnable r = new TrxRunnable() {

			public void run(String trxName) {
				success[0] = saveChangesInTrx(trxName);
			}
		};
		try {
			Trx.run(r);
		} catch (Throwable e) {
			success[0] = false;
			ADialog.error(m_WindowNo, this, "PaymentError", e.getLocalizedMessage());
		}
		if (m_cashLine != null)
			m_cashLine.set_TrxName(null);	
		if (m_mPayment != null)
			m_mPayment.set_TrxName(null);
		if (m_mPaymentOriginal != null)
			m_mPayment.set_TrxName(null);
		return success[0];
	} // saveChanges

	private boolean saveChangesInTrx(final String trxName){

		// set trxname for class objects
		if (m_cashLine != null)
			m_cashLine.set_TrxName(trxName);
		if (m_mPayment != null)
			m_mPayment.set_TrxName(trxName);
		if (m_mPaymentOriginal != null)
			m_mPaymentOriginal.set_TrxName(trxName);
		
		ValueNamePair vp = (ValueNamePair)paymentCombo.getSelectedItem();
		String newPaymentRule = vp.getValue();
		log.info("New Rule: " + newPaymentRule);

		//  only Payment Rule
		if (m_onlyRule)
		{
			if (!newPaymentRule.equals(m_PaymentRule))
				m_mTab.setValue("PaymentRule", newPaymentRule);
			return true;
		}

		//	New Values
		Timestamp newDateAcct = m_DateAcct;
		int newC_PaymentTerm_ID = m_C_PaymentTerm_ID;
		int newC_CashLine_ID = m_C_CashLine_ID;
		int newC_CashBook_ID = m_C_CashBook_ID;
		String newCCType = m_CCType;
		int newC_BankAccount_ID = 0;
		String payTypes = m_Cash_As_Payment ? "KTSDB" : "KTSD";
		
		//	B (Cash)		(Currency)
		if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_Cash))
		{
			if (m_Cash_As_Payment){
				// get bank account
				KeyNamePair kp = (KeyNamePair)sBankAccountCombo.getSelectedItem();
				if (kp != null)
					newC_BankAccount_ID = kp.getKey();
			} else {
				// get cash book
				KeyNamePair kp = (KeyNamePair)bCashBookCombo.getSelectedItem();
				if (kp != null)
					newC_CashBook_ID = kp.getKey();	
			}
			
			newDateAcct = (Timestamp)bDateField.getValue();
			
			// Get changes to cash amount
			m_mPayment.setAmount(m_C_Currency_ID, (BigDecimal) bAmountField.getValue());
			m_Amount = (BigDecimal) bAmountField.getValue();			
		}

		//	K (CreditCard)  Type, Number, Exp, Approval
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_CreditCard))
		{
			vp = (ValueNamePair)kTypeCombo.getSelectedItem();
			if (vp != null)
				newCCType = vp.getValue();
		}

		//	T (Transfer)	BPartner_Bank
		else if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit) 
			|| newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit) )
		{
			tAccountCombo.getSelectedItem();
		}

		//	P (PaymentTerm)	PaymentTerm
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_OnCredit))
		{
			KeyNamePair kp = (KeyNamePair)pTermCombo.getSelectedItem();
			if (kp != null)
				newC_PaymentTerm_ID = kp.getKey();
		}

		//	S (Check)		(Currency) CheckNo, Routing
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_Check))
		{
		//	sCurrencyCombo.getSelectedItem();
			KeyNamePair kp = (KeyNamePair)sBankAccountCombo.getSelectedItem();
			if (kp != null)
				newC_BankAccount_ID = kp.getKey();
		}
		
		else if(newPaymentRule.equals(X_C_Order.PAYMENTRULE_Mixed))
		{
			//Already Payment
			if(!mCardAmountField.isReadWrite()){
				ADialog.error(0, this, "Already Paid!");
				return false;
			}
			
			BigDecimal cardEntered = new BigDecimal(mCardAmountField.getValue() == null ? "0.00":mCardAmountField.getValue().toString());
			BigDecimal cashEntered = new BigDecimal(mCashAmount.getValue() == null ? "0.00":mCashAmount.getValue().toString());
			BigDecimal chequeEntered = new BigDecimal(mChequeAmountField.getValue() == null ? "0.00":mChequeAmountField.getValue().toString());

			KeyNamePair kp = (KeyNamePair)sBankAccountCombo.getSelectedItem();
			if (kp != null)
				newC_BankAccount_ID = kp.getKey();
			
			KeyNamePair bc = (KeyNamePair) bCashBookCombo.getSelectedItem();
			if (kp != null)
				newC_CashBook_ID = kp.getKey();
			
			vp = (ValueNamePair)kTypeCombo.getSelectedItem();
			if (vp != null)
				newCCType = vp.getValue();
			
			
			int C_Order_ID = m_mTab.getRecord_ID();
			MOrder mOrder = new MOrder(Env.getCtx(), C_Order_ID, trxName);
			int C_Invoice_ID = getInvoiceID (mOrder.get_ID(), trxName);
			MInvoice mInvoice = new MInvoice(Env.getCtx(),C_Invoice_ID , trxName);
			String message = "";
			
			//Credit card
			if(cardEntered.compareTo(new BigDecimal(0)) > 0){
				
				MPayment mPayment = new MPayment(Env.getCtx(), 0, trxName);
				mPayment.setTenderType("C");
				mPayment.setC_Invoice_ID(mInvoice.get_ID());
				mPayment.setC_Order_ID(mOrder.get_ID());
				mPayment.setC_BankAccount_ID(newC_BankAccount_ID);
				mPayment.setC_Currency_ID(m_C_Currency_ID);
				mPayment.setPayAmt(cardEntered);
				mPayment.setC_DocType_ID(true);
				mPayment.setC_BPartner_ID(mInvoice.getC_BPartner_ID());
				mPayment.setCreditCard("S", newCCType, mNumberField.getText(), mNameField.getText(), mExpField.getText());
				mPayment.setA_Name(mNameField.getText());
				mPayment.saveEx(trxName);
				
				mOrder.setC_Payment_ID(mPayment.get_ID());
				mOrder.setPaymentRule("M");
				mOrder.saveEx();
				
				mPayment.processIt("CO");
				mPayment.completeIt();
				mPayment.saveEx(trxName);
				message += " Credit card payment :  " + mPayment.getDocumentNo() + ": " + cardEntered.doubleValue();
				//ADialog.warn(0, this, "Payment Created : " + );
			}
			
			//Cash journal
			if(cashEntered.compareTo(new BigDecimal(0)) > 0){
				
				BigDecimal payAmount = cashEntered;
				//  Changed Amount
				if (m_cashLine != null && payAmount.compareTo(m_cashLine.getAmount()) != 0){
					
					log.config("Changed CashBook Amount");
					//m_cashLine.setAmount(payAmount);
					m_cashLine.setAmount(payAmount);
					// ADialog.info(m_WindowNo, this, "m_cashLine - Changed Amount", "Amount: "+m_cashLine.getAmount());
					m_cashLine.saveEx();
					log.config("CashAmt Changed");
				}
				//	Different Date/CashBook
				if (m_cashLine != null && (newC_CashBook_ID != m_C_CashBook_ID || !TimeUtil.isSameDay(m_cashLine.getStatementDate(), newDateAcct)))
				{
					log.config("Changed CashBook/Date: " + m_C_CashBook_ID + "->" + newC_CashBook_ID);
					MCashLine reverse = m_cashLine.createReversal();
					reverse.saveEx();
					m_cashLine = null;
				}
				
				//	Create new
				if (m_cashLine == null)
				{
					log.config("New CashBook");
					int C_Currency_ID = 0;
					if (mInvoice != null)
						C_Currency_ID = mInvoice.getC_Currency_ID();
					if (C_Currency_ID == 0 && mOrder != null)
						C_Currency_ID = mOrder.getC_Currency_ID();
					MCash cash = null;
					if (newC_CashBook_ID != 0)
						cash = MCash.get (Env.getCtx(), newC_CashBook_ID, newDateAcct, trxName);
					else	//	Default
						cash = MCash.get (Env.getCtx(), m_AD_Org_ID, newDateAcct, C_Currency_ID, trxName);
					if (cash == null || cash.get_ID() == 0)
						ADialog.error(m_WindowNo, this, "PaymentError", CLogger.retrieveErrorString("CashNotCreated"));
					else
					{
						MCashLine cl = new MCashLine (cash);
						// cl.setAmount(new BigDecimal(bAmountField.getText()));
						//ADialog.info(m_WindowNo, this, "m_cashLine - New Cashbook", "Amount: "+cl.getAmount());
						if (mInvoice != null)
							cl.setInvoice(mInvoice);	// overrides amount
						if (mOrder != null)
						{
							cl.setOrder(mOrder, trxName); // overrides amount
							m_needSave = true;
						}
						cl.setAmount(payAmount);
						cl.saveEx();
						log.config("CashCreated");
						if (mInvoice == null && C_Invoice_ID != 0)
						{
							mInvoice = new MInvoice (Env.getCtx(), C_Invoice_ID, trxName);	
						}
						if (mInvoice != null) {
							mInvoice.setC_CashLine_ID(cl.getC_CashLine_ID());
							mInvoice.saveEx(trxName);
						}	
						if (mOrder == null && C_Order_ID != 0)
						{
							mOrder = new MOrder (Env.getCtx(), C_Order_ID, trxName);
						}
						if (mOrder != null) {
							mOrder.setC_CashLine_ID(cl.getC_CashLine_ID());
							mOrder.saveEx(trxName);
						}
						log.config("Update Order & Invoice with CashLine");
						message += " Cash Journal : " + payAmount.doubleValue();
					}
				}	
			}
			//Cheque
			if(chequeEntered.compareTo(new BigDecimal(0)) > 0){
				
				MPayment mPayment = new MPayment(Env.getCtx(), 0, trxName);
				mPayment.setTenderType("K");
				mPayment.setC_Invoice_ID(mInvoice.get_ID());
				mPayment.setC_Order_ID(mOrder.get_ID());
				mPayment.setC_DocType_ID(true);
				mPayment.setC_BPartner_ID(mInvoice.getC_BPartner_ID());
				mPayment.setPayAmt(chequeEntered);
				mPayment.setC_Currency_ID(m_C_Currency_ID);
				mPayment.setC_BankAccount_ID(newC_BankAccount_ID);
				
				
				mPayment.setRoutingNo(mRoutingField.getText());
				mPayment.setAccountNo(mChqNumberField.getText());
				mPayment.setCheckNo(mCheckField.getText());
				
				mPayment.saveEx(trxName);
				mOrder.setC_Payment_ID(mPayment.get_ID());
				mOrder.setPaymentRule("M");
				mOrder.saveEx();
				
				mPayment.processIt("CO");
				mPayment.completeIt();
				mPayment.saveEx(trxName);
				message += " Cheque payment :  " + mPayment.getDocumentNo() + ": " + chequeEntered.doubleValue();
			}
			
			ADialog.warn(0, this, "Payment Created : " + message);
			return true;
		}
		else
			return false;

		/***********************
		 *  Changed PaymentRule
		 */
		if (!newPaymentRule.equals(m_PaymentRule))
		{
			log.fine("Changed PaymentRule: " + m_PaymentRule + " -> " + newPaymentRule);
			//  We had a CashBook Entry
			if (m_PaymentRule.equals(X_C_Order.PAYMENTRULE_Cash) && !m_Cash_As_Payment) 
			{
				log.fine("Old Cash - " + m_cashLine);
				if (m_cashLine != null)
				{
					MCashLine cl = m_cashLine.createReversal();
					cl.saveEx();
				}
				newC_CashLine_ID = 0;      //  reset
			}
			//  We had a change in Payment type (e.g. Check to CC)
			else if (payTypes.indexOf(m_PaymentRule) != -1 && payTypes.indexOf(newPaymentRule) != -1 && m_mPaymentOriginal != null)
			{
				log.fine("Old Payment(1) - " + m_mPaymentOriginal);
				m_mPaymentOriginal.setDocAction(DocAction.ACTION_Reverse_Correct);
				boolean ok = m_mPaymentOriginal.processIt(DocAction.ACTION_Reverse_Correct);
				m_mPaymentOriginal.saveEx();
				if (ok)
					log.info( "Payment Cancelled - " + m_mPaymentOriginal);
				else
					ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCancelled " + m_mPaymentOriginal.getDocumentNo());
				m_mPayment.resetNew();
			}
			//	We had a Payment and something else (e.g. Check to Cash)
			else if (payTypes.indexOf(m_PaymentRule) != -1 && payTypes.indexOf(newPaymentRule) == -1) 
			{
				log.fine("Old Payment(2) - " + m_mPaymentOriginal);
				if (m_mPaymentOriginal != null)
				{
					m_mPaymentOriginal.setDocAction(DocAction.ACTION_Reverse_Correct);
					boolean ok = m_mPaymentOriginal.processIt(DocAction.ACTION_Reverse_Correct);
					m_mPaymentOriginal.saveEx();
					if (ok)        //  Cancel Payment
					{
						log.fine("PaymentCancelled " + m_mPayment.getDocumentNo ());
						m_mTab.getTableModel().dataSave(true);
						m_mPayment.resetNew();
						m_mPayment.setAmount(m_C_Currency_ID, m_Amount);
					}
					else
						ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCancelled " + m_mPayment.getDocumentNo());
				}
			}
		}

		//  Get Order and optionally Invoice
		int C_Order_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Order_ID");
		int C_Invoice_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Invoice_ID");
		if (C_Invoice_ID == 0 && m_DocStatus.equals("CO"))
			C_Invoice_ID = getInvoiceID (C_Order_ID, trxName);

		//  Amount sign negative, if ARC (Credit Memo) or API (AP Invoice)
		boolean negateAmt = false;
		MInvoice invoice = null;
		if (C_Invoice_ID != 0)
		{
			invoice = new MInvoice (Env.getCtx(), C_Invoice_ID, trxName);
			negateAmt = invoice.isCreditMemo();
		}
		MOrder order = null;
		if (invoice == null && C_Order_ID != 0)
			order = new MOrder (Env.getCtx(), C_Order_ID, trxName);
		
		BigDecimal payAmount = m_Amount;
		

		if (negateAmt)
			payAmount = m_Amount.negate();
		// Info
		log.config("C_Order_ID=" + C_Order_ID + ", C_Invoice_ID=" + C_Invoice_ID + ", NegateAmt=" + negateAmt);

		/***********************
		 *  CashBook
		 */
		if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_Cash) && !m_Cash_As_Payment)
		{
			log.fine("Cash");
			if (C_Invoice_ID == 0 && order == null)
			{
				log.config("No Invoice!");
				ADialog.error(m_WindowNo, this, "PaymentError", "CashNotCreated");
			}
			else
			{
				payAmount = (BigDecimal) bAmountField.getValue();
				//  Changed Amount
				if (m_cashLine != null
					&& payAmount.compareTo(m_cashLine.getAmount()) != 0)
				{
					log.config("Changed CashBook Amount");
					//m_cashLine.setAmount(payAmount);
					m_cashLine.setAmount((BigDecimal) bAmountField.getValue());
					// ADialog.info(m_WindowNo, this, "m_cashLine - Changed Amount", "Amount: "+m_cashLine.getAmount());
					m_cashLine.saveEx();
					log.config("CashAmt Changed");
				}
				//	Different Date/CashBook
				if (m_cashLine != null
					&& (newC_CashBook_ID != m_C_CashBook_ID 
						|| !TimeUtil.isSameDay(m_cashLine.getStatementDate(), newDateAcct)))
				{
					log.config("Changed CashBook/Date: " + m_C_CashBook_ID + "->" + newC_CashBook_ID);
					MCashLine reverse = m_cashLine.createReversal();
					reverse.saveEx();
					m_cashLine = null;
				}
				
				//	Create new
				if (m_cashLine == null)
				{
					log.config("New CashBook");
					int C_Currency_ID = 0;
					if (invoice != null)
						C_Currency_ID = invoice.getC_Currency_ID();
					if (C_Currency_ID == 0 && order != null)
						C_Currency_ID = order.getC_Currency_ID();
					MCash cash = null;
					if (newC_CashBook_ID != 0)
						cash = MCash.get (Env.getCtx(), newC_CashBook_ID, newDateAcct, trxName);
					else	//	Default
						cash = MCash.get (Env.getCtx(), m_AD_Org_ID, newDateAcct, C_Currency_ID, trxName);
					if (cash == null || cash.get_ID() == 0)
						ADialog.error(m_WindowNo, this, "PaymentError", CLogger.retrieveErrorString("CashNotCreated"));
					else
					{
						MCashLine cl = new MCashLine (cash);
						// cl.setAmount(new BigDecimal(bAmountField.getText()));
						//ADialog.info(m_WindowNo, this, "m_cashLine - New Cashbook", "Amount: "+cl.getAmount());
						if (invoice != null)
							cl.setInvoice(invoice);	// overrides amount
						if (order != null)
						{
							cl.setOrder(order, trxName); // overrides amount
							m_needSave = true;
						}
						cl.setAmount((BigDecimal)bAmountField.getValue());
						cl.saveEx();
						log.config("CashCreated");
						if (invoice == null && C_Invoice_ID != 0)
						{
							invoice = new MInvoice (Env.getCtx(), C_Invoice_ID, trxName);	
						}
						if (invoice != null) {
							invoice.setC_CashLine_ID(cl.getC_CashLine_ID());
							invoice.saveEx(trxName);
						}	
						if (order == null && C_Order_ID != 0)
						{
							order = new MOrder (Env.getCtx(), C_Order_ID, trxName);
						}
						if (order != null) {
							order.setC_CashLine_ID(cl.getC_CashLine_ID());
							order.saveEx(trxName);
						}
						log.config("Update Order & Invoice with CashLine");
					}
				}
			}	//	have invoice
		}
		/***********************
		 *  Payments
		 */
		if (("KS".indexOf(newPaymentRule) != -1) || 
				(newPaymentRule.equals(MOrder.PAYMENTRULE_Cash) && m_Cash_As_Payment))
		{
			log.fine("Payment - " + newPaymentRule);
			//  Set Amount
			m_mPayment.setAmount(m_C_Currency_ID, payAmount);
			
			if (newPaymentRule.equals(MOrder.PAYMENTRULE_CreditCard))
			{
				m_mPayment.setCreditCard(MPayment.TRXTYPE_Sales, newCCType,
					kNumberField.getText(), "", kExpField.getText());
				m_mPayment.setA_Name(kNameField.getText());
				// Get changes to credit card amount
				m_mPayment.setAmount(m_C_Currency_ID, (BigDecimal) kAmountField.getValue());
				m_mPayment.setPaymentProcessor();
			}
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit)
				|| newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit))
			{
				m_mPayment.setBankACH(newC_BankAccount_ID, m_isSOTrx, newPaymentRule, 
					tRoutingField.getText(), tNumberField.getText());
				m_mPayment.setAmount(m_C_Currency_ID, payAmount);
			}
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_Check))
			{
				m_mPayment.setBankCheck(newC_BankAccount_ID, m_isSOTrx, sRoutingField.getText(),
					sNumberField.getText(), sCheckField.getText());
				// Get changes to check amount
				m_mPayment.setAmount(m_C_Currency_ID, (BigDecimal) sAmountField.getValue());
			}
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_Cash))
			{
				// Get changes to cash amount
				m_mPayment.setTenderType(MPayment.TENDERTYPE_Cash);
				m_mPayment.setBankCash(newC_BankAccount_ID, m_isSOTrx, MPayment.TENDERTYPE_Cash);
				m_mPayment.setAmount(m_C_Currency_ID, payAmount);
			}
			m_mPayment.setC_BPartner_ID(m_C_BPartner_ID);
			m_mPayment.setC_Invoice_ID(C_Invoice_ID);
			if (order != null)
			{
				m_mPayment.setC_Order_ID(C_Order_ID);
				m_needSave = true;
			}
			m_mPayment.setDateTrx(m_DateAcct);
			m_mPayment.setDateAcct(m_DateAcct);
			m_mPayment.saveEx();
			
			//  Save/Post
			if (m_mPayment.get_ID() > 0 && MPayment.DOCSTATUS_Drafted.equals(m_mPayment.getDocStatus()))
			{
				boolean ok = m_mPayment.processIt(DocAction.ACTION_Complete);
				m_mPayment.saveEx();
				if (ok)
					ADialog.info(m_WindowNo, this, "PaymentCreated", m_mPayment.getDocumentNo());
				else
					ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCreated");
			}
			else
				log.fine("NotDraft " + m_mPayment);
		}


		/**********************
		 *	Save Values to mTab
		 */
		log.config("Saving changes");
		//
		if (!newPaymentRule.equals(m_PaymentRule))
			m_mTab.setValue("PaymentRule", newPaymentRule);
		//
		if (!newDateAcct.equals(m_DateAcct))
			m_mTab.setValue("DateAcct", newDateAcct);
		//
		if (newC_PaymentTerm_ID != m_C_PaymentTerm_ID)
			m_mTab.setValue("C_PaymentTerm_ID", new Integer(newC_PaymentTerm_ID));
		//	Set Payment
		if (m_mPayment.getC_Payment_ID() != m_C_Payment_ID)
		{
			if (m_mPayment.getC_Payment_ID() == 0)
				m_mTab.setValue("C_Payment_ID", null);
			else
				m_mTab.setValue("C_Payment_ID", new Integer(m_mPayment.getC_Payment_ID()));
		}
		//	Set Cash
		if (newC_CashLine_ID != m_C_CashLine_ID)
		{
			if (newC_CashLine_ID == 0)
				m_mTab.setValue("C_CashLine_ID", null);
			else
				m_mTab.setValue("C_CashLine_ID", new Integer(newC_CashLine_ID));
		}
		return true;
	}
	
	/**
	 *  Check Mandatory
	 *  @return true if all mandatory items are OK
	 */
	
	private boolean checkMandatory()
	{
		log.config( "VPayment.checkMandatory");

		ValueNamePair vp = (ValueNamePair)paymentCombo.getSelectedItem();
		String PaymentRule = vp.getValue();
		//  only Payment Rule
		if (m_onlyRule)
			return true;

		String CCType = m_CCType;
		//
		int C_BankAccount_ID = 0;

		/***********************
		 *	Mandatory Data Check
		 */
		boolean dataOK = true;
		//	B (Cash)		(Currency)
		if (PaymentRule.equals(MOrder.PAYMENTRULE_Cash))
		{
			if (m_Cash_As_Payment)
			{
				KeyNamePair kp = (KeyNamePair)sBankAccountCombo.getSelectedItem();
				if (kp != null)
					C_BankAccount_ID = kp.getKey();
			}
		}

		//	K (CreditCard)  Type, Number, Exp, Approval
		else if (PaymentRule.equals(MOrder.PAYMENTRULE_CreditCard))
		{
			vp = (ValueNamePair)kTypeCombo.getSelectedItem();
			if (vp != null)
				CCType = vp.getValue();
			// Validation of the credit card number is moved to the payment processor.
			// Different payment processors can have different validation rules.
		}

		//	T (Transfer)	BPartner_Bank
		else if (PaymentRule.equals(X_C_Order.PAYMENTRULE_DirectDeposit)
			|| PaymentRule.equals(X_C_Order.PAYMENTRULE_DirectDebit))
		{
			KeyNamePair bpba = (KeyNamePair)tAccountCombo.getSelectedItem();
			if (bpba == null)

			{
				tAccountCombo.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, "PaymentBPBankNotFound");
				dataOK = false;
			}
		}	//	Direct
		//      P (PaymentTerm) PaymentTerm 	 
        else if (PaymentRule.equals(X_C_Order.PAYMENTRULE_OnCredit))
        {
            // ok 	 
        }
		//	S (Check)		(Currency) CheckNo, Routing
		else if (PaymentRule.equals(MOrder.PAYMENTRULE_Check))
		{
		//	sCurrencyCombo.getSelectedItem();
			KeyNamePair kp = (KeyNamePair)sBankAccountCombo.getSelectedItem();
			if (kp != null)
				C_BankAccount_ID = kp.getKey();
			String error = MPaymentValidate.validateRoutingNo(sRoutingField.getText());
			if (error.length() != 0)
			{
				sRoutingField.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
			error = MPaymentValidate.validateAccountNo(sNumberField.getText());
			if (error.length() != 0)
			{
				sNumberField.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
			error = MPaymentValidate.validateCheckNo(sCheckField.getText());
			if (error.length() != 0)
			{
				sCheckField.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
		//Cash, card & cheque	
		}else if(PaymentRule.equals(MOrder.PAYMENTRULE_Mixed))
		{	
			
			BigDecimal cardEntered = new BigDecimal(mCardAmountField.getValue() == null ? "0.00":mCardAmountField.getValue().toString());
			BigDecimal cashEntered = new BigDecimal(mCashAmount.getValue() == null ? "0.00":mCashAmount.getValue().toString());
			BigDecimal chequeEntered = new BigDecimal(mChequeAmountField.getValue() == null ? "0.00":mChequeAmountField.getValue().toString());
			
			//Card
			if(cardEntered.compareTo(new BigDecimal(0)) > 0){
				
				//mNumberField , mExpField
				if(mNumberField.getValue().toString().equals("")){
					dataOK = false;
					mNumberField.setBackground(AdempierePLAF.getFieldBackground_Error());
					ADialog.error(m_WindowNo, this, "Credit card number is not valid");
				}
				if(mExpField.getValue().toString().equals("")){
					dataOK = false;
					mExpField.setBackground(AdempierePLAF.getFieldBackground_Error());
					ADialog.error(m_WindowNo, this, "Expiary date is not valid");
				}	
			}
			//Cash
			if(cashEntered.compareTo(new BigDecimal(0)) > 0){
				
			}
			//Cheque
			if(chequeEntered.compareTo(new BigDecimal(0)) > 0){
				
				//	sCurrencyCombo.getSelectedItem();
				KeyNamePair kp = (KeyNamePair)mBankAccountCombo.getSelectedItem();
				if (kp != null)
					C_BankAccount_ID = kp.getKey();
				String error = MPaymentValidate.validateRoutingNo(mRoutingField.getText());
				if (error.length() != 0)
				{
					mRoutingField.setBackground(AdempierePLAF.getFieldBackground_Error());
					ADialog.error(m_WindowNo, this, error);
					dataOK = false;
				}
				error = MPaymentValidate.validateAccountNo(mChqNumberField.getText());
				if (error.length() != 0)
				{
					mChqNumberField.setBackground(AdempierePLAF.getFieldBackground_Error());
					ADialog.error(m_WindowNo, this, error);
					dataOK = false;
				}
				error = MPaymentValidate.validateCheckNo(mCheckField.getText());
				if (error.length() != 0)
				{
					mCheckField.setBackground(AdempierePLAF.getFieldBackground_Error());
					ADialog.error(m_WindowNo, this, error);
					dataOK = false;
				}
			}
		}
		else
		{
			log.log(Level.SEVERE, "Unknown PaymentRule " + PaymentRule);
			ADialog.error(m_WindowNo, this, "Unknown PaymentRule " + PaymentRule);
			return false;
		}

		//  find Bank Account if not qualified yet
		if (("KTSD".indexOf(PaymentRule) != -1 || 
				(PaymentRule.equals(MOrder.PAYMENTRULE_Cash) && m_Cash_As_Payment)) 
					&& C_BankAccount_ID == 0)
		{
			//	Check & Cash (Payment) must have a bank account
			if (C_BankAccount_ID == 0 && (PaymentRule.equals(MOrder.PAYMENTRULE_Check)) || 
					(PaymentRule.equals(MOrder.PAYMENTRULE_Cash) && m_Cash_As_Payment))
           {
				ADialog.error(m_WindowNo, this, "PaymentNoProcessor");
				dataOK = false;
			}
		}
		//
		log.config("OK=" + dataOK);
		return dataOK;
	}   //  checkMandatory

	/**
	 *  Get Invoice ID for Order
	 *  @param C_Order_ID order
	 *  @return C_Invoice_ID or 0 if not found
	 */
	private static int getInvoiceID (int C_Order_ID, String trxName)
	{
		int retValue = 0;
		String sql = "SELECT C_Invoice_ID FROM C_Invoice WHERE C_Order_ID=? "
			+ "ORDER BY C_Invoice_ID DESC";     //  last invoice
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Order_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		return retValue;
	}   //  getInvoiceID

	
	/**************************************************************************
	 *  Process Online (sales only) - if approved - exit
	 */
	private void processOnline()
	{
		log.config("");
		if (!checkMandatory())
			return;

		boolean approved = false;
		String info = "";
		//
		ValueNamePair vp = (ValueNamePair)paymentCombo.getSelectedItem();
		String PaymentRule = vp.getValue();

		//  --  CreditCard
		if (PaymentRule.equals(X_C_Order.PAYMENTRULE_CreditCard))
		{
			vp = (ValueNamePair)kTypeCombo.getSelectedItem();
			String CCType = vp.getValue();

			m_mPayment.setCreditCard(MPayment.TRXTYPE_Sales, CCType,
				kNumberField.getText(), "", kExpField.getText());
			m_mPayment.setA_Name(kNameField.getText());
			m_mPayment.setAmount(m_C_Currency_ID, m_Amount);
			m_mPayment.setPaymentProcessor();
			m_mPayment.setC_BPartner_ID(m_C_BPartner_ID);
			//
			int C_Invoice_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Invoice_ID");
			int C_Order_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Order_ID");
			if (C_Invoice_ID == 0 && m_DocStatus.equals("CO"))
				C_Invoice_ID = getInvoiceID (C_Order_ID, null);  // TODO: implement trx in processOnline
			if ( C_Invoice_ID != 0 )
				m_mPayment.setC_Invoice_ID(C_Invoice_ID);
			else if ( C_Order_ID != 0 )
				m_mPayment.setC_Order_ID(C_Order_ID);
			m_mPayment.setDateTrx(m_DateAcct);
			//  Set Amount
			m_mPayment.setAmount(m_C_Currency_ID, m_Amount);
			if (!m_mPayment.save()) {
				ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCreated");
			} else {
				approved = m_mPayment.processOnline();
				info = m_mPayment.getR_RespMsg() + " (" + m_mPayment.getR_AuthCode()
					+ ") ID=" + m_mPayment.getR_PnRef();
				m_mPayment.saveEx();

				if (approved)
				{
					boolean ok = m_mPayment.processIt(DocAction.ACTION_Complete);
					m_mPayment.saveEx();
					if (ok)
						ADialog.info(m_WindowNo, this, "PaymentProcessed", info + "\n" + m_mPayment.getDocumentNo());
					else
						ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCreated");
					saveChanges();
					dispose();
				}
				else
				{
					ADialog.error(m_WindowNo, this, "PaymentNotProcessed", info);
				}
			}
		}
		else
			ADialog.error(m_WindowNo, this, "PaymentNoProcessor");
	}   //  online

	/**
	 * 	Need Save record (payment with waiting order)
	 *	@return true if payment with waiting order
	 */
	public boolean needSave()
	{
		return m_needSave;
	}	//	needSave
		
}	//	VPayment