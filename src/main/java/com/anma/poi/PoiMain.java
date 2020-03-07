package com.anma.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.net.URISyntaxException;

public class PoiMain {

    public static void main(String[] args) {

        DocCreationServiceImpl docCreationService = new DocCreationServiceImpl();

        try {

            docCreationService.createDocument("Some body");
            System.out.println("Document created!");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }


}

