package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import remoteobjects.FileMetadata;
import ui.SingleRemoteFilePanel;

public class SingleRemoteFileController implements ActionListener {
	
	private SingleRemoteFilePanel view;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		
	}

	public void setView(SingleRemoteFilePanel view) {
		this.view = view;
	}
	
	public FileMetadata lookUpLocalCopy(String file_name) {
		String dir = "src/controllers/";
		return new FileMetadata(new File(dir+file_name), file_name);
	}

}