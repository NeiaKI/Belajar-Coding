public class ContohDoWhile4 {
    public static void main(String[] args) {
        int x = 10;
        System.out.println("Nilai awal x: " + x);
        do {
            System.out.println("Blok 'do' dieksekusi. Nilai x: " + x);
            x++;
        } while (x < 5); // Kondisi ini False dari awal (10 < 5 salah)
        System.out.println("Perulangan selesai. Nilai akhir x: " + x);
    }
}
