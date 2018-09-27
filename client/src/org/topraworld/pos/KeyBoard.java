package org.topraworld.pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KeyBoard extends JDialog implements ActionListener{
	
	private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JTextField txtValue = new JTextField();
	
	private JButton btn0 = new JButton();
    private JButton btn1 = new JButton();
    private JButton btn2 = new JButton();
    private JButton btn3 = new JButton();
    private JButton btn4 = new JButton();
    private JButton btn5 = new JButton();
    private JButton btn6 = new JButton();
    private JButton btn7 = new JButton();
    private JButton btn8 = new JButton();
    private JButton btn9 = new JButton();
    private JButton btnA = new JButton();
    private JButton btnAnd = new JButton();
    private JButton btnAt = new JButton();
    private JButton btnB = new JButton();
    private JButton btnBackSpace = new JButton();
    private JButton btnC = new JButton();
    private JButton btnClear = new JButton();
    private JButton btnD = new JButton();
    private JButton btnDot = new JButton();
    private JButton btnE = new JButton();
    private JButton btnF = new JButton();
    private JButton btnG = new JButton();
    private JButton btnH = new JButton();
    private JButton btnHash = new JButton();
    private JButton btnI = new JButton();
    private JButton btnJ = new JButton();
    private JButton btnK = new JButton();
    private JButton btnL = new JButton();
    private JButton btnM = new JButton();
    private JButton btnMinus = new JButton();
    private JButton btnN = new JButton();
    private JButton btnO = new JButton();
    private JButton btnOk = new JButton();
    private JButton btnP = new JButton();
    private JButton btnQ = new JButton();
    private JButton btnR = new JButton();
    private JButton btnS = new JButton();
    private JButton btnSpace = new JButton();
    private JButton btnT = new JButton();
    private JButton btnU = new JButton();
    private JButton btnV = new JButton();
    private JButton btnW = new JButton();
    private JButton btnX = new JButton();
    private JButton btnY = new JButton();
    private JButton btnZ = new JButton();
    private JLabel lblTitle = new JLabel();
    private POSPanel posPanel;
    
	@SuppressWarnings("deprecation")
	public KeyBoard(POSPanel posPanel , String title){
		
		super(posPanel.getFrame(), true);
		this.posPanel = posPanel;
		
		this.intComponents();
		
		lblTitle.setText(title);
        this.show(true);
	}
	
	@SuppressWarnings("deprecation")
	public KeyBoard(POSPanel posPanel , String title , String value){
		
		super(posPanel.getFrame(), true);
		this.posPanel = posPanel;
		
		this.intComponents();
		
		lblTitle.setText(title);
		txtValue.setText(value);
		
        this.show(true);
	}
	
	
	private void intComponents(){
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

	    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	    
        btnQ.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnQ.setForeground(new java.awt.Color(0, 0, 0));
        btnQ.setText("Q");
        btnQ.setMinimumSize(new java.awt.Dimension(73, 70));
        btnQ.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnQ.setBackground(new java.awt.Color(255, 255, 255));
        btnQ.addActionListener(this);

        btnW.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnW.setForeground(new java.awt.Color(0, 0, 0));
        btnW.setText("W");
        btnW.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnW.setBackground(new java.awt.Color(255, 255, 255));
        btnW.addActionListener(this);

        btnE.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnE.setForeground(new java.awt.Color(0, 0, 0));
        btnE.setText("E");
        btnE.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnE.setBackground(new java.awt.Color(255, 255, 255));
        btnE.addActionListener(this);

        btnR.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnR.setForeground(new java.awt.Color(0, 0, 0));
        btnR.setText("R");
        btnR.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnR.setBackground(new java.awt.Color(255, 255, 255));
        btnR.addActionListener(this);

        btnT.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnT.setForeground(new java.awt.Color(0, 0, 0));
        btnT.setText("T");
        btnT.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnT.setBackground(new java.awt.Color(255, 255, 255));
        btnT.addActionListener(this);

        btnY.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnY.setForeground(new java.awt.Color(0, 0, 0));
        btnY.setText("Y");
        btnY.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnY.setBackground(new java.awt.Color(255, 255, 255));
        btnY.addActionListener(this);

        btnU.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        btnU.setForeground(new java.awt.Color(0, 0, 0));
        btnU.setText("U");
        btnU.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnU.setBackground(new java.awt.Color(255, 255, 255));
        btnU.addActionListener(this);

        btnI.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnI.setForeground(new java.awt.Color(0, 0, 0));
        btnI.setText("I");
        btnI.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnI.setBackground(new java.awt.Color(255, 255, 255));
        btnI.addActionListener(this);

        btnO.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnO.setForeground(new java.awt.Color(0, 0, 0));
        btnO.setText("O");
        btnO.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnO.setBackground(new java.awt.Color(255, 255, 255));
        btnO.addActionListener(this);

        btnP.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnP.setForeground(new java.awt.Color(0, 0, 0));
        btnP.setText("P");
        btnP.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnP.setBackground(new java.awt.Color(255, 255, 255));
        btnP.addActionListener(this);

        btnA.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnA.setForeground(new java.awt.Color(0, 0, 0));
        btnA.setText("A");
        btnA.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnA.setBackground(new java.awt.Color(255, 255, 255));
        btnA.addActionListener(this);

        btnS.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnS.setForeground(new java.awt.Color(0, 0, 0));
        btnS.setText("S");
        btnS.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnS.setBackground(new java.awt.Color(255, 255, 255));
        btnS.addActionListener(this);

        btnD.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnD.setForeground(new java.awt.Color(0, 0, 0));
        btnD.setText("D");
        btnD.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnD.setBackground(new java.awt.Color(255, 255, 255));
        btnD.addActionListener(this);

        btnF.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnF.setForeground(new java.awt.Color(0, 0, 0));
        btnF.setText("F");
        btnF.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnF.setBackground(new java.awt.Color(255, 255, 255));
        btnF.addActionListener(this);

        btnG.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnG.setForeground(new java.awt.Color(0, 0, 0));
        btnG.setText("G");
        btnG.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnG.setBackground(new java.awt.Color(255, 255, 255));
        btnG.addActionListener(this);

        btnH.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        ///btnH.setForeground(new java.awt.Color(0, 0, 0));
        btnH.setText("H");
        btnH.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnH.setBackground(new java.awt.Color(255, 255, 255));
        btnH.addActionListener(this);

        btnJ.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnJ.setForeground(new java.awt.Color(0, 0, 0));
        btnJ.setText("J");
        btnJ.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnJ.setBackground(new java.awt.Color(255, 255, 255));
        btnJ.addActionListener(this);

        btnK.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnK.setForeground(new java.awt.Color(0, 0, 0));
        btnK.setText("K");
        btnK.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnK.setBackground(new java.awt.Color(255, 255, 255));
        btnK.addActionListener(this);

        btnL.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnL.setForeground(new java.awt.Color(0, 0, 0));
        btnL.setText("L");
        btnL.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnL.setBackground(new java.awt.Color(255, 255, 255));
        btnL.addActionListener(this);

        btnAt.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnAt.setForeground(new java.awt.Color(0, 0, 0));
        btnAt.setText("@");
        btnAt.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnAt.setBackground(new java.awt.Color(255, 255, 255));
        btnAt.addActionListener(this);

        btnZ.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnZ.setForeground(new java.awt.Color(0, 0, 0));
        btnZ.setText("Z");
        btnZ.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnZ.setBackground(new java.awt.Color(255, 255, 255));
        btnZ.addActionListener(this);

        btnX.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnX.setForeground(new java.awt.Color(0, 0, 0));
        btnX.setText("X");
        btnX.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnX.setBackground(new java.awt.Color(255, 255, 255));
        btnX.addActionListener(this);

        btnC.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnC.setForeground(new java.awt.Color(0, 0, 0));
        btnC.setText("C");
        btnC.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnC.setBackground(new java.awt.Color(255, 255, 255));
        btnC.addActionListener(this);

        btnV.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnV.setForeground(new java.awt.Color(0, 0, 0));
        btnV.setText("V");
        btnV.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnV.setBackground(new java.awt.Color(255, 255, 255));
        btnV.addActionListener(this);

        btnB.setFont(new java.awt.Font("Times New Roman", 1, 25));// NOI18N
        //btnB.setForeground(new java.awt.Color(0, 0, 0));
        btnB.setText("B");
        btnB.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnB.setBackground(new java.awt.Color(255, 255, 255));
        btnB.addActionListener(this);

        btnN.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnN.setForeground(new java.awt.Color(0, 0, 0));
        btnN.setText("N");
        btnN.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnN.setBackground(new java.awt.Color(255, 255, 255));
        btnN.addActionListener(this);

        btnM.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnM.setForeground(new java.awt.Color(0, 0, 0));
        btnM.setText("M");
        btnM.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnM.setBackground(new java.awt.Color(255, 255, 255));
        btnM.addActionListener(this);

        btnAnd.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnAnd.setForeground(new java.awt.Color(0, 0, 0));
        btnAnd.setText("&");
        btnAnd.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnAnd.setBackground(new java.awt.Color(255, 255, 255));
        btnAnd.addActionListener(this);

        btnHash.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnHash.setForeground(new java.awt.Color(0, 0, 0));
        btnHash.setText("#");
        btnHash.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnHash.setBackground(new java.awt.Color(255, 255, 255));
        btnHash.addActionListener(this);

        //btnSpace.setForeground(new java.awt.Color(0, 0, 0));
        btnSpace.setText("SPACE");
        btnSpace.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnSpace.setBackground(new java.awt.Color(255, 255, 255));
        btnSpace.setActionCommand(" ");
        btnSpace.addActionListener(this);

        txtValue.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        //txtValue.setForeground(new java.awt.Color(255, 0, 0));

        btnBackSpace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/backspace.png"))); // NOI18N
        btnBackSpace.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnBackSpace.setBackground(new java.awt.Color(255, 255, 255));
        btnBackSpace.addActionListener(this);

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/reset.png"))); // NOI18N
        btnClear.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnClear.setBackground(new java.awt.Color(255, 255, 255));
        btnClear.addActionListener(this);
       
        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/ok.png"))); // NOI18N
        //btnOk.setBackground(new java.awt.Color(255, 255, 255));
        btnOk.addActionListener(this);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnBackSpace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBackSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn7.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn7.setForeground(new java.awt.Color(0, 0, 0));
        btn7.setText("7");
        btn7.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn7.setBackground(new java.awt.Color(255, 255, 255));
        btn7.addActionListener(this);

        btn8.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn8.setForeground(new java.awt.Color(0, 0, 0));
        btn8.setText("8");
        btn8.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn8.setBackground(new java.awt.Color(255, 255, 255));
        btn8.addActionListener(this);

        btn9.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn9.setForeground(new java.awt.Color(0, 0, 0));
        btn9.setText("9");
        btn9.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn9.setBackground(new java.awt.Color(255, 255, 255));
        btn9.addActionListener(this);

        btn5.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn5.setForeground(new java.awt.Color(0, 0, 0));
        btn5.setText("5");
        btn5.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn5.setBackground(new java.awt.Color(255, 255, 255));
        btn5.addActionListener(this);

        btn4.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn4.setForeground(new java.awt.Color(0, 0, 0));
        btn4.setText("4");
        btn4.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn4.setBackground(new java.awt.Color(255, 255, 255));
        btn4.addActionListener(this);

        btn6.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn6.setForeground(new java.awt.Color(0, 0, 0));
        btn6.setText("6");
        btn6.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn6.setBackground(new java.awt.Color(255, 255, 255));
        btn6.addActionListener(this);

        btn2.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn2.setForeground(new java.awt.Color(0, 0, 0));
        btn2.setText("2");
        btn2.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn2.setBackground(new java.awt.Color(255, 255, 255));
        btn2.addActionListener(this);

        btn1.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn1.setForeground(new java.awt.Color(0, 0, 0));
        btn1.setText("1");
        btn1.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn1.setBackground(new java.awt.Color(255, 255, 255));
        btn1.addActionListener(this);

        btn3.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn3.setForeground(new java.awt.Color(0, 0, 0));
        btn3.setText("3");
        btn3.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn3.setBackground(new java.awt.Color(255, 255, 255));
        btn3.addActionListener(this);

        btnDot.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btnDot.setForeground(new java.awt.Color(0, 0, 0));
        btnDot.setText(".");
        btnDot.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnDot.setBackground(new java.awt.Color(255, 255, 255));
        btnDot.addActionListener(this);

        btn0.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btn0.setForeground(new java.awt.Color(0, 0, 0));
        btn0.setText("0");
        btn0.setPreferredSize(new java.awt.Dimension(73, 73));
        //btn0.setBackground(new java.awt.Color(255, 255, 255));
        btn0.addActionListener(this);

        btnMinus.setFont(new java.awt.Font("Times New Roman", 1, 25));; // NOI18N
        //btnMinus.setForeground(new java.awt.Color(0, 0, 0));
        btnMinus.setText("-");
        btnMinus.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnMinus.setBackground(new java.awt.Color(255, 255, 255));
        btnMinus.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnAt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnAnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnHash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSpace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQ, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnW, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnR, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnU, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnI, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnO, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnG, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJ, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnK, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnL, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnN, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnM, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHash, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblTitle.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnOk)){
			
			if(txtValue.getText() == null || txtValue.getText().length() == 0){
				this.dispose(); return;
			}	
			
			//if(this.posPanel.getPosModel().returnOrder(txtValue.getText())){
			this.posPanel.setKeyBoardref(txtValue.getText());
			this.dispose();
			//}
			
		}else if(e.getSource().equals(btnClear)){
			txtValue.setText("");
		
		}else if(e.getSource().equals(btnBackSpace)){
			if(txtValue.getText().length() > 0)
				txtValue.setText(txtValue.getText().substring(0, txtValue.getText().length()-1));
			
		}else{
			txtValue.setText(txtValue.getText().concat(e.getActionCommand()));
		}
	}
}
