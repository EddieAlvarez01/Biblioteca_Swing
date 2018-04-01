package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_EditUser extends JPanel{
	JLabel lblLogin = new JLabel("Admin - Log Out");
	JLabel username = new JLabel("Username: ");
	JLabel name = new JLabel("Nombre: ");
	JLabel lastName = new JLabel("Apellido: ");
	JLabel materialLend = new JLabel("materiales prestados: ");
	JTextField usernameValue = new JTextField(20);
	JTextField nameValue = new JTextField(20);
	JTextField lastnameValue = new JTextField(20);
	JLabel materiallendValue = new JLabel();
	JButton btnCancel = new JButton("Cancelar");
	JButton btnEdit = new JButton("Aceptar");
	
	public Panel_EditUser(){
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		lblLogin.addMouseListener(lblListener);
		loginPanel.add(lblLogin, BorderLayout.EAST);
		JPanel line1 = new JPanel();
		line1.setLayout(new FlowLayout());
		line1.add(username);
		line1.add(usernameValue);
		JPanel line2 = new JPanel();
		line2.setLayout(new FlowLayout());
		line2.add(name);
		line2.add(nameValue);
		JPanel line3 = new JPanel();
		line3.setLayout(new FlowLayout());
		line3.add(lastName);
		line3.add(lastnameValue);
		JPanel line4 = new JPanel();
		line4.setLayout(new FlowLayout());
		line4.add(materialLend);
		line4.add(materiallendValue);
		JPanel btnPanel = new JPanel();
		JPanel btnLayout = new JPanel();
		btnLayout.setLayout(new FlowLayout());
		JPanel allLines = new JPanel();
		allLines.setLayout(new BoxLayout(allLines, BoxLayout.Y_AXIS));
		allLines.add(line1);
		allLines.add(line2);
		allLines.add(line3);
		allLines.add(line4);
		JPanel panelBorder = new JPanel();
		panelBorder.setLayout(new BorderLayout());
		panelBorder.add(allLines, BorderLayout.WEST);
		btnCancel.addActionListener(clickReturn);
		btnEdit.addActionListener(clickEdit);
		btnLayout.add(btnCancel);
		btnLayout.add(btnEdit);
		btnPanel.setLayout(new BorderLayout());
		btnPanel.add(btnLayout, BorderLayout.EAST);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(loginPanel);
		add(panelBorder);
		add(btnPanel);
	}
	
	MouseAdapter lblListener = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
				System_Control.session = null;
				System_Window.loginUser(null);
				JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
				CardLayout card = (CardLayout)panel.getLayout();
				card.show(panel, "panelReading");
		}
	};
	
	ActionListener clickReturn = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)btnCancel.getParent().getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "paneltableUser");
		}
		
	};
	
	public void updateLabels(String username){
		for(int i=1; i<System_Control.listaUsuario.length; i++){
			if(System_Control.listaUsuario[i] != null){
				if(System_Control.listaUsuario[i].getUsername().equals(username)){
					usernameValue.setText(username);
					nameValue.setText(System_Control.listaUsuario[i].getName());
					lastnameValue.setText(System_Control.listaUsuario[i].getLast_name());
					materiallendValue.setText(String.valueOf(System_Control.listaUsuario[i].getBorrowedBook()));
				}
			}
		}
	}
	
	ActionListener clickEdit = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = usernameValue.getText();
			boolean success = true;
			for(int i=1; i<System_Control.listaUsuario.length; i++){
				if(System_Control.listaUsuario[i] != null){
					if(System_Control.listaUsuario[i].getUsername().equals(username)){
						if(usernameValue.getText() != ""){
							if(usernameValue.getText().equals(username)){
							}else{
								Usuario user = new Usuario(username);
								if(user.usernameValidation() == true){
									System_Control.listaUsuario[i].setUsername(usernameValue.getText());
								}else{
									JOptionPane.showMessageDialog(null, "El nombre de usario que edito ya se encuentra en uso", "Error",JOptionPane.ERROR_MESSAGE);
									success = false;
									break;
								}
							}
						}
						if(nameValue.getText() != ""){
							System_Control.listaUsuario[i].setName(nameValue.getText());
						}
						if(lastnameValue.getText() != null){
							System_Control.listaUsuario[i].setLast_name(lastnameValue.getText());
						}
					}
				}
			}
			if(success == true){
				System_Window.updateUsers();
				JOptionPane.showMessageDialog(null, "Se ha editado correctamente el Usuario", "Success",JOptionPane.INFORMATION_MESSAGE);
				JPanel panel = (JPanel)btnCancel.getParent().getParent().getParent().getParent();
				CardLayout card = (CardLayout)panel.getLayout();
				card.show(panel, "paneltableUser");
			}else{
				JOptionPane.showMessageDialog(null, "Error al editar el usuario", "Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	};
	
}
