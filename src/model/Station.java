package src.model;

import java.util.List;
import java.util.ArrayList;
import src.model.vehicles.Vehicle;

public class Station {
    private String id;
    private String name;
    private Location location;
    private int capacity;
    private List<Vehicle> vehicles;
    private boolean hasMechanicalIssue;

    public Station(String id, String name, Location location, int capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
        this.hasMechanicalIssue = false;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicles.size() >= capacity || hasMechanicalIssue) {
            return false;
        }
        vehicles.add(vehicle);
        return true;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupancy() {
        return vehicles.size();
    }

    public boolean hasMechanicalIssue() {
        return hasMechanicalIssue;
    }

    public void setMechanicalIssue(boolean hasIssue) {
        this.hasMechanicalIssue = hasIssue;
    }

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }
} 