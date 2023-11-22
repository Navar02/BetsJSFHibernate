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
		}else {
			setTipo("user");
			return "user";
		}
		
	}
}
