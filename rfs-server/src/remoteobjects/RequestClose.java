package remoteobjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestClose extends RFSCommand{
	
	public FileProxy file;
	
	public RequestClose(FileProxy file) {
		this.file = file;
	}
	
    @Override
    public RFSCommand exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        Method method = server.getClass().getMethod("close", FileProxy.class);
        boolean status = (boolean)method.invoke(server, this.file);

        RFSCommand response = new RFSCommand();
        if (!status) {
        	response.setError(true);
        	response.setErrorMessage("no se pudo cerrrar el archivo");
        	return response;
        }
        return response;
        
    }
	
}
