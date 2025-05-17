package src.model.vehicles;

import src.model.locations.Location;

public class Bicycle extends Vehicle implements VehicleIF {

    private static final double PRICE_PER_MINUTE = 1;
    private static final double BATTERY_CONSUMPTION_RATE = 0.05;

    public Bicycle(String id, Location location) {
        super(id, location);
    }

    public double getBatteryConsumptionRate() {
        return BATTERY_CONSUMPTION_RATE;
    }

    public double getPricePerMinute() {
        return PRICE_PER_MINUTE;
    }
}