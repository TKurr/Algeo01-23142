import java.io.File;
import java.io.FileNotFoundException;
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
        double[][] matrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            double x = points[i][0];
            double y = points[i][1];
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.pow(x, j);
            }
            matrix[i][n] = y;
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

    public double[] backSubstitution(double[][] matrix) {
        int n = matrix.length;
        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            solution[i] = matrix[i][n];
        }
        return solution;
    }

    public double interpolate(double x) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * Math.pow(x, i);
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of points: ");
        int N = scanner.nextInt();
     
        double[][] points = new double[N][2];
        int choose = -1;
        System.out.println("1. Input dari file");
        System.out.println("2. Input Manual");
        while (choose != 1 || choose != 2 ) {
        	System.out.print("Pilihan: ");
        	choose = scanner.nextInt();
        	if (choose == 1) {
        		Scanner scanner1 = new Scanner(System.in);
        		String filename;
		    	System.out.println("Tulis nama file disini");
		    	filename = scanner1.nextLine();
		    	if (InputFile.checkFile(filename)) {
		    		File file = InputFile.getFile(filename);
			    	Scanner scanners = new Scanner(file);
			    	
			    	int totalLine = 0;
			    	int lineLength = 0;
			    	String lineTemp = scanners.nextLine();  
	                String[] valuesTemp = lineTemp.split(" ");
	                lineLength = valuesTemp.length;
	                totalLine = totalLine + 1;
			    	
			    	while (scanners.hasNextLine()) {
		                String line = scanners.nextLine();  
		                String[] values = line.split(" ");
		                lineLength = values.length;
			            totalLine = totalLine + 1;
			            }
			    	scanners.close();
			    	
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
			    	points = newM;
			    	scanner2.close();
		    		}
            	break;
            } else{
            	System.out.println("Enter points (x,y): ");
                for (int i = 0; i < N; i++) {
                    System.out.println("X-" + i + ": ");
                    points[i][0] = scanner.nextDouble();

                    System.out.println("Y-" + i + ": ");
                    points[i][1] = scanner.nextDouble();
                }
            }
        }
        

        InterpolasiPolinomial interpolation = new InterpolasiPolinomial(points);

        System.out.print("Enter x: ");
        double valX = scanner.nextDouble();

        double valY = interpolation.interpolate(valX);
        System.out.println("Estimated/Interpolated Y value: " + valY);
        String kalimat = "Estimated/Interpolated Y value: " + String.valueOf(valY);
        WriteFile.write(kalimat);
        scanner.close();
    }
}