package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] strings = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkNameContains() {
        NameLoad nameLoad = new NameLoad();
        String[] strings = new String[] {"name", "= boom"};
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(strings[0])
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void checkNameStartContains() {
        NameLoad nameLoad = new NameLoad();
        String[] strings = new String[] {"=name", "boom"};
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(strings[0])
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkNameEndContains() {
        NameLoad nameLoad = new NameLoad();
        String[] strings = new String[] {"name=", "boom"};
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(strings[0])
                .hasMessageContaining("does not contain a value");
    }
}