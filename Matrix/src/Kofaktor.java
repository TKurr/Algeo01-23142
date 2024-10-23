
public class Kofaktor {
	public static boolean isMatriks2x2(double[][] m) {
		return (OBE.getRowEff(m) == 2 && OBE.getColEff(m) == 2);
	}
	
	public static double kofaktor(double[][] m, int i, int j) {
		m = OBE.deleteCol(m, j+1);
		m = OBE.deleteRow(m, i+1);
		if (isMatriks2x2(m)) {
			return (double) (Math.pow(-1, i+j)*Determinan.minorMatriks(m));
		} else {
			return (double) (Math.pow(-1, i+j)*Determinan.DeterminanKofaktor(m));
		}
	}
	
	public static double[][] MatriksKofaktor(double[][] m){
		double[][] m2 = new double[OBE.getRowEff(m)][OBE.getColEff(m)];
		for (int i = 0; i < OBE.getRowEff(m);i++ ) {
			for (int j = 0; j < OBE.getColEff(m);j++ ) {
				m2[i][j] = kofaktor(m,i,j);
			}
		}
		return m2;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] theo = {
				{3,2,-1},
				{1,6,3},
				{2,-4,0},
		};
		double C00 = Determinan.DeterminanKofaktor(theo);
		System.out.println(C00);
		double C = kofaktor(theo,0,1);
		System.out.println(C);
		theo = MatriksKofaktor(theo);
		OBE.printMatrix(theo);
	}
}
