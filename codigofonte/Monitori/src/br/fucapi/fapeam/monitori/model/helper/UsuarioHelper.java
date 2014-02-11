package br.fucapi.fapeam.monitori.model.helper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.Mask;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.net.ParseException;
import android.text.Editable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class UsuarioHelper {
	private EditText nome;
	private EditText endereco;
	private EditText bairro;
	private EditText cep;
	private EditText unidadeSaude;
	private EditText celular;
	private EditText telefone;
	private EditText nomeMae;
	private EditText numSus;
	
	private EditText dataNascimento;		
	private ImageButton ibDataNascimento;	
	
	private static final String DATE_FORMAT = "dd/MM/yyyy";	    
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);           
    
	private Calendar dtCalendar = Calendar.getInstance();			
	
	private ImageView foto;
	private Spinner sexo;	
	private Usuario usuario;
	private Context context;		        		        
	
    //private Calendar myCalendar;

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

	private ArrayAdapter<String> adapter;
	
	public UsuarioHelper(final Activity activity){
		//Associacao de campos de tela ao controller
		this.context = activity;						
		
		nome = (EditText) activity.findViewById(R.id.edNome);
		
			
		endereco = (EditText) activity.findViewById(R.id.edEndereco);			
		bairro = (EditText) activity.findViewById(R.id.chbBairro);
		cep = (EditText) activity.findViewById(R.id.edCep);
		unidadeSaude = (EditText) activity.findViewById(R.id.chbUnidadeSaude);
		celular = (EditText) activity.findViewById(R.id.edCelular);
		telefone = (EditText) activity.findViewById(R.id.edTefone);
		nomeMae = (EditText) activity.findViewById(R.id.edNomedamae);
		numSus = (EditText) activity.findViewById(R.id.edit_sus);
		
		dataNascimento = (EditText) activity.findViewById(R.id.edDataNascimento); 				
		dataNascimento.addTextChangedListener(Mask.insert("##/##/####", dataNascimento));
		
		ibDataNascimento= (ImageButton) activity.findViewById(R.id.ibDataNascimento);
		
		ibDataNascimento.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            new DatePickerDialog( activity, date, dtCalendar
	                    .get(Calendar.YEAR), dtCalendar.get(Calendar.MONTH),
	                    dtCalendar.get(Calendar.DAY_OF_MONTH)).show();
	        }
	    });
		
		
		sexo = (Spinner) activity.findViewById(R.id.spinSexo);		
		
		String[] list = activity.getResources().getStringArray(R.array.sexo_arrays);
		Integer[] imageSexo={R.drawable.ic_man,R.drawable.ic_woman};
				
		//adapter = new SpinnerAdapter(activity, R.layout.spinner_item,data1);
		adapter = new SpinnerAdapter(activity, R.layout.spinner_item,list,imageSexo);
	    sexo.setAdapter(adapter);
	    //sexo.setAdapter(new MyAdapter(activity, R.layout.spinner_item, data1));
	    
		//Log.i("TESTE", "Sexo: " +sexo.getText() );
		Log.e("TESTE", "Sexo: " + String.valueOf(sexo.getSelectedItem()) );
		
		
		//foto = (ImageView) activity.findViewById(R.id.foto);				
		
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
		   // em qualquer outra condi��o � gerado um erro
		   edTexto.setError(mensagem);
		   edTexto.setFocusable(true);
		   edTexto.requestFocus();
		   return false;
		  }
		  return false;
		 }
	
	public EditText getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(EditText nomeMae) {
		this.nomeMae = nomeMae;
	}

	public EditText getNumSus() {
		return numSus;
	}

	public void setNumSus(EditText numSus) {
		this.numSus = numSus;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EditText getNome() {
		return nome;
	}

	public void setNome(EditText nome) {
		this.nome = nome;
	}

	public EditText getEndereco() {
		return endereco;
	}

	public void setEndereco(EditText endereco) {
		this.endereco = endereco;
	}

	public EditText getBairro() {
		return bairro;
	}

	public void setBairro(EditText bairro) {
		this.bairro = bairro;
	}

	public EditText getCep() {
		return cep;
	}

	public void setCep(EditText cep) {
		this.cep = cep;
	}

	public EditText getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(EditText unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
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

	public boolean validar(){
		List<View> listview;
		
		
		// cria o mapa
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		mapaDeCampos.put(nome, "Nome obrigatorio");
		mapaDeCampos.put(dataNascimento, "Campo obrigatorio");
		mapaDeCampos.put(telefone, "Telefone obrigatorio");		
		mapaDeCampos.put(cep, "Cep obrigatorio");
		
		
		
		for(View chave: mapaDeCampos.keySet()){
		    //System.out.println("chave: "+chave+", valor: "+mapaDeCampos.get(chave)+".");
		    if(validarDados(chave,  mapaDeCampos.get(chave) ) == false){
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
	
	
}