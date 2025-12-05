package ParameterForm;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * MDIForm - Multiple Document Interface dengan desain modern dan fitur lengkap
 * 
 * Fitur:
 * - JDesktopPane untuk menampung multiple internal frames
 * - Menu bar dengan berbagai pilihan frame
 * - Internal frames dengan desain menarik
 * - Cascading & tiling windows
 * - Status bar
 * - Modern look & feel
 * 
 * @author Developer
 * @version 3.0
 */
public class MDIForm extends javax.swing.JFrame {
    
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile, jMenuWindow, jMenuHelp;
    private javax.swing.JMenuItem mnuNewDocument, mnuNewChart, mnuNewTable, mnuCascade, mnuTile, mnuExit;
    private javax.swing.JMenuItem mnuAbout, mnuHelp;
    private JLabel statusLabel;
    private int frameCount = 0;
    private Random random = new Random();

    /**
     * Constructor
     */
    public MDIForm() {
        initComponents();
        setTitle("MDI Application - Modern Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setIconImage(createAppIcon());
    }

    /**
     * Inisialisasi komponen GUI
     */
    private void initComponents() {
        // ===== MENU BAR =====
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuBar1.setBackground(new Color(240, 240, 240));
        
        // ===== MENU: FILE =====
        jMenuFile = new javax.swing.JMenu("File");
        jMenuFile.setMnemonic(KeyEvent.VK_F);
        
        mnuNewDocument = new javax.swing.JMenuItem("New Document");
        mnuNewDocument.setIcon(createSmallIcon("📄"));
        mnuNewDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        mnuNewDocument.addActionListener(evt -> openDocumentFrame());
        jMenuFile.add(mnuNewDocument);
        
        mnuNewChart = new javax.swing.JMenuItem("New Chart");
        mnuNewChart.setIcon(createSmallIcon("📊"));
        mnuNewChart.addActionListener(evt -> openChartFrame());
        jMenuFile.add(mnuNewChart);
        
        mnuNewTable = new javax.swing.JMenuItem("New Table");
        mnuNewTable.setIcon(createSmallIcon("📋"));
        mnuNewTable.addActionListener(evt -> openTableFrame());
        jMenuFile.add(mnuNewTable);
        
        jMenuFile.addSeparator();
        
        mnuExit = new javax.swing.JMenuItem("Exit");
        mnuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        mnuExit.addActionListener(evt -> confirmExit());
        jMenuFile.add(mnuExit);
        
        // ===== MENU: WINDOW =====
        jMenuWindow = new javax.swing.JMenu("Window");
        jMenuWindow.setMnemonic(KeyEvent.VK_W);
        
        mnuCascade = new javax.swing.JMenuItem("Cascade");
        mnuCascade.addActionListener(evt -> cascadeFrames());
        jMenuWindow.add(mnuCascade);
        
        mnuTile = new javax.swing.JMenuItem("Tile");
        mnuTile.addActionListener(evt -> tileFrames());
        jMenuWindow.add(mnuTile);
        
        // ===== MENU: HELP =====
        jMenuHelp = new javax.swing.JMenu("Help");
        jMenuHelp.setMnemonic(KeyEvent.VK_H);
        
        mnuHelp = new javax.swing.JMenuItem("Help Contents");
        mnuHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        mnuHelp.addActionListener(evt -> showHelp());
        jMenuHelp.add(mnuHelp);
        
        jMenuHelp.addSeparator();
        
        mnuAbout = new javax.swing.JMenuItem("About");
        mnuAbout.addActionListener(evt -> showAbout());
        jMenuHelp.add(mnuAbout);
        
        // ===== ADD MENU KE MENU BAR =====
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(jMenuWindow);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(jMenuHelp);
        setJMenuBar(jMenuBar1);

        // ===== DESKTOP PANE =====
        desktopPane = new javax.swing.JDesktopPane();
        desktopPane.setBackground(new Color(245, 245, 250));
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        // ===== STATUS BAR =====
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        statusPanel.add(statusLabel, BorderLayout.WEST);
        
        JLabel infoLabel = new JLabel("Open documents: 0");
        infoLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        statusPanel.add(infoLabel, BorderLayout.EAST);

        // ===== MAIN LAYOUT =====
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }

    /**
     * Buka frame dokumen baru
     */
    private void openDocumentFrame() {
        frameCount++;
        DocumentFrame doc = new DocumentFrame("Document " + frameCount);
        desktopPane.add(doc);
        doc.setLocation(frameCount * 20, frameCount * 20);
        doc.setVisible(true);
        updateStatus("Opened Document " + frameCount);
    }

    /**
     * Buka frame chart baru
     */
    private void openChartFrame() {
        frameCount++;
        ChartFrame chart = new ChartFrame("Chart " + frameCount);
        desktopPane.add(chart);
        chart.setLocation(frameCount * 20, frameCount * 20);
        chart.setVisible(true);
        updateStatus("Opened Chart " + frameCount);
    }

    /**
     * Buka frame table baru
     */
    private void openTableFrame() {
        frameCount++;
        TableFrame table = new TableFrame("Table " + frameCount);
        desktopPane.add(table);
        table.setLocation(frameCount * 20, frameCount * 20);
        table.setVisible(true);
        updateStatus("Opened Table " + frameCount);
    }

    /**
     * Cascade windows
     */
    private void cascadeFrames() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        int x = 0, y = 0;
        for (JInternalFrame frame : frames) {
            frame.setLocation(x, y);
            x += 30;
            y += 30;
            try { frame.setSelected(true); } catch (Exception e) {}
        }
        updateStatus("Frames cascaded");
    }

