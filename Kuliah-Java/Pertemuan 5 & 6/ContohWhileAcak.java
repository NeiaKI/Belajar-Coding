import java.util.Random;

public class ContohWhileAcak {
    public static void main(String[] args) {
        Random rand = new Random();
        int target = 7; // Angka yang dicari
        int percobaan = 0;
        int hasilDadu = 0;

        System.out.println("Mencari angka " + target + " dengan melempar dadu...");

        // Loop berjalan terus sampai hasilDadu sama dengan target
        while (hasilDadu != target) {
            // Menghasilkan angka acak antara 1 dan 10
            hasilDadu = rand.nextInt(10) + 1;
            percobaan++;
            System.out.println("Percobaan ke-" + percobaan + ": Hasil = " + hasilDadu);
        }
        System.out.println("\nTarget " + target + " tercapai setelah " + percobaan + " percobaan!");
    }
}
