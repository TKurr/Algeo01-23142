
public class Kofaktor {
	public static boolean isMatriks2x2(float[][] m) {
		return (OBE.getRowEff(m) == 2 && OBE.getColEff(m) == 2);
	}
	
	public static float kofaktor(float[][] m, int i, int j) {
		m = OBE.deleteCol(m, j+1);
		m = OBE.deleteRow(m, i+1);
		if (isMatriks2x2(m)) {
			return (float) (Math.pow(-1, i+j)*Determinan.minorMatriks(m));
		} else {
			return (float) (Math.pow(-1, i+j)*Determinan.DeterminanKofaktor(m));
		}
	}
	
	public static float[][] MatriksKofaktor(float[][] m){
		float[][] m2 = new float[OBE.getRowEff(m)][OBE.getColEff(m)];
		for (int i = 0; i < OBE.getRowEff(m);i++ ) {
			for (int j = 0; j < OBE.getColEff(m);j++ ) {
				m2[i][j] = kofaktor(m,i,j);
			}
		}
		return m2;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float[][] theo = {
				{3,2,-1},
				{1,6,3},
				{2,-4,0},
		};
		float C00 = Determinan.DeterminanKofaktor(theo);
		System.out.println(C00);
		float C = kofaktor(theo,0,1);
		System.out.println(C);
		theo = MatriksKofaktor(theo);
		OBE.printMatrix(theo);
	}
}
