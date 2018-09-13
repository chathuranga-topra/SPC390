package org.topraworld.pos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import  javax.swing.JDialog;
import  javax.swing.JButton;
import  javax.swing.JLabel;
import  javax.swing.JPanel;
import  javax.swing.JScrollPane;
import  javax.swing.JTabbedPane;
import  javax.swing.JTable;
import  javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.compiere.model.MMovement;
import org.compiere.model.MOrder;

public class ProductInfor extends JDialog implements ActionListener{
    
	private static final long serialVersionUID = 1L;
	
	private JButton a = new JButton(); 
	private JButton b = new JButton();
    private JButton c = new JButton();
    private JButton d = new JButton();
    private JButton e = new JButton();
    private JButton f = new JButton();
    private JButton g = new JButton();
    private JButton h = new JButton();
    private JButton i = new JButton();
    private JButton j = new JButton();
    private JButton k = new JButton();
    private JButton l = new JButton();
    private JButton m = new JButton();
    private JButton n = new JButton();
    private JButton o = new JButton();
    private JButton p = new JButton();
    private JButton q = new JButton();
    private JButton r = new JButton();
    private JButton s = new JButton();
    private JButton t = new JButton();
    private JButton u = new JButton();
    private JButton v = new JButton();
    private JButton w = new JButton();
    private JButton x = new JButton();
    private JButton y = new JButton();
    private JButton z = new JButton();
    
    private JButton btn0= new JButton();
    private JButton btn1= new JButton();
    private JButton btn2 = new JButton();
    private JButton btn3 = new JButton();
    private JButton btn4 = new JButton();
    private JButton btn5 = new JButton();
    private JButton btn6 = new JButton();
    private JButton btn7 = new JButton();
    private JButton btn8 = new JButton();
    private JButton btn9 = new JButton();
    private JButton btn10 = new JButton();
    private JButton btn14 = new JButton();
    private JButton btn15 = new JButton();
    private JButton btn21 = new JButton();
    private JButton btn28 = new JButton();
    private JButton btn30 = new JButton();
    private JButton btn60 = new JButton();
    private JButton btn90 = new JButton();
    
    private JButton btnBachhSpaceLetter = new JButton();
    private JButton btnBackSpaceNo = new JButton();
    private JButton btnDot = new JButton();
    private JButton btnExchange = new JButton();
    private JButton btnHome = new JButton();
    private JButton btnLetterMins = new JButton();
    private JButton btnMinus = new JButton();
    private JButton btnOk = new JButton();
    private JButton btnResetLetter = new JButton();
    private JButton btnResetNo = new JButton();
    private JButton btnSpace = new JButton();
    
    private JLabel jLabel1 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JRadioButton rbName = new JRadioButton();
    private JRadioButton rbValue = new JRadioButton();
    
    private JTable tbInfo = new JTable();
    private JTextField txtQty = new JTextField();
    private JTextField txtSearch = new JTextField();
    private QueryProduct qp = new QueryProduct();
    private POSPanel posPanel;
    private int TableID;
    private MMovement movement;
	    
    @SuppressWarnings("serial")
	public ProductInfor(POSPanel parent , int  TableID) {
        super(parent.getFrame(), true);
        
        this.posPanel = parent;
        this.TableID = TableID;
        
        this.initComponents();
        
        this.show();
    }
    
    public ProductInfor(POSPanel parent , int  TableID , MMovement movement) {
        super(parent.getFrame(), true);
        
        this.posPanel = parent;
        this.TableID = TableID;
        this.movement = movement;
        
        this.initComponents();
        
        this.show();
    }
    
