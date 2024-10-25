import java.io.IOException;
import java.util.Scanner;

public class main {
	public static void displayMenu() {
		System.out.println("MENU");
		System.out.println("1. Sistem Persamaan Linier");
		System.out.println("2. Determinan");
		System.out.println("3. Matriks Balikan");
		System.out.println("4. Interpolasi Polinom");
		System.out.println("5. Interpolasi Bicubic Spline");
		System.out.println("6. Regresi linier dan kuadratik berganda");
		System.out.println("7. Interpolasi Gambar");
		System.out.println("8. Keluar");
	}
	
	public static void main(String[] args) throws IOException {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				displayMenu();
				Scanner myObj = new Scanner(System.in); 
			    System.out.println("Pilih satu menu (nomor):");
			    String menu = myObj.nextLine();
			    if (menu.equals("1")) {
			    	SPL.main(null);
			    } else if (menu.equals("2")) {
			    	Determinan.main(null);
			    } else if (menu.equals("3")) {
			    	Inverse.main(null);
			    } else if (menu.equals("4")) {
			    	InterpolasiPolinomial.main(null);
			    } else if (menu.equals("5")) {
			    	BicubicSpline.main(null);
			    } else if (menu.equals("6")) {
			    	RegresiBerganda.main(null);
			    } else if (menu.equals("7")) {
			    	ImageResize.main(null);
			    } else if (menu.equals("8")) {
			    	System.out.println("Terima kasih sudah menggunakan program kami!");
			    	break;
			    } else {
			    	System.out.println("Input tidak valid!");
			    	System.out.println("Pencet enter untuk menlanjutkan program");
				    myObj.nextLine();
			    }
			    
			}
			
		}
	}

}
