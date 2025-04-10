package src.model.vehicles;

import src.model.Location;

public class Bicycle extends Vehicle {
    private static final double BASE_RATE = 0.3;

    public Bicycle(String id, Location location, int batteryLevel) {
        super(id, location, batteryLevel);
    }

    @Override
    public double getBatteryConsumptionRate() {
        return 0.2; // 0.2% per kilometer
    }

    @Override
    public double calculateCost(double distance) {
        return distance * BASE_RATE;
    }
} 