package com.waheed.schema;

import com.waheed.client.FantasyPremierLeagueClient;
import com.waheed.models.League;
import com.waheed.models.Team;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import java.io.IOException;

@GraphQLName("query")
public final class QuerySchema {
    @GraphQLField
    public static League league(final DataFetchingEnvironment env) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getLeague();
    }

    @GraphQLField
    public static Team team(final DataFetchingEnvironment env, @GraphQLName("identifier") String identifier) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getLeague().getTeam(identifier);
    }
}
