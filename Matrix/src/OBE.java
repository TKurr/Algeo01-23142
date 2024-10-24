import java.util.Scanner;

public class OBE {
	// switchRow
	public static double[][] switchRow(double[][] m,int row1, int row2) {
		double[] row1List = new double[getColEff(m)];
		double[] row2List = new double[getColEff(m)];
		double[][] mOut = m;
		row1List = getRow(mOut, row1);
		row2List = getRow(mOut, row2);
		for (int i = 0; i < getRowEff(mOut); i++) {
			for (int j = 0; j < getColEff(mOut); j++) {
				if (i == row1-1) {
					mOut[i][j] = row2List[j];
				}
				if (i == row2-1) {
					mOut[i][j] = row1List[j];
				}
			}
		}
		return mOut;
	}
	// multiplyRow
	public static double[][] multiplyRow(double[][] m,int row, double c) {
		double[][] mOut = m;
		for (int j = 0; j < getColEff(mOut); j++) {
			mOut[row-1][j] = (mOut[row-1][j] * c); 
		}
		return mOut;
	}
	
	// multiplyBetweenMatrix
	public static double[][] multiplyBetweenMatrix(double[][] m, double[][] n) {
		double[][] result = new double[getRowEff(m)][getColEff(n)];
		int i, j, k;
		double jumlah = 0;
		for (i = 0; i < getRowEff(m); i++) {
			for (j = 0; j < getColEff(n); j++) {
				for (k = 0; k < getRowEff(n); k++) {
					jumlah += (m[i][k] * n[k][j]);
				}
				result[i][j] = jumlah;
				jumlah = 0;
			}
		}
		return result;
	}
	
	// multiplyMatrix
	public static double[][] multiplyMatrix(double[][] m, double c) {
		double[][] mOut = m;
		for (int i = 0; i < getRowEff(mOut); i++) {
			mOut = multiplyRow(mOut,i+1,c);
		}
		return mOut;
	}

	// addRow
	public static double[][] addRow (double[][] m,int row1, int row2, double c) {
		double[][] mOut = m;
		for (int j = 0; j < getColEff(mOut); j++) {
			mOut[row1-1][j] = mOut[row1-1][j] +( mOut[row2-1][j]*c);
		}
		return mOut;
	}
		
	// getRow
	public static double[] getRow(double[][] m,int row) {
		double[] list = new double[getColEff(m)];
		for (int i = 0; i < getColEff(m);i++) {
			list[i] = m[row-1][i];
		}
		return list;
	}
	// getCol
	public static double[] getCol(double[][] m,int col) {
		double[] list = new double[getRowEff(m)];
		for (int i = 0; i < getRowEff(m);i++) {
			list[i] = m[i][col-1];
		}
		return list;
	}
	// getRowEff
	public static int getRowEff(double[][] m) {
		return m.length;
	}
	// getColEff
	public static int getColEff(double[][] m) {
		return m[0].length;
	}
	
	// deleteRow
	public static double[][] deleteRow(double[][] m, int row){
		int rowEff = getRowEff(m)-1;
		int colEff = getColEff(m);
		double[][] m2 = new double[rowEff][colEff];
		int currentRow = 0;
		for (int i = 0; i<getRowEff(m);i++) {
			if (i != row-1) {
				for (int j = 0; j<getColEff(m);j++) {
					m2[currentRow][j] = m[i][j];	
				}
				currentRow = currentRow + 1;
			}
		}
		return m2;
	}
	// deleteCol
	public static double[][] deleteCol(double[][] m, int col){
		int rowEff = getRowEff(m);
		int colEff = getColEff(m)-1;
		double[][] m2 = new double[rowEff][colEff];
		
		for (int i = 0; i<getRowEff(m);i++) {
			int currentCol = 0;
			for (int j = 0; j<getColEff(m);j++) {
				if (j != col-1) {
					m2[i][currentCol] = m[i][j];
					currentCol = currentCol + 1;
				}
			}
		}
		return m2;
	}
	
	public static double[][] insertCol(double[][] m, int idx, double [][] inputM) {
		double [][] mOut = m;
		for (int i = 0; i<getRowEff(mOut);i++) {
			mOut[i][idx] = inputM[i][0];
		}
		return mOut;
	}
	
