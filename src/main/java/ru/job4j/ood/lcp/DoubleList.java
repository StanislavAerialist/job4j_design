package ru.job4j.ood.lcp;
import ru.job4j.collection.SimpleArrayList;
import java.util.Arrays;

public class DoubleList<T> extends SimpleArrayList {
    private T[] container;
    private int size;
    public DoubleList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(Object value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = (T) value;
        size += 1;
        container[size] = (T) value;
        size += 1;
    }

    private T[] grow() {
        if (container.length == 0) {
            container = (T[]) new Object[10];
        } else {
            container = Arrays.copyOf(container, size * 2);
        }
        return container;
    }

    @Override
    public int size() {
        return size;
    }
}
