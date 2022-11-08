package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Emulator {
    private static final int LOAD_CACHE = 1;
    private static final int SHOW_CACHE = 2;
    private static final String DIR_PATH = "Введите путь к папке для кэширования.";
    private static final String FILE = "Введите \\\\имя_файла для кэширования.";
    private static final String RESULT = "Введите \\\\имя_файла для просмотра содержимого.";
    private static final String EXIT = """ 
                Доброго дня
                System shut down...
                Bip""";
    public static final String MENU = """ 
                Меню
                Введите 1, чтобы загрузить содержимое файла в кэш.
                Введите 2, чтобы получить содержимое файла из кэша.
                Для выхода введите любое другое число.""";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(DIR_PATH);
        String dir = bufferedReader.readLine();
        if (!Files.exists(Paths.get(dir))) {
            throw new IllegalArgumentException(String.format("Directory %s doesn't exist.", dir));
        }
        DirFileCache dirFileCache = new DirFileCache(dir);
        boolean check = true;
        while (check) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(bufferedReader.readLine());
            if (LOAD_CACHE == userChoice) {
                System.out.println(FILE);
                String file = bufferedReader.readLine();
                if (!Files.exists(Paths.get(String.format("%s%s", dir, file)))) {
                    throw new IllegalArgumentException(String.format("File %s doesn't exist in this directory.", file));
                }
                dirFileCache.put(file, dirFileCache.get(file));
                System.out.println("Файл успешно кэширован." + System.lineSeparator());
            } else if (SHOW_CACHE == userChoice) {
                System.out.println(RESULT);
                String key = bufferedReader.readLine();
                System.out.println(dirFileCache.get(key));
            } else {
                check = false;
                System.out.println(EXIT);
            }
        }
    }
}