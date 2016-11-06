package com.waheed.models;

import graphql.annotations.GraphQLField;
import org.bson.Document;

import java.util.Date;

public class Position {
    private int position;
    private String roundId;
    private Date roundStartDate;
    private Date roundEndDate;

    public Position(int position, String roundId, Date roundStartDate, Date roundEndDate) {
        this.position = position;
        this.roundId = roundId;
        this.roundStartDate = roundStartDate;
        this.roundEndDate = roundEndDate;
    }

    @GraphQLField
    public int getPosition() {
        return position;
    }

    @GraphQLField
    public String getRoundId() {
        return roundId;
    }

    @GraphQLField
    public long getRoundStartTime() {
        return roundStartDate.getTime();
    }

    @GraphQLField
    public long getRoundEndTime() {
        return roundEndDate.getTime();
    }

    public static Position fromDocument(Document document) {
        return new Position(
                document.getInteger("position"),
                document.getObjectId("roundId").toString(),
                document.getDate("roundStartDate"),
                document.getDate("roundEndDate")
        );
    }
}
