package com.mx.alura.hotel.modelo;

import javax.persistence.Entity;

@Entity
public class Login {
       
	
	    private String password;
	    private String correo;
	    
	    
	  
	    
		public Login() {
			
		}


		public Login(String password, String correo) {
			
			this.password = password;
			this.correo = correo;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getCorreo() {
			return correo;
		}


		public void setCorreo(String correo) {
			this.correo = correo;
		}
	    
	    
	    
}
