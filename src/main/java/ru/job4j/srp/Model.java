package ru.job4j.srp;

public class Model {
    private int top;

    public Model(int top) {
            this.top = top;
    }
    public int getTop() {
        return top;
    }
    public int cacl(int top) {
            return top + 1;
    }
}
