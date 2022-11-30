package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
@Disabled
class ReportXMLTest {
    @Test
    public void whenXMLReport() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = dateFormat.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(String.format("%n%s%n", "<Employees>"))
                .append(String.format("    %s%n", "<employees>"))
                .append(String.format("        %s%n", "<employee>"))
                .append(String.format("            <fired>%s</fired>%n", date))
                .append(String.format("            <hired>%s</hired>%n", date))
                .append(String.format("            <name>%s</name>%n", worker.getName()))
                .append(String.format("            <salary>%s</salary>%n", worker.getSalary()))
                .append(String.format("        %s%n", "</employee>"))
                .append(String.format("    %s%n", "</employees>"))
                .append(String.format("%s%n", "</Employees>"));
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}