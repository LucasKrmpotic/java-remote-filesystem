package remoteobjects;

import java.lang.reflect.Method;

public class ResponseOpen extends RFSCommand{

    private static final long serialVersionUID = 1L;
	private String file_name;
    private FileMetadata metadata;
    private String file_id;

    public ResponseOpen(String file_name){
        super();
        this.file_name = file_name;
    }

    public ResponseOpen(String file_name, String file_id){
        this(file_name);
        this.file_id = file_id;
    }

    public ResponseOpen(String file_name, FileMetadata metadata){
        this (file_name);
        this.metadata = metadata;
    }


    public void setMetadata(FileMetadata metadata){
        this.metadata = metadata;
    }

    public FileMetadata getMetadata(){
        if (this.metadata != null){
            return this.metadata;
        } else {
            return null;
        }
    }

    public String getFileName(){
        return this.file_name;
    }

    public String getFileId(){
        return this.file_id;
    }

}
