package br.fucapi.fapeam.monitori.model.bean;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Diagnosticar extends AbstractEntityBean implements Serializable{
	
	private String descrever;
	private Calendar dataHoraDiagnostico;
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
	
	public Calendar getDataHoraDiagnostico() {
		return dataHoraDiagnostico;
	}

	public void setDataHoraDiagnostico(Calendar dataHoraDiagnostico) {
		this.dataHoraDiagnostico = dataHoraDiagnostico;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
