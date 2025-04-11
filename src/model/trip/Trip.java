package src.model.trip;

import src.model.Location;
import src.model.vehicles.Vehicle;
import src.model.people.User;
import java.time.LocalDateTime;

public class Trip {
    private String id;
    private User user;
    private Vehicle vehicle;
    private Location startLocation;
    private Location endLocation;
    private double cost;
    private boolean isActive;
    private LocalDateTime startTime;

    public Trip(String id, User user, Vehicle vehicle, Location startLocation) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.startLocation = startLocation;
        this.endLocation = null;
        this.cost = 0.0;
        this.isActive = true;
        this.startTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void endTrip(Location endLocation) {
        this.endLocation = endLocation;
        this.isActive = false;
        // Calculate cost based on distance
        double distance = startLocation.distanceTo(endLocation);
        this.cost = distance * 0.5; // Base rate of 0.5€ per km
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return String.format("Trip %s: %s -> %s, Cost: €%.2f",
            id,
            startLocation,
            endLocation != null ? endLocation : "ongoing",
            cost);
    }
} 