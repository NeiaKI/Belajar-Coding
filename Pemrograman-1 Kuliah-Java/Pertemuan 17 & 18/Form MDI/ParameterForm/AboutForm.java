package ParameterForm;

import javax.swing.*;

/**
 * AboutForm - Form Anak yang berupa JInternalFrame
 * 
 * JInternalFrame = Frame dalam frame (bisa di-resize, minimize, maximize)
 * Berbeda dengan JDialog yang terpisah dari parent
 */
public class AboutForm extends javax.swing.JInternalFrame {
    
    // ===== KOMPONEN GUI =====
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextArea txaInfoPC;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton btnOK;

    /**
     * Constructor
     */
    public AboutForm() {
        initComponents();
        setTitle("About Form");
        
        // ===== PROPERTIES JInternalFrame =====
        setResizable(true);      // Bisa di-resize
        setMaximizable(true);    // Bisa di-maximize
        setIconifiable(true);    // Bisa di-minimize
        setClosable(true);       // Bisa di-close
    }

    /**
     * Inisialisasi komponen GUI
     */
    private void initComponents() {
        // ===== LABEL =====
        jLabel1 = new javax.swing.JLabel("Informasi Program:");
        
        // ===== TEXT AREA =====
        txaInfoPC = new javax.swing.JTextArea();
        txaInfoPC.setColumns(20);
        txaInfoPC.setRows(5);
        txaInfoPC.setEditable(false);  // Tidak bisa diedit
        txaInfoPC.setLineWrap(true);   // Wrap text otomatis
        txaInfoPC.setWrapStyleWord(true);
        txaInfoPC.setText(
            "Program MDI Demo\n" +
            "Version 1.0\n" +
            "Created with Java Swing\n" +
            "Copyright 2024\n\n" +
            "MDI (Multiple Document Interface) adalah interface\n" +
            "yang memungkinkan menampilkan beberapa form dalam satu window."
        );
        
        // ===== SCROLL PANE (untuk text area) =====
        scrollPane = new javax.swing.JScrollPane(txaInfoPC);
        
        // ===== BUTTON =====
        btnOK = new javax.swing.JButton("OK");
        btnOK.addActionListener(evt -> this.dispose());  // Tutup internal frame

        // ===== SET CLOSE OPERATION =====
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

        // ===== LAYOUT =====
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        // Horizontal Group
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(btnOK, javax.swing.GroupLayout.Alignment.CENTER))
                .addGap(20, 20, 20))
        );
        
        // Vertical Group
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(btnOK)
                .addGap(20, 20, 20))
        );

        // ===== SET UKURAN DEFAULT =====
        pack();
        setSize(450, 350);
    }
}
