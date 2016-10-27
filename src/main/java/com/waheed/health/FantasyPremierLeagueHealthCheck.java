package com.waheed.health;

import com.hubspot.dropwizard.guice.InjectableHealthCheck;

public class FantasyPremierLeagueHealthCheck extends InjectableHealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

    @Override
    public String getName() {
        return "fantasy-premier-league";
    }
}
