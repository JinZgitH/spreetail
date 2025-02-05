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

    /* TODO: java doc */
    private void add(String[] args) {
        // TODO: arguments validation
        boolean res = map.computeIfAbsent(args[1], k -> new HashSet<>()).add(args[2]);
        println(res ? "Added" : "ERROR, member already exists for key");
    }

    /* TODO: java doc */
    private void remove(String[] args) {
        // TODO: arguments validation
        Set<String> set = map.get(args[1]);
        if (set == null) {
            println("ERROR, key does not exist.");
        } else {
            boolean res = set.remove(args[2]);
            println(res ? "Removed" : "ERROR, member does not exist");
            if (set.isEmpty()) map.remove(args[1]);
        }

    }

    /* TODO: java doc */
    private void members(String[] args) {
        // TODO: arguments validation
        Set<String> set = map.get(args[1]);
        if (set == null) {
            println("ERROR, key does not exist.");
        } else {
            printSet(set);
        }
    }

    /* TODO: java doc */
    private void keys() {
        // TODO: arguments validation
        printSet(map.keySet());
    } 

    private static void printSet(Set<String> set) {
        int count = 1;
        for (String str: set) {
            println(count++ + ") " + str);
        }
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

    private static void println(String string) {
        System.out.println(string);
    }

    public static void main(String[] args) {
        println("Starting MultiValueDictApp: ");
        println("Enter '" + HELP_COMMAND_STRING + "' for supported commands.");

        MultiValueDictApp app = new MultiValueDictApp();

        while (true) {
            System.out.print("> ");

            String inputStr = scanner.nextLine();
            String[] inputArr = inputStr.split(" ");
            switch (inputArr[0]) {
                case "add":
                    app.add(inputArr);
                    break;
                case "remove":
                    app.remove(inputArr);
                    break;
                case "members":
                    app.members(inputArr);
                    break;
                case "keys":
                    app.keys();
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
