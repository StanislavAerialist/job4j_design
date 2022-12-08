package ru.job4j.ood.lcp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lcp.parking.Car.SIZE;

public class Parking implements Park {
    private int carCapacity;
    private int truckCapacity;
    private final List<Car> carsParking;
    private final List<Truck> trucksParking;

    public Parking(int carCapacity, int truckCapacity) {
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;
        this.carsParking = new ArrayList<>(carCapacity);
        this.trucksParking = new ArrayList<>(truckCapacity);
    }
    @Override
    public boolean toPark(Transport car) {
        boolean rsl = false;
        if (car.getSize() > SIZE) {
            if (truckCapacity - 1 >= 0) {
                trucksParking.add((Truck) car);
                truckCapacity--;
                rsl = true;
            } else if (carCapacity - car.getSize() >= 0) {
                for (int i = 0; i < car.getSize(); i++) {
                    carsParking.add(new Car(car.getName()));
                    carCapacity--;
                }
                rsl = true;
            }
        } else if (car.getSize() == SIZE && carCapacity - SIZE >= 0) {
            carsParking.add((Car) car);
            carCapacity--;
            rsl = true;
        }
        return rsl;
    }

    public List<Car> getCars() {
        return carsParking;
    }

    public List<Truck> getTrucks() {
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
