import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JamDigitalDemo extends javax.swing.JFrame {
    private Timer timer;
    
    /**
     * Creates new form JamDigitalDemo
     */
    public JamDigitalDemo() {
        initComponents();
        setTitle("Jam Digital Demo");
        
        // mengatur latar belakang panel
        jPanel1.setBackground(Color.BLACK);
        
        // mengatur font, warna, dan alignment label
        lblJamDigital.setFont(new Font("Arial", Font.BOLD, 48));
        lblJamDigital.setForeground(Color.YELLOW);
        lblJamDigital.setHorizontalAlignment(JLabel.CENTER);
        
        // membuat timer dengan interval 1000ms (1 detik)
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // mendapatkan waktu sistem sekarang
                Date now = new Date();
                
                // format waktu menggunakan SimpleDateFormat
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                
                // menampilkan waktu ke label
                lblJamDigital.setText(sdf.format(now));
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        lblJamDigital = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        
        lblJamDigital.setFont(new java.awt.Font("Arial", Font.BOLD, 48));
        lblJamDigital.setForeground(new java.awt.Color(255, 255, 0));
        lblJamDigital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJamDigital.setText("00:00:00");
        
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        
        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
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
            .addComponent(lblJamDigital, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblJamDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop)
                    .addComponent(btnKeluar))
                .addContainerGap(50, Short.MAX_VALUE))
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
     * Menjalankan Timer untuk memulai tampilan jam digital
     */
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        timer.start();
    }
    
    /**
     * Menghentikan Timer untuk berhenti tampilan jam digital
     */
    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {
        timer.stop();
    }
    
    /**
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
                new JamDigitalDemo().setVisible(true);
            }
        });
    }
    
    // Deklarasi komponen
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblJamDigital;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnKeluar;
}
