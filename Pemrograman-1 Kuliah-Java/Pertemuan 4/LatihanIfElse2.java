// Menentukan Nilai Kelulusan

import java.util.Scanner;

public class LatihanIfElse2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan sebuah nilai: ");
        int nilai = input.nextInt(); 

        if (nilai >= 70) {
            System.out.println("Lulus");
        } else {
            System.out.println("Tidak Lulus");
        }
    }
}