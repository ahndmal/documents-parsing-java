package com.anma.tika.pdf;

import org.apache.tika.exception.TikaException;
import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.PDF;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlMapper;
import org.apache.tika.parser.html.IdentityHtmlMapper;
import org.apache.tika.parser.image.ImageParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.apache.tika.sax.xpath.Matcher;
import org.apache.tika.sax.xpath.MatchingContentHandler;
import org.apache.tika.sax.xpath.XPathParser;
import org.junit.jupiter.api.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

class TikaPdfParsingTest {
    private static final String PDF_FILE = "/home/andrii/Documents/pdf/Site_FS.pdf";

    @Test
    void ExtractImages() {
        String xhtmlContents = "";
        AutoDetectParser parser = new AutoDetectParser();
        ContentHandler handler = new ToXMLContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        EmbeddedDocumentExtractor ede = new EmbeddedDocumentExtractor() {
            @Override
            public boolean shouldParseEmbedded(Metadata metadata) {
                return true;
            }

            @Override
            public void parseEmbedded(InputStream stream,
                                      ContentHandler handler,
                                      Metadata metadata,
                                      boolean outputHtml) throws IOException {
                Path outputDir = Path.of("src/resources/images");
                Files.createDirectories(outputDir);

                System.out.println(">>> metadata");
                System.out.println(metadata.toString());

//                Path outputPath = new File(outputDir.toString() + "/" + metadata.get(Metadata.RESOURCE_NAME_KEY)).toPath();
                Path outputPath = Path.of(outputDir + "/" + metadata.get("resourceName"));
                Files.deleteIfExists(outputPath);
                Files.copy(stream, outputPath);
            }
        };

        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);
        pdfConfig.setExtractUniqueInlineImagesOnly(true);

        context.set(PDFParserConfig.class, pdfConfig);
        context.set(EmbeddedDocumentExtractor.class, ede);
        context.set(AutoDetectParser.class, parser);

        try (InputStream stream = new FileInputStream(PDF_FILE)) {
            parser.parse(stream, handler, metadata, context);
            xhtmlContents = handler.toString();
        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }

        System.out.println(xhtmlContents);
    }

    @Test
    void parsePdf() throws IOException, TikaException, SAXException {
        PDFParser pdfParser = new PDFParser();
        AutoDetectParser parser = new AutoDetectParser();

        var is = TikaInputStream.get(Path.of(PDF_FILE));

//        BodyContentHandler handler = new BodyContentHandler();
        var handler = new ToHTMLContentHandler();
        var xhtmlContentHandler = new XHTMLContentHandler(handler, new Metadata());
//        ContentHandler handler = new ToXMLContentHandler();

        ParseContext context = new ParseContext();
        context.set(HtmlMapper.class, new IdentityHtmlMapper());

        parser.parse(is, xhtmlContentHandler, new Metadata(), context);

        System.out.println(xhtmlContentHandler);
    }

    @Test
    void imageParse() throws TikaException, IOException, SAXException {
        ImageParser imageParser = new ImageParser();
        var handler = new ToHTMLContentHandler();
        var is = TikaInputStream.get(Path.of(PDF_FILE));

        imageParser.parse(is, handler, new Metadata(), new ParseContext());
    }

    @Test
    void parseOnePartToHTML() throws IOException, SAXException, TikaException {
        // Only get things under html -> body -> div (class=header)
        XPathParser xhtmlParser = new XPathParser("xhtml", XHTMLContentHandler.XHTML);
        Matcher divContentMatcher = xhtmlParser.parse("/xhtml:html/xhtml:body/xhtml:div/descendant::node()");
        ContentHandler handler = new MatchingContentHandler(
                new ToXMLContentHandler(), divContentMatcher);

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try (InputStream stream = TikaInputStream.get(Path.of(PDF_FILE))) {
            parser.parse(stream, handler, metadata);
            System.out.println(handler.toString());
        }
    }
}