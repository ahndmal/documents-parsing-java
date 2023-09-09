package com.anma.tika.smp;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTextExtractorTest {

    @Test
    void simpeFilesParsing() throws TikaException, IOException {
        Tika tika = new Tika();

        String[] files = {"src/resources/test-1.html"};

        for (String file : files) {
            String text = tika.parseToString(new File(file));
            System.out.print(text);
        }
    }
}