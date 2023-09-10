package com.anma.tika.smp;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ParserDecorator;
import org.apache.tika.parser.html.HtmlMapper;
import org.apache.tika.parser.html.IdentityHtmlMapper;
import org.junit.jupiter.api.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

public class ParseContenxtTests {
    @Test
    void context() {
        ParseContext context = new ParseContext();
        context.set(HtmlMapper.class, new IdentityHtmlMapper());
//        parser.parse(stream, handler, metadata, context);

        //  Sometimes you’d rather process the component documents completely separately
        context.set(Parser.class, new ParserDecorator(parser) {
            @Override
            public void parse(
                    InputStream stream, ContentHandler handler,
                    Metadata metadata, ParseContext context)
                    throws IOException, SAXException, TikaException {
                // custom processing of the component document
            }
        });
//        parser.parse(stream, handler, metadata, context);
    }
}
