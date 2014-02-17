package br.fucapi.fapeam.monitori.utils;


import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.dao.BairroDAO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BairroDialog extends DialogFragment {
	
	EditText nomeBairro;

	String savedStateNome;
	AlertDialog dialog;	
	View.OnClickListener clickListener = null;

	private Spinner spinBairro;

	private SpinnerAdapter adapter;
	

	public static BairroDialog newInstance() {
        return new BairroDialog();
    }

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
					       
		super.onCreate(savedInstanceState);
		setRetainInstance(true);			
	}
			
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
										
		//Log.e("Saved Estate1", "string salva = "+ savedNome);
		
		LayoutInflater inflater = LayoutInflater.from(getActivity());
        View layout = inflater.inflate(R.layout.bairrodados, null); //passamos o XML criado
        //View layout = inflater.inflate(R.layout.bairrodados, (ViewGroup) activity.findViewById(R.id.edNome));
        nomeBairro = (EditText) layout.findViewById(R.id.edNome);
        spinBairro = (Spinner) getActivity().findViewById(R.id.spinBairro);
        
        
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());		
		builder.setView(layout);				 		

		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		            dialog.dismiss();
		        }
		    });
		 
		builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        		        	
	        	
	        }
	    });
		dialog = builder.create();		
		
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {

		    @Override
		    public void onShow(DialogInterface dialogI) {

		        Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
		        b.setText("Salvar");
		        
		        if(clickListener==null){
			        b.setOnClickListener(new View.OnClickListener() {
	
			            @Override
			            public void onClick(View view) {
			            	
			            	if(Funcoes.validarDados(nomeBairro,  "Campo Obrigatorio" )){
					        	BairroDAO dao = new BairroDAO(getActivity());
					        	Bairro bairro = new Bairro();
					        	bairro.setNome(nomeBairro.getText().toString());
			            		dao.cadastrar(bairro);
			            		 //Toast.makeText(getActivity(), "nome Bairro = "+nomeBairro.getText().toString(), Toast.LENGTH_LONG).show();	
					        	 //CadastrarBairro
					        	 //atualizarListaBairros
			            		 atualizarListaBairro(nomeBairro.getText().toString());
					        	 dialog.dismiss();			            	
							}else{
								Toast.makeText(getActivity(), "Dados Invalidos ", Toast.LENGTH_LONG).show();
							}
			            }
			        });
		        }else{
		        	b.setOnClickListener(clickListener);
		        }
		    }
		});

		
		dialog.setTitle("Cadastrar Novo Bairro");
		// request keyboard   
		dialog.getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		
		return dialog;
	}		
	
	
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {		
		  
		super.onSaveInstanceState(savedInstanceState);
		
		savedInstanceState.putBoolean("MyBoolean", true);
		
	}
	
	
	
	@Override
	public void onResume() {
		super.onResume();
		Log.e("Saved Estate", "stringState salva = "+ savedStateNome);
		if(savedStateNome!=null){
			nomeBairro.setText(savedStateNome);
		}
		
	}
	
	/**
	 * workaround for issue #17423
	 */
	@Override
	public void onDestroyView() {
		if (getDialog() != null && getRetainInstance())
			getDialog().setOnDismissListener(null);
		
		savedStateNome = nomeBairro.getText().toString();		
		super.onDestroyView();
	}
	
	
	public View.OnClickListener getClickListener() {
		return clickListener;
	}


	public void setClickListener(View.OnClickListener clickListener) {
		this.clickListener = clickListener;
	}
	
	public EditText getNomeBairro() {
		return nomeBairro;
	}


	public void setNomeBairro(EditText nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	
	void atualizarListaBairro(String novoBairro){
		
		BairroDAO daoBairro = new BairroDAO(getActivity()); 
		List<Bairro> listaBairros = daoBairro.listar();										
		
		String[] stringArray = new String[listaBairros.size()+1];
		Integer[] intArray = new Integer[listaBairros.size()+1];
		int index = 0;
		int indexB = 0;
		for (Bairro bairro : listaBairros) {
			stringArray[index] = bairro.getNome();
			if(bairro.getNome().equals(novoBairro)){
				indexB = index;	
			}
			intArray[index] = Integer.parseInt(bairro.getId().toString());
		  index++;
		}				
		
		stringArray[index] = getActivity().getString(R.string.bairro_novo); 				
		intArray[index] = 0;
		adapter = new SpinnerAdapter(getActivity(), R.layout.spinner_bairro,stringArray,intArray);
	    spinBairro.setAdapter(adapter);	    	    
	    
		spinBairro.setSelection(indexB);

		
	}
	
}