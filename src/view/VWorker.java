package src.view;

import java.util.Scanner;
import src.model.people.workers.Worker;
import src.model.people.workers.Mechanic;
import src.model.people.PeopleManager;
import src.model.vehicles.Vehicle;

    public class VWorker {
    private Scanner scanner;

    public VWorker() {
        this.scanner = new Scanner(System.in);
    }

    public void showWorkerMenu() {
        boolean exit = false;
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        PeopleManager peopleManager = new PeopleManager();
        Worker worker = peopleManager.getWorkerByName(name);
        
        if (worker == null) {
            System.out.println("Person not found or not a worker. Please try again.");
            return;
        }
        
        String workerType = (worker instanceof Mechanic) ? "Mechanic" : "Field Operator";
        System.out.println("Welcome " + workerType + " " + worker.getName() + "!");
        
        while (!exit) {
            System.out.println("\n=== Worker Menu ===");
            System.out.println("1. View my assigned vehicles"); 
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (Vehicle vehicle : worker.getAssignedVehicles()) {
                        System.out.println("Vehicle ID: " + vehicle.getId());
                        System.out.println("Type: " + vehicle.getClass().getSimpleName());
                        System.out.println("Location: " + vehicle.getLocation());
                        if (worker instanceof Mechanic) {
                            System.out.println("Needs Repair: " + vehicle.needsRepair());
                        } else {
                            System.out.println("Battery Damaged: " + vehicle.batteryDamaged());
                        }
                        System.out.println("-------------------");
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}