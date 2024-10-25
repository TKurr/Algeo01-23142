# Tugas Besar 1 - Aljabar Linear dan Geometri

> **Linear Algebra Calculator**
> Implementation of Linear Systems, Regression and Interpolation with the Java Programming Language

## Description

This project is a Java-based terminal application designed to solve various linear algebra problems. The program can handle systems of linear equations (SPL), calculate matrix determinants and inverses, perform polynomial interpolation, bicubic spline interpolation, and conduct multiple regression analyses.

## Authors

1. **Nathanael Rachmat** (13523142)
2. **Andrew Tedjapratama** (13523148)
3. **Theo Kurniady** (13523154)

Group Name: **Lastesest**

## Installing / Getting Started

### Requirements

-   Java Development Kit (JDK) version 8 or higher
-   A Java-capable IDE (e.g., Eclipse, IntelliJ IDEA), or you can use a text editor with terminal support.

### Running the Program

1. **Clone the Repository**:
   Open a terminal and run the following command to clone the repository:

    ```bash
    git clone https://https://github.com/Stahlynx/Algeo01-23142
    ```

2. **Open the Project in a Java-capable IDE**:

    - Open your preferred Java IDE (e.g., **Eclipse** or **IntelliJ IDEA**).
    - Import the cloned project by selecting **File** > **Open Projects from File System...** or similar options, depending on your IDE.
    - Navigate to the directory where you cloned the repository, and open it.

3. **Run the Main Class**:

    - In the **src** directory, locate the `main.java.LinearAlgebraCalculator` (or the appropriate main class).
    - Right-click the file and select **Run As** > **Java Application** (in Eclipse) or the equivalent option in other IDEs.

    The program will start running in the terminal provided by your IDE.

4. **Input and Output**:

    - The program accepts input via the terminal or from `.txt` files for matrix data.

## Features

-   **Solving Systems of Linear Equations (SPL)**:

    -   Use **Gaussian Elimination** and **Gauss-Jordan Elimination** to find solutions.
    -   Solve using **Matrix Inversion** (if the matrix is invertible).
    -   Apply **Cramer’s Rule** for small systems where the matrix determinant can be calculated efficiently.

-   **Matrix Operations**:

    -   Compute the **determinant** of any square matrix.
    -   Find the **inverse** of an invertible matrix.
    -   Perform **matrix multiplication** and **scalar multiplication**.

-   **Polynomial Interpolation**:
    -   Perform **Lagrange Polynomial Interpolation** or **Newton's Interpolation** to estimate values between data points.
-   **Bicubic Spline Interpolation**:

    -   Implement **Bicubic Spline Interpolation** to estimate values for two-dimensional data grids, often used in image processing tasks like resizing.

-   **Regression Analysis**:

    -   Conduct **Multiple Linear Regression** to model relationships between multiple variables.
    -   Perform **Quadratic Regression** for fitting data to a quadratic model.
      
-   **Image Resizer**:
    -   Accepts image, new height, and new width (in pixels) as the input.
    -   Outputs a resized image with smooth quality.

-   **Input and Output**:
    -   Accepts input from the terminal (manual input) or from text files (matrix data).
    -   Outputs results directly to the terminal or to a downloadable `.txt` file.

## Usage

Once the program is running, you can use it to perform various linear algebra operations. Below are some of the main features:

-   Solving Linear Equations: You can input matrices manually or from files to solve systems of equations using Gaussian elimination, matrix inversion, or Cramer’s rule.

-   Matrix Operations: Perform operations such as matrix multiplication, finding determinants, and calculating inverses.

-   Interpolation: Use polynomial interpolation or bicubic spline interpolation to estimate unknown values from known data points.

-   Regression Analysis: Input data for regression analysis to determine relationships between variables or predict future outcomes.
  
-   Image Resizer: Place your image in the Matrix/public folder. When prompted, enter the file name of the image (example: image.png). The resized image will be saved in the Matrix/test/output/images folder.

## Technologies Used

-   **Java**
