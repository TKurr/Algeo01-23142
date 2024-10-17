import java.util.Scanner;

public class InterpolasiPolinomial {
	public float[][] points;
	public float[] a;

	public InterpolasiPolinomial(float[][] points) {
		this.points = points;
		this.a = calculateCoefficient();
	}

	// Calculate solution from points using gauss (jordan) method
	public float[] calculateCoefficient() {
        int n = points.length;

		// Build augmented matrix from point inputs
        float[][] matrix = new float[n][n + 1];
        for (int i = 0; i < n; i++) {
            float x = points[i][0];
            float y = points[i][1];
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (float) Math.pow(x, j);
            }
            matrix[i][n] = y;  // Last column = y
        }
		float[][] solvedMatrix = SPL.elimGaussJordan(matrix);
		float[] solution = SPL.listSolution(solvedMatrix);
	
		return solution;
	}

	// Interpolate method to find Y / Pn(x)
    public float interpolate(float x) {
        float result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * Math.pow(x, i);
        }
        return result;
    }


	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {

			// Input N (number of point inputs)
			System.out.print("Enter the number of points: "); // N+1
			int N = scanner.nextInt();
			
			// Input points
			float[][] points = new float[N+1][2];
			System.out.println("Enter points (x,y): ");
			for (int i = 0; i < N; i++) {
				System.out.print("X-" + (i) + ": ");
				points[i][0] = scanner.nextFloat();

				System.out.print("Y-" + (i) + ": ");
				points[i][1] = scanner.nextFloat();
			}
			
			InterpolasiPolinomial interpolation = new InterpolasiPolinomial(points);
			
			// Input X
			System.out.print("Enter x: ");
			float valX = scanner.nextFloat();
			
			// Output
			float valY = interpolation.interpolate(valX);
			System.out.println("Estimated/Interpolated Y value: " + valY);
		}
	}
}