package src.utils;

import java.util.*;
import src.controller.MobilitySystem;
import src.model.people.User;
import src.model.people.Worker;
import src.model.people.Mechanic;
import src.model.vehicles.Vehicle;
import src.model.vehicles.ElectricMotorcycle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Scooter;
import src.model.Station;
import src.model.Location;
import java.util.Arrays;
import java.util.List;

public class UserGenerator {
    public static void generateMockData(MobilitySystem system) {
        // Set city limits
        system.setCityLimits(100, 100);

        // Create stations
        Station[] stations = {
            new Station("S1", "Central Station", new Location(50, 50), 15),
            new Station("S2", "North Station", new Location(20, 80), 10),
            new Station("S3", "South Station", new Location(80, 20), 10),
            new Station("S4", "East Station", new Location(80, 50), 12),
            new Station("S5", "West Station", new Location(20, 50), 12),
            new Station("S6", "University Station", new Location(30, 30), 8),
            new Station("S7", "Shopping Mall Station", new Location(70, 70), 10)
        };

        for (Station station : stations) {
            system.addStation(station);
        }

        // Create vehicles
        Vehicle[] vehicles = {
            // Electric Motorcycles
            new ElectricMotorcycle("EM1", new Location(50, 50), 100),
            new ElectricMotorcycle("EM2", new Location(20, 80), 100),
            new ElectricMotorcycle("EM3", new Location(80, 20), 100),
            new ElectricMotorcycle("EM4", new Location(80, 50), 100),
            new ElectricMotorcycle("EM5", new Location(20, 50), 100),
            new ElectricMotorcycle("EM6", new Location(30, 30), 100),
            new ElectricMotorcycle("EM7", new Location(70, 70), 100),
            
            // Bicycles
            new Bicycle("B1", new Location(50, 50), 100),
            new Bicycle("B2", new Location(20, 80), 100),
            new Bicycle("B3", new Location(80, 20), 100),
            new Bicycle("B4", new Location(80, 50), 100),
            new Bicycle("B5", new Location(20, 50), 100),
            new Bicycle("B6", new Location(30, 30), 100),
            new Bicycle("B7", new Location(70, 70), 100),
            new Bicycle("B8", new Location(40, 60), 100),
            new Bicycle("B9", new Location(60, 40), 100),
            
            // Scooters
            new Scooter("S1", new Location(50, 50), 100),
            new Scooter("S2", new Location(20, 80), 100),
            new Scooter("S3", new Location(80, 20), 100),
            new Scooter("S4", new Location(80, 50), 100),
            new Scooter("S5", new Location(20, 50), 100),
            new Scooter("S6", new Location(30, 30), 100),
            new Scooter("S7", new Location(70, 70), 100),
            new Scooter("S8", new Location(40, 60), 100),
            new Scooter("S9", new Location(60, 40), 100)
        };

        for (Vehicle vehicle : vehicles) {
            system.addVehicle(vehicle);
        }

        // Create users
        User[] users = {
            // Standard Users
            new User("U1", "John Doe", "john", "pass123", false),
            new User("U2", "Alice Smith", "alice", "pass123", false),
            new User("U3", "Bob Johnson", "bob", "pass123", false),
            new User("U4", "Emma Wilson", "emma", "pass123", false),
            new User("U5", "Michael Brown", "michael", "pass123", false),
            
            // Premium Users
            new User("P1", "Jane Smith", "jane", "pass123", true),
            new User("P2", "David Lee", "david", "pass123", true),
            new User("P3", "Sarah Davis", "sarah", "pass123", true),
            new User("P4", "James Wilson", "james", "pass123", true),
            new User("P5", "Emily Taylor", "emily", "pass123", true),
            
            // Workers
            new Worker("W1", "Admin User", "admin", "admin123"),
            new Worker("W2", "System Manager", "manager", "manager123"),
            
            // Mechanics
            new Mechanic("M1", "Mike Mechanic", "mike", "mike123"),
            new Mechanic("M2", "Tom Technician", "tom", "tom123")
        };

        for (User user : users) {
            system.addUser(user);
        }
    }

    public static List<Vehicle> generateVehicles() {
        return Arrays.asList(
            new Scooter("S1", new Location(50, 50), 100),
            new Scooter("S2", new Location(20, 80), 100),
            new Scooter("S3", new Location(80, 20), 100),
            new Scooter("S4", new Location(80, 50), 100),
            new Scooter("S5", new Location(20, 50), 100),
            new Scooter("S6", new Location(30, 30), 100),
            new Scooter("S7", new Location(70, 70), 100),
            new Scooter("S8", new Location(40, 60), 100),
            new Scooter("S9", new Location(60, 40), 100)
        );
    }
} 