package org.topraworld.pos;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.compiere.swing.CFrame;

public class POSError extends JDialog{
		
	JButton btn;
	String header;
	String msg;
	
	public POSError(CFrame parent ,String header , String body) {
        super(parent, true);
        msg = "<html><font size=\"5\" color=\"red\">"+ header.toUpperCase() +"</font>\n" +
                "<font size=\"4\" color=\"blue\"><br> "+ body +"</font></html>";
        initComponents();
        
        this.show();
    }
	
	private void initComponents() {		
		
        btn = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        btn.setBackground(new java.awt.Color(204, 255, 204));
        btn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn.setForeground(new java.awt.Color(0, 0, 204));
        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/abort.png"))); // NOI18N
        btn.setText(msg);
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            	POSError.this.dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }
}
