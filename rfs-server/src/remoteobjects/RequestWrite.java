package remoteobjects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestWrite extends RFSCommand{

    private static final long serialVersionUID = 1L;
    private int count; 
    private String file_name;
    private String file_id;
    public byte[] file_content;
    private String file_hash;
    private FileMetadata metadata;
    private String method_name;
    private String method_name_for_searching_a_opened_file;

    
    public RequestWrite(int count, String file_id, String file_name, String method_name, String method_name_for_searching_a_opened_file){
        this.count = count;
        this.file_name = file_name;
        this.file_content = new byte[1024];
        this.method_name = method_name;
        this.method_name_for_searching_a_opened_file = method_name_for_searching_a_opened_file;
    }

    public RequestWrite(String file_id, String file_name){
        this (0, file_id, file_name, "write", "getOpenedFile");        
    }


    @Override
    public ResponseWrite exec(Object server) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        
        
        Method methodForSearchingOpenedFile = server.getClass().getMethod(this.getMethodNameForSearchingAOpenedFile(), String.class, String.class);

        FileProxy file = (FileProxy) methodForSearchingOpenedFile.invoke(server, this.getFileId(), this.getFileName());

        Method method = server.getClass().getMethod(this.getMethodName(), FileProxy.class);
        method.invoke(server, file);
        // 	RFSWrite peticion = (RFSWrite) obj;
		return null;

        // 	System.out.println(peticion.file_name);
        // 	this.server.write(
        // 		peticion.file_name,
        // 		peticion.count,
        // 		peticion.data
        // 	);


    }

    private String getMethodName() {
		
		return this.method_name;
	}

	private String getMethodNameForSearchingAOpenedFile() {
		return this.method_name_for_searching_a_opened_file;
	}

	public int getCount(){
        return this.count;
    }

    public String getFileId(){
        return this.file_id;
    }

    public String getFileName(){
        return this.file_name;
    }
    
    public String getHash (){
        return this.file_hash;
    }

    public void setHash(String hash){
        this.file_hash = hash;
    }
}