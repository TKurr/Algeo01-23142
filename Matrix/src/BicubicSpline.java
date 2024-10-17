public class BicubicSpline {
	
	public static int function(int i, int j, int x, int y) {
		return (int) (Math.pow(x, i) * Math.pow(y, j));
	}
	
	public static int functionX(int i, int j, int x, int y) {
		return (int) (i * Math.pow(x, i-1) * Math.pow(y, j));
	}
	
	public static int functionY(int i, int j, int x, int y) {
		return (int) (j * Math.pow(x, i) * Math.pow(y, j-1));
	}
	
	public static int functionXY(int i, int j, int x, int y) {
		return (int) (i * j * Math.pow(x, i-1) * Math.pow(y, j-1));
	}
	
	public static float[][] matriksY(float[][] m){
		float[][] Y = new float[16][1];
		for (int i = 0; i < 16; i++) {
			
		}
		return Y;
	}
	
	public static float derivativeX(float[][] m, int i, int j) {
		return (m[i + 1][j] - m[i - 1][j]) / 2;
	}
	
	public static float derivativeY(float[][] m, int i, int j) {
		return (m[i][j+1] - m[i][j-1]) / 2;
	}
	
	public static float derivativeXY(float[][] m, int i, int j) {
		return (m[i + 1][j + 1] - m[i + 1][j - 1] - m[i - 1][j + 1] + m[i - 1][j - 1]) / 4;
	}
	
	public static float[][] addMatriksX(){
		float[][] X = new float[16][17];
		for (int i = 0; i < 16; i++) {
			int x = (i)%2;
			int y = Math.floorDiv(((i)%4),2);
			for (int j = 0; j < 16; j++) {
				if (i < 4) {
					X[i][j] = function(j%4,Math.floorDiv(j,4),x,y);
				} else if (i >= 4 && i < 8) {
					X[i][j] = functionX(j%4,Math.floorDiv(j,4),x,y);
				} else if (i >= 8 && i < 12) {
					X[i][j] = functionY(j%4,Math.floorDiv(j,4),x,y);
				} else {
					X[i][j] = functionXY(j%4,Math.floorDiv(j,4),x,y);
				}
				
			}
		}
		return X;
	}
	
	public static float[][] addMatriksY(float[][] m, float[][] grid){
		for (int idx = 0; idx < 16; idx++) {
			int i = (idx)%2 + 1;
			int j = Math.floorDiv(((idx)%4),2) + 1;
			if (idx < 4) {
				m[idx][16] = grid[i][j];
			} else if (idx >= 4 && idx < 8) {
				m[idx][16] = derivativeX(grid,i,j);
			} else if (idx >= 8 && idx < 12) {
				m[idx][16] = derivativeY(grid,i,j);
			} else {
				m[idx][16] = derivativeXY(grid,i,j);
			}
		}
		return m;
	}
	
	public static float[] getListOfA(float[][] m) {
		m = SPL.elimGaussJordan(m);
		return SPL.listSolution(m);
	}
	
	public static float finalFunction(float x, float y, float[] a) {
		float result = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int indexA = i*4 + j;
//				System.out.println(indexA);
//				System.out.print(j);
//				System.out.println(i);
				result = (float) (result + a[indexA]*(Math.pow(x, j) * Math.pow(y, i)));
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float[][] theo = {
				{21,98,125,153},
				{51,101,161,59},
				{0,42,72,210},
				{16,12,81,96},
		};
		float[][] m = addMatriksX();
		m = addMatriksY(m,theo);
//		OBE.printMatrix(m);
		float[] a = getListOfA(m);
		OBE.printList(a);
		float result = finalFunction((float) 0.75,(float) 0.25,a);
		System.out.println(result);
	}
}
