package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.UnidadeSaudeDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.dao.BairroDAO;
import br.fucapi.fapeam.monitori.utils.BairroDialog;
import br.fucapi.fapeam.monitori.utils.Mask;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class UnidadeSaudeHelper {
		
	private UnidadeSaude ubs;
	private EditText nome;
	private EditText fone;
	private EditText endereco;
	private EditText numero;
	private EditText cep;
	private Spinner spinBairro;
	private int selectionCurrent;
	private FragmentActivity fragmentActivity;	
	private ArrayAdapter<String> adapter;
	
	
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
		atualizarListaBairros();
		
		selectionCurrent = spinBairro.getSelectedItemPosition();
		
		spinBairro.setOnTouchListener(new View.OnTouchListener() {            
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(view !=null){					
					TextView textId = (TextView)view.findViewById(R.id.textID);
					 if(event.getAction()==MotionEvent.ACTION_UP){
						 if (textId.getText().toString().equals("0") ){						
								getNovoBairro();
								return true;
						   }
		              }
		            
				}
				return false;
			}
        });
		
		spinBairro.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
								
				if(selectedItemView !=null){
					if(selectionCurrent!=position){
						TextView textId = (TextView)selectedItemView.findViewById(R.id.textID);
						
						if (textId.getText().toString().equals("0") ){
							spinBairro.setSelection(0);						
							//Toast.makeText(fragmentActivity, "funcionou", Toast.LENGTH_LONG).show();
							getNovoBairro();
					    }
					}
				}
			} 			

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
			    // your code here
			}

			});

		
	}

	private void atualizarListaBairros(){
		BairroDAO daoBairro = new BairroDAO(fragmentActivity); 
		List<Bairro> listaBairros = daoBairro.listar();										
		
		String[] stringArray = new String[listaBairros.size()+1];
		Integer[] intArray = new Integer[listaBairros.size()+1];
		int index = 0;
		int indexB = 0;
		for (Bairro bairro : listaBairros) {
			stringArray[index] = bairro.getNome();			
			intArray[index] = Integer.parseInt(bairro.getId().toString());
		  index++;
		}				
		
		stringArray[index] = fragmentActivity.getString(R.string.bairro_novo); 				
		intArray[index] = 0;
		adapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_generic,stringArray,intArray);
	    spinBairro.setAdapter(adapter);	    	    	    			    
	    
	}
	
	public UnidadeSaude getUnidadeSaude(){
		
		ubs.setNome( nome.getText().toString());
		ubs.setEndereco(endereco.getText().toString());
		ubs.setCep(cep.getText().toString());
		
		ubs.setNumeroUBS( numero.getText().toString());
		ubs.setFone(fone.getText().toString());				

		return ubs;		
	}
	
	public void setUnidadeSaude(UnidadeSaude ubs){
						
		nome.setText(ubs.getNome());		
		fone.setText(ubs.getFone());
		endereco.setText(ubs.getEndereco());		
		cep.setText(ubs.getCep() );
		
		numero.setText(ubs.getNumeroUBS());
		//bairro.set		
		//ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)getSexo().getAdapter();
		//bairro.setSelection(array_spinner.getPosition( paciente.getSexo() ));
	    	
		
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
		List<View> listview;
		
		
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
	
	private void getNovoBairro() {
		
		final BairroDialog diag = BairroDialog.newInstance();		
		diag.show(fragmentActivity.getSupportFragmentManager(), "dialg");		
				
	}
	
}