package ru.job4j.serialization.json;

public class Candidate {
    private final String name;
    private final boolean serveInArmy;
    private final int age;
    private final Address address;
    private final String[] relatives;

    public Candidate(String name, boolean serveInArmy, int age, Address address, String[] relatives) {
        this.name = name;
        this.serveInArmy = serveInArmy;
        this.age = age;
        this.address = address;
        this.relatives = relatives;
    }


}
