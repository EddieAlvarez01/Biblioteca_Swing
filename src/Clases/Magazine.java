package Clases;

import java.util.Calendar;

public class Magazine {
	
	private String id;
	private String title;
	private String company;
	private Calendar date;
	private String theme;
	private boolean available;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Magazine(String id, String title, String company, Calendar date, String theme, boolean available) {
		super();
		this.id = id;
		this.title = title;
		this.company = company;
		this.date = date;
		this.theme = theme;
		this.available = available;
	}
	
	
	
}
