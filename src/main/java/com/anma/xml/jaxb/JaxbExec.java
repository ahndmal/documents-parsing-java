package com.anma.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbExec {

    public static void main(String[] args) {

        Catalog catalog = new Catalog();

        catalog.add(new Book("vasyl", "Title", 3));
        catalog.add(new Book("Ignat", "Title2", 6));

        try {

            File file = new File("D:\\Programming\\java\\projects\\documents-parsing\\src\\resources\\xml\\jaxb_books.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalog, file);
            marshaller.marshal(catalog, System.out);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            catalog = (Catalog) unmarshaller.unmarshal(file);

            System.out.println(catalog);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
