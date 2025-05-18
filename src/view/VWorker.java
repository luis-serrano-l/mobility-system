package src.view;

import java.util.Scanner;
import src.model.people.workers.Worker;
import src.model.vehicles.Vehicle;
import src.controller.MobilitySystem;
import java.util.List;
import src.model.locations.Location;

public class VWorker {
    private Scanner scanner;
    private MobilitySystem mobilitySystem;
    private Worker currentWorker;

    public VWorker(MobilitySystem mobilitySystem, Worker worker) {
        this.scanner = new Scanner(System.in);
        this.mobilitySystem = mobilitySystem;
        this.currentWorker = worker;
    }

    public void showWorkerMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Worker Menu ===");
            System.out.println("1. View assigned vehicles");
            System.out.println("2. Repair vehicle");
            System.out.println("3. Move vehicle");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAssignedVehicles();
                    break;
                case 2:
                    repairVehicle();
                    break;
                case 3:
                    moveVehicle();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAssignedVehicles() {
        List<Vehicle> assignedVehicles = currentWorker.getAssignedVehicles();
        System.out.println("\n=== Assigned Vehicles ===");
        for (Vehicle vehicle : assignedVehicles) {
            System.out.println(vehicle);
        }
    }

    private void repairVehicle() {
        viewAssignedVehicles();
        System.out.print("Enter vehicle ID to repair: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = mobilitySystem.getVehiclesManager().getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!currentWorker.getAssignedVehicles().contains(vehicle)) {
            System.out.println("This vehicle is not assigned to you.");
            return;
        }

        vehicle.repair();
        System.out.println("Vehicle repaired successfully!");
    }

    private void moveVehicle() {
        viewAssignedVehicles();
        System.out.print("Enter vehicle ID to move: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = mobilitySystem.getVehiclesManager().getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!currentWorker.getAssignedVehicles().contains(vehicle)) {
            System.out.println("This vehicle is not assigned to you.");
            return;
        }

        System.out.print("Enter new X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter new Y coordinate: ");
        int y = scanner.nextInt();
        scanner.nextLine();

        vehicle.setLocation(new Location(x, y));
        System.out.println("Vehicle moved successfully!");
    }
}