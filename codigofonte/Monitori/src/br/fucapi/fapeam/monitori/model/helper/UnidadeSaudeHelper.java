package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.dao.BairroDAO;
import br.fucapi.fapeam.monitori.utils.BairroDialog;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.Mask;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;

public class UnidadeSaudeHelper {
		
	private UnidadeSaude ubs;
	private EditText nome;
	private EditText fone;
	private EditText endereco;
	private EditText numero;
	private EditText cep;
	private Spinner spinBairro;
	
	private FragmentActivity fragmentActivity;	

	SpinnerAdapter adapter;
	private boolean onCreateFlag = true;
	
	public UnidadeSaudeHelper(final FragmentActivity fragmentActivity){		
		
		this.fragmentActivity = fragmentActivity;
		
		//criacao do objeto paciente
		ubs = new UnidadeSaude();
		
		nome =(EditText) fragmentActivity.findViewById(R.id.edNome);
		fone =(EditText) fragmentActivity.findViewById(R.id.edFone);
		fone.addTextChangedListener(Mask.insert("(##)####-####", fone));
		
		endereco =(EditText) fragmentActivity.findViewById(R.id.edEndereco);
		numero =(EditText) fragmentActivity.findViewById(R.id.edNumero);		
		cep = (EditText) fragmentActivity.findViewById(R.id.edCep);
		cep.addTextChangedListener(Mask.insert("#####-###", cep));
					
		
		spinBairro = (Spinner) fragmentActivity.findViewById(R.id.spinBairro);
		
		
		spinBairro.setOnTouchListener(new View.OnTouchListener() {            
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(view !=null){
					Funcoes.hideKeyboard(fragmentActivity );
					spinBairro.requestFocusFromTouch();										 
				}
				return false;
			}
        });
		
		spinBairro.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
								
								
				if(selectedItemView !=null){
										
						if(!onCreateFlag ){
							spinBairro.clearFocus();
				            //sexo.requestFocus();
				            //sexo.requestFocusFromTouch();
				            //sexo.performClick();
						}else{
							onCreateFlag = false;
						}												           
					
				}
				

			} 			

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				
			}

			});
		

		
		cep.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView textView, int actionId,
					KeyEvent event) {
				
				if (actionId == EditorInfo.IME_ACTION_NEXT) {
					Funcoes.hideKeyboard(fragmentActivity );
		            textView.clearFocus();		            
		            
		            spinBairro.requestFocus();		            
		            spinBairro.requestFocusFromTouch();
		            
		            spinBairro.performClick();
		        }
		        return true;
								
			}
		});
		
		
		atualizarListaBairros();
		
		
	}

	private void atualizarListaBairros(){
		
		
		BairroDAO daoBairro = new BairroDAO(fragmentActivity); 
		List<SpinnerObject> listaBairros = daoBairro.getBairrosForSpinner();
							
		String[] StringArray = new String[listaBairros.size()];
		int index=0;
		for (SpinnerObject bairros : listaBairros) {			
			StringArray[index++] = bairros.toString();			
		}
		//ArrayAdapter dataAdapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_generic, StringArray ,listaBairros);
		//spinBairro.setAdapter(dataAdapter);
		
		adapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_generic, StringArray ,listaBairros);
		spinBairro.setAdapter(adapter);	    	    	    			    
	    
	}
	
	
	public UnidadeSaude getUnidadeSaude(){
		SpinnerObject SpinAux;
		int posicao;
		
		ubs.setNome( nome.getText().toString());
		ubs.setEndereco(endereco.getText().toString());
		ubs.setCep(cep.getText().toString());
		
		ubs.setNumeroUBS( numero.getText().toString());
		ubs.setFone(fone.getText().toString());				
		
		
		posicao = spinBairro.getSelectedItemPosition();
		SpinAux = (SpinnerObject)spinBairro.getAdapter().getItem(posicao);
						
		Bairro auxBairro = new Bairro();
		auxBairro.setId( SpinAux.getId() );
		auxBairro.setNome( SpinAux.getValue() );
		
		ubs.setBairro(auxBairro);
		
		return ubs;		
	}
	
	public void setUnidadeSaude(UnidadeSaude ubs){
						
		nome.setText(ubs.getNome());		
		fone.setText(ubs.getFone());
		endereco.setText(ubs.getEndereco());		
		cep.setText(ubs.getCep() );
		
		numero.setText(ubs.getNumeroUBS());
				
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) spinBairro.getAdapter();
		List<SpinnerObject> list_bairro = adapter.getSpinnerObjects();
		int index=0, indexKey=0;		
		if(ubs.getBairro() !=null){
			for (SpinnerObject sobairro : list_bairro) {
				if(sobairro.getId() == ubs.getBairro().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		spinBairro.setSelection(indexKey);
		
	    	
		this.ubs = ubs;			
	}
	
	public boolean validarDados(View view, String mensagem) {
		  if (view instanceof EditText) {
			  EditText edTexto = (EditText) view;
			  Editable texto = edTexto.getText();
			  if (texto != null) {
				  String strTexto = texto.toString();
				  if (!TextUtils.isEmpty(strTexto)) {
					  return true;
				  }
			  }
			  // em qualquer outra condição é gerado um erro
			  edTexto.setError(mensagem);
			  edTexto.setFocusable(true);
			  edTexto.requestFocus();
			  return false;
		  }
		  return false;
	}
	
	public boolean validar(){
				
		// cria o mapa
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		mapaDeCampos.put(nome, "Nome obrigatorio");		
		mapaDeCampos.put(fone, "Telefone obrigatorio");
		//mapaDeCampos.put(endereco, "Campo obrigatorio");
		//mapaDeCampos.put(numero, "Telefone obrigatorio");
		//mapaDeCampos.put(cep, "Cep obrigatorio");		
		
		for(View chave: mapaDeCampos.keySet()){
		    //System.out.println("chave: "+chave+", valor: "+mapaDeCampos.get(chave)+".");
		    if(validarDados(chave,  mapaDeCampos.get(chave) ) == false){
				return false;
			}
		}
			return true;
	}
		
	
}