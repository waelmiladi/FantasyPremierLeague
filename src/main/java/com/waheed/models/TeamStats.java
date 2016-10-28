package com.waheed.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import graphql.annotations.GraphQLField;

public class TeamStats {
    @JsonProperty
    private int wins;

    @JsonProperty
    private int draws;

    @JsonProperty("losts")
    private int losses;

    @JsonProperty("points")
    private int points;

    @JsonProperty("scores")
    private int goalsScored;

    @JsonProperty("conceded")
    private int goalsConceded;


    @JsonProperty("goal_difference")
    private int goalDifference;

    @JsonProperty("last_5")
    private String form;

    @JsonProperty("matches_played")
    private int matchesPlayed;

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
    public String getForm() {
        return form;
    }
}