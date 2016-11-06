package com.waheed;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.waheed.client.FantasyPremierLeagueClient;
import com.waheed.client.HttpClient;
import com.waheed.models.Round;
import com.waheed.module.FantasyPremierLeagueScriptsModule;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Scripts {
    private static Injector injector;
    private static HttpClient httpClient;
    private static FantasyPremierLeagueClient fplClient;
    private static MongoDatabase database;

    public static void main(String[] args) throws IOException, ParseException {
        injector = Guice.createInjector(new FantasyPremierLeagueScriptsModule());

        httpClient = injector.getInstance(HttpClient.class);
        fplClient = injector.getInstance(FantasyPremierLeagueClient.class);
        MongoClient mongoClient = injector.getInstance(MongoClient.class);
        database = mongoClient.getDatabase("premier-league");


        //indexRounds();
        indexTeams();
    }

    private static void indexRounds() throws IOException, ParseException {
        JSONObject response = new JSONObject(httpClient.get(Constants.roundsUrl));
        JSONObject data = response.getJSONObject("data");
        JSONArray rounds = data.getJSONArray("rounds");

        MongoCollection<Document> collection = database.getCollection("rounds");
        collection.deleteMany(new BasicDBObject());

        List<Document> docs = new ArrayList<>();

        System.out.println(rounds);
        for (int i=0; i < rounds.length(); i++) {
            JSONObject round = rounds.getJSONObject(i);

            Document doc = new Document("name", "Round")
                    .append("slug", round.getString("round_slug").replace("giornata", "round"))
                    .append("name", round.getString("name").replace("Giornata", "Round"))
                    .append("startDate", convertStringToDate(round.getString("start_date")))
                    .append("endDate", convertStringToDate(round.getString("end_date")));

            System.out.println(doc);

            docs.add(doc);
        }

        collection.insertMany(docs);
    }

    private static void indexTeams() throws IOException, ParseException {
        Round round = fplClient.getRound("581e2d2cf88d57a6a6c7661a");
        JSONObject response = new JSONObject(httpClient.get(Constants.standingsUrl));
        JSONObject data = response.getJSONObject("data");
        JSONArray standings = data.getJSONArray("standings");

        MongoCollection<Document> collection = database.getCollection("teams");
        collection.deleteMany(new BasicDBObject());

        List<Document> docs = new ArrayList<>();

        System.out.println(standings);
        for (int i=0; i < standings.length(); i++) {
            JSONObject standing = standings.getJSONObject(i);

            List<Document> positions = new ArrayList<>();
            List<Document> overallStats = new ArrayList<>();
            List<Document> homeStats = new ArrayList<>();
            List<Document> awayStats = new ArrayList<>();

            Document positionDoc = new Document();
            Document overallStatsDoc = Document.parse(standing.getJSONObject("overall").toString());
            Document homeStatsDoc = Document.parse(standing.getJSONObject("home").toString());
            Document awayStatsDoc = Document.parse(standing.getJSONObject("away").toString());


            positionDoc.put("position", standing.getInt("position"));

            positionDoc.put("roundId", new ObjectId(round.getId()));
            overallStatsDoc.put("roundId", new ObjectId(round.getId()));
            homeStatsDoc.put("roundId", new ObjectId(round.getId()));
            awayStatsDoc.put("roundId", new ObjectId(round.getId()));

            positionDoc.put("roundStartDate", round.getStartDate());
            overallStatsDoc.put("roundStartDate", round.getStartDate());
            homeStatsDoc.put("roundStartDate", round.getStartDate());
            awayStatsDoc.put("roundStartDate", round.getStartDate());

            positionDoc.put("roundEndDate", round.getEndDate());
            overallStatsDoc.put("roundEndDate", round.getEndDate());
            homeStatsDoc.put("roundEndDate", round.getEndDate());
            awayStatsDoc.put("roundEndDate", round.getEndDate());

            positions.add(positionDoc);
            overallStats.add(overallStatsDoc);
            homeStats.add(homeStatsDoc);
            awayStats.add(awayStatsDoc);

            Document document = new Document("name", "Round");

            document.put("name", standing.getString("team"));
            document.put("positions", positions);
            document.put("overallStats", overallStats);
            document.put("homeStats", homeStats);
            document.put("awayStats", awayStats);

            docs.add(document);
        }
        collection.insertMany(docs);
    }

    private static Date convertStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            return formatter.parse(dateString.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
