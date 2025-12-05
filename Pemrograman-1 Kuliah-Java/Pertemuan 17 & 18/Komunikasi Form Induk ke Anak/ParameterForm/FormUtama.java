package ParameterForm;

import javax.swing.*;

/**
 * Form Utama - Form Anak yang MENERIMA data dari FormLogin
 * 
 * DATA DIKIRIM melalui:
 * new FormUtama(username)  <-- parameter di constructor
 */
public class FormUtama extends javax.swing.JFrame {
    
    // ===== VARIABEL UNTUK MENYIMPAN DATA YANG DITERIMA =====
    private String namaUser;
    
    // Deklarasi komponen
    private javax.swing.JLabel lblSelamat;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JButton btnKeluar;

    /**
     * CONSTRUCTOR YANG MENERIMA PARAMETER
     * ===================================
     * Parameter namaUser berisi username yang dikirim dari FormLogin
     */
    public FormUtama(String namaUser) {
        // Simpan parameter yang diterima
        this.namaUser = namaUser;
        
        // Inisialisasi komponen
        initComponents();
        
        // Set judul form dengan username yang diterima
        setTitle("Form Utama - Selamat Datang " + namaUser);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Inisialisasi komponen GUI
     */
    private void initComponents() {
        lblSelamat = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // ===== TAMPILKAN DATA YANG DITERIMA =====
        // Menggunakan HTML untuk styling
        lblSelamat.setText("<html>" +
            "<div style='text-align: center;'>" +
            "<font color='blue' size='6'><b>SELAMAT DATANG!</b></font><br><br>" +
            "<font size='4'>Anda login sebagai:</font><br>" +
            "<font color='green' size='5'><b>" + namaUser + "</b></font>" +
            "</div>" +
            "</html>");
        lblSelamat.setHorizontalAlignment(SwingConstants.CENTER);

        // Info tambahan
        lblInfo.setText("<html>" +
            "<font size='3'>" +
            "Anda berhasil masuk ke aplikasi utama.<br>" +
            "Gunakan tombol Keluar untuk logout." +
            "</font>" +
            "</html>");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

        // Tombol Keluar
        btnKeluar.setText("Keluar");
        btnKeluar.setPreferredSize(new java.awt.Dimension(100, 35));
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        // ===== LAYOUT =====
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSelamat, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(btnKeluar))
                .addGap(50, 50, 50))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblSelamat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * Main method - untuk testing/debugging
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Test: Buka FormUtama dengan username "TestUser"
                new FormUtama("TestUser").setVisible(true);
            }
        });
    }
}