    private void initComponents(){
    	
    	jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbInfo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
       
        btnSpace = new javax.swing.JButton();
        btnBachhSpaceLetter = new javax.swing.JButton();
        btnResetLetter = new javax.swing.JButton();
        btnLetterMins = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn9 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn90 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn60 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btnBackSpaceNo = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnResetNo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnExchange = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rbName = new javax.swing.JRadioButton();
        rbValue = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbInfo.setAutoCreateRowSorter(true);
        tbInfo.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        //tbInfo.setForeground(new java.awt.Color(0, 0, 0));
        tbInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            new String [] { "M_Product_ID", "Code", "Name","Availabe Qty", "Generic", "Pack Size",  "Price" }
        ){
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class,java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };            
			@SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
			boolean[] canEdit = new boolean [] {
                    false, false, false,false, false, false , false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            
        });
        
        //tbInfo.setBackground(new java.awt.Color(238,238,238));
        
        tbInfo.getColumnModel().getColumn(3).setCellRenderer(new CustomRenderer());
        tbInfo.setRowHeight(50);
        //tbInfo.setSelectionBackground(new java.awt.Color(184,207,229));
        //tbInfo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbInfo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbInfo);
        if (tbInfo.getColumnModel().getColumnCount() > 0) {
            tbInfo.getColumnModel().getColumn(0).setMinWidth(0);
            tbInfo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbInfo.getColumnModel().getColumn(0).setMaxWidth(0);
            tbInfo.getColumnModel().getColumn(1).setPreferredWidth(10);
            tbInfo.getColumnModel().getColumn(2).setPreferredWidth(300);
            tbInfo.getColumnModel().getColumn(3).setPreferredWidth(20);
            tbInfo.getColumnModel().getColumn(4).setPreferredWidth(10);
            tbInfo.getColumnModel().getColumn(5).setPreferredWidth(10);
            tbInfo.getColumnModel().getColumn(6).setPreferredWidth(10);
        }
        
        tbInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInfoMouseClicked(evt);
            }
        });
        
        JTableHeader jTHeader =  tbInfo.getTableHeader();
        jTHeader.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );

        jPanel2.setMinimumSize(new java.awt.Dimension(450, 500));
        
        /*jScrollPane1.getHorizontalScrollBar().setSize(10, 0);
        jScrollPane1.setForeground(new java.awt.Color(255,204,255));*/

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMinimumSize(new java.awt.Dimension(450, 500));

        //w.setBackground(new java.awt.Color(255, 255 , 255));
        w.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //w.setForeground(new java.awt.Color(0, 0, 0));
        w.setText("W");
        w.setPreferredSize(new java.awt.Dimension(73, 73));
        w.addActionListener(this);

        //q.setBackground(new java.awt.Color(255, 255 , 255));
        q.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //q.setForeground(new java.awt.Color(0, 0, 0));
        q.setText("Q");
        q.setPreferredSize(new java.awt.Dimension(73, 73));
        q.addActionListener(this);

        //e.setBackground(new java.awt.Color(255, 255 , 255));
        e.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //e.setForeground(new java.awt.Color(0, 0, 0));
        e.setText("E");
        e.setPreferredSize(new java.awt.Dimension(73, 73));
        e.addActionListener(this);

        //t.setBackground(new java.awt.Color(255, 255 , 255));
        t.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //t.setForeground(new java.awt.Color(0, 0, 0));
        t.setText("T");
        t.setPreferredSize(new java.awt.Dimension(73, 73));
        t.addActionListener(this);

        //h.setBackground(new java.awt.Color(255, 255 , 255));
        h.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //h.setForeground(new java.awt.Color(0, 0, 0));
        h.setText("H");
        h.setPreferredSize(new java.awt.Dimension(73, 73));
        h.addActionListener(this);

        //r.setBackground(new java.awt.Color(255, 255 , 255));
        r.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //r.setForeground(new java.awt.Color(0, 0, 0));
        r.setText("R");
        r.setPreferredSize(new java.awt.Dimension(73, 73));
        r.addActionListener(this);

        //a.setBackground(new java.awt.Color(255, 255 , 255));
        a.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //a.setForeground(new java.awt.Color(0, 0, 0));
        a.setText("A");
        a.setPreferredSize(new java.awt.Dimension(73, 73));
        a.addActionListener(this);

        //x.setBackground(new java.awt.Color(255, 255 , 255));
        x.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //x.setForeground(new java.awt.Color(0, 0, 0));
        x.setText("X");
        x.setPreferredSize(new java.awt.Dimension(73, 73));
        x.addActionListener(this);

        //m.setBackground(new java.awt.Color(255, 255 , 255));
        m.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //m.setForeground(new java.awt.Color(0, 0, 0));
        m.setText("M");
        m.setPreferredSize(new java.awt.Dimension(73, 73));
        m.addActionListener(this);

        //y.setBackground(new java.awt.Color(255, 255 , 255));
        y.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //y.setForeground(new java.awt.Color(0, 0, 0));
        y.setText("Y");
        y.setPreferredSize(new java.awt.Dimension(73, 73));
        y.addActionListener(this);
        

        //u.setBackground(new java.awt.Color(255, 255 , 255));
        u.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //u.setForeground(new java.awt.Color(0, 0, 0));
        u.setText("U");
        u.setPreferredSize(new java.awt.Dimension(73, 73));
        u.addActionListener(this);

        //i.setBackground(new java.awt.Color(255, 255 , 255));
        i.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //i.setForeground(new java.awt.Color(0, 0, 0));
        i.setText("I");
        i.setPreferredSize(new java.awt.Dimension(73, 73));
        i.addActionListener(this);

        //p.setBackground(new java.awt.Color(255, 255 , 255));
        p.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //p.setForeground(new java.awt.Color(0, 0, 0));
        p.setText("P");
        p.setPreferredSize(new java.awt.Dimension(73, 73));
        p.addActionListener(this);

        //o.setBackground(new java.awt.Color(255, 255 , 255));
        o.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //o.setForeground(new java.awt.Color(0, 0, 0));
        o.setText("O");
        o.setPreferredSize(new java.awt.Dimension(73, 73));
        o.addActionListener(this);

        //s.setBackground(new java.awt.Color(255, 255 , 255));
        s.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //s.setForeground(new java.awt.Color(0, 0, 0));
        s.setText("S");
        s.setPreferredSize(new java.awt.Dimension(73, 73));
        s.addActionListener(this);

        //d.setBackground(new java.awt.Color(255, 255 , 255));
        d.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //d.setForeground(new java.awt.Color(0, 0, 0));
        d.setText("D");
        d.setPreferredSize(new java.awt.Dimension(73, 73));
        d.addActionListener(this);

        //f.setBackground(new java.awt.Color(255, 255 , 255));
        f.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //f.setForeground(new java.awt.Color(0, 0, 0));
        f.setText("F");
        f.setPreferredSize(new java.awt.Dimension(73, 73));
        f.addActionListener(this);

        //g.setBackground(new java.awt.Color(255, 255 , 255));
        g.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //g.setForeground(new java.awt.Color(0, 0, 0));
        g.setText("G");
        g.setPreferredSize(new java.awt.Dimension(73, 73));
        g.addActionListener(this);

        //l.setBackground(new java.awt.Color(255, 255 , 255));
        l.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //l.setForeground(new java.awt.Color(0, 0, 0));
        l.setText("L");
        l.setPreferredSize(new java.awt.Dimension(73, 73));
        l.addActionListener(this);

        //k.setBackground(new java.awt.Color(255, 255 , 255));
        k.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //k.setForeground(new java.awt.Color(0, 0, 0));
        k.setText("K");
        k.setPreferredSize(new java.awt.Dimension(73, 73));
        k.addActionListener(this);

        //j.setBackground(new java.awt.Color(255, 255 , 255));
        j.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //j.setForeground(new java.awt.Color(0, 0, 0));
        j.setText("J");
        j.setPreferredSize(new java.awt.Dimension(73, 73));
        j.addActionListener(this);

        //z.setBackground(new java.awt.Color(255, 255 , 255));
        z.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //z.setForeground(new java.awt.Color(0, 0, 0));
        z.setText("Z");
        z.setPreferredSize(new java.awt.Dimension(73, 73));
        z.addActionListener(this);

        //v.setBackground(new java.awt.Color(255, 255 , 255));
        v.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //v.setForeground(new java.awt.Color(0, 0, 0));
        v.setText("V");
        v.setPreferredSize(new java.awt.Dimension(73, 73));
        v.addActionListener(this);

        //b.setBackground(new java.awt.Color(255, 255 , 255));
        b.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //b.setForeground(new java.awt.Color(0, 0, 0));
        b.setText("B");
        b.setPreferredSize(new java.awt.Dimension(73, 73));
        b.addActionListener(this);

        //n.setBackground(new java.awt.Color(255, 255 , 255));
        n.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //n.setForeground(new java.awt.Color(0, 0, 0));
        n.setText("N");
        n.setPreferredSize(new java.awt.Dimension(73, 73));
        n.addActionListener(this);

        //c.setBackground(new java.awt.Color(255, 255 , 255));
        c.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //c.setForeground(new java.awt.Color(0, 0, 0));
        c.setText("C");
        c.setPreferredSize(new java.awt.Dimension(73, 73));
        c.addActionListener(this);

        //btnSpace.setBackground(new java.awt.Color(255, 255 , 255));
        btnSpace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        //btnSpace.setForeground(new java.awt.Color(0, 0, 0));
        btnSpace.setText("SPACE");
        btnSpace.setActionCommand(" ");
        btnSpace.setPreferredSize(new java.awt.Dimension(73, 73));
        btnSpace.addActionListener(this);

        //btnBachhSpaceLetter.setBackground(new java.awt.Color(255, 255 , 255));
        btnBachhSpaceLetter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/backspace.png"))); // NOI18N
        btnBachhSpaceLetter.setPreferredSize(new java.awt.Dimension(73, 73));
        btnBachhSpaceLetter.addActionListener(this);

        //btnResetLetter.setBackground(new java.awt.Color(255, 255 , 255));
        btnResetLetter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/reset.png"))); // NOI18N
        btnResetLetter.setPreferredSize(new java.awt.Dimension(73, 73));
        btnResetLetter.addActionListener(this);

        //btnLetterMins.setBackground(new java.awt.Color(255, 255 , 255));
        btnLetterMins.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btnLetterMins.setForeground(new java.awt.Color(0, 0, 0));
        btnLetterMins.setText("-");
        btnLetterMins.setPreferredSize(new java.awt.Dimension(73, 73));
        btnLetterMins.addActionListener(this);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnSpace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnLetterMins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnBachhSpaceLetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnResetLetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(y, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(q, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(w, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(u, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(o, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(g, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(j, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(l, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(z, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(w, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(q, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(y, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(u, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(o, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(g, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(j, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(z, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(h, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnResetLetter, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLetterMins, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBachhSpaceLetter, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("LETTER", jPanel3);

        //btn9.setBackground(new java.awt.Color(255, 255 , 255));
        btn9.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn9.setForeground(new java.awt.Color(0, 0, 0));
        btn9.setText("9");
        btn9.setActionCommand("09");
        btn9.setPreferredSize(new java.awt.Dimension(73, 73));
        btn9.addActionListener(this);

        //btn8.setBackground(new java.awt.Color(255, 255 , 255));
        btn8.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn8.setForeground(new java.awt.Color(0, 0, 0));
        btn8.setText("8");
        btn8.setActionCommand("08");
        btn8.setPreferredSize(new java.awt.Dimension(73, 73));
        btn8.addActionListener(this);

        //btn7.setBackground(new java.awt.Color(255, 255 , 255));
        btn7.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn7.setForeground(new java.awt.Color(0, 0, 0));
        btn7.setText("7");
        btn7.setActionCommand("07");
        btn7.setPreferredSize(new java.awt.Dimension(73, 73));
        btn7.addActionListener(this);

        //btn90.setBackground(new java.awt.Color(255, 255 , 255));
        btn90.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn90.setForeground(new java.awt.Color(0, 0, 0));
        btn90.setText("90");
        btn90.setPreferredSize(new java.awt.Dimension(73, 73));
        btn90.addActionListener(this);

        //btn4.setBackground(new java.awt.Color(255, 255 , 255));
        btn4.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn4.setForeground(new java.awt.Color(0, 0, 0));
        btn4.setText("4");
        btn4.setActionCommand("04");
        btn4.setPreferredSize(new java.awt.Dimension(73, 73));
        btn4.addActionListener(this);

        //btn5.setBackground(new java.awt.Color(255, 255 , 255));
        btn5.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn5.setForeground(new java.awt.Color(0, 0, 0));
        btn5.setText("5");
        btn5.setActionCommand("05");
        btn5.setPreferredSize(new java.awt.Dimension(73, 73));
        btn5.addActionListener(this);

        //btn6.setBackground(new java.awt.Color(255, 255 , 255));
        btn6.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn6.setForeground(new java.awt.Color(0, 0, 0));
        btn6.setText("6");
        btn6.setActionCommand("06");
        btn6.setPreferredSize(new java.awt.Dimension(73, 73));
        btn6.addActionListener(this);

        //btn60.setBackground(new java.awt.Color(255, 255 , 255));
        btn60.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btn60.setForeground(new java.awt.Color(0, 0, 0));
        btn60.setText("60");
        btn60.setPreferredSize(new java.awt.Dimension(73, 73));
        btn60.addActionListener(this);

        //btn1.setBackground(new java.awt.Color(255, 255 , 255));
        btn1.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btn1.setForeground(new java.awt.Color(0, 0, 0));
        btn1.setText("1");
        btn1.setActionCommand("01");
        btn1.setPreferredSize(new java.awt.Dimension(73, 73));
        btn1.setMinimumSize(new Dimension(73, 73));
        btn1.addActionListener(this);

        //btn0.setBackground(new java.awt.Color(255, 255 , 255));
        btn0.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btn0.setForeground(new java.awt.Color(0, 0, 0));
        btn0.setText("0");
        btn0.setActionCommand("00");
        btn0.setPreferredSize(new java.awt.Dimension(73, 73));
        btn0.addActionListener(this);

        //btnMinus.setBackground(new java.awt.Color(255, 255 , 255));
        btnMinus.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btnMinus.setForeground(new java.awt.Color(0, 0, 0));
        btnMinus.setText("-");
        btnMinus.setActionCommand("--");
        btnMinus.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnMinus.addActionListener(this);

        //btn2.setBackground(new java.awt.Color(255, 255 , 255));
        btn2.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btn2.setText("2");
        btn2.setActionCommand("02");
        btn2.setPreferredSize(new java.awt.Dimension(73, 73));
        btn2.addActionListener(this);
        
        btn3.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btn3.setText("3");
        btn3.setPreferredSize(new java.awt.Dimension(73, 73));
        btn3.addActionListener(this);
        btn3.setActionCommand("03");
        
        btnDot.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btnDot.setForeground(new java.awt.Color(0, 0, 0));
        btnDot.setText(".");
        btnDot.setActionCommand("..");
        btnDot.setPreferredSize(new java.awt.Dimension(73, 73));
        //btnDot.addActionListener(this);
        
        btn15.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        btn15.setText("15");
        btn15.setPreferredSize(new java.awt.Dimension(73, 73));
        btn15.addActionListener(this);

        ///btn30.setBackground(new java.awt.Color(255, 255 , 255));
        btn30.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn30.setForeground(new java.awt.Color(0, 0, 0));
        btn30.setText("30");
        btn30.setPreferredSize(new java.awt.Dimension(73, 73));
        btn30.addActionListener(this);

        //btnBackSpaceNo.setBackground(new java.awt.Color(255, 255 , 255));
        btnBackSpaceNo.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btnBackSpaceNo.setForeground(new java.awt.Color(0, 0, 0));
        btnBackSpaceNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/backspace.png"))); // NOI18N
        btnBackSpaceNo.setPreferredSize(new java.awt.Dimension(73, 73));
        btnBackSpaceNo.addActionListener(this);

        //btn21.setBackground(new java.awt.Color(255, 255 , 255));
        btn21.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn21.setForeground(new java.awt.Color(0, 0, 0));
        btn21.setText("21");
        btn21.setPreferredSize(new java.awt.Dimension(73, 73));
        btn21.addActionListener(this);

        //btn14.setBackground(new java.awt.Color(255, 255 , 255));
        btn14.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn14.setForeground(new java.awt.Color(0, 0, 0));
        btn14.setText("14");
        btn14.setPreferredSize(new java.awt.Dimension(73, 73));
        btn14.addActionListener(this);

        //btn10.setBackground(new java.awt.Color(255, 255 , 255));
        btn10.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn10.setForeground(new java.awt.Color(0, 0, 0));
        btn10.setText("10");
        btn10.setPreferredSize(new java.awt.Dimension(73, 73));
        btn10.addActionListener(this);

        //btn28.setBackground(new java.awt.Color(255, 255 , 255));
        btn28.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btn28.setForeground(new java.awt.Color(0, 0, 0));
        btn28.setText("28");
        btn28.setPreferredSize(new java.awt.Dimension(73, 73));
        btn28.addActionListener(this);

        //btnOk.setBackground(new java.awt.Color(255, 255 , 255));
        btnOk.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btnOk.setForeground(new java.awt.Color(0, 0, 0));
        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/ok.png"))); // NOI18N
        btnOk.setPreferredSize(new java.awt.Dimension(73, 73));
        btnOk.addActionListener(this);

        //btnResetNo.setBackground(new java.awt.Color(255, 255 , 255));
        btnResetNo.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btnResetNo.setForeground(new java.awt.Color(0, 0, 0));
        btnResetNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/reset.png"))); // NOI18N
        btnResetNo.setPreferredSize(new java.awt.Dimension(73, 73));
        btnResetNo.addActionListener(this);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBackSpaceNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn0, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btnDot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnResetNo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(51, 51, 51))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn90, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn60, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDot, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBackSpaceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );


        jTabbedPane1.addTab("NUMBER", jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        //btnExchange.setBackground(new java.awt.Color(255, 255 , 255));
        btnExchange.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btnExchange.setForeground(new java.awt.Color(0, 0, 0));
        btnExchange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/exchange.png"))); // NOI18N
        btnExchange.setPreferredSize(new java.awt.Dimension(73, 73));
        btnExchange.addActionListener(this);

        //btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        //btnHome.setForeground(new java.awt.Color(0, 0, 0));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/topraworld/pos/img/home.png"))); // NOI18N
        btnHome.setPreferredSize(new java.awt.Dimension(73, 73));
        btnHome.addActionListener(this);

        txtSearch.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        //txtSearch.setForeground(new java.awt.Color(255, 0, 0));
        txtSearch.setToolTipText("Search Key");
        txtSearch.setEditable(false);
        
        //set document action listner
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) { }
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				qp.queryProduct(ProductInfor.this);
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) { }
    	});

        txtQty.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        ///txtQty.setForeground(new java.awt.Color(255, 0, 0));
        txtQty.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQty.setToolTipText("Quantity");
        txtQty.setEditable(false);

        jLabel1.setText("Search By Item : ");

        rbName.setSelected(true);
        rbName.setText("Name");
        rbName.setActionCommand("rbName");
        rbName.addActionListener(this);
        
        rbValue.setText("Code");
        rbValue.addActionListener(this);
        rbValue.setActionCommand("rbValue");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnExchange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbValue))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExchange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        
        
        pack();
        setLocationRelativeTo(null);
    }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e ==  null)
			return;
		
		if(e.getActionCommand()!= null && e.getActionCommand().length() == 1){//letters
			txtSearch.setText(txtSearch.getText().concat(e.getActionCommand()));
		}else if(e.getActionCommand()!= null && e.getActionCommand().length() == 2){//numbers
			//if the search key selected as value focus shold be name typing
			JButton btn = (JButton) e.getSource();
			if(rbValue.isSelected()){
				txtSearch.setText(txtSearch.getText().concat(btn.getText()));
			}else{
				txtQty.setText(txtQty.getText().concat(btn.getText()));
			}
			
		}else{//controls
			
			if(e.getSource().equals(btnExchange)){
				
				if(jTabbedPane1.getSelectedIndex() == 0) jTabbedPane1.setSelectedIndex(1);
				else jTabbedPane1.setSelectedIndex(0);
			}else if(e.getSource().equals(btnHome)){
				
				this.dispose();
			}else if(e.getSource().equals(rbName) || e.getSource().equals(rbValue)){
				
				if(e.getActionCommand().equals("rbName")){
					rbValue.setSelected(false);
					jTabbedPane1.setSelectedIndex(0);
				}	
				else{
					rbName.setSelected(false);
					jTabbedPane1.setSelectedIndex(1);
				}	
				
			}else if(e.getSource().equals(btnResetLetter)){
				txtSearch.setText("");
			}else if(e.getSource().equals(btnResetNo)){
				
				if(rbValue.isSelected()) txtSearch.setText("");
				else txtQty.setText("");
				
			}else if(e.getSource().equals(btnBachhSpaceLetter)){
				if(txtSearch.getText().length() > 0)
					txtSearch.setText(txtSearch.getText().substring(0, txtSearch.getText().length()-1));
				
			}else if(e.getSource().equals(btnBackSpaceNo)){
				
				if(rbValue.isSelected()){
					if(txtSearch.getText().length() > 0)
						txtSearch.setText(txtSearch.getText().substring(0, txtSearch.getText().length()-1));
				}else{
					if(txtQty.getText().length() > 0)
						txtQty.setText(txtQty.getText().substring(0, txtQty.getText().length()-1));
				}
			}else if(e.getSource().equals(btnOk)){	//create new line
				
				if(txtQty.getText().length() != 0 && tbInfo.getSelectedRow() > -1){
					
					try{
						int M_Product_ID = (Integer) tbInfo.getModel().getValueAt(tbInfo.getSelectedRow(), 0);
						BigDecimal orderdQty = new BigDecimal(txtQty.getText());
						//sales orders
						if(this.TableID == MOrder.Table_ID){
							
							BigDecimal availableQty = (BigDecimal)tbInfo.getValueAt(tbInfo.getSelectedRow(), 3);
							
							//validate for available quantity and quantity ordered
							if(availableQty.compareTo(orderdQty) == -1){
								txtQty.setText(availableQty+"");
								new POSError(this.posPanel.getFrame(), "Insifficient Inventory", "Available : "+availableQty+", Ordered : "+orderdQty+"");
								
							}else{
								//Creating Lines
								if(orderdQty.compareTo(new BigDecimal(0)) == 1)//zero amount quantity
									this.createLine(M_Product_ID, orderdQty);
								else{
									new POSError(this.posPanel.getFrame(), "Zero or Minus Ordered Quantity", "Ordered quantity should be greater than zero!");
								}	
							}
							
						//MInventory requisition	
						}else if(this.TableID == MMovement.Table_ID){
							//transfers are done based on packs
							this.posPanel.getPosModel().newRequisitionLine(movement, M_Product_ID , orderdQty);
							jTabbedPane1.setSelectedIndex(0);
							txtSearch.setText("");
							txtQty.setText("");
						}
						
					}catch(Exception ex){
						
						new POSError(this.posPanel.getFrame(), "Error", ex.toString());
					}
					
				}else{
					new POSError(this.posPanel.getFrame(), "Invalid Input", "Undefined user action occured !");
				}
			}
		}
	}
	
	
	private void tbInfoMouseClicked(java.awt.event.MouseEvent evt) {                                     
        
		if(evt != null){
			rbName.setSelected(true);
			rbValue.setSelected(false);
			txtQty.setText("");
			jTabbedPane1.setSelectedIndex(1);
		}
    }   
	
	public JTable getTbInfo(){
		return this.tbInfo;
	}
	
	public JTextField getSearchKey(){
		return this.txtSearch;
	}
	
	public POSPanel getPosPanel(){
		
		return this.posPanel;
	}
	
	private void createLine(int M_Product_ID , BigDecimal orderdQty){
		
		posPanel.getPosModel().createLine(M_Product_ID, orderdQty);
		//once a line has created again get readying to create new one
		//change number pad to letter pad and clear parameter infors
		jTabbedPane1.setSelectedIndex(0);
		txtSearch.setText("");
		txtQty.setText("");
	}
}


class CustomRenderer extends DefaultTableCellRenderer 
{
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        

        if(column == 3){
        	
        	BigDecimal count = (BigDecimal) value;
        	
        	if(count.compareTo(new BigDecimal(0)) == 1)
        		cellComponent.setForeground(new java.awt.Color(0, 0, 0));
        	else
        		cellComponent.setForeground(Color.RED);
        	
        	setHorizontalAlignment(SwingConstants.CENTER);
        }
        return cellComponent;
    }
}