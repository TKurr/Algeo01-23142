
public class Kofaktor {
	public static boolean isMatriks2x2(double[][] m) {
		double [][] mOut = m;
		return (OBE.getRowEff(mOut) == 2 && OBE.getColEff(mOut) == 2);
	}
	
	public static double kofaktor(double[][] m, int i, int j) {
		double [][] mOut = m;
		mOut = OBE.deleteCol(mOut, j+1);
		mOut = OBE.deleteRow(mOut, i+1);
		if (isMatriks2x2(mOut)) {
			return (double) (Math.pow(-1, i+j)*Determinan.minorMatriks(mOut));
		} else {
			return (double) (Math.pow(-1, i+j)*Determinan.DeterminanKofaktor(mOut));
		}
	}
	
	public static double[][] MatriksKofaktor(double[][] m){
		double [][] mOut = m;
		double[][] m2 = new double[OBE.getRowEff(mOut)][OBE.getColEff(mOut)];
		for (int i = 0; i < OBE.getRowEff(mOut);i++ ) {
			for (int j = 0; j < OBE.getColEff(mOut);j++ ) {
				m2[i][j] = kofaktor(mOut,i,j);
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
