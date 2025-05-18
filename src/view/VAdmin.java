package src.view;

import java.util.Scanner;
import java.util.List;
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
import src.controller.MobilitySystem;
import src.model.prices.Pricing;

public class VAdmin {
    private VehiclesManager vehiclesManager;
    private StationsManager stationsManager;
    private PeopleManager peopleManager;
    private Scanner scanner;
    private MobilitySystem mobilitySystem;

    public VAdmin(MobilitySystem mobilitySystem) {
        this.scanner = new Scanner(System.in);
        this.mobilitySystem = mobilitySystem;
        this.vehiclesManager = mobilitySystem.getVehiclesManager();
        this.stationsManager = mobilitySystem.getStationsManager();
        this.peopleManager = mobilitySystem.getPeopleManager();
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
            System.out.println("11. View damaged vehicles");
            System.out.println("12. Update vehicle");
            System.out.println("13. Update pricing");
            System.out.println("14. Manage premium status");
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
                case 11:
                    viewDamagedVehicles();
                    break;
                case 12:
                    updateVehicle();
                    break;
                case 13:
                    updatePricing();
                    break;
                case 14:
                    managePremiumStatus();
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

    private void viewDamagedVehicles() {
        System.out.println("\n=== Damaged Vehicles ===");
        boolean foundDamaged = false;
        for (Vehicle vehicle : vehiclesManager.getAllVehicles()) {
            if (vehicle.needsRepair() || vehicle.batteryDamaged()) {
                System.out.println("ID: " + vehicle.getId());
                System.out.println("Type: " + vehicle.getClass().getSimpleName());
                System.out.println("Location: " + vehicle.getLocation());
                System.out.println("Needs Repair: " + vehicle.needsRepair());
                System.out.println("Battery Damaged: " + vehicle.batteryDamaged());
                System.out.println("-------------------");
                foundDamaged = true;
            }
        }

        if (!foundDamaged) {
            System.out.println("No damaged vehicles found.");
            return;
        }

        System.out.println("\nWould you like to assign a damaged vehicle to a worker? (Y/N)");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("Y")) {
            System.out.print("Enter vehicle ID to assign: ");
            int vehicleId = scanner.nextInt();
            scanner.nextLine();
            
            Vehicle vehicle = null;
            for (Vehicle v : vehiclesManager.getAllVehicles()) {
                if (v.getId() == vehicleId) {
                    vehicle = v;
                    break;
                }
            }
            
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            if (vehicle.batteryDamaged()) {
                System.out.println("\nAvailable Field Operators:");
                for (Person person : peopleManager.getPeopleByType(FieldOperator.class)) {
                    FieldOperator operator = (FieldOperator) person;
                    System.out.println("Name: " + operator.getName());
                }
                
                System.out.print("\nEnter field operator name: ");
                String operatorName = scanner.nextLine();
                FieldOperator operator = (FieldOperator) peopleManager.getWorkerByName(operatorName);
                
                if (operator != null) {
                    operator.getAssignedVehicles().add(vehicle);
                    System.out.println("Vehicle assigned to field operator successfully.");
                } else {
                    System.out.println("Field operator not found.");
                }
            } else if (vehicle.needsRepair()) {
                System.out.println("\nAvailable Mechanics:");
                for (Person person : peopleManager.getPeopleByType(Mechanic.class)) {
                    Mechanic mechanic = (Mechanic) person;
                    System.out.println("Name: " + mechanic.getName());
                }
                
                System.out.print("\nEnter mechanic name: ");
                String mechanicName = scanner.nextLine();
                Mechanic mechanic = (Mechanic) peopleManager.getWorkerByName(mechanicName);
                
                if (mechanic != null) {
                    mechanic.getAssignedVehicles().add(vehicle);
                    System.out.println("Vehicle assigned to mechanic successfully.");
                } else {
                    System.out.println("Mechanic not found.");
                }
            }
        }
    }

