import java.util.Scanner;

public class ContohDoWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;

        // Block 'do' dieksekusi setidaknya sekali
        do {
            System.out.print("Masukkan angka positif (masukkan -1 untuk keluar): ");
            input = scanner.nextInt();

            if (input > 0) {
                System.out.println("Anda memasukkan: " + input);
            } else if (input == -1) {
                System.out.println("Keluar dari program.");
            } else {
                System.out.println("Angka harus positif atau -1 untuk keluar.");
            }
        } while (input != -1); // Kondisi diperiksa di sini
    }
}
