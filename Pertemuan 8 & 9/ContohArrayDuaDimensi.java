public class ContohArrayDuaDimensi {
    public static void main(String[] args) {
        String[][] koordinat = {
            {"Jakarta", "Indonesia"},
            {"Tokyo", "Jepang"},
            {"London", "Inggris"}
        };

        System.out.println("Negara di baris 2, kolom 1: " + koordinat[1][1]); // Output: Jepang

        System.out.println("\nDaftar Kota dan Negara:");
        for (int i = 0; i < koordinat.length; i++) {
            System.out.print("Baris " + i + ": ");
            for (int j = 0; j < koordinat[i].length; j++) {
                System.out.print(koordinat[i][j] + (j == koordinat[i].length - 1 ? "" : " | "));
            }
            System.out.println();
        }
    }
}
