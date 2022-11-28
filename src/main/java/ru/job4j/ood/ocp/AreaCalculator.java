package ru.job4j.ood.ocp;

public class AreaCalculator {
    public double area(Rectangle[] shapes) {
        double area = 0;
        for (var shape : shapes) {
            area += shape.getWidth() * shape.getHeight();
        }
        return area;
    }
}