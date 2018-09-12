package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.RFSClient;
import ui.SignUpPanel;

public class SignUpController implements ActionListener{

	
	private RFSClient model;
	private SignUpPanel view;

	public SignUpController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(SignUpPanel view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String username = this.view.getUsername();
		String password = this.view.getPassword();
		this.model.signUp(username, password);
		
	}

	
}
