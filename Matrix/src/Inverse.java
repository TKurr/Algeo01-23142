
public class Inverse {
	public static double[][] Transpose(double[][] m){
		double [][] t = new double[OBE.getColEff(m)][OBE.getRowEff(m)];
		int i, j;
		for (i = 0; i < OBE.getColEff(t); i++) {
			for (j = 0; j < OBE.getRowEff(t); j++) {
				t[j][i] = m[i][j];
			}
			
		}
		
		return t;
	}
	
	public static double[][] InverseAdjoin(double[][] m){
		double[][] adjoin, newM = new double[OBE.getColEff(m)][OBE.getRowEff(m)];
		double det;
		adjoin = Transpose(m);
		det = Determinan.DeterminanReduksiBaris(m);
		adjoin = Kofaktor.MatriksKofaktor(adjoin);
		newM = OBE.multiplyMatrix(adjoin, 1/det);
		return newM;
	}
	
	public static double[][] identityMatrix(int n){
		double[][] m = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					m[i][j] = 1;
				} else {
					m[i][j] = 0;
				}
			}
		}
		
		return m;
	}
	
	public static double[][] InverseIdentity(double[][] m){
		int row = OBE.getRowEff(m);
		int col = 2*OBE.getColEff(m);
		double[][] identity = identityMatrix(row);
		double[][] process = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (j < col/2) {
					process[i][j] = m[i][j];
				} else {
					process[i][j] = identity[i][j-3];
				}
			}
		}
		process = SPL.elimGaussJordan(process);
		double[][] mOut = new double[row][col/2];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col/2; j++) {
				mOut[i][j] = process[i][j+3];
			}
		}
		return mOut;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] theo = {
				{1,2,3},
				{2,5,3},
				{1,0,8},
		};
		
		OBE.printMatrix(theo);
		// System.out.println("");
		// theo = Transpose(theo);
		// OBE.printMatrix(theo);
		// System.out.println("");
		theo = InverseAdjoin(theo);
		OBE.printMatrix(theo);
		System.out.println("");
		double[][] m = {
				{1,2,3},
				{2,5,3},
				{1,0,8},
		};
		m = InverseIdentity(m);
		OBE.printMatrix(m);
	}

}
