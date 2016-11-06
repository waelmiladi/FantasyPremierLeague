package com.waheed.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.waheed.FantasyPremierLeagueConfiguration;
import com.waheed.schema.FantasyPremierLeagueSchema;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FantasyPremierLeagueModule extends AbstractModule {

    @Override
    protected void configure() {
        // anything you'd like to configure
    }

    @Provides
    @Named("mashapeKey")
    public String provideMashapeKey(FantasyPremierLeagueConfiguration configuration) {
        return configuration.getGuiceConfig().get("mashapeKey");
    }

    @Provides
    @Named("mongoUri")
    public String provideMongoUri(FantasyPremierLeagueConfiguration configuration) {
        String user = configuration.getGuiceConfig().get("mongoUsername");
        String password = configuration.getGuiceConfig().get("mongoPassword");
        String host = configuration.getGuiceConfig().get("mongoHost");
        String port = configuration.getGuiceConfig().get("mongoPort");
        String database = configuration.getGuiceConfig().get("mongoDatabase");

        return String.format("mongodb://%s:%s@%s:%s/%s", user, password, host, port, database);
    }

    @Provides
    @Named("clusterHost")
    public String provideHost(FantasyPremierLeagueConfiguration configuration) {
        return configuration.getGuiceConfig().get("clusterHost");
    }

    @Provides
    @Named("clusterName")
    public String provideClusterName(FantasyPremierLeagueConfiguration configuration) {
        return configuration.getGuiceConfig().get("clusterName");
    }

    @Provides
    @Named("indexName")
    public String provideIndexName(FantasyPremierLeagueConfiguration configuration) {
        return configuration.getGuiceConfig().get("indexName");
    }

    @Provides
    @Inject
    public MongoClient provideMongoClient(@Named("mongoUri") String mongoUri) {
        MongoClientURI connectionString = new MongoClientURI(mongoUri);
        System.out.println(connectionString);
        return new MongoClient(connectionString);
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

    @Provides
    @Inject
    public Client provideElasticsearchClient(
            @Named("clusterName") String clusterName,
            @Named("clusterHost") String clusterHost) throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .build();
        return TransportClient.builder()
                .settings(settings)
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(clusterHost), 9300));
    }
}
