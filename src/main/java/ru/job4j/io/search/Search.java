package ru.job4j.io.search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    private final List<Path> dir = new ArrayList<>();

    public void search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        dir.addAll(searcher.getPaths());
    }

    private static void validation(ArgsName args) {
        Path path = Paths.get(args.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Not exists: %s", path));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not directory: %s", path));
        }
        String pattern = args.get("t");
        if (!("name".equals(pattern) || "mask".equals(pattern) || "regex".equals(pattern))) {
            throw new IllegalArgumentException(String.format("Invalid search type parameter: %s", pattern));
        }
    }

    private void save(Path out) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(out.toFile()))) {
            dir.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validation(argsName);
        Path path = Paths.get(argsName.get("d"));
        if ("mask".equals(argsName.get("t"))) {
            PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:" + argsName.get("n"));
            search(path, pm::matches);
        } else if ("name".equals(argsName.get("t"))) {
            search(path, p -> argsName.get("n").equalsIgnoreCase(p.toFile().getName()));
        } else {
            search(path, p -> p.toFile().toString().matches(argsName.get("n")));
        }
        if (dir.isEmpty()) {
            System.out.println("No files found");
        } else {
            save(Paths.get(argsName.get("o")));
        }
    }

    /**
     * Ключи:
     * -d - директория, в которой начинать поиск.
     * -n - имя файла, маска, либо регулярное выражение.
     * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
     * -o - результат записать в файл.
     */

    public static void main(String[] args) {
        Search search = new Search();
        try {
            search.start(new String[] {
                    "-d=C:/test", "-n=*/*/???.p*", "-t=mask", "-o=rsl.txt"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
