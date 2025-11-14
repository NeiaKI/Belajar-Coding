import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class StopWatchDemo extends javax.swing.JFrame {
    private static final String Start = "Start";
    private static final String Pause = "Pause";
    private static final String Reset = "Reset";
    
    private boolean isRunning;
    private Timer timer = new Timer(100, new WaktuListener());
    private long initTime = System.currentTimeMillis();
    private long startTime;
    private long pauseTime;
    
    /**
     * Creates new form StopWatchDemo
     */
    public StopWatchDemo() {
        initComponents();
        setTitle("Stop Watch Demo");
        jPanel1.setBackground(Color.BLACK);
        btnReset.setEnabled(false);
        setWaktu();
    }
    
    /**
     * Mengatur format waktu dan tampilan label
     */
    private void setWaktu() {
        lblWaktu.setFont(new Font("Arial", Font.BOLD, 48));
        lblWaktu.setForeground(Color.YELLOW);
        lblWaktu.setText("00:00:00.0");  // Mulai dari 00:00:00.0
        lblWaktu.setHorizontalAlignment(JLabel.CENTER);
    }
    
    /**
     * Memulai stop watch
     */
    public void start() {
        if (isRunning == false) {
            startTime = System.currentTimeMillis();
        } else {
            startTime = System.currentTimeMillis() - (pauseTime - startTime);
        }
        isRunning = true;
        timer.start();
    }
    
    /**
     * Menjeda stop watch
     */
    public void pause() {
        pauseTime = System.currentTimeMillis();
        timer.stop();
    }
    
    /**
     * Mereset stop watch
     */
    public void reset() {
        startTime = 0;
        isRunning = false;
        timer.stop();
        lblWaktu.setText("00:00:00.0");  // Reset ke 00:00:00.0
        btnStart.setText(Start);
    }
    
    /**
     * Memformat waktu menjadi format HH:MM:SS.ms
     */
    private String formatWaktu(final long time) {
        final long hr = TimeUnit.MILLISECONDS.toHours(time);
        final long min = TimeUnit.MILLISECONDS.toMinutes(time - 
                         TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(time - 
                         TimeUnit.HOURS.toMillis(hr) - 
                         TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(time - 
                        TimeUnit.HOURS.toMillis(hr) - 
                        TimeUnit.MINUTES.toMillis(min) - 
                        TimeUnit.SECONDS.toMillis(sec));
        
        return String.format("%02d:%02d:%02d.%01d", hr, min, sec, ms/100);
    }
    
    /**
     * Mendapatkan waktu saat ini
     */
    private String getCurrentTime(long time) {
        return formatWaktu(time);
    }
    
    /**
     * Inner class untuk mendengarkan event Timer
     */
    class WaktuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            lblWaktu.setText(getCurrentTime(System.currentTimeMillis() - startTime));
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        lblWaktu = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        
        lblWaktu.setFont(new java.awt.Font("Arial", Font.BOLD, 48));
        lblWaktu.setForeground(new java.awt.Color(255, 255, 0));
        lblWaktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWaktu.setText("00:00:00.0");
        
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        
        // layout menggunakan GroupLayout
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblWaktu, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnReset))
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
     * Event handler untuk tombol Start/Pause
     */
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        if (btnStart.getText().equals(Start)) {
            btnStart.setText(Pause);
            btnReset.setEnabled(false);
            start();
        } else {
            btnStart.setText(Start);
            btnReset.setEnabled(true);
            pause();
        }
    }
    
    /**
     * Event handler untuk tombol Reset
     */
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        reset();
    }
    
    /**
     * Main method untuk menjalankan aplikasi
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StopWatchDemo().setVisible(true);
            }
        });
    }
    
    // Deklarasi komponen
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStart;
}
