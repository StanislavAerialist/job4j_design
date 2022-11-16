package ru.job4j.template;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Ignore
class SomeGeneratorTest {

    @Test
    void whenProduceSuccess() {
        SomeGenerator gen = new SomeGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        String rsl = "I am a Petr Arsentev, Who are you? ";
        assertThat(gen.produce(template, args)).isEqualTo(rsl);
    }

    @Test
    void whenProduceThenInvalidKey() {
        SomeGenerator gen = new SomeGenerator();
        String template = "I am a ${lastname}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            gen.produce(template, args);
        });
    }

    @Test
    void whenProduceThenInvalidTemplate() {
        SomeGenerator gen = new SomeGenerator();
        String template = "I am a ${name}, ${lastname}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            gen.produce(template, args);
        });
    }
}