import java.util.Scanner;
public class ContohDoWhile5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n--- Menu Aplikasi ---");
            System.out.println("1. Tampilkan Data");
            System.out.println("2. Tambah Data");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan Anda: ");
            pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    System.out.println("Anda memilih: Tampilkan Data.");
                    break;
                case 2:
                    System.out.println("Anda memilih: Tambah Data.");
                    break;
                case 0:
                    System.out.println("Terima kasih. Aplikasi ditutup.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0); // Ulangi selama pilihan bukan '0' (Keluar)
        scanner.close();
    }
}
