public class ContohArrayPerhitungan {
    public static void main(String[] args) {
        double[] suhuMingguan = {28.5, 29.0, 30.1, 27.8, 29.5, 31.0, 28.9};
        double totalSuhu = 0.0;

        for (double suhu : suhuMingguan) {
            totalSuhu += suhu;
        }

        double rataRata = totalSuhu / suhuMingguan.length;

        System.out.println("Data Suhu (" + suhuMingguan.length + " hari):");
        for (double suhu : suhuMingguan) {
            System.out.print(suhu + "oC ");
        }

        System.out.println("\nTotal Suhu: " + totalSuhu + "oC");
        System.out.println("Rata-rata Suhu Mingguan: " + rataRata + "oC");
    }
}
