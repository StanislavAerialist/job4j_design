package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        String output = argsName.get("out");
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")));
        PrintStream out = "stdout".equals(output) ? new PrintStream(System.out) : new PrintStream(new FileOutputStream(output))) {
            scanner.useDelimiter(System.lineSeparator());
            List<String> filters = Arrays.stream(argsName.get("filter").split(",")).toList();
            List<String> columns = Arrays.stream(scanner.next().split(argsName.get("delimiter"))).toList();
            List<Integer> indexes = filters.stream()
                    .mapToInt(columns::indexOf)
                    .filter(index -> index != -1)
                    .boxed()
                    .toList();

            String filteredColumns = filterLine(columns, indexes, argsName.get("delimiter"));
            out.println(filteredColumns);
            while (scanner.hasNext()) {
                List<String> line = Arrays.asList(scanner.next().split(argsName.get("delimiter")));
                String filteredLine = filterLine(line, indexes, argsName.get("delimiter"));
                out.println(filteredLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String filterLine(List<String> line, List<Integer> indexes, String delimiter) {
        return indexes.stream()
                .map(line::get)
                .collect(Collectors.joining(delimiter));
    }

    private static void validate(ArgsName args) {
        Path in = Paths.get(args.get("path"));
        if (!Files.exists(in) || !args.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("Invalid input parameter: %s", in));
        }
        if (!";".equals(args.get("delimiter"))) {
            throw new IllegalArgumentException(String.format("Delimiter must be ; : %s", args.get("delimiter")));
        }
        Path out = Paths.get(args.get("out"));
        if (!(Files.exists(out)) && out.toFile().isFile()) {
            throw new IllegalArgumentException(String.format("Invalid output parameter: %s", args.get("out")));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("args must contain -path, -delimiter, -out,  -filter");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}