package ru.job4j.ood.lcp.storage;

import java.util.Calendar;

public class CalendarExpirationCalculator implements ExpirationCalculator<Calendar> {

    @Override
    public double calculateInPercent(Calendar startDate, Calendar endDate) {
        long start = startDate.getTimeInMillis();
        long end = endDate.getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();
        return ((now - start) * 100.0) / (end - start);
    }
}
