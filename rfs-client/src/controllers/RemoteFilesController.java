package controllers;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.RFSClient;
import net.miginfocom.swing.MigLayout;
import ui.RemoteFilesPanel;
import ui.SingleRemoteFilePanel;
import remoteobjects.FileMetadata;

public class RemoteFilesController implements ActionListener{

	RemoteFilesPanel view;
	RFSClient model;
	List<FileMetadata> files = new ArrayList<FileMetadata>();
	
	public RemoteFilesController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(RemoteFilesPanel view) {
		this.view = view;
	}
	public RFSClient getModel() {
		return this.model;
	}
	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		FileMetadata file = (FileMetadata) this.view.getFile(e.getActionCommand());
		
		SingleRemoteFileController controller = new SingleRemoteFileController(this.getModel());
		controller.setView(
			new SingleRemoteFilePanel(new JFrame(), true, file, controller)
		);
     }
	
//	public List<FileMetadata> getAvailableFiles(){
//		return this.model.getAvailableFiles();
//	}
	
	public List<FileMetadata> getAvailableFiles(){
//		String dir = "src/controllers/";
//		this.files.add(new FileMetadata(new File(dir+"arch.txt"), "arch.txt"));
//		this.files.add(new FileMetadata(new File(dir+"curriculum-sin-cert.pdf"), "curriculum-sin-cert.pdf"));
//		this.files.add(new FileMetadata(new File(dir+"inscripciones.xlsx"),"inscripciones.xlsx"));
//		this.files.add(new FileMetadata(new File(dir+"martin-fierro.jpg"), "martin-fierro.jpg"));
//		this.files.add(new FileMetadata(new File(dir+"TP1-SD.docx"),"TP1-SD.docx"));
//		
//		return files;
		
		return this.getModel().getAvailableFiles();
		
	}
}

@SuppressWarnings("serial") class Dialogz extends JDialog{
	JButton close = new JButton("close");
	
	public Dialogz(JFrame owner,boolean modal, String command) {
		super(owner, modal);
		setSize(800, 400);
		System.out.println(this.getModalityType());
		close.setText(command);
		add(close);
		setLocationRelativeTo(owner);
		setVisible(true);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				closez();
			}
		});
	} 
	
	void closez(){
		setModal(false);
		this.dispose();
		System.out.println("Method Done");
		
	}
	
}