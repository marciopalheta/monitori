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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class BairroDialog extends DialogFragment {
	
	EditText nomeBairro;
	
	String savedStateNome;
	AlertDialog dialog;	
	private List<Bairro> listaBairro;
	private ListView lvListagem;
	
	private Bairro bairroSelecionado;
	
	//private Spinner spinBairro;

	private ArrayAdapter<Bairro> adapter;
	
	public BairroDialog(){}
	
	
	public static BairroDialog newInstance() {
        return new BairroDialog();
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
					       
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		if(this.getArguments() != null){
			bairroSelecionado = (Bairro) this.getArguments().getSerializable(PutExtras.BAIRRO_SELECIONADO);	
		}
		
		
	}
			
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
												 
		BairroDAO daoBairro = new BairroDAO(getActivity()); 
		listaBairro = daoBairro.listar();
				
				
		LayoutInflater inflater = LayoutInflater.from(getActivity());
        View layout = inflater.inflate(R.layout.bairrodados, null); //passamos o XML criado
        nomeBairro = (EditText) layout.findViewById(R.id.edNome);
        
        lvListagem = (ListView) getActivity().findViewById(R.id.lvListagem);
        
        if(bairroSelecionado!=null){
        	nomeBairro.setText(bairroSelecionado.getNome());
        }
        dialog =  Bairro(layout);        
		
		return dialog;
	}		
	
	public void carregarLista(){
		//Criacao do objeto DAO
		BairroDAO dao = new BairroDAO(getActivity());
				
		//Chamado do metodo listar
		this.listaBairro = dao.listar();
		
		//fim da conexao do DB
		dao.close();
		
		//O objeto arrayadapter converte lista em view
		this.adapter = new ArrayAdapter<Bairro>(getActivity(), android.R.layout.simple_list_item_1 , listaBairro);
		//associacao do adapter ao listView
		this.lvListagem.setAdapter(adapter);
	}
	
	private AlertDialog Bairro(View layout){
		
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
		        		        
		        b.setOnClickListener(new View.OnClickListener() {
	
			            @Override
			            public void onClick(View view) {
			            	
			            	if(Funcoes.validarDados(nomeBairro,  "Campo Obrigatorio" )){
			            		String novoBairro = nomeBairro.getText().toString();
			            		for (Bairro bairros : listaBairro) {
			            			if( bairros.getNome().equalsIgnoreCase( novoBairro )){
			            				
			            					nomeBairro.setError("Bairro ja Cadastrado");
			            					nomeBairro.setFocusable(true);
			            					nomeBairro.requestFocus();			            							            				
			            				return;
			            			}			            			
			            		}
			            		
						        	BairroDAO dao = new BairroDAO(getActivity());
						        if(bairroSelecionado ==null){
						        	Bairro bairro = new Bairro();
						        	bairro.setNome(novoBairro);
				            		dao.cadastrar(bairro);
				            		carregarLista();
			            		}else{
			            			bairroSelecionado.setNome(novoBairro);
			            			dao.alterar(bairroSelecionado);
			            			carregarLista();
			            		}

					        	 dialog.dismiss();			            	
							}else{
								nomeBairro.setError("Dados Invalidos ");
            					nomeBairro.setFocusable(true);
            					nomeBairro.requestFocus();
            													
							}
			            }
			        });
		        }		    
		});

		if(bairroSelecionado==null){
			dialog.setTitle("Cadastrar Novo Bairro");
		}else{
			dialog.setTitle("Alterar Bairro");
		}
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
	
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (getDialog() != null && getRetainInstance())
			getDialog().setOnDismissListener(null);
		
		savedStateNome = nomeBairro.getText().toString();				
	}
	
			
}