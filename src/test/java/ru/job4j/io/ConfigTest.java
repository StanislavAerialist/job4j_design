package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user")).isEqualTo("Stanislav");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("number")).isEqualTo("19");
        assertThat(config.value("val")).isEqualTo("uncommon");
    }

    @Test
    void whenStartWithEqual() {
        String path = "./data/start_with_equal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueIsEmpty() {
        String path = "./data/value_empty.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWithoutEqual() {
        String path = "./data/without_equal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenDoubleEqual() {
        String path = "./data/double_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("bang")).isEqualTo("value=1");
    }

    @Test
    void whenOnlyEqual() {
        String path = "./data/only_equal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}