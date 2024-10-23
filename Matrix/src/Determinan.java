
public class Determinan {
	public static double minorMatriks(double[][] m){
		// I.S : matriks terdefinisi 2x2
		// F.S : nilai determinan
		return (m[0][0]*m[1][1]) - (m[0][1]*m[1][0]);
	}
	
	public static double DeterminanReduksiBaris(double[][] m){
		double[][] mOut = m;
		int swap = Eselon.countSwap(mOut);
		mOut = Eselon.SortMatriks(mOut);
		swap = swap + Eselon.CountSwapRed(mOut);
		mOut = Eselon.ReduksiBaris(mOut);
		double result;
		result = 1;
		for (int i = 0; i < OBE.getRowEff(mOut); i++) {
			result *= mOut[i][i];
		}
		return (double) (result*Math.pow(-1, swap));
	}
	
	public static double DeterminanKofaktor(double[][] m) {
		if (Kofaktor.isMatriks2x2(m)) {
			return Determinan.minorMatriks(m);
		} else {
			double ctr = 0;
			for (int i = 0; i < OBE.getColEff(m);i++) {
				if (m[0][i] != 0) {
					ctr = ctr + m[0][i]*Kofaktor.kofaktor(m,0,i);
				} 
			}
			return ctr;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] theo2 = {
				{ 3.00d, 148.30d, 223.70d, 87.77d },
				{ 148.30d, 8148.81d, 11093.13d, 4336.52d },
				{ 223.70d, 11093.13d, 16708.19d, 6544.14d },
				{ 87.77d, 4336.52d, 6544.14d, 2567.87d }
			};
		double n = DeterminanKofaktor(theo2);
		System.out.println(n);
	}
}
