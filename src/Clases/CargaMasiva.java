package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CargaMasiva extends JPanel {
	
	JTextArea txtCharge = new JTextArea();
	JButton btnCharge = new JButton("Cargar Material");
	
	public CargaMasiva(){
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel tittle = new JLabel("Carga Masiva");
		Font auxFont = tittle.getFont();
		tittle.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		titlePanel.add(tittle); 
		JPanel paneText = new JPanel();
		paneText.setLayout(new BorderLayout());
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(txtCharge);
		paneText.add(pane, BorderLayout.CENTER);
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BorderLayout());
		JPanel border = new JPanel();
		border.setLayout(new FlowLayout());
		btnCharge.addActionListener(chargeMaterial);
		border.add(btnCharge);
		btnPanel.add(border, BorderLayout.EAST);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(titlePanel);
		add(paneText);
		add(btnPanel);
	}
	
	ActionListener chargeMaterial = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			String chain =  txtCharge.getText();
			boolean fullBook = true;
			boolean fullMagazine = true;
			boolean fullThesis = true;
			boolean fullUser = true;
			StringTokenizer delimiterlineBreak = new StringTokenizer(chain, "\n");
			while(delimiterlineBreak.hasMoreTokens()){
				String token = delimiterlineBreak.nextToken();
				if(token.substring(0, 1).equals("0")){
					if(fullBook == true){
					StringTokenizer delimiterPipe = new StringTokenizer(token, "|");
						while(delimiterPipe.hasMoreTokens()){
							String quees = delimiterPipe.nextToken();
							String id;
							if(System_Control.counterBook < 10){
								 id = "LIB-0" + System_Control.counterBook;
							}else{
								 id = "LIB-" + System_Control.counterBook;
							}
							System_Control.counterBook++;
							String title = delimiterPipe.nextToken();
							String author = delimiterPipe.nextToken();
							String theme = delimiterPipe.nextToken();
							Integer noPages = Integer.valueOf(delimiterPipe.nextToken()).intValue();
							boolean available = true;
							Book book = new Book(id, title, author, theme, noPages, available);
							if(System_Control.addBook(book) == false){
								fullBook = false;
								System_Control.counterBook--;
								JOptionPane.showMessageDialog(null, "Los libros a partir de el libro con el titulo " + title + " " + "y autor" + author + " " +
										"no se pueden registrar porque ha excedido el limite de libros permitidos", "Atencion",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}else if(token.substring(0, 1).equals("1")){
					if(fullMagazine == true){
						StringTokenizer delimiterPipe = new StringTokenizer(token, "|");
						while(delimiterPipe.hasMoreTokens()){
							delimiterPipe.nextToken();
							String id;
							if(System_Control.counterMagazine < 10){
								 id = "REV-0" + System_Control.counterMagazine;
							}else{
								 id = "REV-" + System_Control.counterMagazine;
							}
							 System_Control.counterMagazine++;
							 String title = delimiterPipe.nextToken();
							 String company = delimiterPipe.nextToken();
							 String date = delimiterPipe.nextToken();
							 String theme = delimiterPipe.nextToken();
							 boolean available = true;
							 Magazine magazine = new Magazine(id, title, company, date, theme, available);
							 if(System_Control.addMagazine(magazine) == false){
								 fullMagazine = false;
								 System_Control.counterMagazine--;
								 JOptionPane.showMessageDialog(null, "Las revistas a partir de la revista con el titulo " + title + " " + "y compañia" + company + " " +
											"no se pueden registrar porque ha excedido el limite de revistas permitidas", "Atencion",JOptionPane.INFORMATION_MESSAGE);
							 }	
						}
					}
				}else if(token.substring(0, 1).equals("2")){
					if(fullThesis == true){
						StringTokenizer delimiterPipe = new StringTokenizer(token, "|");
						while(delimiterPipe.hasMoreTokens()){
							delimiterPipe.nextToken();
							String id;
							if(System_Control.counterThesis < 10){
								 id = "TES-0" + System_Control.counterThesis;
							}else{
								 id = "TES-" + System_Control.counterThesis;
							}
							System_Control.counterThesis++;
							String title = delimiterPipe.nextToken();
							String author = delimiterPipe.nextToken();
							String theme = delimiterPipe.nextToken();
							String grade = delimiterPipe.nextToken();
							Integer year = Integer.valueOf(delimiterPipe.nextToken()).intValue();
							boolean available = true;
							Thesis thesis = new Thesis(id, title, author, grade, theme, year, available);
							if(System_Control.addThesis(thesis) == false){
								fullThesis = false;
								System_Control.counterThesis--;
								JOptionPane.showMessageDialog(null, "Las tesis a partir de la tesis con el titulo " + title + " " + "y autor" + author + " " +
										"no se pueden registrar porque ha excedido el limite de tesis permitidos", "Atencion",JOptionPane.INFORMATION_MESSAGE);
							 }	
						}
					}
				}else{
					if(fullUser == true){
						StringTokenizer delimiterPipe = new StringTokenizer(token, "|");
						while(delimiterPipe.hasMoreTokens()){
							delimiterPipe.nextToken();
							String username = delimiterPipe.nextToken();
							String name = delimiterPipe.nextToken();
							String lastName = delimiterPipe.nextToken();
							String password = delimiterPipe.nextToken();
							String borrowed[] = new String[150];
							Usuario user = new Usuario(name, lastName, username, password, 0, borrowed);
							if(user.usernameValidation() == false){
								JOptionPane.showMessageDialog(null, "El usuario con nombre " + name + " " + "Intenta registrarse con el siguiente username:"
										+ " " + username + " " + ", este ya se encuentra en uso, por lo tanto no se añadira", "Error",JOptionPane.ERROR_MESSAGE);
							}else{
								if(user.registerUser(user) == false){
									fullUser = false;
									JOptionPane.showMessageDialog(null, "Los usuarios a partir de el usario" + name + " " + lastName + " " +
									"no se pueden registrar porque ha excedido el limite de usuarios permitidos", "Atencion",JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, "La carga masiva ha concluido exitosamente", "Succes",JOptionPane.INFORMATION_MESSAGE);
			JPanel panel = (JPanel)btnCharge.getParent().getParent().getParent().getParent();
			CardLayout layout = (CardLayout)panel.getLayout();
			System_Window.updateTable();
			System_Window.updateUsers();
			layout.show(panel, "panelAdmin");
		}
		
	};
	
}
