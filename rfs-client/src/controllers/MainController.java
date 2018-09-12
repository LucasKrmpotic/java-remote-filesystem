package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import client.RFSClient;
import ui.ConnectionPanel;
import ui.LoginPanel;
import ui.MainUI;
import ui.RemoteFilesPanel;
import ui.SignUpPanel;

public class MainController implements ActionListener{
	
	MainUI ui; 
	RFSClient client_model;
	
	
	public MainController() {}
	
	public void setModel(RFSClient model) {
		this.client_model = model;
	}
	
	public RFSClient getModel() {
		return this.client_model;
	}
	
	public void setUI(MainUI view) {
		this.ui = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String methodName = e.getActionCommand();
		
        Method method;
		try {
			method = this.getClass().getMethod(methodName);
			method.invoke(this);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void showConnectionPanel() {
		ConnectionController controller = new ConnectionController(this.getModel());
		ConnectionPanel connectionPanel = new ConnectionPanel(controller);
		controller.setView(connectionPanel);
		
		this.ui.getContentPanel().removeAll();
		this.ui.getContentPanel().add(connectionPanel, "cell 1 0,grow");
		this.ui.getContentPanel().revalidate();
	}
	
	public void showSignUpPanel() {
		SignUpController controller = new SignUpController(this.getModel());
		SignUpPanel signUpPanel = new SignUpPanel(controller);
		controller.setView(signUpPanel);
		
		this.ui.getContentPanel().removeAll();
		this.ui.getContentPanel().add(signUpPanel, "cell 1 0,grow");
		this.ui.getContentPanel().revalidate();
	}

	public void showLoginPanel() {
		
		LoginController controller = new LoginController(this.getModel());
		LoginPanel loginPanel = new LoginPanel(controller);
		controller.setView(loginPanel);
		
		this.ui.getContentPanel().removeAll();
		this.ui.getContentPanel().add(loginPanel, "cell 1 0,grow");
		this.ui.getContentPanel().revalidate();
	}
	
	
	public void showRemoteFilesPanel() {
		RemoteFilesController remoteFilesController = new RemoteFilesController(this.getModel());
		RemoteFilesPanel filesPanel = new RemoteFilesPanel(remoteFilesController); 
		remoteFilesController.setView(filesPanel);
		
		this.ui.getContentPanel().removeAll();
		this.ui.getContentPanel().add(filesPanel, "cell 1 0,grow");
		this.ui.getContentPanel().revalidate();
		
	}
	
	//Agregado del boton ERROR
	public void botonError() {
		JOptionPane.showMessageDialog(null, "Hola... Soy un ERROR ");
	}
	
}