    /**
     * Tile windows
     */
    private void tileFrames() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        if (frames.length == 0) return;

        int cols = (int) Math.sqrt(frames.length);
        int rows = (frames.length + cols - 1) / cols;
        int w = desktopPane.getWidth() / cols;
        int h = desktopPane.getHeight() / rows;

        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols && index < frames.length; col++) {
                frames[index].setLocation(col * w, row * h);
                frames[index].setSize(w, h);
                index++;
            }
        }
        updateStatus("Frames tiled");
    }

    /**
     * Update status bar
     */
    private void updateStatus(String message) {
        statusLabel.setText(message + " | Open frames: " + desktopPane.getAllFrames().length);
    }

    /**
     * Show help
     */
    private void showHelp() {
        JOptionPane.showMessageDialog(this,
            "MDI Application - Help\n\n" +
            "Features:\n" +
            "• Open multiple documents, charts, and tables\n" +
            "• Cascade and tile windows\n" +
            "• Resize, minimize, maximize internal frames\n" +
            "• Close individual frames\n\n" +
            "Shortcuts:\n" +
            "Ctrl+N - New Document\n" +
            "Ctrl+Q - Exit",
            "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show about dialog
     */
    private void showAbout() {
        String about = "<html><div style='text-align: center; font-family: SansSerif;'>" +
            "<h2 style='color: #0066CC;'>MDI Application</h2>" +
            "<p><b>Version 3.0</b></p>" +
            "<hr width='80%'>" +
            "<p>A modern Multiple Document Interface application</p>" +
            "<p style='font-size: 11px; color: #666;'>Built with Java Swing</p>" +
            "<p style='font-size: 11px;'>© 2025 Developer. All rights reserved.</p>" +
            "</div></html>";

        JOptionPane.showMessageDialog(this, about, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Confirm exit
     */
    private void confirmExit() {
        int option = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to exit?",
            "Exit Application",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.YES_OPTION) System.exit(0);
    }

    /**
     * Create app icon (16x16)
     */
    private Image createAppIcon() {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(new Color(0, 102, 204));
        g.fillRect(0, 0, 16, 16);
        g.setColor(Color.WHITE);
        g.drawString("M", 4, 12);
        g.dispose();
        return img;
    }

    /**
     * Create small icon (placeholder)
     */
    private Icon createSmallIcon(String emoji) {
        return new Icon() {
            public void paintIcon(Component c, Graphics g, int x, int y) {}
            public int getIconWidth() { return 16; }
            public int getIconHeight() { return 16; }
        };
    }

    /**
     * Internal Frame untuk Document
     */
    private static class DocumentFrame extends JInternalFrame {
        public DocumentFrame(String title) {
            super(title, true, true, true, true);
            setSize(400, 300);
            JTextArea textArea = new JTextArea();
            textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
            textArea.setMargin(new Insets(5, 5, 5, 5));
            add(new JScrollPane(textArea));
        }
    }

    /**
     * Internal Frame untuk Chart
     */
    private static class ChartFrame extends JInternalFrame {
        public ChartFrame(String title) {
            super(title, true, true, true, true);
            setSize(400, 300);
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    int w = getWidth(), h = getHeight();
                    Random r = new Random(42);
                    
                    g2.setColor(new Color(200, 220, 255));
                    for (int i = 0; i < 5; i++) {
                        int barH = r.nextInt(h - 50) + 20;
                        int x = 50 + i * 60;
                        g2.fillRect(x, h - barH - 30, 40, barH);
                        g2.setColor(new Color(0, 102, 204));
                        g2.drawRect(x, h - barH - 30, 40, barH);
                    }
                    
                    g2.setColor(Color.BLACK);
                    g2.setFont(new Font("SansSerif", Font.BOLD, 14));
                    g2.drawString("Sample Chart", 20, 30);
                }
            };
            add(panel);
        }
    }

    /**
     * Internal Frame untuk Table
     */
    private static class TableFrame extends JInternalFrame {
        public TableFrame(String title) {
            super(title, true, true, true, true);
            setSize(400, 300);
            
            String[] columns = {"Name", "Age", "City", "Occupation"};
            Object[][] data = {
                {"Alice Johnson", 28, "New York", "Engineer"},
                {"Bob Smith", 35, "Los Angeles", "Designer"},
                {"Carol Davis", 42, "Chicago", "Manager"},
                {"David Lee", 31, "Houston", "Developer"},
                {"Emma Wilson", 26, "Phoenix", "Analyst"}
            };
            
            JTable table = new JTable(data, columns);
            table.setRowHeight(25);
            table.getTableHeader().setBackground(new Color(0, 102, 204));
            table.getTableHeader().setForeground(Color.WHITE);
            add(new JScrollPane(table));
        }
    }

    /**
     * Main method
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            MDIForm frame = new MDIForm();
            frame.setVisible(true);
            
            // Buka sample frame pertama
            frame.openDocumentFrame();
        });
    }
}
