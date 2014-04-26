package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.paciente.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.dao.BairroDAO;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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
	private Spinner spinMedico;
	
	private Spinner spinUbs;
	private Activity activity;
	private int selectionCurrent;	
	
	public EditText getNumSus() {
		return numSus;
	}

	public void setNumSus(EditText numSus) {
		this.numSus = numSus;
	}

	public PacienteHelper(PacienteDadosActivity fragmentActivity){
		super(fragmentActivity,TipoUsuario.PACIENTE);
		this.activity = 	fragmentActivity;		
		//criacao do objeto paciente
		paciente = new Paciente();
		
		numSus =(EditText) fragmentActivity.findViewById(R.id.edit_sus);
		
		hipertenso =(CheckBox) fragmentActivity.findViewById(R.id.chbHipertenso);
		diabetico1 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo1);
		diabetico2 =(CheckBox) fragmentActivity.findViewById(R.id.chbTipo2);		
		nomeMae = (EditText) fragmentActivity.findViewById(R.id.edNomedamae);
		spinUbs = getSpinUbs();	
		spinMedico = (Spinner) fragmentActivity.findViewById(R.id.spinMedico);
		
		selectionCurrent = spinMedico.getSelectedItemPosition();
		spinMedico.setNextFocusDownId(R.id.edit_sus);
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
		
		spinMedico.setOnTouchListener(new View.OnTouchListener() {            
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(view !=null){
					
					Funcoes.hideKeyboard(spinMedico );
					spinMedico.requestFocusFromTouch();
					
					//return true;
				}
				return false;
			}
        });		
		
		spinMedico.setOnItemSelectedListener(new OnItemSelectedListener() {
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
			}
		);

		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(getNome(), "Nome Obrigatorio");
		mapaDeCampos.put(getCpf(), "CPF Obrigatorio");
		mapaDeCampos.put(getEditTextDataNascimento(), "Campo Obrigatorio");
		mapaDeCampos.put(getTelefone(), "Telefone Obrigatorio");		
		mapaDeCampos.put(getCep(), "Cep Obrigatorio");
		mapaDeCampos.put(numSus, "Campo Obrigatorio");
		
		setMapCamposObrigatorios(mapaDeCampos);
		setViewCamposObrigatorios();
		atualizarListaMedicos();
		
	}

	
	
	private void atualizarListaMedicos(){
		
		
		UsuarioDAO daoMedico = new UsuarioDAO(activity); 
		List<SpinnerObject> listaMedico = daoMedico.getUsuarioForSpinner(TipoUsuario.MEDICO);
							
		String[] StringArray = new String[listaMedico.size()];
		int index=0;
		for (SpinnerObject medicos : listaMedico) {			
			StringArray[index++] = medicos.toString();			
		}
		//ArrayAdapter dataAdapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_generic, StringArray ,listaBairros);
		//spinBairro.setAdapter(dataAdapter);
		
		adapter = new SpinnerAdapter(activity, R.layout.spinner_generic, StringArray ,listaMedico);
		spinMedico.setAdapter(adapter);	    	    	    			    
	    
	}
	
	public Spinner getSpinMedico() {
		return spinMedico;
	}

	public void setSpinMedico(Spinner spinMedico) {
		this.spinMedico = spinMedico;
	}

	public Paciente getPaciente(){
		
		paciente = (Paciente) getUsuario();
				
		paciente.setNomeMae(nomeMae.getText().toString());
							
		paciente.setTipoUsuario(TipoUsuario.PACIENTE);
		paciente.setNumSus(numSus.getText().toString());
		
		paciente.setHipertenso(hipertenso.isChecked());
		paciente.setDiabetico1(diabetico1.isChecked());
		paciente.setDiabetico2(diabetico2.isChecked());
		
		SpinnerObject SpinAux;
		int posicao;
		
		
		if(!getSpinMedico().getAdapter().isEmpty()){
			
			posicao = getSpinMedico().getSelectedItemPosition();
			SpinAux = (SpinnerObject)getSpinMedico().getAdapter().getItem(posicao);
			if(SpinAux.getId() != 0){
				Medico auxMedico = new Medico();
				auxMedico.setId( SpinAux.getId() );
				auxMedico.setNome( SpinAux.getValue() );
				paciente.setMedico(auxMedico);
			}
		}
		

		return (Paciente) paciente;		
	}
	
	public void setPaciente(Paciente paciente){
		setUsuario(paciente);
														
		nomeMae.setText(paciente.getNomeMae());
		numSus.setText(paciente.getNumSus());
								
		//Log.i("TESTE", "Sexo do Paciente: " +paciente.getSexo());
		Log.e("TESTE", "is Hipertenso: " + paciente.isHipertenso() );
		hipertenso.setChecked(paciente.isHipertenso() );
		diabetico1.setChecked(paciente.isDiabetico1() );
		diabetico2.setChecked(paciente.isDiabetico2() );
		
		int index=0, indexKey=0;
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSpinMedico().getAdapter();
		List<SpinnerObject> list_medico = adapter.getSpinnerObjects();
				
		if(paciente.getMedico() !=null){
			for (SpinnerObject soMedico : list_medico) {
				if(soMedico.getId() == paciente.getMedico().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		getSpinMedico().setSelection(indexKey);
		
		//paciente.setLogin(numSus.getText().toString());
		//paciente.setSenha(numSus.getText().toString());
		
		this.paciente = paciente;	
	//Validando dados
	}
}