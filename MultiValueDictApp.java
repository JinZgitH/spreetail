import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MultiValueDictApp {
    private static final String EXIT_COMMAND_STRING = "exit";
    private static final String HELP_COMMAND_STRING = "help";

    private static final String HELP_FILE = "HELP.md";
    private static final Scanner scanner = new Scanner(System.in);

    private Map<String, Set<String>> map;

    public MultiValueDictApp() {
        this.map = new HashMap<>();
    }

    private String add(String[] args) {
        // TODO: invalid argument handling
        boolean res = map.computeIfAbsent(args[1], k -> new HashSet<>()).add(args[2]);
        return res ? "Added" : "ERROR, member already exists for key";
    }
    private static void printHelp() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(HELP_FILE));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading README file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting MultiValueDictApp: ");
        System.out.println("Enter '" + HELP_COMMAND_STRING + "' for supported commands.");

        MultiValueDictApp app = new MultiValueDictApp();

        while (true) {
            System.out.print("> ");

            String inputStr = scanner.nextLine();
            String[] inputArr = inputStr.split(" ");
            switch (inputArr[0]) {
                case "add":
                    System.out.println(app.add(inputArr));
                    break;
                case HELP_COMMAND_STRING:
                    printHelp();
                    break;
                case EXIT_COMMAND_STRING:
                    System.out.println("Exiting MultiValueDictApp!");
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

}
