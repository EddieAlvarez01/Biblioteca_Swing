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

public class Panel_EditMaterial extends JPanel{
	
	JLabel lblLogin = new JLabel("Admin - Log Out");
	JLabel id = new JLabel("ID: ");
	JLabel title = new JLabel("Titulo: ");
	JLabel atributo3 = new JLabel();
	JLabel atributo4 = new JLabel();
	JLabel atributo5 = new JLabel();
	JLabel atributo6 = new JLabel();
	JLabel atributo7 = new JLabel();
	JLabel idValue = new JLabel();
	JTextField titleValue = new JTextField(20);
	JTextField value3 = new JTextField(20);
	JTextField value4 = new JTextField(20);
	JTextField value5 = new JTextField(20);
	JTextField value6Tes = new JTextField(20);
	JLabel value6 = new JLabel();
	JLabel value7 = new JLabel();
	JButton btnCancel = new JButton("Cancelar");
	JButton btnEdit = new JButton("Aceptar");
	int index;
	String type;
	
	public Panel_EditMaterial(){
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		lblLogin.addMouseListener(lblListener);
		loginPanel.add(lblLogin, BorderLayout.EAST);
		JPanel line1 = new JPanel();
		line1.setLayout(new FlowLayout());
		line1.add(id);
		line1.add(idValue);
		JPanel line2 = new JPanel();
		line2.setLayout(new FlowLayout());
		line2.add(title);
		line2.add(titleValue);
		JPanel line3 = new JPanel();
		line3.setLayout(new FlowLayout());
		line3.add(atributo3);
		line3.add(value3);
		JPanel line4 = new JPanel();
		line4.setLayout(new FlowLayout());
		line4.add(atributo4);
		line4.add(value4);
		JPanel line5 = new JPanel();
		line5.setLayout(new FlowLayout());
		line5.add(atributo5);
		line5.add(value5);
		JPanel line6 = new JPanel();
		line6.setLayout(new FlowLayout());
		line6.add(atributo6);
		line6.add(value6);
		line6.add(value6Tes);
		JPanel line7 = new JPanel();
		line7.setLayout(new FlowLayout());
		line7.add(atributo7);
		line7.add(value7);
		JPanel btnPanel = new JPanel();
		JPanel btnLayout = new JPanel();
		btnLayout.setLayout(new FlowLayout());
		JPanel allLines = new JPanel();
		allLines.setLayout(new BoxLayout(allLines, BoxLayout.Y_AXIS));
		allLines.add(line1);
		allLines.add(line2);
		allLines.add(line3);
		allLines.add(line4);
		allLines.add(line5);
		allLines.add(line6);
		allLines.add(line7);
		JPanel panelBorder = new JPanel();
		panelBorder.setLayout(new BorderLayout());
		panelBorder.add(allLines, BorderLayout.WEST);
		btnCancel.addActionListener(clickReturn);
		btnEdit.addActionListener(clickLend);
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
	
	public void updateLabels(String id, String type){
		this.type = type;
		if(type.equals("Libro")){
			for(int i=0; i<50; i++){
				if(System_Control.listBook[i] != null){
					if(System_Control.listBook[i].getId().equals(id)){
						index = i;
						idValue.setText(id);
						titleValue.setText(System_Control.listBook[i].getTitle());
						atributo3.setText("Autor: ");
						value3.setText(System_Control.listBook[i].getAuthor());
						atributo4.setText("No. Paginas:");
						value4.setText(String.valueOf(System_Control.listBook[i].getNoPages()));
						atributo5.setText("Tema:");
						value5.setText(System_Control.listBook[i].getTheme());
						atributo6.setText("Estado:");
						value6.setVisible(true);
						value6Tes.setVisible(false);
						if(System_Control.listBook[i].isAvailable() == true){
							value6.setText("Disponible");
						}else{
							value6.setText("Prestado");
						}
					}
				}
			}
		}else if(type.equals("Revista")){
			for(int i=0; i<50; i++){
				if(System_Control.listMagazine[i] != null){
					if(System_Control.listMagazine[i].getId().equals(id)){
						index = i;
						idValue.setText(id);
						titleValue.setText(System_Control.listMagazine[i].getTitle());
						atributo3.setText("Compañia: ");
						value3.setText(System_Control.listMagazine[i].getCompany());
						atributo4.setText("Fecha :");
						value4.setText(System_Control.listMagazine[i].getDate());
						atributo5.setText("Tema:");
						value5.setText(System_Control.listMagazine[i].getTheme());
						atributo6.setText("Estado:");
						value6.setVisible(true);
						value6Tes.setVisible(false);
						if(System_Control.listMagazine[i].isAvailable() == true){
							value6.setText("Disponible");
						}else{
							value6.setText("Prestado");
						}
					}
				}
			}
		}else{
			for(int i=0; i<50; i++){
				if(System_Control.listThesis[i] != null){
					if(System_Control.listThesis[i].getId().equals(id)){
						index = i;
						idValue.setText(id);
						titleValue.setText(System_Control.listThesis[i].getTitle());
						atributo3.setText("Autor: ");
						value3.setText(System_Control.listThesis[i].getAuthor());
						atributo4.setText("Grado :");
						value4.setText(System_Control.listThesis[i].getGrade());
						atributo5.setText("Tema:");
						value5.setText(System_Control.listThesis[i].getTheme());
						atributo6.setText("Año:");
						value6.setVisible(false);
						value6Tes.setVisible(true);
						value6Tes.setText(String.valueOf(System_Control.listThesis[i].getYear()));
						atributo7.setText("Estado:");
						if(System_Control.listThesis[i].isAvailable() == true){
							value7.setText("Disponible");
						}else{
							value7.setText("Prestado");
						}
					}
				}
			}
		}
	}
	
	ActionListener clickReturn = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)btnCancel.getParent().getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelAdmin");
		}
		
	};
	
	ActionListener clickLend = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			String id = idValue.getText();
			if(type.equals("Libro")){
				for(int i=0; i<System_Control.listBook.length;i++){
					if(System_Control.listBook[i] != null){
						if(System_Control.listBook[i].getId().equals(id)){
							if(titleValue.getText() != ""){
								System_Control.listBook[i].setTitle(titleValue.getText());
							}
							if(value3.getText() != ""){
								System_Control.listBook[i].setAuthor(value3.getText());
							}
							if(value4.getText() != ""){
								System_Control.listBook[i].setNoPages(Integer.valueOf(value4.getText()));
							}
							if(value5.getText() != ""){
								System_Control.listBook[i].setTheme(value5.getText());
							}
						}
					}
				}
			}else if(type.equals("Revista")){
				for(int i=0; i<System_Control.listMagazine.length;i++){
					if(System_Control.listMagazine[i] != null){
						if(System_Control.listMagazine[i].getId().equals(id)){
							if(titleValue.getText() != ""){
								System_Control.listMagazine[i].setTitle(titleValue.getText());
							}
							if(value3.getText() != ""){
								System_Control.listMagazine[i].setCompany(value3.getText());
							}
							if(value4.getText() != ""){
								System_Control.listMagazine[i].setDate(value4.getText());
							}
							if(value5.getText() != ""){
								System_Control.listMagazine[i].setTheme(value5.getText());
							}
						}
					}
				}
			}else{
				for(int i=0; i<System_Control.listThesis.length;i++){
					if(System_Control.listThesis[i] != null){
						if(System_Control.listThesis[i].getId().equals(id)){
							if(titleValue.getText() != ""){
								System_Control.listThesis[i].setTitle(titleValue.getText());
							}
							if(value3.getText() != ""){
								System_Control.listThesis[i].setAuthor(value3.getText());
							}
							if(value4.getText() != ""){
								System_Control.listThesis[i].setGrade(value4.getText());
							}
							if(value5.getText() != ""){
								System_Control.listThesis[i].setTheme(value5.getText());
							}
							if(value6Tes.getText() != ""){
								System_Control.listThesis[i].setYear(Integer.valueOf(value6Tes.getText()));
							}
						}
					}
				}
			}
			System_Window.updateTable();
			System_Window.updateUsers();
			JOptionPane.showMessageDialog(null, "Se ha editado correctamente el material", "Success",JOptionPane.INFORMATION_MESSAGE);
			JPanel panel = (JPanel)btnCancel.getParent().getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelAdmin");
		}
		
	};
	
}
