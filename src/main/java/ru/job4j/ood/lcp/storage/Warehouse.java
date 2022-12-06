package ru.job4j.ood.lcp.storage;

import java.util.Calendar;

import static ru.job4j.ood.lcp.storage.Shop.WAREHOUSE;

public class Warehouse extends AbstractStore {
    private final ExpirationCalculator<Calendar> expirationCalculator;

    public Warehouse(ExpirationCalculator<Calendar> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }
    @Override
    protected boolean isNotExpired(Food food) {
        double expirationPercent = expirationCalculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return expirationPercent < WAREHOUSE;
    }
}
