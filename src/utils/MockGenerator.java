package src.utils;

import src.model.vehicles.Vehicle;
import src.model.vehicles.Bicycle;
import src.model.vehicles.Scooter;
import src.model.vehicles.SmallMotorcycle;
import src.model.vehicles.BigMotorcycle;
import src.model.locations.Location;
import src.model.locations.Station;
import src.model.people.admin.Admin;
import src.model.people.members.Member;
import src.model.people.workers.FieldOperator;
import src.model.people.workers.Mechanic;
import src.model.people.PeopleManager;
import src.model.locations.StationsManager;
import src.model.vehicles.VehiclesManager;

import java.util.Arrays;
import java.util.List;
public class MockGenerator {
    public static void generateMockData() {
        // Set city limits
        // Create managers
        PeopleManager peopleManager = new PeopleManager();
        StationsManager stationsManager = new StationsManager();
        VehiclesManager vehiclesManager = new VehiclesManager();

        // Create Admin
        Admin admin = new Admin(1, "Admin", "admin@mobility.com");
        peopleManager.addPerson(admin);

        // Create Stations
        Station edisonStation = new Station(20, 30, "Edison Station", 10, 10);
        Station teslaStation = new Station(50, 60, "Tesla Station", 15, 15);
        Station bellStation = new Station(80, 70, "Bell Station", 12, 12);

        stationsManager.addStation(edisonStation);
        stationsManager.addStation(teslaStation);
        stationsManager.addStation(bellStation);

        // Create Workers
        Mechanic mechBoy = new Mechanic(2, "Mechboy", "mechboy@mobility.com");
        Mechanic mechMan = new Mechanic(7, "Mechman", "mechman@mobility.com");
        Mechanic mechPro = new Mechanic(8, "Mechpro", "mechpro@mobility.com");
        FieldOperator fieldBoy = new FieldOperator(3, "Fieldboy", "fieldboy@mobility.com");
        FieldOperator fieldMan = new FieldOperator(9, "Fieldman", "fieldman@mobility.com");
        FieldOperator fieldPro = new FieldOperator(10, "Fieldpro", "fieldpro@mobility.com");

        peopleManager.addPerson(mechBoy);
        peopleManager.addPerson(mechMan);
        peopleManager.addPerson(mechPro);
        peopleManager.addPerson(fieldBoy);
        peopleManager.addPerson(fieldMan);
        peopleManager.addPerson(fieldPro);

        // Create some vehicles at stations
        List<Vehicle> vehicles = Arrays.asList(
            new Bicycle(1, edisonStation),
            new Scooter(2, edisonStation),
            new SmallMotorcycle(3, new Location(10, 10)),
            new BigMotorcycle(4, new Location(15, 10)), 
            new Bicycle(5, bellStation),
            new Scooter(6, bellStation)
        );

        for (Vehicle vehicle : vehicles) {
            vehiclesManager.addVehicle(vehicle);
        }

        // Create some members
        List<Member> members = Arrays.asList(
            new Member(4, "John", "john@email.com"),
            new Member(5, "Jane", "jane@email.com"),
            new Member(6, "Bob", "bob@email.com")
        );

        for (Member member : members) {
            peopleManager.addPerson(member);
        }

    }
}