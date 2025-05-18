package src.view;

import java.util.Scanner;
import src.model.locations.Station;
import src.model.people.members.Member;
import src.model.vehicles.Vehicle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Scooter;
import src.model.vehicles.SmallMotorcycle;
import src.model.vehicles.BigMotorcycle;
import src.model.locations.Location;
import src.model.vehicles.VehiclesManager;
import src.model.locations.StationsManager;
import src.model.people.PeopleManager;
import src.model.people.workers.Mechanic;
import src.model.people.workers.FieldOperator;
import src.model.people.Person;

public class VAdmin {
    private VehiclesManager vehiclesManager;
    private StationsManager stationsManager;
    private PeopleManager peopleManager;
    private Scanner scanner;

    public VAdmin() {
        this.scanner = new Scanner(System.in);
    }

    public void showAdminMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all vehicles");
            System.out.println("2. View all stations");
            System.out.println("3. View all members");
            System.out.println("4. View all mechanics");
            System.out.println("5. View all field operators");
            System.out.println("6. Add vehicle");
            System.out.println("7. Add station");
            System.out.println("8. Add member");
            System.out.println("9. Add mechanic");
            System.out.println("10. Add field operator");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllVehicles();
                    break;
                case 2:
                    viewAllStations();
                    break;
                case 3:
                    viewAllMembers();
                    break;
                case 4:
                    viewAllMechanics();
                    break;
                case 5:
                    viewAllFieldOperators();
                    break;
                case 6:
                    addVehicle();
                    break;
                case 7:
                    addStation();
                    break;
                case 8:
                    addMember();
                    break;
                case 9:
                    addMechanic();
                    break;
                case 10:
                    addFieldOperator();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllVehicles() {
        System.out.println("\n=== All Vehicles ===");
        for (Vehicle vehicle : vehiclesManager.getAllVehicles()) {
            System.out.println("ID: " + vehicle.getId());
            System.out.println("Location: " + vehicle.getLocation());
            System.out.println("Battery: " + vehicle.getBattery() + "%");
            System.out.println("Needs Repair: " + vehicle.needsRepair());
            System.out.println("-------------------");
        }
    }

    private void viewAllStations() {
        System.out.println("\n=== All Stations ===");
        for (Station station : stationsManager.getAllStations()) {
            System.out.println("ID: " + station.getName());
            System.out.println("Location: " + station.getX() + ", " + station.getY());
            System.out.println("Bicycle Capacity: " + station.getBicycleCapacity());
            System.out.println("Scooter Capacity: " + station.getScooterCapacity());
            System.out.println("Total Bicycles: " + station.getTotalBycicles().size());
            System.out.println("Total Scooters: " + station.getTotalScooters().size());
            System.out.println("-------------------");
        }
    }

    private void viewAllMembers() {
        System.out.println("\n=== All members ===");
        for (Person person : peopleManager.getPeopleByType(Member.class)) {
            Member member = (Member) person;
            System.out.println("Name: " + member.getName());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Balance: " + member.getBalance());
            System.out.println("Premium Status: " + member.isPremium());
            System.out.println("-------------------");
        }
    }

    private void viewAllMechanics() {
        System.out.println("\n=== All Mechanics ===");
        for (Person person : peopleManager.getPeopleByType(Mechanic.class)) {
            Mechanic mechanic = (Mechanic) person;
            System.out.println("Name: " + mechanic.getName());
            System.out.println("Email: " + mechanic.getEmail());
            System.out.println("Assigned Vehicles:");
            for (Vehicle vehicle : mechanic.getAssignedVehicles()) {
                System.out.println("\tID: " + vehicle.getId());
                System.out.println("\tLocation: " + vehicle.getLocation());
                System.out.println("\tNeeds Repair: " + vehicle.needsRepair());
                System.out.println("\t-------------------");
            }
            System.out.println("-------------------");
        }
    }

    private void viewAllFieldOperators() {  
        System.out.println("\n=== All Field Operators ===");
        for (Person person : peopleManager.getPeopleByType(FieldOperator.class)) {
            FieldOperator fieldOperator = (FieldOperator) person;
            System.out.println("Name: " + fieldOperator.getName());
            System.out.println("Email: " + fieldOperator.getEmail());
            System.out.println("Assigned Vehicles:");
            for (Vehicle vehicle : fieldOperator.getAssignedVehicles()) {
                System.out.println("\tID: " + vehicle.getId());
                System.out.println("\tLocation: " + vehicle.getLocation());
                System.out.println("\tBattery Damaged: " + vehicle.batteryDamaged());
                System.out.println("\t-------------------");
            }
            System.out.println("-------------------");
        }
    }

