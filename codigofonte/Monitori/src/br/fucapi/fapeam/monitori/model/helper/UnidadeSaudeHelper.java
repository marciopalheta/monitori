package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.UnidadeSaudeDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class UnidadeSaudeHelper {
		
	private UnidadeSaude ubs;
	private EditText nome;
	private EditText fone;
	private EditText endereco;
	private EditText numero;
	private EditText cep;
	private Spinner bairro;
	
	
	
	public UnidadeSaudeHelper(UnidadeSaudeDadosActivity activity){		
						
		//criacao do objeto paciente
		ubs = new UnidadeSaude();
		
		nome =(EditText) activity.findViewById(R.id.edNome);
		fone =(EditText) activity.findViewById(R.id.edFone);
		endereco =(EditText) activity.findViewById(R.id.edEndereco);
		numero =(EditText) activity.findViewById(R.id.edNumero);
		cep =(EditText) activity.findViewById(R.id.edCep);
		
		
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
		mapaDeCampos.put(endereco, "Campo obrigatorio");
		mapaDeCampos.put(numero, "Telefone obrigatorio");
		mapaDeCampos.put(cep, "Cep obrigatorio");		
		
		for(View chave: mapaDeCampos.keySet()){
		    //System.out.println("chave: "+chave+", valor: "+mapaDeCampos.get(chave)+".");
		    if(validarDados(chave,  mapaDeCampos.get(chave) ) == false){
				return false;
			}
		}
				
			return true;		
		
	}
	
}