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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Panel_ReadingMaterial extends JPanel{
	
	JTextField txtSearch = new JTextField(10);
	JButton btnSearch = new JButton("Buscar");
	JComboBox cmbFilter =  new JComboBox();
	JLabel lblLogin = new JLabel("Login");
	JTable tableMaterials = new JTable();
	JPanel panelBorder = new JPanel();
	Object row[][];
	JButton btnView = new JButton("Ver");
	JButton btnBack = new JButton("Regresar");
	
	public Panel_ReadingMaterial(){
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
		tablePanel.setViewportView(tableMaterials);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		panelBorder.setLayout(new BorderLayout());
		btnBack.setVisible(false);
		btnBack.addActionListener(clickBack);
		panelBorder.add(btnBack, BorderLayout.EAST);
		add(loginPanel);
		add(search_filterPanel);
		add(tablePanel);
		add(panelBorder);
	}
	
	MouseAdapter lblListener = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			if(System_Control.session != null){
				System_Control.session = null;
				lblLogin.setText("Login"); 
				JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
				CardLayout card = (CardLayout)panel.getLayout();
				card.show(panel, "panelReading");
			}else{
				JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
				CardLayout card = (CardLayout)panel.getLayout();
				card.show(panel, "panelLogin");
			}
		}
	};
	
	public void fillTable(){
		int counter = 0;
		int counterBook = 0;
		int counterMagazine = 0;
		int counterThesis = 0;
		counter = counterObjects(counter);
		row = new Object[counter][6];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				if(System_Control.listBook[counterBook] != null){
					row[i][j] = System_Control.listBook[counterBook].getId();
					j++;
					row[i][j] = System_Control.listBook[counterBook].getTitle();
					j++;
					row[i][j] = System_Control.listBook[counterBook].getAuthor();
					j++;
					row[i][j] = "Libro";
					j++;
					if(System_Control.listBook[counterBook].isAvailable() == true){
						row[i][j] = "Disponible";
					}else{
						row[i][j] = "Prestado";
					}
					j++;
					row[i][j] = btnView;
					counterBook++;
				}else if(System_Control.listMagazine[counterMagazine] != null){
					row[i][j] = System_Control.listMagazine[counterMagazine].getId();
					j++;
					row[i][j] = System_Control.listMagazine[counterMagazine].getTitle();
					j++;
					row[i][j] = System_Control.listMagazine[counterMagazine].getCompany();
					j++;
					row[i][j] = "Revista";
					j++;
					if(System_Control.listMagazine[counterMagazine].isAvailable() == true){
						row[i][j] = "Disponible";
					}else{
						row[i][j] = "Prestado";
					}
					j++;
					JPanel panel = new JPanel();
					panel.setLayout(new FlowLayout());
					row[i][j] = btnView;
					counterMagazine++;
				}else if(System_Control.listThesis[counterThesis] != null){
					row[i][j] = System_Control.listThesis[counterThesis].getId();
					j++;
					row[i][j] = System_Control.listThesis[counterThesis].getTitle();
					j++;
					row[i][j] = System_Control.listThesis[counterThesis].getAuthor();
					j++;
					row[i][j] = "Tesis";
					j++;
					if(System_Control.listThesis[counterThesis].isAvailable() == true){
						row[i][j] = "Disponible";
					}else{
						row[i][j] = "Prestado";
					}
					j++;
					row[i][j] = btnView;
					counterThesis++;
				}
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable2){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tableMaterials.setModel(dtm);
	}
	
	public  int counterObjects(int counter){
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
		row = new Object[counter][6];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
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
				row[i][j] = btnView;
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable2){
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
		row = new Object[counter][6];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				row[i][j] = System_Control.listMagazine[i].getId();
				j++;
				row[i][j] = System_Control.listMagazine[i].getTitle();
				j++;
				row[i][j] = System_Control.listMagazine[i].getCompany();
				j++;
				row[i][j] = "Revista";
				j++;
				if(System_Control.listMagazine[i].isAvailable() == true){
					row[i][j] = "Disponible";
				}else{
					row[i][j] = "Prestado";
				}
				j++;
				row[i][j] = btnView;
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable2){
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
		row = new Object[counter][6];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				row[i][j] = System_Control.listThesis[i].getId();
				j++;
				row[i][j] = System_Control.listThesis[i].getTitle();
				j++;
				row[i][j] = System_Control.listThesis[i].getAuthor();
				j++;
				row[i][j] = "Tesis";
				j++;
				if(System_Control.listThesis[i].isAvailable() == true){
					row[i][j] = "Disponible";
				}else{
					row[i][j] = "Prestado";
				}
				j++;
				row[i][j] = btnView;
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTable2){
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
					if(btn == btnView){
						String id = (String) tableMaterials.getValueAt(row, 0);
						String type = (String) tableMaterials.getValueAt(row, 3);
						System_Window.updateLabels(id, type);
						JPanel panel = (JPanel)btnSearch.getParent().getParent().getParent().getParent();
						CardLayout layout = (CardLayout)panel.getLayout();
						layout.show(panel, "panelView");
					}
				}
			}
		}
	};
	
	ActionListener clickBack = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)btnSearch.getParent().getParent().getParent().getParent();
			CardLayout layout = (CardLayout)panel.getLayout();
			layout.show(panel, "menuUser");
		}
		
	};
	
}
