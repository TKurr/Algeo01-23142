import java.util.Scanner;

public class Determinan {
	static double[][] currentMatrix = new double[3][3];
	public static double minorMatriks(double[][] m){
		// I.S : matriks terdefinisi 2x2
		// F.S : nilai determinan
		return (m[0][0]*m[1][1]) - (m[0][1]*m[1][0]);
	}
	
	public static double DeterminanReduksiBaris(double[][] m){
		double[][] mOut = m;
		int swap = Eselon.countSwap(mOut);
		mOut = Eselon.SortMatriks(mOut);
		swap = swap + Eselon.CountSwapRed(mOut);
		mOut = Eselon.ReduksiBaris(mOut);
		double result;
		result = 1;
		for (int i = 0; i < OBE.getRowEff(mOut); i++) {
			result *= mOut[i][i];
		}
		return (double) (result*Math.pow(-1, swap));
	}
	
	public static double DeterminanKofaktor(double[][] m) {
		if (Kofaktor.isMatriks2x2(m)) {
			return Determinan.minorMatriks(m);
		} else {
			double ctr = 0;
			for (int i = 0; i < OBE.getColEff(m);i++) {
				if (m[0][i] != 0) {
					ctr = ctr + m[0][i]*Kofaktor.kofaktor(m,0,i);
				} 
			}
			return ctr;
		}
	}
	public static void displayMenuDet() {
    	System.out.println("MENU");
		System.out.println("1. Metode Reduksi Baris");
		System.out.println("2. Metode Kofaktor");
		System.out.println("3. Lihat spesifikasi matriks");
		System.out.println("4. Keluar");
    }
	
	public static boolean checkZeros(double[][] m) {
	    int rowCount = OBE.getRowEff(m);  
	    int colCount = OBE.getColEff(m);  
	    for (int i = 0; i < rowCount; i++) {
	        boolean allZero = true; 
	        for (int j = 0; j < colCount; j++) {
	            if (m[i][j] != 0) {  
	                allZero = false;  
	                break;  
	            }
	        }
	        if (allZero) {
	            return true;
	        }
	    }
	    return false;
	}

	public static void main(String[] args) {
		while (true) {
			displayMenuDet();
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    
		    double[][] m = SPL.copyMatrix(currentMatrix);
		    System.out.println("Pilih satu menu (nomor):");
		    String menu = myObj.nextLine(); 
		    if (menu.equals("1")) {
		    	if (OBE.isMatrixSquare(m)) {
		    		System.out.print("Hasil Determinan: ");
		    		if (!checkZeros(m)) {
		    			System.out.println(DeterminanReduksiBaris(m));
		    		} else {
		    			System.out.println(0);
		    		}
		    	} else {
		    		System.out.println("Matriks tidak valid, jumlah baris dan kolom harus sama");
		    	}
		    	myObj.nextLine(); 
		    } else if (menu.equals("2")) {
		    	if (OBE.isMatrixSquare(m)) {
		    		System.out.print("Hasil Determinan: ");
		    		if (!checkZeros(m)) {
		    			System.out.println(DeterminanKofaktor(m));
		    		} else {
		    			System.out.println(0);
		    		}
		    	} else {
		    		System.out.println("Matriks tidak valid, jumlah baris dan kolom harus sama");
		    	}
		    	myObj.nextLine(); 
		    } else if (menu.equals("3")) {
		    	currentMatrix = OBE.viewMatrix(currentMatrix);
		    } else if (menu.equals("4")) {
		    	break;
		    } else {
		    	System.out.println("Input tidak valid!");
		    	System.out.println("Pencet enter untuk menlanjutkan program");
			    myObj.nextLine();
		    }    
		}
    }
}
