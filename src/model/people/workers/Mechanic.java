package src.model.people.workers;

import java.util.Scanner;
import java.util.ArrayList;
import src.controller.MobilitySystem;
import src.model.vehicles.Vehicle;

public class Mechanic extends Worker {
    private ArrayList<Vehicle> assignedVehicles;
    private double pricePerMinute;

    public Mechanic(String name, String username, double pricePerMinute) {
        super(name, username);
        this.assignedVehicles = new ArrayList<>();
        this.pricePerMinute = pricePerMinute;
    }

    public void fixVehicle(Vehicle vehicle) {
        vehicle.repair();
        System.out.println("Vehicle fixed successfully!");
    }

    public void viewRepairHistory() {
        System.out.println("Total repair cost: " + totalRepairCost);
        System.out.println("Number of vehicles repaired: " + assignedVehicles.size());
    }

    public void generateInvoice(Vehicle vehicle) {
        vehicle.generateInvoice();
    }
}