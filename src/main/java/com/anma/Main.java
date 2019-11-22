package com.anma;

import com.anma.mongo.DBConnectorOld;
import com.anma.mongo.DbConnector;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args) throws UnknownHostException {

        DbConnector connector = new DbConnector("persons","persons");

        FindIterable<Document> documents = connector.getCollection().find();

        for (Document document : documents) {
            System.out.println("Document==" + document.toJson());
        }

//        MongoCursor<Document> cursor = collection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }

        System.out.println(connector.getCollection().find().first().toJson());

        Document document = connector.getCollection().find(eq("firstName", "Ignat")).first();

        System.out.println(document.toJson());

    }




}
