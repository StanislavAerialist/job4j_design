package ru.job4j.ood.lcp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RectangleTest {

    @Test
    public void perimeter() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(5);
        rectangle.setWidth(7);

        int result = rectangle.perimeter();

        assertThat(result).isEqualTo(24);
    }

}