package com.anma.tika;

import org.apache.tika.parser.Parser;
import org.apache.tika.parser.image.AbstractImageParser;
import org.apache.tika.parser.image.ImageParser;
import org.junit.jupiter.api.Test;

public class TIkaCoreTests {

    @Test
    void parserCore() {
        Parser parser = new ImageParser();
    }
}
