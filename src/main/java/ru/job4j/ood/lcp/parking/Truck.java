package ru.job4j.ood.lcp.parking;

import java.util.Objects;

import static ru.job4j.ood.lcp.parking.Car.SIZE;

public class Truck implements Transport {
    private String name;
    private int size;

    public Truck(String name, int size) {
        if (size <= SIZE) {
            throw new IllegalArgumentException("Wrong Truck size");
        }
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Truck{"
                + "name='" + name + '\''
                + ", size=" + size + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size && Objects.equals(name, truck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
