package src.model.prices;

import java.util.Map;
import java.util.HashMap;

public class Pricing {
    private Map<String, Double> vehiclePrices;
    private Map<String, Double> repairCosts;
    private Map<String, Double> repairTimes;
    private double baseRepairCost;
    private double baseRepairTime;

    public Pricing() {
        // Initialize vehicle prices per second
        vehiclePrices = new HashMap<>();
        vehiclePrices.put("scooter", 0.05);
        vehiclePrices.put("bicycle", 0.03);
        vehiclePrices.put("smallmotorcycle", 0.08);
        vehiclePrices.put("bigmotorcycle", 0.10);

        // Initialize repair costs per minute
        repairCosts = new HashMap<>();
        repairCosts.put("scooter", 2.0);
        repairCosts.put("bicycle", 1.5);
        repairCosts.put("smallmotorcycle", 3.0);
        repairCosts.put("bigmotorcycle", 4.0);

        // Initialize repair times in minutes
        repairTimes = new HashMap<>();
        repairTimes.put("scooter", 15.0);
        repairTimes.put("bicycle", 10.0);
        repairTimes.put("smallmotorcycle", 30.0);
        repairTimes.put("bigmotorcycle", 45.0);

        // Base costs for general repairs
        baseRepairCost = 20.0;
        baseRepairTime = 30.0;
    }

    public double getPricePerSecond(String vehicleType) {
        String type = vehicleType.toLowerCase();
        if (!vehiclePrices.containsKey(type)) {
            throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
        return vehiclePrices.get(type);
    }

    public void setPricePerSecond(String vehicleType, double price) {
        String type = vehicleType.toLowerCase();
        if (!vehiclePrices.containsKey(type)) {
            throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
        vehiclePrices.put(type, price);
    }

    public double getRepairCost(String vehicleType) {
        String type = vehicleType.toLowerCase();
        if (!repairCosts.containsKey(type)) {
            return baseRepairCost;
        }
        return repairCosts.get(type);
    }

    public void setRepairCost(String vehicleType, double cost) {
        String type = vehicleType.toLowerCase();
        if (!repairCosts.containsKey(type)) {
            throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
        repairCosts.put(type, cost);
    }

    public double getRepairTime(String vehicleType) {
        String type = vehicleType.toLowerCase();
        if (!repairTimes.containsKey(type)) {
            return baseRepairTime;
        }
        return repairTimes.get(type);
    }

    public void setRepairTime(String vehicleType, double time) {
        String type = vehicleType.toLowerCase();
        if (!repairTimes.containsKey(type)) {
            throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
        repairTimes.put(type, time);
    }

    public double calculateTotalRepairCost(String vehicleType) {
        double costPerMinute = getRepairCost(vehicleType);
        double repairTime = getRepairTime(vehicleType);
        return costPerMinute * repairTime;
    }

    public double getBaseRepairCost() {
        return baseRepairCost;
    }

    public void setBaseRepairCost(double baseRepairCost) {
        this.baseRepairCost = baseRepairCost;
    }

    public double getBaseRepairTime() {
        return baseRepairTime;
    }

    public void setBaseRepairTime(double baseRepairTime) {
        this.baseRepairTime = baseRepairTime;
    }
}
