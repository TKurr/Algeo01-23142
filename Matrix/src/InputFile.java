import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFile {
	public static File getFile(String filename) throws FileNotFoundException {
		File inputFile = new File("test/" + filename);

        Scanner scanner = new Scanner(inputFile);

        scanner.close();
        return inputFile;
	}

    public static boolean checkFile(String filename) {
        try {
            File inputFile = new File("test/" + filename);

            Scanner scanner = new Scanner(inputFile);

            scanner.close();
            
            System.out.println("File found");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
    }
    
    public static boolean checkImage(String filename) {
        try {
            File inputFile = new File("public/" + filename);

            Scanner scanner = new Scanner(inputFile);

            scanner.close();
            
            System.out.println("File found");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
    	if (checkFile("soal1a.txt")) {
    		File inputFile = getFile("soal1a.txt");
    	};
    }
}
