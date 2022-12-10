package ru.job4j.ood.dip;

public class PasswordReminder {
    private MySQLConnection dbConnection;

    public PasswordReminder() {
    this.dbConnection = MySQLConnection.connect();
    }
}
