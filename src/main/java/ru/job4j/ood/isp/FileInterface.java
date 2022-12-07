package ru.job4j.ood.isp;

public interface FileInterface {

    void rename(String oldName, String newName);
    void changeOwner(String user, String group);
}
