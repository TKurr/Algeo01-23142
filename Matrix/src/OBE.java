
public class OBE {
	
	// switchRow
	// switchCol
	// multiplyRow
	// multiplyCol
	// addRow
	// addCol
	// getRow
	public static int[] getRow(int[][] m,int idx) {
		int[] list = new int[getColEff(m)];
		for (int i = 0; i < getColEff(m);i++) {
			list[i] = m[idx-1][i];
		}
		return list;
	}
	// getCol
	public static int[] getCol(int[][] m,int idx) {
		int[] list = new int[getRowEff(m)];
		for (int i = 0; i < getRowEff(m);i++) {
			list[i] = m[i][idx-1];
		}
		return list;
	}
	// getRowEff
	public static int getRowEff(int[][] m) {
		return m.length;
	}
	// getColEff
	public static int getColEff(int[][] m) {
		return m[0].length;
	}
	
	// deleteRow
	public static int[][] deleteRow(int[][] m, int idx){
		int rowEff = getRowEff(m)-1;
		int colEff = getColEff(m);
		int[][] m2 = new int[rowEff][colEff];
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
	public static int[][] deleteCol(int[][] m, int idx){
		int rowEff = getRowEff(m);
		int colEff = getColEff(m)-1;
		int[][] m2 = new int[rowEff][colEff];
		
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
	
	private static void printMatrix(int[][] m) {
		for (int i = 0; i<m.length;i++) {
			for (int j = 0; j<m[0].length;j++) {
				System.out.print(m[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	private static void printList(int[] list) {
		for (int j = 0; j<list.length;j++) {
			System.out.print(list[j]);
			System.out.print(" ");
		}
		System.out.println("");
	}
	
	// main driver
	public static void main(String[] args) {
		int[][] m = new int[5][10];
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
	}

}
