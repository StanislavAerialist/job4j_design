package ru.job4j.io.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Invalid key %s", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(String.format("Invalid arguments %s", args));
        }
        Arrays.stream(args)
                .forEach(line -> {
                    if (!line.startsWith("-") || !line.contains("=")) {
                        throw new IllegalArgumentException(String.format("Incorrect line %s", line));
                    }
                    String[] s = line.split("=", 2);
                    if (s.length != 2 || s[0].isEmpty() || s[1].isEmpty()) {
                        throw new IllegalArgumentException(String.format("Incorrect line %s", line));
                    }
                    if (s[0].substring(1).isEmpty()) {
                        throw new IllegalArgumentException(String.format("Incorrect line %s", s[0]));
                    }
                    values.put(s[0].substring(1), s[1]);
                });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
