
public class Determinan {
	public static float minorMatriks(float[][] m){
		// I.S : matriks terdefinisi 2x2
		// F.S : nilai determinan
		return (m[0][0]*m[1][1]) - (m[0][1]*m[1][0]);
	}
	
	public static float DeterminanReduksiBaris(float[][] m){
		int swap = Eselon.countSwap(m);
		Eselon.SortMatriks(m);
		swap = swap + Eselon.CountSwapRed(m);
		m = Eselon.ReduksiBaris(m);
		float result;
		result = 1;
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			result *= m[i][i];
		}
		return (float) (result*Math.pow(-1, swap));
	}
	
	public static float DeterminanKofaktor(float[][] m) {
		if (Kofaktor.isMatriks2x2(m)) {
			return Determinan.minorMatriks(m);
		} else {
			float ctr = 0;
			for (int i = 0; i < OBE.getColEff(m);i++) {
				ctr = ctr + m[0][i]*Kofaktor.kofaktor(m,0,i);
			}
			return ctr;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float[][] theo2 = {
				{2,-3,1,2,5},
				{4,1,-2,-3,2},
				{5,-4,2,2,-3},
				{3,-1,5,2,1},
				{-4,1,5,-1,2}
				};
		float n = DeterminanReduksiBaris(theo2);
		System.out.println(n);
	}
}
