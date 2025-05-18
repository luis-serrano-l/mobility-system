package src.view;

import src.controller.MobilitySystem;
import src.utils.MockGenerator;
import src.model.people.workers.Worker;
import src.model.people.members.Member;
import java.util.Scanner;

public class VInitial {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilitySystem mobilitySystem;

    public static void main(String[] args) {
        // Initialize system and generate mock data
        mobilitySystem = new MobilitySystem();
        MockGenerator.generateMockData(mobilitySystem);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Mobility System ===");
            System.out.println("1. Login as admin");
            System.out.println("2. Login as worker");
            System.out.println("3. Login as Member");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();
            
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        loginAdmin();
                        break;
                    case 2:
                        loginWorker();
                        break;
                    case 3:
                        loginMember();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void loginAdmin() {
        VAdmin adminView = new VAdmin(mobilitySystem);
        adminView.showAdminMenu();
    }

    private static void loginWorker() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        
        Worker worker = mobilitySystem.getPeopleManager().getWorkerByName(name);
        if (worker == null) {
            System.out.println("Person not found or not a worker. Please try again.");
            return;
        }

        VWorker workerView = new VWorker(mobilitySystem, worker);
        workerView.showWorkerMenu();
    }

    private static void loginMember() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        
        Member member = mobilitySystem.getPeopleManager().getMemberByName(name);
        if (member == null) {
            System.out.println("Person not found or not a member. Please try again.");
            return;
        }

        VMember memberView = new VMember(mobilitySystem);
        memberView.setCurrentMember(member);
        memberView.showMemberMenu();
    }
}