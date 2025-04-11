package src.utils;

import src.controller.MobilitySystem;
import src.model.people.User;
import src.model.vehicles.Vehicle;
import src.model.station.Station;
import src.model.Trip;
import java.io.*;
import java.util.List;

public class Persistence {
    private static final String DATA_FILE = "mock_data.txt";

    public static void saveData(MobilitySystem system) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            // Save city limits
            writer.println("CITY_LIMITS " + system.getCityLimitX() + " " + system.getCityLimitY());

            // Save stations
            for (Station station : system.getAllStations()) {
                writer.println("STATION " + station.getId() + " " + station.getName() + " " +
                             station.getLocation().getX() + " " + station.getLocation().getY() + " " +
                             station.getTotalSlots());
            }

            // Save vehicles
            for (Vehicle vehicle : system.getAllVehicles()) {
                writer.println("VEHICLE " + vehicle.getId() + " " + vehicle.getClass().getSimpleName() + " " +
                             vehicle.getCurrentLocation().getX() + " " + vehicle.getCurrentLocation().getY() + " " +
                             vehicle.getBatteryLevel() + " " + vehicle.isAvailable() + " " + vehicle.hasIssue());
            }

            // Save users
            for (User user : system.getAllUsers()) {
                writer.println("USER " + user.getId() + " " + user.getName() + " " + user.getUsername() + " " +
                             user.getPassword() + " " + user.isPremium());
            }

            // Save active trips
            for (Trip trip : system.getActiveTrips()) {
                writer.println("TRIP " + trip.getId() + " " + trip.getUser().getId() + " " +
                             trip.getVehicle().getId() + " " + trip.getStartTime());
            }

        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadData(MobilitySystem system) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                switch (parts[0]) {
                    case "CITY_LIMITS":
                        system.setCityLimits(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        break;
                    case "STATION":
                        // TODO: Implement station loading
                        break;
                    case "VEHICLE":
                        // TODO: Implement vehicle loading
                        break;
                    case "USER":
                        // TODO: Implement user loading
                        break;
                    case "TRIP":
                        // TODO: Implement trip loading
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
} 