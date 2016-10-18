package com.waheed;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FantasyPremierLeagueApplication extends Application<FantasyPremierLeagueConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FantasyPremierLeagueApplication().run(args);
    }

    @Override
    public String getName() {
        return "FantasyPremierLeague";
    }

    @Override
    public void initialize(final Bootstrap<FantasyPremierLeagueConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FantasyPremierLeagueConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
