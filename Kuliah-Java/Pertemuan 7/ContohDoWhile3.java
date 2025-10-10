public class ContohDoWhile3 {
    public static void main(String[] args) {
        int batas = 10;
        int i = 1;
        int jumlahGanjil = 0;
        do {
            if (i % 2 != 0) { // Cek apakah bilangan ganjil
                jumlahGanjil++;
            }
            i++;
        } while (i <= batas);
        System.out.println("Jumlah bilangan ganjil antara 1 sampai " + batas + " adalah: " + jumlahGanjil);
    }
}
