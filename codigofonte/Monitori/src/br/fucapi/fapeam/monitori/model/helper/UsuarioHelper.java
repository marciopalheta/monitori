package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class UsuarioHelper {
	private EditText nome;
	private EditText endereco;
	private EditText bairro;
	private EditText cep;
	private EditText unidadeSaude;
	private EditText celular;
	private EditText telefone;
	private EditText dataNascimento;
	private EditText login;
	private EditText senha;
	private ImageView foto;
	private RadioButton masculino;
	private RadioButton feminino;
	
	private Usuario usuario;
	
	public UsuarioHelper(Activity activity){
		//Associacao de campos de tela ao controller
		nome = (EditText) activity.findViewById(R.id.edNome);
		endereco = (EditText) activity.findViewById(R.id.edEndereco);
		bairro = (EditText) activity.findViewById(R.id.edBairro);
		cep = (EditText) activity.findViewById(R.id.edCep);
		unidadeSaude = (EditText) activity.findViewById(R.id.edUnidadeSaude);
		celular = (EditText) activity.findViewById(R.id.edCelular);
		telefone = (EditText) activity.findViewById(R.id.edTefone);
		dataNascimento = (EditText) activity.findViewById(R.id.edDataNascimento);
		masculino = (RadioButton) activity.findViewById(R.id.rbMasc);
		feminino = (RadioButton) activity.findViewById(R.id.rbFeminino);
		foto = (ImageView) activity.findViewById(R.id.foto);
		
		//criacao do objeto paciente
		usuario = new Usuario();
	}

	public Usuario getUsuario(){
		
		usuario.setNome(nome.getText().toString());
		usuario.setEndereco(endereco.getText().toString());
		//paciente.setBairro(bairro.getText().toString());
		usuario.setCep(cep.getText().toString());
		//paciente.setUnidadeSaude(unidadeSaude.isActivated());
		usuario.setCelular(celular.getText().toString());
		usuario.setTelefone(telefone.getText().toString());
		//paciente.setDataNascimento(dataNascimento.getText().toString());

		return usuario;		
	}
	
	public void setUsuario(Usuario usuario){
		
		nome.setText(usuario.getNome());
		telefone.setText(usuario.getTelefone());
		endereco.setText(usuario.getEndereco());
		
		this.usuario = usuario;
		
	}
	
	
	public EditText getNome() {
		return nome;
	}

	public void setNome(EditText nome) {
		this.nome = nome;
	}

	public EditText getEndereco() {
		return endereco;
	}

	public void setEndereco(EditText endereco) {
		this.endereco = endereco;
	}

	public EditText getBairro() {
		return bairro;
	}

	public void setBairro(EditText bairro) {
		this.bairro = bairro;
	}

	public EditText getCep() {
		return cep;
	}

	public void setCep(EditText cep) {
		this.cep = cep;
	}

	public EditText getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(EditText unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	public EditText getCelular() {
		return celular;
	}

	public void setCelular(EditText celular) {
		this.celular = celular;
	}

	public EditText getTelefone() {
		return telefone;
	}

	public void setTelefone(EditText telefone) {
		this.telefone = telefone;
	}

	public EditText getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(EditText dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EditText getLogin() {
		return login;
	}

	public void setLogin(EditText login) {
		this.login = login;
	}

	public EditText getSenha() {
		return senha;
	}

	public void setSenha(EditText senha) {
		this.senha = senha;
	}

	public ImageView getFoto() {
		return foto;
	}

	public void setFoto(ImageView foto) {
		this.foto = foto;
	}

	public RadioButton getMasculino() {
		return masculino;
	}

	public void setMasculino(RadioButton masculino) {
		this.masculino = masculino;
	}

	public RadioButton getFeminino() {
		return feminino;
	}

	public void setFeminino(RadioButton feminino) {
		this.feminino = feminino;
	}
	
}