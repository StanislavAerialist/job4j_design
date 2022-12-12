package ru.job4j.ood.dip;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringProcessor {

    private final StringReader stringReader;
    private final StringWriter stringWriter;

    public StringProcessor(StringReader stringReader, StringWriter stringWriter) {
        this.stringReader = stringReader;
        this.stringWriter = stringWriter;
    }
    public void printString() throws IOException {
        stringWriter.write(stringReader.read());
    }
}