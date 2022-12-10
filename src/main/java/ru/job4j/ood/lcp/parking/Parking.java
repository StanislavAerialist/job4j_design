package ru.job4j.ood.lcp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lcp.parking.Car.SIZE;

public class Parking implements Park {
    private int carCapacity;
    private int truckCapacity;
    private final List<Transport> carsParking;
    private final List<Transport> trucksParking;

    public Parking(int carCapacity, int truckCapacity) {
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;
        this.carsParking = new ArrayList<>(carCapacity);
        this.trucksParking = new ArrayList<>(truckCapacity);
    }
    @Override
    public boolean toPark(Transport car) {
        if (car.getSize() > SIZE && truckCapacity >= 1) {
            trucksParking.add(car);
            truckCapacity--;
            return true;
        }
        if (carCapacity - car.getSize() >= 0) {
            for (int i = 0; i < car.getSize(); i++) {
                carsParking.add(car);
                carCapacity--;
            }
            return true;
        }
        return false;
    }

    public List<Transport> getCars() {
        return carsParking;
    }

    public List<Transport> getTrucks() {
        return trucksParking;
    }

    public static void main(String[] args) {
        Parking parking = new Parking(2, 1);
        parking.toPark(new Car("BMW"));
        parking.toPark(new Car("BBJ"));
        System.out.println(parking.getCars());
        System.out.println(parking.getCars().size());
    }
}
