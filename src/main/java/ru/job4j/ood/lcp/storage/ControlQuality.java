package ru.job4j.ood.lcp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }
    public void add(Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }
    public List<Store> getStores() {
        return new ArrayList<>(stores);
    }
}
