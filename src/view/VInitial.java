package src.view;

import java.util.Scanner;
import src.controller.MobilitySystem;
import src.model.people.User;
import src.model.people.Worker;
import src.model.people.Mechanic;
import src.model.people.Admin;
import src.utils.UserGenerator;

public class VInitial {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayMainMenu() {
        System.out.println("\n=== City Mobility System ===");
        System.out.println("1. Login");
        System.out.println("2. Generate Mock Data");
        System.out.println("3. Save Data");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void handleMainMenuOption(int option, MobilitySystem system) {
        switch (option) {
            case 1:
                login(system);
                break;
            case 2:
                generateMockData(system);
                break;
            case 3:
                saveData(system);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void login(MobilitySystem system) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = system.login(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            displayUserMenu(user, system);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void displayUserMenu(User user, MobilitySystem system) {
        boolean loggedIn = true;
        while (loggedIn) {
            user.displayMenu();
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Check for logout options for different user types
            if ((user instanceof User && option == 0) || 
                (user instanceof Worker && option == 4) || 
                (user instanceof Mechanic && option == 0) || 
                (user instanceof Admin && option == 5)) {
                System.out.println("Logging out...");
                loggedIn = false;
                break;
            }

            user.handleOption(option, scanner, system);
        }
    }

    private static void generateMockData(MobilitySystem system) {
        UserGenerator.generateMockData(system);
        System.out.println("Mock data generated successfully.");
    }

    private static void saveData(MobilitySystem system) {
        system.saveData();
        System.out.println("Data saved successfully.");
    }
} 