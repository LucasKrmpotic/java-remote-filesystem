package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import client.RFSClient;
import ui.LoginPanel;


public class LoginController implements ActionListener {
	
	private RFSClient model;
	private LoginPanel view;

	public LoginController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(LoginPanel view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String username = this.view.getUsername();
		String password = this.view.getPassword();
		System.out.println(username);
		System.out.println(password);
		try {
			this.model.login(username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
