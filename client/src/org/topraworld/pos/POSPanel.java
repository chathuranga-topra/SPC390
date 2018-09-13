package org.topraworld.pos;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.swing.CFrame;
import org.compiere.util.DB;
import org.topraworld.pos.OrderInfor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

//org.topraworld.pos.POSPanel
public class POSPanel implements FormPanel , ActionListener{

	private CFrame frame;
	private POSBasePanel basePanel;
	private int WindowNo;
	boolean answer;
	private String keyBoardref;
	private int width , height , mainComWidth;
	private POSModel posModel;
	private FormFrame formFrame;

	private JButton brnArrowFirst ,btnArrowDown ,btnArrowLast ,btnArrowUp ,btnCustomer ,btnCut ,btnDecrease ,btnIncrease ,btnNewCard , btnQueryProduct;
	private JButton btnNewCash ,btnNewReturn ,btnPrint ,btnProduct ,btnSearch ,btnSwipe ,btnRequsition , btnOrderType;
	private JLabel jLabel1 , jLabel2 , jLabel3 ,jLabel4 ,jLabel5 , jLabel6 , jLabel7;
	private JPanel jPanel1 , jPanel2 , jPanel3 , jPanel4 , jPanel5 , jPanel7;
	private JScrollPane jScrollPane1;
	private JTable tblLines;
	private JTextField txtDiscount , txtDocNo , txtItemCount  , txtRef , txtTotal;
	private JScrollBar vertical;
	
	public POSPanel(POSBasePanel basePanel){
		this.basePanel = basePanel;
	}
	
	
	
	 @Override
	 public void init(int WindowNo, FormFrame formFrame) {
		
		this.frame = formFrame.getCFrame();
		this.formFrame = formFrame;
		this.WindowNo = WindowNo;
		posModel = new POSModel(this, this.basePanel.getTrx(), this.basePanel.getM_ctx());
		
		//set window size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width; 
		height = screenSize.height;
		mainComWidth = (width / 2)/8; 

		
		
		jPanel1 = new javax.swing.JPanel(); 
		jPanel3 = new javax.swing.JPanel();
		btnNewReturn = new javax.swing.JButton();
		btnNewCash = new javax.swing.JButton();
		btnNewCard = new javax.swing.JButton();
		btnSwipe = new javax.swing.JButton();
		btnRequsition = new javax.swing.JButton();
		btnPrint = new javax.swing.JButton();
		btnSearch = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		txtItemCount = new javax.swing.JTextField();
		btnOrderType = new javax.swing.JButton();
		txtDocNo = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		txtTotal = new javax.swing.JTextField();
		txtDiscount = new javax.swing.JTextField();
		jPanel7 = new javax.swing.JPanel();
		brnArrowFirst = new javax.swing.JButton();
		btnArrowUp = new javax.swing.JButton();
		btnArrowDown = new javax.swing.JButton();
		btnArrowLast = new javax.swing.JButton();
		btnIncrease = new javax.swing.JButton();
		btnDecrease = new javax.swing.JButton();
		btnCut = new javax.swing.JButton();
		jPanel5 = new javax.swing.JPanel();
		btnCustomer = new javax.swing.JButton();
		btnQueryProduct = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		btnProduct = new javax.swing.JButton();
		txtRef = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblLines = new javax.swing.JTable();
		
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        //btnNewReturn.setBackground(new java.awt.Color(255, 255, 255));
        btnNewReturn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnNewReturn.setForeground(new java.awt.Color(102, 102, 255));
        btnNewReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/return.png"))); // NOI18N
        btnNewReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNewReturn.addActionListener(this);
        
        //btnNewCash.setBackground(new java.awt.Color(255, 255, 255));
        btnNewCash.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnNewCash.setForeground(new java.awt.Color(102, 102, 255));
        btnNewCash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/cash.png"))); // NOI18
        btnNewCash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNewCash.addActionListener(this);
        

        //btnNewCard.setBackground(new java.awt.Color(255, 255, 255));
        btnNewCard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnNewCard.setForeground(new java.awt.Color(102, 102, 255));
        btnNewCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/card.png"))); // NOI18N
        btnNewCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNewCard.addActionListener(this);
        
        //btnSwipe.setBackground(new java.awt.Color(255, 255, 255));
        btnSwipe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnSwipe.setForeground(new java.awt.Color(102, 102, 255));
        btnSwipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/logout.png"))); // NOI18N
        btnSwipe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSwipe.addActionListener(this);

        //btnRequsition.setBackground(new java.awt.Color(255, 255, 255));
        btnRequsition.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnRequsition.setForeground(new java.awt.Color(102, 102, 255));
		btnRequsition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/requsition.png"))); // NOI18N
		btnRequsition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		btnRequsition.addActionListener(this);
		
        //btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnPrint.setForeground(new java.awt.Color(102, 102, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/printer.png"))); // NOI18N
        btnPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPrint.addActionListener(this);
        
        //btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnSearch.setForeground(new java.awt.Color(102, 102, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/search.png"))); // NOI18N
        btnSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSearch.addActionListener(this);
        
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNewCash, javax.swing.GroupLayout.PREFERRED_SIZE, mainComWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewCard, javax.swing.GroupLayout.PREFERRED_SIZE, mainComWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewReturn, javax.swing.GroupLayout.PREFERRED_SIZE, mainComWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, mainComWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRequsition, javax.swing.GroupLayout.PREFERRED_SIZE, mainComWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, mainComWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSwipe, javax.swing.GroupLayout.PREFERRED_SIZE, mainComWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewCash, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewCard, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSwipe, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRequsition, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        
        //jPanel3.setPreferredSize(new java.awt.Dimension(width / 2, jPanel3.getHeight()));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Document No");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Order Type");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Item Count");

        txtItemCount.setEditable(false);
        txtItemCount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //txtItemCount.setForeground(new java.awt.Color(0,0,0));
        txtItemCount.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        
        btnOrderType.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnOrderType.setForeground(new java.awt.Color(0,0,0));
        btnOrderType.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        btnOrderType.addActionListener(this);
        

        txtDocNo.setEditable(false);
        txtDocNo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //txtDocNo.setForeground(new java.awt.Color(0,0,0));
        txtDocNo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Discount");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Grand Total");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        //txtTotal.setForeground(new java.awt.Color(255, 102, 102));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0.00");

