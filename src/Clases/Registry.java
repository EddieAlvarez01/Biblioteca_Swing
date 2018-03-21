package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registry extends JPanel{
	
	public Registry(){
		load_window();
	}
	
	public void load_window(){
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel tittle = new JLabel("Registrarse");
		Font auxFont = tittle.getFont();
		tittle.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		titlePanel.add(tittle);
		JPanel datosPanel = new JPanel();
		datosPanel.setLayout(new GridBagLayout());
		JTextField name = new JTextField(20);
		JTextField last_name = new JTextField(20);
		JTextField username = new JTextField(20);
		JPasswordField password = new JPasswordField(20);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		datosPanel.add(titlePanel);
		gbc.gridy++;
		datosPanel.add(name, gbc);
		gbc.gridy = 2;
		datosPanel.add(last_name, gbc);
		gbc.gridy += gbc.gridy;
		datosPanel.add(username, gbc);
		gbc.gridy += gbc.gridy;
		datosPanel.add(password, gbc);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		JButton enter = new JButton("Aceptar");
		enter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String sName = name.getText();
				String slastName = last_name.getText();
				String sUsername = username.getText();
				String sPassword = new String(password.getPassword());
				Pattern coincidence = Pattern.compile("^\\s*$");
				if(coincidence.matcher(sName).find() == true || coincidence.matcher(slastName).find() == true ||coincidence.matcher(sUsername).find() == true 
						|| coincidence.matcher(sPassword).find() == true){
					JOptionPane.showMessageDialog(null, "No puede haber campos vacios", "Error",JOptionPane.ERROR_MESSAGE);
				}else{
					Usuario user = new Usuario(sName, slastName, sUsername, sPassword);
					if (user.usernameValidation() == true){
						if (user.registerUser(user) == true){
							JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "Succes",JOptionPane.INFORMATION_MESSAGE);
							JButton button = (JButton)e.getSource();
							JPanel panel = (JPanel)button.getParent();
							JPanel panel2 = (JPanel)panel.getParent();
							JPanel panel3 = (JPanel)panel2.getParent();
							JPanel panel4 = (JPanel)panel3	.getParent();
							CardLayout card = (CardLayout)panel4.getLayout();
							card.show(panel4, "panelLogin");
							
						}else{
							JOptionPane.showMessageDialog(null, "Limite de usuarios alcanzado", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "El username ya esta en uso", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		JButton cancel = new JButton("Cancelar");
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JButton button = (JButton)e.getSource();
				JPanel panel = (JPanel)button.getParent();
				JPanel panel2 = (JPanel)panel.getParent();
				JPanel panel3 = (JPanel)panel2.getParent();
				JPanel panel4 = (JPanel)panel3	.getParent();
				CardLayout card = (CardLayout)panel4.getLayout();
				card.show(panel4, "panelLogin");
			}
			
		});
		buttonsPanel.add(enter);
		buttonsPanel.add(cancel);
		gbc.gridy += gbc.gridy;
		datosPanel.add(buttonsPanel, gbc);
		add(datosPanel, BorderLayout.CENTER);
	}
}
