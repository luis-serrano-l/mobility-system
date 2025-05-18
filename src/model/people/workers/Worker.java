package src.model.people.workers;

import java.util.ArrayList;

import src.model.people.Person;
import src.model.vehicles.Vehicle;
public class Worker extends Person {
    private ArrayList<Vehicle> assignedVehicles;
    private boolean available;

    public Worker(int id, String name, String email) {
        super(id, name, email);
        this.assignedVehicles = new ArrayList<>();
        this.available = true;
    }

    public ArrayList<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }

    public void addAssignedVehicle(Vehicle vehicle) {
        assignedVehicles.add(vehicle);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void repairVehicle(Vehicle vehicle) {
        vehicle.setNeedsRepair(false);
    }
}