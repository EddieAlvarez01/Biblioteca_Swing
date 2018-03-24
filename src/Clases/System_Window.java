package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class System_Window extends JFrame{
	
	CardLayout card = new CardLayout();
	static JPanel panel = new JPanel();
	Login loginPanel = new Login();
	Registry registryPanel = new Registry();
	static Panel_ReadingMaterial panelReading = new Panel_ReadingMaterial();
	Panel_ReadingMaterialAdmin panelAdmin = new Panel_ReadingMaterialAdmin();
	CargaMasiva charge = new CargaMasiva();
	
	public System_Window(){
		panel.setLayout(card);
		panel.add(loginPanel, "panelLogin");
		panel.add(registryPanel, "registryPanel");
		panel.add(panelReading, "panelReading");
		panel.add(panelAdmin, "panelAdmin");
		panel.add(charge, "panelCharge");
		getContentPane().add(panel, BorderLayout.CENTER);
		card.show(panel, "panelReading");
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addAdmin();
	}
	
	/*public void controlCard(String reference){
		card.show(panel, reference);
	}*/
	public void addAdmin(){
		Usuario admin = new Usuario("admin", "admin", "admin", "admin");
		System_Control.listaUsuario[0] = admin;
	}
	
	public static void loginUser(String username){
		panelReading.lblLogin.setText(username + " " + "-" + "Log Out");
	} 
}