        txtDiscount.setEditable(false);
        txtDiscount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //txtDiscount.setForeground(new java.awt.Color(255, 102, 102));
        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscount.setText("0.00");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

       // brnArrowFirst.setBackground(new java.awt.Color(255, 255, 255));
        brnArrowFirst.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //brnArrowFirst.setForeground(new java.awt.Color(51, 204, 0));
        brnArrowFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/fullUp.png"))); // NOI18N
        brnArrowFirst.addActionListener(this);
        
        //btnArrowUp.setBackground(new java.awt.Color(255, 255, 255));
        btnArrowUp.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnArrowUp.setForeground(new java.awt.Color(51, 204, 0));
        btnArrowUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/up.png"))); // NOI18N
        btnArrowUp.addActionListener(this);
        
       // btnArrowDown.setBackground(new java.awt.Color(255, 255, 255));
        btnArrowDown.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnArrowDown.setForeground(new java.awt.Color(51, 204, 0));
        btnArrowDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/down.png"))); // NOI18N
        btnArrowDown.addActionListener(this);
        
       // btnArrowLast.setBackground(new java.awt.Color(255, 255, 255));
        btnArrowLast.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnArrowLast.setForeground(new java.awt.Color(51, 204, 0));
        btnArrowLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/fullDown.png"))); // NOI18N
        btnArrowLast.addActionListener(this);

       //btnIncrease.setBackground(new java.awt.Color(255, 255, 255));
        btnIncrease.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnIncrease.setForeground(new java.awt.Color(255, 0, 0));
        btnIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/plus.png"))); // NOI18N
        btnIncrease.addActionListener(this);

        //btnDecrease.setBackground(new java.awt.Color(255, 255, 255));
        btnDecrease.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnDecrease.setForeground(new java.awt.Color(51, 204, 0));
        btnDecrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/minus.png"))); // NOI18N
        btnDecrease.addActionListener(this);

