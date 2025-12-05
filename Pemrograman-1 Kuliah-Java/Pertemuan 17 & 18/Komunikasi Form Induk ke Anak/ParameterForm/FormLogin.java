package ParameterForm;

import javax.swing.*;
import java.awt.*;

/**
 * Form Login - Form Induk yang MENGIRIM data ke FormUtama
 */
public class FormLogin extends javax.swing.JFrame {
    
    // Deklarasi komponen
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JPasswordField txpPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnKeluar;

    /**
     * Constructor
     */
    public FormLogin() {
        initComponents();
        setTitle("Form Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Inisialisasi komponen GUI
     */
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txpPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // Set label text
        jLabel1.setText("Username:");
        jLabel2.setText("Password:");

        // Tombol Login
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        // Tombol Keluar
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        // ===== LAYOUT MENGGUNAKAN GroupLayout =====
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        // Horizontal Group
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txpPassword))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        
        // Vertical Group
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnKeluar))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * EVENT HANDLER TOMBOL LOGIN
     * =============================
     * PROSES:
     * 1. Ambil input username dan password
     * 2. Validasi
     * 3. Jika benar, BUKA FormUtama dan KIRIM USERNAME sebagai parameter
     * 4. Tutup FormLogin
     */
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // Ambil nilai dari text field
        String username = txtUsername.getText();
        String password = new String(txpPassword.getPassword());

        // Validasi input tidak kosong
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Username dan Password tidak boleh kosong!",
                "WARNING",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verifikasi username dan password (contoh sederhana)
        // Username: admin, Password: 12345
        if (username.equalsIgnoreCase("admin") && password.equals("12345")) {
            // ===== LOGIN BERHASIL =====
            // BUKA FormUtama DAN KIRIM USERNAME sebagai parameter
            FormUtama mainForm = new FormUtama(username);
            mainForm.setVisible(true);
            
            // Tutup form login
            this.dispose();
        } else {
            // ===== LOGIN GAGAL =====
            JOptionPane.showMessageDialog(this,
                "Username atau Password salah!\n\n" +
                "Gunakan:\n" +
                "Username: admin\n" +
                "Password: 12345",
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
            
            // Kosongkan password
            txpPassword.setText("");
            txpPassword.requestFocus();
        }
    }

    /**
     * Main method - Entry point program
     */
    public static void main(String args[]) {
        // Set Look and Feel sesuai sistem operasi
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Tampilkan FormLogin di Event Dispatch Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }
}
