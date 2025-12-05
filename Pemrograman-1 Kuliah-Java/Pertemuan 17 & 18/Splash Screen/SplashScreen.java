import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * SplashScreen - Class untuk membuat Splash Screen dengan Progress Bar
 * Versi yang diperbaiki dengan best practices Java
 * 
 * @author Anda
 * @version 2.0
 */
public class SplashScreen extends JWindow {
    
    // ===== CONSTANTS =====
    private static final int DEFAULT_PROGRESS_MAX = 100;
    
    // ===== COMPONENTS =====
    private BorderLayout borderLayout1 = new BorderLayout();
    private JLabel imageLabel = new JLabel();
    private JPanel southPanel = new JPanel();
    private FlowLayout southPanelFlowLayout = new FlowLayout();
    private JProgressBar progressBar = new JProgressBar();
    private ImageIcon imageIcon;

    /**
     * Constructor untuk membuat Splash Screen
     * @param imageIcon - gambar yang akan ditampilkan
     */
    public SplashScreen(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
        try {
            initComponents();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error initializing Splash Screen: " + ex.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Constructor alternatif dengan path gambar
     * @param imagePath - path ke file gambar
     */
    public SplashScreen(String imagePath) {
        this(new ImageIcon(imagePath));
    }

    /**
     * Inisialisasi komponen
     */
    private void initComponents() throws Exception {
        // Set icon ke label
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // Layout utama
        this.getContentPane().setLayout(borderLayout1);
        
        // Panel bawah untuk progress bar
        southPanel.setLayout(southPanelFlowLayout);
        southPanel.setBackground(Color.BLACK);
        southPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Setup progress bar
        progressBar.setStringPainted(true);
        progressBar.setString("Loading...");
        progressBar.setForeground(new Color(0, 150, 255));
        progressBar.setBackground(Color.WHITE);
        progressBar.setBorderPainted(true);
        progressBar.setMaximum(DEFAULT_PROGRESS_MAX);
        progressBar.setMinimum(0);
        progressBar.setValue(0);
        
        // Tambahkan komponen
        this.getContentPane().add(imageLabel, BorderLayout.CENTER);
        this.getContentPane().add(southPanel, BorderLayout.SOUTH);
        southPanel.add(progressBar);
        
        // Ukuran sesuai gambar
        this.pack();
        
        // Center di layar
        this.setLocationRelativeTo(null);
    }

    /**
     * Set maksimum nilai progress bar
     * @param maxProgress - nilai maksimum progress bar
     */
    public void setProgressMax(int maxProgress) {
        if (maxProgress < 0) {
            throw new IllegalArgumentException("Maximum progress cannot be negative");
        }
        progressBar.setMaximum(maxProgress);
    }

    /**
     * Set progress bar dan pesan
     * @param message - pesan yang akan ditampilkan
     * @param progress - nilai progress (0 sampai maksimum)
     */
    public void setProgress(String message, int progress) {
        final int theProgress = Math.max(0, Math.min(progress, progressBar.getMaximum()));
        final String theMessage = message != null ? message : "";
        
        // Update progress bar di Event Dispatch Thread
        EventQueue.invokeLater(() -> {
            progressBar.setValue(theProgress);
            setMessage(theMessage);
        });
    }
    
    /**
     * Set progress bar tanpa pesan
     * @param progress - nilai progress (0 sampai maksimum)
     */
    public void setProgress(int progress) {
        setProgress(null, progress);
    }

    /**
     * Tampilkan atau sembunyikan splash screen
     * @param visible - true untuk menampilkan, false untuk menyembunyikan
     */
    public void setScreenVisible(boolean visible) {
        final boolean isVisible = visible;
        EventQueue.invokeLater(() -> {
            setVisible(isVisible);
            if (isVisible) {
                // Center di layar saat ditampilkan
                setLocationRelativeTo(null);
            }
        });
    }
    
    /**
     * Tampilkan splash screen
     */
    public void showSplash() {
        setScreenVisible(true);
    }
    
    /**
     * Sembunyikan splash screen
     */
    public void hideSplash() {
        setScreenVisible(false);
    }
    
    /**
     * Tampilkan splash screen dengan delay otomatis
     * @param delayMillis - delay dalam milliseconds sebelum splash screen ditutup
     */
    public void showSplashWithDelay(int delayMillis) {
        showSplash();
        new Thread(() -> {
            try {
                Thread.sleep(delayMillis);
                hideSplash();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                hideSplash();
            }
        }).start();
    }

    /**
     * Set pesan pada progress bar
     * @param message - pesan yang akan ditampilkan
     */
    private void setMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            progressBar.setStringPainted(false);
        } else {
            progressBar.setStringPainted(true);
            progressBar.setString(message);
        }
    }
    
    /**
     * Get nilai progress saat ini
     * @return nilai progress saat ini
     */
    public int getProgress() {
        return progressBar.getValue();
    }
    
    /**
     * Get nilai maksimum progress
     * @return nilai maksimum progress
     */
    public int getMaxProgress() {
        return progressBar.getMaximum();
    }
    
    /**
     * Reset progress bar ke 0
     */
    public void resetProgress() {
        setProgress(null, 0);
    }
    
