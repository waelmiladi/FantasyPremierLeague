package com.waheed.client;

import com.google.inject.Inject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDbClient {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> roundsCollection;
    private MongoCollection<Document> teamsCollection;

    @Inject
    public MongoDbClient(MongoClient client) {
        this.client = client;
        this.database = client.getDatabase("premier-league");
        roundsCollection = database.getCollection("rounds");
        teamsCollection = database.getCollection("teams");
    }

    public MongoCollection<Document> getRoundsCollection() {
        return this.roundsCollection;
    }

    public MongoCollection<Document> getTeamsCollection() {
        return this.teamsCollection;
    }
}
