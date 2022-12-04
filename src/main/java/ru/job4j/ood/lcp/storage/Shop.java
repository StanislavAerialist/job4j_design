package ru.job4j.ood.lcp.storage;

import java.util.Calendar;

public class Shop extends AbstractStore {
    private static final double WAREHOUSE = 25;
    private static final double DISCOUNT = 75;
    private static final double TRASH = 100;

    @Override
    public boolean isNotExpired(Food food) {
        boolean rsl = false;
        long start = food.getCreateDate().getTimeInMillis();
        long end = food.getExpiryDate().getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();
        double expirationPercent = ((now - start) * 100.0) / (end - start);
        if (expirationPercent > WAREHOUSE && expirationPercent < TRASH) {
            if (expirationPercent > DISCOUNT) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount()));
            }
            rsl = true;
        }
        return rsl;
    }
}
