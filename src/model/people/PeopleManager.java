package src.model.people;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.model.people.workers.Worker;
import src.model.people.members.Member;
public class PeopleManager {
    private Map<Class<? extends Person>, List<Person>> peopleByType;

    public PeopleManager() {
        peopleByType = new HashMap<>();
    }

    public void addPerson(Person person) {
        peopleByType.computeIfAbsent(person.getClass(), k -> new ArrayList<>()).add(person);
    }

    public List<Person> getPeopleByType(Class<? extends Person> type) {
        return peopleByType.getOrDefault(type, new ArrayList<>());
    }

    public List<Person> getAllPeople() {
        List<Person> allPeople = new ArrayList<>();
        for (List<Person> people : peopleByType.values()) {
            allPeople.addAll(people);
        }
        return allPeople;
    }

    public Person getPersonByName(String name) {
        for (List<Person> people : peopleByType.values()) {
            for (Person person : people) {
                if (person.getName().equals(name)) {
                    return person;
                }
            }
        }
        return null;
    }

    public Worker getWorkerByName(String name) {
        for (List<Person> people : peopleByType.values()) {
            for (Person person : people) {
                if (person instanceof Worker && person.getName().equals(name)) {
                }
            }
        }
        return null;
    }

    public Member getMemberByName(String name) {
        for (List<Person> people : peopleByType.values()) {
            for (Person person : people) {
                if (person instanceof Member && person.getName().equals(name)) {
                    return (Member) person;
                }
            }
        }
        return null;
    }
}
