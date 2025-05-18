package src.model.vehicles;

import src.model.locations.Station;

public class Bicycle extends Vehicle {
    private Station station;

    public Bicycle(int id, Station station) {
        super(id, station);
        this.station = station;
    }

    public Station getLocation() {
        return station;
    }

    public void setLocation(Station station) {
        this.station = station;
    }
}