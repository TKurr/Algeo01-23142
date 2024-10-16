
public class SPL {
    public static float[][] elimGaussJordan(float[][] m){
    	Eselon.SortMatriks(m);
        m = Eselon.ReduksiBaris(m);
        m = Eselon.MatriksEselon(m);
        m = Eselon.MatriksEselonTereduksi(m);
        return m;
    }
    
    public static boolean isSolution(float[][] m) {
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

    public static boolean isInfiniteSolution(float[][] m) {
    	return (OBE.getRowEff(m) < OBE.getColEff(m) - 1);
    }

    public static void printParametric(float[][] m) {
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
    
    public static void printParametric2(float[][] m) {
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
    
    public static float[] listSolution(float[][] m) {
    	// matriks sudah berbentuk matriks eselon tereduksi
    	// solusi matriks unik
    	float[] result = new float[OBE.getRowEff(m)];
    	for (int i = 0; i < result.length; i++) {
    		result[i] = m[i][result.length];
    	}
    	return result;
    	
    }

    public static void main(String[] args) {
        float[][] m = {
				{0,0,1,3},
				{0,1,0,5},
				{1,0,0,5},
		};
        m = elimGaussJordan(m);
        System.out.println(isSolution(m));
        OBE.printMatrix(m);
        printParametric(m);
        OBE.printList(listSolution(m));
    }
}
