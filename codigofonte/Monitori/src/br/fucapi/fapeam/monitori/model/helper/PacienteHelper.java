package br.fucapi.fapeam.monitori.model.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.paciente.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;

public class PacienteHelper extends UsuarioHelper {
	
	private Paciente paciente;
	private EditText numSus;	
	private EditText nomeMae;
	private CheckBox hipertenso;
	private CheckBox diabetico1;
	private CheckBox diabetico2;
	private Spinner spinUbs;
	private Activity activity;
	private int selectionCurrent;
	private br.fucapi.fapeam.monitori.utils.SpinnerAdapter adapter;
	
	public EditText getNumSus() {
		return numSus;
	}

	public void setNumSus(EditText numSus) {
		this.numSus = numSus;
	}

	public PacienteHelper(PacienteDadosActivity fragmentActivity){
		super(fragmentActivity);
		this.activity = 	fragmentActivity;		
		//criacao do objeto paciente
		paciente = new Paciente();
		
		numSus =(EditText) fragmentActivity.findViewById(R.id.edit_sus);
		
		hipertenso =(CheckBox) fragmentActivity.findViewById(R.id.chbHipertenso);
		diabetico1 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo1);
		diabetico2 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo2);
		nomeMae = getNomeMae();		
		spinUbs = getSpinUbs();		
		
		spinUbs.setNextFocusForwardId( R.id.edit_sus );
		selectionCurrent = spinUbs.getSelectedItemPosition();
		
		nomeMae.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView textView, int actionId,
					KeyEvent event) {
				
				if (actionId == EditorInfo.IME_ACTION_NEXT) {
					Funcoes.hideKeyboard(activity );
		            textView.clearFocus();		            
		            
		            spinUbs.requestFocus();		            
		            spinUbs.requestFocusFromTouch();		            
		            spinUbs.performClick();
		        }
		        return true;
								
			}
		});

		spinUbs.setOnTouchListener(new View.OnTouchListener() {            
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(view !=null){
					
					Funcoes.hideKeyboard(spinUbs );
					spinUbs.requestFocusFromTouch();
					
					//return true;
				}
				return false;
			}
        });		
		
		spinUbs.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
								
								
				if(selectedItemView !=null){
					if(selectionCurrent!=position){
						selectionCurrent = position;
						getNumSus().requestFocus();		            																						           
						getNumSus().performClick();
						//Funcoes.showKeyboard(activity);
						Funcoes.showKeyboard(getNumSus());
		            //showKeyboard
					}
					
				}
				

			} 			

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				
			}

			});
		

		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(getNome(), "Nome obrigatorio");
		mapaDeCampos.put(getEditTextDataNascimento(), "Campo obrigatorio");
		mapaDeCampos.put(getTelefone(), "Telefone obrigatorio");		
		mapaDeCampos.put(getCep(), "Cep obrigatorio");
		mapaDeCampos.put(numSus, "Campo obrigatorio");
		
		setMapaDeCampos(mapaDeCampos);
	}

		
	
	public Paciente getPaciente(){
		SpinnerObject SpinAux;
		int posicao;
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

				
		if(!getSpinBairro().getAdapter().isEmpty()){
					
					posicao = getSpinBairro().getSelectedItemPosition();
					SpinAux = (SpinnerObject)getSpinBairro().getAdapter().getItem(posicao);
					if(SpinAux.getId() != 0){
						Bairro auxBairro = new Bairro();
						auxBairro.setId( SpinAux.getId() );
						auxBairro.setNome( SpinAux.getValue() );
						paciente.setBairro(auxBairro);
					}
		}									
		
		
		if(!getSpinUbs().getAdapter().isEmpty()){
			posicao = getSpinUbs().getSelectedItemPosition();
			 
			SpinAux = (SpinnerObject)getSpinUbs().getAdapter().getItem(posicao);				
			if(SpinAux.getId() != 0){
				UnidadeSaude auxUbs = new UnidadeSaude();
				auxUbs.setId( SpinAux.getId() );
				auxUbs.setNome( SpinAux.getValue() );						
				
				paciente.setUnidadeSaude(auxUbs);
			}
		}
		
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
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSexo().getAdapter();
		List<SpinnerObject> list_sexo = adapter.getSpinnerObjects();
		int index=0, indexKey=0;		
		for (SpinnerObject sexo : list_sexo) {
			if(sexo.getValue().equals(paciente.getSexo()) ){
				indexKey = index;
				break;	
			}
			index++;					
		}
		getSexo().setSelection(indexKey);
			
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSpinBairro().getAdapter();
		List<SpinnerObject> list_bairro = adapter.getSpinnerObjects();
		index=0; indexKey=0;		
		if(paciente.getBairro() !=null){
			for (SpinnerObject sobairro : list_bairro) {
				if(sobairro.getId() == paciente.getBairro().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		getSpinBairro().setSelection(indexKey);
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSpinUbs().getAdapter();
		List<SpinnerObject> list_ubs = adapter.getSpinnerObjects();
		index=0; indexKey=0;		
		if(paciente.getUnidadeSaude() !=null){
			for (SpinnerObject soUbs: list_ubs) {
				if(soUbs.getId() == paciente.getUnidadeSaude().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		getSpinUbs().setSelection(indexKey);
				
		
		/*
		ArrayAdapter<String> array_bairro=(ArrayAdapter<String>)getSpinBairro().getAdapter();
		if(paciente.getBairro() !=null){
			getSpinBairro().setSelection(array_bairro.getPosition( paciente.getBairro().getNome() ) );
		}
		    						
		ArrayAdapter<String> array_ubs=(ArrayAdapter<String>)getSpinUbs().getAdapter();
		if(paciente.getUnidadeSaude() !=null){
			getSpinUbs().setSelection(array_ubs.getPosition( paciente.getUnidadeSaude().getNome() ) );
		}
		*/
		//Log.i("TESTE", "Sexo do Paciente: " +paciente.getSexo());
		Log.e("TESTE", "is Hipertenso: " + paciente.isHipertenso() );
		hipertenso.setChecked(paciente.isHipertenso() );
		diabetico1.setChecked(paciente.isDiabetico1() );
		diabetico2.setChecked(paciente.isDiabetico2() );
		
		this.paciente = paciente;	
	//Validando dados
	}
}