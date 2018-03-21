package Clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		JButton register = new JButton("Cancelar");
		buttonsPanel.add(enter);
		buttonsPanel.add(register);
		gbc.gridy += gbc.gridy;
		datosPanel.add(buttonsPanel, gbc);
		add(datosPanel, BorderLayout.CENTER);
	}
	
}
