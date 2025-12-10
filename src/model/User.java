package model;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // ⭐ Add this method so combo box shows only the name
    @Override
    public String toString() {
        return name;
    }
}
