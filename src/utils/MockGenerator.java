package src.utils;

import src.controller.MobilitySystem;
import src.model.vehicles.Vehicle;
import src.model.vehicles.Motorcycle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Scooter;
import src.model.locations.Location;
import src.model.locations.Station;
import src.model.people.admin.Admin;
import src.model.people.standard.StandardUser;
import src.model.people.workers.Mechanic;
import src.model.people.workers.Worker;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class MockGenerator {
    public static void generateMockData(MobilitySystem mobilitySystem) {
        // Set city limits
        mobilitySystem.setCityLimits(100, 100);

        // Create stations
        Station[] stations = {
            new Station("S1", new Location(50, 50), 15),
            new Station("S2", new Location(20, 80), 10),
            new Station("S3", new Location(80, 20), 10),
            new Station("S4", new Location(80, 50), 12),
            new Station("S5", new Location(20, 50), 12),
            new Station("S6", new Location(30, 30), 8),
            new Station("S7", new Location(70, 70), 10)
        };

        for (Station station : stations) {
            mobilitySystem.addStation(station);
        }

        // Create vehicles
        Vehicle[] vehicles = {
            // Electric Motorcycles
            new Motorcycle("EM1", new Location(50, 50), 100, Motorcycle.MotorcycleSize.SMALL),
            new Motorcycle("EM2", new Location(20, 80), 100, Motorcycle.MotorcycleSize.SMALL),
            new Motorcycle("EM3", new Location(80, 20), 100, Motorcycle.MotorcycleSize.BIG),
            new Motorcycle("EM4", new Location(80, 50), 100, Motorcycle.MotorcycleSize.BIG),
            new Motorcycle("EM5", new Location(20, 50), 100, Motorcycle.MotorcycleSize.SMALL),
            new Motorcycle("EM6", new Location(30, 30), 100, Motorcycle.MotorcycleSize.SMALL),
            new Motorcycle("EM7", new Location(70, 70), 100, Motorcycle.MotorcycleSize.BIG),
            
            // Bicycles
            new Bicycle("B1", new Location(50, 50), 100, 5.0, true, false),
            new Bicycle("B2", new Location(20, 80), 100, 5.0, true, false),
            new Bicycle("B3", new Location(80, 20), 100, 5.0, true, false),
            new Bicycle("B4", new Location(80, 50), 100, 5.0, true, false),
            new Bicycle("B5", new Location(20, 50), 100, 5.0, true, false),
            new Bicycle("B6", new Location(30, 30), 100, 5.0, true, false),
            new Bicycle("B7", new Location(70, 70), 100, 5.0, true, false),
            new Bicycle("B8", new Location(40, 60), 100, 5.0, true, false),
            new Bicycle("B9", new Location(60, 40), 100, 5.0, true, false),
            
            // Scooters
            new Scooter("S1", new Location(50, 50), 100.0, 7.0, true),
            new Scooter("S2", new Location(20, 80), 100.0, 7.0, true),
            new Scooter("S3", new Location(80, 20), 100.0, 7.0, true),
            new Scooter("S4", new Location(80, 50), 100.0, 7.0, true),
            new Scooter("S5", new Location(20, 50), 100.0, 7.0, true),
            new Scooter("S6", new Location(30, 30), 100.0, 7.0, true),
            new Scooter("S7", new Location(70, 70), 100.0, 7.0, true),
            new Scooter("S8", new Location(40, 60), 100.0, 7.0, true),
            new Scooter("S9", new Location(60, 40), 100.0, 7.0, true)
        };

        for (Vehicle vehicle : vehicles) {
            mobilitySystem.addVehicle(vehicle);
        }

        // Create users
        StandardUser[] users = {
            // Standard Users
            createStandardUser("U1", "John Doe", "john", false),
            createStandardUser("U2", "Alice Smith", "alice", false),
            createStandardUser("U3", "Bob Johnson", "bob", false),
            createStandardUser("U4", "Emma Wilson", "emma", false),
            createStandardUser("U5", "Michael Brown", "michael", false),
            
            // Premium Users
            createStandardUser("P1", "Jane Smith", "jane", true),
            createStandardUser("P2", "David Lee", "david", true),
            createStandardUser("P3", "Sarah Davis", "sarah", true),
            createStandardUser("P4", "James Wilson", "james", true),
            createStandardUser("P5", "Emily Taylor", "emily", true),
            
            // Admin
            new Admin("W1", "Admin User", "admin")
        };

        for (StandardUser user : users) {
            mobilitySystem.addUser(user);
        }

        // Create mechanics
        Mechanic[] mechanics = {
            new Mechanic("M1", "Mike Mechanic", "mech1"),
            new Mechanic("M2", "Tom Technician", "mech2")
        };

        for (Mechanic mechanic : mechanics) {
            mobilitySystem.addMechanic(mechanic);
        }
    }

    private static StandardUser createStandardUser(String id, String name, String username, boolean isPremium) {
        return new StandardUser(id, name, username, isPremium);
    }
    public static List<Vehicle> generateVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        
        // Add scooters
        vehicles.add(new Scooter("S1", new Location(50, 50), 100.0, 7.0, true));
        vehicles.add(new Scooter("S2", new Location(20, 80), 100.0, 7.0, true));
        vehicles.add(new Scooter("S3", new Location(80, 20), 100.0, 7.0, true));
        vehicles.add(new Scooter("S4", new Location(80, 50), 100.0, 7.0, true));
        vehicles.add(new Scooter("S5", new Location(20, 50), 100.0, 7.0, true));
        // Add bicycles
        vehicles.add(new Bicycle("B1", new Location(30, 30), 100.0, 5.0, true, false));
        vehicles.add(new Bicycle("B2", new Location(70, 70), 100.0, 5.0, true, false));
        
        // Add motorcycles
        vehicles.add(new Motorcycle("M1", new Location(40, 60), 100.0, Motorcycle.MotorcycleSize.SMALL));
        vehicles.add(new Motorcycle("M2", new Location(60, 40), 100.0, Motorcycle.MotorcycleSize.MEDIUM));

        return vehicles;
    }
}