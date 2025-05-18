package src.model.locations;

import java.util.ArrayList;

import src.model.vehicles.Vehicle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Scooter;

public class Station extends Location {
    private String name;
    private int bicycleCapacity;
    private int scooterCapacity;
    private ArrayList<Vehicle> totalBycicles;
    private ArrayList<Vehicle> totalScooters;

    public Station(double x, double y, String name, int bicycleCapacity, int scooterCapacity) {
        super(x, y);
        this.name = name;
        this.bicycleCapacity = bicycleCapacity;
        this.scooterCapacity = scooterCapacity;
        this.totalBycicles = new ArrayList<>();
        this.totalScooters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getBicycleCapacity() {
        return bicycleCapacity;
    }

    public int getScooterCapacity() {
        return scooterCapacity;
    }

    public ArrayList<Vehicle> getTotalScooters() {
        return totalScooters;
    }

    public ArrayList<Vehicle> getTotalBycicles() {
        return totalBycicles;
    }

    public boolean canAddBicycle() {
        return totalBycicles.size() < bicycleCapacity;
    }

    public boolean canAddScooter() {
        return totalScooters.size() < scooterCapacity;
    }

    public void addBicycle(Bicycle bicycle) {
        totalBycicles.add(bicycle);
    }

    public void addScooter(Scooter scooter) {
        totalScooters.add(scooter);
    }

    public int getAvailableByciclesSlots() {
        return bicycleCapacity - totalBycicles.size();
    }

    public int getAvailableScootersSlots() {
        return scooterCapacity - totalScooters.size();
    }

    @Override
    public String toString() {
        return String.format("%s at %s", name, super.toString());
    }
}
