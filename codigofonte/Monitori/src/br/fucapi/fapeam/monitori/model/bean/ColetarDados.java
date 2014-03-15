package br.fucapi.fapeam.monitori.model.bean;

public class ColetarDados extends AbstractEntityBean{
	
	private String sis;
	private String glicose;
	private String jejum;
	private String posPandrial;
	
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
	public String getJejum() {
		return jejum;
	}
	public void setJejum(String jejum) {
		this.jejum = jejum;
	}
	public String getPosPandrial() {
		return posPandrial;
	}
	public void setPosPandrial(String posPandrial) {
		this.posPandrial = posPandrial;
	}
}
