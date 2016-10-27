package com.waheed.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

import java.util.List;

@GraphQLName("League")
@JsonIgnoreProperties(value = { "statusCode", "statusReason", "errorCode" })
public class League {
    @JsonProperty("standings")
    private List<Team> teams;

    @GraphQLField
    public List<Team> getTeams() {
        return teams;
    }
}
