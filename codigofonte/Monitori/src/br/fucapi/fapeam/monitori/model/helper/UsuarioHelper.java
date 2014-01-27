package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
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
	
	private Paciente paciente;
	
	public UsuarioHelper(PacienteDadosActivity activity){
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
		paciente = new Paciente();
	}

	public Paciente getUsuario(){
		
		paciente.setNome(nome.getText().toString());
		paciente.setEndereco(endereco.getText().toString());
		//paciente.setBairro(bairro.getText().toString());
		paciente.setCep(cep.getText().toString());
		//paciente.setUnidadeSaude(unidadeSaude.isActivated());
		paciente.setCelular(celular.getText().toString());
		paciente.setTelefone(telefone.getText().toString());
		//paciente.setDataNascimento(dataNascimento.getText().toString());

		return (Paciente) paciente;		
	}
	
	public void setPaciente(Paciente paciente){
		
		nome.setText(paciente.getNome());
		telefone.setText(paciente.getTelefone());
		endereco.setText(paciente.getEndereco());
		
		this.paciente = paciente;
		
	}
}