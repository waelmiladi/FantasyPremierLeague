package com.waheed.client;

public class ElasticsearchQueries {


    public static String currentRound = "" +
            "{\n" +
            "    \"query\": {\n" +
            "        \"bool\": {\n" +
            "            \"must\": [\n" +
            "                {\n" +
            "                    \"range\": {\n" +
            "                        \"startDate\": {\n" +
            "                            \"lte\": \"now\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"range\": {\n" +
            "                        \"endDate\": {\n" +
            "                            \"gte\": \"now\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";

    public static String league = "{\n" +
            "   \"query\": {\n" +
            "       \"bool\": {\n" +
            "           \"must\": [\n" +
            "               { \"match\": { \"positions.roundId\": \"welxfmnfymmgvj7qvw6ziq43fa12pybh\" }}\n" +
            "           ]\n" +
            "       }\n" +
            "   }\n" +
            "   \"sort\": { \"positions.position\": { \"order\": \"asc\" }}\n" +
            "}";
}
