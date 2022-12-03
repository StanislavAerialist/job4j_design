package ru.job4j.ood.lcp;

import java.awt.*;

public class Draw {
    void drawShape(Shape shape) {
        if (shape instanceof Square) {
            drawSquare((Square) shape);
        } else {
            drawCircle((Circle) shape);
        }
    }
    private void drawCircle(Circle shape) {
    }
    private void drawSquare(Square shape) {
    }
}
