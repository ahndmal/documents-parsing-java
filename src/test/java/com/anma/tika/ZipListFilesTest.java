package com.anma.tika;

import java.io.IOException;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.*;

class ZipListFilesTest {


    @org.junit.jupiter.api.Test
    void listZipEntries() throws IOException {

        try (ZipFile zip = new ZipFile("path")) {
            for (ZipEntry entry : Collections.list(zip.entries())) {
                System.out.println(entry.getName());
            }
        }
    }
}