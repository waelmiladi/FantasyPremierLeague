package com.waheed;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class FantasyPremierLeagueConfiguration extends Configuration {
    @NotEmpty
    private String mashapeKey;

    public String getMashapeKey() {
        return mashapeKey;
    }

    @JsonProperty
    public void setMashapeKey(String mashapeKey) {
        this.mashapeKey = mashapeKey;
    }
}
