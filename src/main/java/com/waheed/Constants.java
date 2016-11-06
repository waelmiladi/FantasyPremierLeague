package com.waheed;

/**
 * Created by waheedelmiladi on 27/10/2016.
 */
public class Constants {
    private static final String sportsopBaseUrl = "https://sportsop-soccer-sports-open-data-v1.p.mashape.com/v1";

    public static final String standingsUrl = sportsopBaseUrl + "/leagues/premier-league/seasons/16-17/standings";
    public static final String roundsUrl = sportsopBaseUrl + "/leagues/premier-league/seasons/16-17/rounds";

    public static final String roundDocumentType = "round";
    public static final String teamDocumentType = "team";
}
