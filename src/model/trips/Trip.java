package src.model.trips;

import src.model.vehicles.Vehicle;
import src.model.vehicles.VehicleIF;
import src.model.people.standard.StandardUser;
import src.model.locations.Location;

public class Trip {
    private Vehicle vehicle;
    private StandardUser user;
    private Location startLocation;
    private Location endLocation;
    private double cost;
    private int duration;

    public Trip(Vehicle vehicle, StandardUser user, Location startLocation, Location endLocation, int duration) {
        this.vehicle = vehicle;
        this.user = user;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.duration = duration;
        this.cost = vehicle.getPricePerMinute() * duration;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public StandardUser getUser() {
        return user;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public int getDuration() {
        return duration;
    }
}