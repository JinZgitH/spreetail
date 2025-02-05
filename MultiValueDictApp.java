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
    private static final String ERROR_NO_KEY = "ERROR, key does not exist.";
    private static final String ERROR_NO_MEMBER = "ERROR, member does not exist.";

    private static final String HELP_FILE = "HELP.md";
    private static final Scanner scanner = new Scanner(System.in);

    private Map<String, Set<String>> map;

    public MultiValueDictApp() {
        this.map = new HashMap<>();
    }

    /* TODO: java doc */
    private void add(String[] args) {
        if (mismatchArgsCount(args.length, 3)) return;
        boolean res = map.computeIfAbsent(args[1], k -> new HashSet<>()).add(args[2]);
        println(res ? "Added" : "ERROR, member already exists for key");
    }

    /* TODO: java doc */
    private void remove(String[] args) {
        if (mismatchArgsCount(args.length, 3)) return;
        Set<String> set = map.get(args[1]);
        if (set == null) {
            println(ERROR_NO_KEY);
        } else {
            boolean res = set.remove(args[2]);
            println(res ? "Removed" : ERROR_NO_MEMBER);
            if (set.isEmpty()) map.remove(args[1]);
        }
    }

    /* TODO: java doc */
    private void removeall(String[] args) {
        if (mismatchArgsCount(args.length, 2)) return;
        if (map.remove(args[1]) == null) {
            println(ERROR_NO_KEY);
        } else {
            println("Removed");
        }
    }

    /* TODO: java doc */
    private void clear() {
        // TODO: arguments validation
        map.clear();
        println("Cleared");
    }

    /* TODO: java doc */
    private void members(String[] args) {
        if (mismatchArgsCount(args.length, 2)) return;
        Set<String> set = map.get(args[1]);
        if (set == null) {
            println(ERROR_NO_KEY);
        } else {
            printSet(set);
        }
    }

    /* TODO: java doc */
    private void allMembers() {
        // TODO: arguments validation
        int count = 1;
        for (Set<String> set : map.values()) {
            count = printSet(set, count);
        }
    }

    /* TODO: java doc */
    private void items() {
        // TODO: arguments validation
        int count = 1;
        for (String key : map.keySet()) {
            Set<String> value = map.get(key);
            count = printSet(value, count, key);
        }
    }

    /* TODO: java doc */
    private void keys() {
        // TODO: arguments validation
        printSet(map.keySet());
    } 

    /* TODO: java doc */
    private void keyExists(String[] args) {
        if (mismatchArgsCount(args.length, 2)) return;
        println("" + map.containsKey(args[1]));
    } 

    /* TODO: java doc */
    private void memberExists(String[] args) {
        if (mismatchArgsCount(args.length, 3)) return;
        Set<String> set = map.get(args[1]);
        if (set == null) {
            println("false");
        } else {
            println("" + set.contains(args[2]));
        }    
    } 

    private static void printSet(Set<String> set) {
        printSet(set, 1);
    }

    private static int printSet(Set<String> set, int count) {
        return printSet(set, count, "");
    }

    private static int printSet(Set<String> set, int count, String key) {
        String midString = key.length() == 0 ? ") " : ") " + key + ": ";
        for (String str: set) {
            println(count++ + midString + str);
        }
        return count;
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

    private static boolean mismatchArgsCount(int argsLength, int expectedLength) {
        if (argsLength == expectedLength) {
            return false;
        } else {
            println("Invalid argument counts: " + argsLength + " while expecting " + expectedLength);
            return true;
        }
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
                case "removeall":
                    app.removeall(inputArr);
                    break;
                case "clear":
                    app.clear();
                    break;
                case "members":
                    app.members(inputArr);
                    break;
                case "allmembers":
                    app.allMembers();
                    break;
                case "items":
                    app.items();
                    break;
                case "keys":
                    app.keys();
                    break;
                case "keyexists":
                    app.keyExists(inputArr);
                    break;
                case "memberexists":
                    app.memberExists(inputArr);
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
