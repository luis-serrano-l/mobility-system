package src.view;

import java.util.*;
import src.model.*;
import src.model.people.*;
import src.model.vehicles.*;
import src.controller.MobilitySystem;
import src.model.station.Station;
import src.model.trip.Trip;
import src.model.Location;

public class VWorker {
    private Worker worker;
    private MobilitySystem system;
    private Scanner scanner;

    public VWorker(Worker worker, MobilitySystem system) {
        this.worker = worker;
        this.system = system;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Worker Menu ===");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Start Trip");
            System.out.println("3. End Trip");
            System.out.println("4. View Trip History");
            System.out.println("5. Report Vehicle Issue");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            handleOption(option);
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1:
                viewAvailableVehicles();
                break;
            case 2:
                startTrip();
                break;
            case 3:
                endTrip();
                break;
            case 4:
                viewTripHistory();
                break;
            case 5:
                reportVehicleIssue();
                break;
            case 6:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void viewAvailableVehicles() {
        List<Vehicle> availableVehicles = system.getAvailableVehicles();
        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles available at the moment.");
            return;
        }

        System.out.println("\nAvailable Vehicles:");
        for (Vehicle vehicle : availableVehicles) {
            System.out.printf("ID: %s, Location: (%d,%d), Battery: %d%%\n",
                vehicle.getId(),
                vehicle.getLocation().getX(),
                vehicle.getLocation().getY(),
                vehicle.getBatteryLevel());
        }
    }

    private void startTrip() {
        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.next();
        
        Vehicle vehicle = system.findVehicle(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is not available.");
            return;
        }

        if (!worker.canRentVehicle(vehicle)) {
            System.out.println("Cannot rent vehicle. Battery level too low or other restrictions apply.");
            return;
        }

        startTrip(worker, vehicle, system);
    }

    private void endTrip() {
        if (system.getActiveTrip(worker) == null) {
            System.out.println("No active trip found.");
            return;
        }

        System.out.print("Enter current location (x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        if (system.endTrip(worker, x, y)) {
            System.out.println("Trip ended successfully!");
        } else {
            System.out.println("Failed to end trip.");
        }
    }

    private void viewTripHistory() {
        List<Trip> history = system.getTripHistory(worker);
        if (history.isEmpty()) {
            System.out.println("No trip history available.");
            return;
        }

        System.out.println("\nTrip History:");
        for (Trip trip : history) {
            System.out.printf("Trip ID: %s, Vehicle: %s, Cost: %.2f\n",
                trip.getId(),
                trip.getVehicle().getId(),
                trip.getCost());
        }
    }

    private void reportVehicleIssue() {
        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.next();
        
        Vehicle vehicle = system.findVehicle(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        vehicle.setHasIssue(true);
        System.out.println("Vehicle issue reported successfully.");
    }

    public static void startTrip(Worker worker, Vehicle vehicle, MobilitySystem system) {
        Trip trip = system.startTrip(worker, vehicle);
        if (trip != null) {
            System.out.println("Trip started successfully!");
        } else {
            System.out.println("Failed to start trip.");
        }
    }
} 