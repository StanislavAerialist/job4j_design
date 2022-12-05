package ru.job4j.ood.lcp.storage;

import java.util.Calendar;

public class Shop extends AbstractStore {
    private final ExpirationCalculator calculator = new CalendarExpirationCalculator();
    public static final double WAREHOUSE = 25;
    private static final double DISCOUNT = 75;
    public static final double TRASH = 100;

    @Override
    protected boolean isNotExpired(Food food) {
        boolean rsl = false;
        double expirationPercent = calculator.calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        if (expirationPercent > WAREHOUSE && expirationPercent < TRASH) {
            if (expirationPercent > DISCOUNT) {
                discount(food);
            }
            rsl = true;
        }
        return rsl;
    }

    private void discount(Food food) {
            food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount()));
    }
}
