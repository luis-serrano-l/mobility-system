package src.controller;

import src.model.locations.Location;
import src.model.locations.StationsManager;
import src.model.vehicles.VehiclesManager;
import src.model.people.PeopleManager;
import src.model.trips.TripsManager;
import src.model.prices.Pricing;

public class MobilitySystem {
    private Location cityLimits;
    private StationsManager stationsManager;
    private VehiclesManager vehiclesManager;
    private PeopleManager peopleManager;
    private TripsManager tripsManager;
    private Pricing pricing;

    public MobilitySystem() {
        this.cityLimits = new Location(100, 100);
        this.stationsManager = new StationsManager();
        this.vehiclesManager = new VehiclesManager();
        this.peopleManager = new PeopleManager();
        this.tripsManager = new TripsManager();
        this.pricing = new Pricing();
    }

    public void setCityLimits(int width, int height) {
        this.cityLimits = new Location(width, height);
    }

    public boolean isLocationWithinCityLimits(Location location) {
        return location.getX() >= 0 && location.getX() <= cityLimits.getX() &&
               location.getY() >= 0 && location.getY() <= cityLimits.getY();
    }

    public StationsManager getStationsManager() {
        return stationsManager;
    }

    public VehiclesManager getVehiclesManager() {
        return vehiclesManager;
    }

    public PeopleManager getPeopleManager() {
        return peopleManager;
    }

    public TripsManager getTripsManager() {
        return tripsManager;
    }

    public Pricing getPricing() {
        return pricing;
    }
}