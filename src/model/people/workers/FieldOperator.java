package src.model.people.workers;

import java.util.ArrayList;
import src.model.vehicles.Vehicle;

public class FieldOperator extends Worker {
    private ArrayList<Vehicle> assignedVehicles;

    public FieldOperator(int id, String name, String email) {
        super(id, name, email);
        this.assignedVehicles = new ArrayList<>();
    }

    public void fixVehicle(Vehicle vehicle) {
        vehicle.setNeedsRepair(false);
        System.out.println("Vehicle fixed successfully!");
    }

    public ArrayList<Vehicle> getAssignedVehicles() {
        return assignedVehicles;
    }
}
