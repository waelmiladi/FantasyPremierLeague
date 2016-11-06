package com.waheed;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class FantasyPremierLeagueConfiguration extends Configuration {
    private Map<String, String> guiceConfig = ImmutableMap.of();

    @JsonProperty("guiceConfig")
    public void setGuiceConfig(Map<String,String> cfg) {
        this.guiceConfig = ImmutableMap.copyOf(cfg);
    }

    @NotNull
    @JsonProperty("guiceConfig")
    public Map<String, String> getGuiceConfig() {
        return guiceConfig;
    }
}
