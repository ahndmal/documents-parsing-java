package com.anma.tika.pdf;

import java.io.FileNotFoundException;

public interface TikaService {

    String convertToText(String doc) throws FileNotFoundException;

    String convertToHTML(String doc);

    String getMetadata(String doc);
}
