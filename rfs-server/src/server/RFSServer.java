package server;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import authmiddleware.FakeAuthService;
import models.UserModel;
import models.FileModel;
import remoteobjects.FileMetadata;
import remoteobjects.FileProxy;
import remoteobjects.ResponseLogin;

public class RFSServer {
	private FakeAuthService _authService = new FakeAuthService();
	private FileModel fileModel = new FileModel();
	public ArrayList<FileProxy> remote_files_opened;
	
	public RFSServer() {
        this.remote_files_opened = new ArrayList<FileProxy>();
    }
	
    public FileProxy getOpenedFile(String id, String file_name){
        FileProxy result = null;
        for (FileProxy f : this.remote_files_opened) {
            if (f.getFileId().equals(id) && f.getFileName().equals(file_name)){
                result = f;
                break;
            }
            System.out.println(f.getFileName());
            System.out.println(f.getFileId());
        }
        return result;        
    }

    
    public ResponseLogin login(String username, String password) {
    	UserModel user =  this._authService.login(username, password);
    	if (user == null) {
    		return null;
    	}
    	ResponseLogin response = new ResponseLogin();
    	response.setUserToken(user.getUID());
    	response.setAvailableFiles(this.getAvailableUserFiles(user.getUID()));
    	return response;
    	
    }
    
	public void open(FileProxy file) {

        file.setFileId(UUID.randomUUID().toString());
        this.remote_files_opened.add(file);
        
    }
		
    public void read( FileProxy file ) throws IOException{

        FileInputStream is = new FileInputStream(file.getFile());
        file.fileBufferInitialize();
        int count = is.read(file.file_buffer);
        file.setFileLength(count);
        is.close();
        
    }
	
	
	public void write (String file_name, int count, byte[] data) throws IOException {
        
        String path = "servidor-"+ file_name; 
        File f = new File(path);
        f.createNewFile();
        FileOutputStream out = new FileOutputStream(f);
        out.write(data, 0, count);
	}
        
	
	public void close(String file_name) {
		
	}
	
	public List getAvailableUserFiles(String owner) {
		return (List) this.fileModel.filterByOwner(owner);
	}
	
    
    
}
