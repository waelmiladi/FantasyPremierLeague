package com.waheed.schema;

import com.waheed.client.FantasyPremierLeagueClient;
import com.waheed.models.Round;
import com.waheed.models.Team;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import java.io.IOException;
import java.util.List;

@GraphQLName("query")
public final class QuerySchema {
    @GraphQLField
    public static List<Round> rounds(final DataFetchingEnvironment env) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getRounds();
    }

    @GraphQLField
    public static Round currentRound(final DataFetchingEnvironment env) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getCurrentRound();
    }

    @GraphQLField
    public static Round round(final DataFetchingEnvironment env, @GraphQLName("id") String id) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getRound(id);
    }

    @GraphQLField
    public static List<Team> teams(final DataFetchingEnvironment env) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getTeams();
    }

    @GraphQLField
    public static Team team(final DataFetchingEnvironment env, @GraphQLName("id") String id) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getTeam(id);
    }
}
