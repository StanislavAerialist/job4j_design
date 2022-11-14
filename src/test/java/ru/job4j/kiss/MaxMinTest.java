package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void whenSearchMax() {
        MaxMin maxMin = new MaxMin();
        List<Integer> ints = Arrays.asList(1, 2, 3, 5, 6, 1);
        assertThat(maxMin.max(ints, Comparator.naturalOrder())).isEqualTo(6);
    }

    @Test
    void whenSearchMin() {
        MaxMin maxMin = new MaxMin();
        List<Integer> ints = Arrays.asList(1, 2, 3, 5, 6, 1);
        assertThat(maxMin.min(ints, Comparator.naturalOrder())).isEqualTo(1);
    }

    @Test
    void whenSearchNull() {
        MaxMin maxMin = new MaxMin();
        List<Integer> ints = new ArrayList<>();
        assertThat(maxMin.search(ints, Comparator.naturalOrder())).isNull();
    }
}