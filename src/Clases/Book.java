package Clases;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String theme;
	private int noPages;
	
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getNoPages() {
		return noPages;
	}
	public void setNoPages(int noPages) {
		this.noPages = noPages;
	}
	
	public Book(int id, String title, String author, String theme, int noPages) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.theme = theme;
		this.noPages = noPages;
	}
	
}
