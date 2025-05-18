package src.model.vehicles;

import src.model.locations.Location;
import src.model.prices.Pricing;

public abstract class Vehicle implements VehicleIF {
    private int id;
    private Location location;
    private double battery;
    private boolean needsRepair;
    private boolean isActive;
    private boolean batteryDamaged;
    private boolean isAvailable;
    private double repairCost;
    private double repairTime;
    private Pricing pricing;

    public Vehicle(int id, Location location) {
        this.id = id;
        this.location = location;
        this.battery = 100;
        this.needsRepair = false;
        this.batteryDamaged = false;
        this.isActive = true;
        this.isAvailable = true;
        this.repairCost = 0.0;
        this.repairTime = 0.0;
        this.pricing = new Pricing();
    }

    public int getId() {
        return id;
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

    public void repair() {
        this.needsRepair = false;
        this.batteryDamaged = false;
        this.battery = 100;
        String vehicleType = this.getClass().getSimpleName().toLowerCase();
        this.repairCost = pricing.calculateTotalRepairCost(vehicleType);
        this.repairTime = pricing.getRepairTime(vehicleType);
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public double getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(double repairTime) {
        this.repairTime = repairTime;
    }

    public void generateInvoice() {
        System.out.println("=== Repair Invoice ===");
        System.out.println("Vehicle ID: " + id);
        System.out.println("Vehicle Type: " + this.getClass().getSimpleName());
        System.out.println("Repair Cost: $" + String.format("%.2f", repairCost));
        System.out.println("Repair Time: " + String.format("%.1f", repairTime) + " minutes");
        System.out.println("===================");
    }

    public double getBasePrice() {
        return 0.5; // Default base price per minute
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nType: %s\nLocation: %s\nBattery: %.1f%%\nNeeds Repair: %s\n-------------------",
            id,
            this.getClass().getSimpleName(),
            location,
            battery,
            needsRepair);
    }
}
