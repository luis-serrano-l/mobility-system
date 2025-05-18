package src.model.vehicles;

import src.model.locations.Location;

public abstract class Vehicle implements VehicleIF {
    private Location location;
    private double battery;
    private boolean needsRepair;
    private boolean isActive;
    private boolean batteryDamaged;
    private boolean isAvailable;

    public Vehicle(Location location) {
        this.location = location;
        this.battery = 100;
        this.needsRepair = false;
        this.batteryDamaged = false;
        this.isActive = true;
        this.isAvailable = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public boolean needsRepair() {
        return needsRepair;
    }

    public boolean batteryDamaged() {
        return batteryDamaged;
    }

    public void setBatteryDamaged(boolean batteryDamaged) {
        this.batteryDamaged = batteryDamaged;
    }

    public void setNeedsRepair(boolean needsRepair) {
        this.needsRepair = needsRepair;
    }

    public boolean hasIssue() {
        return needsRepair;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
