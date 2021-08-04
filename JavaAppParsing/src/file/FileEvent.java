package nikita.parsit.papki.com.file;

public interface FileEvent {
    
    public final static int CREATED = 1;
   // public final static int DELETED = -1;
    
    public void process(String fName, int kind);
}