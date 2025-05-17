package src.model.vehicles;

import src.model.locations.Location;

public class Motorcycle extends Vehicle implements VehicleIF {
    private enum MotorcycleSize {
        SMALL(0.4, 15),
        BIG(0.25, 20);

        private final double batteryConsumptionRate;
        private final double pricePerMinute;

        MotorcycleSize(double batteryConsumptionRate, double pricePerMinute) {
            this.batteryConsumptionRate = batteryConsumptionRate;
            this.pricePerMinute = pricePerMinute;
        }
    }

    private final MotorcycleSize size;

    public Motorcycle(String id, Location location) {
        super(id, location);
        this.size = MotorcycleSize.SMALL;
    }

    public Motorcycle(String id, Location location, String size) {
        super(id, location);
        switch (size.toUpperCase()) {
            case "S":
                this.size = MotorcycleSize.SMALL;
                break;
            case "B":
                this.size = MotorcycleSize.BIG;
                break;
            default:
                throw new IllegalArgumentException("Invalid motorcycle size");
        }
    }

    public double getBatteryConsumptionRate() {
        return size.batteryConsumptionRate;
    }

    public double getPricePerMinute() {
        return size.pricePerMinute;
    }
}