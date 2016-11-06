package com.waheed.client;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.waheed.models.Round;
import com.waheed.models.Team;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class FantasyPremierLeagueClient {
    public static final int roundsSize = 38;
    public static final int currentRoundSize = 1;
    public static final int leagueSize = 20;

    private MongoDbClient mongoClient;
    private MongoCollection<Document> roundsCollection;
    private MongoCollection<Document> teamsCollection;

    @Inject
    public FantasyPremierLeagueClient(MongoDbClient mongoClient) {
        this.mongoClient = mongoClient;

        this.roundsCollection = mongoClient.getRoundsCollection();
        this.teamsCollection = mongoClient.getTeamsCollection();
    }

    public List<Round> getRounds() throws IOException {
        FindIterable<Document> roundDocuments = roundsCollection.find();
        roundDocuments.sort(new BasicDBObject("startDate", 1));

        List<Round> rounds = new ArrayList<>();
        roundDocuments.forEach((Block<Document>) document -> {
            rounds.add(Round.fromDocument(document));
        });
        return rounds;
    }

    public Round getRound(String id) throws IOException {
        Document currentRoundDocument = roundsCollection
                .find(new BasicDBObject("_id", new ObjectId(id)))
                .first();
        return Round.fromDocument(currentRoundDocument);
    }

    public Round getCurrentRound() throws IOException {
        Date now = new Date();
        Document currentRoundDocument = roundsCollection
                .find(and(lte("startDate", now), gte("endDate", now)))
                .first();

        return Round.fromDocument(currentRoundDocument);
    }

    public List<Team> getTeams() throws IOException {
        FindIterable<Document> teamDocuments = teamsCollection.find();

        List<Team> teams = new ArrayList<>();
        teamDocuments.forEach((Block<Document>) document -> {
            teams.add(Team.fromDocument(document));
        });
        return teams;
    }

    public Team getTeam(String id) throws IOException {
        Document teamDocument = roundsCollection
                .find(new BasicDBObject("_id", new ObjectId(id)))
                .first();
        return Team.fromDocument(teamDocument);
    }
}
