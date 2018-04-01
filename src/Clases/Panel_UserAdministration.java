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

public class Panel_UserAdministration extends JPanel{
	
	JLabel lblLogin = new JLabel("admin - Log Out");
	JTable tableUsers = new JTable();
	JButton btnEdit = new JButton("Editar");
	JButton btnDelete = new JButton("Eliminar");
	JButton btnReturn = new JButton("Regresar");
	Object row[][];
	
	public Panel_UserAdministration(){
		JPanel loginPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel tittle = new JLabel("Administracion de Usuarios");
		Font auxFont = tittle.getFont();
		tittle.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
		titlePanel.add(tittle);
		loginPanel.setLayout(new BorderLayout());
		lblLogin.addMouseListener(lblListener);
		loginPanel.add(lblLogin, BorderLayout.EAST);
		loginPanel.add(titlePanel,BorderLayout.SOUTH);
		JScrollPane tablePanel = new JScrollPane();
		tableUsers.setDefaultRenderer(Object.class, new Render());
		fillTable();
		tableUsers.addMouseListener(btnClicked);
		tablePanel.setViewportView(tableUsers);
		JPanel panelReturn = new JPanel();
		panelReturn.setLayout(new BorderLayout());
		btnReturn.addActionListener(chanceReturn);
		panelReturn.add(btnReturn, BorderLayout.EAST);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(loginPanel);
		add(tablePanel);
		add(panelReturn);
	}
	
	
	public void fillTable(){
		int counter = 0;
		counter = counterUsers(counter);
		row = new Object[counter][6];
		for(int i=0; i<row.length; i++){
			for(int j=0; j<row[i].length; j++){
				for(int k=1; k<System_Control.listaUsuario.length; k++){
					if(System_Control.listaUsuario[k] != null){
						row[i][j] = System_Control.listaUsuario[k].getUsername();
						j++;
						row[i][j] = System_Control.listaUsuario[k].getName();
						j++;
						row[i][j] = System_Control.listaUsuario[k].getLast_name();
						j++;
						row[i][j] = System_Control.listaUsuario[k].getBorrowedBook();
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
		DefaultTableModel dtm = new DefaultTableModel(row, System_Control.columnTableUser){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tableUsers.setModel(dtm);
	}
	
	MouseAdapter lblListener = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			System_Control.session = null;
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout layout = (CardLayout)panel.getLayout();
			layout.show(panel, "panelReading");
		}
	};
	
	public  int counterUsers(int counter){
		for(int i=1; i<11; i++){
			if(System_Control.listaUsuario[i] != null){
				counter++;
			}
		}
		return counter;
	}
	
	ActionListener chanceReturn = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = (JPanel)btnReturn.getParent().getParent().getParent();
			CardLayout layout = (CardLayout)panel.getLayout();
			layout.show(panel, "panelAdmin");
		}
		
	};
	
	MouseAdapter btnClicked = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			int column = tableUsers.getColumnModel().getColumnIndexAtX(e.getX());
			int row = e.getY()/tableUsers.getRowHeight();
			if(row < tableUsers.getRowCount() && row >= 0 && column < tableUsers.getColumnCount() && column >= 0){
				Object value = tableUsers.getValueAt(row, column);
				if(value instanceof JButton){
					((JButton)value).doClick();
					JButton btn = (JButton)value;
					if(btn == btnEdit){
						String username = (String) tableUsers.getValueAt(row, 0);
						System_Window.updateEditUser(username);
						JPanel panel = (JPanel)btnReturn.getParent().getParent().getParent();
						CardLayout layout = (CardLayout)panel.getLayout();
						layout.show(panel, "EditUser");
					}else{
						String username = (String) tableUsers.getValueAt(row, 0);
						boolean success = true;
						for(int i=1; i<System_Control.listaUsuario.length; i++){
							if(System_Control.listaUsuario[i] != null){
								if(System_Control.listaUsuario[i].getUsername().equals(username)){
									if(System_Control.listaUsuario[i].getBorrowedBook() == 0){
										System_Control.listaUsuario[i] = null;
									}else{
										success = false;
										JOptionPane.showMessageDialog(null, "El usuario que desea eliminar tiene libros prestados, no se puede eliminar", "Error",JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}
						if(success == true){
							JOptionPane.showMessageDialog(null, "se ha elminado correctamente el usuario", "Success",JOptionPane.INFORMATION_MESSAGE);
						}
						System_Window.updateUsers();
					}
				}
			}
		}
	};
	
}
