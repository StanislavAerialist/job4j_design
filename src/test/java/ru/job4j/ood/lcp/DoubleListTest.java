package ru.job4j.ood.lcp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.collection.SimpleArrayList;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class DoubleListTest {
    @Test
    public void checkBehaviourForDoubleList() {
        SimpleArrayList list = new DoubleList(2);
        list.add("1");
        assertThat(list.size()).isEqualTo(1);
    }
}