import java.util.Scanner;
public class ContohDoWhile2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bilangan;
        do {
            System.out.print("Masukkan bilangan positif: ");
            bilangan = scanner.nextInt();
            if (bilangan <= 0) {
                System.out.println("Bilangan harus positif. Coba lagi.");
            }
        } while (bilangan <= 0);
        System.out.println("Anda memasukkan bilangan positif: " + bilangan);
        scanner.close();
    }
}
