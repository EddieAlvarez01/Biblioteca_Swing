package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Panel_ReadingMaterialAdmin extends JPanel{
	JTextField txtSearch = new JTextField(10);
	JButton btnSearch = new JButton("Buscar");
	JComboBox cmbFilter =  new JComboBox();
	JLabel lblLogin = new JLabel("admin - Log Out");
	JTable tableMaterials = new JTable();
	JButton btnManage = new JButton("Administrar Usuarios");
	JButton btnCharge = new JButton("Cargar Material");
	JPanel panelBorder = new JPanel();
	Object row[][];
	JButton btnEdit = new JButton("Editar");
	JButton btnDelete = new JButton("Eliminar");
	
	
	public Panel_ReadingMaterialAdmin(){
		JPanel loginPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel tittle = new JLabel("Material de Lectura");
		Font auxFont = tittle.getFont();
		tittle.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		titlePanel.add(tittle);
		loginPanel.setLayout(new BorderLayout());
		lblLogin.addMouseListener(lblListener);
		loginPanel.add(lblLogin, BorderLayout.EAST);
		loginPanel.add(titlePanel,BorderLayout.SOUTH);
		JPanel search_filterPanel = new JPanel();
		search_filterPanel.setLayout(new BorderLayout());
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout());
		panelSearch.add(txtSearch);
		panelSearch.add(btnSearch);
		JPanel panelFilter = new JPanel();
		panelFilter.setLayout(new FlowLayout());
		panelFilter.add(new JLabel("Filtro por :"));
		cmbFilter.addItem("Todos");
		cmbFilter.addItem("Libro");
		cmbFilter.addItem("Revista");
		cmbFilter.addItem("Tesis");
		cmbFilter.addActionListener(cmbListener);
		panelFilter.add(cmbFilter);
		search_filterPanel.add(panelSearch, BorderLayout.WEST);
		search_filterPanel.add(panelFilter, BorderLayout.EAST);
		JScrollPane tablePanel = new JScrollPane();
		tableMaterials.setDefaultRenderer(Object.class, new Render());
		fillTable();
		tableMaterials.addMouseListener(btnClicked);
		tablePanel.add(tableMaterials);
		tablePanel.setViewportView(tableMaterials);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		btnCharge.addActionListener(chancetoCharge);
		btnManage.addActionListener(changepanelUsersTable);
		buttonsPanel.add(btnManage);
		buttonsPanel.add(btnCharge);
		panelBorder.setLayout(new BorderLayout());
		panelBorder.add(buttonsPanel, BorderLayout.EAST);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(loginPanel);
		add(search_filterPanel);
		add(tablePanel);
		add(panelBorder);
	}
	
	MouseAdapter lblListener = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			System_Control.session = null;
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelReading");
		}
	};
	
	ActionListener chancetoCharge = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)btnCharge.getParent().getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelCharge");
		}
		
	};
	
	public void fillTable(){
		int counter = 0;
		counter = counterObjects(counter);
		row = new Object[counter][7];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				for(int k=0; k<50; k++){
					if(System_Control.listBook[k] != null){
						row[i][j] = System_Control.listBook[k].getId();
						j++;
						row[i][j] = System_Control.listBook[k].getTitle();
						j++;
						row[i][j] = System_Control.listBook[k].getAuthor();
						j++;
						row[i][j] = "Libro";
						j++;
						if(System_Control.listBook[k].isAvailable() == true){
							row[i][j] = "Disponible";
						}else{
							row[i][j] = "Prestado";
						}
						j++;
						row[i][j] = btnEdit;
						j++;
						row[i][j] = btnDelete;
						i++;
						j = 0;
					}
					if(System_Control.listMagazine[k] != null){
						row[i][j] = System_Control.listMagazine[k].getId();
						j++;
						row[i][j] = System_Control.listMagazine[k].getTitle();
						j++;
						row[i][j] = System_Control.listMagazine[k].getCompany();
						j++;
						row[i][j] = "Revista";
						j++;
						if(System_Control.listMagazine[k].isAvailable() == true){
							row[i][j] = "Disponible";
						}else{
							row[i][j] = "Prestado";
						}
						j++;
						row[i][j] = btnEdit;
						j++;
						row[i][j] = btnDelete;
						i++; 
						j= 0;
					}
					if(System_Control.listThesis[k] != null){
						row[i][j] = System_Control.listThesis[k].getId();
						j++;
						row[i][j] = System_Control.listThesis[k].getTitle();
						j++;
						row[i][j] = System_Control.listThesis[k].getAuthor();
						j++;
						row[i][j] = "Tesis";
						j++;
						if(System_Control.listThesis[k].isAvailable() == true){
							row[i][j] = "Disponible";
						}else{
							row[i][j] = "Prestado";
						}
						j++;
						row[i][j] = btnEdit;
						j++;
						row[i][j] = btnDelete;
						i++;
						j = 0;
					}
				}
				break;
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tableMaterials.setModel(dtm);
	}
	
	public int counterObjects(int counter){
		for(int i=0; i<50; i++){
			if(System_Control.listBook[i] != null){
				counter++;
			}
			if(System_Control.listMagazine[i] != null){
				counter++;
			}
			if(System_Control.listThesis[i] != null){
				counter++;
			}
		}
		return counter;
	}
	
	ActionListener changepanelUsersTable = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)btnCharge.getParent().getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "paneltableUser");
		}
		
	};
	
	ActionListener cmbListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String material = (String)cb.getSelectedItem();
			if(material.equals("Libro")){
				filltableBook();
			}else if(material.equals("Revista")){
				filltableMagazine();
			}else if(material.equals("Tesis")){
				filltableThesis();
			}else{
				fillTable();
			}
		}
		
	};
	
	public void filltableBook(){
		int counter = 0;
		counter = counterBook(counter);
		row = new Object[counter][7];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				for(int k=0; k<50; k++){
					if(System_Control.listBook[k] != null){
						row[i][j] = System_Control.listBook[k].getId();
						j++;
						row[i][j] = System_Control.listBook[k].getTitle();
						j++;
						row[i][j] = System_Control.listBook[k].getAuthor();
						j++;
						row[i][j] = "Libro";
						j++;
						if(System_Control.listBook[k].isAvailable() == true){
							row[i][j] = "Disponible";
						}else{
							row[i][j] = "Prestado";
						}
						j++;
						row[i][j] = btnEdit;
						j++;
						row[i][j] = btnDelete;
						i++;
						j = 0;
					}
				}
				break;
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tableMaterials.setModel(dtm);
	}
	
	public int counterBook(int counter){
		for(int i=0; i<50; i++){
			if(System_Control.listBook[i] != null){
				counter++;
			}
		}
		return counter;
	}
	
	public void filltableMagazine(){
		int counter = 0;
		counter = counterMagazine(counter);
		row = new Object[counter][7];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				for(int k=0; k<50; k++){
					if(System_Control.listMagazine[k] != null){
						row[i][j] = System_Control.listMagazine[k].getId();
						j++;
						row[i][j] = System_Control.listMagazine[k].getTitle();
						j++;
						row[i][j] = System_Control.listMagazine[k].getCompany();
						j++;
						row[i][j] = "Revista";
						j++;
						if(System_Control.listMagazine[k].isAvailable() == true){
							row[i][j] = "Disponible";
						}else{
							row[i][j] = "Prestado";
						}
						j++;
						row[i][j] = btnEdit;
						j++;
						row[i][j] = btnDelete;
						i++;
						j = 0;
					}
				}
				break;
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tableMaterials.setModel(dtm);
	}
	
	public int counterMagazine(int counter){
		for(int i=0; i<50; i++){
			if(System_Control.listMagazine[i] != null){
				counter++;
			}
		}
		return counter;
	}
	
	public void filltableThesis(){
		int counter = 0;
		counter = counterThesis(counter);
		row = new Object[counter][7];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				for(int k=0; k<50; k++){
					if(System_Control.listThesis[k] != null){
						row[i][j] = System_Control.listThesis[k].getId();
						j++;
						row[i][j] = System_Control.listThesis[k].getTitle();
						j++;
						row[i][j] = System_Control.listThesis[k].getAuthor();
						j++;
						row[i][j] = "Tesis";
						j++;
						if(System_Control.listThesis[k].isAvailable() == true){
							row[i][j] = "Disponible";
						}else{
							row[i][j] = "Prestado";
						}
						j++;
						row[i][j] = btnEdit;
						j++;
						row[i][j] = btnDelete;
						i++;
						j = 0;
					}
				}
				break;
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tableMaterials.setModel(dtm);
	}
	
	public int counterThesis(int counter){
		for(int i=0; i<50; i++){
			if(System_Control.listThesis[i] != null){
				counter++;
			}
		}
		return counter;
	}
	
	MouseAdapter btnClicked = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			int column = tableMaterials.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY()/tableMaterials.getRowHeight();
			if(row < tableMaterials.getRowCount() && row >= 0 && column < tableMaterials.getColumnCount() && column >= 0){
				Object value = tableMaterials.getValueAt(row, column);
				if(value instanceof JButton){
					((JButton)value).doClick();
					JButton btn = (JButton)value;
					if(btn == btnEdit){
						String id = (String) tableMaterials.getValueAt(row, 0);
						String type = (String) tableMaterials.getValueAt(row, 3);
						System_Window.updateEdit(id, type);
						JPanel panel = (JPanel)btnSearch.getParent().getParent().getParent().getParent();
						CardLayout layout = (CardLayout)panel.getLayout();
						layout.show(panel, "panelEdit");
					}else{
						String id = (String) tableMaterials.getValueAt(row, 0);
						String type = (String) tableMaterials.getValueAt(row, 3);
						boolean success = true;
						if(type.equals("Libro")){
							for(int i=0; i<System_Control.listBook.length; i++){
								if(System_Control.listBook[i] != null){
									if(System_Control.listBook[i].getId().equals(id)){
										if(System_Control.listBook[i].isAvailable() == true){
											System_Control.listBook[i] = null;
										}else{
											JOptionPane.showMessageDialog(null, "El libro que desea eliminar se encuentra en prestamo, no se puede eliminar", "Error",JOptionPane.ERROR_MESSAGE);
											success = false;
										}
									}
								}
							}
						}else if(type.equals("Revista")){
							for(int i=0; i<System_Control.listMagazine.length; i++){
								if(System_Control.listMagazine[i] != null){
									if(System_Control.listMagazine[i].getId().equals(id)){
										if(System_Control.listMagazine[i].isAvailable() == true){
											System_Control.listMagazine[i] = null;
										}else{
											JOptionPane.showMessageDialog(null, "La revista que desea eliminar se encuentra en prestamo, no se puede eliminar", "Error",JOptionPane.ERROR_MESSAGE);
											success = false;
										}
									}
								}
							}
						}else{
							for(int i=0; i<System_Control.listThesis.length; i++){
								if(System_Control.listThesis[i] != null){
									if(System_Control.listThesis[i].getId().equals(id)){
										if(System_Control.listThesis[i].isAvailable() == true){
											System_Control.listThesis[i] = null;
										}else{
											JOptionPane.showMessageDialog(null, "La tesis que desea eliminar se encuentra en prestamo, no se puede eliminar", "Error",JOptionPane.ERROR_MESSAGE);
											success = false;
										}
									}
								}
							}
						}
						if(success == true){
							JOptionPane.showMessageDialog(null, "se ha elminado correctamente el material", "Success",JOptionPane.INFORMATION_MESSAGE);
						}
						System_Window.updateTable();
						System_Window.updateUsers();
					}
				}
			}
		}
	};
	
	ActionListener clickedbtnSearch = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			String search = txtSearch.getText();
			String cmb = (String)cmbFilter.getSelectedItem();
			if(cmb.equals("Libro")){
				int counter = 0;
				counter = counterTitleBook(search, counter);
				row = new Object[counter][7];
				for(int i=0; i<row.length; i++){
					for(int j=0; j<row[i].length; j++){
						if(System_Control.listBook[i] != null){
							
						}
						row[i][j] = System_Control.listBook[i].getId();
						j++;
						row[i][j] = System_Control.listBook[i].getTitle();
						j++;
						row[i][j] = System_Control.listBook[i].getAuthor();
						j++;
						row[i][j] = "Libro";
						j++;
						if(System_Control.listBook[i].isAvailable() == true){
							row[i][j] = "Disponible";
						}else{
							row[i][j] = "Prestado";
						}
						j++;
						row[i][j] = btnEdit;
						j++;
						row[i][j] = btnDelete;
						j++;
					}
				}
			}
		}
		
	};
	
	public int counterTitleBook(String title, int counter){
		for(int i=0; i<50; i++){
			if(System_Control.listBook[i] != null){
				if(System_Control.listBook[i].getTitle().equals(title)){
					counter ++;
				}
			}
		}
		return counter;
	}
	
}
