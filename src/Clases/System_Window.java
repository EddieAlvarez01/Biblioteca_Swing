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
	static Panel_ReadingMaterialAdmin panelAdmin = new Panel_ReadingMaterialAdmin();
	CargaMasiva charge = new CargaMasiva();
	static Panel_UserAdministration paneltableUser = new Panel_UserAdministration();
	static Panel_Ver panelView = new Panel_Ver();
	static Panel_Lend panelLend = new Panel_Lend();
	static Panel_MenuUser menuUser = new Panel_MenuUser();
	Panel_MenuAdmin menuAdmin = new Panel_MenuAdmin();
	static Panel_EditMaterial panelEdit = new Panel_EditMaterial();
	static Panel_EditUser panelEditUser = new Panel_EditUser();
	
	public System_Window(){
		panel.setLayout(card);
		panel.add(loginPanel, "panelLogin");
		panel.add(registryPanel, "registryPanel");
		panel.add(panelReading, "panelReading");
		panel.add(panelAdmin, "panelAdmin");
		panel.add(charge, "panelCharge");
		panel.add(paneltableUser, "paneltableUser");
		panel.add(panelView, "panelView");
		panel.add(panelLend, "panelLend");
		panel.add(menuUser, "menuUser");
		panel.add(menuAdmin, "menuAdmin");
		panel.add(panelEdit, "panelEdit");
		panel.add(panelEditUser, "EditUser");
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
		String borrowed[] = new String[1];
		Usuario admin = new Usuario("admin", "admin", "admin", "admin", 0, borrowed);
		System_Control.listaUsuario[0] = admin;
	}
	
	public static void loginUser(String username){
		if(username != null){
			panelReading.lblLogin.setText(username + " " + "-"+ " " + "Log Out");
			panelReading.btnBack.setVisible(true);
		}else{
			panelReading.lblLogin.setText("Login");
			panelReading.btnBack.setVisible(false);
		}
		
	} 
	
	public static void updateTable(){
		panelAdmin.fillTable();
		panelReading.fillTable();
		panelLend.fillTable();
	}
	
	public static void updateUsers(){
		paneltableUser.fillTable();
	}
	
	public static void updateLabels(String id, String type){
		panelView.updateLabels(id, type);
		panelView.updatelblLogin();
	}
	
	public static void updateEdit(String id, String type){
		panelEdit.updateLabels(id, type);
	}
	
	public static void updateLblMenu(){
		menuUser.updateLbl();
		panelLend.lblLogin.setText(System_Control.session+" "+ "-" + " " +"Log Out");
	}
	
	public static void updateEditUser(String username){
		panelEditUser.updateLabels(username);
	}
}
