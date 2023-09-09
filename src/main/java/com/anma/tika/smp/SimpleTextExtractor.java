package com.anma.tika.smp;

import java.io.File;

import org.apache.tika.Tika;

public class SimpleTextExtractor {
    public static void main(String[] args) throws Exception {
        Tika tika = new Tika();

//      Parse all given files and print out the extracted text content
        for (String file : args) {
            String text = tika.parseToString(new File(file));
            System.out.print(text);
        }
    }
}
