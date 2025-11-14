import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DaftarBelanja {
    public static void main(String[] args) {
        // Inisialisasi ArrayList untuk menyimpan item belanja
        ArrayList<String> daftarBelanja = new ArrayList<>();
        
        // Scanner untuk input user
        Scanner scanner = new Scanner(System.in);
        
        int pilihan = 0;
        
        // Loop menu utama
        do {
            // Tampilkan menu
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         APLIKASI DAFTAR BELANJA      ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("1. Tambah Item");
            System.out.println("2. Hapus Item");
            System.out.println("3. Tampilkan Daftar Belanja");
            System.out.println("4. Keluar");
            System.out.println("──────────────────────────────────────");
            System.out.print("Pilih menu (1-4): ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // konsumsi newline

                // Switch-case untuk menangani pilihan menu
                switch (pilihan) {
                    case 1 -> {
                        // TAMBAH ITEM
                        System.out.print("\nMasukkan nama item: ");
                        String itemBaru = scanner.nextLine().trim();
                        if (!itemBaru.isEmpty()) {
                            daftarBelanja.add(itemBaru);
                            System.out.println("✓ Item \"" + itemBaru + "\" berhasil ditambahkan!");
                        } else {
                            System.out.println("✗ Nama item tidak boleh kosong.");
                        }
                    }
                    case 2 -> {
                        // HAPUS ITEM
                        if (daftarBelanja.isEmpty()) {
                            System.out.println("\n✗ Daftar belanja kosong! Tidak ada yang bisa dihapus.");
                        } else {
                            System.out.println("\nDaftar Belanja:");
                            for (int i = 0; i < daftarBelanja.size(); i++) {
                                System.out.println((i + 1) + ". " + daftarBelanja.get(i));
                            }
                            System.out.print("\nMasukkan nomor item yang ingin dihapus: ");
                            int nomorHapus = scanner.nextInt();
                            scanner.nextLine(); // konsumsi newline
                            if (nomorHapus > 0 && nomorHapus <= daftarBelanja.size()) {
                                String itemDihapus = daftarBelanja.remove(nomorHapus - 1);
                                System.out.println("✓ Item \"" + itemDihapus + "\" berhasil dihapus!");
                            } else {
                                System.out.println("✗ Nomor tidak valid!");
                            }
                        }
                    }
                    case 3 -> {
                        // TAMPILKAN DAFTAR BELANJA
                        if (daftarBelanja.isEmpty()) {
                            System.out.println("\n✗ Daftar belanja kosong!");
                        } else {
                            System.out.println("\n╔══════════════════════════════════════╗");
                            System.out.println("║           DAFTAR BELANJA ANDA        ║");
                            System.out.println("╚══════════════════════════════════════╝");
                            for (int i = 0; i < daftarBelanja.size(); i++) {
                                System.out.println((i + 1) + ". " + daftarBelanja.get(i));
                            }
                            System.out.println("──────────────────────────────────────");
                            System.out.println("Total item: " + daftarBelanja.size());
                        }
                    }
                    case 4 -> {
                        // KELUAR
                        System.out.println("\n╔══════════════════════════════════════╗");
                        System.out.println("║  Terima kasih telah menggunakan      ║");
                        System.out.println("║    Aplikasi Daftar Belanja!          ║");
                        System.out.println("╚══════════════════════════════════════╝");
                    }
                    default -> System.out.println("\n✗ Pilihan tidak valid! Silakan pilih 1-4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n✗ Input tidak valid. Masukkan angka 1-4.");
                scanner.nextLine(); // buang input yang salah
            }
        } while (pilihan != 4);

        // Tutup scanner
        scanner.close();
    }
}
