//package com.anma.lucene;
//
//import java.io.File;
//import java.io.Reader;
//
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.Field.Index;
//
//import org.apache.lucene.document.Field.Store;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.store.SimpleFSDirectory;
//import org.apache.tika.Tika;
//
//public class FullIndexTikaLucene {
//    private Tika tika;
//    private IndexWriter writer;
//
//    public void indexDocument(File file) throws Exception {
//        Document document = new Document();
//        document.add(new Field("filename", file.getName(), Store.YES, Index.ANALYZED));
//        document.add(new Field("fulltext", tika.parseToString(file), Store.NO, Index.ANALYZED));
//        writer.addDocument(document);
//
//        IndexWriter writer = new IndexWriter(
//                new SimpleFSDirectory(new File(args[0])),
//                new StandardAnalyzer(Version.LUCENE_30),
//                MaxFieldLength.UNLIMITED);
//        try {
//            FullIndexTikaLucene indexer = new FullIndexTikaLucene(new Tika(), writer);
//            for (int i = 1; i < args.length; i++) {
//                indexer.indexDocument(new File(args[i]));
//            }
//        } finally {
//            writer.close();
//        }
//    }
//
//    public void indexDocument(File file) throws Exception {
//        Reader fulltext = tika.parse(file);
//        try {
//            Document document = new Document();
//            document.add(new Field(
//                    "filename", file.getName(),
//                    Store.YES, Index.ANALYZED));
//            document.add(new Field("fulltext", fulltext));
//            writer.addDocument(document);
//        } finally {
//            fulltext.close();
//        }
//    }
//}
//
//
