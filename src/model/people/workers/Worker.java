package src.model.people.workers;

import java.util.ArrayList;
import java.util.List;

import src.model.people.Person;
import src.model.vehicles.Vehicle;

public abstract class Worker extends Person {
    private List<Vehicle> assignedVehicles;
    private boolean available;

    public Worker(int id, String name, String email) {
        super(id, name, email);
        this.assignedVehicles = new ArrayList<>();
        this.available = true;
    }

    public void assignVehicle(Vehicle vehicle) {
        if (!assignedVehicles.contains(vehicle)) {
            assignedVehicles.add(vehicle);
        }
    }

    public List<Vehicle> getAssignedVehicles() {
        return new ArrayList<>(assignedVehicles);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void repairVehicle(Vehicle vehicle) {
        if (assignedVehicles.contains(vehicle)) {
            vehicle.repair();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s\nEmail: %s\nAssigned Vehicles:", getName(), getEmail()));
        if (assignedVehicles.isEmpty()) {
            sb.append("\nNone");
        } else {
            for (Vehicle vehicle : assignedVehicles) {
                sb.append("\n- ").append(vehicle);
            }
        }
        return sb.toString();
    }
}