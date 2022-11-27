package ru.job4j.srp;

public class Logic {
        public void output(Model model) {
            System.out.println(model.getTop());
        }
        public int cacl(Model model) {
            return model.getTop() + 1;
        }
}
