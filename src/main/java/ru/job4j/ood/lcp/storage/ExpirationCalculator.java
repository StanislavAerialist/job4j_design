package ru.job4j.ood.lcp.storage;

public interface ExpirationCalculator<T> {

    double calculateInPercent(T startDate, T endDate);

}
