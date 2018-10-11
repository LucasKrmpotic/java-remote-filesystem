package remoteobjects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;
import java.lang.System;


public class RequestRead extends RFSCommand{
    private static final long serialVersionUID = 1L;
    private FileProxy file;
    private byte[] buffer;
    
    public RequestRead(FileProxy file, byte[] buffer){
        this.file = file;
        this.buffer = buffer;
    }


    @Override
    public ResponseRead exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
    	
        Method method = server.getClass().getMethod("read", FileProxy.class, byte[].class);
        int count = (int) method.invoke(server, this.file, this.buffer);
        
        System.out.println("Count:"+count);

        ResponseRead response = new ResponseRead(count, file);
        if (count != -1) {        	
        	System.arraycopy(buffer, 0, response.data, 0, count);
        }
        return response;
        
//        while ((count = (int) method.invoke(server, this.file)) != -1) {
//        	ResponseRead response = new ResponseRead(count, file);
//        	System.arraycopy(file.file_buffer, 0, response.data, 0, file.getFileLength());
//        	return response;         	
//        }
        

    }

}
