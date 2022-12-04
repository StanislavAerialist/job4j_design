package ru.job4j.ood.lcp.storage;

import java.util.List;

public interface Store {

    boolean add(Food food);

    List<Food> getAll();
}
