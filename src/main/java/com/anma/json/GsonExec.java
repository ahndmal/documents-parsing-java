package com.anma.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URL;

public class GsonExec {

    private final static String jsonUrl = "https://api.privatbank.ua/p24api/exchange_rates?json&date=01.04.2020";
    private final static String path = "D:\\Programming\\java\\projects\\documents-parsing\\src\\resources\\json\\cats.json";

    public static void main(String[] args) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        try {

            JsonReader jsonReader = new JsonReader(new InputStreamReader(new URL(jsonUrl).openStream()));
            Currency currency = gson.fromJson(jsonReader, Currency.class);
            System.out.println(currency.getBank());

            JsonReader jsonReader2 = new JsonReader(new FileReader(new File(path)));
            Cat[] cats = gson.fromJson(jsonReader2, Cat[].class);
            for (Cat cat: cats) {
                System.out.println(cat.getName());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
