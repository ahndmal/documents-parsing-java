package com.anma.tika.zip;

import java.io.IOException;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTika {
    public static void listZipEntries(String path) throws IOException {
        ZipFile zip = new ZipFile(path);
        for (ZipEntry entry : Collections.list(zip.entries())) {
            System.out.println(entry.getName());
        }
    }
}


