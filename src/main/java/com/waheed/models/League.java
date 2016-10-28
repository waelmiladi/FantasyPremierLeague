package com.waheed.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import graphql.annotations.GraphQLField;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(value = { "statusCode", "statusReason", "errorCode" })
public class League {
    @JsonProperty("standings")
    private List<Team> teams;

    @GraphQLField
    public List<Team> getTeams() {
        return teams;
    }

    @GraphQLField
    public Team getTeam(String identifier) {
        Optional<Team> team = teams.stream()
                .filter(t -> identifier.equals(t.getIdentifier()))
                .findFirst();
        if (team.isPresent()) return team.get();
        return null;
    }
}
