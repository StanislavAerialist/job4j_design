package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static final int ADD = 1;

    public static final int SHOW = 2;

    public static final int FINISH = 3;

    public static final String MENU = """
            Введите 1, чтобы добавить в пункт меню.
            Введите 2, чтобы вывоести меню на экран.
            Введите 3, чтобы выйти из программы.
            """;

    public static final String SELECT = "Введите число";

    public static final String SELECTPARENT = "Введите пункт";

    public static final String SELECTCHILD = "Введите подпункт";

    public static final String WRONG = "Неправильно введены данные";

    public static final String EXIT = "Конец работы";

    private static void start(Menu menu, Scanner scanner, TreePrint print) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (ADD == userChoice) {
                System.out.println(SELECTPARENT);
                var addParent = scanner.nextLine();
                System.out.println(SELECTCHILD);
                var addChild = scanner.nextLine();
                if (addParent.isEmpty() && addChild.isEmpty()
                        || addParent.isEmpty() && !addChild.isEmpty()) {
                    System.out.println(WRONG);
                } else if (!addParent.isEmpty() && addChild.isEmpty()) {
                    menu.add(Menu.ROOT, addParent, STUB_ACTION);
                } else {
                    menu.add(addParent, addChild, STUB_ACTION);
                }
            } else if (SHOW == userChoice) {
                print.print(menu);
            } else if (FINISH == userChoice) {
                System.out.println(EXIT);
                run = false;
            }
        }
    }

    public static void main(String[] args) {
        TreePrint print = new TreePrint();
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        start(menu, scanner, print);
    }
}