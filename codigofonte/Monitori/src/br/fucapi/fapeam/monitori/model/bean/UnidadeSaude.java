package br.fucapi.fapeam.monitori.model.bean;

@SuppressWarnings("serial")
public class UnidadeSaude extends AbstractEntityBean {

	private String nome;
	private String endereco;
	private String cep;
	private Bairro bairro;
	private String numeroUBS;
	private String fone;
	
	@Override
	public String toString() {		
		return nome;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getNumeroUBS() {
		return numeroUBS;
	}

	public void setNumeroUBS(String numeroUBS) {
		this.numeroUBS = numeroUBS;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

}
