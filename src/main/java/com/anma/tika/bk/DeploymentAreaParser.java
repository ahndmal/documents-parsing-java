package com.anma.tika.bk;

import org.apache.commons.io.IOUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.FileUtils.isSymlink;

public class DeploymentAreaParser {
    public void parse(InputStream is, ContentHandler handler,
                      Metadata metadata, ParseContext context) throws IOException, SAXException, TikaException {
        File deployArea = new File(IOUtils.toString(is));
        File[] versions = deployArea.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return !pathname.getName().startsWith("current");
            }
        });
        XHTMLContentHandler xhtml = new XHTMLContentHandler(handler, metadata);
        xhtml.startDocument();
        for (File v : versions) {
            if (isSymlink(v)) continue;
            xhtml.startElement("a", "href", v.toURL().toExternalForm());
            xhtml.characters(v.getName());
            xhtml.endElement("a");
        }
    }
}
