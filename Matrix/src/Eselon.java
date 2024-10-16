
public class Eselon {
	// column Elimination (eliminates column to zero)
	public static float[][] colElim (float[][] m,int idx) {
		float temp = 1;
		for (int i = idx+1; i < OBE.getRowEff(m); i++) {
			temp = m[i][idx] / m[idx][idx];
			for (int j = 0; j < OBE.getColEff(m); j++) {
				m[i][j] -= (temp * m[idx][j]);
			}
		}
		return m;
	}
	
	// column Elimination 2 (utk eselon tereduksi)
		public static float[][] colElim2 (float[][] m,int idx) {
			int colIdx = 0;
			for (int i = 0; i<OBE.getRowEff(m);i++) {
				if (m[idx][i] == 1) {
					colIdx = i;
					break;
				}
			}
			for (int i = 0; i<OBE.getRowEff(m);i++) {
				
				if (i != idx) {
					float c = (-1)*m[i][colIdx] / m[idx][colIdx];
					m= OBE.addRow(m,i+1,idx+1,c);
				}	
			}
			
			return m;
		}
	
	// row normalization (sets the first value to one)
	public static float[][] rowNorm(float[][] m,int idx) {
		float x = 1;
		for (int i = 0; i < OBE.getColEff(m); i++) {
			if (m[idx][i] != 0) {
				x = m[idx][i];
				break;
			}
		}
		for (int i = 0; i < OBE.getRowEff(m);i++) {
			for (int j = 0; j < OBE.getColEff(m); j++) {
				if (i == idx) {
					m[i][j] = m[i][j] / x;
				}
			}
		}
		return m;
	}
	
	// Eselon
	public static float[][] MatriksEselon(float[][] m){
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			rowNorm(m,i);
		}
		return m;
	}
	
	// Eselon tereduksi
	public static float[][] MatriksEselonTereduksi(float[][] m){
		for (int i = OBE.getRowEff(m)-1; i > 0; i--) {
			m = colElim2(m,i);
		}
		return m;
	}
	
	// Reduksi Baris
	public static float[][] ReduksiBaris(float[][] m){
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			if (i < (OBE.getRowEff(m))) {
				colElim(m,i);
			}
		}
		return m;
	}
	
	public static int countZero(float[][] m, int idx) {
		int ctr = 0;
		for (int i = 0; i < OBE.getColEff(m); i++) {
			if (m[idx][i] != 0) {
				return ctr;
			}
			ctr++;
		}
		return ctr;
	}
	
	public static int countSwap(float[][] m) {
		int n = OBE.getRowEff(m);
		int ctr = 0;
		boolean swapped;
		for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (countZero(m,j) > countZero(m,j+1)) {
                    ctr = ctr + 1;
                    swapped = true;
                }
            }
            if (!swapped) break;
		}
		return ctr;
	}
	
	public static void SortMatriks(float[][] m) {
		int n = OBE.getRowEff(m);
		boolean swapped;
		for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (countZero(m,j) > countZero(m,j+1)) {
                    OBE.switchRow(m, j+1, j+2);
                    swapped = true;
                }
            }
            if (!swapped) break;
		}
	}
	
	public static void main(String[] args) {
		float[][] theo = {
				{0,1,-1,-1,10},
				{0,0,-7,-5,4},
				{2,-1,1,3,6},
				{5,2,-4,3,1}
				};
		System.out.println(countSwap(theo));
		SortMatriks(theo);
		OBE.printMatrix(theo);
//		theo = ReduksiBaris(theo);
//		OBE.printMatrix(theo);
//		System.out.println("");
//		theo = MatriksEselon(theo);
//		OBE.printMatrix(theo);
//		System.out.println("");
//		theo = MatriksEselonTereduksi(theo);
//		OBE.printMatrix(theo);
//		System.out.println("");
	}
}
