import java.util.Scanner;

public class InterpolasiPolinomial {
    public double[][] points;
    public double[] a;

    public InterpolasiPolinomial(double[][] points) {
        this.points = normalizeX(points);
        this.a = calculateCoefficient();
    }

    public double[][] normalizeX(double[][] points) {
        double meanX = 0;
        for (int i = 0; i < points.length; i++) {
            meanX += points[i][0];
        }
        meanX /= points.length;

        for (int i = 0; i < points.length; i++) {
            points[i][0] -= meanX;
        }

        return points;
    }

    public double[] calculateCoefficient() {
        int n = points.length;

        // Build augmented matrix from point inputs
        double[][] matrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            double x = points[i][0];
            double y = points[i][1];
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.pow(x, j);
            }
            matrix[i][n] = y;  // Last column = y
        }

        double[][] solvedMatrix = elimGaussJordan(matrix);
        double[] solution = backSubstitution(solvedMatrix);

        return solution;
    }

    public double[][] elimGaussJordan(double[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k;
                }
            }
            double[] temp = matrix[i];
            matrix[i] = matrix[maxRow];
            matrix[maxRow] = temp;
            for (int k = i + 1; k <= n; k++) {
                matrix[i][k] /= matrix[i][i];
            }
            matrix[i][i] = 1.0;

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double ratio = matrix[j][i];
                    for (int k = i; k <= n; k++) {
                        matrix[j][k] -= ratio * matrix[i][k];
                    }
                }
            }
        }
        return matrix;
    }

    // Back substitution to get solution
    public double[] backSubstitution(double[][] matrix) {
        int n = matrix.length;
        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            solution[i] = matrix[i][n];
        }
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of points: ");
        int N = scanner.nextInt();
        
        // Input points
        double[][] points = new double[N][2];
        System.out.println("Enter points (x,y): ");
        for (int i = 0; i < N; i++) {
            System.out.println("X-" + i + ": ");
            points[i][0] = scanner.nextDouble();

            System.out.println("Y-" + i + ": ");
            points[i][1] = scanner.nextDouble();
        }

        InterpolasiPolinomial interpolation = new InterpolasiPolinomial(points);

        // Input X
        System.out.print("Enter x: ");
        double valX = scanner.nextDouble();

        double valY = interpolation.interpolate(valX);
        System.out.println("Estimated/Interpolated Y value: " + valY);
        scanner.close();
    }
}