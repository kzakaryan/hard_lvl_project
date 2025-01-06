package am.main;

import server.Database;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Database db = new Database();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 3);

            switch (parts[0]) {
                case "set":
                    if (parts.length == 3) {
                        try {
                            int index = Integer.parseInt(parts[1]);
                            String text = parts[2];
                            System.out.println(db.set(index, text)); // Call set and output result
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR");
                        }
                    } else {
                        System.out.println("ERROR");
                    }
                    break;

                case "get":
                    if (parts.length == 2) {
                        try {
                            int index = Integer.parseInt(parts[1]);
                            System.out.println(db.get(index)); // Call get and output result
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR");
                        }
                    } else {
                        System.out.println("ERROR");
                    }
                    break;

                case "delete":
                    if (parts.length == 2) {
                        try {
                            int index = Integer.parseInt(parts[1]);
                            System.out.println(db.delete(index)); // Call delete and output result
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR");
                        }
                    } else {
                        System.out.println("ERROR");
                    }
                    break;

                case "exit":
                    System.out.println("Bye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("ERROR");
            }
        }
    }
}