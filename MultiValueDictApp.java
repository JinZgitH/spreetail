import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MultiValueDictApp {
    private static final String EXIT_COMMAND_STRING = "exit";
    // private static final String README_FILE = "README.md";
    private static final String HELP_FILE = "HELP.md";
    private static final Scanner scanner = new Scanner(System.in);


    private static void printReadme() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(HELP_FILE));
            System.out.println("\n=== README CONTENT ===");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("======================\n");
        } catch (IOException e) {
            System.out.println("Error reading README file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter your command: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    printReadme();
                    break;
                case EXIT_COMMAND_STRING:
                    System.out.println("Exiting the App!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
