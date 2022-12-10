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

    public void execute(List<Food> foods) {
        List<Food> rsl = new ArrayList<>(foods);
        for (Store store : this.stores) {
            for (Food food : rsl) {
                if (store.add(food)) {
                    rsl.remove(food);
                    break;
                }
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
            for (Store store : this.stores) {
                foods.addAll(store.getAll());
                store.clear();
            }
            execute(foods);
        }
}
