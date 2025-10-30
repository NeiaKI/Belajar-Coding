// Menentukan Diskon Belanja

import java.util.Scanner;

public class LatihanIfElse4 {
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan total belanja: ");
        int total= input.nextInt();

        if (total >= 500000) {
            System.out.println("Anda mendapatkan diskon 20%"); 
        } else if (total >= 200000) {
            System.out.println("Anda mendapatkan diskon 10%"); 
        } else 
            System.out.println("Tidak ada diskon"); 
    }
}