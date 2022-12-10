package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream(32);
    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddAndPrintThenPrint() {
        System.setOut(new PrintStream(output));
        var print = new TreePrint();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add(Menu.ROOT, "Убраться дома", STUB_ACTION);
        menu.add("Убраться дома", "Пропылесосить", STUB_ACTION);
        menu.add("Убраться дома", "Помыть полы", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        String expected = """
                ----1.Сходить в магазин
                --------1.1.Купить продукты
                ------------1.1.1.Купить хлеб
                ------------1.1.2.Купить молоко
                ----2.Покормить собаку
                ----3.Убраться дома
                --------3.1.Пропылесосить
                --------3.2.Помыть полы""".indent(0);
        print.print(menu);
        Assertions.assertEquals(expected, output.toString());
        System.setOut(null);
    }

    @Test
    public void whenAddEqualsSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Spider man", STUB_ACTION);
        menu.add("Spider man", "Spider", STUB_ACTION);
        menu.add("Spider man", "Man", STUB_ACTION);
        menu.add(Menu.ROOT, "Iron man", STUB_ACTION);
        menu.add("Iron man", "Iron", STUB_ACTION);
        menu.add("Iron man", "Man", STUB_ACTION);
        menu.add(Menu.ROOT, "Batman", STUB_ACTION);
        menu.add("Batman", "Bat", STUB_ACTION);
        menu.add("Batman", "Man", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Spider man",
                List.of("Spider", "Man"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Spider man").get());
        assertThat(new Menu.MenuItemInfo("Batman",
                List.of("Bat", "Man"), STUB_ACTION, "3."))
                .isEqualTo(menu.select("Batman").get());
        assertThat(new Menu.MenuItemInfo("Iron man",
                List.of("Iron", "Man"), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Iron man").get());
    }
}
