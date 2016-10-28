package com.waheed.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.waheed.Constants;
import com.waheed.models.League;
import com.waheed.models.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class FantasyPremierLeagueClient {

    private HttpClient httpClientClient;
    private ObjectMapper mapper;
    private String mashapeKey;

    @Inject
    public FantasyPremierLeagueClient(
            @Named("mashapeKey") String mashapeKey,
            HttpClient httpClientClient,
            ObjectMapper mapper
    ) {
        this.httpClientClient = httpClientClient;
        this.mapper = mapper;
        this.mashapeKey = mashapeKey;
    }

    public League getLeague() throws IOException {
        JSONObject response = new JSONObject(httpClientClient.get(Constants.leagueUrl, mashapeKey));
        JSONObject data = response.getJSONObject("data");
        return mapper.readValue(data.toString(), League.class);
    }
}
