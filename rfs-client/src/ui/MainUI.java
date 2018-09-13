package ui;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import controllers.MainController;
import controllers.RemoteFilesController;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MainUI {

	public JFrame frame;
	private JPanel content;
	private JPanel contenedor;
	public MainController controller;
	public JTextField txtFiletext;
	private JButton btnTransferirArchivos;
	
	public JTextField gettxtFiletext() {
		return this.txtFiletext;
	}

	/**
	 * Create the application.
	 */
	public MainUI(MainController controller) {
		this.controller = controller; 
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/ui/icons/3d-printing-software(4).png")));
		frame.setType(Type.UTILITY);
		frame.setTitle("Sistemas Distribuidos 2018 - Águila, Krmpotic");
		frame.getContentPane().setFont(new Font("DejaVu Sans Condensed", Font.PLAIN, 20));
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("fillx", "[grow,right]", "[grow]"));

		
		HeaderPanel header = new HeaderPanel();
		header.brand.setFont(new Font("DejaVu Sans", Font.BOLD, 24));
		frame.getContentPane().add(header, "dock north");		
		
		// Añadimos el sidebar
		SidebarPanel sidebar = new SidebarPanel(this.controller);
		frame.getContentPane().add(sidebar, "dock west");
		
		
		content = new JPanel();
		frame.getContentPane().add(content, "cell 0 0,grow");
		content.setLayout(new MigLayout("", "[1px:1px:1px][grow][1px:1px:1px]", "[grow]"));
		
		contenedor = new JPanel();
		content.add(contenedor, "cell 1 0,grow");
		contenedor.setLayout(new MigLayout("", "[1px:1px:1px][grow][1px:1px:1px]", "[grow][grow]"));
		
		RemoteFilesController remoteFilesController = new RemoteFilesController(this.controller.getModel());
		
		JPanel rmtPanel = new JPanel();
		contenedor.add(rmtPanel, "cell 1 0,grow");
		rmtPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ARCHIVOS REMOTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), new java.awt.Color(0, 0, 0)));
        
		txtFiletext = new JTextField();
		txtFiletext.setText("file_text");
		rmtPanel.add(txtFiletext);
		txtFiletext.setColumns(10);
		
		this.btnTransferirArchivos = new JButton("Transferir Archivos");
		rmtPanel.add(btnTransferirArchivos);
		btnTransferirArchivos.addActionListener(controller);
		btnTransferirArchivos.setActionCommand("showJFC");
		
		RemoteFilesPanel filesPanel = new RemoteFilesPanel(remoteFilesController); 
		remoteFilesController.setView(filesPanel);
		contenedor.add(filesPanel, "cell 1 1,grow");

		FooterPanel footer = new FooterPanel();
		frame.getContentPane().add(footer, "south,aligny center");
				
	}

	public JPanel getContentPanel() {
		return this.content;
	}
}














