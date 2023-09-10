package com.anma.tika;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

public class DisplayMetInstance {
    public static Metadata getMeta(URL url) throws IOException, SAXException, TikaException {
        Metadata met = new Metadata();
        PDFParser parser = new PDFParser();
        parser.parse(url.openStream(), new BodyContentHandler(), met, new ParseContext());
        return met;
    }

    public static void main(String[] args) throws Exception {
        Metadata met = DisplayMetInstance.getMeta(new URL("https://example.com"));
        System.out.println(met);
    }

    /*
    public void indexWithDublinCore(File file) throws Exception {
        Metadata met = new Metadata();
        met.add(Metadata.CREATOR, "Manning");
        met.add(Metadata.CREATOR, "Tika in Action");
        met.set(Metadata.DATE, new Date());
        met.set(Metadata.FORMAT, tika.detect(file));
        met.set(DublinCore.SOURCE, file.toURL().toString());
        met.add(Metadata.SUBJECT, "File");
        met.add(Metadata.SUBJECT, "Indexing");
        met.add(Metadata.SUBJECT, "Metadata");
        met.set(Property.externalClosedChoise(Metadata.RIGHTS,
                "public", "private"), "public");
        InputStream is = new FileInputStream(file);
        tika.parse(is, met);
        try {
            Document document = new Document();
            for (String key : met.names()) {
                String[] values = met.getValues(key);
                for (String val : values) {
                    document.add(new Field(key, val, Store.YES, Index.ANALYZED));
                }
                writer.addDocument(document);
            }
        } finally {
            is.close();
        }
    }
    */

   /*
   public String generateRSS(File indexFile) throws CorruptIndexException,
            IOException {
       // Extending the Lucene indexer with content-specific metadata
        StringBuffer output = new StringBuffer();
//        output.append(getRSSHeaders());
        try {
            reader = IndexReader.open(new SimpleFSDirectory(indexFile));
            IndexSearcher searcher = new IndexSearcher(reader);
            GregorianCalendar gc = new java.util.GregorianCalendar();
            gc.setTime(new Date());
            String nowDateTime = ISO8601.format(gc);
            gc.add(java.util.GregorianCalendar.MINUTE, -5);
            String fiveMinsAgo = ISO8601.format(gc);
            TermRangeQuery query = new TermRangeQuery(Metadata.DATE.toString(),
                    fiveMinsAgo, nowDateTime, true, true);
            TopScoreDocCollector collector =
                    TopScoreDocCollector.create(20, true);
            searcher.search(query, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                Document doc = searcher.doc(hits[i].doc);
                output.append(getRSSItem(doc));
            }
        } finally {
            reader.close();
        }
        output.append(getRSSFooters());
        return output.toString();
    }*/
}