    private void addVehicle() {
        System.out.println("\n=== Add Vehicle ===");
        System.out.print(
                "Enter vehicle type (B for Bicycle/S for Scooter/SM for Small Motorcycle/BM for Big Motorcycle): ");
        String type = scanner.nextLine();
        boolean success = false;

        switch (type.toUpperCase()) {
            case "B":
            case "S":
                System.out.println("\nAvailable Stations:");
                for (Station station : stationsManager.getAllStations()) {
                    System.out.println(station.getName() + " - Location: " + station.getX() + ", " + station.getY());
                }
                System.out.print("Enter station name: ");
                String stationName = scanner.nextLine();
                Station selectedStation = stationsManager.getStationByName(stationName);
                if (selectedStation == null) {
                    System.out.println("Invalid station name.");
                    return;
                }
                int vehicleId = vehiclesManager.getAllVehicles().size() + 1;
                Vehicle vehicle;
                if (type.equalsIgnoreCase("B")) {
                    vehicle = new Bicycle(vehicleId, selectedStation);
                } else {
                    vehicle = new Scooter(vehicleId, selectedStation);
                }
                vehiclesManager.addVehicle(vehicle);
                success = true;
                break;
            case "SM":
            case "BM":
                System.out.print("Enter X coordinate: ");
                int x = scanner.nextInt();
                System.out.print("Enter Y coordinate: ");
                int y = scanner.nextInt();
                scanner.nextLine();

                Location location = new Location(x, y);
                vehicleId = vehiclesManager.getAllVehicles().size() + 1;
                if (type.equalsIgnoreCase("SM")) {
                    vehicle = new SmallMotorcycle(vehicleId, location);
                } else {
                    vehicle = new BigMotorcycle(vehicleId, location);
                }
                vehiclesManager.addVehicle(vehicle);
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

    private void updateVehicle() {
        System.out.println("\n=== Update Vehicle ===");
        viewAllVehicles();
        
        System.out.print("Enter vehicle ID to update: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();
        
        Vehicle vehicle = vehiclesManager.getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Battery level");
        System.out.println("2. Repair status");
        System.out.println("3. Location");
        System.out.println("4. Availability");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new battery level (0-100): ");
                double battery = scanner.nextDouble();
                scanner.nextLine();
                if (battery >= 0 && battery <= 100) {
                    vehicle.setBattery(battery);
                    System.out.println("Battery level updated successfully.");
                } else {
                    System.out.println("Invalid battery level. Must be between 0 and 100.");
                }
                break;
            case 2:
                System.out.print("Does the vehicle need repair? (Y/N): ");
                String needsRepair = scanner.nextLine();
                vehicle.setNeedsRepair(needsRepair.equalsIgnoreCase("Y"));
                System.out.println("Repair status updated successfully.");
                break;
            case 3:
                if (vehicle instanceof Scooter || vehicle instanceof Bicycle) {
                    System.out.println("\nAvailable Stations:");
                    for (Station station : stationsManager.getAllStations()) {
                        System.out.println(station.getName() + " - Location: " + station.getX() + ", " + station.getY());
                    }
                    System.out.print("Enter station name: ");
                    String stationName = scanner.nextLine();
                    Station selectedStation = stationsManager.getStationByName(stationName);
                    if (selectedStation != null) {
                        vehicle.setLocation(selectedStation);
                        System.out.println("Location updated successfully.");
                    } else {
                        System.out.println("Invalid station name.");
                    }
                } else {
                    System.out.print("Enter new X coordinate: ");
                    int x = scanner.nextInt();
                    System.out.print("Enter new Y coordinate: ");
                    int y = scanner.nextInt();
                    scanner.nextLine();
                    vehicle.setLocation(new Location(x, y));
                    System.out.println("Location updated successfully.");
                }
                break;
            case 4:
                System.out.print("Is the vehicle available? (Y/N): ");
                String isAvailable = scanner.nextLine();
                vehicle.setAvailable(isAvailable.equalsIgnoreCase("Y"));
                System.out.println("Availability updated successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void updatePricing() {
        System.out.println("\n=== Update Pricing ===");
        System.out.println("Select vehicle type to update pricing:");
        System.out.println("1. Scooter");
        System.out.println("2. Bicycle");
        System.out.println("3. Small Motorcycle");
        System.out.println("4. Big Motorcycle");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String vehicleType;
        switch (choice) {
            case 1:
                vehicleType = "scooter";
                break;
            case 2:
                vehicleType = "bicycle";
                break;
            case 3:
                vehicleType = "smallmotorcycle";
                break;
            case 4:
                vehicleType = "bigmotorcycle";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Price per second");
        System.out.println("2. Repair cost");
        System.out.println("3. Repair time");
        System.out.print("Enter your choice: ");
        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new price per second: $");
                double price = scanner.nextDouble();
                scanner.nextLine();
                if (price > 0) {
                    mobilitySystem.getPricing().setPricePerSecond(vehicleType, price);
                    System.out.println("Price updated successfully.");
                } else {
                    System.out.println("Price must be greater than 0.");
                }
                break;
            case 2:
                System.out.print("Enter new repair cost per minute: $");
                double repairCost = scanner.nextDouble();
                scanner.nextLine();
                if (repairCost > 0) {
                    mobilitySystem.getPricing().setRepairCost(vehicleType, repairCost);
                    System.out.println("Repair cost updated successfully.");
                } else {
                    System.out.println("Repair cost must be greater than 0.");
                }
                break;
            case 3:
                System.out.print("Enter new repair time in minutes: ");
                double repairTime = scanner.nextDouble();
                scanner.nextLine();
                if (repairTime > 0) {
                    mobilitySystem.getPricing().setRepairTime(vehicleType, repairTime);
                    System.out.println("Repair time updated successfully.");
                } else {
                    System.out.println("Repair time must be greater than 0.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void managePremiumStatus() {
        System.out.println("\n=== Manage Premium Status ===");
        System.out.println("Select a member to update premium status:");
        
        List<Person> members = peopleManager.getPeopleByType(Member.class);
        for (int i = 0; i < members.size(); i++) {
            Member member = (Member) members.get(i);
            System.out.printf("%d. %s (Premium: %s)\n", 
                i + 1, 
                member.getName(), 
                member.isPremium() ? "Yes" : "No");
        }
        
        System.out.print("Enter member number: ");
        int memberChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (memberChoice < 1 || memberChoice > members.size()) {
            System.out.println("Invalid member selection.");
            return;
        }
        
        Member selectedMember = (Member) members.get(memberChoice - 1);
        
        System.out.print("Set premium status (Y/N): ");
        String response = scanner.nextLine().trim().toUpperCase();
        
        if (response.equals("Y") || response.equals("N")) {
            boolean newStatus = response.equals("Y");
            selectedMember.setPremium(newStatus);
            System.out.printf("Premium status updated successfully for %s.\n", selectedMember.getName());
        } else {
            System.out.println("Invalid response. Please enter Y or N.");
        }
    }
}