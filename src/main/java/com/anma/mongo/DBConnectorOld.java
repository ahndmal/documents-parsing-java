package com.anma.mongo;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBConnectorOld {



    public MongoClient getMongoClient() throws UnknownHostException {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        return mongoClient;
    }

    public DB getMongoDB(String databaseName) throws UnknownHostException {

        DB database = new DBConnectorOld().getMongoClient().getDB(databaseName);

        return database;
    }

    public DBCollection getCollection(String dbName, String collectionName) throws UnknownHostException {

//        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

       return getMongoDB(dbName).getCollection(collectionName);

    }

    public List findObjectByAge(String age, String db, String coll) throws UnknownHostException {

        BasicDBObject searchQuery = new BasicDBObject();

        List objects = new ArrayList();

        searchQuery.put("Age", age);

        DBCursor cursor = new DBConnectorOld().getCollection(db, coll).find(searchQuery);

        while (cursor.hasNext()) {

            objects.add(cursor.next());
        }

        return objects;
    }

    public List getCollectionItems(String dbName, String collectionName) throws UnknownHostException {

        return new DBConnectorOld().getCollection(dbName, collectionName).find().toArray();
    }

    public void addObject(Map<String, String> map, String db, String coll) throws UnknownHostException {

        BasicDBObject object = new BasicDBObject();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            object.put(entry.getKey(), entry.getValue());
        }

        getCollection(db, coll).insert(object);
    }


}
