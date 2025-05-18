package src.model.people.members;

import java.util.ArrayList;

import src.model.people.Person;
import src.model.trips.Trip;
import src.model.vehicles.Vehicle;
import src.model.vehicles.Scooter;
import src.model.vehicles.Bicycle;

public class Member extends Person {
    private double balance;
    private ArrayList<Trip> tripHistory;
    private boolean isPremium;
    private int id;

    public Member(int id, String name, String email) {
        super(id, name, email);
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

    public ArrayList<Trip> getTripHistory() {
        return tripHistory;
    }

    public boolean canPromoteToPremium() {
        // Check if member has used any vehicle at least 15 times
        if (tripHistory.size() >= 15) {
            return true;
        }

        // Check if member has used vehicles at least 30 times total
        if (tripHistory.size() >= 30) {
            return true;
        }

        // Check if member has used all vehicle types
        boolean hasUsedScooter = false;
        boolean hasUsedBicycle = false;
        boolean hasUsedMotorcycle = false;
        
        for (Trip trip : tripHistory) {
            Vehicle vehicle = trip.getVehicle();
            if (vehicle instanceof Scooter) {
                hasUsedScooter = true;
            } else if (vehicle instanceof Bicycle) {
                hasUsedBicycle = true; 
            } else { // Must be motorcycle
                hasUsedMotorcycle = true;
            }
        }
        
        boolean allTypesUsed = hasUsedScooter && hasUsedBicycle && hasUsedMotorcycle;
        
        return allTypesUsed;
    }

    public void setPremium(boolean premium) {
        this.isPremium = premium;
    }

    public void reportVehicleIssue(Vehicle vehicle, int issueType) {
        if (issueType == 1) {
            vehicle.setNeedsRepair(true);
        } else if (issueType == 2) {
            vehicle.setBatteryDamaged(true);
        }
    }
}