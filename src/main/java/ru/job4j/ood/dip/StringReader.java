package ru.job4j.ood.dip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReader {
    public String read() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
