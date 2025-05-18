package src.model.trips;

import java.util.ArrayList;
import java.util.List;
import src.model.vehicles.Vehicle;
import src.model.people.members.Member;

public class TripsManager {
    private List<Trip> trips;
    
    public TripsManager() {
        this.trips = new ArrayList<>();
    }
    
    public void addTrip(Trip trip) {
        trips.add(trip);
    }
    
    public List<Trip> getTrips() {
        return trips;
    }

    public List<Trip> getTripsByMember(Member member) {
        List<Trip> memberTrips = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.getMember().equals(member)) {
                memberTrips.add(trip);
            }
        }
        return memberTrips;
    }

    public List<Trip> getTripsByVehicle(Vehicle vehicle) {
        List<Trip> vehicleTrips = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.getVehicle().equals(vehicle)) {
                vehicleTrips.add(trip);
            }
        }
        return vehicleTrips;
    }
}
