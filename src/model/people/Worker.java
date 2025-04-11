package src.model.people;

import java.util.Scanner;
import java.util.List;

import src.controller.MobilitySystem;
import src.model.vehicles.Vehicle;
import src.model.station.Station;
import src.model.Trip;
import src.model.Location;

public class Worker extends User {
    public Worker(String id, String name, String username, String password) {
        super(id, name, username, password, false); // Workers are not premium users
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Username: %s, Role: Worker",
            getId(), getName(), getUsername());
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Worker Menu ===");
        System.out.println("1. View all vehicles");
        System.out.println("2. View all stations");
        System.out.println("3. View active trips");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void handleOption(int option, Scanner scanner, MobilitySystem system) {
        switch (option) {
            case 1:
                viewAllVehicles(system);
                break;
            case 2:
                viewAllStations(system);
                break;
            case 3:
                viewActiveTrips(system);
                break;
            case 4:
                // Logout is handled in VInitial
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void viewAllVehicles(MobilitySystem system) {
        System.out.println("\n=== All Vehicles ===");
        List<Vehicle> vehicles = system.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private void viewAllStations(MobilitySystem system) {
        System.out.println("\n=== All Stations ===");
        List<Station> stations = system.getAllStations();
        if (stations.isEmpty()) {
            System.out.println("No stations in the system.");
            return;
        }
        for (Station station : stations) {
            System.out.println(station);
        }
    }

    private void viewActiveTrips(MobilitySystem system) {
        System.out.println("\n=== Active Trips ===");
        List<Trip> activeTrips = system.getActiveTrips();
        if (activeTrips.isEmpty()) {
            System.out.println("No active trips.");
            return;
        }
        for (Trip trip : activeTrips) {
            System.out.println(trip);
        }
    }

    private void chargeVehicle(Scanner scanner, MobilitySystem system) {
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

    private void moveVehicle(Scanner scanner, MobilitySystem system) {
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

        vehicle.setCurrentLocation(new Location(x, y));
        System.out.println("Vehicle moved successfully.");
    }

    @Override
    public boolean canRentVehicle(Vehicle vehicle) {
        return false; // Workers cannot rent vehicles
    }
} 