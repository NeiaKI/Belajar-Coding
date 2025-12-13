package ioform;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class TextToPDF extends JFrame {
    private JTextArea txaIsiBerkas;
    private JButton btnBuka;
    private JButton btnSaveAsPDF;
    private JButton btnKeluar;
    private File berkas;

    public TextToPDF() {
        initComponents();
        setTitle("Text to PDF Demo using iText");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnBuka = new JButton("Buka");
        btnBuka.addActionListener(evt -> btnBukaActionPerformed(evt));
        buttonPanel.add(btnBuka);

        btnSaveAsPDF = new JButton("Save As PDF");
        btnSaveAsPDF.addActionListener(evt -> btnSaveAsPDFActionPerformed(evt));
        buttonPanel.add(btnSaveAsPDF);

        btnKeluar = new JButton("Keluar");
        btnKeluar.addActionListener(evt -> System.exit(0));
        buttonPanel.add(btnKeluar);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        txaIsiBerkas = new JTextArea();
        txaIsiBerkas.setColumns(20);
        txaIsiBerkas.setRows(15);
        txaIsiBerkas.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));
        txaIsiBerkas.setLineWrap(true);
        txaIsiBerkas.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txaIsiBerkas);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    private void btnBukaActionPerformed(ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = 
            new FileNameExtensionFilter("Text Document (.txt, .log)", "txt", "log");
        fc.setFileFilter(filter);
        fc.setMultiSelectionEnabled(false);

        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }

        try {
            berkas = fc.getSelectedFile();

            if (berkas == null || berkas.getName().equals("")) {
                JOptionPane.showMessageDialog(this,
                    "Berkas belum dipilih!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(berkas));
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            txaIsiBerkas.setText(sb.toString());
            br.close();
            setTitle("Text to PDF Demo - File: " + berkas.getName());

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "File tidak ditemukan!",
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error membaca file: " + e.getMessage(),
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnSaveAsPDFActionPerformed(ActionEvent evt) {
        saveAsPDF();
    }

    public void saveAsPDF() {
        if (berkas == null) {
            JOptionPane.showMessageDialog(this,
                "Silakan pilih file terlebih dahulu!",
                "WARNING",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String pdfFileName = berkas.getParent() + File.separator 
                                 + berkas.getName().replace(".txt", ".pdf")
                                 .replace(".log", ".pdf");

            OutputStream file = new FileOutputStream(pdfFileName);
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, file);
            document.open();

            try {
                String content = txaIsiBerkas.getText();
                Paragraph paragraph = new Paragraph(content);
                
                // Gunakan com.lowagie.text.Font, bukan java.awt.Font
                com.lowagie.text.Font font = new com.lowagie.text.Font(
                    com.lowagie.text.Font.HELVETICA, 11, com.lowagie.text.Font.NORMAL);
                paragraph.setFont(font);

                document.add(paragraph);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error menambahkan content: " + e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }

            document.close();
            file.close();

            JOptionPane.showMessageDialog(this,
                "File berhasil disimpan ke:\n" + pdfFileName,
                "SUCCESS",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "Folder tujuan tidak ditemukan!",
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error saat menyimpan ke PDF: " + e.getMessage(),
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextToPDF frame = new TextToPDF();
            frame.setVisible(true);
        });
    }
}
