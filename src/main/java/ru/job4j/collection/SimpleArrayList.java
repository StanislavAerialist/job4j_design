package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;
    private int count;

    private int expectedModCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        add(value, container, size);
    }

    private void add(T value, T[] container, int s) {
        if (s == container.length) {
            container = grow();
        }
        container[s] = value;
        size = s + 1;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T rsl = container[index];
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        final int newSize = size - 1;
        T rsl = container[index];
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
        }
        size = newSize;
        container[size] = null;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private T[] grow() {
        container = Arrays.copyOf(container, size * 2);
        return container;
    }

    @Override
    public Iterator<T> iterator() {
        count = 0;
        expectedModCount = modCount;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
            return container[count++];
            }
        };
    }
}
