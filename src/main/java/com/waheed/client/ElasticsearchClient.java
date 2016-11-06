package com.waheed.client;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.json.JSONObject;

import java.io.IOException;

public class ElasticsearchClient {
    private Client client;
    private String indexName;

    @Inject
    public ElasticsearchClient(Client client, @Named("indexName") String indexName) {
        this.client = client;
        this.indexName = indexName;
    }

    public IndexResponse index(String documentType, JSONObject document, String id) throws IOException {
        return client.prepareIndex(indexName, documentType, id)
                .setSource(document.toString())
                .get();
    }

    public GetResponse findDocument(String documentType, String id) throws IOException {
        return client.prepareGet(indexName, documentType, id).get();
    }
}
