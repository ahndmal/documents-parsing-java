package com.anma;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helper {
    public static String convertTextFileToString(String fileName) {

        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {
            return stream.collect(Collectors.joining(" "));

        } catch (IOException | URISyntaxException e) {
            return null;
        }
    }
}
