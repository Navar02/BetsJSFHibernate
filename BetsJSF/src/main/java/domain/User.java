package domain;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class User {
	@Id
	String usuario;
	String password;
	
	public User (String usuario,String password) {
		this.usuario=usuario;
		this.password=password;
	}
	
	
}
