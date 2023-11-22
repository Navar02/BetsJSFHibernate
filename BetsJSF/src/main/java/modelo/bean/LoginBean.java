package modelo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginBean {
	
	private String nombre;
	private String password;
	private String tipo;
	private static List<String> tipos=new ArrayList<String>();
	
	public LoginBean() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setTipo(String tipo) {
		 this.tipo = tipo;
		 System.out.println("El tipo del usuario:" + this.tipo);
	}
	public List<String> getTipos() {
		return tipos;
	}
	public void setTipos(List<String> tipos) {
		 this.tipos = tipos;
	}
	
	public String comprobar() {
		if(nombre.equals("admin") && password.equals("1234")) {
			setTipo("admin");
			return "admin";
		}else if(nombre.equals("admin") && !password.equals("1234")) {
			FacesContext.getCurrentInstance().addMessage(null, 
					 new FacesMessage("Error: Acceso incorrecto al administrador"));
			 return null;
		}else if(!passValida()){
			FacesContext.getCurrentInstance().addMessage(null, 
					 new FacesMessage("Error: La contraseña debe tener una letra"
					 		+ " mayuscula otra minuscula, un número,"
					 		+ "un caracter especial y debe tener entre 6 y 20 carateres."));
			 return null;
		}else {
			setTipo("user");
			return "user";
		}
		
	}
	
	public boolean passValida() {
		// combrobar q la longitd esta enre 6 y 20
        if (!((password.length() >= 6)
              && (password.length() <= 20))) {
            return false;
        }
 
        // comprobamos q no tenga ningun espacio
        if (password.contains(" ")) {
            return false;
        }
        if (true) {
            int count = 0;
 
            // check digits from 0 to 9
            for (int i = 0; i <= 9; i++) {
 
                // to convert int to string
                String str1 = Integer.toString(i);
 
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }
        // caracteres especiales
        if (!(password.contains("@") || password.contains("#")
              || password.contains("!") || password.contains("~")
              || password.contains("$") || password.contains("%")
              || password.contains("^") || password.contains("&")
              || password.contains("*") || password.contains("(")
              || password.contains(")") || password.contains("-")
              || password.contains("+") || password.contains("/")
              || password.contains(":") || password.contains(".")
              || password.contains(", ") || password.contains("<")
              || password.contains(">") || password.contains("?")
              || password.contains("|"))) {
            return false;
        }
 
        if (true) {
            int count = 0;
 
            // comprobando mayusculas
            for (int i = 65; i <= 90; i++) {
 
                // type casting
                char c = (char)i;
 
                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }
 
        if (true) {
            int count = 0;
 
            // comprobando minusculas
            for (int i = 97; i <= 122; i++) {
 
                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);
 
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }
 
        // if all conditions fails
        return true;
    }
}