        //btnCut.setBackground(new java.awt.Color(255, 255, 255));
        btnCut.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnCut.setForeground(new java.awt.Color(51, 204, 0));
        btnCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/delete.png"))); // NOI18N
        btnCut.addActionListener(this);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brnArrowFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowUp, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowDown, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowLast, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCut, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brnArrowFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArrowUp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArrowDown, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArrowLast, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCut, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDocNo, javax.swing.GroupLayout.DEFAULT_SIZE, mainComWidth * 2, Short.MAX_VALUE)
                    .addComponent(btnOrderType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtItemCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDocNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOrderType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtItemCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(null);

        //btnCustomer.setBackground(new java.awt.Color(255, 255, 255));
        btnCustomer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        //btnCustomer.setText("Customer");
        jPanel5.add(btnCustomer);
        btnCustomer.setBounds(60, 20, 150, 40);
        btnCustomer.addActionListener(this);

        btnQueryProduct.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnQueryProduct.setForeground(new java.awt.Color(0,0,0));
        btnQueryProduct.setMinimumSize(new java.awt.Dimension(10, 23));
        btnQueryProduct.setPreferredSize(new java.awt.Dimension(10, 23));
        jPanel5.add(btnQueryProduct);
        btnQueryProduct.setBounds(60, 70, 430, 40);
        //btnQueryProduct.setBackground(new java.awt.Color(255, 255, 255));
        btnQueryProduct.addActionListener(this);
        btnQueryProduct.setText("CREATE LINES");
        
        txtRef.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //txtRef.setForeground(new java.awt.Color(0,0,0));
        txtRef.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRef.setMinimumSize(new java.awt.Dimension(10, 23));
        txtRef.setPreferredSize(new java.awt.Dimension(10, 23));
        jPanel5.add(txtRef);
        txtRef.setBounds(275, 20, 215, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Reference");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(200, 30, 70, 15);

       // btnProduct.setBackground(new java.awt.Color(255, 255, 255));
        btnProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/productInfor.png"))); // NOI18N
        jPanel5.add(btnProduct);
        btnProduct.setBounds(10, 70, 40, 40);

        
        
        txtRef.addMouseListener(new MouseAdapter()
        {   
			@Override
            public void mouseClicked(MouseEvent e)
            {
				setRefference();
				
            }
        }); 
        

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/customer.png"))); // NOI18N
        jPanel5.add(jLabel7);
        jLabel7.setBounds(10, 15, 40, 50);
        
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
        		.addGap(0,0,0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGap(2,2,2)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0,0,0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0,0,0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0,0,0))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));

        tblLines.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblLines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            new String [] {
                "C_OrderLine_ID" , "Select " ,  "Search Key", "Product Name", "Pack Size", "Unit Price", "Quantity", "Line Total"
            }
        ){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] types = new Class [] {
				Integer.class,Boolean.class , String.class, String.class, Double.class, BigDecimal.class, Double.class, BigDecimal.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            
            boolean[] canEdit = new boolean [] {
            		false ,true , false, false, false,false, false, false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        tblLines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	tblLines1MouseClicked(evt);
            }
        });
        
        tblLines.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tblLines.columnAtPoint(e.getPoint()) == 1)
                	setSelection();
            }
        });
        
        
        //column settings
        JTableHeader jTHeader =  tblLines.getTableHeader();
        jTHeader.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        //jTHeader.setForeground(new java.awt.Color(0,0,0));
        
        
        //tblLines.setBackground(new java.awt.Color(238,238,238));
        
        tblLines.setRowHeight(35);
        tblLines.setRowMargin(2);
        //tblLines.setSelectionBackground(new java.awt.Color(184,207,229));
        //tblLines.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblLines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLines.setSurrendersFocusOnKeystroke(true);
        tblLines.getTableHeader().setResizingAllowed(false);
        tblLines.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblLines);
        vertical = jScrollPane1.getVerticalScrollBar();
        
        if (tblLines.getColumnModel().getColumnCount() > 0) {
        	
        	tblLines.getColumnModel().getColumn(0).setMinWidth(0);
        	tblLines.getColumnModel().getColumn(0).setPreferredWidth(0);
        	tblLines.getColumnModel().getColumn(0).setMaxWidth(0);
        	
        	tblLines.getColumnModel().getColumn(1).setPreferredWidth(3);
            tblLines.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblLines.getColumnModel().getColumn(3).setPreferredWidth(350);
            tblLines.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblLines.getColumnModel().getColumn(5).setPreferredWidth(60);
            tblLines.getColumnModel().getColumn(6).setPreferredWidth(60);
            tblLines.getColumnModel().getColumn(7).setPreferredWidth(60);
        }
        tblLines.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
        		.addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        
        
        
        frame.pack();
        //frame.setLocationRelativeTo(null);
        
	 }

	@Override
	public void dispose() {
		//this.posModel.dispose((FormFrame)this.getFrame());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnNewCash)){
			this.posModel.createNewCashOrder();
			//btnOrderType.setText("Cash - Sale");
			
		}else if(e.getSource().equals(btnNewCard)){
			this.posModel.createNewCardOrder();
			//btnOrderType.setText("Card - Sale");
			
		}else if(e.getSource().equals(btnNewReturn)){
			//btnOrderType.setText("Cash - Return");
			this.keyBoardref = null;
			new KeyBoard(this , "ENTER ORDER NO TO BE RETURNED : ");
			
			if(keyBoardref != null && keyBoardref.length() > 0)
				this.getPosModel().returnOrder(keyBoardref);
			
		}else if(e.getSource().equals(btnSwipe)){
			//log out from the pos
			this.posModel.dispose(formFrame);
			
		}else if(e.getSource().equals(btnRequsition)){
			
			new Requisition(this);
			
		}else if(e.getSource().equals(btnSearch)){
			
			new OrderInfor(this ,getCtx() , getTrxName());
			
		}else if(e.getSource().equals(btnPrint)){
			
			if(this.posModel.getMorder() != null && tblLines.getRowCount() > 0)
				this.posModel.printOrder();
			
		}else if(e.getSource().equals(btnCustomer)){
			
			this.posModel.changeBPartner();//change business partner
			
		}else if(e.getSource().equals(btnIncrease)){
			
			int i = tblLines.getSelectedRow();
			
			if(i >= 0){
				int C_OrderLine_ID = (Integer) tblLines.getModel().getValueAt(i, 0);
				posModel.plusQty(C_OrderLine_ID);
				//set selected row
				tblLines.getSelectionModel().setSelectionInterval(i , i);
			}
		}else if(e.getSource().equals(btnDecrease)){
			
			int i = tblLines.getSelectedRow();
			
			if(i >= 0){
				int C_OrderLine_ID = (Integer) tblLines.getModel().getValueAt(i, 0);
				posModel.minusQty(C_OrderLine_ID);
				//set selected row
				tblLines.getSelectionModel().setSelectionInterval(i , i);
			}
			
		}else if(e.getSource().equals(btnCut)){
			
			int i = tblLines.getSelectedRow();
			
			if(i >= 0){
				
				ArrayList arr = getSelectedRows();
				
				if(arr.isEmpty()){
					int C_OrderLine_ID = (Integer) tblLines.getModel().getValueAt(tblLines.getSelectedRow(), 0);
					arr.add(C_OrderLine_ID);
				}
				posModel.removeLines(arr);
				
				//focus to last row
				if(tblLines.getRowCount() > 0)
					tblLines.getSelectionModel().setSelectionInterval(i-1 , i-1);
				
			}else if(tblLines.getRowCount() > 0){//all selected
				posModel.removeLines(getSelectedRows());
			}
			
		}else if(e.getSource().equals(btnQueryProduct)){
			//change business partner
			if(this.getPosModel().getMorder()!=null && this.getPosModel().getMorder().getC_DocTypeTarget_ID() != this.getPosModel().getPosConf().getC_DocTypeReturn_ID())
				 new ProductInfor(this , MOrder.Table_ID);
			//else
				//new POSError(this.frame , "STOP" , "Undifined operation!");	
			
		}else if(e.getSource().equals(btnArrowLast)){
			
			if(tblLines.getRowCount() > 0){
	        	tblLines.getSelectionModel().setSelectionInterval(tblLines.getRowCount() -1 , tblLines.getRowCount() -1);
	        	vertical.setValue(vertical.getMaximum());
			}
	        
		}else if(e.getSource().equals(brnArrowFirst)){
			
			if(tblLines.getRowCount() > 0){
	        	tblLines.getSelectionModel().setSelectionInterval(0 , 0);
	        	vertical.setValue(0);
			}
			
		}else if(e.getSource().equals(btnArrowUp)){
			int i = tblLines.getSelectionModel().getMaxSelectionIndex();
			if(i == -1)
				tblLines.getSelectionModel().setSelectionInterval(0, 0);
			else{
				tblLines.getSelectionModel().setSelectionInterval(i - 1, i -1);
				vertical.setValue(( (i -1) * tblLines.getRowHeight()));
			}
			
		}else if(e.getSource().equals(btnArrowDown)){
			int i = tblLines.getSelectionModel().getMaxSelectionIndex();
			int rowCount = tblLines.getRowCount();
			
			if(i == -1)
				tblLines.getSelectionModel().setSelectionInterval(0, 0);
			else{
				if(i+1 < rowCount){
					tblLines.getSelectionModel().setSelectionInterval(i + 1, i +1);
					vertical.setValue(( i * tblLines.getRowHeight()));
				}
			}
		}else if(e.getSource().equals(btnOrderType)){
			
			if(posModel.getMorder() != null && posModel.getMorder().getDocStatus().equals("DR")){
				
				new OrderType(this);
				
				int M_PriceList_ID = 0;
				//card type
				if(posModel.getMorder().getPaymentRule().equals("K")){
					if(posModel.getMorder().getC_BPartner_ID() == posModel.getPosConf().getC_BpGeneral_ID()){
						M_PriceList_ID = posModel.getPosConf().getM_PriceListCash_ID();
					}else{
						M_PriceList_ID = posModel.getPosConf().getM_PriceListCard_ID();
					}
				//cash	
				}else {
					if(posModel.getMorder().getC_BPartner_ID() == posModel.getPosConf().getC_BpGeneral_ID()){
						M_PriceList_ID = posModel.getPosConf().getM_PriceListCash_ID();
					}else{
						M_PriceList_ID = posModel.getPosConf().getM_PriceListSpecial_ID();
					}
				}
				
				posModel.getMorder().setM_PriceList_ID(M_PriceList_ID);
				posModel.getMorder().save();
				posModel.changePriceList(M_PriceList_ID);
				posModel.updateInfor();
			}
		}
	}
	
	
	
	protected void setHeaderInfor(MOrder morder){
		
		MOrder order = new MOrder(getCtx(), morder.get_ID(), morder.get_TrxName());
		
		txtDocNo.setText(order.getDocumentNo() + " : " + order.getDocStatus());
		btnCustomer.setText(order.getC_BPartner().getC_BP_Group().getName());
		txtTotal.setText(order.getGrandTotal().setScale(2, RoundingMode.CEILING)+"");
		txtItemCount.setText(order.getLines().length+"");//item count
		txtRef.setText(order.getPOReference());
		
		//return
		if(order.getC_DocTypeTarget_ID() == this.posModel.getPosConf().getC_DocTypeReturn_ID()){
			
			if(order.getM_PriceList_ID() == posModel.getPosConf().getM_PriceListCash_ID())
				btnOrderType.setText("Cash - Return");
			else if(order.getM_PriceList_ID() == posModel.getPosConf().getM_PriceListCard_ID())
				btnOrderType.setText("Card - Return");
			else if(order.getM_PriceList_ID() == posModel.getPosConf().getM_PriceListSpecial_ID())
				btnOrderType.setText("Cash - Return");
		//selling	
		}else{
			
			if(order.getPaymentRule().equals("K")){
				btnOrderType.setText("Card - Sale");
			}else{
				btnOrderType.setText("Cash - Sale");
			}
			
			/*if(order.getM_PriceList_ID() == posModel.getPosConf().getM_PriceListCash_ID())
				btnOrderType.setText("Cash - Sale");
			else if(order.getM_PriceList_ID() == posModel.getPosConf().getM_PriceListCard_ID())
				btnOrderType.setText("Card - Sale");
			else if(order.getM_PriceList_ID() == posModel.getPosConf().getM_PriceListSpecial_ID())
				btnOrderType.setText("Cash - Sale");*/
		}
		
		BigDecimal discount = new BigDecimal(0);
		for(MOrderLine line : order.getLines()){//load order table and calculate discount
			discount =discount.add(line.getQtyEntered().multiply((line.getPriceList().subtract(line.getPriceActual()))));
			discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		txtDiscount.setText(discount.setScale(2, RoundingMode.CEILING) + "");
	}
	
	protected void setDetailInfo(MOrder order){
		
		DefaultTableModel model = (DefaultTableModel) tblLines.getModel();
		model.setRowCount(0);
		
		String SQL = "SELECT OL.C_ORDERLINE_ID , PR.VALUE , "
				+ "SUBSTR(PR.NAME, 1, INSTR(PR.NAME, '|')-1) AS NAME , "
				+ "PR.UNITSPERPALLET AS PACKSIZE, "
				+ "OL.PRICEENTERED ,  "
				+ "OL.QTYENTERED ,  "
				+ "OL.LINENETAMT "
				+ "FROM C_ORDERLINE OL INNER JOIN M_PRODUCT PR ON OL.M_PRODUCT_ID = PR.M_PRODUCT_ID "
				+ "WHERE OL.C_ORDER_ID = ? "
				+ "AND OL.AD_CLIENT_ID = ? "
				+ "AND OL.AD_ORG_ID =  ? ";
		
		ResultSet rs = null; PreparedStatement st = null; 
		try{
			
			st = DB.prepareStatement(SQL, getTrxName());
			st.setInt(1, order.get_ID());
			st.setInt(2, order.getAD_Client_ID());
			st.setInt(3, order.getAD_Org_ID());
			
			rs = st.executeQuery();
			
			while(rs.next()){
				
				model.addRow(new Object[]{rs.getInt(1) , false, rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBigDecimal(5).setScale(2, RoundingMode.CEILING), rs.getDouble(6), rs.getBigDecimal(7).setScale(2, RoundingMode.CEILING)});
			}		
			
		}catch(Exception ex){ 
			ex.printStackTrace();
		}
		finally{ DB.close(rs, st);rs = null;st = null; }
	}
	
	private void setSelection(){
		
		if(tblLines.getRowCount() == 0)
			return;
		
		boolean isSelected = false;
		
		if(tblLines.getValueAt(0, 1) != null){
			
			if( (boolean) tblLines.getValueAt(0, 1))
				isSelected = true;
			else
				isSelected = false;
		}
		
		for (int row=0; row < tblLines.getRowCount(); row++) {
			//based on the first column decide all select or deselect
			if(isSelected)
				tblLines.setValueAt(false, row, 1);
			else
				tblLines.setValueAt(true, row, 1);
        }
	}
	
	private void tblLines1MouseClicked(MouseEvent evt) {
		
		//number pad visualize only for draft bill
		if(! this.getPosModel().getMorder().getDocStatus().equals("DR"))
			return;
		int column = tblLines.getSelectedColumn();
		if(column == 6)//number pad visible only for quantity column clicked
			new NumberPad(this);
    }
	
	public JTable gettblLine(){
		
		return this.tblLines;
	}
	
	public CFrame getFrame() {
		return frame;
	}
	public int getWindowNo() {
		
		return WindowNo;
	}
	
	public Properties getCtx(){
		
		return this.basePanel.getM_ctx();
	}
	
	public String getTrxName(){
		
		return this.basePanel.getTrx().getTrxName();
	}
	
	public POSModel getPosModel(){
		return this.posModel;
	}
	
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
	
	private void setRefference(){
		
		if(this.getPosModel().getMorder() != null && this.getPosModel().getMorder().getDocStatus().equals("DR")){
			
			keyBoardref = null;
			new KeyBoard(this, "Enter order reference" , txtRef.getText());
			if(keyBoardref != null){
				txtRef.setText(keyBoardref);
				this.getPosModel().setPoRefference(keyBoardref);
			}
				
		}	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList getSelectedRows(){
		
		if(tblLines.getRowCount() == 0)
			return null;
		
		ArrayList arr = new ArrayList();
		
		for(int row = 0; row < tblLines.getRowCount() ; row ++){
			
			if(tblLines.getValueAt(row, 1) != null){
				
				if( (boolean) tblLines.getValueAt(row, 1)){
					
					arr.add(tblLines.getValueAt(row, 0));
				}
			}
		}
		
		return arr;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	protected void setKeyBoardref(String ref){
		
		this.keyBoardref = ref;
	}
}
