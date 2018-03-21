package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class System_Window extends JFrame{
	
	CardLayout card = new CardLayout();
	JPanel panel = new JPanel();
	
	public System_Window(){
		panel.setLayout(card);
		Login loginPanel = new Login();
		Registry registryPanel = new Registry();
		panel.add(loginPanel, "panelLogin");
		panel.add(registryPanel, "registryPanel");
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void controlCard(String reference){
		card.show(panel, reference);
	}
	
}
