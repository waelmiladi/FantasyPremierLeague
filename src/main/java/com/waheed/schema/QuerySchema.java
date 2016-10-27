package com.waheed.schema;

import com.google.inject.Inject;
import com.waheed.client.FantasyPremierLeagueClient;
import com.waheed.models.League;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;

import java.io.IOException;

@GraphQLName("query")
public final class QuerySchema {

    @Inject
    private static FantasyPremierLeagueClient client;

    @GraphQLField
    public static League league(final DataFetchingEnvironment env) throws IOException {
        FantasyPremierLeagueClient client = (FantasyPremierLeagueClient) env.getContext();
        return client.getLeague();
    }
}
