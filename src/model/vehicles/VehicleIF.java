package src.model.vehicles;

import src.model.locations.Location;

public interface VehicleIF {
    int getId();
    Location getLocation();
    void setLocation(Location location);
    double getBattery();
    void setBattery(double battery);
    boolean needsRepair();
    void setNeedsRepair(boolean needsRepair);
    boolean hasIssue();
    boolean batteryDamaged();
    void setBatteryDamaged(boolean batteryDamaged);
}