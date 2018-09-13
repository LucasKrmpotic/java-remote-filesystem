package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import remoteobjects.FileProxy;
import remoteobjects.FileMetadata;
import remoteobjects.ResponseLogin;

public class RFSClient {

	private static ClientStub stub;
	private String user_token;
	public ArrayList<FileProxy> remote_files_opened; 
	private List<FileMetadata> availableFiles;
	
	public RFSClient() throws UnknownHostException, IOException{	
//		this.stub = new ClientStub("localhost", 7896);
		this.remote_files_opened = new ArrayList<FileProxy>();
	}
	

	// CONNECT	
	public void connect(String hostname, String port) throws NumberFormatException, UnknownHostException, IOException, Exception {
		stub = this.getStub(hostname, Integer.parseInt(port));
	}	

	public ClientStub getStub(String hostname, int port) throws UnknownHostException, IOException {
		if(stub == null) {
			stub = new ClientStub(hostname, port);
		}
		return stub;
	}
	
	// LOGIN	
	public void login(String username, String password) throws ClassNotFoundException, IOException{
		
		ResponseLogin response = stub.login(username, password);
		this.setUserToken(response.getUserToken());
		List<FileMetadata> availableFiles = response.getAvailableFiles();
		if(!availableFiles.isEmpty())
			this.setAvailableFiles(response.getAvailableFiles());
	}
	
	// CREATE AN ACCOUNT
	public void signUp(String username, String password) {
		stub.signUp(username, password);
	}
	
	public void open(String file_name) throws ClassNotFoundException, IOException, Exception {
		
		FileProxy file = stub.rfs_open(file_name);
		
		System.out.println(file.getFileName());
		remote_files_opened.add(file);
	}
	
	public void read(FileProxy file) throws ClassNotFoundException, Exception {
		byte[] buffer = new byte[1024];

	    int count = stub.rfs_read(file, buffer);
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

			stub.rfs_wrtite(file_name, buffer, count);
		}		
	}
	
	public void close() {
		
	}
	
	public List<FileMetadata> getAvailableFiles(){
		return this.availableFiles;
	}
	
	public String getUserToken() {
		return this.user_token;
	}
	
	public void setUserToken(String token) {
		this.user_token = token;
	}
	
	public void setAvailableFiles(List<FileMetadata> files) {
		if (this.availableFiles.isEmpty()) {
			availableFiles = new ArrayList<FileMetadata>(files);
		}
	}
	
}

