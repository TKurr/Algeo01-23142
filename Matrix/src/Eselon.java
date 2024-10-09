
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
		
	public static void main(String[] args) {
		float n;
		float[][] theo = {
				{1,1,-1,-1,10},
				{2,5,-7,-5,4},
				{2,-1,1,3,6},
				{5,2,-4,3,1}
				};
		float[][] theo2 = {
				{1,1,-1,-1},
				{2,5,-7,-5},
				{2,-1,1,3},
				{5,2,-4,3}
				};
		theo = ReduksiBaris(theo);
		OBE.printMatrix(theo);
		System.out.println("");
		theo = MatriksEselon(theo);
		OBE.printMatrix(theo);
		System.out.println("");
		theo = MatriksEselonTereduksi(theo);
		OBE.printMatrix(theo);
		System.out.println("");
		theo2 = ReduksiBaris(theo2);
		OBE.printMatrix(theo2);
		System.out.println("");
	}
}
