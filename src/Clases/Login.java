package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel{
	
	JButton enter = new JButton("Entrar");
	JButton register = new JButton("Registro");
	JTextField name = new JTextField(10);
	JPasswordField password = new JPasswordField(10);
	
	public Login(){
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel tittle = new JLabel("Iniciar sesion");
		Font auxFont = tittle.getFont();
		tittle.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		titlePanel.add(tittle);
		JPanel datosPanel = new JPanel();
		datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
		datosPanel.add(titlePanel);
		datosPanel.add(name);
		datosPanel.add(password);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
				JPanel panel = (JPanel)button.getParent();
				JPanel panel2 = (JPanel)panel.getParent();
				JPanel panel3 = (JPanel)panel2.getParent();
				JPanel panel4 = (JPanel)panel3	.getParent();
				CardLayout card = (CardLayout)panel4.getLayout();
				card.show(panel4, "registryPanel");
			}
	
		});
		enter.addActionListener(login);
		buttonsPanel.add(enter);
		buttonsPanel.add(register);
		datosPanel.add(buttonsPanel);
		add(datosPanel);
		
	}
	
	public String Reference(String reference){
		return reference;
	}
	
	ActionListener login = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String username = name.getText();
			String pass = new String(password.getPassword());
			if(System_Control.validateUsername(username, pass) == true){
				JPanel panel = (JPanel)enter.getParent().getParent().getParent().getParent();
				CardLayout card = (CardLayout)panel.getLayout();
				if(username.equals("admin")){
					card.show(panel, "panelAdmin");
				}else{
					System_Window.loginUser(username);
					card.show(panel, "panelReading");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Credenciales Incorrectos", "Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	};
}
