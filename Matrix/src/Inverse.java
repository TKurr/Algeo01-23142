import java.util.Scanner;

public class Inverse {
	public static double[][] Transpose(double[][] m){
		double [][] t = new double[OBE.getColEff(m)][OBE.getRowEff(m)];
		int i, j;
		for (i = 0; i < OBE.getColEff(t); i++) {
			for (j = 0; j < OBE.getRowEff(t); j++) {
				t[j][i] = m[i][j];
			}	
		}
		return t;
	}
	
	public static double[][] InverseAdjoin(double[][] m){
		double[][] adjoin, newM = new double[OBE.getColEff(m)][OBE.getRowEff(m)];
		double det;
		adjoin = Transpose(m);
		det = Determinan.DeterminanKofaktor(m);
		adjoin = Kofaktor.MatriksKofaktor(adjoin);
		newM = OBE.multiplyMatrix(adjoin, 1/det);
		return newM;
	}
	
	public static double[][] identityMatrix(int n){
		double[][] m = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					m[i][j] = 1;
				} else {
					m[i][j] = 0;
				}
			}
		}
		
		return m;
	}
	
	public static double[][] InverseIdentity(double[][] m){
		int row = OBE.getRowEff(m);
		int col = 2*OBE.getColEff(m);
		double[][] identity = identityMatrix(row);
		double[][] process = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (j < col/2) {
					process[i][j] = m[i][j];
				} else {
					process[i][j] = identity[i][j-row];
				}
			}
		}
		process = SPL.elimGaussJordan(process);
		double[][] mOut = new double[row][col/2];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col/2; j++) {
				mOut[i][j] = process[i][j+row];
			}
		}
		return mOut;
	}
	
	public static void displayMenuInv() {
    	System.out.println("MENU");
		System.out.println("1. Metode Adjoin");
		System.out.println("2. Metode Reduksi Baris");
		System.out.println("3. Lihat spesifikasi matriks");
		System.out.println("4. Keluar");
    }
	
	static double[][] currentMatrix = {
		    {3,1},
		    {5,2},
		  };
	public static void main(String[] args) {
		while (true) {
			displayMenuInv();
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    double[][] m = SPL.copyMatrix(currentMatrix);
		    OBE.printMatrix(m);
		    System.out.println("Pilih satu menu (nomor):");
		    String menu = myObj.nextLine(); 
		    if (menu.equals("1")) {
		    	if (OBE.isMatrixSquare(m)) {
		    		if (Determinan.checkZeros(m)) {
		    			System.out.println("Matriks tidak valid, hasil determinan = 0");
		    		}
		    		else if (Determinan.DeterminanKofaktor(m) == 0) {
		    			System.out.println("Matriks tidak valid, hasil determinan = 0");
		    		} else {
		    			System.out.println("Hasil Inverse: ");
		    			
			    		OBE.printMatrix((InverseAdjoin(m)));
		    		}
		    	} else {
		    		System.out.println("Matriks tidak valid, jumlah baris dan kolom harus sama");
		    	}
		    	myObj.nextLine(); 
		    } else if (menu.equals("2")) {
		    	if (OBE.isMatrixSquare(m)) {
		    		if (Determinan.checkZeros(m)) {
		    			System.out.println("Matriks tidak valid, hasil determinan = 0");
		    		}
		    		else if (Determinan.DeterminanKofaktor(m) == 0) {
		    			System.out.println("Matriks tidak valid, hasil determinan = 0");
		    		} else {
			    		OBE.printMatrix((InverseIdentity(m)));
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