	public static void printMatrix(double[][] m) {
		for (int i = 0; i<m.length;i++) {
			for (int j = 0; j<m[0].length;j++) {
				System.out.printf("%f ",m[i][j]);
			}
			System.out.println("");
		}
	}
	
	public static void printList(double[] list) {
		for (int j = 0; j<list.length;j++) {
			if (list[j] == -0.00) {
				System.out.printf("0,00");
				System.out.print(" ");
			}
			else {
				System.out.printf("%.2f",list[j]);
				System.out.print(" ");
			}
		}
		System.out.println("");
	}
	
	public static void matrixMenu() {
		System.out.println("MENU");
		System.out.println("1. Ubah elemen");
		System.out.println("2. Tambah baris");
		System.out.println("3. Hapus baris");
		System.out.println("4. Tambah kolom");
		System.out.println("5. Hapus kolom");
		System.out.println("6. Keluar");
	}
	
	public static double[][] changeElmt(double[][] m){
		
		int row,col;
		while (true) {
			System.out.print("Input baris (antara 0 hingga ");
			System.out.print(getRowEff(m)-1);
			System.out.println(")");
			row = inputInteger();
			if (row >= 0 && row < getRowEff(m) ) {
				break;
			} else {
				System.out.println("Input diluar batas");
			}
		}
		while (true) {
			System.out.print("Input kolom (antara 0 hingga ");
			System.out.print(getColEff(m)-1);
			System.out.println(")");
			col = inputInteger();
			if (col >= 0 && col < getColEff(m) ) {
				break;
			} else {
				System.out.println("Input diluar batas");
			}
		}
		System.out.println("Input elemen baru:");
		double newElmt = inputDouble();
		m[row][col] = newElmt;
		return m;	
	}
	
	public static double[][] insertRow(double[][] m, int idx) {
        int originalRows = m.length;
        int cols = m[0].length;
        double[][] newMatrix = new double[originalRows + 1][cols];
        for (int i = 0; i < idx; i++) {
            newMatrix[i] = m[i];
        }
        for (int j = 0; j < cols; j++) {
            newMatrix[idx][j] = 0.0;
        }
        
        for (int i = idx; i < originalRows; i++) {
            newMatrix[i + 1] = m[i];
        }
        return newMatrix;
    }
	
