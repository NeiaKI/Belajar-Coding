import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Demo penggunaan SplashScreen
 * Versi yang diperbaiki tanpa package declaration
 */
public class SplashScreenDemo {
    private SplashScreen screen;

    public SplashScreenDemo() {
        // Tampilkan splash screen
        splashScreenInit();
        
        // Simulasikan proses loading
        simulateLoading();
        
        // Sembunyikan splash screen setelah selesai
        splashScreenDestruct();
        
        // Tampilkan aplikasi utama
        showMainApplication();
    }

    /**
     * Inisialisasi dan tampilkan splash screen
     */
    private void splashScreenInit() {
        ImageIcon splashImage = null;
        
        // Coba load gambar dari folder Icons (jika ada)
        try {
            java.net.URL imageUrl = getClass().getResource("/Icons/Duke3D.jpg");
            if (imageUrl != null) {
                ImageIcon loadedImage = new ImageIcon(imageUrl);
                // Cek apakah gambar berhasil dimuat
                if (loadedImage.getIconWidth() > 0 && loadedImage.getIconHeight() > 0) {
                    splashImage = loadedImage;
                }
            }
        } catch (Exception e) {
            // Gambar tidak ditemukan, akan menggunakan default
        }
        
        // Jika gambar tidak ditemukan, gunakan gambar default yang dibuat programmatically
        if (splashImage == null) {
            splashImage = createDefaultSplashImage();
        }
        
        // Buat splash screen dengan gambar yang tersedia
        screen = new SplashScreen(splashImage);
        screen.setLocationRelativeTo(null);
        screen.setProgressMax(100);
        screen.setScreenVisible(true);
    }
    
    /**
     * Simulasi proses loading dengan pesan yang lebih informatif
     */
    private void simulateLoading() {
        String[] messages = {
            "Memuat konfigurasi...",
            "Menginisialisasi komponen...",
            "Memuat data...",
            "Menyiapkan antarmuka...",
            "Hampir selesai...",
            "Selesai!"
        };
        
        int totalSteps = messages.length;
        int delay = 800; // milliseconds per step
        
        for (int i = 0; i < totalSteps; i++) {
            int progress = (int) ((i + 1) * 100.0 / totalSteps);
            screen.setProgress(messages[i], progress);
            
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Sembunyikan splash screen
     */
    private void splashScreenDestruct() {
        screen.setScreenVisible(false);
        screen.dispose();
    }
    
    /**
     * Tampilkan aplikasi utama setelah splash screen selesai
     */
    private void showMainApplication() {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Aplikasi Utama - SplashScreen Demo");
            mainFrame.setSize(700, 500);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLocationRelativeTo(null);
            
            // Create menu bar
            JMenuBar menuBar = new JMenuBar();
            
            // File Menu
            JMenu fileMenu = new JMenu("File");
            fileMenu.setMnemonic(java.awt.event.KeyEvent.VK_F);
            
            JMenuItem logoutItem = new JMenuItem("Logout", java.awt.event.KeyEvent.VK_L);
            logoutItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_L, 
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
            logoutItem.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Apakah Anda yakin ingin logout?",
                    "Konfirmasi Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(
                        mainFrame,
                        "Anda telah logout. Terima kasih!",
                        "Logout Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    System.exit(0);
                }
            });
            fileMenu.add(logoutItem);
            
            fileMenu.addSeparator();
            
            JMenuItem exitItem = new JMenuItem("Keluar", java.awt.event.KeyEvent.VK_K);
            exitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_X, 
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
            exitItem.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Apakah Anda yakin ingin keluar dari aplikasi?",
                    "Konfirmasi Keluar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            });
            fileMenu.add(exitItem);
            
            // Help Menu
            JMenu helpMenu = new JMenu("Help");
            helpMenu.setMnemonic(java.awt.event.KeyEvent.VK_H);
            
            JMenuItem aboutItem = new JMenuItem("About", java.awt.event.KeyEvent.VK_A);
            aboutItem.addActionListener(e -> {
                String about = "<html>" +
                    "<div style='text-align: center;'>" +
                    "<font color='#0066CC' size='+2'><b>SplashScreen Demo</b></font><br>" +
                    "<font size='-1'>Version 2.0</font><br>" +
                    "<hr width='80%'>" +
                    "<p style='text-align: left;'>" +
                    "<b>Dibuat dengan:</b> Java Swing<br>" +
                    "<b>Platform:</b> " + System.getProperty("os.name") + "<br>" +
                    "<b>Java Version:</b> " + System.getProperty("java.version") + "<br><br>" +
                    "<font color='#009900'><b>Fitur:</b></font><br>" +
                    "✓ Splash Screen dengan Progress Bar<br>" +
                    "✓ Menu Logout & Keluar<br>" +
                    "✓ Dialog Konfirmasi<br>" +
                    "✓ Aplikasi Utama<br>" +
                    "</p>" +
                    "<hr width='80%'>" +
                    "<font color='#CC0000' size='-1'>© 2025 - Hak Cipta Dilindungi</font>" +
                    "</div></html>";
                
                JOptionPane.showMessageDialog(
                    mainFrame,
                    about,
                    "About",
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
            helpMenu.add(aboutItem);
            
            menuBar.add(fileMenu);
            menuBar.add(helpMenu);
            
            mainFrame.setJMenuBar(menuBar);
            
            // Create main content
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>" +
                "<h1 style='color: #0066CC;'>Selamat Datang!</h1>" +
                "<h2>Aplikasi berhasil dimuat</h2>" +
                "<p style='font-size: 14px;'>SplashScreen Demo</p>" +
                "<p style='font-size: 12px; color: #666;'>Versi 2.0</p>" +
                "<hr>" +
                "<p style='font-size: 12px;'>Gunakan menu <b>File → Logout</b> untuk logout</p>" +
                "<p style='font-size: 12px;'>atau <b>File → Keluar</b> untuk keluar dari aplikasi</p>" +
                "</div></html>", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            
            mainPanel.add(welcomeLabel, BorderLayout.CENTER);
            
            // Create button panel
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            
            JButton logoutButton = new JButton("Logout");
            logoutButton.setPreferredSize(new Dimension(120, 35));
            logoutButton.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Apakah Anda yakin ingin logout?",
                    "Konfirmasi Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(
                        mainFrame,
                        "Anda telah logout. Terima kasih!",
                        "Logout Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    System.exit(0);
                }
            });
            logoutButton.setFocusPainted(false);
            
