package com.waheed.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import graphql.annotations.GraphQLName;

@GraphQLName("TeamStats")
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
    private String goalsConceded;


    @JsonProperty("goal_difference")
    private String goalDifference;

    @JsonProperty("last_5")
    private String form;

    @JsonProperty("matches_played")
    private int matchesPlayed;

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public String getGoalsConceded() {
        return goalsConceded;
    }

    public String getGoalDifference() {
        return goalDifference;
    }

    public String getForm() {
        return form;
    }
}