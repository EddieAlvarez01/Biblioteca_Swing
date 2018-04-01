package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel_MenuUser extends JPanel{
	
	JLabel lblLogin = new JLabel(System_Control.session+" "+ "-" + " " +"Log Out");
	JButton btnMaterials = new JButton("Materiales de Lectura");
	JButton btnLend = new JButton("Mis préstamos");
	
	public Panel_MenuUser(){
		JPanel loginPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel tittle = new JLabel("Menú");
		Font auxFont = tittle.getFont();
		tittle.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		titlePanel.add(tittle);
		loginPanel.setLayout(new BorderLayout());
		lblLogin.addMouseListener(lblListener);
		loginPanel.add(lblLogin, BorderLayout.EAST);
		loginPanel.add(titlePanel,BorderLayout.SOUTH);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		setLayout(new BorderLayout());
		btnMaterials.addActionListener(clickMaterial);
		btnLend.addActionListener(clickLend);
		buttonPanel.add(btnMaterials);
		buttonPanel.add(btnLend);
		add(loginPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
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
	
	public void updateLbl(){
		lblLogin.setText(System_Control.session+" "+ "-" + " " +"Log Out");
	}
	
	ActionListener clickMaterial = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelReading");
		}
		
	};
	
	ActionListener clickLend = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelLend");
		}
		
	};
	
}
