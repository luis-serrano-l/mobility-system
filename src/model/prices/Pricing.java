package src.model.prices;

import java.util.Map;
import java.util.HashMap;

public class Pricing {
    public Pricing() {
        Map<String, Double> priceMap = new HashMap<>();
        priceMap.put("scooter", 0.05);
        priceMap.put("bicycle", 0.03);
        priceMap.put("smallmotorcycle", 0.08);
        priceMap.put("bigmotorcycle", 0.10);
        this.vehiclePrices = priceMap;
    }

    private Map<String, Double> vehiclePrices;

    public double getPricePerSecond(String vehicleType) {
        String type = vehicleType.toLowerCase();
        if (!vehiclePrices.containsKey(type)) {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
        return vehiclePrices.get(type);
    }

    public void setPricePerSecond(String vehicleType, double price) {
        String type = vehicleType.toLowerCase();
        if (!vehiclePrices.containsKey(type)) {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
        vehiclePrices.put(type, price);
    }
}
