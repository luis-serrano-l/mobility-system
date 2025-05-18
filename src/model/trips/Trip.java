package src.model.trips;

import src.model.people.members.Member;
import src.model.vehicles.Vehicle;
import src.model.locations.Location;
import java.time.LocalDateTime;

public class Trip {
    private Member member;
    private Vehicle vehicle;
    private Location startLocation;
    private Location endLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double cost;
    private int duration;

    public Trip(Member member, Vehicle vehicle) {
        this.member = member;
        this.vehicle = vehicle;
        this.startLocation = vehicle.getLocation();
        this.startTime = LocalDateTime.now();
    }

    public void endTrip(Location endLocation, int duration) {
        this.endLocation = endLocation;
        this.endTime = LocalDateTime.now();
        this.duration = duration;
        this.cost = calculateCost();
        updateBattery();
    }

    private void updateBattery() {
        // Calculate battery consumption based on vehicle type and duration
        double batteryConsumption;
        String vehicleType = vehicle.getClass().getSimpleName().toLowerCase();
        
        switch (vehicleType) {
            case "scooter":
                batteryConsumption = 0.1 * (duration / 60.0); // 0.1% per minute
                break;
            case "bicycle":
                batteryConsumption = 0.05 * (duration / 60.0); // 0.05% per minute
                break;
            case "smallmotorcycle":
                batteryConsumption = 0.2 * (duration / 60.0); // 0.2% per minute
                break;
            case "bigmotorcycle":
                batteryConsumption = 0.3 * (duration / 60.0); // 0.3% per minute
                break;
            default:
                batteryConsumption = 0.1 * (duration / 60.0); // Default consumption
        }

        // Update vehicle's battery level
        double currentBattery = vehicle.getBattery();
        double newBattery = Math.max(0, currentBattery - batteryConsumption);
        vehicle.setBattery(newBattery);

        // If battery is below 20%, mark as needing repair
        if (newBattery < 20) {
            vehicle.setBatteryDamaged(true);
        }
    }

    private double calculateCost() {
        // Basic cost calculation based on duration and vehicle type
        return (duration / 60.0) * vehicle.getBasePrice();
    }

    public Member getMember() {
        return member;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getCost() {
        return cost;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return String.format("Trip from %s to %s\nVehicle: %s\nDuration: %d seconds\nCost: $%.2f",
            startLocation, endLocation, vehicle, duration, cost);
    }
}