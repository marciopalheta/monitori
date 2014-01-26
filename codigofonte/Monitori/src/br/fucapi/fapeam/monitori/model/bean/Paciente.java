package br.fucapi.fapeam.monitori.model.bean;

@SuppressWarnings("serial")
public class Paciente extends Usuario {
	private boolean hipertenso;
	private boolean diabetico1;
	private boolean diabetico2;

	public String toString(){
		return getNome();
	}
	public boolean isHipertenso() {
		return hipertenso;
	}

	public void setHipertenso(boolean hipertenso) {
		this.hipertenso = hipertenso;
	}

	public boolean isDiabetico1() {
		return diabetico1;
	}

	public void setDiabetico1(boolean diabetico1) {
		this.diabetico1 = diabetico1;
	}

	public boolean isDiabetico2() {
		return diabetico2;
	}

	public void setDiabetico2(boolean diabetico2) {
		this.diabetico2 = diabetico2;
	}

}
