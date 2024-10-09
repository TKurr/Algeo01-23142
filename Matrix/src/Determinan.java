
public class Determinan {
	public static float minorMatriks(float[][] m){
		// I.S : matriks terdefinisi 2x2
		// F.S : nilai determinan
		return (m[0][0]*m[1][1]) - (m[0][1]*m[1][0]);
	}
	
	// Determinan Reduksi Baris
	public static float DeterminanReduksiBaris(float[][] m){
		float result;
		result = 1;
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			result *= m[i][i];
		}
		return result;
	}

	public static void main(String[] args) {
		float[][] theo2 = {
				{1,1,-1,-1},
				{2,5,-7,-5},
				{2,-1,1,3},
				{5,2,-4,3}
				};
		theo2 = Eselon.ReduksiBaris(theo2);
		OBE.printMatrix(theo2);
		System.out.println("");
		float n;
		n = DeterminanReduksiBaris(theo2);
		System.out.println(n);
	}

}
