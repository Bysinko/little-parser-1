package nikita.parsit.papki.com.folder;

import folder.ESSaver;
import nikita.parsit.papki.com.config.Config;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

//TODO Multi-threading logic should be implemented in this class
public class FolderParser {

    private final Config config;

    private final ExecutorService executorService;
    private final List<FolderProcessor> processors;

    public FolderParser(Config config) {
        this.config = config;
        this.executorService = Executors.newFixedThreadPool(4);
        this.processors = new ArrayList<>(4);
    }

    public void start() {
        try {
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(config.getESHost(), config.getESPort(), config.getESProtocol())
                    ));
            ESSaver esSaver = new ESSaver(client);
            FolderProcessor folderProcessor = new FolderProcessor(Paths.get(config.getWorkingFolder() + "/" + config.getTextFolderName()), esSaver);
            FolderProcessor folderProcessor1 = new FolderProcessor(Paths.get(config.getWorkingFolder() + "/" + config.getArchiveFolderName()), esSaver);
            FolderProcessor folderProcessor2 = new FolderProcessor(Paths.get(config.getWorkingFolder() + "/" + config.getEmailFolderName()), esSaver);
            FolderProcessor folderProcessor3 = new FolderProcessor(Paths.get(config.getWorkingFolder() + "/" + config.getImageFolderName()), esSaver);

            processors.add(folderProcessor);
            executorService.submit(folderProcessor);

//			new Thread(folderProcessor).start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        //TODO
        processors.forEach(processor -> {
            processor.stop();
        });

    }
}
