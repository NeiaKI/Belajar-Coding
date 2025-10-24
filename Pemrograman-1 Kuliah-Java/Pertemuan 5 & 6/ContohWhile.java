import java.util.Scanner;

public class ContohWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = 0;
        int angka = 0;

        System.out.println("Masukkan angka (masukkan 0 untuk berhenti):");

        // kondisi: akan terus mengulang selama 'angka' bukan 0
        while (angka != 0) {
            angka = scanner.nextInt(); // Membaca input baru
            total += angka;
        }
        System.out.println("Total dari angka yang dimasukkan adalah: " + total);
    }
}