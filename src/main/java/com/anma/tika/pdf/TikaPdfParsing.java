package com.anma.tika.pdf;

import org.apache.tika.exception.TikaException;
import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.ToXMLContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class TikaPdfParsing {

    private static final Logger LOG = LoggerFactory.getLogger(TikaPdfParsing.class);

    public static final String PDF = ".pdf";
    public static final String IMAGES = "/images";

    public static String extractImages(String pdfFile) throws IOException {

        String xhtmlContents = "";

        AutoDetectParser parser = new AutoDetectParser();
        ContentHandler handler = new ToXMLContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        Path imagesDir = Path.of(pdfFile.replace(PDF, "") + IMAGES);

        try {

            Files.createDirectories(imagesDir);

        } catch (IOException e) {
            LOG.error("[BHT DOCS] Error when creating output dir: " + imagesDir);
            LOG.error(e.getMessage());
        }

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

//                String imagesFilePath = String.format(SRC_RESOURCES_IMAGES, pdfFIle);

                // /tmp/files/ --> /tmp/files/Site_FS.pdf/images
                // /tmp/files/Site_FS.pdf/images

//                Path outputPath = new File(outputDir.toString() + "/" + metadata.get(Metadata.RESOURCE_NAME_KEY)).toPath();
                Path outputPath = Path.of(imagesDir + "/" + metadata.get("resourceName"));

//                Files.deleteIfExists(outputPath);

                Files.copy(stream, outputPath);

                // todo: replace images here ?
            }
        };

        // config
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);
        pdfConfig.setExtractUniqueInlineImagesOnly(true);
//        pdfConfig.setExtractMarkedContent(true);

        context.set(PDFParserConfig.class, pdfConfig);
        context.set(EmbeddedDocumentExtractor.class, ede);
        context.set(AutoDetectParser.class, parser);

        // parse
        try (InputStream stream = new FileInputStream(pdfFile)) {
            parser.parse(stream, handler, metadata, context);
            xhtmlContents = handler.toString();
        } catch (IOException e) {
            LOG.error("[BHT DOCS] IO exception: " + e.getMessage());
        } catch (SAXException | TikaException e) {
            LOG.error("[BHT DOCS] Error parsing the PDF: " + e.getMessage());
        }
        String pageTitle = pdfFile;

        // update page body with images as atatchments
        xhtmlContents = xhtmlContents
                .replaceAll("<div .+>", "")
                .replaceAll("</div>", "");
//                .replaceAll("<img.+>", ""); //todo: replace IMG with attachment


        // upload images from 'outputDir' folder
        Stream<Path> imagesStream = Files.list(imagesDir);

        return "";
    }
}
