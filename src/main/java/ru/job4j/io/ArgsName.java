package ru.job4j.io;

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
        if (args.length == 0) {
            throw new IllegalArgumentException(String.format("Invalid arguments %s", args));
        }
        Arrays.stream(args)
                .forEach(line -> {
                    String[] s = line.split("=", 2);
                    if (s.length != 2 || s[0].isEmpty() || !s[0].startsWith("-") || s[1].isEmpty()) {
                        throw new IllegalArgumentException(String.format("Incorrect line %s", line));
                    }
                    String[] s2 = s[0].split("-");
                    if (s2.length != 2  || s2[1].isEmpty()) {
                        throw new IllegalArgumentException(String.format("Incorrect line %s", s[0]));
                    }
                    values.put(s[0].split("-")[1], s[1]);
                });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}