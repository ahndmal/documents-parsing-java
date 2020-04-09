package com.anma.xml.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class Book {

    private String author;
    private String title;
    private int price;

    public Book() {
    }

    public Book(String author, String title, int price) {
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(int price) {
        this.price = price;
    }
}
