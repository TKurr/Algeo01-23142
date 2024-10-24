import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
            // Partial pivoting
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k;
                }
            }
            // Swap maximum row with current row (column by column)
            double[] temp = matrix[i];
            matrix[i] = matrix[maxRow];
            matrix[maxRow] = temp;

            // Make all rows below this one 0 in current column
            for (int k = i + 1; k < n; k++) {
                double factor = matrix[k][i] / matrix[i][i];
                for (int j = i; j <= n; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
            }
        }

        // Back substitution
        for (int i = n - 1; i >= 0; i--) {
            matrix[i][n] /= matrix[i][i];
            matrix[i][i] = 1.0;
            for (int k = i - 1; k >= 0; k--) {
                double factor = matrix[k][i];
                matrix[k][n] -= factor * matrix[i][n];
                matrix[k][i] = 0.0;
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

    // Method to read points from a file
    public static double[][] readPointsFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            // Read number of points
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid file format: First line should be an integer representing the number of points.");
                scanner.close();
                return null;
            }
            int N = scanner.nextInt();
            double[][] points = new double[N][2];
            
            // Read each point
            for (int i = 0; i < N; i++) {
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid file format: Expected x-coordinate for point " + (i + 1));
                    scanner.close();
                    return null;
                }
                points[i][0] = scanner.nextDouble();
                
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid file format: Expected y-coordinate for point " + (i + 1));
                    scanner.close();
                    return null;
                }
                points[i][1] = scanner.nextDouble();
            }
            
            scanner.close();
            System.out.println("Points successfully read from file.");
            return points;
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return null;
        }
    }

    // Method to display menu
    public static void displayMenu() {
        System.out.println("\nMENU INTERPOLASI POLINOMIAL");
        System.out.println("1. Input titik secara manual");
        System.out.println("2. Baca titik dari file");
        System.out.println("3. Interpolasi titik");
        System.out.println("4. Keluar");
        System.out.print("Pilih menu (1-4): ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[][] points = null;
        InterpolasiPolinomial interpolation = null;
        boolean isDataLoaded = false;

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // Manual input
                System.out.print("Masukkan jumlah titik (N): ");
                int N = 0;
                try {
                    N = Integer.parseInt(scanner.nextLine());
                    if (N <= 0) {
                        System.out.println("Jumlah titik harus positif.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harus berupa integer.");
                    continue;
                }

                points = new double[N][2];
                for (int i = 0; i < N; i++) {
                    System.out.println("Titik ke-" + (i + 1) + ":");
                    System.out.print("X-" + (i + 1) + ": ");
                    try {
                        points[i][0] = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid. Harus berupa angka.");
                        i--; // Retry this point
                        continue;
                    }

                    System.out.print("Y-" + (i + 1) + ": ");
                    try {
                        points[i][1] = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid. Harus berupa angka.");
                        i--; // Retry this point
                    }
                }

                interpolation = new InterpolasiPolinomial(points);
                isDataLoaded = true;
                System.out.println("Titik berhasil dimasukkan secara manual.");

            } else if (choice.equals("2")) {
                // Read from file
                System.out.print("Masukkan nama file (misal: points.txt): ");
                String filename = scanner.nextLine();
                points = readPointsFromFile(filename);
                if (points != null) {
                    interpolation = new InterpolasiPolinomial(points);
                    isDataLoaded = true;
                }

            } else if (choice.equals("3")) {
                // Perform interpolation
                if (!isDataLoaded) {
                    System.out.println("Data titik belum dimasukkan. Silakan input terlebih dahulu.");
                    continue;
                }

                System.out.print("Masukkan nilai X untuk diinterpolasi: ");
                double valX = 0;
                try {
                    valX = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harus berupa angka.");
                    continue;
                }

                double valY = interpolation.interpolate(valX);
                System.out.println("Nilai Y yang diinterpolasi: " + valY);

            } else if (choice.equals("4")) {
                // Exit
                System.out.println("Terima kasih telah menggunakan program ini.");
                break;

            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih menu yang tersedia.");
            }
        }

        scanner.close();
    }
}
