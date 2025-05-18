package src.model.trips;

import src.model.vehicles.Vehicle;
import src.model.vehicles.VehicleIF;
import src.model.locations.Location;
import src.model.people.members.Member;

public class Trip {
    private Vehicle vehicle;
    private Member member;
    private Location endLocation;
    private double cost;
    private int duration;

    public Trip(Vehicle vehicle, Member member, Location endLocation, int duration) {
        this.vehicle = vehicle;
        this.member = member;
        this.endLocation = endLocation;
        this.duration = duration;
        this.cost = ((VehicleIF)vehicle).getPricePerMinute() * duration;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Member getMember() {
        return member;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public int getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    public double getBatteryConsumption() {
        return ((VehicleIF)vehicle).getBatteryConsumptionRate() * duration;
    }
}