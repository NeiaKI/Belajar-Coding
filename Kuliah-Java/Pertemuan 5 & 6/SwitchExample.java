public class SwitchExample {
    public static void main(String[] args) {
        int hari = 2; // Misal: 1=Senin, 2=Selasa, dst.
        
        System.out.println("--- TANPA BREAK ---");
        switch (hari) {
            case 1:
                System.out.println("Hari Senin");
            case 2:
                System.out.println("Hari Selasa");       
            case 3:
                System.out.println("Hari Rabu");
            default:
                System.out.println("Hari lain");
        }
    }
}