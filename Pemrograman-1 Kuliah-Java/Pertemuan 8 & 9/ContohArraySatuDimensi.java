public class ContohArraySatuDimensi {
    public static void main(String[] args) {
        int[] nilaiSiswa = {85, 90, 78, 92, 88};

        System.out.println("Nilai siswa ke-1: " + nilaiSiswa[0]);
        System.out.println("Nilai siswa ke-3: " + nilaiSiswa[2]);

        nilaiSiswa[4] = 95;
        System.out.println("Nilai siswa ke-5 setelah diubah: " + nilaiSiswa[4]);

        System.out.println("\nSemua nilai:");
        for (int i = 0; i < nilaiSiswa.length; i++) {
            System.out.println("Indeks " + i + ": " + nilaiSiswa[i]);
        }
    }
}
