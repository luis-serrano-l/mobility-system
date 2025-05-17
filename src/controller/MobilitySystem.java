package src.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import src.model.locations.Location;
import src.model.locations.Station;
import src.model.trips.Trip;
import src.model.people.workers.Mechanic;
import src.model.people.workers.Worker;
import src.model.people.admin.Admin;
import src.model.people.standard.StandardUser;
import src.model.vehicles.Vehicle;
import src.model.vehicles.Motorcycle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Scooter;
import src.model.vehicles.VehicleIF;

public class MobilitySystem {
    private List<StandardUser> users;
    private List<Vehicle> vehicles;
    private List<Station> stations;
    private Map<String, Trip> activeTrips;
    private Location cityLimits;
    private double premiumDiscountRate;
    private StandardUser currentUser;
    private List<Mechanic> mechanics;
    private List<Worker> workers;
    private List<Admin> admins;
    private List<Trip> trips;
    private int cityWidth;
    private int cityHeight;

    public MobilitySystem() {
        this.users = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.activeTrips = new HashMap<>();
        this.cityLimits = new Location(100, 100); // Default city limits
        this.premiumDiscountRate = 0.20; // Default 20% discount for premium users
        this.mechanics = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.trips = new ArrayList<>();
        this.cityWidth = 0;
        this.cityHeight = 0;
        this.baseRate = 1.0;
    }

    public void setCityLimits(int width, int height) {
        this.cityWidth = width;
        this.cityHeight = height;
    }

    public boolean isLocationWithinCityLimits(Location location) {
        return location.getX() >= 0 && location.getX() <= (int)cityLimits.getX() &&
               location.getY() >= 0 && location.getY() <= (int)cityLimits.getY();
    }

    public StandardUser login(String username) {
        for (StandardUser user : users) {
            if (user.getUsername().equals(username)) {
                currentUser = user;
                return user;
            }
        }
        return null;
    }

    public Admin loginAdmin(String username) {
        for (StandardUser user : users) {
            if (user instanceof Admin && user.getUsername().equals(username)) {
                currentUser = user;
                return (Admin) user;
            }
        }
        return null;
    }

    public StandardUser loginUser(String username) {
        for (StandardUser user : users) {
            if (user.getUsername().equals(username)) {
                currentUser = user;
                return user;
            }
        }
        return null;
    }

    public void addUser(StandardUser user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public void addVehicle(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
        }
    }

    public Vehicle getVehicle(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }

    public Vehicle getVehicleById(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }

    public void addStation(Station station) {
        if (!stations.contains(station)) {
            stations.add(station);
        }
    }

    public Station getStation(String id) {
        for (Station station : stations) {
            if (station.getId().equals(id)) {
                return station;
            }
        }
        return null;
    }

    public Trip startTrip(StandardUser user, Vehicle vehicle) {
        if (activeTrips.containsKey(user.getId())) {
            return null;
        }

        String tripId = "T" + System.currentTimeMillis();
        Trip trip = new Trip(tripId, user, vehicle, vehicle.getCurrentLocation());
        activeTrips.put(user.getId(), trip);
        return trip;
    }

    public double endTrip(StandardUser user, Location endLocation) {
        Trip trip = activeTrips.get(user.getId());
        if (trip == null) {
            return -1;
        }

        trip.endTrip(endLocation);
        trip.getVehicle().setCurrentLocation(endLocation);
        activeTrips.remove(user.getId());
        
        // Apply premium discount if applicable
        double finalCost = trip.getCost();
        if (user.isPremium()) {
            finalCost *= (1 - premiumDiscountRate);
        }
        
        return finalCost;
    }

    public Trip getActiveTrip(StandardUser user) {
        return activeTrips.get(user.getId());
    }

