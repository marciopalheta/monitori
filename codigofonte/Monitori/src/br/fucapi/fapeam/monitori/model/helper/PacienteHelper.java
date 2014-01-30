package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class PacienteHelper extends UsuarioHelper {
		
	private Paciente paciente;
	private CheckBox hipertenso;
	
	public PacienteHelper(PacienteDadosActivity activity){
		super(activity);
						
		//criacao do objeto paciente
		paciente = new Paciente();
		
		hipertenso =(CheckBox) activity.findViewById(R.id.chbHipertenso);
	}

	public Paciente getPaciente(){
		
		paciente.setNome(getNome().getText().toString());
		paciente.setEndereco(getEndereco().getText().toString());
		//paciente.setBairro(bairro.getText().toString());
		paciente.setCep(getCep().getText().toString());
		//paciente.setUnidadeSaude(unidadeSaude.isActivated());
		paciente.setCelular(getCelular().getText().toString());
		paciente.setTelefone(getTelefone().getText().toString());
		//paciente.setDataNascimento(dataNascimento.getText().toString());
		paciente.setLogin(paciente.getNome());
		paciente.setSenha(paciente.getNome());
		
		paciente.setHipertenso(hipertenso.isChecked());

		return (Paciente) paciente;		
	}
	
	public void setPaciente(Paciente paciente){
		
		getNome().setText(paciente.getNome());		
		getTelefone().setText(paciente.getTelefone());
		getEndereco().setText(paciente.getEndereco());
		
		this.paciente = paciente;
		
	}
}