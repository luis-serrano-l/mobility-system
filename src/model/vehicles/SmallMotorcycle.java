package src.model.vehicles;

import src.model.locations.Location;

public class SmallMotorcycle extends Vehicle {
    private Location location;

    public SmallMotorcycle(int id, Location location) {
        super(id, location);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
