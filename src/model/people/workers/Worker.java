package src.model.people.workers;

import java.util.ArrayList;

import src.model.people.Person;
import src.model.vehicles.Vehicle;
public class Worker extends Person {
    private String name;
    private String email;
    private ArrayList<Vehicle> assignedVehicles;

    public Worker(int id, String name, String email) {
        super(name, email);
        this.id = id;
        this.assignedVehicles = new ArrayList<>();
    }

    public ArrayList<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }

    public void addAssignedVehicle(Vehicle vehicle) {
        assignedVehicles.add(vehicle);
    }

    public void takeVehicle(Vehicle vehicle) {
        vehicle.setAvailable(false);
    }

    public void returnVehicle(Vehicle vehicle) {
        vehicle.setAvailable(true);
    }

    public void repairVehicle(Vehicle vehicle) {
        vehicle.repair();
    }
}