import java.util.Scanner;
import src.model.people.User;
import src.model.people.Worker;
import src.model.people.Mechanic;
import src.model.people.Admin;
import src.controller.MobilitySystem;
import src.utils.UserGenerator;

public class movilidad {
    private static User currentUser;
    private static MobilitySystem system;
    private static Scanner scanner;

    public static void main(String[] args) {
        system = new MobilitySystem();
        scanner = new Scanner(System.in);
        
        while (true) {
            displayMainMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (option == 0) {
                break;
            }
            
            handleMainOption(option);
        }
        
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=== City Mobility System ===");
        System.out.println("1. Login");
        System.out.println("2. Generate Mock Data");
        System.out.println("3. Save Data");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleMainOption(int option) {
        switch (option) {
            case 1:
                login();
                break;
            case 2:
                UserGenerator.generateMockData(system);
                System.out.println("Mock data generated successfully.");
                break;
            case 3:
                system.saveData();
                System.out.println("Data saved successfully.");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        currentUser = system.login(username, password);
        if (currentUser != null) {
            System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
            handleUserMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void handleUserMenu() {
        while (currentUser != null) {
            currentUser.displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Check for logout options for different user types
            if ((currentUser instanceof User && option == 0) || 
                (currentUser instanceof Worker && option == 4) || 
                (currentUser instanceof Mechanic && option == 0) || 
                (currentUser instanceof Admin && option == 5)) {
                System.out.println("Logging out...");
                currentUser = null;
                break;
            }
            
            currentUser.handleOption(option, scanner, system);
        }
    }
} 