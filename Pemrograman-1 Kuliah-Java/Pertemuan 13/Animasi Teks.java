import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    // Komponen UI yang dipakai dalam kode asli
    private JButton btnStart;
    private JButton btnPause;
    private JButton btnKeluar;
    private JTextArea txaPuisi;
    private JScrollPane scrollPane;

    /** Creates new form TypingDemo */
    public TypingDemo() {
        initComponents();
        setTitle("Typing Demo");
        btnPause.setEnabled(false);
        txaPuisi.setForeground(Color.blue);
        txaPuisi.setEditable(false);
        txaPuisi.setLineWrap(true);
        txaPuisi.setWrapStyleWord(true);
        
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String puisiText = txaPuisi.getText();
                puisiText += puisi.charAt(charIndex);
                txaPuisi.setText(puisiText);
                charIndex++;
                
                if (charIndex >= puisi.length()) {
                    //((Timer)e.getSource()).stop();
                    timer.stop();
                    btnStart.setEnabled(true);
                    btnPause.setEnabled(false);
                }
            }
        });
    }

    private void initComponents() {
        btnStart = new JButton("Start");
        btnPause = new JButton("Pause");
        btnKeluar = new JButton("Keluar");
        txaPuisi = new JTextArea();
        scrollPane = new JScrollPane(txaPuisi);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });
        btnKeluar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnStart);
        panelBtn.add(btnPause);
        panelBtn.add(btnKeluar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 520);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBtn, BorderLayout.SOUTH);
    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (charIndex >= puisi.length()) {
            //((Timer)e.getSource()).stop();
            txaPuisi.setText("");
            charIndex = 0;
        }
        
        timer.start();
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
    }

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (btnPause.getText().equals("Pause")) {
            btnPause.setText("Continue");
            timer.stop();
        } else {
            btnPause.setText("Pause");
            timer.start();
        }
    }

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TypingDemo().setVisible(true);
        });
    }
}
