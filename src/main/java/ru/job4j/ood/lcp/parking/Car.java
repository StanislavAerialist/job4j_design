package ru.job4j.ood.lcp.parking;

import java.util.Objects;

public class Car implements Transport {
    public static final int SIZE = 1;
    private String name;
    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "Car{"
                + "name='" + name + '\''
                + ", size=" + SIZE + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
