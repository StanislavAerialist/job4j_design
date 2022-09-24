package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean check = true;
        byte count = 12;
        short num = 56;
        char c = 'c';
        float f = 1.04F;
        double d = 12.13D;
        LOG.debug("User info name : {}, age : {}, check : {}, count : {}, num : {}, c : {}, f : {}, d : {}", name, age, check, count, num, c, f, d);
    }
}