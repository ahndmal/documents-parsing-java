package com.anma.tika;

import org.apache.tika.exception.TikaException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LanguagesIdentifyTest {

    @Test
    void identify() throws TikaException, IOException, SAXException {

        LanguagesIdentify.identify();

    }
}