package com.waheed.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class FantasyPremierLeagueScriptsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("mashapeKey"))
                .toInstance("HXXAUCDnOpmsheFruHUydCaE3FODp1lhyj7jsnYTFn7TvoihEZ");
        bind(String.class)
                .annotatedWith(Names.named("mongoUri"))
                .toInstance("mongodb://premier-league:240208ela@ds145677.mlab.com:45677/premier-league");
    }

    @Provides
    @Inject
    public MongoClient provideMongoClient(@Named("mongoUri") String mongoUri) {
        MongoClientURI connectionString = new MongoClientURI(mongoUri);
        return new MongoClient(connectionString);
    }
}
