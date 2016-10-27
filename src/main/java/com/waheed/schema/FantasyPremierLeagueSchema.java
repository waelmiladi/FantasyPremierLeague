package com.waheed.schema;

import graphql.annotations.GraphQLAnnotations;
import graphql.schema.GraphQLSchema;

import static graphql.schema.GraphQLSchema.newSchema;

public class FantasyPremierLeagueSchema {
    public GraphQLSchema getSchema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        return newSchema()
                .query(GraphQLAnnotations.object(QuerySchema.class))
                .build();
    }
}
