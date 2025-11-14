import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingDemo extends javax.swing.JFrame {
    private String puisi = "Krawang-Bekasi\n\n" +
        "Kami yang kini terbaring antara Krawang-Bekasi\n" +
        "tidak bisa teriak \"Merdeka\" dan angkat senjata lagi.\n" +
        "\n" +
        "Tapi siapakah yang tidak lagi mendengar deru kami, \n" +
        "terbayang kami maju dan mendegap hati ?\n\n" +
        "Kami bicara padamu dalam hening di malam sepi\n" +
        "\n" +
        "Jika dada rasa hampa dan jam dinding yang berdetak\n" +
        "Kami mati muda. Yang tinggal tulang diliputi debu.\n" +
        "Kenang, kenanglah kami.\n\n" +
        "Kami sudah coba apa yang kami bisa\n" +
        "Tapi kerja belum selesai, belum bisa memperhitungkan " +
        "arti 4-5 ribu nyawa\n\n" +
        "Kami cuma tulang-tulang berserakan\n" +
        "Tapi adalah kepunyaanmu\n" +
        "Kaulah lagi yang tentukan nilai tulang-tulang berserakan\n\n" +
        "Atau jiwa kami melayang untuk kemerdekaan kemenangan dan harapan\n" +
        "atau tidak untuk apa-apa, \n" +
        "Kami tidak tahu, kami tidak lagi bisa berkata\n" +
        "Kaulah sekarang yang berkata\n\n" +
        "Kami bicara padamu dalam hening di malam sepi\n" +
        "Jika ada rasa hampa dan jam dinding yang berdetak\n\n" +
        "Kenang, kenanglah kami\n" +
        "Teruskan, teruskan jiwa kami\n" +
        "Menjaga Bung Karno\n" +
        "menjaga Bung Hatta\n" +
        "menjaga Bung Sjahrir\n\n" +
        "Kami sekarang mayat\n" +
        "Berikan kami arti\n" +
        "Berjagalah terus di garis batas pernyataan dan impian\n\n" +
        "Kenang, kenanglah kami\n" +
        "yang tinggal tulang-tulang diliputi debu\n" +
        "Beribu kami terbaring antara Krawang-Bekasi\n\n" +
        "Chairil Anwar (1948)";
    
    int charIndex = 0;
    private Timer timer;
    
    /**
     * Creates new form TypingDemo
     */
    public TypingDemo() {
        initComponents();
        setTitle("Typing Demo");
        btnPause.setEnabled(false);
        
        // mengatur tampilan text area
        txaPuisi.setForeground(Color.blue);
        txaPuisi.setEditable(false);
        txaPuisi.setLineWrap(true);
        txaPuisi.setWrapStyleWord(true);
        
        // membuat timer dengan interval 100ms untuk efek ketikan
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // mengambil teks yang sudah ditampilkan
                String puisiText = txaPuisi.getText();
                
                // menambahkan karakter berikutnya dari puisi
                puisiText += puisi.charAt(charIndex);
                
                // menampilkan teks ke text area
                txaPuisi.setText(puisiText);
                
                // menambah index untuk karakter berikutnya
                charIndex++;
                
                // jika semua karakter sudah ditampilkan
                if (charIndex >= puisi.length()) {
                    timer.stop();
                    btnStart.setEnabled(true);
                    btnPause.setEnabled(false);
                }
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        txaPuisi = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        
        txaPuisi.setColumns(20);
        txaPuisi.setRows(5);
        txaPuisi.setFont(new Font("Arial", Font.PLAIN, 12));
        jScrollPane1.setViewportView(txaPuisi);
        
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        
        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });
        
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        
        // layout menggunakan GroupLayout
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnPause)
                    .addComponent(btnKeluar))
                .addGap(20, 20, 20))
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Event handler untuk tombol Start
     * Memulai atau melanjutkan animasi ketikan
     */
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        // jika animasi sudah selesai, reset dan mulai dari awal
        if (charIndex >= puisi.length()) {
            txaPuisi.setText("");
            charIndex = 0;
        }
        
        // memulai timer
        timer.start();
        
        // mengubah status tombol
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
    }
    
    /**
     * Event handler untuk tombol Pause
     * Menjeda atau melanjutkan animasi ketikan
     */
    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {
        if (btnPause.getText().equals("Pause")) {
            // ubah text menjadi Continue dan stop timer
            btnPause.setText("Continue");
            timer.stop();
        } else {
            // ubah text menjadi Pause dan jalankan timer kembali
            btnPause.setText("Pause");
            timer.start();
        }
    }
    
    /**
     * Event handler untuk tombol Keluar
     * Menutup aplikasi
     */
    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    /**
     * Main method untuk menjalankan aplikasi
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TypingDemo().setVisible(true);
            }
        });
    }
    
    // Deklarasi komponen
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextArea txaPuisi;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnKeluar;
}