    /**
     * Set warna progress bar
     * @param color - warna untuk progress bar
     */
    public void setProgressBarColor(Color color) {
        if (color != null) {
            progressBar.setForeground(color);
        }
    }
    
    /**
     * Set warna background progress bar
     * @param color - warna background untuk progress bar
     */
    public void setProgressBarBackground(Color color) {
        if (color != null) {
            progressBar.setBackground(color);
        }
    }
    
    /**
     * Main method untuk demo SplashScreen
     */
    public static void main(String[] args) {
        // Buat gambar splash screen sederhana
        ImageIcon splashImage = createDefaultSplashImage();
        
        // Buat splash screen
        SplashScreen splash = new SplashScreen(splashImage);
        splash.setProgressMax(100);
        
        // Tampilkan splash screen
        splash.showSplash();
        
        // Simulasi loading dengan progress
        simulateLoading(splash);
        
        // Tutup splash screen setelah loading selesai
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        splash.hideSplash();
        
        // Tampilkan aplikasi utama dengan menu logout/keluar
        SwingUtilities.invokeLater(() -> {
            createMainApplication();
        });
    }
    
    /**
     * Membuat aplikasi utama dengan menu logout/keluar
     */
    private static void createMainApplication() {
        JFrame mainFrame = new JFrame("Aplikasi Utama");
        mainFrame.setSize(700, 500);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        
        // Handle window closing
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logout(mainFrame);
            }
        });
        
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem logoutItem = new JMenuItem("Logout", KeyEvent.VK_L);
        logoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        logoutItem.addActionListener(e -> logout(mainFrame));
        fileMenu.add(logoutItem);
        
        fileMenu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Keluar", KeyEvent.VK_K);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(e -> exitApplication(mainFrame));
        fileMenu.add(exitItem);
        
        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutItem.addActionListener(e -> showAbout(mainFrame));
        helpMenu.add(aboutItem);
        
        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        mainFrame.setJMenuBar(menuBar);
        
        // Create main content
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>" +
            "<h1 style='color: #0066CC;'>Selamat Datang!</h1>" +
            "<h2>Aplikasi berhasil dimuat</h2>" +
            "<p style='font-size: 14px;'>Splash Screen Demo</p>" +
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
        logoutButton.addActionListener(e -> logout(mainFrame));
        logoutButton.setFocusPainted(false);
        
        JButton exitButton = new JButton("Keluar");
        exitButton.setPreferredSize(new Dimension(120, 35));
        exitButton.addActionListener(e -> exitApplication(mainFrame));
        exitButton.setFocusPainted(false);
        
        buttonPanel.add(logoutButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(exitButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
    
    /**
     * Method untuk logout - menampilkan konfirmasi dan menutup aplikasi
     */
    private static void logout(JFrame frame) {
        int option = JOptionPane.showConfirmDialog(
            frame,
            "Apakah Anda yakin ingin logout?",
            "Konfirmasi Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (option == JOptionPane.YES_OPTION) {
            // Tampilkan pesan logout
            JOptionPane.showMessageDialog(
                frame,
                "Anda telah logout. Terima kasih!",
                "Logout Berhasil",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Tutup aplikasi
            frame.dispose();
            System.exit(0);
        }
    }
    
    /**
     * Method untuk keluar dari aplikasi
     */
    private static void exitApplication(JFrame frame) {
        int option = JOptionPane.showConfirmDialog(
            frame,
            "Apakah Anda yakin ingin keluar dari aplikasi?",
            "Konfirmasi Keluar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (option == JOptionPane.YES_OPTION) {
            frame.dispose();
            System.exit(0);
        }
    }
    
    /**
     * Method untuk menampilkan dialog About
     */
    private static void showAbout(JFrame parent) {
        String about = "<html>" +
            "<div style='text-align: center;'>" +
            "<font color='#0066CC' size='+2'><b>Splash Screen Demo</b></font><br>" +
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
            parent,
            about,
            "About",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Simulasi proses loading dengan update progress
     */
    private static void simulateLoading(SplashScreen splash) {
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
            splash.setProgress(messages[i], progress);
            
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    /**
     * Membuat gambar splash screen default jika tidak ada file gambar
     */
    private static ImageIcon createDefaultSplashImage() {
        BufferedImage img = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        
        // Enable antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Background gradient
        GradientPaint bgGradient = new GradientPaint(0, 0, new Color(30, 60, 120),
                                                     0, 300, new Color(60, 120, 200));
        g2d.setPaint(bgGradient);
        g2d.fillRect(0, 0, 500, 300);
        
        // Border
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(0, 0, 499, 299);
        
        // Title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 36));
        FontMetrics fm = g2d.getFontMetrics();
        String title = "Aplikasi Saya";
        int x = (500 - fm.stringWidth(title)) / 2;
        int y = 120;
        g2d.drawString(title, x, y);
        
        // Subtitle
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 18));
        fm = g2d.getFontMetrics();
        String subtitle = "Versi 2.0";
        x = (500 - fm.stringWidth(subtitle)) / 2;
        y = 160;
        g2d.drawString(subtitle, x, y);
        
        // Decorative elements
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(100, 200, 400, 200);
        g2d.drawLine(100, 210, 400, 210);
        
        g2d.dispose();
        return new ImageIcon(img);
    }
}
