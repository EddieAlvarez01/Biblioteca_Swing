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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Panel_Lend extends JPanel{
	
	JLabel lblLogin = new JLabel(System_Control.session+" "+ "-" + " " +"Log Out");
	JButton btnReturn = new JButton("Regresar");
	JButton btngiveBack = new JButton("Devolver");
	JTable tableLend = new JTable();
	Object row[][];
	int indexUser;
	
	public Panel_Lend(){
		JPanel loginPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel tittle = new JLabel("Mis préstamos");
		Font auxFont = tittle.getFont();
		tittle.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		titlePanel.add(tittle);
		loginPanel.setLayout(new BorderLayout());
		lblLogin.addMouseListener(lblListener);
		loginPanel.add(lblLogin, BorderLayout.EAST);
		loginPanel.add(titlePanel,BorderLayout.SOUTH);
		JScrollPane tablePanel = new JScrollPane();
		tableLend.setDefaultRenderer(Object.class, new Render());
		fillTable();
		tableLend.addMouseListener(btnClicked);
		tablePanel.setViewportView(tableLend);
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BorderLayout());
		btnReturn.addActionListener(clickReturn);
		panelButton.add(btnReturn, BorderLayout.EAST);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(loginPanel);
		add(tablePanel);
		add(panelButton);
	}
	
	public void fillTable(){
		int counter = 0;
		counter = counterLend(counter);
		row = new Object[counter][5];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				for(int k=0; k<150; k++){
					if(System_Control.listaUsuario[indexUser].getBorrowed()[k] != null){
						row[i][j] = System_Control.listaUsuario[indexUser].getBorrowed()[k];
						j++;
						String type = typeSearch(System_Control.listaUsuario[indexUser].getBorrowed()[k]);
						if(type.equals("Libro")){
							for(int l=0; l<50; l++){
								if(System_Control.listBook[l] != null){
									if(System_Control.listBook[l].getId().equals(System_Control.listaUsuario[indexUser].getBorrowed()[k])){
										row [i][j] = System_Control.listBook[l].getTitle();
										j++;
										row[i][j] = System_Control.listBook[l].getAuthor();
										j++;
										row[i][j] = type;
										j++;
										row[i][j] = btngiveBack;
										i++;
										j = 0;
									}
								}
							}
						}else if(type.equals("Revista")){
							for(int l=0; l<50; l++){
								if(System_Control.listMagazine[l] != null){
									if(System_Control.listMagazine[l].getId().equals(System_Control.listaUsuario[indexUser].getBorrowed()[k])){
										row [i][j] = System_Control.listMagazine[l].getTitle();
										j++;
										row[i][j] = System_Control.listMagazine[l].getCompany();
										j++;
										row[i][j] = type;
										j++;
										row[i][j] = btngiveBack;
										i++;
										j = 0;
									}
								}
							}
							break;
						}else{
							for(int l=0; l<50; l++){
								if(System_Control.listThesis[l] != null){
									if(System_Control.listThesis[l].getId().equals(System_Control.listaUsuario[indexUser].getBorrowed()[k])){
										row [i][j] = System_Control.listThesis[l].getTitle();
										j++;
										row[i][j] = System_Control.listThesis[l].getAuthor();
										j++;
										row[i][j] = type;
										j++;
										row[i][j] = btngiveBack;
										i++;
										j = 0;
									}
								}
							}
						}
					}
				}
				break;
			}
		}
		String columnTableLend[] = new String[]{"ID", "Título", "Autor", "Tipo", "Accion"};
		DefaultTableModel dtm = new DefaultTableModel(row, columnTableLend){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tableLend.setModel(dtm);
	}
	
	public  int counterLend(int counter){
		for(int i=1; i<System_Control.listaUsuario.length; i++){
			if(System_Control.listaUsuario[i] != null){
				if(System_Control.session.equals(System_Control.listaUsuario[i].getUsername())){
					indexUser = i;
					for(int j=0; j<System_Control.listaUsuario[i].getBorrowed().length; j++){
						if(System_Control.listaUsuario[i].getBorrowed()[j] != null){
							counter++;
						}
					}
				}
			}
		}
		return counter;
	}
	
	public String typeSearch(String id){
		for(int i=0; i<50; i++){
			if(System_Control.listBook[i] != null){
				if(System_Control.listBook[i].getId().equals(id)){
					return "Libro";
				}
			}
			if(System_Control.listMagazine[i] != null){
				if(System_Control.listMagazine[i].getId().equals(id)){
					return "Revista";
				}
			}
			if(System_Control.listThesis[i] != null){
				if(System_Control.listThesis[i].getId().equals(id)){
					return "Tesis";
				}
			}	
		}
		return "";
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
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "menuUser");
		}
		
	};
	
	MouseAdapter btnClicked = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			int column = tableLend.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY()/tableLend.getRowHeight();
			if(row < tableLend.getRowCount() && row >= 0 && column < tableLend.getColumnCount() && column >= 0){
				Object value = tableLend.getValueAt(row, column);
				if(value instanceof JButton){
					((JButton)value).doClick();
					JButton btn = (JButton)value;
					if(btn == btngiveBack){
						String id = (String) tableLend.getValueAt(row, 0);
						String type = (String) tableLend.getValueAt(row, 3);
						if(type.equals("Libro")){
							for(int i=0; i<System_Control.listBook.length; i++){
								if(System_Control.listBook[i] != null){
									if(System_Control.listBook[i].getId().equals(id)){
										System_Control.listBook[i].setAvailable(true);
										break;
									}
								}
							}
						}else if(type.equals("Revista")){
							for(int i=0; i<System_Control.listMagazine.length; i++){
								if(System_Control.listMagazine[i] != null){
									if(System_Control.listMagazine[i].getId().equals(id)){
										System_Control.listMagazine[i].setAvailable(true);
										break;
									}
								}
							}
						}else{
							for(int i=0; i<System_Control.listThesis.length; i++){
								if(System_Control.listThesis[i] != null){
									if(System_Control.listThesis[i].getId().equals(id)){
										System_Control.listThesis[i].setAvailable(true);
										break;
									}
								}
							}
						}
						System_Control.listaUsuario[indexUser].setBorrowedBook(System_Control.listaUsuario[indexUser].getBorrowedBook() - 1);
						for(int i=0; i<System_Control.listaUsuario[indexUser].getBorrowed().length; i++){
							if(System_Control.listaUsuario[indexUser].getBorrowed()[i] != null){
								if(System_Control.listaUsuario[indexUser].getBorrowed()[i].equals(id)){
									System_Control.listaUsuario[indexUser].getBorrowed()[i] = null;
									break;
								}
							}
						}
						JOptionPane.showMessageDialog(null, "Se ha devuelto correctamente el "+type, "Success",JOptionPane.INFORMATION_MESSAGE);
						System_Window.updateTable();
						System_Window.updateUsers();
					}
				}
			}
		}
	};
	
}