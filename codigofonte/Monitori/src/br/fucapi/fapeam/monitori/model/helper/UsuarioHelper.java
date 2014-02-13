package br.fucapi.fapeam.monitori.model.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.DTDHandler;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.R.layout;
import br.fucapi.fapeam.monitori.R.menu;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.BairroDialog;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.Mask;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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
	private CheckBox isHipertenso;
	private Button novoBairro;
	private EditText nomeBairro; 		
	
	private static final String DATE_FORMAT = "dd/MM/yyyy";	    
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);           
    
	private Calendar dtCalendar = Calendar.getInstance();			
	
	private ImageView foto;
	private Spinner sexo;	
	private Usuario usuario;
	private Context context;		        		        
	private FragmentActivity fragmentActivity;
	
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
	
	public UsuarioHelper(final FragmentActivity fragmentActivity){
		//Associacao de campos de tela ao controller
		this.context = fragmentActivity;						
		this.fragmentActivity = fragmentActivity;
		nome = (EditText) fragmentActivity.findViewById(R.id.edNome);
		
			
		endereco = (EditText) fragmentActivity.findViewById(R.id.edEndereco);			
		bairro = (EditText) fragmentActivity.findViewById(R.id.chbBairro);
		cep = (EditText) fragmentActivity.findViewById(R.id.edCep);
		unidadeSaude = (EditText) fragmentActivity.findViewById(R.id.chbUnidadeSaude);
		celular = (EditText) fragmentActivity.findViewById(R.id.edCelular);
		telefone = (EditText) fragmentActivity.findViewById(R.id.edTefone);
		nomeMae = (EditText) fragmentActivity.findViewById(R.id.edNomedamae);
		numSus = (EditText) fragmentActivity.findViewById(R.id.edit_sus);
		
		dataNascimento = (EditText) fragmentActivity.findViewById(R.id.edDataNascimento); 				
		dataNascimento.addTextChangedListener(Mask.insert("##/##/####", dataNascimento));
		
		novoBairro = (Button) fragmentActivity.findViewById(R.id.novoBairro);
		novoBairro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getNovoBairro();
				
			}
		});
		
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
		
		
		sexo = (Spinner) fragmentActivity.findViewById(R.id.spinSexo);		
		
		String[] list = fragmentActivity.getResources().getStringArray(R.array.sexo_arrays);
		Integer[] imageSexo={R.drawable.ic_man,R.drawable.ic_woman};
				
		//adapter = new SpinnerAdapter(activity, R.layout.spinner_item,data1);
		adapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_item,list,imageSexo);
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

	
	private void getNovoBairro() {
				
		BairroDialog diag = BairroDialog.newInstance();
		
		diag.show(fragmentActivity.getSupportFragmentManager(), "dialg");
		
		
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
	
	
}