    private void addVehicle() {
        System.out.println("\n=== Add Vehicle ===");
        System.out.print(
                "Enter vehicle type (B for Bicycle/S for Scooter/SM for Small Motorcycle/BM for Big Motorcycle): ");
        String type = scanner.nextLine();
        System.out.print("Enter X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = scanner.nextInt();
        scanner.nextLine();

        Location location = new Location(x, y);
        boolean success = false;

        switch (type.toUpperCase()) {
            case "B":
                System.out.println("\nAvailable Stations:");
                for (Station station : stationsManager.getAllStations()) {
                    System.out.println(station.getName() + " - Location: " + station.getX() + ", " + station.getY());
                }
                System.out.print("Enter station name: ");
                String stationName = scanner.nextLine();
                Station selectedStation = stationsManager.getStationByName(stationName);
                if (selectedStation == null) {
                    System.out.println("Invalid station ID.");
                    return;
                }
                int vehicleId = vehiclesManager.getAllVehicles().size() + 1;
                Vehicle bicycle = new Bicycle(vehicleId, selectedStation);
                vehiclesManager.addVehicle(bicycle);
                success = true;
                break;
            case "S":
                System.out.println("\nAvailable Stations:");
                for (Station station : stationsManager.getAllStations()) {
                    System.out.println(station.getName() + " - Location: " + station.getX() + ", " + station.getY());
                }
                System.out.print("Enter station name: ");
                stationName = scanner.nextLine();
                selectedStation = stationsManager.getStationByName(stationName);
                if (selectedStation == null) {
                    System.out.println("Invalid station ID.");
                    return;
                }
                vehicleId = vehiclesManager.getAllVehicles().size() + 1;
                Vehicle scooter = new Scooter(vehicleId, selectedStation);
                vehiclesManager.addVehicle(scooter);
                success = true;
                break;
            case "SM":
                vehicleId = vehiclesManager.getAllVehicles().size() + 1;
                Vehicle smallMotorcycle = new SmallMotorcycle(vehicleId, location);
                vehiclesManager.addVehicle(smallMotorcycle);
                success = true;
                break;
            case "BM":
                vehicleId = vehiclesManager.getAllVehicles().size() + 1;
                Vehicle bigMotorcycle = new BigMotorcycle(vehicleId, location);
                vehiclesManager.addVehicle(bigMotorcycle);
                success = true;
                break;
            default:
                System.out.println("Invalid vehicle type.");
                return;
        }

        if (success) {
            System.out.println("Vehicle added successfully.");
        } else {
            System.out.println("Failed to add vehicle. Vehicle ID may already exist.");
        }
    }

    private void addStation() {
        System.out.println("\n=== Add Station ===");
        String name;
        boolean validName = false;
        do {
            System.out.print("Enter station name (unique): ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Station name cannot be empty.");
            } else if (stationsManager.getStationByName(name) != null) {
                System.out.println("A station with this name already exists. Please choose a different name.");
            } else {
                validName = true;
            }
        } while (!validName);
        System.out.print("Enter X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = scanner.nextInt();
        System.out.print("Enter capacity for bicycles: ");
        int bicycleCapacity = scanner.nextInt();
        System.out.print("Enter capacity for scooters: ");
        int scooterCapacity = scanner.nextInt();
        scanner.nextLine();

        Station station = new Station(x, y, name, bicycleCapacity, scooterCapacity);
        stationsManager.addStation(station);
        System.out.println("Station added successfully.");
    }

    private void addMember() {
        System.out.println("\n=== Add Member ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        int id = peopleManager.getAllPeople().size() + 1;
        Member member = new Member(id, name, email);
        peopleManager.addPerson(member);
        System.out.println("Member added successfully.");
    }

    private void addMechanic() {
        System.out.println("\n=== Add Mechanic ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        int id = peopleManager.getAllPeople().size() + 1;
        Mechanic mechanic = new Mechanic(id, name, email);
        peopleManager.addPerson(mechanic);
        System.out.println("Mechanic added successfully.");
    }

    private void addFieldOperator() {
        System.out.println("\n=== Add Field Operator ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        int id = peopleManager.getAllPeople().size() + 1;
        FieldOperator fieldOperator = new FieldOperator(id, name, email);
        peopleManager.addPerson(fieldOperator);
        System.out.println("Field Operator added successfully.");
    }
}