public class BicubicSpline {

	public static int function(int i, int j, int x, int y) {
		return (int) (Math.pow(x, i) * Math.pow(y, j));
	}

	public static int functionX(int i, int j, int x, int y) {
		return (int) (i * Math.pow(x, i - 1) * Math.pow(y, j));
	}

	public static int functionY(int i, int j, int x, int y) {
		return (int) (j * Math.pow(x, i) * Math.pow(y, j - 1));
	}

	public static int functionXY(int i, int j, int x, int y) {
		return (int) (i * j * Math.pow(x, i - 1) * Math.pow(y, j - 1));
	}

	public static double[][] matriksY(double[][] m) {
		double[][] Y = new double[16][1];
		for (int i = 0; i < 16; i++) {

		}
		return Y;
	}

	public static double derivativeX(double[][] m, int i, int j) {
		return (m[i + 1][j] - m[i - 1][j]) / 2;
	}

	public static double derivativeY(double[][] m, int i, int j) {
		return (m[i][j + 1] - m[i][j - 1]) / 2;
	}

	public static double derivativeXY(double[][] m, int i, int j) {
		return (m[i + 1][j + 1] - m[i + 1][j - 1] - m[i - 1][j + 1] + m[i - 1][j - 1]) / 4;
	}

	public static double[][] addMatriksX() {
		double[][] X = new double[16][17];
		for (int i = 0; i < 16; i++) {
			int x = (i) % 2;
			int y = Math.floorDiv(((i) % 4), 2);
			for (int j = 0; j < 16; j++) {
				if (i < 4) {
					X[i][j] = function(j % 4, Math.floorDiv(j, 4), x, y);
				} else if (i >= 4 && i < 8) {
					X[i][j] = functionX(j % 4, Math.floorDiv(j, 4), x, y);
				} else if (i >= 8 && i < 12) {
					X[i][j] = functionY(j % 4, Math.floorDiv(j, 4), x, y);
				} else {
					X[i][j] = functionXY(j % 4, Math.floorDiv(j, 4), x, y);
				}

			}
		}
		return X;
	}

	public static double[][] addMatriksY(double[][] m, double[][] grid) {
		double[][] mOut = m;
		for (int idx = 0; idx < 16; idx++) {
			int i = (idx) % 2 + 1;
			int j = Math.floorDiv(((idx) % 4), 2) + 1;
			if (idx < 4) {
				mOut[idx][16] = grid[i][j];
			} else if (idx >= 4 && idx < 8) {
				mOut[idx][16] = derivativeX(grid, i, j);
			} else if (idx >= 8 && idx < 12) {
				mOut[idx][16] = derivativeY(grid, i, j);
			} else {
				mOut[idx][16] = derivativeXY(grid, i, j);
			}
		}
		return mOut;
	}

	public static double[] getListOfA(double[][] m) {
		return SPL.gaussJordanSPL(m);
	}

	public static double finalFunction(double x, double y, double[] a) {
		double result = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int indexA = i * 4 + j;
				// System.out.println(indexA);
				// System.out.print(j);
				// System.out.println(i);
				result = (double) (result + a[indexA] * (Math.pow(x, j) * Math.pow(y, i)));
			}
		}
		return result;
	}

	public static double[][] matrixSolution(double[] a) {
		double[][] m = new double[4][4];
		int n = 4;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				double result = finalFunction((double) (i + 1) / (n) + 0.125d, (double) (j + 1) / (n) + 0.125d, a);
				m[i][j] = result;
			}
		}
		return m;
	}

	public static double[][] pixelSmoothening(double[][] m) {
		double[][] mOut = addMatriksX();
		mOut = addMatriksY(mOut, m);
		double[] a = getListOfA(mOut);
		mOut = matrixSolution(a);
		return mOut;
	}
	
	public static double bicubic(double[][] m,double x, double y) {
		double[][] mOut = addMatriksX();
		for (int i = 0; i < 16; i++) {
			int idx = Math.floorDiv(i, 4);
			int jdx = i%4;
			mOut[i][16] = m[idx][jdx];
		}
		double[] a = getListOfA(mOut);
		return finalFunction((double) x, (double) y, a);
	}

	public static void main(String[] args) {
		double[][] testMatrix = {
				{ 21, 98, 125, 152},
				{ 51, 101, 161, 59 },
				{ 0, 42, 72, 210 },
				{ 16, 12, 81, 96 }
		};
		OBE.printMatrix(testMatrix);
		System.out.print("X: ");
		double x = OBE.inputDouble();
		System.out.print("Y: ");
		double y = OBE.inputDouble();
		System.out.println(bicubic(testMatrix,x,y));
	}
}
