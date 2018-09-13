package org.topraworld.pos;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class OrderType extends JDialog implements ActionListener{

	private JButton btnCard = new javax.swing.JButton();
	private JButton btnCash = new javax.swing.JButton();
	private POSPanel posPanel;
	
	@SuppressWarnings("deprecation")
	public OrderType( POSPanel posPanel ) {
		
        super(posPanel.getFrame(), true);
        this.posPanel = posPanel;
        initComponents();
        
        if(posPanel.getPosModel().getMorder().getPaymentRule().equals("K")){
        	btnCard.setEnabled(false);
        }else{
        	btnCash.setEnabled(false);
        }
        
        this.show();
    }
	
	 private void initComponents() {		 

		 btnCash = new javax.swing.JButton();
		 btnCard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CHANGE ORDER TYPE");

        //btnCash.setBackground(new java.awt.Color(255, 255, 255));
        btnCash.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnCash.setForeground(new java.awt.Color(102, 102, 255));
        btnCash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/cash.png"))); // NOI18N
        btnCash.setActionCommand("btnCash");
        btnCash.setHideActionText(true);
        btnCash.addActionListener(this);

        //btnCard.setBackground(new java.awt.Color(255, 255, 255));
        btnCard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnCard.setForeground(new java.awt.Color(102, 102, 255));
        btnCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/card.png"))); // NOI18N
        btnCard.setActionCommand("btnCash");
        btnCard.setHideActionText(true);
        btnCard.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCash, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCard, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCash, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
            .addComponent(btnCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        this.setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>    

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnCash)){
			
			this.posPanel.getPosModel().getMorder().setPaymentRule("B");
			
		}else if(e.getSource().equals(btnCard)){
			
			this.posPanel.getPosModel().getMorder().setPaymentRule("K");
		}
		
		this.dispose();
	}
}

