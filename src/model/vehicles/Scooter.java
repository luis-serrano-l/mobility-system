package src.model.vehicles;

import src.model.locations.Station;

public class Scooter extends Vehicle implements VehicleIF {
    private static final double BATTERY_CONSUMPTION = 0.5; // Battery decrease percentage per minute
    private static final double PRICE_PER_MINUTE = 1.0; // Price in euros per minute

    public Scooter(String id, Station station) {
        super(id, station.getLocation());
    }

    public double getPricePerMinute() {
        return PRICE_PER_MINUTE;
    }

    public double getBatteryConsumptionRate() {
        return BATTERY_CONSUMPTION;
    }
}