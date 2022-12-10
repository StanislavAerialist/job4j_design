package ru.job4j.ood.lcp.storage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private List<Food> store = new ArrayList<>();

    public boolean add(Food food) {
        boolean rsl = false;
        if (isNotExpired(food)) {
            store.add(food);
            rsl = true;
        }
        return rsl;
    }

    public List<Food> getAll() {
        return new ArrayList<Food>(store);
    }

    protected abstract boolean isNotExpired(Food food);

    public void clear() {
        this.store.clear();
    }
}