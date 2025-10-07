public class ContohStrukturKontrol {
    public static void main(String[] args) {
        
        char nilai = 'B';
        String predikat;

        //menggunakan if-else if-else
        System.out.println("--- Menggunakan if-else if-else ---");
        
        if (nilai == 'A') {
            predikat = "Sangat Baik";
        } else if (nilai == 'B') {
            predikat = "Baik";
        } else if (nilai == 'C') {
            predikat = "Cukup";
        } else if (nilai == 'D') {
            predikat = "Kurang";
        } else {
            predikat = "Nilai Tidak Valid";
        }
        System.out.println("Predikat Anda:B " + predikat);
        
        System.out.println("\n===================================\n");

        //menggunakan switch
        System.out.println("--- Menggunakan switch ---");

        switch (nilai) {
            case 'A':
                predikat = "Sangat Baik";
                break;
            case 'B':
                predikat = "Baik";
                break;
            case 'C':
                predikat = "Cukup";
                break;
            case 'D':
                predikat = "Kurang";
                break;
            default:
                predikat = "Nilai Tidak Valid";
                break;
        }
        System.out.println("Predikat Anda:C " + predikat);
    }
}