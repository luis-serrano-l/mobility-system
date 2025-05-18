package src.view;

import src.controller.MobilitySystem;
import src.model.people.members.Member;
import src.model.trips.Trip;
import src.model.vehicles.Vehicle;
import src.model.vehicles.Scooter;
import src.model.vehicles.Bicycle;
import src.model.locations.Location;
import src.model.locations.Station;
import java.util.List;
import java.util.Scanner;

public class VMember {
    private Scanner scanner;
    private MobilitySystem mobilitySystem;
    private Member currentMember;

    public VMember(MobilitySystem mobilitySystem) {
        this.scanner = new Scanner(System.in);
        this.mobilitySystem = mobilitySystem;
    }

    public void showMemberMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Member Menu ===");
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
        List<Vehicle> vehicles = mobilitySystem.getVehiclesManager().getAvailableVehicles();
        System.out.println("\n=== Available Vehicles ===");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private void startTrip() {
        if (currentMember == null) {
            System.out.println("Please login first.");
            return;
        }

        // Check balance first
        if (currentMember.getBalance() <= 0) {
            System.out.println("Insufficient balance. Please add funds before starting a trip.");
            return;
        }

        viewAvailableVehicles();
        System.out.print("Enter vehicle ID: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = mobilitySystem.getVehiclesManager().getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        Location destination;
        // For scooters and bicycles, require station selection
        if (vehicle instanceof Scooter || vehicle instanceof Bicycle) {
            System.out.println("\nAvailable Stations:");
            List<Station> stations = mobilitySystem.getStationsManager().getAllStations();
            for (int i = 0; i < stations.size(); i++) {
                Station station = stations.get(i);
                System.out.printf("%d. %s\n", i + 1, station);
            }
            
            int stationChoice;
            do {
                System.out.print("Select destination station number (1-" + stations.size() + "): ");
                try {
                    stationChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (stationChoice < 1 || stationChoice > stations.size()) {
                        System.out.println("Invalid station number. Please try again.");
                        continue;
                    }
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                }
            } while (true);
            
            destination = stations.get(stationChoice - 1);
        } else {
            // For motorcycles, allow any location within bounds
            int x, y;
            do {
                System.out.print("Enter destination X coordinate (0-100): ");
                x = scanner.nextInt();
                if (x < 0 || x > 100) {
                    System.out.println("X coordinate must be between 0 and 100.");
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.print("Enter destination Y coordinate (0-100): ");
                y = scanner.nextInt();
                if (y < 0 || y > 100) {
                    System.out.println("Y coordinate must be between 0 and 100.");
                    continue;
                }
                break;
            } while (true);
            scanner.nextLine();
            
            destination = new Location(x, y);
        }

        System.out.print("Enter trip duration (seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        // Calculate trip cost
        double tripCost = vehicle.getBasePrice() * (duration / 60.0);
        if (currentMember.getBalance() < tripCost) {
            System.out.printf("Insufficient balance. Trip cost: $%.2f, Your balance: $%.2f\n", 
                tripCost, currentMember.getBalance());
            return;
        }

        Trip trip = new Trip(currentMember, vehicle);
        trip.endTrip(destination, duration);
        
        // Deduct cost from balance
        currentMember.deductBalance(tripCost);
        
        mobilitySystem.getTripsManager().addTrip(trip);
        System.out.printf("Trip started successfully! Cost: $%.2f, Remaining balance: $%.2f\n", 
            tripCost, currentMember.getBalance());
    }

    private void viewTripHistory() {
        if (currentMember == null) {
            System.out.println("Please login first.");
            return;
        }

        List<Trip> trips = mobilitySystem.getTripsManager().getTripsByMember(currentMember);
        System.out.println("\n=== Trip History ===");
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }

    private void viewBalance() {
        if (currentMember == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println("\nCurrent Balance: $" + currentMember.getBalance());
    }

    private void addBalance() {
        if (currentMember == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter amount to add: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        currentMember.addBalance(amount);
        System.out.println("Balance updated successfully!");
    }

    private void reportVehicleIssue() {
        if (currentMember == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter vehicle ID: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = mobilitySystem.getVehiclesManager().getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        vehicle.setNeedsRepair(true);
        System.out.println("Issue reported successfully!");
    }

    public void setCurrentMember(Member member) {
        this.currentMember = member;
    }
}

