import java.util.Scanner;

public class InterpolasiPolinomial {
	public double[][] points;
	public double[] a;

	public InterpolasiPolinomial(double[][] points) {
		this.points = points;
		this.a = calculateCoefficient();
	}

	// Calculate solution from points using gauss (jordan) method
	public double[] calculateCoefficient() {
        int n = points.length;

		// Build augmented matrix from point inputs
        double[][] matrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            double x = points[i][0];
            double y = points[i][1];
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (double) Math.pow(x, j);
            }
            matrix[i][n] = y;  // Last column = y
        }
		double[][] solvedMatrix = SPL.elimGaussJordan(matrix);
		double[] solution = SPL.gaussJordanSPL(solvedMatrix);
	
		return solution;
	}

	// Interpolate method to find Y / Pn(x)
    public double interpolate(double x) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * Math.pow(x, i);
        }
        return result;
    }


	public static void main(String[] args) {
		// Input N (number of point inputs)
		System.out.println("Enter the number of points: "); // N+1
		int N = OBE.inputInteger();
		
		// Input points
		double[][] points = new double[N][2];
		System.out.println("Enter points (x,y): ");
		for (int i = 0; i < N; i++) {
			System.out.println("X-" + (i) + ": ");
			points[i][0] = OBE.inputDouble();

			System.out.println("Y-" + (i) + ": ");
			points[i][1] = OBE.inputDouble();
		}
		
		InterpolasiPolinomial interpolation = new InterpolasiPolinomial(points);
		
		// Input X
		System.out.print("Enter x: ");
		double valX = OBE.inputDouble();
		
		// Output
		double valY = interpolation.interpolate(valX);
		System.out.println("Estimated/Interpolated Y value: " + valY);
		
	}
}