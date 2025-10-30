class Produk {
    String nama;
    double harga;

    public Produk(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }
}

public class ContohArrayObjek {
    public static void main(String[] args) {
        Produk[] daftarProduk = new Produk[3];

        daftarProduk[0] = new Produk("Laptop", 12000000.0);
        daftarProduk[1] = new Produk("Mouse", 250000.0);
        daftarProduk[2] = new Produk("Keyboard", 800000.0);

        System.out.println("Daftar Produk:");
        for (Produk p : daftarProduk) {
            System.out.println("Nama: " + p.nama + ", Harga: Rp" + p.harga);
        }
    }
}
