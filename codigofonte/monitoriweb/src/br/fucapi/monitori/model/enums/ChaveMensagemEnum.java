package br.fucapi.monitori.model.enums;

public enum ChaveMensagemEnum {
	ALERTA_OPERACAO_RESULTADO("alerta_operacao_resultado"), 
	ALERTA_OPERACAO_SUCESSO("alerta_operacao_sucesso"),
	ALERTA_OPERACAO_CANCELADA("alerta_operacao_cancelada"), 
	ALERTA_OPERACAO_FALHA("alerta_operacao_falha"), 
	LABEL_NAO_INFORMADO("label_nao_informado"),
	ALERTA_FALHA_LOGIN("alerta_operacao_falha_login"),
	ALERTA_ERRO_SENHA_ATUAL("alerta_senha_incorreta"),
	ALERTA_ERRO_SENHA_REPETIDA("alerta_senha_repetida"), 
	ALERTA_ERRO_LOGIN_INVALIDO("alerta_login_indisponivel");

	private String chave;

	private ChaveMensagemEnum(String chave) {
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}
}
