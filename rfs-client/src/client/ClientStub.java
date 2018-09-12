package client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import remoteobjects.FileMetadata;
import remoteobjects.FileProxy;
import remoteobjects.RFSCommand;
import remoteobjects.RequestLogin;
import remoteobjects.RequestOpen;
import remoteobjects.RequestRead;
import remoteobjects.ResponseLogin;
import remoteobjects.ResponseOpen;
import remoteobjects.ResponseRead;

import java.lang.System;

public class ClientStub {
	private Socket s;
	ObjectInputStream in;
	ObjectOutputStream out;

	public String archivo_remoto;

	public ClientStub(String hostname, int port) {
		try {
			s = new Socket(hostname, port);
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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
}
