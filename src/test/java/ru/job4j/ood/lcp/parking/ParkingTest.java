package ru.job4j.ood.lcp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ParkingTest {

    @Test
    public void whenValidParkingWithCarsAndTruck() {
        Parking parking = new Parking(2, 1);
        Car car1 = new Car("WV");
        Car car2 = new Car("BMW");
        Truck truck = new Truck("Larry", 2);
        parking.toPark(car1);
        parking.toPark(car2);
        parking.toPark(truck);
        List<Car> rsl1 = List.of(car1, car2);
        List<Truck> rsl2 = List.of(truck);
        assertThat(parking.getCars()).hasSize(2).hasSameElementsAs(rsl1);
        assertThat(parking.getTrucks()).hasSize(1).hasSameElementsAs(rsl2);
    }

    @Test
    public void whenValidParkingWithTrucks() {
        Parking parking = new Parking(2, 1);
        Truck truck1 = new Truck("Larry", 4);
        Truck truck2 = new Truck("Man", 2);
        assertThat(parking.toPark(truck1)).isTrue();
        assertThat(parking.toPark(truck2)).isTrue();
        assertThat(parking.getCars()).hasSize(2);
        assertThat(parking.getTrucks()).hasSize(1).hasSameElementsAs(List.of(truck1));
    }

    @Test
    public void whenInvalidParkingWithTrucks() {
        Parking parking = new Parking(2, 1);
        Truck truck1 = new Truck("Larry", 4);
        Truck truck2 = new Truck("Man", 3);
        assertThat(parking.toPark(truck1)).isTrue();
        assertThat(parking.toPark(truck2)).isFalse();
    }

    @Test
    public void whenInvalidParkingWithCars() {
        Parking parking = new Parking(1, 1);
        Car car1 = new Car("WV");
        Car car2 = new Car("BMW");
        assertThat(parking.toPark(car1)).isTrue();
        assertThat(parking.toPark(car2)).isFalse();
    }

    @Test
    public void whenValidParkingWithCars() {
        Parking parking = new Parking(2, 1);
        Car car1 = new Car("WV");
        Car car2 = new Car("BMW");
        parking.toPark(car1);
        parking.toPark(car2);
        assertThat(parking.getCars()).hasSize(2).hasSameElementsAs(List.of(car1, car2));
    }
}