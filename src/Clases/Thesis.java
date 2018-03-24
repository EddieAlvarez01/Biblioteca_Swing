package Clases;

public class Thesis {
	private String id;
	private String title;
	private String author;
	private String grade;
	private String theme;
	private int year;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Thesis(String id, String title, String author, String grade, String theme, int year, boolean available) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.grade = grade;
		this.theme = theme;
		this.year = year;
		this.available = available;
	}
	
}
