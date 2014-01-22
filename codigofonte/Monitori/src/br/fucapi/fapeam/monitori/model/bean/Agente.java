package br.fucapi.fapeam.monitori.model.bean;

@SuppressWarnings("serial")
public class Agente extends Usuario {

	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
