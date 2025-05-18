package src.model.vehicles;

import src.model.locations.Location;

public class BigMotorcycle extends Vehicle {
    private Location location;

    public BigMotorcycle(int id, Location location) {
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
