package br.fucapi.fapeam.monitori.model.bean;

import java.util.Calendar;

@SuppressWarnings("serial")
public class Usuario extends AbstractEntityBean {

	private String nome;
	private String endereco;
	private Bairro bairro;
	private UnidadeSaude unidadeSaude;
	private String cep;
	private String celular;
	private String telefone;
	private Calendar dataNascimento;
	private String observacao;
	private String login;
	private String senha;
	private String foto;
	private String masculino;
	private String feminino;
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getMasculino() {
		return masculino;
	}

	public void setMasculino(String masculino) {
		this.masculino = masculino;
	}

	public String getFeminino() {
		return feminino;
	}

	public void setFeminino(String feminino) {
		this.feminino = feminino;
	}

	@Override
	public String toString() {
		return nome;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
