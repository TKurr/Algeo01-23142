
public class Inverse {
	public static float[][] Transpose(float[][] m){
		float [][] t = new float[OBE.getRowEff(m)][OBE.getRowEff(m)];
		int i, j;
		for (i = 0; i < OBE.getRowEff(t); i++) {
			for (j = 0; j < OBE.getRowEff(t); j++) {
				t[i][j] = m[j][i];
			}
			
		}
		
		return t;
	}
	
	public static float[][] InverseAdjoin(float[][] m){
		float[][] adjoin, newM = new float[OBE.getRowEff(m)][OBE.getRowEff(m)];
		float det;
		det = Determinan.DeterminanKofaktor(m);
		adjoin = Kofaktor.MatriksKofaktor(m);
		newM = OBE.multiplyMatrix(adjoin, 1/det);
		return newM;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float[][] theo = {
				{1,2,3},
				{2,5,3},
				{1,0,8},
		};
		OBE.printMatrix(theo);
		System.out.println("");
		theo = Transpose(theo);
		OBE.printMatrix(theo);
		System.out.println("");
		theo = InverseAdjoin(theo);
		OBE.printMatrix(theo);
		
	}

}
