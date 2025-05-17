package src.model.vehicles;

import src.model.locations.Location;

public interface VehicleIF {
    String getId();
    Location getLocation();
    void setLocation(Location location);
    double getBattery();
    void setBattery(double battery);
    boolean needsRepair();
    void setNeedsRepair();
    void repair();
    boolean hasIssue();
    boolean needsStation();
    Location getCurrentLocation();
    void setCurrentLocation(Location location);
    double getPricePerMinute();
    double getBatteryConsumptionRate();
}