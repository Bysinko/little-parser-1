package folder;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nikita.parsit.papki.com.config.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class ESSaver {
    private final RestHighLevelClient client;

    public ESSaver(RestHighLevelClient client) {
        this.client = client;
    }
  
        
   
    public boolean save(String str, String index, String filename, int lineNumber) {
        try { 
            IndexRequest indexRequest = new IndexRequest(index, "doc");
            indexRequest.source("data", str, "filename", filename, "line", lineNumber);
            

            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            return indexResponse.status() == RestStatus.CREATED;
        } catch (Throwable ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
