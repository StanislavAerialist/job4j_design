package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
            Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Need arguments: start path and file extension");
        }
        if (!Files.exists(Path.of(args[0]))) {
            throw new IllegalArgumentException(String.format("Not exists: %s", Path.of(args[0])));
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException(String.format("Not directory: %s", Path.of(args[0])));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format("Extension must start from . : %s", Path.of(args[1])));
        }

    }
}