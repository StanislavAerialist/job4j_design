package ru.job4j.ood.lcp.storage;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    public void whenAddWithDiscount() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 3);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10);
        Food food = new Food("foodWithD", expiryDate, createDate, 100, 0.1);
        Store shop = new Shop();
        shop.add(food);
        List<Food> foods = shop.getAll();
        assertThat(foods.get(0).getPrice()).isEqualTo(90);
    }

    @Test
    public void whenAddWithoutDiscount() {
        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 10);
        createDate.set(createDate.get(Calendar.YEAR), createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10);
        Food food = new Food("foodWithoutD", expiryDate, createDate, 100, 0.1);
        Store shop = new Shop();
        shop.add(food);
        List<Food> foods = shop.getAll();
        assertThat(foods.get(0).getPrice()).isEqualTo(100);
    }
}