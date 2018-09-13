package client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.OutputStream;

import remoteobjects.FileMetadata;
import remoteobjects.FileProxy;
import remoteobjects.RFSCommand;
import remoteobjects.RequestLogin;
import remoteobjects.RequestOpen;
import remoteobjects.RequestRead;
import remoteobjects.RequestSignUp;
import remoteobjects.ResponseLogin;
import remoteobjects.ResponseOpen;
import remoteobjects.ResponseRead;

import java.lang.System;

public class ClientStub {
	private static Socket socket = null;
	private static ObjectInputStream in = null;
	private static ObjectOutputStream out = null;
	
	public String archivo_remoto;

	public ClientStub(String hostname, int port) throws UnknownHostException, IOException {

		socket = this.getSocket(hostname, port);		
		in = this.getInputStream(socket);
		out = this.getOutputStream(socket);

	}

	
	// LOGIN	
	public ResponseLogin login(String username, String password) throws IOException, ClassNotFoundException {
		RequestLogin request = new RequestLogin(username, password);
		out.writeObject(request);
		RFSCommand response = (RFSCommand) in.readObject();
		if (response.error) {
			ResponseLogin error = new ResponseLogin();
			error.error = true;
			error.setErrorMessage(response.getErrorMessaage());
			return error;
		}
		return (ResponseLogin) response;
	}
	
	
	// CREATE AN ACCOUNT
	public RFSCommand signUp(String username, String password) throws IOException, ClassNotFoundException {
		
		RequestSignUp request = new RequestSignUp(username, password);
		out.writeObject(request);
		RFSCommand response = (RFSCommand) in.readObject();
		return response;
		
	}
	
	
	public FileProxy rfs_open(String file_name) throws Exception, IOException, ClassNotFoundException {

		RequestOpen request = new RequestOpen(file_name);
		out.writeObject(request);
		ResponseOpen response = (ResponseOpen) in.readObject();

		if (response.error){
			throw new Exception(response.getErrorMessaage());
		}

		FileProxy file = new FileProxy(response.getFileName());
		file.setFileId(response.getFileId());

		if (response.getMetadata() != null){
			file.setMetadata((FileMetadata) response.getMetadata());
		}
		return file;
	}

	public int rfs_read(FileProxy file, byte[] buffer) throws IOException, ClassNotFoundException, Exception {

		RequestRead request = new RequestRead(file.getFileId(),file.getFileName());
		out.writeObject(request);

		ResponseRead response = (ResponseRead) in.readObject();

		if (response.error){
			throw new Exception(response.getErrorMessaage());
		}

		System.arraycopy(response.data, 0, buffer, 0, response.count);
		return response.count;

	}

	public void rfs_wrtite(String file_name, byte[] contenido, int count) throws IOException, ClassNotFoundException {

//		RFSWrite peticion = new RFSWrite(file_name, count, contenido);
//		out.writeObject(peticion);
//
//		RFSWrite respuesta = (RFSWrite)in.readObject();

	}

	public void rfs_close(File archivo_remoto) throws IOException, ClassNotFoundException {

//		RFSClose obj_remoto = new RFSClose(archivo_remoto);
//
//		out.writeObject(obj_remoto);
//
//		RFSClose archivo = (RFSClose) in.readObject();

		// return archivo.cerrado;
	}
	
	
	
	
	public Socket getSocket(String hostname, int port) throws UnknownHostException, IOException {
		if(this.socket == null)
			this.socket = new Socket(hostname, port);
		return this.socket; 
	}
	
	public ObjectOutputStream getOutputStream(Socket socket) throws IOException {
		if (this.out == null) {
			this.out = new ObjectOutputStream(socket.getOutputStream());
		}
		return this.out;
	}
	
	public ObjectInputStream getInputStream(Socket socket) throws IOException {
		if(this.in == null) {
			this.in = new ObjectInputStream(socket.getInputStream());
		}
		return this.in;
	}
}
