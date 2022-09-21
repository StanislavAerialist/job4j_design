package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(file)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(file)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName args) {
        Path path = Paths.get(args.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Not exists: %s", path));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not directory: %s", path));
        }
        if (!args.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Exclusion must start from . : %s", args.get("e")));
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Target file must be zip: %s", args.get("o")));
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(String.format("args must contain -d directory, -e - exclude, -o - output"));
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.validate(argsName);
        List<Path> paths = Search.search(Paths.get(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(paths, new File(argsName.get("o")));
    }
}