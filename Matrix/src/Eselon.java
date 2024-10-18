
public class Eselon {
	// column Elimination (eliminates column to zero)
	public static double[][] colElim(double[][] m, int idx) {
		int colIdx = countZero(m, idx);
		double temp = 1;
		for (int i = idx + 1; i < OBE.getRowEff(m); i++) {
			temp = m[i][idx] / m[idx][colIdx];
			for (int j = 0; j < OBE.getColEff(m); j++) {
				m[i][j] -= (temp * m[idx][j]);
			}
		}
		return m;
	}

	// column Elimination 2 (utk eselon tereduksi)
	public static double[][] colElim2(double[][] m, int idx) {
		int colIdx = 0;
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			if (m[idx][i] == 1) {
				colIdx = i;
				break;
			}
		}
		for (int i = 0; i < OBE.getRowEff(m); i++) {

			if (i != idx) {
				double c = (-1) * m[i][colIdx] / m[idx][colIdx];
				m = OBE.addRow(m, i + 1, idx + 1, c);
			}
		}

		return m;
	}

	// row normalization (sets the first value to one)
	public static double[][] rowNorm(double[][] m, int idx) {
		double x = 1;
		for (int i = 0; i < OBE.getColEff(m); i++) {
			if (m[idx][i] != 0) {
				x = m[idx][i];
				break;
			}
		}
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			for (int j = 0; j < OBE.getColEff(m); j++) {
				if (i == idx) {
					m[i][j] = m[i][j] / x;
				}
			}
		}
		return m;
	}

	// Eselon
	public static double[][] MatriksEselon(double[][] m) {
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			rowNorm(m, i);
		}
		return m;
	}

	// Eselon tereduksi
	public static double[][] MatriksEselonTereduksi(double[][] m) {
		for (int i = OBE.getRowEff(m) - 1; i > 0; i--) {
			m = colElim2(m, i);
		}
		return m;
	}

	// Reduksi Baris
	public static double[][] ReduksiBaris(double[][] m) {
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			SortMatriks(m);
			if (i < (OBE.getRowEff(m))) {
				colElim(m, i);
			}
		}
		return m;
	}

	public static int CountSwapRed(double[][] m) {
		int ctr = 0;
		for (int i = 0; i < OBE.getRowEff(m); i++) {
			countSwap(m);
			SortMatriks(m);
			if (i < (OBE.getRowEff(m))) {
				colElim(m, i);
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

	public static void SortMatriks(double[][] m) {
		int n = OBE.getRowEff(m);
		boolean swapped;
		for (int i = 0; i < n - 1; i++) {
			swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (countZero(m, j) > countZero(m, j + 1)) {
					OBE.switchRow(m, j + 1, j + 2);
					swapped = true;
				}
			}
			if (!swapped)
				break;
		}
	}

	public static void main(String[] args) {
		double[][] theo = {
				{ 3.00d, 148.30d, 223.70d, 87.77d },
				{ 148.30d, 8148.81d, 11093.13d, 4336.52d },
				{ 223.70d, 11093.13d, 16708.19d, 6544.14d },
				{ 87.77d, 4336.52d, 6544.14d, 2567.87d }
		};
		// System.out.println(countSwap(theo));
		SortMatriks(theo);
		theo = ReduksiBaris(theo);
		OBE.printMatrix(theo);
		double n = Determinan.DeterminanReduksiBaris(theo);
		System.out.println(n);
	}
}
