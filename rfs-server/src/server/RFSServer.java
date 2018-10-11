package server;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.jws.soap.SOAPBinding.Use;

import authmiddleware.FakeAuthService;
import models.UserModel;
import models.FileModel;
import remoteobjects.FileMetadata;
import remoteobjects.FileProxy;
import remoteobjects.IRFSConstants;
import remoteobjects.RFSCommand;
import remoteobjects.ResponseLogin;
import remoteobjects.ResponseRead;

public class RFSServer {
	
	private UserModel user = null;
	private FileModel fileModel = new FileModel();
	private FakeAuthService _authService = new FakeAuthService();
	public List<FileProxy> remote_files_opened;
	
	public RFSServer() {
        this.remote_files_opened = new ArrayList<FileProxy>();
    }
	
    public FileProxy getOpenedFile(FileProxy file){
		FileProxy result = null;
		System.out.println("Dentro del getOpenedFIle");
		System.out.println(file.getFileID() + "  "+ file.getFileName());
        // for (FileProxy f : this.remote_files_opened) {
        //     if (f.getFileID() == file.getFileID()){
		// 		result = f;
        //         break;
		// 	}
		// }

		result = (FileProxy)this.remote_files_opened.stream()
		 	.filter(f -> f.getFileID().equals(file.getFileID()))
		 	.findFirst()
		 	.get();
        
		if (result == null)
			System.out.println("nulo");
		System.out.println("Despues del For");
		System.out.println(result.getFileName()+ "  "+ result.getFileID());
		// result = this.remote_files_opened.stream()
		// 	.filter(f -> f.getFileID() == file.getFileID())
		// 	.findFirst()
		// 	.get();
        return result;        
    }

    
    // LOGIN
    public ResponseLogin login(String username, String password) {
    	
    	UserModel user =  this._authService.login(username, password);
    	
    	ResponseLogin response = new ResponseLogin();
    	response.setUserToken(user.getUID());
    	
    	List<FileMetadata> availableFiles = this.getAvailableUserFiles(user.getUID());
    	if(!availableFiles.isEmpty())
    		response.setAvailableFiles(availableFiles);
    	
    	return response;
    }
    
    // SIGNUP
    public String signup (String username, String password) throws IOException {
    	this.user = this._authService.signup(username, password);
    	if (this.user == null)
    		return null;
    	return this.user.getUID();
    }
    
	public FileProxy open(String file_name, int mode, String user_token) {
		FileProxy file;
		try {
			file = new FileProxy(file_name);
			if ((
					mode == IRFSConstants.OPEN && 
					file.exists() && 
					file.isOwner(user_token)
					
					)|| mode == IRFSConstants.OPEN_O_CREAT) {
				// file.setFileId(UUID.randomUUID().toString());

				System.out.println("Tamaño Antes del ADD"+this.remote_files_opened.size());
				this.remote_files_opened.add(file);
				System.out.println("Tamaño Despues del ADD"+this.remote_files_opened.size());
				
				System.out.println(file.getFileName());
				System.out.println(file.getFileID());
				
				return file;
				
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}        
        return null;
	}

		
    public int read( FileProxy file, byte[] buffer) throws IOException{
		
		System.out.println("NOMBRE:"+ file.getFileName());
		FileProxy openedFile = this.getOpenedFile(file);

		//openedFile.fileBufferInitialize();
		System.out.println("OpenesFile: "+ openedFile.getFile().getName() + " " + openedFile.getFileName());
		System.out.println("TAMAÑO:"+openedFile.getFileLength());
		FileInputStream algo = openedFile.getFileInputS();
		if(algo == null){
			System.out.println("FILE INPUT STREAM NULL");
		}
		int pruebaCount = algo.read(buffer);
		System.out.println("Count: "+ pruebaCount);

        int count = openedFile.getFileInputS().read(buffer);
        return count;

		
    	// file.fileBufferInitialize();
        // FileInputStream is = new FileInputStream(file.getFile());
        // is.skip(offset);        
        // int count = is.read(file.file_buffer);
        // return count;
        
    }
	
	
	public void write (FileProxy file, int count, byte[] data) throws IOException {		

		//FileOutputStream out = new FileOutputStream(file.getFile(), true);
		System.out.println("ID del fileProxy"+file.getFileID());

		FileProxy openedFile = this.getOpenedFile(file);
		System.out.println(openedFile.getFileID());
		file.getFileOutputS().write(data);;
		//out.write(data);
	}
	public boolean close(FileProxy file) {
		
		FileProxy file_to_remove = (FileProxy) this.remote_files_opened
				.stream()
				.filter(f -> f.getFileID().equals(file.getFileID()))
				.findFirst()
				.get();
		
		return this.remote_files_opened.remove(file_to_remove);
		
	}
	
	public List<FileMetadata> getAvailableUserFiles(String owner) {
		return (List<FileMetadata>) this.fileModel.filterByOwner(owner);
	}
	
    
    
}
