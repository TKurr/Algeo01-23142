import java.io.File;
import java.io.FileNotFoundException;
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
		System.out.println("2. Metode OBE");
    	System.out.println("3. Baca Matriks");
    	System.out.println("4. Baca Matriks dari File");
		System.out.println("3. Lihat spesifikasi matriks");
		System.out.println("4. Keluar");
    }
	
	public static void readInv() {
    	System.out.print("Jumlah baris dan kolom (n): ");
    	n = OBE.inputInteger();
    	currentMatrix = new double[n][n];
    	for (int i = 0;i<n;i++) {
    		for (int j = 0;j<n;j++) {
    			System.out.print("A["+i+"]"+"["+j+"]: ");
    			currentMatrix[i][j] = OBE.inputDouble();
        	}
    	}
    }
	
	static int n = 0;
    static double[][] currentMatrix = new double[1][1];
    
	public static void main(String[] args) throws FileNotFoundException {
		
		while (true) {
			displayMenuInv();
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    double[][] m = SPL.copyMatrix(currentMatrix);
		    System.out.println("Pilih satu menu (nomor):");
		    String menu = myObj.nextLine(); 
		    if (menu.equals("0")) {
		    	readInv();
		    }
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
		    	readInv();
		    } else if (menu.equals("4")) {
		    	String filename;
		    	System.out.println("Tulis nama file disini");
		    	filename = myObj.nextLine();
		    	if (InputFile.checkFile(filename)) {
		    		File file = InputFile.getFile(filename);
			    	Scanner scanner = new Scanner(file);
			    	
			    	int totalLine = 0;
			    	int lineLength = 0;
			    	String lineTemp = scanner.nextLine();  
	                String[] valuesTemp = lineTemp.split(" ");
	                lineLength = valuesTemp.length;
	                totalLine = totalLine + 1;
			    	
			    	while (scanner.hasNextLine()) {
		                String line = scanner.nextLine();  
		                String[] values = line.split(" ");
		                lineLength = values.length;
			            totalLine = totalLine + 1;
			            }
			    	scanner.close();
			    	
			    	Scanner scanner2 = new Scanner(file);
			    	double[][] newM = new double[totalLine][lineLength];
			    	
			    	int ctr = 0;
			    	while (scanner2.hasNextLine()) {
		                String line = scanner2.nextLine();  
		                String[] values = line.split(" ");
		                for (int i = 0; i < values.length; i++) {
			                    newM[ctr][i] = Double.parseDouble(values[i]);
			                }
		                ctr = ctr+1;
			            }
			    	scanner2.close();
			    	currentMatrix = newM;
		    	}
		    	
		    } else if (menu.equals("5")) {
		    	currentMatrix = OBE.viewMatrix(currentMatrix);
		    } else if (menu.equals("6")) {
		    	break;
		    } else {
		    	System.out.println("Input tidak valid!");
		    	System.out.println("Pencet enter untuk menlanjutkan program");
			    myObj.nextLine();
		    }    
		}
    }
}
