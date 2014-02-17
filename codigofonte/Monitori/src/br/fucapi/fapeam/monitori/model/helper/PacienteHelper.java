package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
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
import android.widget.TextView;

public class PacienteHelper extends UsuarioHelper {
		
	private Paciente paciente;
	private CheckBox hipertenso;
	private CheckBox diabetico1;
	private CheckBox diabetico2;
	
	public PacienteHelper(PacienteDadosActivity fragmentActivity){
		super(fragmentActivity);
						
		//criacao do objeto paciente
		paciente = new Paciente();
		
		hipertenso =(CheckBox) fragmentActivity.findViewById(R.id.chbHipertenso);
		diabetico1 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo1);
		diabetico2 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo2);
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
		//getSpinBairro().getSelectedView().getId();
		Bairro auxBairro = new Bairro();
		
		TextView textId = (TextView)getSpinBairro().getSelectedView().findViewById(R.id.textID); 
		auxBairro.setId(Long.parseLong( textId.getText().toString() ) );
		auxBairro.setNome(getSpinBairro().getSelectedItem().toString());
		
		Log.e("BAIRRO", "Spin Bairro nome: " + getSpinBairro().getSelectedItem() );
		Log.e("BAIRRO", "Spin Bairro id: " + textId.getText() );
						
		paciente.setBairro(auxBairro);
		
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
	    				
		
		ArrayAdapter<String> array_bairro=(ArrayAdapter<String>)getSpinBairro().getAdapter();
		getSpinBairro().setSelection(array_bairro.getPosition( paciente.getBairro().getNome() ) );	    				
		
		//Log.i("TESTE", "Sexo do Paciente: " +paciente.getSexo());
		Log.e("TESTE", "is Hipertenso: " + paciente.isHipertenso() );
		hipertenso.setChecked(paciente.isHipertenso() );
		diabetico1.setChecked(paciente.isDiabetico1() );
		diabetico2.setChecked(paciente.isDiabetico2() );
		
		
		this.paciente = paciente;
		
	
	//Validando dados
	
			
			}
}