            JButton exitButton = new JButton("Keluar");
            exitButton.setPreferredSize(new Dimension(120, 35));
            exitButton.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(
                    mainFrame,
                    "Apakah Anda yakin ingin keluar dari aplikasi?",
                    "Konfirmasi Keluar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            });
            exitButton.setFocusPainted(false);
            
            buttonPanel.add(logoutButton);
            buttonPanel.add(Box.createHorizontalStrut(10));
            buttonPanel.add(exitButton);
            
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            mainFrame.add(mainPanel);
            mainFrame.setVisible(true);
        });
    }
    
    /**
     * Membuat gambar splash screen default yang menarik secara programmatically
     */
    private ImageIcon createDefaultSplashImage() {
        BufferedImage img = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        
        // Enable antialiasing untuk kualitas lebih baik
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Background gradient yang menarik
        GradientPaint bgGradient = new GradientPaint(
            0, 0, new Color(25, 50, 100),
            0, 400, new Color(50, 100, 180)
        );
        g2d.setPaint(bgGradient);
        g2d.fillRect(0, 0, 600, 400);
        
        // Border dengan efek glow
        g2d.setColor(new Color(255, 255, 255, 200));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRoundRect(2, 2, 595, 395, 10, 10);
        
        // Inner border
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(5, 5, 589, 389, 8, 8);
        
        // Icon/Logo sederhana (lingkaran dengan simbol)
        int centerX = 300;
        int centerY = 150;
        int radius = 50;
        
        // Outer circle
        g2d.setColor(new Color(255, 255, 255, 180));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        
        // Inner circle
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.fillOval(centerX - radius + 10, centerY - radius + 10, radius * 2 - 20, radius * 2 - 20);
        
        // Simbol loading (3 titik)
        g2d.setColor(new Color(50, 100, 180));
        g2d.fillOval(centerX - 15, centerY - 5, 10, 10);
        g2d.fillOval(centerX - 5, centerY - 5, 10, 10);
        g2d.fillOval(centerX + 5, centerY - 5, 10, 10);
        
        // Title dengan shadow effect
        g2d.setFont(new Font("SansSerif", Font.BOLD, 42));
        FontMetrics fm = g2d.getFontMetrics();
        String title = "SplashScreen Demo";
        
        // Shadow
        g2d.setColor(new Color(0, 0, 0, 100));
        int shadowX = (600 - fm.stringWidth(title)) / 2 + 2;
        int shadowY = 250 + 2;
        g2d.drawString(title, shadowX, shadowY);
        
        // Main text
        g2d.setColor(Color.WHITE);
        int textX = (600 - fm.stringWidth(title)) / 2;
        int textY = 250;
        g2d.drawString(title, textX, textY);
        
        // Subtitle
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
        fm = g2d.getFontMetrics();
        String subtitle = "Versi 2.0 - Loading Application";
        int subX = (600 - fm.stringWidth(subtitle)) / 2;
        int subY = 285;
        g2d.setColor(new Color(220, 220, 255));
        g2d.drawString(subtitle, subX, subY);
        
        // Decorative lines
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(255, 255, 255, 150));
        g2d.drawLine(150, 310, 450, 310);
        g2d.drawLine(150, 315, 450, 315);
        
        // Copyright text
        g2d.setFont(new Font("SansSerif", Font.ITALIC, 12));
        fm = g2d.getFontMetrics();
        String copyright = "© 2025 - Java Swing Application";
        int copyX = (600 - fm.stringWidth(copyright)) / 2;
        int copyY = 350;
        g2d.setColor(new Color(200, 200, 255));
        g2d.drawString(copyright, copyX, copyY);
        
        g2d.dispose();
        return new ImageIcon(img);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        try {
            // Set Look and Feel sesuai sistem operasi
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Jalankan demo
        SwingUtilities.invokeLater(() -> {
            new SplashScreenDemo();
        });
    }
}
