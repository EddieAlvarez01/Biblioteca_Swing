package Clases;

public final class System_Control {
	
	public static Book listBook[] = new Book[50];
	public static Magazine listMagazine[] = new Magazine[50];
	public static Thesis listThesis[] = new Thesis[50];
	public static Usuario listaUsuario[] = new Usuario[11];
	static String session;
	
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
}
