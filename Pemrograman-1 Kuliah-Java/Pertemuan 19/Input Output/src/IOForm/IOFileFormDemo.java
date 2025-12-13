import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class IOFileFormDemo extends JFrame {
    // Komponen GUI
    private JTextArea txaIsiFile;
    private JButton btnBuka;
    private JButton btnIsiUbah;
    private JButton btnSimpan;
    private JButton btnKeluar;

    // File path
    private String filePath = System.getProperty("user.dir") + "/src/data/IOData.txt";

    public IOFileFormDemo() {
        initComponents();
        createFileIfNotExists();  // ← BUAT FILE OTOMATIS
        setTitle("Form Input Output Berkas Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
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

        btnIsiUbah = new JButton("Isi/Ubah");
        btnIsiUbah.addActionListener(e -> btnIsiUbahActionPerformed());

        btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> btnSimpanActionPerformed());

        btnKeluar = new JButton("Keluar");
        btnKeluar.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnBuka);
        buttonPanel.add(btnIsiUbah);
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnKeluar);

        // Text Area
        txaIsiFile = new JTextArea();
        txaIsiFile.setEditable(false);
        txaIsiFile.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txaIsiFile);

        // Susun layout
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    /**
     * Method untuk membuat file otomatis jika tidak ada
     */
    private void createFileIfNotExists() {
        File file = new File(filePath);
        
        // Jika file belum ada, buatkan
        if (!file.exists()) {
            try {
                // Pastikan folder ada
                file.getParentFile().mkdirs();
                // Buat file dengan data awal
                file.createNewFile();
                
                // Tulis data awal ke file
                FileWriter fw = new FileWriter(filePath);
                fw.write("=== DATA AWAL PROGRAM ===\n");
                fw.write("Selamat datang di program Input Output\n");
                fw.write("File ini dibuat otomatis pada saat pertama kali program dijalankan\n");
                fw.write("Anda bisa mengedit dan menyimpan data di sini\n");
                fw.flush();
                fw.close();
                
                System.out.println("✓ File berhasil dibuat: " + filePath);
                
            } catch (IOException e) {
                System.out.println("✗ Error membuat file: " + e.getMessage());
            }
        }
    }

    /**
     * Event handler untuk tombol Buka - Membaca file
     */
    private void btnBukaActionPerformed() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line;

            // Baca setiap baris dari file
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            // Tampilkan isi file ke text area
            String content = sb.toString();
            if (content.isEmpty()) {
                txaIsiFile.setText("(File kosong - Klik 'Isi/Ubah' untuk menambah data)");
                txaIsiFile.setForeground(Color.GRAY);
            } else {
                txaIsiFile.setText(content);
                txaIsiFile.setEditable(false);
                txaIsiFile.setForeground(Color.BLUE);
            }
            
            setTitle("Membuka File: IOData.txt ✓");
            System.out.println("✓ File berhasil dibuka!");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "File tidak ditemukan!\nPath: " + filePath,
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("✗ File tidak ditemukan: " + ex.getMessage());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi error membaca file!",
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
     * Event handler untuk tombol Isi/Ubah - Edit text area
     */
    private void btnIsiUbahActionPerformed() {
        txaIsiFile.setEditable(true);
        txaIsiFile.setForeground(Color.BLACK);
        setTitle("Mode Edit - Silakan edit isi file");
        JOptionPane.showMessageDialog(this,
                "Text Area sekarang dapat diedit.\n" +
                        "Klik 'Simpan' untuk menyimpan perubahan.",
                "Mode Edit",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Event handler untuk tombol Simpan - Menyimpan file
     */
    private void btnSimpanActionPerformed() {
        FileWriter fileWriter = null;
        try {
            // Siapkan file writer
            fileWriter = new FileWriter(filePath);

            // Tulis isi text area ke file
            fileWriter.write(txaIsiFile.getText());
            fileWriter.flush();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(this,
                    "File berhasil disimpan!",
                    "SUKSES",
                    JOptionPane.INFORMATION_MESSAGE);

            System.out.println("✓ File berhasil disimpan!");

            // Set text area menjadi tidak bisa diedit
            txaIsiFile.setEditable(false);
            txaIsiFile.setForeground(Color.GREEN);
            setTitle("Menyimpan File: IOData.txt ✓");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "File tidak ditemukan!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("✗ File tidak ditemukan: " + ex.getMessage());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi error saat menyimpan file!",
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
        SwingUtilities.invokeLater(() -> new IOFileFormDemo());
    }
}
