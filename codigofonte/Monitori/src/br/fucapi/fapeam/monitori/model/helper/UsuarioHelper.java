package br.fucapi.fapeam.monitori.model.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.BairroDAO;
import br.fucapi.fapeam.monitori.utils.BairroDialog;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.Mask;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UsuarioHelper {
	private EditText nome;
	private EditText cpf;
	private EditText endereco;
	private EditText numero;
	private EditText cep;	
	private EditText celular;
	private EditText telefone;
	
	private EditText dataNascimento;		
	private ImageButton ibDataNascimento;	
	private Spinner spinBairro;			
	private Spinner spinUbs;
			
	private static final String DATE_FORMAT = "dd/MM/yyyy";	    
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);           
    
	private Calendar dtCalendar = Calendar.getInstance();			
			
	private ImageView foto;
	private Spinner sexo;	
	private Usuario usuario;
	private Context context;		        		        
	private FragmentActivity fragmentActivity;
	private boolean onCreateFlag = true;
	
    //private Calendar myCalendar;


	private Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
	
	public Map<View, String> getMapaDeCampos() {
		return mapaDeCampos;
	}

	public void setMapaDeCampos(Map<View, String> mapaDeCampos) {
		this.mapaDeCampos = mapaDeCampos;
	}

	private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	            int dayOfMonth) {
	        // TODO Auto-generated method stub
	    	dtCalendar.set(Calendar.YEAR, year);
	    	dtCalendar.set(Calendar.MONTH, monthOfYear);
	    	dtCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	        updateLabel();
	    }

	};

	//private ArrayAdapter<String> adapter;
	SpinnerAdapter adapter;
	
	public UsuarioHelper(final FragmentActivity fragmentActivity, TipoUsuario tipoUsuario){
		//Associacao de campos de tela ao controller
		this.context = fragmentActivity;						
		this.fragmentActivity = fragmentActivity;
		
		if(tipoUsuario == TipoUsuario.PACIENTE){
			usuario = new Paciente();
		}else if(tipoUsuario == TipoUsuario.AGENTE){
			usuario = new Agente();
		}else if(tipoUsuario == TipoUsuario.MEDICO){
			usuario = new Medico();
		}else{
			usuario = new Usuario();
		}
		nome = (EditText) fragmentActivity.findViewById(R.id.edNome);
		
		foto = (ImageView) fragmentActivity.findViewById(R.id.foto);
				
		cpf = (EditText) fragmentActivity.findViewById(R.id.edCpf);
		cpf.addTextChangedListener(Mask.insert("###.###.###-##", cpf));
		
		endereco = (EditText) fragmentActivity.findViewById(R.id.edEndereco);					
		numero = (EditText) fragmentActivity.findViewById(R.id.edNumero);
		
		cep = (EditText) fragmentActivity.findViewById(R.id.edCep);
		cep.addTextChangedListener(Mask.insert("#####-###", cep));
		
		celular = (EditText) fragmentActivity.findViewById(R.id.edCelular);
		celular.addTextChangedListener(Mask.insert("(##)####-####", celular));
		
		telefone = (EditText) fragmentActivity.findViewById(R.id.edTefone);
		telefone.addTextChangedListener(Mask.insert("(##)####-####", telefone));
		
		
		//numSus = (EditText) fragmentActivity.findViewById(R.id.edit_sus);
		
		dataNascimento = (EditText) fragmentActivity.findViewById(R.id.edDataNascimento); 				
		dataNascimento.addTextChangedListener(Mask.insert("##/##/####", dataNascimento));
						
		ibDataNascimento= (ImageButton) fragmentActivity.findViewById(R.id.ibDataNascimento);
		
		ibDataNascimento.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            new DatePickerDialog( fragmentActivity, date, dtCalendar
	                    .get(Calendar.YEAR), dtCalendar.get(Calendar.MONTH),
	                    dtCalendar.get(Calendar.DAY_OF_MONTH)).show();
	        }
	    });
		
		
		spinUbs = (Spinner) fragmentActivity.findViewById(R.id.spinUbs);		
		spinBairro = (Spinner) fragmentActivity.findViewById(R.id.spinBairro);
		sexo = (Spinner) fragmentActivity.findViewById(R.id.spinSexo);		
		
		sexo.setOnTouchListener(new View.OnTouchListener() {            
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(view !=null){
					
					Funcoes.hideKeyboard(fragmentActivity );
					sexo.requestFocusFromTouch();
					
					//return true;
				}
				return false;
			}
        });
						
				
		spinBairro.setOnTouchListener(new View.OnTouchListener() {            
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(view !=null){
					Funcoes.hideKeyboard(fragmentActivity );
					spinBairro.requestFocusFromTouch();
					/*
					TextView textId = (TextView)view.findViewById(R.id.textID);
					 if(event.getAction()==MotionEvent.ACTION_UP){
						 if (textId.getText().toString().equals("0") ){						
								getNovoBairro();
								return true;
						   }
		              }*/					 
				}
				return false;
			}
        });
		
		spinBairro.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
								
								
				if(selectedItemView !=null){
										
					//if(position != spinBairro.getCount()-1 ){
						if(!onCreateFlag){
							spinBairro.clearFocus();
				            sexo.requestFocus();
				            sexo.requestFocusFromTouch();
				            sexo.performClick();
						}else{
							onCreateFlag = false;
						}
					//}else{
						//spinBairro.setSelection(0);
						//getNovoBairro();
					//}
												           
					
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

		
		
		//String[] list = fragmentActivity.getResources().getStringArray(R.array.sexo_arrays);
		//Integer[] imageSexo={R.drawable.ic_man,R.drawable.ic_woman};
		
		List<SpinnerObject> listSexo = new ArrayList<SpinnerObject>();
		listSexo.add( new SpinnerObject(fragmentActivity.getResources().getString(R.string.masculino ) , R.drawable.ic_man ) );
		listSexo.add( new SpinnerObject(fragmentActivity.getResources().getString(R.string.feminino ) , R.drawable.ic_woman ) );
								
		String[] StringArray = new String[listSexo.size()];
		int i=0;
		for(SpinnerObject s: listSexo){
			StringArray[i++] = s.toString();
		}				
		
		adapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_sexo,StringArray,listSexo );		
	    sexo.setAdapter(adapter);
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
	
	 private void updateLabel() {
		 			 			 
		 	String dateForButton = null;		 			 			 			 			 	
		 			 					
			if(dtCalendar!=null){
				dateForButton = dateFormat.format(dtCalendar.getTime());
			}else{
				dateForButton = context.getString(R.string.dtNascimento);
			}
		 			 	
			dataNascimento.setText(dateForButton);	        	       
	 }		
		
	public EditText getEditTextDataNascimento() {
		return dataNascimento;
	}

	public void setEditTextDataNascimento(EditText dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
			

	public EditText getNome() {
		return nome;
	}

	public void setNome(EditText nome) {
		this.nome = nome;
	}

	public EditText getCpf() {
		return cpf;
	}

	public void setCpf(EditText cpf) {
		this.cpf = cpf;
	}
	
	public EditText getEndereco() {
		return endereco;
	}

	public void setEndereco(EditText endereco) {
		this.endereco = endereco;
	}

	
	public EditText getNumero() {
		return numero;
	}

	public void setNumero(EditText numero) {
		this.numero = numero;
	}

	public EditText getCep() {
		return cep;
	}

	public void setCep(EditText cep) {
		this.cep = cep;
	}


	public EditText getCelular() {
		return celular;
	}

	public void setCelular(EditText celular) {
		this.celular = celular;
	}

	public EditText getTelefone() {
		return telefone;
	}

	public void setTelefone(EditText telefone) {
		this.telefone = telefone;
	}
	
	public ImageView getFoto() {
		return foto;
	}

	public void setFoto(ImageView foto) {
		this.foto = foto;
	}

	public Calendar getDataNascimento() {
			String dtNascto = null;				
			dtNascto = dataNascimento.getText().toString();		
			
			try {
				if(dtNascto !=null){
					dtCalendar.setTime(dateFormat.parse(dtNascto));
					return dtCalendar;
				}
			} catch (java.text.ParseException e) {
				
				Log.e("ERRO PARSE", e.getMessage());
			}
										
		return null;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		if(dataNascimento!=null){
			this.dtCalendar = dataNascimento;			
			updateLabel();
		}
		
	}

	
	private void getNovoBairro() {
				
		final BairroDialog diag = BairroDialog.newInstance();		
		diag.show(fragmentActivity.getSupportFragmentManager(), "dialg");		
				
	}
	
	public boolean validar(){
		// cria o mapa

		//Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(nome, "Nome obrigatorio");
		mapaDeCampos.put(dataNascimento, "Campo obrigatorio");
		mapaDeCampos.put(telefone, "Telefone obrigatorio");		
		mapaDeCampos.put(cep, "Cep obrigatorio");
			
		for(View chave: mapaDeCampos.keySet()){
		    //System.out.println("chave: "+chave+", valor: "+mapaDeCampos.get(chave)+".");
		    if(Funcoes.validarDados(chave,  mapaDeCampos.get(chave) ) == false){
				return false;
			}
		}
				
			return true;		
		
	}

	public Spinner getSexo() {
		return sexo;
	}

	public void setSexo(Spinner sexo) {
		this.sexo = sexo;
	}

	public Spinner getSpinBairro() {
		return spinBairro;
	}

	public void setSpinBairro(Spinner spinBairro) {
		this.spinBairro = spinBairro;
	}
	
	public Spinner getSpinUbs() {
		return spinUbs;
	}

	public void setSpinUbs(Spinner spinUbs) {
		this.spinUbs= spinUbs;
	}
	

	public void carregarFoto(String localFoto) {
		try {			
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();									
			
			// downsizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 8;			
			Bitmap bitmapFoto = BitmapFactory.decodeFile(localFoto,
					options);			
						
			if(bitmapFoto==null){
				Toast.makeText(context, "Impossivel abrir esse arquivo", Toast.LENGTH_SHORT).show();
				return;
			}else{
				
				try {
					bitmapFoto = Funcoes.applyOrientation(bitmapFoto,Funcoes.resolveBitmapOrientation(localFoto) );
					
					if(bitmapFoto.getWidth() < 100){
						bitmapFoto = BitmapFactory.decodeFile(localFoto);
					}
										
				} catch (IOException e) {
					Log.d("IMAGE_NOT_FOUND", e.getMessage() );
					
				}
			}			
				
			usuario.setFoto(localFoto);
			foto.setImageBitmap(bitmapFoto);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	
	public Usuario getUsuario(){
		SpinnerObject SpinAux;
		int posicao;
		usuario.setNome(getNome().getText().toString());				
		
		usuario.setEndereco(getEndereco().getText().toString());
		usuario.setNumero(getNumero().getText().toString());
		usuario.setCep(getCep().getText().toString());
		usuario.setCelular(getCelular().getText().toString());
		usuario.setTelefone(getTelefone().getText().toString());		
		
		usuario.setDataNascimento(getDataNascimento());					
		usuario.setCpf(cpf.getText().toString());		
		
		usuario.setSexo( String.valueOf(getSexo().getSelectedItem()) );
				
		if(!getSpinBairro().getAdapter().isEmpty()){
					
					posicao = getSpinBairro().getSelectedItemPosition();
					SpinAux = (SpinnerObject)getSpinBairro().getAdapter().getItem(posicao);
					if(SpinAux.getId() != 0){
						Bairro auxBairro = new Bairro();
						auxBairro.setId( SpinAux.getId() );
						auxBairro.setNome( SpinAux.getValue() );
						usuario.setBairro(auxBairro);
					}
		}									
		
		
		if(!getSpinUbs().getAdapter().isEmpty()){
			posicao = getSpinUbs().getSelectedItemPosition();
			 
			SpinAux = (SpinnerObject)getSpinUbs().getAdapter().getItem(posicao);				
			if(SpinAux.getId() != 0){
				UnidadeSaude auxUbs = new UnidadeSaude();
				auxUbs.setId( SpinAux.getId() );
				auxUbs.setNome( SpinAux.getValue() );						
				
				usuario.setUnidadeSaude(auxUbs);
			}
		}
				
		
		//usuario.setLogin(usuario.getCpf());
		//usuario.setSenha(usuario.getCpf());
		usuario.setLogin(usuario.getCpf());
		usuario.setSenha(usuario.getCpf());				
				
		return usuario;		
	}

	
	public void setUsuario(Usuario usuario){
		
		getNome().setText(usuario.getNome());		
		getTelefone().setText(usuario.getTelefone());
		getEndereco().setText(usuario.getEndereco());	
		getNumero().setText(usuario.getNumero());
		getCep().setText(usuario.getCep() );
		getCelular().setText(usuario.getCelular());
		getTelefone().setText(usuario.getTelefone());										
		

		if (usuario.getFoto() != null) {
			carregarFoto(usuario.getFoto());
		}
		
		cpf.setText(usuario.getCpf());
		
		setDataNascimento(usuario.getDataNascimento());
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSexo().getAdapter();
		List<SpinnerObject> list_sexo = adapter.getSpinnerObjects();
		int index=0, indexKey=0;		
		for (SpinnerObject sexo : list_sexo) {
			if(sexo.getValue().equals(usuario.getSexo()) ){
				indexKey = index;
				break;	
			}
			index++;					
		}
		getSexo().setSelection(indexKey);
			
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSpinBairro().getAdapter();
		List<SpinnerObject> list_bairro = adapter.getSpinnerObjects();
		index=0; indexKey=0;		
		if(usuario.getBairro() !=null){
			for (SpinnerObject sobairro : list_bairro) {
				if(sobairro.getId() == usuario.getBairro().getId() ){
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
		if(usuario.getUnidadeSaude() !=null){
			for (SpinnerObject soUbs: list_ubs) {
				if(soUbs.getId() == usuario.getUnidadeSaude().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		getSpinUbs().setSelection(indexKey);						
		
		this.usuario = usuario;	
	//Validando dados
	}
	
	
}