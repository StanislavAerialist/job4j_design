package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void classLoader() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculate(String s) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(s);
        }
    }
    public void createTable(String tableName) throws SQLException {
            String sql = String.format("create table if not exists %s;", tableName);
            calculate(sql);
    }

    public void dropTable(String tableName) throws SQLException {
            String sql = String.format("drop table %s;", tableName);
            calculate(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
            String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
            calculate(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
            String sql = String.format("alter table %s drop column %s;", tableName, columnName);
            calculate(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
            String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
            calculate(sql);
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            TableEditor tableEditor = new TableEditor(config);
            tableEditor.createTable("n_table()");
            Connection connect = tableEditor.connection;
            System.out.println(getTableScheme(connect, "n_table"));
            tableEditor.addColumn("n_table", "beer", "text");
            System.out.println(getTableScheme(connect, "n_table"));
            tableEditor.renameColumn("n_table", "beer", "sport");
            System.out.println(getTableScheme(connect, "n_table"));
            tableEditor.dropColumn("n_table", "sport");
            System.out.println(getTableScheme(connect, "n_table"));
            tableEditor.dropTable("n_table");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}