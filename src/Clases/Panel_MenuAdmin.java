package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel_MenuAdmin extends JPanel{
	
	JLabel lblLogin = new JLabel("admin - Log Out");
	JButton btnMaterials = new JButton("Materiales de Lectura");
	JButton btnReport = new JButton("Reportes");
	
	public Panel_MenuAdmin(){
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
		btnReport.addActionListener(clickReport);
		buttonPanel.add(btnMaterials);
		buttonPanel.add(btnReport);
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
	
	ActionListener clickMaterial = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelAdmin");
		}
		
	};
	
	ActionListener clickReport = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			//card.show(panel, "panelLend");
		}
		
	};
	
}