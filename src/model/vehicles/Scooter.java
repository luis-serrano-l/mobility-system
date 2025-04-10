package src.model.vehicles;

import src.model.Location;

public class Scooter extends Vehicle {
    private static final double BASE_RATE = 0.5;
    private static final double BATTERY_CONSUMPTION_RATE = 0.3;

    public Scooter(String id, Location location, int batteryLevel) {
        super(id, location, batteryLevel);
    }

    @Override
    public double calculateCost(double distance) {
        return distance * BASE_RATE;
    }

    @Override
    public double getBatteryConsumptionRate() {
        return BATTERY_CONSUMPTION_RATE;
    }
} 