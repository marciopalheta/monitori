package br.fucapi.fapeam.monitori.model.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Diagnosticar extends AbstractEntityBean implements Serializable{
	
	private String descrever;
	private Usuario usuario;

	public String getDescrever() {
		return descrever;
	}

	public void setDescrever(String descrever) {
		this.descrever = descrever;
	}
	
	@Override
	public String toString() {
		
		return getDescrever();
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
