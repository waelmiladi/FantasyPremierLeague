package com.waheed.models;

import graphql.annotations.GraphQLField;
import org.bson.Document;

import java.util.Date;

public class TeamStats {
    private int wins;
    private int draws;
    private int losses;
    private int points;
    private int goalsScored;
    private int goalsConceded;
    private int goalDifference;
    private int matchesPlayed;
    private String roundId;
    private Date roundStartDate;
    private Date roundEndDate;

    public TeamStats(int wins, int draws, int losses, int points, int goalsScored, int goalsConceded, int goalDifference,
                     int matchesPlayed, String roundId, Date roundStartDate, Date roundEndDate) {
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.points = points;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.goalDifference = goalDifference;
        this.matchesPlayed = matchesPlayed;
        this.roundId = roundId;
        this.roundStartDate = roundStartDate;
        this.roundEndDate = roundEndDate;
    }

    @GraphQLField
    public int getWins() {
        return wins;
    }

    @GraphQLField
    public int getDraws() {
        return draws;
    }

    @GraphQLField
    public int getLosses() {
        return losses;
    }

    @GraphQLField
    public int getPoints() {
        return points;
    }

    @GraphQLField
    public int getGoalsScored() {
        return goalsScored;
    }

    @GraphQLField
    public int getGoalsConceded() {
        return goalsConceded;
    }

    @GraphQLField
    public int getGoalDifference() {
        return goalDifference;
    }

    @GraphQLField
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    @GraphQLField
    public String getRoundId() {
        return roundId;
    }

    @GraphQLField
    public long getRoundStartTime() {
        return roundStartDate.getTime();
    }

    @GraphQLField
    public long getRoundEndTime() {
        return roundEndDate.getTime();
    }

    public static TeamStats fromDocument(Document document) {
        return new TeamStats(
                document.getInteger("wins"),
                document.getInteger("draws"),
                document.getInteger("losts"),
                document.getInteger("points"),
                document.getInteger("scores"),
                document.getInteger("conceded"),
                document.getInteger("goal_difference"),
                document.getInteger("matches_played"),
                document.getObjectId("roundId").toString(),
                document.getDate("roundStartDate"),
                document.getDate("roundEndDate")
        );
    }
}