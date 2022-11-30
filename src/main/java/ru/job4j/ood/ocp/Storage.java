package ru.job4j.ood.ocp;
import ru.job4j.ood.srp.model.Employee;
import java.util.ArrayList;
public class Storage {
    private ArrayList<Employee> store;
    public Storage() {
    }
    public void save(Employee e) {
        store.add(e);
    }
}