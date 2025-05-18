package src.view;

import src.model.vehicles.Vehicle;
import src.model.vehicles.VehiclesManager;
import src.model.vehicles.Scooter;
import src.model.vehicles.Bicycle;
import src.model.locations.Location;
import src.model.people.members.Member;
import src.model.people.PeopleManager;
import src.model.trips.Trip;
import src.model.locations.StationsManager;
import src.model.locations.Station;
import java.util.List;
import java.util.Scanner;

public class VMember {
    private Scanner scanner;
    private VehiclesManager vehiclesManager;
    private StationsManager stationsManager;

    public VMember() {
        this.scanner = new Scanner(System.in);
        this.vehiclesManager = new VehiclesManager();
        this.stationsManager = new StationsManager();
    }

    public void showMemberMenu() {
        boolean exit = false;
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        PeopleManager peopleManager = new PeopleManager();
        Member member = peopleManager.getMemberByName(name);

        if (member == null) {
            System.out.println("Person not found or not a member. Please try again.");
            return;
        }

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
        System.out.println("\n=== Available Vehicles ===");
        for (Vehicle vehicle : vehiclesManager.getAllAvailableVehicles()) {
            System.out.println("Vehicle ID: " + vehicle.getId());
            System.out.println("Type: " + vehicle.getClass().getSimpleName());
            System.out.println("Location: " + vehicle.getLocation());
            System.out.println("Battery Level: " + vehicle.getBattery() + "%");
            System.out.println("-------------------");
        }
    }

    private void startTrip() {
        System.out.println("\n=== Start Trip ===");
        System.out.print("Enter vehicle ID: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter trip duration (seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        int x, y;
        Vehicle vehicle = vehiclesManager.getVehicleById(vehicleId);
        if (vehicle instanceof Scooter || vehicle instanceof Bicycle) {
            System.out.println("\nAvailable Stations:");
            List<Station> stations = stationsManager.getAllStations();
            for (int i = 0; i < stations.size(); i++) {
                Station station = stations.get(i);
                System.out.println((i+1) + ". " + station.getName() + " (" + 
                                 station.getLocation().getX() + "," + 
                                 station.getLocation().getY() + ")");
            }
            System.out.print("Choose station number: ");
            int stationNum = scanner.nextInt();
            scanner.nextLine();
            Station selectedStation = stations.get(stationNum - 1);
            x = selectedStation.getLocation().getX();
            y = selectedStation.getLocation().getY();
        } else {
            System.out.print("Enter destination x coordinate: ");
            x = scanner.nextInt();
            System.out.print("Enter destination y coordinate: ");
            y = scanner.nextInt();
            scanner.nextLine();
        }
        Location destination = new Location(x, y);

        Trip trip = new Trip(vehicle, member, vehicle.getLocation(), destination);
        mobilitySystem.addTrip(trip);
        vehicle.setLocation(destination);
        member.addTrip(trip);
    }

    private void viewTripHistory() {
        System.out.println("\n=== Trip History ===");
        for (Trip trip : member.getTripHistory()) {
            System.out.println(trip);
        }
    }

    private void viewBalance() {
        System.out.println("\n=== Current Balance ===");
        System.out.println("Balance: " + member.getBalance());
    }

    private void addBalance() {
        System.out.println("\n=== Add Balance ===");
        System.out.print("Enter amount to add: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        member.addBalance(amount);
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

        member.reportVehicleIssue(vehicle, description);
        System.out.println("Issue reported successfully.");
    }
}
