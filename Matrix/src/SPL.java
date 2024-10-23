
public class SPL {
    public static double[][] elimGaussJordan(double[][] m){
    	double [][] mOut = m;
		mOut = Eselon.SortMatriks(mOut);
        mOut = Eselon.ReduksiBaris(mOut);
        mOut = Eselon.MatriksEselon(mOut);
        mOut = Eselon.MatriksEselonTereduksi(mOut);
        return mOut;
    }

	public static double[][] inverseSPL(double[][] A, double[][] b) {
		double[][] x;
		double[][] inverseA = Inverse.InverseAdjoin(A);
		OBE.printMatrix(inverseA);
		x = OBE.multiplyBetweenMatrix(inverseA, b);
		return x;
	}
    
    public static boolean isSolution(double[][] m) {
        int rowCount = OBE.getRowEff(m); 
        int colCount = OBE.getColEff(m);

        for (int i = 0; i < rowCount; i++) {
            boolean allZero = true;
            for (int j = 0; j < colCount - 1; j++) { 
                if (m[i][j] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero && m[i][colCount - 1] != 0) {
                return false; 
            }
        }
        return true; 
    }

    public static boolean isInfiniteSolution(double[][] m) {
		return (OBE.getRowEff(m) < OBE.getColEff(m) - 1);
    }

    public static void printParametric(double[][] m) {
		int i,j;
		for (i = 0; i < OBE.getRowEff(m); i++) {
			String stringTemp = "X" + (i+1);
			stringTemp = stringTemp + " = " + String.valueOf(m[i][OBE.getColEff(m)-1]);
			boolean satuUtama = false;
			for (j = 0; j < OBE.getColEff(m)-1; j++) {
				if (satuUtama) {
					if (m[i][j] < 0) {
						stringTemp = stringTemp + " + " + (-m[i][j]) + "X" + (j+1);
					} else if (m[i][j] > 0) {
						stringTemp = stringTemp + " - " + (m[i][j]) + "X" + (j+1);
					}
				} 
				if (m[i][j] == 1) {
					satuUtama = true;
				}
			}
			System.out.println(stringTemp);
		}
    }
    
    public static void printParametric2(double[][] m) {
		int i, j;
		for (i = 0; i < OBE.getRowEff(m); i++){
			System.out.print("X" +  (i+1) + " = " + m[i][OBE.getColEff(m)-1]); //ubah i+1 nya ntar
			for (j = OBE.getColEff(m) - OBE.getRowEff(m); j < OBE.getColEff(m) - 1; j++){
				if (m[i][j] > 0){
					System.out.print("+");
				} 
				System.out.println(m[i][j]);
			}
		}

    }
    
    public static double[] listSolution(double[][] m) {
    	// matriks sudah berbentuk matriks eselon tereduksi
    	// solusi matriks unik
		double[] result = new double[OBE.getRowEff(m)];
		for (int i = 0; i < result.length; i++) {
			result[i] = m[i][result.length];
		}
		return result;

    }
    
    public static double[][] copyMatrix(double[][] m){
    	double[][] mOut = new double[OBE.getRowEff(m)][OBE.getColEff(m)];
    	for (int i = 0; i < OBE.getRowEff(mOut); i++) {
    		for (int j = 0; j < OBE.getColEff(mOut); j++) {
    			mOut[i][j] = m[i][j];
    		}
    	}
    	return mOut;
    }

    public static double[] Cramer(double[][] m, double[] n) {
    	int row = OBE.getRowEff(m);
    	int col = OBE.getColEff(m);
    	double det, tempDet;
    	double[][] copy = new double[row][col];
    	det = Determinan.DeterminanKofaktor(m);
    	double[] result = new double[col];
    	for (int i = 0; i < row; i++) {
    		copy = copyMatrix(m);
    		for (int j = 0; j < row; j++) {
        		for (int k = 0; k < col; k++) {
        			if (k == i) {
        				copy[j][k] = n[j];
        			}
        		}
        	}
    		tempDet = Determinan.DeterminanKofaktor(copy);
    		result[i] = tempDet/det;
    	}
    	return result;
    }
    
    public static void main(String[] args) {
		double[][] A = {
			{1, 2, 3},
			{2, 5, 3},
			{1, 0, 8}
		};
	
		double[][] b = {
			{1},
			{0},
			{2}
		};
		double[][] m = {
		 		{-1,2,-3},
		 		{2,0,1},
		 		{3,-4,4},
		 };
         
         double[] n = {1,0,2};

		double[][] x = inverseSPL(m, b);
		OBE.printMatrix(x);
         
         System.out.println("");
         double[] cramer = Cramer(m, n);
         OBE.printList(cramer);

        // m = elimGaussJordan(m);
        // System.out.println(isSolution(m));
        // OBE.printMatrix(m);
        // printParametric(m);
        // OBE.printList(listSolution(m));
    }
}
