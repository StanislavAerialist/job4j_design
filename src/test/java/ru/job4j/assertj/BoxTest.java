package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isNotNull()
                .isGreaterThan("Bla")
                .isNotSameAs("Pip");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotNull()
                .isLessThan("Judge");
    }

    @Test
    void whenThisCubeEdge6ThenArea216() {
        Box box = new Box(8, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(216D)
                .isPositive()
                .isCloseTo(215d, withPercentage(1.0d));
    }

    @Test
    void whenThisSphereEdge1ThenArea12dot56() {
        Box box = new Box(0, 1);
        double area = box.getArea();
        assertThat(area).isEqualTo(12.566370614359172)
                .isPositive()
                .isCloseTo(12.56d, withPercentage(0.1d));
    }

    @Test
    void whenThisTetrahedronIsExist() {
        Box box = new Box(4, 1);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue()
                .isNotNull();
    }

    @Test
    void whenThisUnknownIsNotExist() {
        Box box = new Box(-1, 1);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse()
                .isNotNull();
    }

    @Test
    void whenThisCubeHas8Vertex() {
        Box box = new Box(8, 6);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(8)
                .isGreaterThan(7)
                .isLessThan(9);
    }

    @Test
    void whenThisTetrahedronHas4Vertex() {
        Box box = new Box(4, 6);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(4)
                .isGreaterThan(3)
                .isLessThan(5)
                .isPositive()
                .isNotNull();
    }
}