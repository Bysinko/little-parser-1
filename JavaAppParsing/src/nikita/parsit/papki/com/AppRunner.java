package nikita.parsit.papki.com;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import nikita.parsit.papki.com.config.Config;
import nikita.parsit.papki.com.folder.FolderParser;


import java.util.Properties;

public class AppRunner {
	public static void main(String[] args) throws IOException {
		new FolderParser(new AppRunner().initializeConfigAndGet()).start();
        
//        RestHighLevelClient client = new RestHighLevelClient(
//        RestClient.builder(
//            new HttpHost("localhost", 9200, "http")
//        ));
//        
//        IndexRequest indexRequest = new IndexRequest("helloworld", "doc");
//          indexRequest.source("hello", "heello world");
//
//
//        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
//        System.out.println(index.status());
//        
	}

	private Config initializeConfigAndGet() throws FileNotFoundException, IOException {
				
                Properties properties = new Properties();
                properties.load(new FileInputStream("src\\config\\newproperties.properties"));
		return new Config(properties);
	}
}