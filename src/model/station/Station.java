package src.model.station;

import java.util.ArrayList;
import java.util.List;
import src.model.vehicles.Vehicle;
import src.model.Location;

public class Station {
    private String id;
    private String name;
    private Location location;
    private int totalSlots;
    private List<Vehicle> parkedVehicles;
    private boolean hasMechanicalIssue;

    public Station(String id, String name, Location location, int totalSlots) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.totalSlots = totalSlots;
        this.parkedVehicles = new ArrayList<>();
        this.hasMechanicalIssue = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getAvailableSlots() {
        return totalSlots - parkedVehicles.size();
    }

    public List<Vehicle> getParkedVehicles() {
        return new ArrayList<>(parkedVehicles);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (getAvailableSlots() > 0) {
            parkedVehicles.add(vehicle);
            return true;
        }
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return parkedVehicles.remove(vehicle);
    }

    public boolean hasMechanicalIssue() {
        return hasMechanicalIssue;
    }

    public void setMechanicalIssue(boolean hasMechanicalIssue) {
        this.hasMechanicalIssue = hasMechanicalIssue;
    }

    @Override
    public String toString() {
        return String.format("Station %s at %s (%d/%d slots available)", 
            name, location, getAvailableSlots(), totalSlots);
    }
} 