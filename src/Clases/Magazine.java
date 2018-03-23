package Clases;

import java.util.Calendar;

public class Magazine {
	
	private int id;
	private String title;
	private String company;
	private Calendar date;
	private String theme;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public Magazine(int id, String title, String company, Calendar date, String theme) {
		super();
		this.id = id;
		this.title = title;
		this.company = company;
		this.date = date;
		this.theme = theme;
	}
	
	
	
}
