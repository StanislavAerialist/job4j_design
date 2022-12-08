package ru.job4j.ood.lcp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TruckTest {

    @Test
    public void whenInvalidSize() {
        assertThatThrownBy(() -> new Truck("Invalid Truck", 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}