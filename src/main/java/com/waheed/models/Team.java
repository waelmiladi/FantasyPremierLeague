package com.waheed.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

@GraphQLName("Team")
@JsonIgnoreProperties(value = { "identifier", "penalization_points" })
public class Team {
    @JsonProperty("team_identifier")
    private String identifier;

    @JsonProperty
    private int position;

    @JsonProperty("team")
    private String name;

    @JsonProperty("overall")
    private TeamStats overallStats;

    @JsonProperty("home")
    private TeamStats homeStats;

    @JsonProperty("away")
    private TeamStats awayStats;

    @GraphQLField
    public String getIdentifier() {
        return identifier;
    }

    @GraphQLField
    public int getPosition() {
        return position;
    }

    @GraphQLField
    public String getName() {
        return name;
    }

    @GraphQLField
    public TeamStats getOverallStats() {
        return overallStats;
    }

    @GraphQLField
    public TeamStats getHomeStats() {
        return homeStats;
    }

    @GraphQLField
    public TeamStats getAwayStats() {
        return awayStats;
    }
}
