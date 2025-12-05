package ParameterForm;

import javax.swing.*;

/**
 * FormMasukan - Form Anak yang berupa DIALOG (JDialog)
 * 
 * Fungsi:
 * - Menampilkan form input untuk user
 * - Menyimpan data yang diinput
 * - Mengirim data kembali ke form induk via getter methods
 * 
 * Status Code:
 * - CANCEL = 0 (user klik Batal)
 * - OK = 1 (user klik Tambah)
 */
public class FormMasukan extends javax.swing.JDialog {
    
    // ===== STATUS CODE =====
    public static final int CANCEL = 0;
    public static final int OK = 1;

    // ===== VARIABEL UNTUK MENYIMPAN DATA =====
    private int status = CANCEL;  // Status default: CANCEL
    private String nama;
    private String jurusan;
    private String umur;
    private String jenisKelamin;
    
    // ===== KOMPONEN GUI =====
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private javax.swing.JTextField txtNama, txtUmur;
    private javax.swing.JComboBox<String> cboJurusan, cboJenisKelamin;
    private javax.swing.JButton btnTambah, btnBatal;

    /**
     * CONSTRUCTOR - Dialog dengan Parent Form dan Modal Flag
     * 
     * @param parent - Form induk yang membuka dialog ini
     * @param modal - true = dialog modal (induk tidak bisa diakses sampai dialog ditutup)
     */
    public FormMasukan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);  // Panggil constructor JDialog
        initComponents();
        setTitle("Form Masukan");
        setLocationRelativeTo(parent);  // Posisi di tengah form induk
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

        // ===== INPUT FIELD =====
        txtNama = new javax.swing.JTextField(20);
        txtUmur = new javax.swing.JTextField(20);

        // ===== COMBO BOX =====
        cboJurusan = new javax.swing.JComboBox<>(
            new String[]{"SI", "KA", "TI"}
        );
        cboJenisKelamin = new javax.swing.JComboBox<>(
            new String[]{"Laki-laki", "Perempuan"}
        );

        // ===== BUTTON =====
        btnTambah = new javax.swing.JButton("Tambah");
        btnTambah.addActionListener(evt -> btnTambahActionPerformed(evt));

        btnBatal = new javax.swing.JButton("Batal");
        btnBatal.addActionListener(evt -> btnBatalActionPerformed(evt));

        // ===== LAYOUT =====
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

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
                    .addComponent(cboJurusan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUmur)
                    .addComponent(cboJenisKelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cboJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUmur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnBatal))
                .addGap(20, 20, 20))
        );

        pack();
    }

    /**
     * EVENT HANDLER TOMBOL TAMBAH
     * =============================
     * Proses:
     * 1. Ambil semua nilai dari input fields
     * 2. Simpan ke variabel instance
     * 3. Set status = OK
     * 4. Tutup dialog (setVisible(false))
     */
    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {
        // ===== AMBIL DATA DARI INPUT FIELDS =====
        this.nama = txtNama.getText();
        this.jurusan = (String) cboJurusan.getSelectedItem();
        this.umur = txtUmur.getText();
        this.jenisKelamin = (String) cboJenisKelamin.getSelectedItem();
        
        // ===== SET STATUS = OK =====
        this.status = OK;
        
        // ===== TUTUP DIALOG =====
        // setVisible(false) = dialog tetap ada tapi tidak terlihat
        // Form induk akan membaca data via getter methods
        this.setVisible(false);
        
        System.out.println("[Dialog] Data tersimpan: " + nama + ", " + jurusan);
    }

    /**
     * EVENT HANDLER TOMBOL BATAL
     * =============================
     * Proses:
     * 1. Set status = CANCEL (user tidak menginput apa-apa)
     * 2. Tutup dialog
     */
    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {
        // Set status = CANCEL
        this.status = CANCEL;
        
        // Tutup dialog tanpa menyimpan data
        this.setVisible(false);
        
        System.out.println("[Dialog] User membatalkan input");
    }

    // ===== GETTER METHODS - UNTUK FORM INDUK MEMBACA DATA =====
    
    /**
     * Dapatkan status (OK atau CANCEL)
     */
    public int getStatus() { 
        return status; 
    }

    /**
     * Dapatkan nama yang diinput
     */
    public String getNama() { 
        return nama; 
    }

    /**
     * Dapatkan jurusan yang dipilih
     */
    public String getJurusan() { 
        return jurusan; 
    }

    /**
     * Dapatkan umur yang diinput
     */
    public String getUmur() { 
        return umur; 
    }

    /**
     * Dapatkan jenis kelamin yang dipilih
     */
    public String getJenisKelamin() { 
        return jenisKelamin; 
    }
}
