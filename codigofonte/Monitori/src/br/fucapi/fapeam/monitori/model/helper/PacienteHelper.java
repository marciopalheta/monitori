package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.content.Context;
import android.util.Log;
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
		
		paciente.setDataNascimento(getDataNascimento());
		
		
		//paciente.setDataNascimento( getDataNascimento().toString()); 
		paciente.setSexo(getSexo().getText().toString() );
		
		paciente.setLogin(paciente.getNome());
		paciente.setSenha(paciente.getNome());
		
		paciente.setHipertenso(hipertenso.isChecked());
		paciente.setTipoUsuario(TipoUsuario.PACIENTE);

		return (Paciente) paciente;		
	}
	
	public void setPaciente(Paciente paciente){
						
		getNome().setText(paciente.getNome());		
		getTelefone().setText(paciente.getTelefone());
		getEndereco().setText(paciente.getEndereco());		
		getCep().setText(paciente.getCep() );
		getCelular().setText(paciente.getCelular());
		getTelefone().setText(paciente.getTelefone());										
		
		setDataNascimento(paciente.getDataNascimento());
		
		Log.i("TESTE", "Sexo do Paciente: " +paciente.getSexo());
		
		if(paciente.getSexo().equals("Masculino")){
			//getSexo().check(getMasculino().getId());
			getMasculino().setChecked(true);
			
		}else{ 
			//getSexo().check(getFeminino().getId());
			getFeminino().setChecked(true);
			
		}
		
		hipertenso.setChecked(paciente.isHipertenso() );
						
		this.paciente = paciente;
		
	}
}