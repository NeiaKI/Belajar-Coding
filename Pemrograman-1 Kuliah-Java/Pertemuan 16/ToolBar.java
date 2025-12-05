import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ToolBar - Aplikasi text editor dengan toolbar yang lengkap
 * Versi yang diperbaiki dengan best practices Java
 * 
 * @author Anda
 * @version 2.0
 */
public class ToolBar extends JFrame {
    
    // ===== CONSTANTS =====
    private static final String APP_TITLE = "Toolbar Demo";
    private static final String APP_VERSION = "2.0";
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int ICON_SIZE = 32;
    private static final String DEFAULT_FONT_NAME = "Monospaced";
    private static final int DEFAULT_FONT_SIZE = 14;
    
    // ===== ENUM FOR ICON TYPES =====
    private enum IconType {
        NEW, OPEN, SAVE, PRINT, CUT, COPY, PASTE, UNDO, REDO, FIND, ABOUT, EXIT
    }
    
    // ===== COMPONENTS =====
    private JToolBar toolBar;
    private JPanel mainPanel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JLabel statusBar;
    
    private JButton btnNew, btnOpen, btnSave, btnPrint;
    private JButton btnCut, btnCopy, btnPaste;
    private JButton btnUndo, btnRedo, btnFind;
    private JButton btnAbout, btnExit;
    
    // ===== STATE MANAGEMENT =====
    private File currentFile;
    private boolean isModified = false;
    private String originalContent = "";

    public ToolBar() {
        initializeApplication();
    }
    
    private void initializeApplication() {
        initComponents();
        setupWindow();
        setupEventHandlers();
        updateTitle();
        updateStatusBar("Ready");
    }
    
    private void setupWindow() {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        
        // Handle window closing with confirmation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
    }
    
