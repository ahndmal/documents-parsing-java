package com.anma;

import com.anma.services.CreateDocument;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        CreateDocument.createDocument();

    }
}
