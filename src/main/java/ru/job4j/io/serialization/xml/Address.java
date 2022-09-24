package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

    @XmlAttribute
    private String home;
    public Address() {
    }
    public Address(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Address{"
                + "home='" + home + '\''
                + '}';
    }
}