package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .isNotNull()
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .doesNotContain("six")
                .isNotInstanceOfAny(Integer.class, Double.class, String.class)
                .isInstanceOfAny(List.class);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("boom1", "boom2", "pip3", "goodbye4");
        assertThat(set).hasSize(4)
                .isNotNull()
                .isEqualTo(Set.of("boom1", "boom2", "pip3", "goodbye4"))
                .containsAnyOf("pip3", "boom3", "goodbye3")
                .doesNotContainNull()
                .contains("boom2")
                .doesNotContain("goodbye1");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("boom1", "boom2", "pip3", "goodbye4");
        assertThat(map).containsKey("boom1")
                .containsEntry("boom2", 1)
                .containsValues(2, 3)
                .doesNotContainKey("BigBoom")
                .doesNotContainEntry("boom", 2)
                .isNotNull()
                .hasSize(4);
    }
}