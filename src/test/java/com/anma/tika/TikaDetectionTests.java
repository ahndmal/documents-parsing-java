package com.anma.tika;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TemporaryResources;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TikaDetectionTests {
    File[] myListOfFiles = {new File("src/resources/sample.jpg")};
    final String IMAGE_PATH = "src/resources/sample.jpg";


    @Test
    void detect() throws IOException {
        Tika tika = new Tika();

        File file = new File(IMAGE_PATH);

        String type = tika.detect(file);

        System.out.println(file + ": " + type);
    }

    @Test
    void detectOne() throws IOException {
        InputStream stream = Files.newInputStream(Path.of(IMAGE_PATH));

        Detector detector = new DefaultDetector();

//        TemporaryResources tmp = new TemporaryResources();
//        TikaInputStream tis = TikaInputStream.get(stream, tmp, metadata);
//        this.maybeSpool(tis, this.autoDetectParserConfig, metadata);
//        MediaType type = this.detector.detect(tis, metadata);

        detector.detect(stream, new Metadata());

    }

    @Test
    void detectTwo() throws TikaException, IOException {
        TikaConfig tika = new TikaConfig();

        for (File file : myListOfFiles) {
            Metadata metadata = new Metadata();
            //TikaInputStream sets the TikaCoreProperties.RESOURCE_NAME_KEY
            //when initialized with a file or path

            MediaType mimetype = tika.getDetector().detect(TikaInputStream.get(file, metadata), metadata);
            System.out.println("File " + file + " is " + mimetype);
        }

        InputStream[] streams = {new FileInputStream("src/resources/sample.jpg")};

        for (InputStream is : streams) {
            Metadata metadata = new Metadata();
            //if you know the file name, it is a good idea to
            //set it in the metadata, e.g.
            //metadata.set(TikaCoreProperties.RESOURCE_NAME_KEY, "somefile.pdf");
            String mimetype = tika.getDetector().detect(TikaInputStream.get(is), metadata).toString();
            System.out.println("Stream " + is + " is " + mimetype);
        }
    }
}
