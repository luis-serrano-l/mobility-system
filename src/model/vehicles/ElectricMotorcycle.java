package src.model.vehicles;

import src.model.Location;

public class ElectricMotorcycle extends Vehicle {
    private static final double BASE_RATE = 0.4;
    private static final double BATTERY_CONSUMPTION_RATE = 0.5;

    public ElectricMotorcycle(String id, Location location, int batteryLevel) {
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