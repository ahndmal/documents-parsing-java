package com.anma;

import com.anma.mongo.DBConnectorOld;
import com.anma.mongo.DbConnector;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public Main() throws UnknownHostException {
    }

    public static void main(String[] args) throws UnknownHostException {

        DbConnector connector = new DbConnector("persons","persons");

        FindIterable<Document> documents = connector.getCollection().find();

        for (Document document : documents) {
            System.out.println(document.toJson());
        }
        System.out.println(connector.getCollection().find().first().toJson());

        Document document = connector.getCollection().find(eq("firstName", "Ignat")).first();

        System.out.println(document.toJson());

    }




}
