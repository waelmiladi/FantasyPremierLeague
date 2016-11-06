package com.waheed.models;

import graphql.annotations.GraphQLField;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String id;
    private String name;
    private List<Position> positions;
    private List<TeamStats> overallStats;
    private List<TeamStats> homeStats;
    private List<TeamStats> awayStats;

    public Team(
            String id,
            String name,
            List<Position> positions,
            List<TeamStats> overallStats,
            List<TeamStats> homeStats,
            List<TeamStats> awayStats) {
        this.id = id;
        this.name = name;
        this.positions = positions;
        this.overallStats = overallStats;
        this.homeStats = homeStats;
        this.awayStats = awayStats;
    }

    @GraphQLField
    public String getId() {
        return id;
    }

    @GraphQLField
    public String getName() {
        return name;
    }

    @GraphQLField
    public List<Position> getPositions() {
        return positions;
    }

    @GraphQLField
    public List<TeamStats> getOverallStats() {
        return overallStats;
    }

    @GraphQLField
    public List<TeamStats> getHomeStats() {
        return homeStats;
    }

    @GraphQLField
    public List<TeamStats> getAwayStats() {
        return awayStats;
    }

    public static Team fromDocument(Document document) {
        List<Document> positionDocuments = (List<Document>) document.get("positions");
        List<Document> overallStatsDocuments = (List<Document>) document.get("overallStats");
        List<Document> homeStatsDocuments = (List<Document>) document.get("homeStats");
        List<Document> awayStatsDocuments = (List<Document>) document.get("awayStats");

        List<Position> positions = new ArrayList<>();
        List<TeamStats> overallStats = new ArrayList<>();
        List<TeamStats> homeStats = new ArrayList<>();
        List<TeamStats> awayStats = new ArrayList<>();

        positionDocuments.forEach(position -> {
            positions.add(Position.fromDocument(position));
        });

        overallStatsDocuments.forEach(overallStat -> {
            overallStats.add(TeamStats.fromDocument(overallStat));
        });

        homeStatsDocuments.forEach(homeStat -> {
            homeStats.add(TeamStats.fromDocument(homeStat));
        });

        awayStatsDocuments.forEach(awayStat -> {
            awayStats.add(TeamStats.fromDocument(awayStat));
        });

        return new Team(
                document.get("_id").toString(),
                document.getString("name"),
                positions,
                overallStats,
                homeStats,
                awayStats
        );
    }
}
