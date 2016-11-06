package com.waheed.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import graphql.annotations.GraphQLField;
import org.bson.Document;

import java.util.Date;

public class Round {
    private String id;
    private String name;
    private String slug;
    private Date startDate;
    private Date endDate;

    public Round() {}

    public Round(String id, String name, String slug, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @GraphQLField
    public String getId() {
        return id;
    }

    @GraphQLField
    public String getName() {
        return name.replace("Giornata", "Round");
    }

    public Date getStartDate() {
        return startDate;
    }

    @GraphQLField
    public long getStartTime() {
        return startDate.getTime();
    }

    @GraphQLField
    public Date getEndDate() {
        return endDate;
    }

    @GraphQLField
    public long getEndTime() {
        return endDate.getTime();
    }

    public String getSlug() {
        return slug.replace("giornata", "round");
    }

    public static Round fromDocument(Document document) {
        return new Round(
                document.get("_id").toString(),
                document.getString("name"),
                document.getString("slug"),
                document.getDate("startDate"),
                document.getDate("endDate"));
    }
}
