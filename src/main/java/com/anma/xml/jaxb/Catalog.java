package com.anma.xml.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "catalog")
public class Catalog {

    @XmlElement(name = "book")
    private List<Book> books = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }

    public Catalog() {
    }

}
