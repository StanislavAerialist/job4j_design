package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int cReturn = 0;
    private int cIn = 0;

    public T poll() {
        if (cReturn == 0 && cIn == 0) {
            throw new NoSuchElementException();
        }
        if (cReturn == 0) {
            while (cIn != 0) {
                out.push(in.pop());
                cIn--;
                cReturn++;
            }
        }
        cReturn--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        cIn++;
    }
}