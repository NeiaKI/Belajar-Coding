import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Program demo Input Output dengan JFileChooser
 * Fitur: Buka file, edit, dan simpan dengan dialog file chooser
 */
public class IOFileChooserDemo extends JFrame {
    // Komponen GUI
    private JTextArea txaIsiBerkas;
    private JButton btnBuka;
    private JButton btnSaveAs;
    private JButton btnSimpan;
    private JButton btnKeluar;
    private JLabel lblStatus;
    
    // Variabel untuk menyimpan file yang dipilih
    private File berkas = null;

    public IOFileChooserDemo() {
        initComponents();
        setTitle("Form Input Output dengan JFileChooser Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Inisialisasi komponen GUI
     */
    private void initComponents() {
        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel tombol (Utara)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        btnBuka = new JButton("Buka");
        btnBuka.addActionListener(e -> btnBukaActionPerformed());

        btnSaveAs = new JButton("Save As");
        btnSaveAs.addActionListener(e -> btnSaveAsActionPerformed());

        btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> btnSimpanActionPerformed());

        btnKeluar = new JButton("Keluar");
        btnKeluar.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnBuka);
        buttonPanel.add(btnSaveAs);
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnKeluar);

        // Text Area
        txaIsiBerkas = new JTextArea();
        txaIsiBerkas.setEditable(false);
        txaIsiBerkas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txaIsiBerkas.setLineWrap(true);
        txaIsiBerkas.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txaIsiBerkas);

        // Panel status (Selatan)
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblStatus = new JLabel("Belum ada file yang dibuka");
        lblStatus.setForeground(Color.BLUE);
        statusPanel.add(lblStatus);

        // Susun layout
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    /**
     * Event handler untuk tombol Buka
     * Menampilkan dialog pemilihan file
     */
    private void btnBukaActionPerformed() {
        // Buat JFileChooser
        JFileChooser fc = new JFileChooser();
        
        // Set untuk hanya memilih file (bukan folder)
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Buat filter untuk file txt dan log
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Text Files (*.txt, *.log)", "txt", "log");
        fc.setFileFilter(filter);
        
        // Disable multi-selection
        fc.setMultiSelectionEnabled(false);
        
        // Set direktori awal
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

        // Tampilkan dialog Open
        int returnVal = fc.showOpenDialog(this);

        // Jika user klik OK
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            berkas = fc.getSelectedFile();
            bacaFile(berkas);
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this,
                "Pembukaan file dibatalkan",
                "INFO",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Method untuk membaca file yang dipilih
     */
    private void bacaFile(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;

            // Baca setiap baris
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            // Tampilkan ke text area
            String content = sb.toString();
            if (content.isEmpty()) {
                txaIsiBerkas.setText("(File kosong)");
                txaIsiBerkas.setForeground(Color.GRAY);
            } else {
                txaIsiBerkas.setText(content);
                txaIsiBerkas.setEditable(true);
                txaIsiBerkas.setForeground(Color.BLACK);
            }

            // Update status
            lblStatus.setText("✓ File dibuka: " + file.getAbsolutePath());
            lblStatus.setForeground(Color.GREEN);
            setTitle("Form Input Output dengan JFileChooser - " + file.getName());

            System.out.println("✓ File berhasil dibaca: " + file.getName());

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "File tidak ditemukan!\n" + ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("✗ File tidak ditemukan: " + ex.getMessage());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi error membaca file!\n" + ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("✗ Error I/O: " + ex.getMessage());

        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.out.println("✗ Error menutup file: " + e.getMessage());
            }
        }
    }

    /**
     * Event handler untuk tombol Save As
     * Simpan dengan nama file baru
     */
    private void btnSaveAsActionPerformed() {
        JFileChooser fc = new JFileChooser();
        
        // Set untuk hanya file
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Buat filter
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Text Files (*.txt)", "txt");
        fc.setFileFilter(filter);
        
        // Set default filename
        if (berkas != null) {
            fc.setSelectedFile(berkas);
        } else {
            fc.setSelectedFile(new File("file_baru.txt"));
        }

        // Tampilkan dialog Save As
        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            
            // Cek apakah file sudah ada
            if (file.exists()) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "File sudah ada. Ganti?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            simpanFile(file);
            berkas = file;
        }
    }

    /**
     * Event handler untuk tombol Simpan
     * Simpan ke file yang sedang dibuka
     */
    private void btnSimpanActionPerformed() {
        if (berkas == null) {
            JOptionPane.showMessageDialog(this,
                    "Belum ada file yang dibuka!\nGunakan 'Buka' atau 'Save As' terlebih dahulu.",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        simpanFile(berkas);
    }

    /**
     * Method untuk menyimpan file
     */
    private void simpanFile(File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(txaIsiBerkas.getText());
            fileWriter.flush();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(this,
                    "File berhasil disimpan:\n" + file.getAbsolutePath(),
                    "SUKSES",
                    JOptionPane.INFORMATION_MESSAGE);

            System.out.println("✓ File berhasil disimpan: " + file.getAbsolutePath());

            // Update status
            lblStatus.setText("✓ File disimpan: " + file.getName());
            lblStatus.setForeground(Color.GREEN);
            setTitle("Form Input Output dengan JFileChooser - " + file.getName());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi error saat menyimpan file!\n" + ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("✗ Error I/O: " + ex.getMessage());

        } finally {
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                System.out.println("✗ Error menutup file: " + e.getMessage());
            }
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IOFileChooserDemo());
    }
}
