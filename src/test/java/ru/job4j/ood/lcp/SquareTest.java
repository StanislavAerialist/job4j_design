package ru.job4j.ood.lcp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
public class SquareTest extends RectangleTest {
    @Test
    public void perimeter() {
        Rectangle rectangle = initRectangle();
        rectangle.setHeight(5);
        rectangle.setWidth(7);

        int result = rectangle.perimeter();

        assertThat(result).isEqualTo(24);
    }
    protected Rectangle initRectangle() {
        return new Square();
    }
}
