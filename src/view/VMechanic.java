package src.view;

import java.util.*;
import src.model.*;
import src.model.people.*;
import src.model.vehicles.*;
import src.controller.MobilitySystem;

public class VMechanic {
    public static void displayMenu(Mechanic mechanic, Scanner scanner, MobilitySystem system) {
        while (true) {
            mechanic.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 0) {
                break;
            }

            mechanic.handleOption(choice, scanner, system);
        }
    }
} 