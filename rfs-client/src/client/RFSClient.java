package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import remoteobjects.FileProxy;
import remoteobjects.FileMetadata;
import remoteobjects.ResponseLogin;

public class RFSClient {

	public ClientStub stub;
	public ArrayList<FileProxy> remote_files_opened; 
	private List<FileMetadata> availableFiles;
	
	public RFSClient(){		
		this.stub = new ClientStub("localhost", 7896);
		this.remote_files_opened = new ArrayList<FileProxy>();
	}
	
	
	public void login(String username, String password) throws ClassNotFoundException, IOException {
		this.stub.login(username, password);
	}
	
	public void open(String file_name) throws ClassNotFoundException, IOException, Exception {
		
		FileProxy file = this.stub.rfs_open(file_name);
		
		System.out.println(file.getFileName());
		remote_files_opened.add(file);
	}
	
	public void read(FileProxy file) throws ClassNotFoundException, Exception {
		byte[] buffer = new byte[1024];

	    int count = this.stub.rfs_read(file, buffer);
		String path = "cliente-"+ file.getFileName(); 
		File f = new File(path);
		f.createNewFile();
		FileOutputStream out = new FileOutputStream(f);
		out.write(buffer, 0, count);	
	}
	
	public void write(String file_name) throws ClassNotFoundException, IOException {
		byte[] buffer = new byte[1024];

		File f = new File(file_name);

		if (f.exists() && !f.isDirectory()){
			byte[] buf = new byte[1024];

			FileInputStream fi = new FileInputStream(f);
			int count = fi.read(buffer);

			this.stub.rfs_wrtite(file_name, buffer, count);
		}		
	}
	
	public void close() {
		
	}
	
	public List<FileMetadata> getAvailableFiles(){
		return this.availableFiles;
	}
	
}

