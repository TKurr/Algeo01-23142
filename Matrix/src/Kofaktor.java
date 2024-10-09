
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float[][] theo = {
				{3,-1,2},
				{5,0,4},
				{8,2,-3},
		};
		float C00 = Determinan.DeterminanKofaktor(theo);
		System.out.println(C00);
		float C = kofaktor(theo,0,1);
		System.out.println(C);
	}

}