    public List<Trip> getTripHistory(StandardUser user) {
        List<Trip> history = new ArrayList<>();
        for (Trip trip : activeTrips.values()) {
            if (trip.getUser().equals(user)) {
                history.add(trip);
            }
        }
        return history;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.hasIssue()) {
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

    public List<StandardUser> getAllUsers() {
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

    public StandardUser getCurrentUser() {
        return currentUser;
    }

    public void setBaseRate(double rate) {
        this.baseRate = rate;
    }

    public void setPremiumDiscountRate(double rate) {
        this.premiumDiscountRate = rate;
    }

    public StandardUser getUser(String id) {
        for (StandardUser user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public List<StandardUser> searchUsersByName(String name) {
        List<StandardUser> results = new ArrayList<>();
        for (StandardUser user : users) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(user);
            }
        }
        return results;
    }

    public List<StandardUser> searchUsersByType(String type) {
        List<StandardUser> results = new ArrayList<>();
        for (StandardUser user : users) {
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

    public List<StandardUser> getPotentialPremiumUsers() {
        List<StandardUser> potentialUsers = new ArrayList<>();
        for (StandardUser user : users) {
            if (!(user instanceof Mechanic) && !(user instanceof Admin) && !user.isPremium()) {
                if (getTripHistory(user).size() >= 5) {
                    potentialUsers.add(user);
                }
            }
        }
        return potentialUsers;
    }

    public Vehicle findVehicle(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }

    public boolean endTrip(StandardUser user, int x, int y) {
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

    public void addTrip(Trip trip) {
        if (!trips.contains(trip)) {
            trips.add(trip);
        }
    }

    public List<Mechanic> getMechanics() {
        return mechanics;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Station getStationById(String id) {
        for (Station station : stations) {
            if (station.getId().equals(id)) {
                return station;
            }
        }
        return null;
    }

    public Mechanic getMechanicById(String id) {
        for (Mechanic mechanic : mechanics) {
            if (mechanic.getId().equals(id)) {
                return mechanic;
            }
        }
        return null;
    }

    public Worker getWorkerById(String id) {
        for (Worker worker : workers) {
            if (worker.getId().equals(id)) {
                return worker;
            }
        }
        return null;
    }

    public Admin getAdminById(String id) {
        for (Admin admin : admins) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }
        return null;
    }

    public Trip getTripById(String id) {
        for (Trip trip : trips) {
            if (trip.getId().equals(id)) {
                return trip;
            }
        }
        return null;
    }

    public void removeVehicle(String id) {
        vehicles.removeIf(vehicle -> vehicle.getId().equals(id));
    }

    public void removeStation(String id) {
        stations.removeIf(station -> station.getId().equals(id));
    }

    public void removeUser(String id) {
        users.removeIf(user -> user.getId().equals(id));
        mechanics.removeIf(mechanic -> mechanic.getId().equals(id));
        workers.removeIf(worker -> worker.getId().equals(id));
        admins.removeIf(admin -> admin.getId().equals(id));
    }

    public void removeTrip(String id) {
        trips.removeIf(trip -> trip.getId().equals(id));
    }

    public void updateVehicleLocation(String id, Location location) {
        Vehicle vehicle = getVehicleById(id);
        if (vehicle != null) {
            vehicle.setLocation(location);
        }
    }

    public void updateStationLocation(String id, Location location) {
        Station station = getStationById(id);
        if (station != null) {
            station.setLocation(location);
        }
    }

    public void updateUserBalance(String id, double amount) {
        StandardUser user = getUser(id);
        if (user != null) {
            user.addBalance(amount);
        }
    }

    public void updateVehicleBattery(String id, int batteryLevel) {
        Vehicle vehicle = getVehicleById(id);
        if (vehicle != null) {
            vehicle.setBattery(batteryLevel);
        }
    }

    public void updateVehicleRepairStatus(String id, boolean needsRepair) {
        Vehicle vehicle = getVehicleById(id);
        if (vehicle != null) {
            vehicle.setNeedsRepair(needsRepair);
        }
    }

    public void updateVehicleAvailability(String id, boolean isAvailable) {
        Vehicle vehicle = getVehicleById(id);
        if (vehicle != null) {
            vehicle.setAvailable(isAvailable);
        }
    }

    public void updateStationCapacity(String id, int capacity) {
        Station station = getStationById(id);
        if (station != null) {
            station.setCapacity(capacity);
        }
    }

    public void updateUserPremiumStatus(String id, boolean isPremium) {
        StandardUser user = getUser(id);
        if (user != null) {
            user.setPremium(isPremium);
        }
    }

    public void updateTripEndTime(String id, long endTime) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setEndTime(endTime);
        }
    }

    public void updateTripCost(String id, int cost) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setCost(cost);
        }
    }

    public void updateTripStatus(String id, boolean isActive) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setActive(isActive);
        }
    }

    public void updateTripVehicle(String id, Vehicle vehicle) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setVehicle(vehicle);
        }
    }

    public void updateTripUser(String id, StandardUser user) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setUser(user);
        }
    }

    public void updateTripStartLocation(String id, Location location) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setStartLocation(location);
        }
    }

    public void updateTripEndLocation(String id, Location location) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setEndLocation(location);
        }
    }

    public void updateTripStartTime(String id, long startTime) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setStartTime(startTime);
        }
    }

    public void updateTripDuration(String id, int duration) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setDuration(duration);
        }
    }

    public void updateTripDistance(String id, double distance) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setDistance(distance);
        }
    }

    public void updateTripRating(String id, int rating) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setRating(rating);
        }
    }

    public void updateTripComment(String id, String comment) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setComment(comment);
        }
    }

    public void updateTripIssue(String id, boolean hasIssue) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setHasIssue(hasIssue);
        }
    }

    public void updateTripIssueDescription(String id, String issueDescription) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setIssueDescription(issueDescription);
        }
    }

    public void updateTripIssueReported(String id, boolean issueReported) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setIssueReported(issueReported);
        }
    }

    public void updateTripIssueReportedTime(String id, long issueReportedTime) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setIssueReportedTime(issueReportedTime);
        }
    }

    public void updateTripIssueReportedBy(String id, StandardUser user) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setIssueReportedBy(user);
        }
    }

    public void updateTripIssueReportedLocation(String id, Location location) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setIssueReportedLocation(location);
        }
    }

    public void updateTripIssueReportedDescription(String id, String issueDescription) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setIssueReportedDescription(issueDescription);
        }
    }

    public void updateTripIssueReportedStatus(String id, boolean issueReportedStatus) {
        Trip trip = getTripById(id);
        if (trip != null) {
            trip.setIssueReportedStatus(issueReportedStatus);
        }
    }

    public List<StandardUser> getUsers() {
        return users;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public StandardUser login(String username, String password) {
        for (StandardUser user : users) {
            if (user.getUsername().equals(username)) {
                // TODO: Implement proper password checking
                return user;
            }
        }
        return null;
    }

    public StandardUser register(String name, String username, String password) {
        // Check if username is already taken
        for (StandardUser user : users) {
            if (user.getUsername().equals(username)) {
                return null;
            }
        }

        // Create new user
        StandardUser newUser = new StandardUser("U" + (users.size() + 1), name, username, false);
        users.add(newUser);
        return newUser;
    }

    public StandardUser getUserById(String userId) {
        for (StandardUser user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public void addAdmin(Admin admin) {
        if (!admins.contains(admin)) {
            admins.add(admin);
        }
    }

    public void removeAdmin(Admin admin) {
        admins.remove(admin);
    }

    public void addMechanic(Mechanic mechanic) {
        if (!mechanics.contains(mechanic)) {
            mechanics.add(mechanic);
        }
    }

    public void removeMechanic(Mechanic mechanic) {
        mechanics.remove(mechanic);
    }
} 