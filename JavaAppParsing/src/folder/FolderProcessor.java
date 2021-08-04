package nikita.parsit.papki.com.folder;

import folder.ESSaver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FolderProcessor implements Runnable {

//    private final long minFileAge = TimeUnit.SECONDS.toMillis(5);
    private final Path folderPath;
    private static long fileCount = 0;

    private boolean stop = false;
    private final ESSaver esSaver;
    
    public FolderProcessor(Path folderPath, ESSaver esSaver) throws IOException {
        this.esSaver = esSaver;
        this.folderPath = folderPath;
        
        initFolder();
    }

    @Override
    public void run() {
        this.stop = false;
        while (!stop) {
            List<File> files = null;
            try {
                files = getFiles();
            } catch (IOException ex) {
                Logger.getLogger(FolderProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(files);
            files.forEach(this::processFile);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void stop() {
        this.stop = true;
    }

    private void initFolder() throws IOException {
        if (!folderPath.toFile().exists()) {
            System.out.println("Folder is missing: " + folderPath.toString());
            if (folderPath.toFile().mkdir()) {
                System.out.println("Folder is created: " + folderPath.toString());
            } else {
                throw new IOException("Failed to create a folder: " + folderPath.toString());
            }
        }
    }

    private List<File> getFiles() throws IOException {
        final List<File> remainingFiles = new ArrayList<>();

        File[] files = folderPath.toFile().listFiles();
        if (files == null) {
            System.err.println("Failed to get file list");
            return new ArrayList<>();
        }
        System.out.println("fielsprocessed :" + fileCount);
        for (File file : files) {
            if (!file.getName().endsWith(".txt")) {
//                if (!file.getName().endsWith(".zip")) {
                continue;
//                }

            }
//            if (!isFileOldEnough(file)) {
//                continue;
//            }
            remainingFiles.add(file);
            fileCount++;
            

//            BufferedReader reader = new BufferedReader(new FileReader(file)); чтение и запись в текстовый файл 
//            String str;
//              BufferedWriter writer = new BufferedWriter(new FileWriter("full_txt.txt", true));
//            while ((str = reader.readLine()) != null) {
//                writer.write(str);
//            }
//            reader.close();
//            writer.close();
        }
        return remainingFiles;
    }

    private void processFile(File file) {

        try {
            BufferedReader reader;
                System.out.println(file.getName());
            reader = new BufferedReader(new FileReader(file));
            String str;
            int lineNumber = 0; 
            while ((str = reader.readLine()) != null) { 
                System.out.println(str); //оставляем для тестирования, но понимаем, что логи будут раздуты
                
                boolean isSuccess = esSaver.save(str, "allcontent", file.getName(), ++lineNumber);
                if (!isSuccess){
                    System.out.println("Error save file with name " + file.getName());
               
                }
                else {
                    System.out.println("OK");
                }
            }

            reader.close();
        } catch (IOException ex) {
          ex.printStackTrace();

        }

        // open file
        // read file
        // write file to ES
        // confirmation from ES about write
        // close file
        // FIXME
//        System.out.println(file.getName());
    }

//    private boolean isFileOldEnough(File file) {
//        try {
//            long currentTimestamp = System.currentTimeMillis();
//            long maxAllowedTimestamp = currentTimestamp - minFileAge; // timestamp of the file must be less than this value
//            long fileTimestamp = Files.readAttributes(Paths.get(file.getAbsolutePath()), BasicFileAttributes.class).lastModifiedTime().toMillis();
//            return fileTimestamp > maxAllowedTimestamp;
//        } catch (IOException e) {
//            // must not happen
//        }
//        return false;
//    }
}
