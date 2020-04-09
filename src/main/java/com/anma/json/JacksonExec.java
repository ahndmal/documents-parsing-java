package com.anma.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JacksonExec {

    private final static String path = "D:\\Programming\\java\\projects\\documents-parsing\\src\\resources\\json\\cats.json";

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        Cat[] cats = null;

        try {

            cats =  objectMapper.readValue(new File(path), Cat[].class);

            for (Cat cat : cats ) {
                System.out.println(cat.getName());
                System.out.println(cat.getAge());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
