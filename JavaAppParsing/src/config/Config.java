package nikita.parsit.papki.com.config;

import java.util.Properties;

public class Config {
	private final Properties properties;
 
    // 1. Есть ли ограничения по размеру файла?
   
    // 3. Какой результат по поиску должен быть в итоге после сохранения данных в ES?
        

	public Config(Properties properties) {
		this.properties = properties;
	}

	public String getWorkingFolder() {
		//TODO
		return properties.getProperty("directory.work");
		//FIXME
		//return "./work"; - я так полагаю, что это заглушка?
	}

	public String getArchiveFolderName() {
		//TODO
		return properties.getProperty("directory.archives");
		//FIXME
		
	}

	public String getEmailFolderName() {
		//TODO
		return properties.getProperty("directory.emails");
		//FIXME
		//return "emails";
	}

	public String getImageFolderName() {
		//TODO
		return properties.getProperty("directory.images");
		//FIXME
		//return "images";
	}

	public String getTextFolderName() {
		//TODO
		return properties.getProperty("directory.texts");
		//FIXME
		//return "texts";
	}
        
        public String getESHost() {
                return properties.getProperty("ES.host");
        } 
        
       public int getESPort() {
                return Integer.parseInt(properties.getProperty("ES.port"));
                
        }
       
       public String getESProtocol() {
                return properties.getProperty("ES.protocol");
        }
}