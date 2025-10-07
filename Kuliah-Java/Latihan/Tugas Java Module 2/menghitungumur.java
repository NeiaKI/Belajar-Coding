import java.util.Scanner;

public class menghintungumur {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan umur: ");
        int umur = input.nextInt();

        if (umur >= 17) {
            System.out.println("Anda sudah dewasa.");
        } else {
            System.out.println("Anda belum dewasa.");
        }
    }
}