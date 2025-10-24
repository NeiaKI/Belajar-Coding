import java.util.Scanner;
    public class InputVariabel {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Masukkan nama: ");
    String nama = input.nextLine();
    System.out.print("Masukkan umur: ");
    int umur = input.nextInt();
    System.out.println("Halo " + nama + ", umur kamu " + umur + " tahun.");
    }
}