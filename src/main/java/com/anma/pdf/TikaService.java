package com.anma.pdf;

public interface TikaService {

    String convertToText(String doc);

    String convertToHTML(String doc);
}
