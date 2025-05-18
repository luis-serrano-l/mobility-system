package src.model.invoices;

import src.model.people.workers.Mechanic;
import src.model.vehicles.Vehicle;

public class Invoice {
    private String invoiceId;
    private Mechanic mechanic;
    private Vehicle vehicle;
    private String issueDescription;
    private double amount;

    public Invoice(String invoiceId, Mechanic mechanic, Vehicle vehicle, String issueDescription, double amount) {
        this.invoiceId = invoiceId;
        this.mechanic = mechanic;
        this.vehicle = vehicle;
        this.issueDescription = issueDescription;
        this.amount = amount;
    }
}