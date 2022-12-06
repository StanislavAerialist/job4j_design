package ru.job4j.ood.lcp.storage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    @Test
    public void whenAddAllTypeOfFoods() {
    Store warehouse = new Warehouse(new CalendarExpirationCalculator());
    Store shop = new Shop(new CalendarExpirationCalculator());
    Store trash = new Trash(new CalendarExpirationCalculator());
    List<Store> stores = new ArrayList<>(List.of(warehouse, shop, trash));
    ControlQuality cq = new ControlQuality(stores);
    Calendar expiryDate = Calendar.getInstance();
    Calendar createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 10);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 1);
    Food food1 = new Food("warehouse", expiryDate, createDate, 100, 0.1);
        expiryDate = Calendar.getInstance();
        createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 3);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10);
    Food food2 = new Food("shop", expiryDate, createDate, 100, 0.1);
    expiryDate = new GregorianCalendar(2022, 01, 2);
    createDate = new GregorianCalendar(2021, 11, 16);
    Food food3 = new Food("trash", expiryDate, createDate, 100, 0.1);
    List<Food> rsl1 = new ArrayList<>();
    rsl1.add(food1);
    List<Food> rsl2 = new ArrayList<>();
    rsl2.add(food2);
    List<Food> rsl3 = new ArrayList<>();
    rsl3.add(food3);
    cq.add(food1);
    cq.add(food2);
    cq.add(food3);
    assertThat(warehouse.getAll()).hasSize(1).hasSameElementsAs(rsl1);
    assertThat(shop.getAll()).hasSize(1).hasSameElementsAs(rsl2);
    assertThat(trash.getAll()).hasSize(1).hasSameElementsAs(rsl3);
    }
}