package ru.job4j.ood.lcp.storage;

import java.util.Calendar;

public class Warehouse extends AbstractStore {

    private static final double WAREHOUSE = 25;
    @Override
    public boolean isNotExpired(Food food) {
        long start = food.getCreateDate().getTimeInMillis();
        long end = food.getExpiryDate().getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();
        double expirationPercent = ((now - start) * 100.0) / (end - start);
        return expirationPercent < WAREHOUSE;
    }
}
