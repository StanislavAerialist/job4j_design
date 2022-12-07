package ru.job4j.ood.lcp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements Park {
    private int carCapacity;
    private int truckCapacity;

    private List<Car> carsParking;
    private List<Truck> trucksParking;

    public Parking(int carCapacity, int truckCapacity) {
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;
        this.carsParking = new ArrayList<>(carCapacity);
        this.trucksParking = new ArrayList<>(truckCapacity);
    }
    @Override
    public boolean toPark(Transport car) {
        return false;
    }

    public List<Car> getCars() {
        return carsParking;
    }

    public List<Truck> getTrucks() {
        return trucksParking;
    }
}
