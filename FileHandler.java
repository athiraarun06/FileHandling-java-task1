import java.io.*;
import java.util.Scanner;

public class FileHandler {

    static final String FILE_NAME = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Java File Handling Utility");
        System.out.println("1. Write to File");
        System.out.println("2. Read from File");
        System.out.println("3. Modify File Content");
        System.out.print("Choose an option (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                writeToFile(scanner);
                break;
            case 2:
                readFromFile();
                break;
            case 3:
                modifyFile(scanner);
                break;
            default:
                System.out.println("Invalid option!");
        }

        scanner.close();
    }

    public static void writeToFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            System.out.println("Enter text to write to the file (type 'exit' to stop):");
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data successfully written to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("Reading contents of " + FILE_NAME + ":
");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void modifyFile(Scanner scanner) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            reader.close();

            System.out.print("Enter the word to replace: ");
            String oldWord = scanner.nextLine();
            System.out.print("Enter the new word: ");
            String newWord = scanner.nextLine();

            String modifiedContent = content.toString().replaceAll(oldWord, newWord);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(modifiedContent);
            writer.close();

            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}