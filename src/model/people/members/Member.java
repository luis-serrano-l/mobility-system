package src.model.people.members;

import java.util.ArrayList;

import src.model.people.Person;
import src.model.trips.Trip;
import src.model.vehicles.Vehicle;

public class Member extends Person {
    private double balance;
    private ArrayList<Trip> tripHistory;
    private boolean isPremium;
    private int id;

    public Member(int id, String name, String email) {
        super(name, email);
        this.balance = 0.0;
        this.tripHistory = new ArrayList<>();
        this.isPremium = false;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public void addTrip(Trip trip) {
        tripHistory.add(trip);
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void canPromoteToPremium() {
        // TODO: Implement this method
    }

    public void setPremium(boolean premium) {
        this.isPremium = premium;
    }

    public void reportVehicleIssue(Vehicle vehicle) {
        vehicle.setNeedsRepair(true);
    }
}