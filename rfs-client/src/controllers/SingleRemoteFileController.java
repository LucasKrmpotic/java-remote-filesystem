package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import client.RFSClient;
import remoteobjects.FileMetadata;
import ui.SingleRemoteFilePanel;

public class SingleRemoteFileController implements ActionListener {
	private RFSClient model;
	private SingleRemoteFilePanel view;
	
	public SingleRemoteFileController (RFSClient model) {
		this.model = model;
	}

	public void setView(SingleRemoteFilePanel view) {
		this.view = view;
	}
	
	public RFSClient getModel() {
		return this.model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String methodName = e.getActionCommand();
				
        Method method;
		if (methodName.equals("rmtRead")) {
					
			FileMetadata remote_file = this.view.getRemoteFileMetadata();
			this.rmtRead(remote_file);
					
		} else if (methodName.equals("lclWrite")) {

			FileMetadata local_file = this.view.getLocalFile(); 
			this.lclWrite(local_file);
		}
	}

	
	public FileMetadata lookUpLocalCopy(String file_name) {
		String[] f = file_name.split("/");
		System.out.println(f);
		FileMetadata file = new FileMetadata(f[2]);
		if (file.getFileName() == null)
			return null;
		return file;
	}
	
	//Eventos Remotos
	public void rmtRead(FileMetadata remote_file) {
		System.out.println("entró a read");
		System.out.println(remote_file.getFileName());
		this.getModel().readFileFromServer(remote_file);
		
	}

	public void lclWrite(FileMetadata local_file) {
		File file = new File(local_file.getFileName());
		this.model.writeFileToServer(file);
	}

}