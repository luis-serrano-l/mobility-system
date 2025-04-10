package src.model.vehicles;

import src.model.Location;

public abstract class Vehicle {
    protected String id;
    protected Location location;
    protected int batteryLevel;
    protected boolean hasIssue;
    protected boolean isAvailable;
    private Location currentLocation;

    public Vehicle(String id, Location location, int batteryLevel) {
        this.id = id;
        this.location = location;
        this.batteryLevel = batteryLevel;
        this.hasIssue = false;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setCurrentLocation(Location location) {
        this.location = location;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public boolean hasIssue() {
        return hasIssue;
    }

    public void setHasIssue(boolean hasIssue) {
        this.hasIssue = hasIssue;
    }

    public boolean isAvailable() {
        return !hasIssue && batteryLevel > 20;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void charge() {
        this.batteryLevel = 100;
    }

    public abstract double calculateCost(double distance);
    public abstract double getBatteryConsumptionRate();

    @Override
    public String toString() {
        return String.format("ID: %s, Type: %s, Battery: %.1f%%, Location: %s, Has Issue: %s",
            id,
            this.getClass().getSimpleName(),
            (float)batteryLevel,
            location,
            hasIssue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle other = (Vehicle) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Location getCurrentLocation() {
        return location;
    }

    public void moveTo(double x, double y) {
        this.location = new Location((int)x, (int)y);
    }
} 