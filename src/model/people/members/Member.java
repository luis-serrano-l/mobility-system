package src.model.people.standard;

import java.util.ArrayList;

import src.model.people.Person;
import src.model.trips.Trip;
import src.model.vehicles.Vehicle;

public class StandardUser extends Person {
    private double balance;
    private ArrayList<Trip> tripHistory;
    private boolean isPremium;
    private double discountRate;

    public StandardUser(String name, String username, boolean isPremium) {
        super(name, username);
        this.balance = 0.0;
        this.tripHistory = new ArrayList<>();
        this.isPremium = isPremium;
        this.discountRate = isPremium ? 0.2 : 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public boolean deductBalance(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public void addTrip(Trip trip) {
        tripHistory.add(trip);
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        this.isPremium = premium;
        this.discountRate = premium ? 0.2 : 0.0;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void reportVehicleIssue(Vehicle vehicle) {
        vehicle.setNeedsRepair();
    }
} 