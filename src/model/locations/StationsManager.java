package src.model.locations;

import java.util.ArrayList;
import java.util.List;

public class StationsManager {
    private List<Station> stations;
    
    public StationsManager() {
        stations = new ArrayList<>();
    }
    
    public void addStation(Station station) {
        stations.add(station);
    }
    
    public List<Station> getAllStations() {
        return new ArrayList<>(stations);
    }

    public Station getStationByName(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }
}
