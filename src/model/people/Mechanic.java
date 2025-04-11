package src.model.people;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import src.controller.MobilitySystem;
import src.model.vehicles.Vehicle;
import src.model.station.Station;
import src.model.Location;

public class Mechanic extends User {
    private List<Vehicle> assignedVehicles;
    private double totalRepairCost;

    public Mechanic(String id, String name, String username, String password) {
        super(id, name, username, password, false); // Mechanics are not premium users
        this.assignedVehicles = new ArrayList<>();
        this.totalRepairCost = 0.0;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Username: %s, Role: Mechanic, Assigned Vehicles: %d",
            getId(), getName(), getUsername(), assignedVehicles.size());
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Mechanic Menu ===");
        System.out.println("1. View Assigned Vehicles");
        System.out.println("2. Fix Vehicle");
        System.out.println("3. View Repair History");
        System.out.println("0. Logout");
    }

    @Override
    public void handleOption(int option, Scanner scanner, MobilitySystem system) {
        switch (option) {
            case 1:
                viewAssignedVehicles();
                break;
            case 2:
                fixVehicle(scanner, system);
                break;
            case 3:
                viewRepairHistory();
                break;
            case 0:
                // Logout is handled in VInitial
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    public void addAssignedVehicle(Vehicle vehicle) {
        if (!assignedVehicles.contains(vehicle)) {
            assignedVehicles.add(vehicle);
        }
    }

    private void viewAssignedVehicles() {
        if (assignedVehicles.isEmpty()) {
            System.out.println("No vehicles assigned");
            return;
        }

        System.out.println("\nAssigned Vehicles:");
        for (Vehicle vehicle : assignedVehicles) {
            System.out.printf("ID: %s, Type: %s, Battery: %.1f%%, Has Issue: %s%n",
                vehicle.getId(),
                vehicle.getClass().getSimpleName(),
                vehicle.getBatteryLevel(),
                vehicle.hasIssue());
        }
    }

    private void fixVehicle(Scanner scanner, MobilitySystem system) {
        if (assignedVehicles.isEmpty()) {
            System.out.println("No vehicles assigned");
            return;
        }

        System.out.println("\nAssigned Vehicles:");
        for (int i = 0; i < assignedVehicles.size(); i++) {
            Vehicle v = assignedVehicles.get(i);
            System.out.printf("%d. ID: %s, Type: %s, Battery: %.1f%%%n",
                i + 1,
                v.getId(),
                v.getClass().getSimpleName(),
                v.getBatteryLevel());
        }

        System.out.print("\nEnter vehicle number to fix: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice < 1 || choice > assignedVehicles.size()) {
            System.out.println("Invalid choice");
            return;
        }

        Vehicle vehicle = assignedVehicles.get(choice - 1);
        System.out.print("Enter repair cost: €");
        double cost = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        vehicle.setHasIssue(false);
        totalRepairCost += cost;
        System.out.println("Vehicle fixed successfully");
    }

    private void viewRepairHistory() {
        System.out.printf("\nTotal repair cost: €%.2f%n", totalRepairCost);
        System.out.println("Fixed vehicles:");
        for (Vehicle vehicle : assignedVehicles) {
            if (!vehicle.hasIssue()) {
                System.out.printf("ID: %s, Type: %s%n",
                    vehicle.getId(),
                    vehicle.getClass().getSimpleName());
            }
        }
    }

    @Override
    public boolean canRentVehicle() {
        return false; // Mechanics cannot rent vehicles
    }
} 