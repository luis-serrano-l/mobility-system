package src.view;

import src.controller.MobilitySystem;
import src.model.people.standard.StandardUser;
import src.model.vehicles.Vehicle;
import src.model.locations.Location;
import src.model.trips.Trip;

import java.util.Scanner;

public class VStandardUser {
    private MobilitySystem mobilitySystem;
    private StandardUser user;
    private Scanner scanner;

    public VStandardUser(MobilitySystem mobilitySystem, StandardUser user) {
        this.mobilitySystem = mobilitySystem;
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    public void showUserMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View available vehicles");
            System.out.println("2. Start trip");
            System.out.println("3. View trip history");
            System.out.println("4. View balance");
            System.out.println("5. Add balance");
            System.out.println("6. Report vehicle issue");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableVehicles();
                    break;
                case 2:
                    startTrip();
                    break;
                case 3:
                    viewTripHistory();
                    break;
                case 4:
                    viewBalance();
                    break;
                case 5:
                    addBalance();
                    break;
                case 6:
                    reportVehicleIssue();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAvailableVehicles() {
        System.out.println("\n=== Available Vehicles ===");
        for (Vehicle vehicle : mobilitySystem.getVehicles()) {
            if (vehicle.isAvailable() && !vehicle.needsRepair() && user.canReserveVehicle(vehicle)) {
                System.out.println(vehicle);
            }
        }
    }

    private void startTrip() {
        System.out.println("\n=== Start Trip ===");
        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.nextLine();

        Vehicle vehicle = mobilitySystem.getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter trip duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter destination (x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();
        Location destination = new Location(x, y);

        Trip trip = new Trip(vehicle, user, vehicle.getLocation(), destination);
        mobilitySystem.addTrip(trip);
        vehicle.setLocation(destination);
        user.addTrip(trip);
    }

    private void viewTripHistory() {
        System.out.println("\n=== Trip History ===");
        for (Trip trip : user.getTripHistory()) {
            System.out.println(trip);
        }
    }

    private void viewBalance() {
        System.out.println("\n=== Current Balance ===");
        System.out.println("Balance: " + user.getBalance());
    }

    private void addBalance() {
        System.out.println("\n=== Add Balance ===");
        System.out.print("Enter amount to add: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        user.addBalance(amount);
        System.out.println("Balance added successfully.");
    }

    private void reportVehicleIssue() {
        System.out.println("\n=== Report Vehicle Issue ===");
        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.nextLine();

        Vehicle vehicle = mobilitySystem.getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter issue description: ");
        String description = scanner.nextLine();

        user.reportVehicleIssue(vehicle, description);
        System.out.println("Issue reported successfully.");
    }
}

