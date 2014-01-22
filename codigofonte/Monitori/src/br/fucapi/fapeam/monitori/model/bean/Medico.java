package br.fucapi.fapeam.monitori.model.bean;

@SuppressWarnings("serial")
public class Medico extends Usuario {

	private String crm;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}
