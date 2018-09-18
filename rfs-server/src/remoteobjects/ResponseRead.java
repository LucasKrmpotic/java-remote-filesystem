package remoteobjects;

import java.io.Serializable;
import java.io.File;

public class ResponseRead extends RFSCommand {
    
    private static final long serialVersionUID = 1L;
    public FileProxy file;
    public int count;
    public byte[] data;

    public ResponseRead(int count, FileProxy file){
        super();
        this.file = file;
        this.count = count;
        this.data = new byte[1024];
    }

}
