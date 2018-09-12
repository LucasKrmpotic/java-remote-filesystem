package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.RFSClient;
import ui.ConnectionPanel;

public class ConnectionController implements ActionListener{
	private RFSClient model;
	private ConnectionPanel view;

	public ConnectionController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(ConnectionPanel view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String hostname = this.view.getHostname();
		String port = this.view.getPort();
		try {			
			this.model.connect(hostname, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
