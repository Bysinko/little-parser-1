package nikita.parsit.papki.com.exceptions;

import java.io.FileNotFoundException;

public class FolderNotFoundException extends FileNotFoundException {

	public FolderNotFoundException(String s) {
		super(s);
	}
}