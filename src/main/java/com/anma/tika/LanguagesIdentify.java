package com.anma.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageHandler;
import org.apache.tika.language.detect.LanguageResult;
import org.apache.tika.language.detect.LanguageWriter;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.xml.sax.SAXException;

import java.io.IOException;

public class LanguagesIdentify {

    public static void identify() throws IOException, TikaException, SAXException {

        String text = "Alla människor är födda fria och" + " lika i värde och rättigheter.";

        LanguageDetector.getLanguageDetectors().forEach(System.out::println);

        LanguageDetector detector = LanguageDetector.getDefaultLanguageDetector();

        detector.addText(text);

//        LanguageIdentifier identifier = new LanguageIdentifier(profile);

        System.out.println(detector);

        LanguageWriter writer = new LanguageWriter(detector);

        writer.append("Minden emberi lény");
        writer.append(" szabadon születik és");
        writer.append(" egyenl? méltósága és");
        writer.append(" joga van.");

        LanguageResult writerLanguage = writer.getLanguage();

        System.out.println(writerLanguage.getLanguage());


        LanguageHandler languageHandler = new LanguageHandler(writer);

//        ProfilingHandler handler = new ProfilingHandler();

        new AutoDetectParser().parse(System.in, languageHandler, new Metadata(), new ParseContext());

        LanguageResult handlerLanguage = languageHandler.getLanguage();

        System.out.println(handlerLanguage.getLanguage());
    }
}