    private void setupEventHandlers() {
        // Update status bar on text area changes
        textArea.addCaretListener(e -> {
            int caretPos = textArea.getCaretPosition();
            int line = 1;
            int col = 1;
            try {
                int caretLine = textArea.getLineOfOffset(caretPos);
                line = caretLine + 1;
                col = caretPos - textArea.getLineStartOffset(caretLine) + 1;
            } catch (Exception ex) {
                // Ignore
            }
            updateStatusBar(String.format("Line %d, Column %d | %d characters", 
                line, col, textArea.getText().length()));
        });
        
        // Track changes
        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                markAsModified();
            }
            
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                markAsModified();
            }
            
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                markAsModified();
            }
        });
    }

    private void initComponents() {
        // Initialize main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        // Initialize text area
        textArea = new JTextArea();
        textArea.setFont(new Font(DEFAULT_FONT_NAME, Font.PLAIN, DEFAULT_FONT_SIZE));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setTabSize(4);
        textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Initialize toolbar
        initToolBar();
        
        // Initialize status bar
        initStatusBar();
        
        // Setup main layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(statusBar, BorderLayout.SOUTH);
    }
    
    private void initToolBar() {
        toolBar = new JToolBar("Main Toolbar");
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        toolBar.setBorder(BorderFactory.createEtchedBorder());
        toolBar.setMargin(new Insets(5, 5, 5, 5));

        // File operations group
        btnNew = createToolBarButton(IconType.NEW, "New File (Ctrl+N)", e -> newFile());
        toolBar.add(btnNew);

        btnOpen = createToolBarButton(IconType.OPEN, "Open File (Ctrl+O)", e -> openFile());
        toolBar.add(btnOpen);

        btnSave = createToolBarButton(IconType.SAVE, "Save File (Ctrl+S)", e -> saveFile());
        toolBar.add(btnSave);

        btnPrint = createToolBarButton(IconType.PRINT, "Print (Ctrl+P)", e -> printFile());
        toolBar.add(btnPrint);

        toolBar.addSeparator();

        // Edit operations group
        btnCut = createToolBarButton(IconType.CUT, "Cut (Ctrl+X)", e -> textArea.cut());
        toolBar.add(btnCut);

        btnCopy = createToolBarButton(IconType.COPY, "Copy (Ctrl+C)", e -> textArea.copy());
        toolBar.add(btnCopy);

        btnPaste = createToolBarButton(IconType.PASTE, "Paste (Ctrl+V)", e -> textArea.paste());
        toolBar.add(btnPaste);

        toolBar.addSeparator();

        // Undo/Redo group
        btnUndo = createToolBarButton(IconType.UNDO, "Undo (Ctrl+Z)", e -> {
            if (textArea.getDocument() instanceof javax.swing.text.DefaultStyledDocument) {
                // For styled documents
            } else {
                // For plain text, we'll use a simple approach
                try {
                    javax.swing.undo.UndoManager undoManager = new javax.swing.undo.UndoManager();
                    if (undoManager.canUndo()) {
                        undoManager.undo();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Cannot undo: " + ex.getMessage());
                }
            }
        });
        toolBar.add(btnUndo);

        btnRedo = createToolBarButton(IconType.REDO, "Redo (Ctrl+Y)", e -> {
            // Similar to undo
            JOptionPane.showMessageDialog(this, "Redo feature coming soon");
        });
        toolBar.add(btnRedo);

        toolBar.addSeparator();

        // Find
        btnFind = createToolBarButton(IconType.FIND, "Find (Ctrl+F)", e -> findText());
        toolBar.add(btnFind);

        // Add flexible space
        toolBar.add(Box.createHorizontalGlue());

        toolBar.addSeparator();

        // Help and Exit
        btnAbout = createToolBarButton(IconType.ABOUT, "About", e -> showAbout());
        toolBar.add(btnAbout);

        btnExit = createToolBarButton(IconType.EXIT, "Exit (Ctrl+X)", e -> exitApplication());
        toolBar.add(btnExit);
    }
    
    private JButton createToolBarButton(IconType iconType, String tooltip, ActionListener listener) {
        JButton button = new JButton(createIcon(iconType));
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        button.setMargin(new Insets(2, 2, 2, 2));
        button.addActionListener(listener);
        return button;
    }
    
    private void initStatusBar() {
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        statusBar.setPreferredSize(new Dimension(getWidth(), 25));
        statusBar.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.setFont(new Font("SansSerif", Font.PLAIN, 11));
    }
    
    // ===== FILE OPERATIONS =====
    
    private void newFile() {
        if (checkUnsavedChanges()) {
            textArea.setText("");
            currentFile = null;
            isModified = false;
            originalContent = "";
            updateTitle();
            updateStatusBar("New file created");
        }
    }
    
    private void openFile() {
        if (!checkUnsavedChanges()) {
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Open File");
        
        if (currentFile != null) {
            fileChooser.setCurrentDirectory(currentFile.getParentFile());
        }
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            loadFile(file);
        }
    }
    
    private void loadFile(File file) {
        if (!file.exists() || !file.canRead()) {
            showError("Error", "Cannot read file: " + file.getName());
            return;
        }
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), 
                                      StandardCharsets.UTF_8);
            textArea.setText(content);
            currentFile = file;
            originalContent = content;
            isModified = false;
            updateTitle();
            updateStatusBar("File loaded: " + file.getName());
        } catch (IOException ex) {
            showError("Error Opening File", 
                "Failed to open file:\n" + ex.getMessage());
        }
    }
    
    private void saveFile() {
        if (currentFile == null) {
            saveFileAs();
        } else {
            saveToFile(currentFile);
        }
    }
    
    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File As");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (currentFile != null) {
            fileChooser.setCurrentDirectory(currentFile.getParentFile());
            fileChooser.setSelectedFile(currentFile);
        }
        
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            if (file.exists()) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "File already exists. Overwrite?",
                    "Confirm Overwrite",
                    JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            saveToFile(file);
        }
    }
    
    private void saveToFile(File file) {
        try {
            Files.write(Paths.get(file.getAbsolutePath()), 
                       textArea.getText().getBytes(StandardCharsets.UTF_8));
            
            currentFile = file;
            originalContent = textArea.getText();
            isModified = false;
            updateTitle();
            updateStatusBar("File saved: " + file.getName());
            
            JOptionPane.showMessageDialog(this,
                "File saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            showError("Error Saving File",
                "Failed to save file:\n" + ex.getMessage());
        }
    }
    
    private boolean checkUnsavedChanges() {
        if (isModified) {
            int result = JOptionPane.showConfirmDialog(this,
                "You have unsaved changes. Do you want to save them?",
                "Unsaved Changes",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (result == JOptionPane.YES_OPTION) {
                saveFile();
                return !isModified;
            } else if (result == JOptionPane.CANCEL_OPTION) {
                return false;
            }
        }
        return true;
    }
    
    private void printFile() {
        try {
            boolean printResult = textArea.print();
            if (printResult) {
                updateStatusBar("Print job sent to printer");
            } else {
                updateStatusBar("Print job cancelled");
            }
        } catch (java.awt.print.PrinterException ex) {
            showError("Print Error", "Failed to print:\n" + ex.getMessage());
        }
    }
    
    private void findText() {
        String searchText = JOptionPane.showInputDialog(this, 
            "Enter text to find:", "Find", JOptionPane.QUESTION_MESSAGE);
        
        if (searchText != null && !searchText.isEmpty()) {
            String content = textArea.getText();
            int index = content.indexOf(searchText, textArea.getCaretPosition());
            
            if (index >= 0) {
                textArea.setCaretPosition(index);
                textArea.select(index, index + searchText.length());
                textArea.grabFocus();
                updateStatusBar("Found: " + searchText);
            } else {
                // Try from beginning
                index = content.indexOf(searchText);
                if (index >= 0) {
                    textArea.setCaretPosition(index);
                    textArea.select(index, index + searchText.length());
                    textArea.grabFocus();
                    updateStatusBar("Found: " + searchText + " (from beginning)");
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Text not found: " + searchText,
                        "Find",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    private void exitApplication() {
        if (checkUnsavedChanges()) {
            System.exit(0);
        }
    }
    
    // ===== UI UPDATES =====
    
    private void markAsModified() {
        if (!isModified) {
            isModified = true;
            updateTitle();
        }
    }
    
    private void updateTitle() {
        String title = APP_TITLE;
        if (currentFile != null) {
            title += " - " + currentFile.getName();
        } else {
            title += " - Untitled";
        }
        if (isModified) {
            title += " *";
        }
        setTitle(title);
    }
    
    private void updateStatusBar(String message) {
        statusBar.setText(message);
    }
    
    // ===== ICON CREATION =====
    
    /**
     * Creates an icon using Graphics2D with antialiasing for better quality
     */
    private ImageIcon createIcon(IconType type) {
        BufferedImage img = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        // Enable antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Background with gradient
        GradientPaint bgGradient = new GradientPaint(0, 0, new Color(245, 245, 250),
                                                     0, ICON_SIZE, new Color(230, 230, 240));
        g2d.setPaint(bgGradient);
        g2d.fillRoundRect(0, 0, ICON_SIZE, ICON_SIZE, 4, 4);
        
        // Border
        g2d.setColor(new Color(180, 180, 180));
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(1, 1, ICON_SIZE - 3, ICON_SIZE - 3, 4, 4);
        
        // Icon color
        g2d.setColor(new Color(50, 100, 180));
        g2d.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        switch(type) {
            case NEW:
                // Document with plus sign
                g2d.drawRoundRect(6, 5, 18, 24, 3, 3);
                g2d.drawLine(15, 12, 15, 20);
                g2d.drawLine(11, 16, 19, 16);
                break;
                
            case OPEN:
                // Open folder
                int[] folderX = {5, 5, 27, 27};
                int[] folderY = {14, 8, 8, 26};
                g2d.fillPolygon(folderX, folderY, 4);
                g2d.setColor(new Color(245, 245, 250));
                g2d.drawLine(5, 8, 27, 8);
                g2d.setColor(new Color(50, 100, 180));
                g2d.drawPolygon(folderX, folderY, 4);
                break;
                
            case SAVE:
                // Floppy disk
                g2d.fillRoundRect(6, 5, 20, 26, 3, 3);
                g2d.setColor(new Color(245, 245, 250));
                g2d.fillRect(7, 5, 18, 10);
                g2d.setColor(new Color(50, 100, 180));
                g2d.drawRoundRect(6, 5, 20, 26, 3, 3);
                g2d.drawRect(11, 18, 10, 10);
                break;
                
            case PRINT:
                // Printer
                g2d.fillRoundRect(5, 12, 22, 16, 3, 3);
                g2d.setColor(new Color(245, 245, 250));
                g2d.fillRect(8, 10, 16, 2);
                g2d.fillRect(8, 18, 16, 2);
                g2d.setColor(new Color(50, 100, 180));
                g2d.drawRoundRect(5, 12, 22, 16, 3, 3);
                g2d.drawLine(10, 6, 10, 12);
                g2d.drawLine(22, 6, 22, 12);
                break;
                
            case CUT:
                // Scissors
                g2d.drawOval(6, 6, 8, 8);
                g2d.drawOval(18, 6, 8, 8);
                g2d.drawLine(14, 10, 18, 14);
                g2d.drawLine(14, 14, 18, 10);
                g2d.drawLine(10, 20, 22, 20);
                break;
                
            case COPY:
                // Two overlapping documents
                g2d.drawRoundRect(6, 5, 16, 20, 2, 2);
                g2d.drawRoundRect(10, 8, 16, 20, 2, 2);
                break;
                
            case PASTE:
                // Clipboard
                g2d.drawRoundRect(8, 4, 16, 20, 2, 2);
                g2d.fillRect(10, 4, 12, 4);
                g2d.setColor(new Color(50, 100, 180));
                for (int i = 0; i < 3; i++) {
                    g2d.drawLine(12, 12 + i * 4, 20, 12 + i * 4);
                }
                break;
                
            case UNDO:
                // Curved arrow pointing left
                g2d.drawArc(8, 8, 12, 12, 45, 270);
                g2d.drawLine(8, 14, 4, 10);
                g2d.drawLine(8, 14, 4, 18);
                break;
                
            case REDO:
                // Curved arrow pointing right
                g2d.drawArc(12, 8, 12, 12, 135, 270);
                g2d.drawLine(24, 14, 28, 10);
                g2d.drawLine(24, 14, 28, 18);
                break;
                
            case FIND:
                // Magnifying glass
                g2d.drawOval(8, 8, 12, 12);
                g2d.drawLine(18, 18, 24, 24);
                break;
                
            case EXIT:
                // Door with arrow
                g2d.fillRect(7, 6, 14, 24);
                g2d.setColor(new Color(245, 245, 250));
                g2d.fillOval(18, 14, 6, 6);
                g2d.setColor(new Color(50, 100, 180));
                g2d.drawRect(7, 6, 14, 24);
                g2d.drawLine(21, 18, 27, 18);
                g2d.drawLine(25, 14, 27, 18);
                g2d.drawLine(25, 22, 27, 18);
                break;
                
            case ABOUT:
                // Information icon (i)
                g2d.fillOval(14, 8, 4, 4);
                g2d.drawLine(16, 14, 16, 26);
                break;
        }
        
        g2d.dispose();
        return new ImageIcon(img);
    }
    
    // ===== DIALOGS =====
    
    private void showAbout() {
        // Create custom About dialog
        JDialog aboutDialog = new JDialog(this, "About " + APP_TITLE, true);
        aboutDialog.setSize(450, 400);
        aboutDialog.setLocationRelativeTo(this);
        aboutDialog.setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.WHITE);
        
        // Top panel with icon and title
        JPanel topPanel = new JPanel(new BorderLayout(15, 10));
        topPanel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(createLargeIcon());
        iconLabel.setBorder(new EmptyBorder(0, 0, 0, 15));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(APP_TITLE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        
        JLabel versionLabel = new JLabel("Version " + APP_VERSION);
        versionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        versionLabel.setForeground(Color.GRAY);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(versionLabel, BorderLayout.SOUTH);
        
        topPanel.add(iconLabel, BorderLayout.WEST);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        
        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        JLabel descLabel = new JLabel("<html><div style='text-align: center; width: 350px;'>" +
            "Aplikasi text editor dengan toolbar lengkap<br>" +
            "untuk mengedit dan mengelola file teks.</div></html>");
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        infoPanel.add(descLabel);
        
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(new JSeparator());
        infoPanel.add(Box.createVerticalStrut(15));
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        addInfoRow(detailsPanel, "Dibuat dengan:", "Java Swing");
        addInfoRow(detailsPanel, "Platform:", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        addInfoRow(detailsPanel, "Java Version:", System.getProperty("java.version"));
        addInfoRow(detailsPanel, "Author:", "Anda");
        
        infoPanel.add(detailsPanel);
        infoPanel.add(Box.createVerticalStrut(15));
        
        JLabel featuresLabel = new JLabel("<html><b>Fitur:</b></html>");
        featuresLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        featuresLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        featuresLabel.setForeground(new Color(0, 153, 0));
        infoPanel.add(featuresLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        
        String[] features = {
            "✓ New, Open, Save, Print",
            "✓ Cut, Copy, Paste",
            "✓ Undo, Redo",
            "✓ Find Text",
            "✓ Toolbar dengan Icon",
            "✓ Status Bar",
            "✓ Unsaved Changes Detection"
        };
        
        for (String feature : features) {
            JLabel featureLabel = new JLabel(feature);
            featureLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            featureLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            infoPanel.add(featureLabel);
        }
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JLabel copyrightLabel = new JLabel("© 2025 - Hak Cipta Dilindungi");
        copyrightLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        copyrightLabel.setForeground(new Color(204, 0, 0));
        bottomPanel.add(copyrightLabel);
        
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(80, 30));
        okButton.addActionListener(e -> aboutDialog.dispose());
        okButton.setFocusPainted(false);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        aboutDialog.add(mainPanel, BorderLayout.CENTER);
        aboutDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        aboutDialog.getRootPane().setDefaultButton(okButton);
        aboutDialog.getRootPane().registerKeyboardAction(
            e -> aboutDialog.dispose(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        aboutDialog.setVisible(true);
    }
    
    private void addInfoRow(JPanel panel, String label, String value) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel labelComp = new JLabel("<html><b>" + label + "</b></html>");
        labelComp.setFont(new Font("SansSerif", Font.PLAIN, 11));
        labelComp.setPreferredSize(new Dimension(120, 20));
        
        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("SansSerif", Font.PLAIN, 11));
        
        row.add(labelComp);
        row.add(valueComp);
        panel.add(row);
    }
    
    private ImageIcon createLargeIcon() {
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        GradientPaint bgGradient = new GradientPaint(0, 0, new Color(245, 245, 250),
                                                     0, 64, new Color(230, 230, 240));
        g2d.setPaint(bgGradient);
        g2d.fillRoundRect(0, 0, 64, 64, 8, 8);
        
        g2d.setColor(new Color(180, 180, 180));
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.drawRoundRect(1, 1, 62, 62, 8, 8);
        
        g2d.setColor(new Color(50, 100, 180));
        g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        // Toolbar icon representation
        g2d.drawRoundRect(12, 8, 40, 48, 4, 4);
        g2d.setStroke(new BasicStroke(2.0f));
        for (int i = 0; i < 5; i++) {
            int y = 20 + i * 8;
            g2d.drawLine(18, y, 46, y);
        }
        
        g2d.dispose();
        return new ImageIcon(img);
    }
    
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, 
            JOptionPane.ERROR_MESSAGE);
    }
    
    // ===== MAIN METHOD =====
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default look and feel
        }
        
        SwingUtilities.invokeLater(() -> {
            ToolBar app = new ToolBar();
            app.setVisible(true);
        });
    }
}
