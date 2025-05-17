package src.model.vehicles;

import src.model.locations.Location;

public class Vehicle {
    private Location location;
    private double battery;
    private boolean needsRepair;

    public Vehicle(Location location) {
        this.location = location;
        this.needsRepair = false;
        this.battery = 100;
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

    public void charge() {
        this.battery = 100;
    }

    public void useBattery(double battery) {
        this.battery -= battery;
    }

    public boolean needsRepair() {
        return needsRepair;
    }

    public void setNeedsRepair() {
        this.needsRepair = true;
    }

    public void repair() {
        this.needsRepair = false;
    }

    public boolean hasIssue() {
        return needsRepair;
    }
}
