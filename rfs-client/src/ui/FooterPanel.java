package ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class FooterPanel extends JPanel{
	
	private JLabel lblEstadoDeLa;
	private JTextField txtConectado;
	private JTextField txtAutenticado;
	public FooterPanel () {
		
		this.setBackground(SystemColor.window);
		this.setLayout(new MigLayout("fill", "[grow][][][][][][grow]", "[50px:50px:200px,grow 100][][100px:100px:200px,grow 100]"));
		this.lblEstadoDeLa = new JLabel("Estado de la conexión");
		this.lblEstadoDeLa.setBounds(0, 0, 220, 24);
		this.add(lblEstadoDeLa, "cell 1 1");
		this.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		
		this.txtConectado = new JTextField();
		this.add(txtConectado, "cell 3 1");
		this.txtConectado.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.txtConectado.setText("conectado");
		this.txtConectado.setColumns(10);
		
		this.txtAutenticado = new JTextField();
		this.add(txtAutenticado, "cell 5 1");
		this.txtAutenticado.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.txtAutenticado.setText("autenticado");
		this.txtAutenticado.setColumns(10);
	}
}
