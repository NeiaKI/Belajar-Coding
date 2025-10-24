// Menentukan Umur Kategori

import java.util.Scanner;

public class LatihanIfElse5 {
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan Usia: ");
        int usia = input.nextInt(); 

        if (usia < 12) {
            System.out.println("Anak-anak"); 
        } else if (usia < 18) {
            System.out.println("Remaja"); 
        } else if (usia < 60) {
            System.out.println("Dewasa"); 
        } else {
            System.out.println("Lansia"); 
        }
    }
}