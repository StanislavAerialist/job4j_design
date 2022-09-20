package ru.job4j.io;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analizy {
    public void unavailable(String source, String target) {

        try (BufferedReader read = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            final int[] check = {1};
            read.lines()
                    .forEach(line -> {
                        if (check[0] == 1 && (line.startsWith("400") || (line.startsWith("500")))) {
                            check[0] = 0;
                            out.print(line.split(" ")[1] + ";");
                        } else if (check[0] == 0 && (line.startsWith("200") || (line.startsWith("300")))) {
                            check[0] = 1;
                            out.println(line.split(" ")[1]);
                        }
                    });
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}