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
import javax.swing.JLabel;
import javax.swing.JButton;

public class MainUI {

	public JFrame frame;
	private JPanel content;	
	public MainController controller;

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
		
		JPanel contenedor = new JPanel();
		frame.getContentPane().add(contenedor, "cell 0 0,grow");
		contenedor.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JPanel panelNorth = new JPanel();
		contenedor.add(panelNorth, "cell 0 0,grow");
		
		JPanel panelSouth = new JPanel();
		contenedor.add(panelSouth, "cell 0 1,grow");
		
		
		
				
	}

	public JPanel getContentPanel() {
		return this.content;
	}
}














