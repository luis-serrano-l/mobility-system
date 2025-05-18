package src.model.people;

public abstract class Person {
    protected String name;
    protected String email;
    protected boolean isActive;
    protected int id;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActiveStatus(boolean isActive) {
        this.isActive = isActive;
    }
}