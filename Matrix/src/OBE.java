
public class OBE {
	
	// switchRow
	public static float[][] switchRow(float[][] m,int row1, int row2) {
		float[] row1List = new float[getColEff(m)];
		float[] row2List = new float[getColEff(m)];
		row1List = getRow(m, row1);
		row2List = getRow(m, row2);
		for (int i = 0; i < getRowEff(m); i++) {
			for (int j = 0; j < getColEff(m); j++) {
				if (i == row1-1) {
					m[i][j] = row2List[j];
				}
				if (i == row2-1) {
					m[i][j] = row1List[j];
				}
			}
		}
		return m;
	}
	
	// row normalization (sets the first value to one)
	public static float[][] rowNorm(float[][] m,int idx) {
		float x = 1;
		for (int i = 0; i < getColEff(m); i++) {
			if (m[idx][i] != 0) {
				x = m[idx][i];
				break;
			}
		}
		for (int i = 0; i < getRowEff(m);i++) {
			for (int j = 0; j < getColEff(m); j++) {
				if (i == idx) {
					m[i][j] = m[i][j] / x;
				}
			}
		}
		return m;
	}
	
	public static float[][] multiplyRow(float[][] m,int row, float c) {
		for (int j = 0; j < getColEff(m); j++) {
			m[row-1][j] = m[row-1][j] * c;
		}
		return m;
	}
	
	// multiplyMatrix
	public static float[][] multiplyMatrix(float[][] m, float c) {
		for (int i = 0; i < getRowEff(m); i++) {
			m = multiplyRow(m,i+1,c);
		}
		return m;
	}
	// column Elimination (eliminates column to zero)
	public static float[][] colElim (float[][] m,int idx, boolean gss) {
		float temp = 1;
		for (int i = idx+1; i < getRowEff(m); i++) {
			if (gss) {
				temp = m[i][idx];
			} else {
				temp = m[i][idx] / m[idx][idx];
			}
			for (int j = 0; j < getColEff(m); j++) {
				m[i][j] -= (temp * m[idx][j]);
				
			}
		}
		return m;
	}
	// addRow
	public static float[][] addRow (float[][] m,int row1, int row2, int c) {
		for (int j = 0; j < getColEff(m); j++) {
			m[row1-1][j] = m[row1-1][j] +( m[row2-1][j]*c);
		}
		return m;
	}
		
	// getRow
	public static float[] getRow(float[][] m,int row) {
		float[] list = new float[getColEff(m)];
		for (int i = 0; i < getColEff(m);i++) {
			list[i] = m[row-1][i];
		}
		return list;
	}
	// getCol
	public static float[] getCol(float[][] m,int col) {
		float[] list = new float[getRowEff(m)];
		for (int i = 0; i < getRowEff(m);i++) {
			list[i] = m[i][col-1];
		}
		return list;
	}
	// getRowEff
	public static int getRowEff(float[][] m) {
		return m.length;
	}
	// getColEff
	public static int getColEff(float[][] m) {
		return m[0].length;
	}
	
	// deleteRow
	public static float[][] deleteRow(float[][] m, int row){
		int rowEff = getRowEff(m)-1;
		int colEff = getColEff(m);
		float[][] m2 = new float[rowEff][colEff];
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
	public static float[][] deleteCol(float[][] m, int col){
		int rowEff = getRowEff(m);
		int colEff = getColEff(m)-1;
		float[][] m2 = new float[rowEff][colEff];
		
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
	
	public static void printMatrix(float[][] m) {
		for (int i = 0; i<m.length;i++) {
			for (int j = 0; j<m[0].length;j++) {
				if (m[i][j] == -0.00) {
					System.out.printf("0,00");
					System.out.print(" ");
				}
				else {
					System.out.printf("%.2f",m[i][j]);
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}
	
	public static void printList(float[] list) {
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
		float[][] m = new float[5][10];
		float[][] theo = {
				{1,1,-1,-1,1},
				{2,5,-7,-5,-2},
				{2,-1,1,3,4},
				{5,2,-4,2,6}
				};
		float[][] theo2 = {
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
