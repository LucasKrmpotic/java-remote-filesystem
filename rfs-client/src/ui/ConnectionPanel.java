package ui;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ConnectionPanel extends JPanel {
	
	public JLabel IP;
	public JTextField textField;
	public JTextField textField_1;
	public JButton btnConectar_1;
	public JLabel lblPuerto;
	
	public ConnectionPanel() {
		this.setVisible(true);
		this.setLayout(new MigLayout("", "[][grow]", "[grow 40,fill][][][][grow]"));
		this.IP = new JLabel("IP");
		this.textField = new JTextField();
		this.lblPuerto = new JLabel("Puerto");
		this.textField_1 = new JTextField();
		this.btnConectar_1 = new JButton("Conectar");
		this.IP.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(IP, "cell 0 1");
		
		
		this.textField.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(textField, "cell 1 1,growx");
		this.textField.setColumns(10);
		
		
		this.lblPuerto.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(lblPuerto, "cell 0 2,alignx trailing");
		
		
		this.textField_1.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(textField_1, "cell 1 2,growx");
		this.textField_1.setColumns(10);
		
		
		this.btnConectar_1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
		this.btnConectar_1.setIcon(new ImageIcon(MainUI.class.getResource("/ui/icons/login.png")));
		this.add(btnConectar_1, "cell 1 3");
	}
}


