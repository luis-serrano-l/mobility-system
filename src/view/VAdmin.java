package src.view;

import java.util.Scanner;
import src.controller.MobilitySystem;
import src.model.locations.Station;
import src.model.vehicles.Vehicle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Motorcycle;
import src.model.vehicles.Scooter;
import src.model.locations.Location;
import src.model.people.standard.StandardUser;
import src.model.people.admin.Admin;

import java.util.List;

public class VAdmin {
    private MobilitySystem mobilitySystem;
    private Admin admin;
    private Scanner scanner;

    public VAdmin(MobilitySystem mobilitySystem, Admin admin) {
        this.mobilitySystem = mobilitySystem;
        this.admin = admin;
        this.scanner = new Scanner(System.in);
    }

    public void showAdminMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all vehicles");
            System.out.println("2. View all stations");
            System.out.println("3. View all users");
            System.out.println("4. Add vehicle");
            System.out.println("5. Remove vehicle");
            System.out.println("6. Add station");
            System.out.println("7. Remove station");
            System.out.println("8. Update user balance");
            System.out.println("9. Update user premium status");
            System.out.println("10. View system statistics");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllVehicles();
                    break;
                case 2:
                    viewAllStations();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    addVehicle();
                    break;
                case 5:
                    removeVehicle();
                    break;
                case 6:
                    addStation();
                    break;
                case 7:
                    removeStation();
                    break;
                case 8:
                    updateUserBalance();
                    break;
                case 9:
                    updateUserPremiumStatus();
                    break;
                case 10:
                    viewSystemStatistics();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllVehicles() {
        System.out.println("\n=== All Vehicles ===");
        for (Vehicle vehicle : mobilitySystem.getVehicles()) {
            System.out.println(vehicle);
        }
    }

    private void viewAllStations() {
        System.out.println("\n=== All Stations ===");
        for (Station station : mobilitySystem.getStations()) {
            System.out.println(station);
        }
    }

    private void viewAllUsers() {
        System.out.println("\n=== All Users ===");
        for (StandardUser user : mobilitySystem.getUsers()) {
            System.out.println(user);
        }
    }

    private void addVehicle() {
        System.out.println("\n=== Add Vehicle ===");
        System.out.print("Enter vehicle ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter vehicle type (Bicycle/Motorcycle/Scooter): ");
        String type = scanner.nextLine();
        System.out.print("Enter X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = scanner.nextInt();
        System.out.print("Enter battery level: ");
        int battery = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = null;
        switch (type.toUpperCase()) {
            case "BICYCLE":
                vehicle = new Bicycle(id, new Location(x, y), battery);
                break;
            case "MOTORCYCLE":
                vehicle = new Motorcycle(id, new Location(x, y), battery, Motorcycle.MotorcycleSize.MEDIUM);
                break;
            case "SCOOTER":
                vehicle = new Scooter(id, new Location(x, y), battery);
                break;
            default:
                System.out.println("Invalid vehicle type.");
                return;
        }

        mobilitySystem.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    private void removeVehicle() {
        System.out.println("\n=== Remove Vehicle ===");
        System.out.print("Enter vehicle ID: ");
        String id = scanner.nextLine();

        Vehicle vehicle = mobilitySystem.getVehicleById(id);
        if (vehicle != null) {
            mobilitySystem.removeVehicle(id);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void addStation() {
        System.out.println("\n=== Add Station ===");
        System.out.print("Enter station ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = scanner.nextInt();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        Station station = new Station(id, new Location(x, y), capacity);
        mobilitySystem.addStation(station);
        System.out.println("Station added successfully.");
    }

    private void removeStation() {
        System.out.println("\n=== Remove Station ===");
        System.out.print("Enter station ID: ");
        String id = scanner.nextLine();

        Station station = mobilitySystem.getStationById(id);
        if (station != null) {
            mobilitySystem.removeStation(id);
            System.out.println("Station removed successfully.");
        } else {
            System.out.println("Station not found.");
        }
    }

    private void updateUserBalance() {
        System.out.println("\n=== Update User Balance ===");
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter amount to add: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        StandardUser user = mobilitySystem.getUserById(id);
        if (user != null) {
            mobilitySystem.updateUserBalance(id, amount);
            System.out.println("User balance updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void updateUserPremiumStatus() {
        System.out.println("\n=== Update User Premium Status ===");
        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();
        System.out.print("Set premium status (true/false): ");
        boolean isPremium = scanner.nextBoolean();
        scanner.nextLine();

        StandardUser user = mobilitySystem.getUserById(id);
        if (user != null) {
            mobilitySystem.updateUserPremiumStatus(id, isPremium);
            System.out.println("User premium status updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void viewSystemStatistics() {
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Vehicles: " + mobilitySystem.getVehicles().size());
        System.out.println("Total Stations: " + mobilitySystem.getStations().size());
        System.out.println("Total Users: " + mobilitySystem.getUsers().size());
    }
} 