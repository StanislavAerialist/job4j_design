package ru.job4j.io.serialization.xml;

import ru.job4j.serialization.json.Address;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "candidate")
@XmlAccessorType(XmlAccessType.FIELD)
public class Candidate {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean serveInArmy;
    @XmlAttribute
    private int age;
    private Address address;
    @XmlElementWrapper(name = "relatives")
    @XmlElement(name = "relative")
    private String[] relatives;

    public Candidate() {
    }

    public Candidate(String name, boolean serveInArmy, int age, Address address, String[] relatives) {
        this.name = name;
        this.serveInArmy = serveInArmy;
        this.age = age;
        this.address = address;
        this.relatives = relatives;
    }

    public static void main(String[] args) throws JAXBException {
        final Candidate candidate = new Candidate("Ivan", true, 25, new Address("Moscow"),
                new String[] {"Father", "Mother"});

        JAXBContext context = JAXBContext.newInstance(Candidate.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(candidate, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
