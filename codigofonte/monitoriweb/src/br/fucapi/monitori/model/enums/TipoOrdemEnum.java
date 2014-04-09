package br.fucapi.monitori.model.enums;

public enum TipoOrdemEnum {
	CRESCENTE("asc"), DECRESCENTE("desc");
	private String nome;

	private TipoOrdemEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
