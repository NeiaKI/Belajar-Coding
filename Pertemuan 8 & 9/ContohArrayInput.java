import java.util.Scanner;
public class ContohArrayInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen yang ingin disimpan: ");
        int ukuran = scanner.nextInt();

        String[] namaTeman = new String[ukuran];

        for (int i = 0; i < ukuran; i++) {
            System.out.print("Masukkan nama teman ke-" + (i + 1) + ": ");
            namaTeman[i] = scanner.next();
        }

        System.out.println("\nNama-nama yang telah diinput:");
        for (String nama : namaTeman) {
            System.out.println("- " + nama);
        }

        scanner.close();
    }
}
