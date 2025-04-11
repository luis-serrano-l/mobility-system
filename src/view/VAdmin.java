package src.view;

import java.util.Scanner;
import src.controller.MobilitySystem;
import src.model.vehicles.Vehicle;
import src.model.station.Station;
import java.util.List;

public class VAdmin {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View all vehicles");
        System.out.println("2. View all stations");
        System.out.println("3. Charge vehicle");
        System.out.println("4. Move vehicle");
        System.out.println("5. Logout");
    }

    public static void handleOption(int option, MobilitySystem system) {
        switch (option) {
            case 1:
                viewAllVehicles(system);
                break;
            case 2:
                viewAllStations(system);
                break;
            case 3:
                chargeVehicle(system);
                break;
            case 4:
                moveVehicle(system);
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private static void viewAllVehicles(MobilitySystem system) {
        List<Vehicle> vehicles = system.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        
        System.out.println("\nAll Vehicles:");
        for (Vehicle vehicle : vehicles) {
            System.out.printf("ID: %s, Type: %s, Battery: %.1f%%, Location: %s, Available: %s%n",
                vehicle.getId(),
                vehicle.getClass().getSimpleName(),
                vehicle.getBatteryLevel(),
                vehicle.getCurrentLocation(),
                vehicle.isAvailable());
        }
    }

    private static void viewAllStations(MobilitySystem system) {
        List<Station> stations = system.getAllStations();
        if (stations.isEmpty()) {
            System.out.println("No stations in the system.");
            return;
        }
        
        System.out.println("\nAll Stations:");
        for (Station station : stations) {
            System.out.printf("ID: %s, Name: %s, Location: %s, Available Slots: %d/%d%n",
                station.getId(),
                station.getName(),
                station.getLocation(),
                station.getAvailableSlots(),
                station.getTotalSlots());
        }
    }

    private static void chargeVehicle(MobilitySystem system) {
        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.nextLine();
        
        Vehicle vehicle = system.getVehicle(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        vehicle.charge();
        System.out.println("Vehicle charged successfully.");
    }

    private static void moveVehicle(MobilitySystem system) {
        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.nextLine();
        
        Vehicle vehicle = system.getVehicle(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter new location (x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        vehicle.setCurrentLocation(new src.model.Location(x, y));
        System.out.println("Vehicle moved successfully.");
    }
} 