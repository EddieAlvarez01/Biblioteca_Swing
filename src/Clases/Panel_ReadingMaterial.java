package Clases;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class Panel_ReadingMaterial extends JPanel{
	
	JTextField txtSearch = new JTextField(10);
	JButton btnSearch = new JButton("Buscar");
	JComboBox cmbFilter =  new JComboBox();
	JLabel lblLogin = new JLabel("Login");
	JTable tableMaterials = new JTable();
	JPanel panelBorder = new JPanel();
	
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
		panelFilter.add(cmbFilter);
		search_filterPanel.add(panelSearch, BorderLayout.WEST);
		search_filterPanel.add(panelFilter, BorderLayout.EAST);
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setViewportView(tableMaterials);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(loginPanel);
		add(search_filterPanel);
		add(tablePanel);
		add(panelBorder);
	}
	
	MouseAdapter lblListener = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			JPanel panel = (JPanel)lblLogin.getParent().getParent().getParent();
			CardLayout card = (CardLayout)panel.getLayout();
			card.show(panel, "panelLogin");
		}
	};
	
	
	
}