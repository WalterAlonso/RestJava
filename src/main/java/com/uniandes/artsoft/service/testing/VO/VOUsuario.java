package com.uniandes.artsoft.service.testing.VO;

public class VOUsuario {
	private String usuario;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserValido() {
		return userValido;
	}
	public void setUserValido(boolean userValido) {
		this.userValido = userValido;
	}
	private String password;
	private boolean userValido;
}
