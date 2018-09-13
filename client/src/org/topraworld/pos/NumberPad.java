package org.topraworld.pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NumberPad extends JDialog implements ActionListener{
	
	private JButton btn0 = new JButton();
    private JButton btn1 = new JButton();
    private JButton btn10 = new JButton();
    private JButton btn14 = new JButton();
    private JButton btn15 = new JButton();
    private JButton btn2 = new JButton();
    private JButton btn21 = new JButton();
    private JButton btn28 = new JButton();
    private JButton btn3 = new JButton();
    private JButton btn30 = new JButton();
    private JButton btn4 = new JButton();
    private JButton btn5 = new JButton();
    private JButton btn6 = new JButton();
    private JButton btn60 = new JButton();
    private JButton btn7 = new JButton();
    private JButton btn8 = new JButton();
    private JButton btn9 = new JButton();
    private JButton btn90 = new JButton();
    private JButton btnBackSpaceNo = new JButton();
    private JButton btnOk = new JButton();
    private JButton btnResetNo = new JButton();
    private JPanel jPanel4 = new JPanel();
    private JTextField txtValue = new JTextField();
	
	private POSPanel posPanel;
	
	public NumberPad(POSPanel posPanel) {
		
        //super(posPanel.getFrame(), true);
        
        this.posPanel = posPanel;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        //btn8.setBackground(new java.awt.Color(255, 255, 255));
        btn8.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn8.setForeground(new java.awt.Color(0, 0, 0));
        btn8.setText("8");
        btn8.addActionListener(this);
        btn8.setPreferredSize(new java.awt.Dimension(73, 73));

        ///btn7.setBackground(new java.awt.Color(255, 255, 255));
        btn7.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn7.setForeground(new java.awt.Color(0, 0, 0));
        btn7.setText("7");
        btn7.addActionListener(this);
        btn7.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn90.setBackground(new java.awt.Color(255, 255, 255));
        btn90.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn90.setForeground(new java.awt.Color(0, 0, 0));
        btn90.addActionListener(this);
        btn90.setText("90");
        btn90.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn4.setBackground(new java.awt.Color(255, 255, 255));
        btn4.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn4.setForeground(new java.awt.Color(0, 0, 0));
        btn4.setText("4");
        btn4.addActionListener(this);
        btn4.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn5.setBackground(new java.awt.Color(255, 255, 255));
        btn5.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn5.setForeground(new java.awt.Color(0, 0, 0));
        btn5.setText("5");
        btn5.addActionListener(this);
        btn5.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn6.setBackground(new java.awt.Color(255, 255, 255));
        btn6.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn6.setForeground(new java.awt.Color(0, 0, 0));
        btn6.setText("6");
        btn6.addActionListener(this);
        btn6.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn60.setBackground(new java.awt.Color(255, 255, 255));
        btn60.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn60.setForeground(new java.awt.Color(0, 0, 0));
        btn60.addActionListener(this);
        btn60.setText("60");
        btn60.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn1.setBackground(new java.awt.Color(255, 255, 255));
        btn1.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn1.setForeground(new java.awt.Color(0, 0, 0));
        btn1.setText("1");
        btn1.addActionListener(this);
        btn1.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn0.setBackground(new java.awt.Color(255, 255, 255));
        btn0.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn0.setForeground(new java.awt.Color(0, 0, 0));
        btn0.setText("0");
        btn0.addActionListener(this);
        btn0.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn2.setBackground(new java.awt.Color(255, 255, 255));
        btn2.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn2.setForeground(new java.awt.Color(0, 0, 0));
        btn2.setText("2");
        btn2.addActionListener(this);
        btn2.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn3.setBackground(new java.awt.Color(255, 255, 255));
        btn3.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn3.setForeground(new java.awt.Color(0, 0, 0));
        btn3.addActionListener(this);
        btn3.setText("3");
        btn3.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn15.setBackground(new java.awt.Color(255, 255, 255));
        btn15.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn15.setForeground(new java.awt.Color(0, 0, 0));
        btn15.addActionListener(this);
        btn15.setText("15");
        btn15.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn30.setBackground(new java.awt.Color(255, 255, 255));
        btn30.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn30.setForeground(new java.awt.Color(0, 0, 0));
        btn30.addActionListener(this);
        btn30.setText("30");
        btn30.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn21.setBackground(new java.awt.Color(255, 255, 255));
        btn21.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn21.setForeground(new java.awt.Color(0, 0, 0));
        btn21.setText("21");
        btn21.addActionListener(this);
        btn21.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn14.setBackground(new java.awt.Color(255, 255, 255));
        btn14.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn14.setForeground(new java.awt.Color(0, 0, 0));
        btn14.addActionListener(this);
        btn14.setText("14");
        btn14.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn10.setBackground(new java.awt.Color(255, 255, 255));
        btn10.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn10.setForeground(new java.awt.Color(0, 0, 0));
        btn10.setText("10");
        btn10.addActionListener(this);
        btn10.setPreferredSize(new java.awt.Dimension(73, 73));

        //btn28.setBackground(new java.awt.Color(255, 255, 255));
        btn28.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn28.setForeground(new java.awt.Color(0, 0, 0));
        btn28.addActionListener(this);
        btn28.setText("28");
        btn28.setPreferredSize(new java.awt.Dimension(73, 73));

        //btnOk.setBackground(new java.awt.Color(255, 255, 255));
        btnOk.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnOk.setForeground(new java.awt.Color(0, 0, 0));
        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/ok.png"))); // NOI18N
        btnOk.setPreferredSize(new java.awt.Dimension(73, 73));
        btnOk.addActionListener(this);

        //btn9.setBackground(new java.awt.Color(255, 255, 255));
        btn9.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btn9.setForeground(new java.awt.Color(0, 0, 0));
        btn9.setText("9");
        btn9.addActionListener(this);
        btn9.setPreferredSize(new java.awt.Dimension(73, 73));

        //btnBackSpaceNo.setBackground(new java.awt.Color(255, 255, 255));
        btnBackSpaceNo.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnBackSpaceNo.setForeground(new java.awt.Color(0, 0, 0));
        btnBackSpaceNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/backspace.png"))); // NOI18N
        btnBackSpaceNo.setPreferredSize(new java.awt.Dimension(73, 73));
        btnBackSpaceNo.addActionListener(this);

        //btnResetNo.setBackground(new java.awt.Color(255, 255, 255));
        btnResetNo.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        //btnResetNo.setForeground(new java.awt.Color(0, 0, 0));
        btnResetNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/reset.png"))); // NOI18N
        btnResetNo.setPreferredSize(new java.awt.Dimension(73, 73));  
        btnResetNo.addActionListener(this);
        
        txtValue.setEditable(false);
        txtValue.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        txtValue.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnBackSpaceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnResetNo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn0, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBackSpaceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResetNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn90, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn60, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
        
        this.show(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnOk)){
			
			if(txtValue.getText() == null || txtValue.getText().length() == 0){
				
				this.dispose();
				return;
			}	
			
		 	int row = posPanel.gettblLine().getSelectedRow();
		 	int C_OrderLine_ID = (Integer) posPanel.gettblLine().getModel().getValueAt(row, 0);
		 	int oderedQty = Integer.parseInt(txtValue.getText());
			
			if(this.posPanel.getPosModel().changeQty(C_OrderLine_ID, oderedQty)){
				this.dispose();
			}
			
		}else if(e.getSource().equals(btnResetNo)){
			txtValue.setText("");
		
		}else if(e.getSource().equals(btnBackSpaceNo)){
			if(txtValue.getText().length() > 0)
				txtValue.setText(txtValue.getText().substring(0, txtValue.getText().length()-1));
			
		}else{
			txtValue.setText(txtValue.getText().concat(e.getActionCommand()));
		}
		
	} 

}
