import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegresiBerganda {
	// Fungsi untuk menghitung koefisien regresi
    public static double[] calculateLinearCoefficients(double[][] X, double[] Y) {
        int m = OBE.getRowEff(X);
        int n = OBE.getColEff(X);
        double[][] XT = new double[n][m];
        XT = Inverse.Transpose(X);
        double[][] XTX = new double[n][n];
        XTX = OBE.multiplyBetweenMatrix(XT, X);
        
        // Matriks X^T * Y
        double[] XTY = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                XTY[i] += XT[i][j] * Y[j];
            }
        }
       

        // Invers dari matriks X^T * X
        double[][] XTXInv = Inverse.InverseAdjoin(XTX);
        
        // Koefisien regresi: (X^T * X)^-1 * (X^T * Y)
        double[] coefficients = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	coefficients[i] += XTXInv[i][j] * XTY[j];
            }
        }
        
        return coefficients;
    }
    
    public static double[] calculateQuadraticCoefficients(double[][] X, double[] Y) {
        int m = OBE.getRowEff(X);
        int n = OBE.getColEff(X);
        double[][] X2 = new double[m][n-1];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n-1; j++) {
        		X2[i][j] = Math.pow(X[i][j+1], 2);
        	}
        }
        double[][] newX = new double[m][2*n-1];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < 2*n-1; j++) {
        		if (j < n) {
        			newX[i][j] = X[i][j];
        		} else {
        			newX[i][j] = X2[i][j-n];
        		}
        	}
        }
        n = 2*n-1;
        double[][] XT = new double[n][m];
        XT = Inverse.Transpose(newX);
        double[][] XTX = new double[n][n];
        XTX = OBE.multiplyBetweenMatrix(XT, newX);
        
        // Matriks X^T * Y
        double[] XTY = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                XTY[i] += XT[i][j] * Y[j];
            }
        }
        
        // Invers dari matriks X^T * X
        double[][] XTXInv = Inverse.InverseAdjoin(XTX);
        
        // Koefisien regresi: (X^T * X)^-1 * (X^T * Y)
        double[] coefficients = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	coefficients[i] += XTXInv[i][j] * XTY[j];
            }
        }
        return coefficients;
    }
	
    public static void MultipleRegression() throws FileNotFoundException {
    	Scanner sc = new Scanner(System.in);
      
        // Input jumlah variabel (n) dan jumlah sampel (m)
        System.out.print("Masukkan jumlah variabel n: ");
        int n = sc.nextInt();
        System.out.print("Masukkan jumlah sampel m: ");
        int m = sc.nextInt();
        double[][] X = new double[m][n+1];
        double[] Y = new double[m];
        
        int choose = -1;
        System.out.println("1. Input dari file");
        System.out.println("2. Input Manual");
        while (choose != 1 || choose != 2 ) {
        	System.out.print("Pilihan: ");
        	choose = sc.nextInt();
        	if (choose == 1) {
        		Scanner scanner1 = new Scanner(System.in);
        		String filename;
		    	System.out.println("Tulis nama file disini");
		    	filename = scanner1.nextLine();
		    	if (InputFile.checkFile(filename)) {
		    		File file = InputFile.getFile(filename);
			    	Scanner scanner = new Scanner(file);
			    	
			    	int totalLine = 0;
			    	int lineLength = 0;
			    	String lineTemp = scanner.nextLine();  
	                String[] valuesTemp = lineTemp.split(" ");
	                lineLength = valuesTemp.length;
	                totalLine = totalLine + 1;
			    	
			    	while (scanner.hasNextLine()) {
		                String line = scanner.nextLine();  
		                String[] values = line.split(" ");
		                lineLength = values.length;
			            totalLine = totalLine + 1;
			            }
			    	scanner.close();
			    	
			    	Scanner scanner2 = new Scanner(file);
			    	double[][] newM = new double[totalLine][lineLength];
			    	
			    	int ctr = 0;
			    	while (scanner2.hasNextLine()) {
		                String line = scanner2.nextLine();  
		                String[] values = line.split(" ");
		                for (int i = 0; i < values.length; i++) {
			                    newM[ctr][i] = Double.parseDouble(values[i]);
			                }
		                ctr = ctr+1;
			            }
			    	
			    	Y = OBE.getCol(newM, 1);
			    	for (int i = 0; i < OBE.getRowEff(newM); i++) {
			    		for (int j = 0; j < OBE.getColEff(newM); j++) {
			    			if (j == 0){
			    				newM[i][j] = 1;
			    			}
			    		}
			    	}
			    	X = newM;
			    	scanner2.close();
		    		}
            	break;
            } else{
            	System.out.println("Masukkan nilai-nilai xi dan yi:");
                for (int i = 0; i < m; i++) {
                	Y[i] = sc.nextDouble();
                	Y[i] = Math.round(Y[i] * 100.0) / 100.0;
                    X[i][0] = 1;
                    for (int j = 1; j <= n; j++) {
                        X[i][j] = sc.nextDouble();
                        X[i][j] = Math.round(X[i][j] * 100.0) / 100.0;
                    }
                }
            	break;
            }
        }
        // Input nilai-nilai x1i, x2i, ..., xni, dan yi
        
        boolean linear = false;
        System.out.println("1. Multiple Linear Regression");
        System.out.println("2. Multiple Quadratic Regression");
        choose = -1;
        double[] coefficients = new double[n+1];
        while (choose != 1 || choose != 2 ) {
        	System.out.print("Pilihan: ");
        	choose = sc.nextInt();
        	if (choose == 1) {
            	coefficients = calculateLinearCoefficients(X, Y);
            	linear = true;
            	break;
            } else if (choose == 2){
            	coefficients = calculateQuadraticCoefficients(X, Y);
            	break;
            }
        }
        
        
        System.out.printf("f(x) = %.4f", coefficients[0]);
        double temp = 0;
        for (int i = 1; i < coefficients.length; i++) {
        	if (coefficients[i] >= 0) {
        		System.out.print(" + ");
        		temp = coefficients[i];
        	} else {
        		temp = -coefficients[i];
        		System.out.print(" - ");
        	}
        	System.out.printf("%.4fX", temp);
        	if (linear) {
        		System.out.printf("%d", i);
        	}else {
        		if (i < (coefficients.length/2)+1) {
        			System.out.printf("%d", i);
        		}else {
        			System.out.printf("%d²", i-coefficients.length/2);
        		}
        	}
        }
        System.out.println("");
        
        // Input nilai-nilai xk yang akan ditaksir nilai fungsinya
        double[] xk = new double[n+1];
        System.out.println("Masukkan nilai-nilai xk:");
        for (int i = 0; i <= n; i++) {
            xk[i] = sc.nextDouble();
        }
        
        // Prediksi nilai yk
        double yk = 0;
        for (int i = 0; i <= n; i++) {
            yk += coefficients[i] * xk[i];
        }
        System.out.printf("f(xk) = %.4f\n", yk);
        String kalimat = "f(xk) = " + String.valueOf(yk);
        choose = -1;
        WriteFile.write(kalimat);
        sc.close();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	MultipleRegression();
    }
}
    
