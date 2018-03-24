package Clases;

public class Book {
	
	private String id;
	private String title;
	private String author;
	private String theme;
	private int noPages;
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
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Book(String id, String title, String author, String theme, int noPages, boolean available) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.theme = theme;
		this.noPages = noPages;
		this.available = available;
	}
	
}
