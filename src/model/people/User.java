package src.model.people;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.Date;

import src.model.Location;
import src.model.Trip;
import src.model.vehicles.Vehicle;
import src.model.vehicles.ElectricMotorcycle;
import src.model.Station;
import src.controller.MobilitySystem;

public class User extends Person {
    private double balance;
    private List<Trip> tripHistory;
    private boolean isPremium;
    private double discountRate;
    private Date lastReservation;
    private Vehicle reservedVehicle;

    public User(String id, String name, String username, String password, boolean isPremium) {
        super(id, name, username, password);
        this.balance = 0.0;
        this.tripHistory = new ArrayList<>();
        this.isPremium = isPremium;
        this.discountRate = isPremium ? 0.2 : 0.0; // 20% discount for premium users
        this.lastReservation = null;
        this.reservedVehicle = null;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Username: %s, Premium: %s, Balance: %.2f€",
            id, name, username, isPremium ? "Yes" : "No", balance);
    }

    public void displayMenu() {
        System.out.println("\n=== User Menu ===");
        System.out.println("1. View Available Vehicles");
        System.out.println("2. Start Trip");
        System.out.println("3. End Trip");
        System.out.println("4. Report Vehicle Issue");
        System.out.println("5. View Trip History");
        System.out.println("6. Add Balance");
        System.out.println("0. Logout");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void handleOption(int option, Scanner scanner, MobilitySystem system) {
        switch (option) {
            case 1:
                viewAvailableVehicles(system);
                break;
            case 2:
                startTrip(scanner, system);
                break;
            case 3:
                endTrip(scanner, system);
                break;
            case 4:
                reportVehicleIssue(scanner, system);
                break;
            case 5:
                viewTripHistory();
                break;
            case 6:
                addBalance(scanner);
                break;
            case 0:
                // Logout is handled in VInitial
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void viewAvailableVehicles(MobilitySystem system) {
        List<Vehicle> availableVehicles = system.getAvailableVehicles();
        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles available");
            return;
        }

        System.out.println("\nAvailable Vehicles:");
        for (Vehicle vehicle : availableVehicles) {
            System.out.printf("ID: %s, Type: %s, Battery: %d%%, Location: %s%n",
                vehicle.getId(),
                vehicle.getClass().getSimpleName(),
                vehicle.getBatteryLevel(),
                vehicle.getCurrentLocation());
        }
    }

    private void startTrip(Scanner scanner, MobilitySystem system) {
        if (system.getActiveTrip(this) != null) {
            System.out.println("You already have an active trip");
            return;
        }

        if (lastReservation != null) {
            long timeSinceLastReservation = System.currentTimeMillis() - lastReservation.getTime();
            if (timeSinceLastReservation < 20 * 60 * 1000) { // 20 minutes in milliseconds
                System.out.println("You must wait 20 minutes between reservations");
                return;
            }
        }

        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.nextLine();
        Vehicle vehicle = system.getVehicle(vehicleId);
        
        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        if (!canRentVehicle(vehicle)) {
            System.out.println("Cannot rent this vehicle");
            return;
        }

        if (!system.isLocationWithinCityLimits(vehicle.getCurrentLocation())) {
            System.out.println("Vehicle is outside city limits");
            return;
        }

        Trip trip = system.startTrip(this, vehicle);
        if (trip != null) {
            lastReservation = new Date();
            System.out.println("Trip started successfully");
        } else {
            System.out.println("Failed to start trip");
        }
    }

    private void endTrip(Scanner scanner, MobilitySystem system) {
        Trip trip = system.getActiveTrip(this);
        if (trip == null) {
            System.out.println("No active trip");
            return;
        }

        System.out.print("Enter end location (x y): ");
        String[] loc = scanner.nextLine().split(" ");
        Location endLocation = new Location(Double.parseDouble(loc[0]), Double.parseDouble(loc[1]));

        if (!system.isLocationWithinCityLimits(endLocation)) {
            System.out.println("End location is outside city limits");
            return;
        }

        double cost = system.endTrip(this, endLocation);
        if (cost >= 0) {
            System.out.printf("Trip ended successfully. Cost: €%.2f%n", cost);
            tripHistory.add(trip);
        } else {
            System.out.println("Failed to end trip");
        }
    }

    private void reportVehicleIssue(Scanner scanner, MobilitySystem system) {
        Trip trip = system.getActiveTrip(this);
        if (trip == null) {
            System.out.println("No active trip");
            return;
        }

        Vehicle vehicle = trip.getVehicle();
        vehicle.setHasIssue(true);
        System.out.println("Vehicle issue reported successfully");
    }

    private void viewTripHistory() {
        List<Trip> history = tripHistory;
        if (history.isEmpty()) {
            System.out.println("No trip history");
            return;
        }

        System.out.println("\nTrip History:");
        for (Trip trip : history) {
            System.out.printf("Vehicle: %s, Start: %s, End: %s, Cost: €%.2f%n",
                trip.getVehicle().getId(),
                trip.getStartLocation(),
                trip.getEndLocation(),
                trip.getCost());
        }
    }

    private void addBalance(Scanner scanner) {
        System.out.print("Enter amount to add: €");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        balance += amount;
        System.out.printf("Balance updated: €%.2f%n", balance);
    }

    public boolean canRentVehicle(Vehicle vehicle) {
        if (!canRentVehicle()) {
            return false;
        }

        int requiredBattery = isPremium ? 10 : 20;
        return vehicle.getBatteryLevel() >= requiredBattery;
    }

    public boolean canRentVehicle() {
        return true; // Override in subclasses if needed
    }

    public double getBalance() {
        return balance;
    }

    public void addTrip(Trip trip) {
        tripHistory.add(trip);
    }

    public List<Trip> getTripHistory() {
        return new ArrayList<>(tripHistory);
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
        discountRate = premium ? 0.2 : 0.0;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double rate) {
        this.discountRate = rate;
    }
} 