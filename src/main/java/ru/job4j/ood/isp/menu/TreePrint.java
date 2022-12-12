package ru.job4j.ood.isp.menu;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreePrint implements MenuPrinter {

    public static final String DEC = "----";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            int count = item.getNumber().split("\\.").length - 1;
            System.out.println(DEC.repeat(count)
                    + item.getNumber()
                    + item.getName());
        }
    }
}