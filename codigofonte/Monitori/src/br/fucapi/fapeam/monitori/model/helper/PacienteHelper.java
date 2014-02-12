package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class PacienteHelper extends UsuarioHelper {
		
	private Paciente paciente;
	private CheckBox hipertenso;
	private CheckBox diabetico1;
	private CheckBox diabetico2;
	
	public PacienteHelper(PacienteDadosActivity activity){
		super(activity);
						
		//criacao do objeto paciente
		paciente = new Paciente();
		
		hipertenso =(CheckBox) activity.findViewById(R.id.chbHipertenso);
		diabetico1 =(CheckBox) activity.findViewById(R.id.chbTipo1);
		diabetico2 =(CheckBox) activity.findViewById(R.id.chbTipo2);
	}

	public Paciente getPaciente(){
		
		paciente.setNome(getNome().getText().toString());
		paciente.setEndereco(getEndereco().getText().toString());
		//paciente.setBairro(bairro.getText().toString());
		paciente.setCep(getCep().getText().toString());
		//paciente.setUnidadeSaude(unidadeSaude.isActivated());
		paciente.setCelular(getCelular().getText().toString());
		paciente.setTelefone(getTelefone().getText().toString());
		paciente.setNomeMae(getNomeMae().getText().toString());
		paciente.setNumSus(getNumSus().getText().toString());
		paciente.setDataNascimento(getDataNascimento());
		
		
		//paciente.setDataNascimento( getDataNascimento().toString());
		
		paciente.setSexo( String.valueOf(getSexo().getSelectedItem()) );
		
		paciente.setLogin(paciente.getNome());
		paciente.setSenha(paciente.getNome());
		
		paciente.setHipertenso(hipertenso.isChecked());
		paciente.setDiabetico1(diabetico1.isChecked());
		paciente.setDiabetico2(diabetico2.isChecked());
		
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
		getNomeMae().setText(paciente.getNomeMae());
		getNumSus().setText(paciente.getNumSus());
		setDataNascimento(paciente.getDataNascimento());
		
		ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)getSexo().getAdapter();
		getSexo().setSelection(array_spinner.getPosition( paciente.getSexo() ));
	    	
		//Log.i("TESTE", "Sexo do Paciente: " +paciente.getSexo());
		Log.e("TESTE", "is Hipertenso: " + paciente.isHipertenso() );
		hipertenso.setChecked(paciente.isHipertenso() );
		diabetico1.setChecked(paciente.isDiabetico1() );
		diabetico2.setChecked(paciente.isDiabetico2() );
		
		
		this.paciente = paciente;
		
	
	//Validando dados
	
			
			}
}