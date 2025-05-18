package src.model.people.workers;

import src.model.vehicles.Vehicle;

public class Mechanic extends Worker {
    private double scooterPricePerMinute;
    private double bicyclePricePerMinute;
    private double smallMotorcyclePricePerMinute;
    private double bigMotorcyclePricePerMinute;

    public Mechanic(int id, String name, String email) {
        super(id, name, email);
        // Default repair prices per minute
        this.scooterPricePerMinute = 2.0;
        this.bicyclePricePerMinute = 1.5;
        this.smallMotorcyclePricePerMinute = 3.0;
        this.bigMotorcyclePricePerMinute = 4.0;
    }

    public void fixVehicle(Vehicle vehicle) {
        vehicle.setNeedsRepair(false);
        System.out.println("Vehicle fixed successfully!");
    }

    public void generateInvoice(Vehicle vehicle) {
        double time = vehicle.getRepairTime();
        double pricePerMinute = getPriceForVehicleType(vehicle);
        double totalPrice = pricePerMinute * time;
        System.out.println("Repair Invoice:");
        System.out.println("Time spent: " + time + " minutes");
        System.out.println("Price per minute: $" + String.format("%.2f", pricePerMinute));
        System.out.println("Total price: $" + String.format("%.2f", totalPrice));
    }

    private double getPriceForVehicleType(Vehicle vehicle) {
        String vehicleType = vehicle.getClass().getSimpleName();
        switch(vehicleType) {
            case "Scooter":
                return scooterPricePerMinute;
            case "Bicycle":
                return bicyclePricePerMinute;
            case "SmallMotorcycle":
                return smallMotorcyclePricePerMinute;
            case "BigMotorcycle":
                return bigMotorcyclePricePerMinute;
            default:
                return 2.0; // Default price
        }
    }
}