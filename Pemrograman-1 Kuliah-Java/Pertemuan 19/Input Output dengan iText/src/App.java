// App.java
package ioform;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Jalankan aplikasi GUI di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            TextToPDF app = new TextToPDF();
            app.setVisible(true);
        });
    }
}
