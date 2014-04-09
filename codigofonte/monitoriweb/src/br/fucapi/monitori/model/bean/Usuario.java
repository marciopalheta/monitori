package br.fucapi.monitori.model.bean;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Usuario extends AbstractEntityBean {

	private String nome;
	private String login;
	private String email;
	private String senha;
	private String cpf;
	private boolean ativo;
	private boolean alterarSenha;

	@Override
	public String toString() {
		return nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAlterarSenha() {
		return alterarSenha;
	}

	public void setAlterarSenha(boolean alterarSenha) {
		this.alterarSenha = alterarSenha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void limparCPF() {
		if (cpf != null)
			this.cpf = cpf.replace(".", "").replace("-", "");
	}

	public String getCpfMask() {
		if (cpf == null || cpf.length() == 0) {
			return null;
		}
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
				+ cpf.substring(6, 9) + "-" + cpf.substring(9);
	}

	public void recriarSenha() {
		senha = cpf;
	}
}
