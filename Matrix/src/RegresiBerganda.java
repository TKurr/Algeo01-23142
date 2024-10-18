import java.util.Scanner;

public class RegresiBerganda {
	// Fungsi untuk menghitung koefisien regresi
    public static double[] calculateCoefficients(double[][] X, double[] Y) {
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
//        OBE.printMatrix(XTXInv);
//        System.out.println("");
//        OBE.printMatrix(XTXInv);
//        System.out.println(""); // for debugging
        // Koefisien regresi: (X^T * X)^-1 * (X^T * Y)
        double[] coefficients = new double[n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	coefficients[i] += XTXInv[i][j] * XTY[j];
            	
            }
        }
        
        return coefficients;
    }
	
    public static void LinearRegression() {
    	Scanner sc = new Scanner(System.in);
      
        // Input jumlah variabel (n) dan jumlah sampel (m)
        System.out.print("Masukkan jumlah variabel n: ");
        int n = sc.nextInt();
        System.out.print("Masukkan jumlah sampel m: ");
        int m = sc.nextInt();
        double[][] X = new double[m][n+1];
        double[] Y = new double[m];
        
        // Input nilai-nilai x1i, x2i, ..., xni, dan yi
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
        
        // Kalkulasi koefisien regresi menggunakan (X^T * X)^-1 * X^T * Y
        double[] coefficients = calculateCoefficients(X, Y);
        
        System.out.printf("f(x) = %.4f", coefficients[0]);
        for (int i = 1; i < coefficients.length; i++) {
        	if (coefficients[i] >= 0) {
        		System.out.print(" + ");
        	} else {
        		coefficients[i] = -coefficients[i];
        		System.out.print(" - ");
        	}
        	System.out.printf("%.4fX", coefficients[i]);
        	System.out.printf("%d", i);
        }
        System.out.println("");
        
        // Input nilai-nilai xk yang akan ditaksir nilai fungsinya
        double[] xk = new double[n+1];
        xk[0] = 1;
        System.out.println("Masukkan nilai-nilai xk:");
        for (int i = 1; i <= n; i++) {
            xk[i] = sc.nextDouble();
        }
        
        // Prediksi nilai yk
        double yk = 0;
        for (int i = 0; i <= n; i++) {
            yk += coefficients[i] * xk[i];
        }
        System.out.printf("f(xk) = %.4f\n", yk);   
        sc.close();
    }
    
    public static void main(String[] args) {
        LinearRegression();
    }
    
    
}
    