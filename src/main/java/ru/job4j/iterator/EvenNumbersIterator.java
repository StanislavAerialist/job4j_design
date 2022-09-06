package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean even(Integer num) {
        return num % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        while (index < data.length) {
            if (even(data[index])) {
                rsl = even(data[index]);
                break;
            } else {
                index++;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
