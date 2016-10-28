package com.waheed.resources;

import com.google.inject.Inject;
import com.waheed.client.FantasyPremierLeagueClient;
import graphql.GraphQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Root {

    private GraphQL graphQL;
    private FantasyPremierLeagueClient client;

    @Inject
    public Root(GraphQL graphQL, FantasyPremierLeagueClient client) {
        this.graphQL = graphQL;
        this.client = client;
    }

    @POST
    public Object query(String query) throws Exception {
        return graphQL.execute(query, client).getData();
    }
}
