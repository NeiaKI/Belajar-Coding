import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JamDigitalDemo extends javax.swing.JFrame {
    private Timer timer;
    private JPanel jPanel1;
    private JLabel lblJamDigital;
    private JButton btnStart;
    private JButton btnStop;
    private JButton btnKeluar;
    
    /** Creates new form JamDigitalDemo */
    public JamDigitalDemo() {
        setTitle("Jam Digital Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 180);
        setLocationRelativeTo(null);
        
        initComponents();
        jPanel1.setBackground(Color.BLACK);
        
        // mengatur font dan warna label
        lblJamDigital.setFont(new Font("Arial", Font.BOLD, 48));
        lblJamDigital.setForeground(Color.YELLOW);
        lblJamDigital.setHorizontalAlignment(JLabel.CENTER);
        
        // membuat timer dengan interval 1000ms (1 detik)
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // mendapatkan waktu sekarang
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                // menampilkan waktu ke label
                lblJamDigital.setText(sdf.format(now));
            }
        });
    }
    
    private void initComponents() {
        jPanel1 = new JPanel(new BorderLayout());
        lblJamDigital = new JLabel("00:00:00");
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnKeluar = new JButton("Keluar");
        
        jPanel1.add(lblJamDigital, BorderLayout.CENTER);
        add(jPanel1, BorderLayout.CENTER);
        
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBtn.add(btnStart);
        panelBtn.add(btnStop);
        panelBtn.add(btnKeluar);
        add(panelBtn, BorderLayout.SOUTH);
        
        btnStart.addActionListener(e -> btnStartActionPerformed(e));
        btnStop.addActionListener(e -> btnStopActionPerformed(e));
        btnKeluar.addActionListener(e -> btnKeluarActionPerformed(e));
    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        timer.start();
    }

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        timer.stop();
    }

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JamDigitalDemo().setVisible(true);
        });
    }
}
