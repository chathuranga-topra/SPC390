package org.topraworld.pos;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Properties;

@SuppressWarnings("serial")
public class OrderInfor extends JDialog implements ActionListener{

	private JButton brnArrowFirst = new JButton();
	private JButton btnArrowUp = new JButton();
	private JButton btnArrowDown = new JButton();
	private JButton btnArrowLast = new JButton();
	private JButton btnHome = new JButton();
	private JButton btnComplete = new JButton();
	private JButton btnDraft = new JButton();
	private JButton btnVoid = new JButton();
	private JButton btnInProress = new JButton();
	private JButton btnSearch = new JButton();
	private JButton btnDelete = new javax.swing.JButton();
	private JTable tblDetails = new JTable();
	private Properties ctx;
	private String trxName;
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
	private QueryOrders queryOrders;
	private POSPanel  posPanel;
	
    public OrderInfor(POSPanel posPanel , Properties ctx , String trxName) {
		 
        //super(posPanel.getFrame(), true);
        this.ctx = ctx;
        this.trxName = trxName;
        this.posPanel = posPanel;
        
        queryOrders = new QueryOrders(ctx , trxName , this.tblDetails);
       
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        
        setTitle("Existing orders");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        //setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        //brnArrowFirst.setBackground(new java.awt.Color(255, 255, 255));
        brnArrowFirst.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //brnArrowFirst.setForeground(new java.awt.Color(51, 204, 0));
        brnArrowFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/fullUp.png"))); // NOI18N
        brnArrowFirst.addActionListener(this);
        
        //btnArrowUp.setBackground(new java.awt.Color(255, 255, 255));
        btnArrowUp.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnArrowUp.setForeground(new java.awt.Color(51, 204, 0));
        btnArrowUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/up.png"))); // NOI18N
        btnArrowUp.addActionListener(this);
        
        //btnArrowDown.setBackground(new java.awt.Color(255, 255, 255));
        btnArrowDown.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnArrowDown.setForeground(new java.awt.Color(51, 204, 0));
        btnArrowDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/down.png"))); // NOI18N
        btnArrowDown.addActionListener(this);
        
        //btnArrowLast.setBackground(new java.awt.Color(255, 255, 255));
        btnArrowLast.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnArrowLast.setForeground(new java.awt.Color(51, 204, 0));
        btnArrowLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/fullDown.png"))); // NOI18N
        btnArrowLast.addActionListener(this);	
        
        //btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnHome.setForeground(new java.awt.Color(0, 0, 0));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/home.png"))); // NOI18N
        btnHome.setPreferredSize(new java.awt.Dimension(73, 73));
        btnHome.addActionListener(this);
        
        //btnComplete.setBackground(new java.awt.Color(255, 255, 255));
        btnComplete.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnComplete.setForeground(new java.awt.Color(0, 0, 0));
        btnComplete.setText("CO");
        btnComplete.setPreferredSize(new java.awt.Dimension(73, 73));
        btnComplete.addActionListener(this);

        //btnDraft.setBackground(new java.awt.Color(255, 255, 255));
        btnDraft.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnDraft.setForeground(new java.awt.Color(0, 0, 0));
        btnDraft.setText("DR");
        btnDraft.setPreferredSize(new java.awt.Dimension(73, 73));
        btnDraft.addActionListener(this);

        //btnVoid.setBackground(new java.awt.Color(255, 255, 255));
        btnVoid.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnVoid.setForeground(new java.awt.Color(0, 0, 0));
        btnVoid.setText("VO");
        btnVoid.setPreferredSize(new java.awt.Dimension(73, 73));
        btnVoid.addActionListener(this);

        //btnInProress.setBackground(new java.awt.Color(255, 255, 255));
        btnInProress.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnInProress.setForeground(new java.awt.Color(0, 0, 0));
        btnInProress.setText("IP");
        btnInProress.setPreferredSize(new java.awt.Dimension(73, 73));
        btnInProress.addActionListener(this);

        //btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        //btnSearch.setForeground(new java.awt.Color(0, 0, 0));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/expand.png"))); // NOI18N
        btnSearch.addActionListener(this);
        
        //btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/remove.png"))); // NOI18N
        btnDelete.setPreferredSize(new java.awt.Dimension(73, 73));
        btnDelete.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brnArrowFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowUp, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowDown, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowLast, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInProress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDraft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInProress, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoid, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDraft, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArrowLast, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnArrowUp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnArrowDown, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(brnArrowFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblDetails.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tblDetails.setAutoCreateRowSorter(true);
        //tblDetails.setForeground(new java.awt.Color(153, 0, 153));
        
        tblDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            new String [] {
                    "C_Order_ID", "Line No", "Document No", "Business Partner", "Document Type", "Price List", "Item Count", "Discount", "Grand Total"
                }
            ) {
                Class[] types = new Class [] {
                    Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, BigDecimal.class, BigDecimal.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
        });
        
        //tblDetails.setBackground(new java.awt.Color(238,238,238));
        
        //tblDetails.setSelectionBackground(new java.awt.Color(184,207,229));
        //tblDetails.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblDetails.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        tblDetails.setRowHeight(40);
        tblDetails.setAutoscrolls(false);
        jScrollPane1.setViewportView(tblDetails);
        if (tblDetails.getColumnModel().getColumnCount() > 0) {
            tblDetails.getColumnModel().getColumn(0).setMinWidth(0);
            tblDetails.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDetails.getColumnModel().getColumn(0).setMaxWidth(0);
            
            tblDetails.getColumnModel().getColumn(1).setMinWidth(10);
            tblDetails.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblDetails.getColumnModel().getColumn(6).setMinWidth(8);
            tblDetails.getColumnModel().getColumn(6).setPreferredWidth(8);
            tblDetails.getColumnModel().getColumn(7).setMinWidth(15);
            tblDetails.getColumnModel().getColumn(7).setPreferredWidth(15);
            tblDetails.getColumnModel().getColumn(8).setMinWidth(15);
            tblDetails.getColumnModel().getColumn(8).setPreferredWidth(15);
            
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
        //queryOrders.getDrafBill();
        this.btnDraft.doClick();
        this.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnHome)){
			this.dispose();
			
		}else if(e.getSource().equals(btnDraft)){
		
			btnDelete.setEnabled(true);
			queryOrders.getDrafBill();
			
		}else if(e.getSource().equals(btnInProress)){
			
			queryOrders.getIPBill();
			
		}else if(e.getSource().equals(btnComplete)){
			
			btnDelete.setEnabled(false);
			queryOrders.getCompleteBill();
			
		}else if(e.getSource().equals(btnVoid)){
			
			btnDelete.setEnabled(false);
			queryOrders.getVoidBill();
			
		}else if(e.getSource().equals(btnSearch)){
			
			btnDelete.setEnabled(false);
			int row = tblDetails.getSelectedRow();
			
			if(row >= 0){
				int C_Order_ID = (Integer) tblDetails.getModel().getValueAt(row, 0);
				this.posPanel.getPosModel().zoomOut(C_Order_ID);
				this.dispose();
			}
			
		}else if(e.getSource().equals(btnArrowLast)){
			
			if(tblDetails.getRowCount() > 0){
				tblDetails.getSelectionModel().setSelectionInterval(tblDetails.getRowCount() -1 , tblDetails.getRowCount() -1);
				vertical.setValue(vertical.getMaximum());
			}
		}else if(e.getSource().equals(brnArrowFirst)){
			
			if(tblDetails.getRowCount() > 0){
				tblDetails.getSelectionModel().setSelectionInterval(0 , 0);
				vertical.setValue(0);
			}
			
		}else if(e.getSource().equals(btnArrowUp)){
			int i = tblDetails.getSelectionModel().getMaxSelectionIndex();
			if(i == -1)
				tblDetails.getSelectionModel().setSelectionInterval(0, 0);
			else{
				tblDetails.getSelectionModel().setSelectionInterval(i - 1, i -1);
				//vertical.setValue(tblDetails.getSelectedRow()+1);
				vertical.setValue(( (i -1) * tblDetails.getRowHeight()));
			}
		}else if(e.getSource().equals(btnArrowDown)){
			int i = tblDetails.getSelectionModel().getMaxSelectionIndex();
			int rowCount = tblDetails.getRowCount();
			
			if(i == -1)
				tblDetails.getSelectionModel().setSelectionInterval(0, 0);
			else{
				if(i+1 < rowCount){
					tblDetails.getSelectionModel().setSelectionInterval(i + 1, i +1);
					vertical.setValue(( i * tblDetails.getRowHeight()));
				}
			}
			
		}else if(e.getSource().equals(btnDelete)){
			
			if(tblDetails.getSelectedRow() == -1) return;
			//new POSQuestion(posPanel.getFrame(), "Are you sure to void this order ?", posPanel);
			
			if(posPanel.answer){
				
				int C_Order_ID = (Integer) tblDetails.getModel().getValueAt(tblDetails.getSelectedRow(), 0);
				posPanel.getPosModel().voidOrder(C_Order_ID);
				this.queryOrders.getDrafBill();
			}
		}
	}
}
