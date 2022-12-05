package ru.job4j.ood.lcp.storage;

import static ru.job4j.ood.lcp.storage.Shop.WAREHOUSE;

public class Warehouse extends AbstractStore {
    private final ExpirationCalculator calculator = new CalendarExpirationCalculator();
    @Override
    protected boolean isNotExpired(Food food) {
        double expirationPercent = calculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return expirationPercent < WAREHOUSE;
    }
}
