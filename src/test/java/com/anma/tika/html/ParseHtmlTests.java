package com.anma.tika.html;

import org.apache.tika.Tika;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

public class ParseHtmlTests {
    @Test
    void htmlParsing() throws IOException {
        Tika tika = new Tika();

        var url = new URL("https://learn.microsoft.com/en-us/training/browse/");

        String detected = tika.detect(url);
        System.out.println(detected);

        Reader parsed = tika.parse(url);

        BufferedReader bufferedReader = new BufferedReader(parsed);

        while (bufferedReader.ready()) {
            System.out.println(bufferedReader.readLine());
        }
    }
}
