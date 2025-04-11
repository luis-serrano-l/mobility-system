package src.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import src.model.Location;
import src.model.station.Station;
import src.model.trip.Trip;
import src.model.people.User;
import src.model.people.Worker;
import src.model.people.Mechanic;
import src.model.people.Admin;
import src.model.vehicles.Vehicle;
import src.model.vehicles.ElectricMotorcycle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Scooter;
import src.utils.Persistence;

public class MobilitySystem {
    private List<User> users;
    private List<Vehicle> vehicles;
    private List<Station> stations;
    private Map<String, Trip> activeTrips;
    private Location cityLimits;
    private double baseRate;
    private double premiumDiscountRate;
    private Persistence persistence;
    private User currentUser;

    public MobilitySystem() {
        this.users = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.activeTrips = new HashMap<>();
        this.cityLimits = new Location(100, 100); // Default city limits
        this.baseRate = 0.50; // Default base rate per km
        this.premiumDiscountRate = 0.20; // Default 20% discount for premium users
        this.persistence = new Persistence();
    }

    public void setCityLimits(double x, double y) {
        this.cityLimits = new Location(x, y);
    }

    public boolean isLocationWithinCityLimits(Location location) {
        return location.getX() >= 0 && location.getX() <= (int)cityLimits.getX() &&
               location.getY() >= 0 && location.getY() <= (int)cityLimits.getY();
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle getVehicle(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public Station getStation(String id) {
        for (Station station : stations) {
            if (station.getId().equals(id)) {
                return station;
            }
        }
        return null;
    }

    public Trip startTrip(User user, Vehicle vehicle) {
        if (activeTrips.containsKey(user.getId())) {
            return null;
        }

        String tripId = "T" + System.currentTimeMillis();
        Trip trip = new Trip(tripId, user, vehicle, vehicle.getCurrentLocation());
        activeTrips.put(user.getId(), trip);
        return trip;
    }

    public double endTrip(User user, Location endLocation) {
        Trip trip = activeTrips.get(user.getId());
        if (trip == null) {
            return -1;
        }

        trip.endTrip(endLocation);
        trip.getVehicle().setCurrentLocation(endLocation);
        activeTrips.remove(user.getId());
        return trip.getCost();
    }

    public Trip getActiveTrip(User user) {
        return activeTrips.get(user.getId());
    }

    public List<Trip> getTripHistory(User user) {
        List<Trip> history = new ArrayList<>();
        for (Trip trip : activeTrips.values()) {
            if (trip.getUser().equals(user) && !trip.isActive()) {
                history.add(trip);
            }
        }
        return history;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.hasIssue() && !isVehicleInUse(vehicle)) {
                available.add(vehicle);
            }
        }
        return available;
    }

    public Vehicle findNearestVehicle(Location location) {
        Vehicle nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Vehicle vehicle : getAvailableVehicles()) {
            double distance = location.distanceTo(vehicle.getCurrentLocation());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = vehicle;
            }
        }

        return nearest;
    }

    public List<Vehicle> getVehiclesWithIssues() {
        List<Vehicle> withIssues = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.hasIssue()) {
                withIssues.add(vehicle);
            }
        }
        return withIssues;
    }

    public List<Station> getStationsWithIssues() {
        List<Station> stationsWithIssues = new ArrayList<>();
        for (Station station : stations) {
            if (station.hasMechanicalIssue()) {
                stationsWithIssues.add(station);
            }
        }
        return stationsWithIssues;
    }

    public int getCityLimitX() {
        return (int) cityLimits.getX();
    }

    public int getCityLimitY() {
        return (int) cityLimits.getY();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    public List<Station> getAllStations() {
        return new ArrayList<>(stations);
    }

    public List<Trip> getActiveTrips() {
        return new ArrayList<>(activeTrips.values());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void saveData() {
        Persistence.saveData(this);
    }

    public void setBaseRate(double rate) {
        this.baseRate = rate;
    }

    public void setPremiumDiscountRate(double rate) {
        this.premiumDiscountRate = rate;
    }

    public User getUser(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public List<User> searchUsersByName(String name) {
        List<User> results = new ArrayList<>();
        for (User user : users) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(user);
            }
        }
        return results;
    }

    public List<User> searchUsersByType(String type) {
        List<User> results = new ArrayList<>();
        for (User user : users) {
            if (type.equals("USER") && !(user instanceof Mechanic) && !(user instanceof Admin)) {
                results.add(user);
            } else if (type.equals("MECHANIC") && user instanceof Mechanic) {
                results.add(user);
            } else if (type.equals("ADMIN") && user instanceof Admin) {
                results.add(user);
            }
        }
        return results;
    }

    public List<User> getPotentialPremiumUsers() {
        List<User> potentialUsers = new ArrayList<>();
        for (User user : users) {
            if (!(user instanceof Mechanic) && !(user instanceof Admin) && !user.isPremium()) {
                if (getTripHistory(user).size() >= 5) {
                    potentialUsers.add(user);
                }
            }
        }
        return potentialUsers;
    }

    public boolean isVehicleInUse(Vehicle vehicle) {
        return activeTrips.values().stream()
            .anyMatch(trip -> trip.getVehicle().equals(vehicle));
    }

    public Vehicle findVehicle(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }

    public boolean endTrip(User user, int x, int y) {
        Trip trip = activeTrips.get(user);
        if (trip == null) {
            return false;
        }

        Location endLocation = new Location(x, y);
        if (!isLocationWithinCityLimits(endLocation)) {
            return false;
        }

        trip.endTrip(endLocation);
        activeTrips.remove(user);
        return true;
    }
} 