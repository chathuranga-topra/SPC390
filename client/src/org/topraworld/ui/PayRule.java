package org.topraworld.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.compiere.apps.ADialog;
import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.swing.CComboBox;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;

public class PayRule extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPay;
    private CComboBox cmbCardType;
    private CComboBox cmbbank;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCash;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelCheque;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField txtCardAmount;
    private javax.swing.JTextField txtCardExpires;
    private javax.swing.JTextField txtCardName;
    private javax.swing.JTextField txtCardNum;
    private javax.swing.JTextField txtCash;
    private javax.swing.JTextField txtCashBal;
    private javax.swing.JLabel txtCashBillAmt;
    private javax.swing.JTextField txtChqAccNo;
    private javax.swing.JTextField txtChqAmount;
    private javax.swing.JTextField txtChqCheckNo;
    private javax.swing.JTextField txtChqRouting;
    private javax.swing.JTextField txtTotal;
    private String payMode;
    private String total;
    private Cashier_SPC parent;
    private Trx trx;
    private int C_Invoice_ID;
    private BigDecimal cash;
    private BigDecimal balance;

	public PayRule(Cashier_SPC parent) {
    	
        super(parent.getFrame(), true);
        this.payMode = parent.getPaymentRule();
    	this.total = parent.getTotal();
    	this.parent = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Customer Payment");
    }
    
    
    private void initComponents() {    	

    	jPanel1 = new javax.swing.JPanel();
    	jPanelCash = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanelCheque = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelCard = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        
        trx = Trx.get(Trx.createTrxName("CASHIER_CASH"), true);
        
        
        txtCash = new JTextField();
        txtCash.setActionCommand("txtCash");
        txtCash.addActionListener(this);
        txtCashBal = new JTextField();
        txtCashBal.setActionCommand("txtCashBal");
        txtCashBal.addActionListener(this);
        txtCardName = new JTextField();
        txtCardName.setActionCommand("txtCardName");
        txtCardName.addActionListener(this);
        txtCardExpires = new JTextField();
        txtCardExpires.setActionCommand("txtCardExpires");
        txtCardExpires.addActionListener(this);
        txtCardAmount = new JTextField();
        txtCardAmount.setActionCommand("txtCardAmount");
        txtCardAmount.addActionListener(this);
        txtCardNum = new JTextField();
        txtCardNum.setActionCommand("txtCardNum");
        txtCardNum.addActionListener(this);
        txtTotal = new JTextField();
        txtTotal.setActionCommand("txtTotal");
        txtTotal.addActionListener(this);
        
        //txtCardExpires.setEditable(false);
        //txtCardName.setEditable(false);
        
        txtCashBillAmt = new javax.swing.JLabel();
        txtChqAccNo = new JTextField();
        txtChqAccNo.addActionListener(this);
        txtChqAccNo.setActionCommand("txtChqAccNo");
        txtChqCheckNo = new JTextField();
        txtChqCheckNo.addActionListener(this);
        txtChqCheckNo.setActionCommand("txtChqCheckNo");
        txtChqAmount = new JTextField();
        txtChqAmount.addActionListener(this);
        txtChqCheckNo.setActionCommand("txtChqCheckNo");
        txtChqAmount.setEditable(false);
        txtChqAmount.addActionListener(this);
        txtChqAmount.setActionCommand("txtChqAmount");
        txtChqRouting = new JTextField();
        txtChqRouting.addActionListener(this);
        txtChqRouting.setActionCommand("txtChqRouting");
        
        cmbCardType = new CComboBox();
        cmbbank = new CComboBox();
        
        btnClose = new JButton();
        btnClose.addActionListener(this);
        btnPay = new JButton();
        btnPay.setEnabled(false);
        btnPay.addActionListener(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelCash.setBorder(javax.swing.BorderFactory.createTitledBorder("Cash"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Cash");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Balance");
        
        txtCashBal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCashBal.setForeground(new java.awt.Color(255, 0, 0));
        txtCashBal.setText("0.00");
        txtCashBal.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanelCash);
        jPanelCash.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCash, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txtCashBal))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCashBal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanelCard.setBorder(javax.swing.BorderFactory.createTitledBorder("Card"));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText(" Type");
        jLabel3.setPreferredSize(new java.awt.Dimension(40, 14));

        cmbCardType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VISA", "MASTER", "AMEX" }));
        //this.setCreditCards();

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Number");
        jLabel4.setPreferredSize(new java.awt.Dimension(40, 14));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Name");
        jLabel5.setPreferredSize(new java.awt.Dimension(40, 14));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Expiers(MMYY)");
        jLabel6.setPreferredSize(new java.awt.Dimension(40, 14));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Amount");
        jLabel7.setPreferredSize(new java.awt.Dimension(40, 14));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanelCard);
        jPanelCard.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCardAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCardType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCardNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCardName)
                            .addComponent(txtCardExpires))))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCardType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCardNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCardName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCardExpires, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCardAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtTotal.setText("0.00");
        txtTotal.setEditable(false);

        txtCashBillAmt.setBackground(new java.awt.Color(255, 0, 0));
        txtCashBillAmt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCashBillAmt.setForeground(new java.awt.Color(255, 0, 0));
        txtCashBillAmt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtCashBillAmt.setText("Total Amount");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCashBillAmt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCashBillAmt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCheque.setBorder(javax.swing.BorderFactory.createTitledBorder("Cheque"));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Bank");
        jLabel8.setPreferredSize(new java.awt.Dimension(40, 14));
        //
        this.setBankAccounts();

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText(" Routing No");
        jLabel9.setPreferredSize(new java.awt.Dimension(40, 14));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Acc No");
        jLabel10.setPreferredSize(new java.awt.Dimension(40, 14));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Check No");
        jLabel11.setPreferredSize(new java.awt.Dimension(40, 14));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Amount");
        jLabel12.setPreferredSize(new java.awt.Dimension(40, 14));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanelCheque);
        jPanelCheque.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChqAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbbank, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChqRouting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChqAccNo)
                            .addComponent(txtChqCheckNo))))
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbbank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChqRouting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChqAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChqCheckNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChqAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnClose.setText("Close");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnPay.setText("Complete");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanelCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
      //Press escape to close
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"ESCAPE");
        this.getRootPane().getActionMap().put("ESCAPE",new AbstractAction(){
            public void actionPerformed(ActionEvent ae)
            {	
            	btnClose.doClick();
            }
        });
        
        //Enter to pay
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"Enter");
        this.getRootPane().getActionMap().put("Enter",new AbstractAction(){
            public void actionPerformed(ActionEvent ae)
            {	
            	btnPay.doClick();
            }
        });
        
        this.txtTotal.setText(total);
        
        switch(payMode){
        //Cash
		case  "B" :
			jPanelCash.setVisible(true);
			jPanelCard.setVisible(false);
			jPanelCheque.setVisible(false);
			txtCash.setText("");
			this.getFocus(txtCash);
			break;
		//Card	
		case  "K" :
			jPanelCard.setVisible(true);
			jPanelCash.setVisible(false);
			jPanelCheque.setVisible(false);
			txtCardAmount.setText(total);
			txtCardAmount.setEditable(false);
			btnPay.setEnabled(true);
			this.getFocus(txtCardNum);
			break;
		//Mix	
		case  "M" :
			jPanelCard.setVisible(true);
			jPanelCash.setVisible(true);
			jPanelCheque.setVisible(true);
			btnPay.setEnabled(false);
			txtCardAmount.setText("0.00");
			txtChqAmount.setEditable(true);
			this.getFocus(txtCash);
			break;
		//Cheque	
		case  "S" :
			jPanelCheque.setVisible(true);
			jPanelCard.setVisible(false);
			jPanelCash.setVisible(false);
			this.getFocus(txtChqCheckNo);
			txtChqAmount.setText(total);
			txtChqAmount.setEditable(false);
			btnPay.setEnabled(true);
			break;
        }
        pack(); 
    }
    
    private void getFocus(final Component txtCash){
    	
    	this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                txtCash.requestFocus();
            }
        });
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			
			String action = e.getActionCommand();
			if (action == null || action.length() == 0)
				return;
			
			if(action.equals("Complete")){
				
				if(!this.validatepayment()){
					ADialog.error(0, this, "Please fill mandetory fields to proceed!");
					return;
				}
				
				if(this.completeOrder()){
					this.parent.clear();
					this.parent.openCashDrawer();
					this.dispose();
					ADialog.warn(0, this, "Payment Succeeded!", "Press ENTER to continue..");
				}else{
					ADialog.error(0, this, "Payment Error!");
				}
				
			}else if(action.equals("Close")){
				this.dispose();
				
			}else if(action.equals("txtCash")){
				
				//Cash Payment
				if(payMode.equals("B"))
					this.setCashBalance();
				//Mix Payment
				else{
					//set balance to card amount
					BigDecimal total = new BigDecimal(txtTotal.getText());
					BigDecimal cash = new BigDecimal(txtCash.getText().length()== 0?"0":txtCash.getText());
					BigDecimal bal = total.subtract(cash);
					bal = bal.setScale(2, RoundingMode.HALF_UP);
					txtCardAmount.setText(bal.toString());
					
					//disable cash
					txtCash.setEditable(txtCash.getText().length()!=0);
					txtCardAmount.requestFocusInWindow();
				}
				
			}else if(e.getSource().equals(txtCardNum)){
				if(txtCardNum.getText().length()<1){
					txtCardNum.setBackground(Color.pink);
					txtCardNum.requestFocusInWindow();
				}else{
					btnPay.setEnabled(true);
					btnPay.requestFocusInWindow();
				}
			}
			
			else if(e.getSource().equals(txtCardName))
				txtCardExpires.requestFocusInWindow();
			
			else if(e.getSource().equals(txtCardExpires)){
				
				if(txtCardExpires.getText().length()<1){
					txtCardExpires.setBackground(Color.pink);
					txtCardExpires.requestFocusInWindow();
				}else{
					if(payMode.equals("K")){
						btnPay.requestFocusInWindow();
					//Mix Payment	
					}else{
						//enable complete button Cash + Card
						Double chq = Double.parseDouble(txtChqAmount.getText());
						if(chq <= 0.00){
							btnPay.setEnabled(true);
							btnPay.requestFocusInWindow();
						//With Cheque Payment	
						}else{
							txtChqRouting.requestFocusInWindow();
						}
					}
				}	
				
			}else if(e.getSource().equals(txtChqRouting)){
				
				txtChqAccNo.requestFocusInWindow();
				
			}else if(e.getSource().equals(txtChqAccNo)){
				txtChqCheckNo.requestFocusInWindow();
				
			}else if(e.getSource().equals(txtChqCheckNo)){
				if(txtChqCheckNo.getText().length()<1){
					txtChqCheckNo.setBackground(Color.pink);
					txtChqCheckNo.requestFocusInWindow();
				}else
					btnPay.setEnabled(true);
					btnPay.requestFocusInWindow();
				
			}else if(e.getSource().equals(txtCardAmount)){
				//Mix Payment
				txtCardNum.requestFocusInWindow();
				//Calculate Different for cheque
				BigDecimal total = new BigDecimal(txtTotal.getText());
				BigDecimal card = new BigDecimal(txtCardAmount.getText().length()== 0?"0":txtCardAmount.getText());
				BigDecimal cash = new BigDecimal(txtCash.getText().length()== 0?"0":txtCash.getText());
				BigDecimal bal = total.subtract(card.add(cash));
				bal = bal.setScale(2, RoundingMode.HALF_UP);
				txtChqAmount.setText(bal.toString());
				txtChqAmount.setEditable(false);
			}
			else
				return;
			
		}catch(Exception ex){
			ADialog.warn(0,this, ex.getMessage());
			ex.printStackTrace();
		}
	}   
	
	private void setCashBalance(){
		
		if(txtCash.getText().length() > 0){
			try{
				BigDecimal total = new BigDecimal(txtTotal.getText());
				BigDecimal cash = new BigDecimal(txtCash.getText());
				BigDecimal bal = cash.subtract(total);
				bal = bal.setScale(2, RoundingMode.HALF_UP);
				
				if(bal.compareTo(new BigDecimal("-.1")) <=0){
					ADialog.warn(0,this, "STOP! Insuffient Cash.");
					txtCash.setBackground(Color.pink);
					txtCash.requestFocusInWindow();
					btnPay.setEnabled(false);
				}else{
					txtCash.setBackground(Color.white);
					txtCashBal.setText(bal.toString());
					btnPay.setEnabled(true);
					btnPay.requestFocusInWindow();
				}
				
			}catch(Exception ex){}
		}
	}
	
	int count = 0;
	private boolean completeOrder(){
		
		JTable orderTable =  this.parent.getOrderTable();
		DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
		Properties		p_ctx = Env.getCtx();
		boolean orderCompleted = false;
		count = dtm.getRowCount();
		
		cash = new BigDecimal(txtCash.getText().length() == 0?"0": txtCash.getText());
		balance = new BigDecimal(txtCashBal.getText().length() == 0 ?"0": txtCashBal.getText());
		
		for (int i = 0 ; i <  count; i++){
			MOrder mOrder = new MOrder(p_ctx, ((MOrder) dtm.getValueAt(i, 0)).get_ID(), trx.getTrxName());
			try{
				if (mOrder.getDocStatus().equals("DR") || mOrder.getDocStatus().equals("IP") ){
					
					mOrder.setPaymentRule(this.payMode);
					mOrder.processIt(DocAction.ACTION_Complete);
					mOrder.setDocAction(DocAction.ACTION_Close);
					mOrder.setDocStatus(DocAction.ACTION_Complete);
					mOrder.completeIt();
					mOrder.save();
					
					trx.commit();
					
					//Create payments based on the payment rule
					//Cash Payment
					if(this.payMode.equals("B")){
						//set cash balances for mulpiple invoices
						if(orderTable.getRowCount() == 1){
							mOrder.set_ValueOfColumn("CASH_BALANCE", balance+"");
							mOrder.set_ValueOfColumn("ORDERTYPE", cash+"");
							//for many invoices	
						}else{
							if(count -1 != i){
								mOrder.set_ValueOfColumn("CASH_BALANCE",cash+"");
								mOrder.set_ValueOfColumn("ORDERTYPE", cash+"");
								cash = cash.subtract(mOrder.getGrandTotal().setScale(2, RoundingMode.HALF_UP));
							//last bill	
							}else{
								mOrder.set_ValueOfColumn("CASH_BALANCE", cash.subtract(mOrder.getGrandTotal()).setScale(2, RoundingMode.HALF_UP)+"");
								mOrder.set_ValueOfColumn("ORDERTYPE", cash+"");
							}
						}
					orderCompleted = this.createCashLine(mOrder , mOrder.getGrandTotal());
					//Card Payment	
					}else if(this.payMode.equals("K")){
						if(this.validatepayment())
							orderCompleted = this.createCardPayment(mOrder , mOrder.getGrandTotal());
					//Mix Payment
					}else if(this.payMode.equals("M")){
						if(this.validatepayment()){
							
							BigDecimal card = new BigDecimal(txtCardAmount.getText().length()== 0?"0":txtCardAmount.getText());
							BigDecimal cash = new BigDecimal(txtCash.getText().length()== 0?"0":txtCash.getText());
							BigDecimal cheque = new BigDecimal(txtChqAmount.getText().length()== 0?"0":txtChqAmount.getText());
							
							//Create cash Line
							if(cash.compareTo(new BigDecimal(0)) > 0)
								orderCompleted = this.createCashLine(mOrder , cash);
							
							//Create Card Payment
							if(card.compareTo(new BigDecimal(0)) > 0)
								orderCompleted = this.createCardPayment(mOrder , card);
							
							
							if(cheque.compareTo(new BigDecimal(0)) > 0)
								orderCompleted = this.createCheckPayment(mOrder , cheque);
							
							mOrder.setPaymentRule(X_C_Order.PAYMENTRULE_Mixed);
							mOrder.save();
							
						}
					//Cheque payment	
					}else if(this.payMode.equals("S")){
						if(this.validatepayment())
							orderCompleted = this.createCheckPayment(mOrder , mOrder.getGrandTotal());
					}
					
				}else{
					ADialog.warn(0,this, "Completed Order!");
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
				ADialog.warn(0,this, "Process Terminated! Please Contact System Administrator.");
			}finally{
				if( mOrder.getDocStatus().equals("IN") ){
					mOrder.setDocStatus("DR");
					mOrder.save();
				}
				else if(mOrder.getDocStatus().equals("CO") ){
					orderCompleted = true;
					mOrder.save();
					trx.commit();
					//print order
					mOrder.setIsPrinted(this.parent.printReciept(mOrder));
				}
			}
	    }
		trx.close();
		return orderCompleted;
	}
	
	private boolean createCashLine(MOrder mOrder , BigDecimal payAmount){
		
		//No need to create cash journal manually for pos orders except pos terminal created orders
		if(mOrder.getC_POS_ID() == 0 && mOrder.getPaymentRule().equals(X_C_Order.PAYMENTRULE_Cash))
			return true;
		
		boolean success = false;
		//BigDecimal payAmount = mOrder.getGrandTotal();
		
		mOrder.setPaymentRule(X_C_Order.PAYMENTRULE_Cash);
		int newC_CashBook_ID =0;
		int m_AD_Org_ID = mOrder.getAD_Org_ID();
		int C_Order_ID = mOrder.get_ID();
		
		Timestamp newDateAcct = new Timestamp(System.currentTimeMillis());
		int C_Invoice_ID = PayRule.getInvoiceID(mOrder.get_ID(), trx.getTrxName());
		MInvoice mInvoice = new MInvoice(Env.getCtx(), C_Invoice_ID, trx.getTrxName());
		
		int C_Currency_ID = 0;
		if (mInvoice != null)
			C_Currency_ID = mInvoice.getC_Currency_ID();
		if (C_Currency_ID == 0 && mOrder != null)
			C_Currency_ID = mOrder.getC_Currency_ID();
		MCash cash = null;
		if (newC_CashBook_ID != 0)
			cash = MCash.get (Env.getCtx(), newC_CashBook_ID, newDateAcct, trx.getTrxName());
		else	//	Default
			cash = MCash.get (Env.getCtx(), m_AD_Org_ID, newDateAcct, C_Currency_ID, trx.getTrxName());
		if (cash == null || cash.get_ID() == 0)
			ADialog.error(0, this, "PaymentError", CLogger.retrieveErrorString("CashNotCreated"));
		else{
			
			MCashLine cl = new MCashLine (cash);
			
			if (mInvoice != null)
				cl.setInvoice(mInvoice);	// overrides amount
			if (mOrder != null){
				
				cl.setOrder(mOrder, trx.getTrxName()); // overrides amount
			}
			cl.setAmount(payAmount);
			cl.saveEx();
			success = true;
			
			if (mInvoice != null) {
				mInvoice.setC_CashLine_ID(cl.getC_CashLine_ID());
				mInvoice.saveEx(trx.getTrxName());
			}	
			if (mOrder == null && C_Order_ID != 0){
				
				mOrder = new MOrder (Env.getCtx(), C_Order_ID, trx.getTrxName());
			}
			if (mOrder != null) {
				mOrder.setC_CashLine_ID(cl.getC_CashLine_ID());
				mOrder.saveEx(trx.getTrxName());
			}
		}

		trx.commit();
		return success;
	}
	
	//Card payment
	private boolean createCardPayment(MOrder mOrder , BigDecimal payAmount){
		
		//set payment type
		mOrder.setPaymentRule(X_C_Order.PAYMENTRULE_CreditCard);
		MPayment payment = createPayment(mOrder , MPayment.TENDERTYPE_CreditCard);
		payment.setAmount(mOrder.getC_Currency_ID(), payAmount);
		
		String cardtype = (cmbCardType.getSelectedItem().toString()).substring(0, 1);
		//get bank account
		KeyNamePair pair = (KeyNamePair) cmbbank.getSelectedItem();
		payment.setC_BankAccount_ID(pair.getKey());
		//payment.setCreditCard(MPayment.TRXTYPE_Sales,cardtype , cardNo, "", month, year);
		//payment.setCreditCard("S", cardtype, txtCardNum.getText(), "", txtCardExpires.getText());
		payment.setCreditCardType(cardtype);
		payment.setCreditCardExp(txtCardExpires.getText());
		payment.setCreditCardNumber(txtCardNum.getText());
		payment.setA_Name(txtCardName.getText());
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		payment.saveEx();
		if ( payment.processIt(MPayment.DOCACTION_Complete) )
		{
			payment.saveEx();
			return true;
		}else 
			return false;
	}
	
	public boolean createCheckPayment(MOrder mOrder ,BigDecimal payAmount){
	
		mOrder.setPaymentRule(X_C_Order.PAYMENTRULE_Check);
		MPayment payment = createPayment(mOrder ,MPayment.TENDERTYPE_Check);
		payment.setAmount(mOrder.getC_Currency_ID(), payAmount);
		//set bank account
		KeyNamePair pair = (KeyNamePair) cmbbank.getSelectedItem();
		payment.setC_BankAccount_ID(pair.getKey());
		
		payment.setAccountNo(txtChqAccNo.getText());
		payment.setRoutingNo(txtChqRouting.getText());
		payment.setCheckNo(txtChqCheckNo.getText());
		payment.saveEx();
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if ( payment.processIt(MPayment.DOCACTION_Complete) ){
			payment.saveEx();
			return true;
		}else
			return false;
	}	
	
	
	private MPayment createPayment(MOrder mOrder , String tenderType)
	{
		MPayment payment = new MPayment(Env.getCtx(), 0, null);
		payment.setAD_Org_ID(mOrder.getAD_Org_ID());
		payment.setTenderType(tenderType);
		payment.setC_Order_ID(mOrder.get_ID());
		payment.setIsReceipt(true);
		payment.setC_BPartner_ID(mOrder.getC_BPartner_ID());
		//set invoice
		payment.setC_Invoice_ID(this.getInvoiceID(mOrder.get_ID(), trx.getTrxName()));
		return payment;
	}
		
	private static int getInvoiceID (int C_Order_ID, String trxName)
	{
		
		String sql = "SELECT C_Invoice_ID FROM C_Invoice WHERE C_Order_ID=? "
			+ "ORDER BY C_Invoice_ID DESC";     //  last invoice
		return DB.getSQLValue(trxName, sql, C_Order_ID);
	}
	
	private void setBankAccounts(){
		
		String SQL = "SELECT a.c_bankaccount_id ,  b.name ||'_'|| a.accountno  from c_bank b , c_bankaccount a where b.c_bank_id = a.c_bank_id "
				+ " and b.ad_org_id = ? ";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, Env.getAD_Org_ID(Env.getCtx()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				cmbbank.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException eac){ 
			eac.printStackTrace();
		}
	}
	
	private boolean validatepayment(){
		
		boolean isValid = true;
		//Card
		if(this.payMode.equals("K")){
		
			if(txtCardNum.getText().length() == 0){
				txtCardNum.setBackground(Color.pink);
				isValid = false;
				txtCardNum.requestFocusInWindow();
				
			}	
			/*if(txtCardExpires.getText().length() == 0){
				txtCardExpires.setBackground(Color.pink);
				isValid = false;
				txtCardExpires.requestFocusInWindow();
			}*/
			
		//Cheque	
		}else if(this.payMode.equals("S")){
			
			if(txtChqCheckNo.getText().length() < 0){
				txtChqCheckNo.setBackground(Color.pink);
				isValid = false;
			}
		//Mix	
		}else if(this.payMode.equals("M")){
			
			//Total Balance
			BigDecimal total = new BigDecimal(txtTotal.getText());
			BigDecimal card = new BigDecimal(txtCardAmount.getText().length()== 0?"0":txtCardAmount.getText());
			BigDecimal cash = new BigDecimal(txtCash.getText().length()== 0?"0":txtCash.getText());
			BigDecimal cheque = new BigDecimal(txtChqAmount.getText().length()== 0?"0":txtChqAmount.getText());
			
			total = total.subtract((card.add(cash).add(cheque)));
			if(total.compareTo(new BigDecimal(0))>0){
				isValid = false;
				ADialog.error(0, this, "Mix payment Error","Insuficient Amount");
			}	
		}
		
		return isValid;
	}
	
}
