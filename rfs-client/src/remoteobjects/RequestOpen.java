package remoteobjects;

import remoteobjects.IRFSConstants;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class RequestOpen extends RFSCommand{
    private static final long serialVersionUID = 1L;
	public String file_name;
    public int mode; 
    public String method_name;

    public RequestOpen (String file_name, int mode, String method_name){
        super();
        this.file_name = file_name;        
        this.mode = mode;
        this.method_name = method_name;
    }

    public RequestOpen(String file_name, String method_name){
        this (file_name, IRFSConstants.OPEN_O_CREAT, method_name);
    }

    public RequestOpen(String file_name, int mode){
        this (file_name, mode, "open");
    }

    public RequestOpen(String file_name) {
        this (file_name, IRFSConstants.OPEN_O_CREAT, "open");
    }

    public ResponseOpen exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
    
        FileProxy file = new FileProxy(this.file_name);
        if ((
                this.getMode() == IRFSConstants.OPEN && 
                file.exists() && 
                file.isOwner(this.getUserToken())
            
            )|| this.getMode() == IRFSConstants.OPEN_O_CREAT){
                
            Method method = server.getClass().getMethod(this.getMethodName(), FileProxy.class);
            method.invoke(server, file);

            ResponseOpen response = new ResponseOpen(file.getFileName(), file.getFileId());

            if (file.getMetadata() != null){
                response.setMetadata((FileMetadata) file.getMetadata());
            }
            return response;
        } 
        return null;
    }

    public String getFileName(){
        return this.file_name;
    }
    public String getMethodName(){
        return this.method_name;
    }
    public int getMode(){
        return this.mode;
    }
}
