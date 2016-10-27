package com.waheed.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.waheed.FantasyPremierLeagueConfiguration;
import com.waheed.schema.FantasyPremierLeagueSchema;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

public class FantasyPremierLeagueModule extends AbstractModule {
    @Override
    protected void configure() {
        // anything you'd like to configure
    }

    @Provides
    @Named("mashapeKey")
    public String provideTemplate(FantasyPremierLeagueConfiguration configuration) {
        return configuration.getMashapeKey();
    }

    @Provides
    @Inject
    public GraphQLSchema provideGraphQLSchema(FantasyPremierLeagueSchema schema) throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        return schema.getSchema();
    }

    @Provides
    @Inject
    public GraphQL provideGraphQL(GraphQLSchema schema) {
        return new GraphQL(schema);
    }
}
