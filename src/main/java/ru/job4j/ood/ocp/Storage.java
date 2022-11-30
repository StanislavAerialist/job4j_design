package ru.job4j.ood.ocp;

import java.util.List;

public class Storage {

    private List<Integer> store;
    private final int count = 15;

    public Storage(List store) {
        this.store = store;
    }

    public List<Integer> save(int i) {
        store.add(i + count);
        return store;
    }
}
