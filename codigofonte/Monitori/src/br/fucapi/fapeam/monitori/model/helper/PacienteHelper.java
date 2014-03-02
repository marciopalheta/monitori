package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class PacienteHelper extends UsuarioHelper {
	
	private Paciente paciente;
	private EditText numSus;
	private CheckBox hipertenso;
	private CheckBox diabetico1;
	private CheckBox diabetico2;
	
	
	public EditText getNumSus() {
		return numSus;
	}

	public void setNumSus(EditText numSus) {
		this.numSus = numSus;
	}

	public PacienteHelper(PacienteDadosActivity fragmentActivity){
		super(fragmentActivity);
						
		//criacao do objeto paciente
		paciente = new Paciente();
		
		numSus =(EditText) fragmentActivity.findViewById(R.id.edit_sus);
		
		hipertenso =(CheckBox) fragmentActivity.findViewById(R.id.chbHipertenso);
		diabetico1 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo1);
		diabetico2 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo2);
		
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(getNome(), "Nome obrigatorio");
		mapaDeCampos.put(getEditTextDataNascimento(), "Campo obrigatorio");
		mapaDeCampos.put(getTelefone(), "Telefone obrigatorio");		
		mapaDeCampos.put(getCep(), "Cep obrigatorio");
		mapaDeCampos.put(numSus, "Campo obrigatorio");
		
		setMapaDeCampos(mapaDeCampos);
	}

	public Paciente getPaciente(){
		
		paciente.setNome(getNome().getText().toString());
		paciente.setEndereco(getEndereco().getText().toString());
		paciente.setNumero(getNumero().getText().toString());
		paciente.setCep(getCep().getText().toString());
		paciente.setCelular(getCelular().getText().toString());
		paciente.setTelefone(getTelefone().getText().toString());
		paciente.setNomeMae(getNomeMae().getText().toString());
		paciente.setNumSus(getNumSus().getText().toString());
		paciente.setDataNascimento(getDataNascimento());					
		paciente.setSexo( String.valueOf(getSexo().getSelectedItem()) );

		Bairro auxBairro = new Bairro();				
		TextView idBairro = (TextView)getSpinBairro().getSelectedView().findViewById(R.id.textID); 
		auxBairro.setId(Long.parseLong( idBairro.getText().toString() ) );
		auxBairro.setNome(getSpinBairro().getSelectedItem().toString());					
		paciente.setBairro(auxBairro);
		
		
		UnidadeSaude auxUbs = new UnidadeSaude();
		TextView idUbs = (TextView)getSpinUbs().getSelectedView().findViewById(R.id.textID); 
		auxUbs.setId(Long.parseLong( idUbs.getText().toString() ) );
		auxUbs.setNome(getSpinUbs().getSelectedItem().toString());					
		paciente.setUnidadeSaude(auxUbs);
		
		
		paciente.setTipoUsuario(TipoUsuario.PACIENTE);
		
		paciente.setLogin(paciente.getNome());
		paciente.setSenha(paciente.getNome());
		
		paciente.setHipertenso(hipertenso.isChecked());
		paciente.setDiabetico1(diabetico1.isChecked());
		paciente.setDiabetico2(diabetico2.isChecked());
		
		

		return (Paciente) paciente;		
	}
	
	public void setPaciente(Paciente paciente){
						
		getNome().setText(paciente.getNome());		
		getTelefone().setText(paciente.getTelefone());
		getEndereco().setText(paciente.getEndereco());	
		getNumero().setText(paciente.getNumero());
		getCep().setText(paciente.getCep() );
		getCelular().setText(paciente.getCelular());
		getTelefone().setText(paciente.getTelefone());										
		getNomeMae().setText(paciente.getNomeMae());
		getNumSus().setText(paciente.getNumSus());
		setDataNascimento(paciente.getDataNascimento());
		
		ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)getSexo().getAdapter();
		getSexo().setSelection(array_spinner.getPosition( paciente.getSexo() ));
	    				
		
		ArrayAdapter<String> array_bairro=(ArrayAdapter<String>)getSpinBairro().getAdapter();
		if(paciente.getBairro() !=null){
			getSpinBairro().setSelection(array_bairro.getPosition( paciente.getBairro().getNome() ) );
		}
		    						
		ArrayAdapter<String> array_ubs=(ArrayAdapter<String>)getSpinUbs().getAdapter();
		if(paciente.getUnidadeSaude() !=null){
			getSpinUbs().setSelection(array_ubs.getPosition( paciente.getUnidadeSaude().getNome() ) );
		}
		
		//Log.i("TESTE", "Sexo do Paciente: " +paciente.getSexo());
		Log.e("TESTE", "is Hipertenso: " + paciente.isHipertenso() );
		hipertenso.setChecked(paciente.isHipertenso() );
		diabetico1.setChecked(paciente.isDiabetico1() );
		diabetico2.setChecked(paciente.isDiabetico2() );
		
		this.paciente = paciente;	
	//Validando dados
	}
}