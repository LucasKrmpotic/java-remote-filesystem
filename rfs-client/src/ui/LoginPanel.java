package ui;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel{
	
	private JLabel username;
	private JTextField textField_2;
	private JLabel lblPassword;
	private JTextField textField_3;
	private JButton btnConectar_1;
	
	public LoginPanel () {
		this.setVisible(true);
		this.setLayout(new MigLayout("", "[][grow]", "[grow 40,fill][][][][grow]"));
		this.username = new JLabel("Username");
		username.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(username, "cell 0 1,alignx trailing");
		
		this.textField_2 = new JTextField();
		this.textField_2.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(textField_2, "cell 1 1,growx");
		this.textField_2.setColumns(10);
		
		this.lblPassword = new JLabel("Password");
		this.lblPassword.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(lblPassword, "cell 0 2,alignx trailing");
		
		this.textField_3 = new JTextField();
		this.textField_3.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(textField_3, "cell 1 2,growx");
		this.textField_3.setColumns(10);
		
		this.btnConectar_1 = new JButton("Login");
		this.btnConectar_1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
		this.btnConectar_1.setIcon(new ImageIcon(MainUI.class.getResource("/ui/icons/login.png")));
		this.add(btnConectar_1, "cell 1 3");
	}
	
}
