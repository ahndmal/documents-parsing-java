package com.anma.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DocCreationService {

    void createDocument(String body) throws URISyntaxException, IOException, InvalidFormatException;
}
