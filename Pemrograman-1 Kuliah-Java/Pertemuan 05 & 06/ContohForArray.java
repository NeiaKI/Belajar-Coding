public class ContohForArray {
    public static void main(String[] args) {
        String[] buah = {"Apel", "Jeruk", "Mangga", "Nanas"};

        System.out.println("Daftar Buah:");
        // Menggunakan indeks "i" untuk mengakses setiap elemen
        for (int i = 0; i < buah.length; i++) {
            System.out.println("Buah ke-" + (i + 1) + ": " + buah[i]);
        }
    }
}