package com.anma.tika;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;

import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.language.ProfilingHandler;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.DelegatingParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.TeeContentHandler;

public class LanguagesIdentify {
    void identify() {
        LanguageProfile profile = new LanguageProfile(
                "Alla människor är födda fria och" + " lika i värde och rättigheter.");
        LanguageIdentifier identifier = new LanguageIdentifier(profile);
        System.out.println(identifier.getLanguage());

        ProfilingWriter writer = new ProfilingWriter();
        writer.append("Minden emberi lény");
        writer.append(" szabadon születik és");
        writer.append(" egyenl? méltósága és");
        writer.append(" joga van.");
        LanguageIdentifier identifier =
                writer.getLanguage();
        System.out.println(identifier.getLanguage());


        ProfilingHandler handler = new ProfilingHandler();
        new AutoDetectParser().parse(System.in, handler, new Metadata(), new ParseContext());
        LanguageIdentifier identifier = handler.getLanguage();
        System.out.println(identifier.getLanguage());
    }
}
