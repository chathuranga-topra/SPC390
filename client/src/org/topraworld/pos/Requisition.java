package org.topraworld.pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.util.Trx;

public class Requisition extends JDialog implements ActionListener{

	private JButton btnArrowFirst = new JButton();
    private JButton btnArrowDown = new JButton();
    private JButton btnArrowLast = new JButton();
    private JButton btnArrowUp = new JButton();
    private JButton btnCreateLines = new JButton();
    private JButton btnCut = new JButton();
    private JButton btnHome = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel3 = new JLabel();;
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable tblLines = new JTable();
    private JTextField txtDocNo = new JTextField();
    private JTextField txtItemCount = new JTextField();
    private MMovement movement;
    private JScrollBar vertical;
	
	private POSPanel panel;
	
	 public Requisition(POSPanel panel) {
		 
        super(panel.getFrame(), true);
        this.panel = panel;
        
        this.initComponents();
	 }
	 
	 private void initComponents(){
		 
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventory Requisition");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        //btnArrowFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnArrowFirst.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnArrowFirst.setForeground(new java.awt.Color(51, 204, 0));
        btnArrowFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/fullUp.png"))); // NOI18N
        btnArrowFirst.addActionListener(this);

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

        //btnCut.setBackground(new java.awt.Color(255, 255, 255));
        btnCut.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnCut.setForeground(new java.awt.Color(51, 204, 0));
        btnCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/delete.png"))); // NOI18N
        btnCut.addActionListener(this);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Document No");

        txtDocNo.setEditable(false);
        txtDocNo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //txtDocNo.setForeground(new java.awt.Color(51, 0, 204));
        txtDocNo.setMinimumSize(new java.awt.Dimension(10, 23));
        txtDocNo.setPreferredSize(new java.awt.Dimension(10, 23));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Item Count");

        txtItemCount.setEditable(false);
        txtItemCount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //txtItemCount.setForeground(new java.awt.Color(51, 0, 204));
        txtItemCount.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtItemCount.setMinimumSize(new java.awt.Dimension(10, 23));
        txtItemCount.setPreferredSize(new java.awt.Dimension(10, 23));

        //btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        //btnHome.setForeground(new java.awt.Color(0, 0, 0));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/home.png"))); // NOI18N
        btnHome.setPreferredSize(new java.awt.Dimension(73, 73));
        btnHome.addActionListener(this);
       

        //btnCreateLines.setBackground(new java.awt.Color(255, 255, 255));
        btnCreateLines.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        //btnCreateLines.setForeground(new java.awt.Color(51, 204, 0));
        btnCreateLines.setText("CREATE LINES");
        btnCreateLines.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnArrowFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowUp, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowDown, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowLast, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCut, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDocNo, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(txtItemCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateLines, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCreateLines, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnArrowFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnArrowUp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnArrowDown, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnArrowLast, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDocNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtItemCount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblLines.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblLines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            new String [] {"" , "Line No", "Product", "Pack Size", "Pack Qty", "Movment Qty" }
        ) {
            Class[] types = new Class [] {
        		java.lang.Object.class , java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };            
            
            @SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
			boolean[] canEdit = new boolean [] {
                    false, false, false,false, false , false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vertical = jScrollPane1.getVerticalScrollBar();
        tblLines.setRowHeight(40);
        tblLines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblLines);
        if (tblLines.getColumnModel().getColumnCount() > 0) {
        	
        	tblLines.getColumnModel().getColumn(0).setMinWidth(0);
            tblLines.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblLines.getColumnModel().getColumn(0).setMaxWidth(0);
        	
            tblLines.getColumnModel().getColumn(1).setMinWidth(70);
            tblLines.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblLines.getColumnModel().getColumn(1).setMaxWidth(70);
            tblLines.getColumnModel().getColumn(3).setMinWidth(100);
            tblLines.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblLines.getColumnModel().getColumn(3).setMaxWidth(100);
            tblLines.getColumnModel().getColumn(4).setMinWidth(100);
            tblLines.getColumnModel().getColumn(4).setMaxWidth(100);
            tblLines.getColumnModel().getColumn(5).setMinWidth(100);
            tblLines.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblLines.getColumnModel().getColumn(5).setMaxWidth(100);
        }
        
        
      //column settings
        JTableHeader jTHeader =  tblLines.getTableHeader();
        jTHeader.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        //jTHeader.setForeground(new java.awt.Color(51, 0, 204));
        
        //tblLines.setBackground(new java.awt.Color(238,238,238));
        
        tblLines.setRowHeight(35);
        tblLines.setRowMargin(2);
        //tblLines.setSelectionBackground(new java.awt.Color(184,207,229));
        tblLines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLines.setSurrendersFocusOnKeystroke(true);
        tblLines.getTableHeader().setResizingAllowed(false);
        tblLines.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
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

        pack();
        setLocationRelativeTo(null);
        
       
        movement =  this.panel.getPosModel().newRequisition();
		this.setHeaderInfo(movement);
        
        this.show();
	 }
	 
	 private void setHeaderInfo(MMovement movement){
		 
		 if(movement == null || movement.get_ID() == -1)
			 return;
		 
		 txtDocNo.setText(movement.getDocumentNo() + " : " + movement.getDocStatus());
		 int count = movement.getLines(true).length;
		 txtItemCount.setText(count + "");
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == null) return;
		
		if(e.getSource().equals(btnHome)){
			
			if(tblLines.getRowCount() == 0)
				Trx.get(panel.getTrxName(), false).rollback();
			else{
				movement.set_CustomColumn("ACTIONLIST", "B");
				movement.save(panel.getTrxName());
				Trx.get(panel.getTrxName(), false).commit();
			}	
		
			this.dispose();
			
		}else if(e.getSource().equals(btnCreateLines)){
			
			new ProductInfor(panel , MMovement.Table_ID , this.movement);
			this.updateInfo();
			
		}else if(e.getSource().equals(btnArrowFirst)){
			
			if(tblLines.getRowCount() > 0){
	        	tblLines.getSelectionModel().setSelectionInterval(0 , 0);
	        	vertical.setValue(0);
			}
			
		}else if(e.getSource().equals(btnArrowLast)){
			
			if(tblLines.getRowCount() > 0){
	        	tblLines.getSelectionModel().setSelectionInterval(tblLines.getRowCount() -1 , tblLines.getRowCount() -1);
	        	vertical.setValue(vertical.getMaximum());
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
			
		}else if(e.getSource().equals(btnArrowUp)){
			
			int i = tblLines.getSelectionModel().getMaxSelectionIndex();
			if(i == -1)
				tblLines.getSelectionModel().setSelectionInterval(0, 0);
			else{
				tblLines.getSelectionModel().setSelectionInterval(i - 1, i -1);
				vertical.setValue(( (i -1) * tblLines.getRowHeight()));
			}
			
		}else if(e.getSource().equals(btnCut)){
			
			if(tblLines.getSelectedRow() != -1){
			
				int id = (Integer) tblLines.getModel().getValueAt(tblLines.getSelectedRow(), 0);
				this.panel.getPosModel().removeRequisitionLine(id);
				updateInfo();
			}
		}
	}
	
	private void updateInfo(){
		
		MMovementLine[] moveLines = this.movement.getLines(true);
		DefaultTableModel model = (DefaultTableModel) tblLines.getModel();
		model.setRowCount(0);
		
		int i = 0;
		
		for(MMovementLine line : moveLines){
			i++;
			model.addRow(new Object[]{ line.get_ID() , new BigDecimal(i) , line.getM_Product().getName() , line.getM_Product().getUnitsPerPallet() , line.get_Value("packQty") , line.getMovementQty()});
		}
		txtItemCount.setText(i+"");
	}
}