	public static double[][] insertCol(double[][] m, int idx) {
        int rows = m.length;
        int originalCols = m[0].length;
        double[][] newMatrix = new double[rows][originalCols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < idx; j++) {
                newMatrix[i][j] = m[i][j];
            }
        }
        for (int i = 0; i < rows; i++) {
            newMatrix[i][idx] = 0.0;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = idx; j < originalCols; j++) {
                newMatrix[i][j + 1] = m[i][j];
            }
        }
        return newMatrix;
    }
	
	public static boolean isMatrixSquare(double[][] m) {
		return (getRowEff(m) == getColEff(m));
	}
	
	public static double[][] viewMatrix(double[][] m) {
		while (true) {
			System.out.println("Current Matrix:");
			printMatrix(m);
			System.out.println("");
			matrixMenu();
			Scanner myObj = new Scanner(System.in);  
		    System.out.println("Pilih satu menu (nomor):");
		    String menu = myObj.nextLine();  
		    if (menu.equals("1")) {
		    	m = changeElmt(m);
		    } else if (menu.equals("2")) {
		    	System.out.println("Tambah di baris mana?");
		    	int row;
		    	while (true) {
					System.out.print("Input baris (antara 0 hingga ");
					System.out.print(getRowEff(m)-1);
					System.out.println(")");
					row = inputInteger();
					if (row >= 0 && row < getRowEff(m) ) {
						break;
					} else {
						System.out.println("Input diluar batas");
					}
					
				}
		    	m = insertRow(m,row);
		    	System.out.println("Baris berhasil di tambah");
		    } else if (menu.equals("3")) {
		    	System.out.println("Hapus baris yang mana?");
		    	int row;
		    	while (true) {
					System.out.print("Input baris (antara 0 hingga ");
					System.out.print(getRowEff(m)-1);
					System.out.println(")");
					row = inputInteger();
					if (row >= 0 && row < getRowEff(m) ) {
						break;
					} else {
						System.out.println("Input diluar batas");
					}
					
				}
		    	m = deleteRow(m,row+1);
		    	System.out.println("Baris berhasil di hapus");
		    	
		    } else if (menu.equals("4")) {
		    	System.out.println("Tambah di kolom mana?");
		    	int col;
		    	while (true) {
					System.out.print("Input kolom (antara 0 hingga ");
					System.out.print(getColEff(m)-1);
					System.out.println(")");
					col = inputInteger();
					if (col >= 0 && col < getColEff(m) ) {
						break;
					} else {
						System.out.println("Input diluar batas");
					}
					
				}
		    	m = insertCol(m,col);
		    	System.out.println("Kolom berhasil di tambah");
		    	
		    } else if (menu.equals("5")) {
		    	System.out.println("Hapus kolom yang mana?");
		    	int col;
		    	while (true) {
					System.out.print("Input kolom (antara 0 hingga ");
					System.out.print(getColEff(m)-1);
					System.out.println(")");
					col = inputInteger();
					if (col >= 0 && col < getColEff(m) ) {
						break;
					} else {
						System.out.println("Input diluar batas");
					}
					
				}
		    	m = deleteCol(m,col+1);
		    	System.out.println("Kolom berhasil di hapus");
		    	
		    } else if (menu.equals("6")) {
		    	break;
		    } else {
		    	System.out.println("Input tidak valid!");
		    	System.out.println("Pencet enter untuk menlanjutkan program");
			    myObj.nextLine();
		    }
		    
		}
		return m;
	}
	
	public static int inputInteger() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.println("Masukkan angka:");
            String input = scanner.nextLine(); 
            try {
                int number = Integer.parseInt(input);
                return number;  
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka lagi.");
            }
        }
	}
	
	public static double inputDouble() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
            System.out.println("Masukkan angka:");
            String input = scanner.nextLine();  
            try {
                double number = Double.parseDouble(input);
                return number; 
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka lagi.");
            }
        }
	}
	
	
	
	// main driver
	public static void main(String[] args) {
		double[][] m = new double[5][10];
		double[][] theo = {
				{1,1,-1,-1,1},
				{2,5,-7,-5,-2},
				{2,-1,1,3,4},
				{5,2,-4,2,6}
				};
		double[][] theo2 = {
				{1,1,-1,-1,1},
				{2,5,-7,-5,-2},
				{2,-1,1,3,4},
				{5,2,-4,2,6}
				};
		double[][] theo3 = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
				};
		double[][] theo4 = {
				{9,8,7},
				{6,5,4},
				{3,2,1},
				};
		for (int i = 0; i<getRowEff(m);i++) {
			for (int j = 0; j<getColEff(m);j++) {
				m[i][j] = (i-j)*(i+j);
			}
		}
		printMatrix(m);
		m = deleteRow(m,3);
		System.out.println("");
		printMatrix(m);
		m = deleteCol(m,10);
		System.out.println("");
		printMatrix(m);
		System.out.println("");
		m = switchRow(m,1,3);
		printMatrix(m);
		System.out.println("");
		m = multiplyRow(m,2,10);
		printMatrix(m);
		System.out.println("");
		m = addRow(m,1,4,3);
		printMatrix(m);
		System.out.println("");
		m = multiplyMatrix(m,2);
		printMatrix(m);
		System.out.println("");
		printMatrix(theo);
		System.out.println("");
		theo = Eselon.ReduksiBaris(theo);
		printMatrix(theo);
		System.out.println("");
		theo2 = Eselon.MatriksEselon(theo2);
		printMatrix(theo2);
		double [][] theo5 = new double[3][3];
		System.out.println("");
		theo5 = multiplyBetweenMatrix(theo3, theo4);
		printMatrix(theo5);
//		printMatrix(theo);
//		System.out.println("");
//		theo = addRow(theo, 0);
//		printMatrix(theo);
//		System.out.println("");		
	}
}
