package src.model.people;

import java.util.*;
import src.model.vehicles.*;
import src.model.*;
import src.controller.MobilitySystem;
import src.model.people.User;
import src.model.vehicles.Vehicle;
import src.model.station.Station;
import java.util.List;
import java.util.ArrayList;

public class Admin extends User {
    public Admin(String id, String name, String username, String password) {
        super(id, name, username, password, true);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Username: %s, Role: Admin",
            getId(), getName(), getUsername());
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View all vehicles");
        System.out.println("2. View all stations");
        System.out.println("3. View all users");
        System.out.println("4. View system statistics");
        System.out.println("5. Logout");
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
                viewAllUsers(system);
                break;
            case 4:
                viewSystemStatistics(system);
                break;
            case 5:
                // Logout is handled in VInitial
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void viewAllVehicles(MobilitySystem system) {
        List<Vehicle> vehicles = system.getAllVehicles();
        System.out.println("\n=== All Vehicles ===");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private void viewAllStations(MobilitySystem system) {
        List<Station> stations = system.getAllStations();
        System.out.println("\n=== All Stations ===");
        for (Station station : stations) {
            System.out.printf("ID: %s, Name: %s, Location: %s, Available Slots: %d/%d%n",
                station.getId(),
                station.getName(),
                station.getLocation(),
                station.getAvailableSlots(),
                station.getTotalSlots());
        }
    }

    private void viewAllUsers(MobilitySystem system) {
        List<User> users = system.getAllUsers();
        System.out.println("\n=== All Users ===");
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void viewSystemStatistics(MobilitySystem system) {
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Vehicles: " + system.getAllVehicles().size());
        System.out.println("Total Stations: " + system.getAllStations().size());
        System.out.println("Total Users: " + system.getAllUsers().size());
        System.out.println("Active Trips: " + system.getActiveTrips().size());
    }

    @Override
    public boolean canRentVehicle() {
        return true; // Admins can always rent vehicles
    }
} 