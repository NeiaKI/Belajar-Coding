import java.util.Scanner;

public class membandingkanduaangka {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan angka pertama: ");
        int angka1 = input.nextInt();

        System.out.print("Masukkan angka kedua: ");
        int angka2 = input.nextInt();

        if (angka1 > angka2) {
            System.out.println("Angka pertama lebih besar dari angka kedua.");
        } else if (angka1 < angka2) {
            System.out.println("Angka kedua lebih besar dari angka pertama.");
        } else {
            System.out.println("Kedua angka sama besar.");
        }
    }
}