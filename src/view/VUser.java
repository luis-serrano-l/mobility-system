package src.view;

import java.util.*;
import src.model.*;
import src.model.people.*;
import src.model.vehicles.*;
import src.controller.MobilitySystem;

public class VUser {
    public static void displayMenu(User user, Scanner scanner, MobilitySystem system) {
        while (true) {
            user.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 0) {
                break;
            }

            user.handleOption(choice, scanner, system);
        }
    }
} 