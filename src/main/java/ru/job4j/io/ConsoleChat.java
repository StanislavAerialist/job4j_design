package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> dialog = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> answers = readPhrases();
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        boolean check = true;
        String s = "";
        System.out.println("Начнем общение");
        while (!OUT.equals(s)) {
            s = rd.readLine();
            dialog.add(s);
            if (STOP.equals(s)) {
                check = false;
            }
            if (check) {
                String botAnswer = answers.get((int) (Math.random() * answers.size()));
                System.out.println(botAnswer);
                dialog.add(botAnswer);
            }
            if (CONTINUE.equals(s)) {
                check = true;
            }
        }
        saveLog(dialog);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            rd.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("c:\\test\\log.txt", "c:\\test\\answers.txt");
        cc.run();
    }
}