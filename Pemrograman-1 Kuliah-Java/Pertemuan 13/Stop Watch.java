import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class StopWatchDemo extends javax.swing.JFrame {
    private static final String Start = "Start";
    private static final String Pause = "Pause";
    private static final String Reset = "Reset";
    private boolean isRunning;
    private Timer timer = new Timer(100, new WaktuListener());
    private long initTime = 0; // jangan gunakan System.currentTimeMillis() di sini
    private long startTime;
    private long pauseTime;
    private long elapsedTime = 0L; // menyimpan waktu yang sudah berlalu saat pause

    // komponen UI (diasumsikan ada pada form)
    private JPanel jPanel1;
    private JLabel lblWaktu;
    private JButton btnStart;
    private JButton btnReset;

    /** Creates new form StopWatchDemo */
    public StopWatchDemo() {
        setTitle("Stop Watch Demo");
        initComponents();
        jPanel1.setBackground(Color.BLACK);
        btnReset.setEnabled(false);
        setWaktu();
    }

    private void initComponents() {
        // Implementasi sederhana untuk membuat form berfungsi
        jPanel1 = new JPanel(new BorderLayout());
        lblWaktu = new JLabel("00:00:00.0", SwingConstants.CENTER);
        btnStart = new JButton(Start);
        btnReset = new JButton(Reset);

        lblWaktu.setFont(new Font("Arial", Font.BOLD, 48));
        lblWaktu.setForeground(Color.YELLOW);

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnStart);
        panelBtn.add(btnReset);

        add(jPanel1, BorderLayout.CENTER);
        jPanel1.add(lblWaktu, BorderLayout.CENTER);
        add(panelBtn, BorderLayout.SOUTH);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // tombol akan mengubah teks dan memanggil start/pause sesuai status
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
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        setSize(420, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setWaktu() {
        lblWaktu.setFont(new Font("Arial", Font.BOLD, 48));
        lblWaktu.setForeground(Color.YELLOW);
        // Mulai dari 00:00:00.0 — jangan gunakan initTime yang menyebabkan offset
        lblWaktu.setText(getCurrentTime(0));
        lblWaktu.setHorizontalAlignment(JLabel.CENTER);
    }

    public void start() {
        // mulai atau resume: gunakan elapsedTime untuk resume agar tidak mulai dari offset acak
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            timer.start();
            isRunning = true;
        }
    }

    public void pause() {
        if (isRunning) {
            pauseTime = System.currentTimeMillis();
            elapsedTime = pauseTime - startTime; // simpan elapsed saat pause
            timer.stop();
            isRunning = false;
        }
    }

    public void reset() {
        startTime = 0;
        pauseTime = 0;
        elapsedTime = 0;
        isRunning = false;
        timer.stop();
        // tetapkan ke nol secara eksplisit
        lblWaktu.setText(getCurrentTime(0));
        btnStart.setText(Start);
        btnReset.setEnabled(false);
    }

    private String formatWaktu(final long time) {
        final long hr = TimeUnit.MILLISECONDS.toHours(time);
        final long min = TimeUnit.MILLISECONDS.toMinutes(time - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(time - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = time - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec);
        return String.format("%02d:%02d:%02d.%01d", hr, min, sec, ms / 100);
    }

    private String getCurrentTime(long time) {
        return formatWaktu(time);
    }

    class WaktuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            long displayTime = isRunning ? (System.currentTimeMillis() - startTime) : elapsedTime;
            lblWaktu.setText(getCurrentTime(displayTime));
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        reset();
    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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

    // main untuk menjalankan demo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StopWatchDemo sw = new StopWatchDemo();
            sw.setVisible(true);
        });
    }
}
