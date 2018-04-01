package Clases;

public class Usuario {
	
	private int id;
	private String name;
	private String last_name;
	private String username;
	private String password;
	private int borrowedBook;
	private String borrowed[];
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getBorrowedBook() {
		return borrowedBook;
	}
	
	public void setBorrowedBook(int borrowedBook) {
		this.borrowedBook = borrowedBook;
	}
	
	public String[] getBorrowed() {
		return borrowed;
	}
	
	public void setBorrowed(String[] borrowed) {
		this.borrowed = borrowed;
	}
	
	public Usuario(String name, String last_name, String username, String password, int borrowedBook, String[] borrowed) {
		this.name = name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.borrowedBook = borrowedBook;
		this.borrowed = borrowed;
	}
	
	public Usuario(String username){
		this.username = username;
	}
	
	public boolean usernameValidation(){
		for(int i=0; i<System_Control.listaUsuario.length;i++){
			if(System_Control.listaUsuario[i] == null){
			}else{
				if(username.equals(System_Control.listaUsuario[i].getUsername())){
				return false;
				}
			}
		}
		return true;
	}
	
	public boolean registerUser(Usuario user){
		for(int i=0; i<System_Control.listaUsuario.length;i++){
			if(System_Control.listaUsuario[i] == null){
				System_Control.listaUsuario[i] = user;
				return true;
			}
		}
		return false;
	}
	
}
