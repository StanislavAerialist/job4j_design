package ru.job4j.ood.lcp.parking;

import java.util.List;

public class Parking implements Park {
    private int carCapacity;
    private int truckCapacity;

    private List<Car> carsParking;
    private List<Truck> trucksParking;

    public Parking(int carCapacity, int truckCapacity) {
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;
    }
    @Override
    public boolean toPark(AbstractCar car) {
        return false;
    }
}
