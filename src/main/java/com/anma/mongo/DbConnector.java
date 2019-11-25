package com.anma.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

public class DbConnector {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public DbConnector(String db, String coll) {
        this.mongoClient = MongoClients.create();
        this.database = mongoClient.getDatabase(db);
        this.collection = database.getCollection(coll);
    }

    public void addPerson(String fName, String lname, String age) {
        Document document = new Document().append("First Name", fName)
                .append("Last Name", lname)
                .append("Age", age);
        getCollection().insertOne(document);

    }


    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }
}
