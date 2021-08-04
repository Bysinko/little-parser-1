package nikita.parsit.papki.com.file;

import java.io.File;
import java.io.IOException;

public class FileFilter {

	private File file;

	public FileFilter(String fName) throws IOException {
		file = new File(fName);
		file = file.getAbsoluteFile();
		System.out.println(file.getCanonicalPath());
	}

	public String getCanonicalPath() throws IOException {
		return file.getCanonicalPath();
	}

	public boolean isTXTFile() throws IOException {
		return file.getName().endsWith(".txt");//&&nikita.parsit.papki.com.file.isFile(); 
	}
}