package ru.job4j.serialization.json;

public class Address {
    private final String home;
    public Address(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Address{"
                + "home='" + home + '\''
                + '}';
    }
}
