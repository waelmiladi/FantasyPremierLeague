package com.waheed.client;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

public class HttpClient {
    private String mashapeKey;
    private CloseableHttpClient httpClient;

    @Inject
    public HttpClient(@Named("mashapeKey") String mashapeKey ) {
        this.mashapeKey = mashapeKey;
        this.httpClient = HttpClients.createDefault();
    }

    public String get(String url) throws IOException {
        HttpGet request = createGetRequest(url);
        request.setHeader("X-Mashape-Key", mashapeKey);
        CloseableHttpResponse response = httpClient.execute(request);
        return getContent(response);
    }

    private HttpGet createGetRequest(String url) {
        HttpGet request = new HttpGet(url);
        request.setHeader("Accept", "application/json");
        return request;
    }

    private String getContent(CloseableHttpResponse response) throws IOException {
        String content = "";
        try {
            HttpEntity entity = response.getEntity();
            content = inputStreamToString(entity.getContent());
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return content;
    }

    private String inputStreamToString(InputStream inputStream) {
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
