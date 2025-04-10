package src.model;

import java.time.LocalDateTime;
import src.model.vehicles.Vehicle;
import src.model.people.User;

public class Trip {
    private String id;
    private User user;
    private Vehicle vehicle;
    private Location startLocation;
    private Location endLocation;
    private double cost;
    private boolean isActive;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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

    public void endTrip(Location endLocation) {
        this.endLocation = endLocation;
        this.isActive = false;
        calculateCost();
        this.endTime = LocalDateTime.now();
    }

    private void calculateCost() {
        double distance = startLocation.distanceTo(endLocation);
        double baseCost = distance * 0.50; // Base rate per km
        if (user.isPremium()) {
            baseCost *= 0.8; // 20% discount for premium users
        }
        this.cost = baseCost;
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

    public double getCost() {
        return cost;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return String.format("Trip[id=%s, user=%s, vehicle=%s, start=%s, end=%s, cost=€%.2f, active=%s]",
            id, user.getName(), vehicle.getId(), startLocation, endLocation, cost, isActive);
    }
} 