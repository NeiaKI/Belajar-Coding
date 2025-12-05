package ParameterForm;

import javax.swing.*;

/**
 * FormKeluaran - Form Induk yang membuka FormMasukan
 * 
 * Fungsi:
 * - Menampilkan data yang diterima dari Form Anak
 * - Membuka FormMasukan saat tombol "Masukan" diklik
 * - Membaca data dari FormMasukan setelah dialog ditutup
 */
public class FormKeluaran extends javax.swing.JFrame {
    
    // ===== KOMPONEN UNTUK MENAMPILKAN DATA =====
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private javax.swing.JTextField txtNama, txtJurusan, txtUmur, txtJenisKelamin;
    private javax.swing.JButton btnMasukan, btnKeluar;

    /**
     * Constructor
     */
    public FormKeluaran() {
        initComponents();
        setTitle("Form Keluaran");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Inisialisasi komponen GUI
     */
    private void initComponents() {
        // ===== LABEL =====
        jLabel1 = new javax.swing.JLabel("Nama:");
        jLabel2 = new javax.swing.JLabel("Jurusan:");
        jLabel3 = new javax.swing.JLabel("Umur:");
        jLabel4 = new javax.swing.JLabel("Jenis Kelamin:");

        // ===== TEXT FIELD (READ-ONLY) =====
        txtNama = new javax.swing.JTextField(20);
        txtNama.setEditable(false);  // Tidak bisa diedit
        
        txtJurusan = new javax.swing.JTextField(20);
        txtJurusan.setEditable(false);  // Tidak bisa diedit
        
        txtUmur = new javax.swing.JTextField(20);
        txtUmur.setEditable(false);  // Tidak bisa diedit
        
        txtJenisKelamin = new javax.swing.JTextField(20);
        txtJenisKelamin.setEditable(false);  // Tidak bisa diedit

        // ===== BUTTON MASUKAN =====
        btnMasukan = new javax.swing.JButton("Masukan");
        btnMasukan.addActionListener(evt -> btnMasukanActionPerformed(evt));

        // ===== BUTTON KELUAR =====
        btnKeluar = new javax.swing.JButton("Keluar");
        btnKeluar.addActionListener(evt -> System.exit(0));

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
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txtJurusan)
                    .addComponent(txtUmur)
                    .addComponent(txtJenisKelamin))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(btnMasukan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        
        // Vertical Group
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUmur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMasukan)
                    .addComponent(btnKeluar))
                .addGap(20, 20, 20))
        );

        pack();
    }

    /**
     * EVENT HANDLER TOMBOL MASUKAN
     * =============================
     * PROSES KOMUNIKASI FORM ANAK KE INDUK:
     * 
     * 1. BUAT instance FormMasukan
     * 2. TAMPILKAN FormMasukan (dialog)
     *    -> User mengisi data
     *    -> User klik "Tambah" atau "Batal"
     * 3. DIALOG DITUTUP (setVisible(false))
     * 4. CEK STATUS dari FormMasukan
     * 5. JIKA OK: AMBIL DATA via getter methods
     * 6. TAMPILKAN DATA di text fields
     */
    private void btnMasukanActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("[FormInduk] Membuka dialog...");
        
        // ===== STEP 1: BUAT INSTANCE FormMasukan =====
        FormMasukan dialog = new FormMasukan(this, true);
        
        // ===== STEP 2: TAMPILKAN DIALOG (MODAL) =====
        // Modal = form induk tidak bisa diakses sampai dialog ditutup
        dialog.setVisible(true);
        
        // ===== STEP 3: DIALOG DITUTUP, PROSES LANJUT KE SINI =====
        
        System.out.println("[FormInduk] Dialog ditutup");
        System.out.println("[FormInduk] Status dari dialog: " + dialog.getStatus());
        
        // ===== STEP 4: CEK STATUS =====
        // Jika user klik tombol "Tambah", status akan OK
        if (dialog.getStatus() == FormMasukan.OK) {
            System.out.println("[FormInduk] User menekan TAMBAH, ambil data...");
            
            // ===== STEP 5: AMBIL DATA VIA GETTER METHODS =====
            String nama = dialog.getNama();
            String jurusan = dialog.getJurusan();
            String umur = dialog.getUmur();
            String jenisKelamin = dialog.getJenisKelamin();
            
            // ===== STEP 6: TAMPILKAN DATA DI TEXT FIELDS =====
            txtNama.setText(nama);
            txtJurusan.setText(jurusan);
            txtUmur.setText(umur);
            txtJenisKelamin.setText(jenisKelamin);
            
            System.out.println("[FormInduk] Data ditampilkan:");
            System.out.println("  Nama: " + nama);
            System.out.println("  Jurusan: " + jurusan);
            System.out.println("  Umur: " + umur);
            System.out.println("  Jenis Kelamin: " + jenisKelamin);
            
            // Optional: Tampilkan message dialog
            JOptionPane.showMessageDialog(this,
                "Data berhasil ditambahkan!\n\n" +
                "Nama: " + nama + "\n" +
                "Jurusan: " + jurusan + "\n" +
                "Umur: " + umur + " tahun\n" +
                "Jenis Kelamin: " + jenisKelamin,
                "SUCCESS",
                JOptionPane.INFORMATION_MESSAGE);
        } 
        else {
            // Jika user klik tombol "Batal", status akan CANCEL
            System.out.println("[FormInduk] User menekan BATAL");
            JOptionPane.showMessageDialog(this,
                "Input dibatalkan!",
                "INFO",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Main method
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormKeluaran().setVisible(true);
            }
        });
    }
}
