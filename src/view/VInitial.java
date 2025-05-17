package src.view;

import src.controller.MobilitySystem;
import src.model.people.members.Member;
import src.view.VAdmin;
import src.view.VWorker;
import src.view.VMember;
import java.util.Scanner;

public class VInitial {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MobilitySystem mobilitySystem = new MobilitySystem();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Mobility System ===");
            System.out.println("1. Login as admin");
            System.out.println("2. Login as worker");
            System.out.println("3. Login as Member");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loginAdmin(mobilitySystem);
                    break;
                case 2:
                    loginWorker(mobilitySystem);
                    break;
                case 3:
                    loginMember(mobilitySystem);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loginAdmin(MobilitySystem mobilitySystem) {
        VAdmin adminView = new VAdmin(mobilitySystem);
        adminView.showAdminMenu();
    }

    private static void loginWorker(MobilitySystem mobilitySystem) {
        VWorker workerView = new VWorker(mobilitySystem);
        workerView.showWorkerMenu();
    }

    private static void loginMember(MobilitySystem mobilitySystem) {
        VMember memberView = new VMember(mobilitySystem);
        memberView.showMemberMenu();
    }
}