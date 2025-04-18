import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class SPL {
    public static double[][] elimGaussJordan(double[][] m){
    	double [][] mOut = m;
		mOut = Eselon.SortMatriks(mOut);
        mOut = Eselon.ReduksiBaris(mOut);
        mOut = Eselon.MatriksEselon(mOut);
        mOut = Eselon.MatriksEselonTereduksi(mOut);
        return mOut;
    }
    
    public static double[][] elimGauss(double[][] m){
    	double[][] mOut = m;
		mOut = Eselon.SortMatriks(mOut);
        mOut = Eselon.ReduksiBaris(mOut);
        mOut = Eselon.MatriksEselon(mOut);
        return mOut;
    }
    
    public static double[] gaussSPL(double[][] m){
    	double[][] mOut = elimGauss(m);
    	double[] listSolution = new double[OBE.getRowEff(mOut)];
    	for (int i = OBE.getRowEff(mOut)-1;i>=0;i--) {
    		double temp = mOut[i][OBE.getColEff(mOut)-1];
    		for (int j = OBE.getColEff(mOut)-2;j > i ;j--) {
    			temp = temp - mOut[i][j]*listSolution[j];
    		}
    		listSolution[i] = temp;
    	}
        return listSolution;
    }

	public static double[] inverseSPL(double[][] A, double[][] b) {
		double[][] x;
		double[][] inverseA = Inverse.InverseAdjoin(A);
		x = OBE.multiplyBetweenMatrix(inverseA, b);
		double[] list = OBE.getCol(x, 1);
		return list;
	}
    
    public static boolean isSolution(double[][] m) {
        int rowCount = OBE.getRowEff(m); 
        int colCount = OBE.getColEff(m);

        for (int i = 0; i < rowCount; i++) {
            boolean allZero = true;
            for (int j = 0; j < colCount - 1; j++) { 
                if (m[i][j] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                return false; 
            }
        }
        return true; 
    }

    public static boolean isInfiniteSolution(double[][] m) {
		return (OBE.getRowEff(m) < OBE.getColEff(m) - 1);
    }
    
    public static boolean isNoSolution(double[][] m) {
    	return (OBE.getRowEff(m) >= OBE.getColEff(m));
    }

    public static void printParametric(double[][] m) {
    	double[][] newM = elimGaussJordan(m);
		int i,j;
		int colIdx;
		for (i = 0; i < OBE.getRowEff(newM); i++) {
			colIdx = Eselon.countZero(newM, i);
			
			String stringTemp = "X" + (colIdx+1);
			stringTemp = stringTemp + " = " + String.valueOf(newM[i][OBE.getColEff(newM)-1]);
			for (j = colIdx+1; j < OBE.getColEff(newM)-1; j++) {
				if (newM[i][j] < 0) {
					if (newM[i][j] == -1) {
						stringTemp = stringTemp + " + " + "X" + (j+1);
					} else {
						stringTemp = stringTemp + " + " + (-newM[i][j]) + "X" + (j+1);
					}
					
				} else if (newM[i][j] > 0) {
					if (newM[i][j] == 1) {
						stringTemp = stringTemp + " - " + "X" + (j+1);
					} else {
						stringTemp = stringTemp + " - " + (newM[i][j]) + "X" + (j+1);
					}
					
				}
			}
			System.out.println(stringTemp);
		}
    }
    
    
    public static double[] gaussJordanSPL(double[][] m) {
    	double[][] mOut = m;
    	mOut = elimGaussJordan(mOut);
		double[] result = new double[OBE.getRowEff(mOut)];
		for (int i = 0; i < result.length; i++) {
			result[i] = mOut[i][result.length];
		}
		return result;

    }
    
    public static double[][] copyMatrix(double[][] m){
    	double[][] mOut = new double[OBE.getRowEff(m)][OBE.getColEff(m)];
    	for (int i = 0; i < OBE.getRowEff(mOut); i++) {
    		for (int j = 0; j < OBE.getColEff(mOut); j++) {
    			mOut[i][j] = m[i][j];
    		}
    	}
    	return mOut;
    }

    public static double[] Cramer(double[][] m, double[][] n) {
    	int row = OBE.getRowEff(m);
    	int col = OBE.getColEff(m);
    	double det, tempDet;
    	double[][] copy = new double[row][col];
    	det = Determinan.DeterminanKofaktor(m);
    	double[] result = new double[col];
    	for (int i = 0; i < row; i++) {
    		copy = copyMatrix(m);
    		for (int j = 0; j < row; j++) {
        		for (int k = 0; k < col; k++) {
        			if (k == i) {
        				copy[j][k] = n[j][0];
        			}
        		}
        	}
    		tempDet = Determinan.DeterminanKofaktor(copy);
    		result[i] = tempDet/det;
    	}
    	return result;
    }
    
    public static void displayMenuSPL() {
    	System.out.println("MENU");	
		System.out.println("1. Metode Eliminasi Gauss");
		System.out.println("2. Metode Eliminasi Gauss Jordan");
		System.out.println("3. Metode Matriks Balikan");
		System.out.println("4. Kaidah Cramer");
		System.out.println("5. Baca Matriks");
		System.out.println("6. Baca Input dari File");
		System.out.println("7. Lihat spesifikasi matriks A");
		System.out.println("8. Lihat spesifikasi matriks B");
		System.out.println("9. Keluar");
    }
    
    public static void printSolution(double[] listSolution) {
    	for (int i = 0; i < listSolution.length; i++) {
    		System.out.print("X");
    		System.out.print(i+1);
    		System.out.print(": ");
    		System.out.println(listSolution[i]);
    	}
    }
    
    public static double[][] augmentMatrix(double[][] A, double[][] B){
    	double[][] m = new double[OBE.getRowEff(B)][OBE.getColEff(A)+1];
    	for (int i = 0; i < OBE.getRowEff(A);i++) {
    		for (int j = 0; j < OBE.getColEff(A);j++) {
        		m[i][j] = A[i][j];
        	}
    	}
    	for (int i = 0; i < OBE.getRowEff(B);i++) {
    		for (int j = 0; j < OBE.getColEff(B);j++) {
        		m[i][OBE.getColEff(m)-1] = B[i][j];
        	}
    	}
    	return m;
    	
    }
    
    public static boolean isMatrixABvalid(double[][] A, double[][] B) {
    	return (OBE.getColEff(A) == OBE.getRowEff(A) && OBE.getColEff(B) == 1 && OBE.getColEff(A) == OBE.getRowEff(B));
    }
    
    public static void readSPL() {
    	System.out.print("Jumlah baris (m): ");
    	m2 = OBE.inputInteger();
    	System.out.print("Jumlah kolom (n): ");
    	n = OBE.inputInteger();
    	currentMatrixA = new double[m2][n];
    	currentMatrixB = new double[m2][1];
    	for (int i = 0;i<m2;i++) {
    		for (int j = 0;j<n;j++) {
    			System.out.print("A["+i+"]"+"["+j+"]: ");
    			currentMatrixA[i][j] = OBE.inputDouble();
        	}
    	}
    	for (int i = 0;i<m2;i++) {
    		System.out.print("B["+i+"]");
    		currentMatrixB[i][0] = OBE.inputDouble();
    	}
    }
    
    static int m2 = 0;
    static int n = 0;
    static double[][] currentMatrixA = new double[1][1];
    static  double[][] currentMatrixB = new double[1][1];
    
    public static void main(String[] args) throws FileNotFoundException {
    		
			while (true) {
				displayMenuSPL();
				Scanner myObj = new Scanner(System.in);  
			    double[][] A = copyMatrix(currentMatrixA);
			    double[][] B = copyMatrix(currentMatrixB);
			    double[][] m = augmentMatrix(A,B);
			    System.out.println("Pilih satu menu (nomor):");
			    String menu = myObj.nextLine(); 
	
			    if (menu.equals("1")) {
			    	if (isNoSolution(m)) {
			    		System.out.println("Tidak ada solusi");
			    	} else if (isInfiniteSolution(m)) {
			    		printParametric(m);
			    	} else if (!isSolution(m)) {
			    		System.out.println("Tidak ada solusi");
			    	} else {
			    		double[][] newM = elimGauss(m);
			    		if (isSolution(newM)) {
			    			double[] M = gaussSPL(m);
			    			printSolution(M);
			    			String kalimat = Arrays.toString(M);
			    			WriteFile.write(kalimat);
			    		} else {
			    			System.out.println("Tidak ada solusi");
			    		}
			    	}
			    	myObj.nextLine(); 
			    } else if (menu.equals("2")) {
			    	if (isNoSolution(m)) {
			    		System.out.println("Tidak ada solusi");
			    	} else if (isInfiniteSolution(m)) {
			    		printParametric(m);
			    	} else if (!isSolution(m)) {
			    		System.out.println("Tidak ada solusi");
			    	} else {
			    		double[][] newM = elimGaussJordan(m);
			    		if (isSolution(newM)) {
			    			double[] M = gaussJordanSPL(m);
			    			printSolution(M);
			    			String kalimat = Arrays.toString(M);
			    			WriteFile.write(kalimat);
			    		} else {
			    			System.out.println("Tidak ada solusi");
			    		}
			    	}
			    	myObj.nextLine(); 
			    } else if (menu.equals("3")) {
			    	if (isMatrixABvalid(A,B)) {
			    		if (!isSolution(m)) {
				    		System.out.println("Tidak ada solusi");
				    	} else {
				    		double[][] newM = elimGaussJordan(m);
				    		if (isSolution(newM)) {
				    			double[] sol = inverseSPL(A,B);
				    			String kalimat = Arrays.toString(sol);
				    			printSolution(sol);
				    			WriteFile.write(kalimat);
				    		} else {
				    			System.out.println("Tidak ada solusi");
				    		}
				    	}
			    	}
			    	myObj.nextLine(); 
			    } else if (menu.equals("4")) {
			    	if (isMatrixABvalid(A,B)) {
			    		if (!isSolution(m)) {
				    		System.out.println("Tidak ada solusi");
				    	} else {
				    		double[][] newM = elimGaussJordan(m);
				    		if (isSolution(newM)) {
				    			double[] sol = Cramer(A,B);
				    			String kalimat = Arrays.toString(sol);
				    			printSolution(sol);
				    			WriteFile.write(kalimat);
				
				    		} else {
				    			System.out.println("Tidak ada solusi");
				    		}
				    	}
			    	}
			    	myObj.nextLine(); 
			    } else if (menu.equals("5")) {
			    	readSPL();
			    	
			    } else if (menu.equals("6")) {
			    	
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
				    	
				    	currentMatrixA = new double[totalLine][lineLength-1];
				    	for (int i = 0; i < totalLine; i++) {
				    		for (int j = 0; j < lineLength-1; j++) {
					    		currentMatrixA[i][j] = newM[i][j];
					    	}
				    	}
				    	currentMatrixB = new double[totalLine][1];
				    	for (int i = 0; i < totalLine; i++) {
				    		currentMatrixB[i][0] = newM[i][lineLength-1];
				    	}
				    	System.out.println("File berhasil disimpan");
			    	} else {
			    		System.out.println("File tidak berhasil disimpan");
			    	}
			    	
			   
			    } else if (menu.equals("7")) {
			    	currentMatrixA = OBE.viewMatrix(currentMatrixA);
			    } else if (menu.equals("8")) {
			    	currentMatrixB = OBE.viewMatrix(currentMatrixB);
			    } else if (menu.equals("9")) {
			    	break;
			    } else {
			    	System.out.println("Input tidak valid!");
			    	System.out.println("Pencet enter untuk menlanjutkan program");
				    myObj.nextLine();
			    }    
			}
	    }
	}
