
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
			mOut[row-1][j] = Math.round((mOut[row-1][j] * c) * 100.0) / 100.0 ; 
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
		for (int j = 0; j < getColEff(m); j++) {
			m[row1-1][j] = m[row1-1][j] +( m[row2-1][j]*c);
		}
		return m;
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
		printMatrix(m);
		double [][] mOut = m;
		for (int i = 0; i<getRowEff(mOut);i++) {
			mOut[i][idx] = inputM[i][0];
		}
		return mOut;
	}
	
	public static void printMatrix(double[][] m) {
		for (int i = 0; i<m.length;i++) {
			for (int j = 0; j<m[0].length;j++) {
				if (m[i][j] == -0.00) {
					System.out.printf("0,00");
					System.out.print(" ");
				}
				else {
					System.out.print(m[i][j]);
					System.out.print(" ");
				}
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
//		printMatrix(theo);
//		System.out.println("");
//		theo = addRow(theo, 0);
//		printMatrix(theo);
//		System.out.println("");		
	}
}
