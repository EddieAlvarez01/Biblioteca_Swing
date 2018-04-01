package Clases;

public final class System_Control {
	
	public static Book listBook[] = new Book[50];
	public static Magazine listMagazine[] = new Magazine[50];
	public static Thesis listThesis[] = new Thesis[50];
	public static Usuario listaUsuario[] = new Usuario[11];
	static String session;
	static int counterBook = 1;
	static int counterMagazine = 1;
	static int counterThesis = 1;
	static String columnTable[] = new String[]{"ID", "Titulo", "Autor", "Tipo", "Estado", "Accion", " "};
	static String columnTable2[] = new String[]{"ID", "Titulo", "Autor", "Tipo", "Estado", "Accion"};
	static String columnTableUser[] = new String[]{"Username", "Nombre", "Apellido", "Materiales prestados", "Accion", ""};
	
	
	public static boolean validateUsername(String username, String pass){
		for(int i=0; i<listaUsuario.length; i++){
			if(System_Control.listaUsuario[i] != null){
				if(username.equals(System_Control.listaUsuario[i].getUsername()) && pass.equals(System_Control.listaUsuario[i].getPassword())){
					session = System_Control.listaUsuario[i].getUsername();
					return true;
				}
			}
		}
		return false;
	}
	
	
	public static boolean addBook(Book book){
		for(int i=0; i<listBook.length; i++){
			if(listBook[i] == null ){
				listBook[i] = book;
				return true;
			}
		}
		return false;
	}
	
	public static boolean addMagazine(Magazine magazine){
		for(int i=0; i<listMagazine.length; i++){
			if(listMagazine[i] == null ){
				listMagazine[i] = magazine;
				return true;
			}
		}
		return false;
	}
	
	public static boolean addThesis(Thesis thesis){
		for(int i=0; i<listThesis.length; i++){
			if(listThesis[i] == null ){
				listThesis[i] = thesis;
				return true;
			}
		}
		return false;
	}
}
