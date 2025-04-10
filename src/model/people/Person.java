package src.model.people;

import java.util.Scanner;
import src.controller.MobilitySystem;

public abstract class Person {
    protected String id;
    protected String name;
    protected String username;
    protected String password;

    public Person(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void displayMenu();
    public abstract void handleOption(int option, Scanner scanner, MobilitySystem system);
} 