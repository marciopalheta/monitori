package br.fucapi.fapeam.monitori.model.bean;

import java.io.Serializable;

public class ColetarDados extends AbstractEntityBean implements Serializable{
	
	private String sis;
	private String glicose;
	private boolean jejum;
	private boolean posPandrial;
	private Usuario usuario;		
	
	public String getSis() {
		return sis;
	}
	
	public void setSis(String sis) {
		this.sis = sis;
	}
	
	public String getGlicose() {
		return glicose;
	}
	
	public void setGlicose(String glicose) {
		this.glicose = glicose;
	}
	
	public boolean isPos_pandrial() {
		return posPandrial;
	}

	public void setPos_pandrial(boolean pos_pandrial) {
		this.posPandrial = pos_pandrial;
	}

	public boolean isJejum() {
		return jejum;
	}

	public void setJejum(boolean jejum) {
		this.jejum = jejum;
	}
	
	
	@Override
	public String toString() {
		
		return getSis();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
