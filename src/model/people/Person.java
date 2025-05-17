package src.model.people;

public class Person {
    protected String name;
    protected String username;

    public Person(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
} 