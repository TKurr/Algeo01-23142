import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Determinan {
	public static double minorMatriks(double[][] m){
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
		System.out.println("3. Baca Ulang Matriks");
		System.out.println("4. Baca Input dari File");
		System.out.println("5. Lihat spesifikasi matriks");
		System.out.println("6. Keluar");
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
	public static void readDet() {
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
			displayMenuDet();
			Scanner myObj = new Scanner(System.in);
		    double[][] m = SPL.copyMatrix(currentMatrix);
		    System.out.println("Pilih satu menu (nomor):");
		    String menu = myObj.nextLine(); 
		   
		    if (menu.equals("1")) {
		    	if (OBE.isMatrixSquare(m)) {
		    		System.out.print("Hasil Determinan: ");
		    		m = Eselon.ReduksiBaris(m);
		    		if (!checkZeros(m)) {
		    			System.out.println(DeterminanReduksiBaris(m));
		    			String kalimat = String.valueOf(DeterminanReduksiBaris(m));
		    			WriteFile.write(kalimat);
		    		} else {
		    			System.out.println(0d);
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
		    			String kalimat = String.valueOf(DeterminanKofaktor(m));
		    			WriteFile.write(kalimat);
		    		} else {
		    			System.out.println(0d);
		    		}
		    	} else {
		    		System.out.println("Matriks tidak valid, jumlah baris dan kolom harus sama");
		    	}
		    	myObj.nextLine(); 
		    } else if (menu.equals("3")) {
		    	readDet();
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
		    	System.out.println("Pencet enter untuk melanjutkan program");
			    myObj.nextLine();
		    }    
		}
    }
}
