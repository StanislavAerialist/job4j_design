package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        HashMap<Integer, String> mapOld = new HashMap<>();
        HashMap<Integer, String> mapNew = new HashMap<>();
        for (User u : previous) {
            mapOld.put(u.getId(), u.getName());
        }
        for (User u : current) {
            mapNew.put(u.getId(), u.getName());
        }
        for (Map.Entry<Integer, String> m : mapOld.entrySet()) {
            Integer k = m.getKey();
            String v = m.getValue();
            if (mapNew.containsKey(k)) {
                int changeC = mapNew.get(k).equals(v) ? 0 : 1;
                rsl.setChanged(rsl.getChanged() + changeC);
            } else {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        }
        rsl.setAdded(current.size() - previous.size() + rsl.getDeleted());
        return rsl;
    }

}