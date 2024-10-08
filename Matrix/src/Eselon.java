
public class Eselon {
	// Gauss
	public static float[][] MatriksEselon(float[][] m){
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			boolean gss = true;
			if (i < (OBE.getRowEff(m))) {
				OBE.rowNorm(m,i);
				OBE.colElim(m,i,gss);
			}
		}
		return m;
	}
	
	// Reduksi Baris
	public static float[][] ReduksiBaris(float[][] m){
		boolean gss = false;
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			if (i < (OBE.getRowEff(m))) {
				OBE.colElim(m,i,gss);
			}
		}
		return m;
	}
	public static void main(String[] args) {
		float[][] theo = {
				{1,1,-1,-1},
				{2,5,-7,-5},
				{2,-1,1,3},
				{5,2,-4,3}
				};
		theo = ReduksiBaris(theo);
		OBE.printMatrix(theo);
	}
	// Eselon tereduksi

}
