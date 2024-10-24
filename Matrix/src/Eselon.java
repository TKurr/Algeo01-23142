
public class Eselon {
	// column Elimination (eliminates column to zero)
	public static double[][] colElim(double[][] m, int idx) {
		double [][] mOut = m;
		int colIdx = Math.min(countZero(mOut, idx),OBE.getColEff(mOut)-1);
		double temp = 1;
		for (int i = idx + 1; i < OBE.getRowEff(mOut); i++) {
			temp = mOut[i][colIdx] / mOut[idx][colIdx];
			for (int j = 0; j < OBE.getColEff(mOut); j++) {
				mOut[i][j] -= (temp * mOut[idx][j]);
			}
		}
		return mOut;
	}

	// column Elimination 2 (utk eselon tereduksi)
	public static double[][] colElim2(double[][] m, int idx) {
		double [][] mOut = m;
		int colIdx = countZero(mOut,idx);
		for (int i = 0; i < OBE.getRowEff(mOut); i++) {

			if (i != idx) {
				double c = (-1) * mOut[i][colIdx] / mOut[idx][colIdx];
				mOut = OBE.addRow(mOut, i + 1, idx + 1, c);
			}
		}
		return mOut;
	}

	// row normalization (sets the first value to one)
	public static double[][] rowNorm(double[][] m, int idx) {
		double [][] mOut = m;
		double x = 1;
		for (int i = 0; i < OBE.getColEff(mOut); i++) {
			if (mOut[idx][i] != 0) {
				x = mOut[idx][i];
				break;
			}
		}
		for (int i = 0; i < OBE.getRowEff(mOut); i++) {
			for (int j = 0; j < OBE.getColEff(mOut); j++) {
				if (i == idx) {
					mOut[i][j] = mOut[i][j] / x;
				}
			}
		}
		return mOut;
	}

	// Eselon
	public static double[][] MatriksEselon(double[][] m) {
		double [][] mOut = m;
		for (int i = 0; i < OBE.getRowEff(mOut); i++) {
			rowNorm(mOut, i);
		}
		return mOut;
	}

	// Eselon tereduksi
	public static double[][] MatriksEselonTereduksi(double[][] m) {
		double [][] mOut = m;
		for (int i = OBE.getRowEff(mOut) - 1; i > 0; i--) {
			mOut = colElim2(mOut, i);
		}
		return mOut;
	}

	// Reduksi Baris
	public static double[][] ReduksiBaris(double[][] m) {
		double [][] mOut = m;
		for (int i = 0; i < OBE.getRowEff(mOut); i++) {
			mOut = SortMatriks(mOut);
			if (i < (OBE.getRowEff(mOut))) {
				colElim(mOut, i);
			}
		}
		return mOut;
	}

	public static int CountSwapRed(double[][] m) {
		int ctr = 0;
		double [][] mOut = m;
		for (int i = 0; i < OBE.getRowEff(mOut); i++) {
			countSwap(mOut);
			mOut = SortMatriks(mOut);
			if (i < (OBE.getRowEff(mOut))) {
				colElim(mOut, i);
			}
		}
		return ctr;
	}

	public static int countZero(double[][] m, int idx) {
		int ctr = 0;
		for (int i = 0; i < OBE.getColEff(m); i++) {
			if (m[idx][i] != 0) {
				return ctr;
			}
			ctr++;
		}
		return ctr;
	}

	public static int countSwap(double[][] m) {
		int n = OBE.getRowEff(m);
		int ctr = 0;
		boolean swapped;
		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (countZero(m, j) > countZero(m, j + 1)) {
					ctr = ctr + 1;
					swapped = true;
				}
			}
			if (!swapped)
				break;
		}
		return ctr;
	}

	public static double[][] SortMatriks(double[][] m) {
		int n = OBE.getRowEff(m);
		double[][] mOut = m;
		boolean swapped;
		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (countZero(mOut, j) > countZero(mOut, j + 1)) {
					OBE.switchRow(mOut, j + 1, j + 2);
					swapped = true;
				}
			}
			if (!swapped)
				break;
		}
		return mOut;
	}

	public static void main(String[] args) {
		double[][] theo = {
				{ 3.00d, 148.30d, 223.70d, 87.77d },
				{ 148.30d, 8148.81d, 11093.13d, 4336.52d },
				{ 223.70d, 11093.13d, 16708.19d, 6544.14d },
				{ 87.77d, 4336.52d, 6544.14d, 2567.87d }
		};
		// System.out.println(countSwap(theo));
		theo =SortMatriks(theo);
		theo = ReduksiBaris(theo);
		OBE.printMatrix(theo);
		double n = Determinan.DeterminanReduksiBaris(theo);
		System.out.println(n);
	}
}
