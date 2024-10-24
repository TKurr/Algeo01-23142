import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteFile {
    public static void write(String kalimat) {
    	System.out.println("Save to file (Y/N)? ");
    	String option = "";
    	Scanner sc = new Scanner(System.in);
    	option = sc.nextLine().trim().toUpperCase();
		if (option.equals("Y")) {
			 // Get the current working directory
	        String currentDirectory = System.getProperty("user.dir");
	        currentDirectory += "\\test\\output";
	        
	        // Take file name input from the user
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter the file name (with extension, e.g., file.txt): ");
	        String fileName = scanner.nextLine();
	        
	        // Create folder and file
	        File directory = new File(currentDirectory); // Use the current directory
	        File file = new File(directory, fileName);

	        try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw)) {
	            // Write content to the file
	            bw.write(kalimat);

	            System.out.println("File '" + fileName + "' created and content written successfully!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	           
	        }
		} else if (option.equals("N")) {
			System.out.println("Oghe");
		}
       
    }

    public static void main(String[] args) {
    	String kalimat = "a";
    	write(kalimat);
    }
}
