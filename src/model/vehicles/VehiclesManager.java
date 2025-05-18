package src.model.vehicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehiclesManager {
    private Map<Class<? extends Vehicle>, List<Vehicle>> vehiclesByType;
    
    public VehiclesManager() {
        vehiclesByType = new HashMap<>();
        vehiclesByType.put(Bicycle.class, new ArrayList<>());
        vehiclesByType.put(Scooter.class, new ArrayList<>());
        vehiclesByType.put(SmallMotorcycle.class, new ArrayList<>());
        vehiclesByType.put(BigMotorcycle.class, new ArrayList<>());
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehiclesByType.get(vehicle.getClass()).add(vehicle);
    }
    
    public List<Vehicle> getVehiclesByType(Class<? extends Vehicle> type) {
        return vehiclesByType.get(type);
    }
    
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> allVehicles = new ArrayList<>();
        for (List<Vehicle> vehicles : vehiclesByType.values()) {
            allVehicles.addAll(vehicles);
        }
        return allVehicles;
    }

    public List<Vehicle> getAllAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (List<Vehicle> vehicles : vehiclesByType.values()) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.isAvailable() && !vehicle.needsRepair()) {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }

    public Vehicle getVehicleById(int vehicleId) {
        for (List<Vehicle> vehicles : vehiclesByType.values()) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId() == vehicleId) {
                    return vehicle;
                }
            }
        }
        return null;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (List<Vehicle> vehicles : vehiclesByType.values()) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.isAvailable() && !vehicle.needsRepair()) {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }
}
