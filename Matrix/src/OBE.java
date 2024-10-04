
public class OBE {
	
	// switchRow
	public static float[][] switchRow(float[][] m,int idx1, int idx2) {
		float[] row1 = new float[getColEff(m)];
		float[] row2 = new float[getColEff(m)];
		row1 = getRow(m, idx1+1);
		row2 = getRow(m, idx2+1);
		for (int i = 0; i < getRowEff(m); i++) {
			for (int j = 0; j < getColEff(m); j++) {
				if (i == idx1) {
					m[i][j] = row2[j];
				}
				if (i == idx2) {
					m[i][j] = row1[j];
				}
			}
		}
		return m;
	}
	
	// switchCol
	public static float[][] switchCol(float[][] m,int idx1, int idx2) {
		float[] col1 = new float[getRowEff(m)];
		float[] col2 = new float[getRowEff(m)];
		col1 = getCol(m, idx1+1);
		col2 = getCol(m, idx2+1);
		for (int i = 0; i < getRowEff(m); i++) {
			for (int j = 0; j < getColEff(m); j++) {
				if (j == idx1) {
					m[i][j] = col2[i];
				}
				if (j == idx2) {
					m[i][j] = col1[i];
				}
			}
		}
		return m;
	}
	
	// multiplyRow
	public static float[][] multiplyRow(float[][] m,int idx) {
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
	
	// multiplyCol belum dicek lagi karena kemungkinan ga kepake
	public static float[][] multiplyCol(float[][] m,int idx) {
		float x = 1;
		for (int i = 0; i < getColEff(m);i++) {
			if (m[idx][i] != 0) {
				x = m[idx][i];
				break;
			}
		}
		for (int i = 0; i < getRowEff(m);i++) {
			for (int j = 0; j < getColEff(m); j++) {
				if (j == idx) {
					m[i][j] = m[i][j] * x;
				}
			}
		}
		return m;
	}
	
	// addRow
	public static float[][] addRow (float[][] m,int idx) {
		int rowIdx = 0; //declare aja dlu
		float [] col = getCol(m, idx+1);
		for (int i = 0; i < getRowEff(m); i++) {
			if (col[i] == 1) {
				rowIdx = i;
				break;
			}
		}
		
		for (int i = rowIdx+1; i < getRowEff(m); i++) {
			float temp = m[i][idx];
			for (int j = 0; j < getColEff(m); j++) {
				m[i][j] -= (temp * m[rowIdx][j]);
			}
		}
		return m;
	}
	
	// addCol
	
	
	// getRow
	public static float[] getRow(float[][] m,int idx) {
		float[] list = new float[getColEff(m)];
		for (int i = 0; i < getColEff(m);i++) {
			list[i] = m[idx-1][i];
		}
		return list;
	}
	// getCol
	public static float[] getCol(float[][] m,int idx) {
		float[] list = new float[getRowEff(m)];
		for (int i = 0; i < getRowEff(m);i++) {
			list[i] = m[i][idx-1];
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
	public static float[][] deleteRow(float[][] m, int idx){
		int rowEff = getRowEff(m)-1;
		int colEff = getColEff(m);
		float[][] m2 = new float[rowEff][colEff];
		int currentIdx = 0;
		for (int i = 0; i<getRowEff(m);i++) {
			if (i != idx-1) {
				for (int j = 0; j<getColEff(m);j++) {
					m2[currentIdx][j] = m[i][j];	
				}
				currentIdx = currentIdx + 1;
			}
		}
		return m2;
	}
	// deleteCol
	public static float[][] deleteCol(float[][] m, int idx){
		int rowEff = getRowEff(m);
		int colEff = getColEff(m)-1;
		float[][] m2 = new float[rowEff][colEff];
		
		for (int i = 0; i<getRowEff(m);i++) {
			int currentIdx = 0;
			for (int j = 0; j<getColEff(m);j++) {
				if (j != idx-1) {
					m2[i][currentIdx] = m[i][j];
					currentIdx = currentIdx + 1;
				}
			}
		}
		return m2;
	}
	
	private static void printMatrix(float[][] m) {
		for (int i = 0; i<m.length;i++) {
			for (int j = 0; j<m[0].length;j++) {
				System.out.printf("%.2g",m[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	private static void printList(float[] list) {
		for (int j = 0; j<list.length;j++) {
			System.out.printf("%.2g",list[j]);
			System.out.print(" ");
		}
		System.out.println("");
	}
	
	// main driver
	public static void main(String[] args) {
		float[][] m = new float[5][10];
		float[][] theo = new float[4][4];
		float count = 1;
		for (int i = 0; i<getRowEff(theo);i++) {
			for (int j = 0; j<getColEff(theo);j++) {
				theo[i][j] = count;
				count += 1;
			}
		}
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
		m = switchRow(m,0,3);
		printMatrix(m);
		System.out.println("");
		m = switchCol(m,2,3);
		printMatrix(m);
		System.out.println("");
		m = multiplyRow(m,2);
		printMatrix(m);
		System.out.println("");
		printMatrix(theo);
		System.out.println("");
		theo = addRow(theo, 0);
		printMatrix(theo);
		System.out.println("");		
	}
}
