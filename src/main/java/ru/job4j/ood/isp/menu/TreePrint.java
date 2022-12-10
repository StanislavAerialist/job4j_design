package ru.job4j.ood.isp.menu;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreePrint implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo next = iterator.next();
            StringBuilder s = new StringBuilder();
            Pattern p = Pattern.compile("\\.");
            Matcher m = p.matcher(next.getNumber());
            while (m.find()) {
                s.append("----");
            }
            System.out.printf(s + next.getNumber() + next.getName() + "\n");
        }
    }
}