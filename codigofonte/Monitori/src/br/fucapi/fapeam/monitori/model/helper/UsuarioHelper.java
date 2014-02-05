package br.fucapi.fapeam.monitori.model.helper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ParseException;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UsuarioHelper {
	private EditText nome;
	private EditText endereco;
	private EditText bairro;
	private EditText cep;
	private EditText unidadeSaude;
	private EditText celular;
	private EditText telefone;
	//private EditText dataNascimento;	
	private Button btDataNascimento;
	private Calendar dataNascimento = Calendar.getInstance();	
	private ImageView foto;
	private RadioButton masculino;
	private RadioButton feminino;
	private RadioButton sexo;	
	private RadioGroup rgSexo;
	private Usuario usuario;
	private Context context;		        	
	
    //private Calendar myCalendar;

	private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	            int dayOfMonth) {
	        // TODO Auto-generated method stub
	    	dataNascimento.set(Calendar.YEAR, year);
	    	dataNascimento.set(Calendar.MONTH, monthOfYear);
	    	dataNascimento.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	        updateLabel();
	    }

	};

	
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
		
		masculino = (RadioButton) activity.findViewById(R.id.rbMasc);
		feminino = (RadioButton) activity.findViewById(R.id.rbFeminino);
		rgSexo = (RadioGroup) activity.findViewById(R.id.radioSex);
		sexo = (RadioButton) activity.findViewById(rgSexo.getCheckedRadioButtonId());
		
		btDataNascimento= (Button) activity.findViewById(R.id.btDataNascimento);
		
		btDataNascimento.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            new DatePickerDialog( activity, date, dataNascimento
	                    .get(Calendar.YEAR), dataNascimento.get(Calendar.MONTH),
	                    dataNascimento.get(Calendar.DAY_OF_MONTH)).show();
	        }
	    });
		
		Log.i("TESTE", "Sexo: " +sexo.getText() );
		
		//foto = (ImageView) activity.findViewById(R.id.foto);				
		
	}

	 private void updateLabel() {
		 			 			 
		 	String dateForButton = null;		 			 			 			 			 	
		 	dateForButton = DateUtils.formatDateTime(context, dataNascimento.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR);		 	
	        btDataNascimento.setText(dateForButton);	        	       
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

	public RadioButton getMasculino() {
		return masculino;
	}

	public void setMasculino(RadioButton masculino) {
		this.masculino = masculino;
	}

	public RadioButton getFeminino() {
		return feminino;
	}

	public void setFeminino(RadioButton feminino) {
		this.feminino = feminino;
	}

	public RadioButton getSexo() {
		return sexo;
	}


	public void setSexo(RadioButton sexo) {
		this.sexo = sexo;
	}


	public RadioGroup getRgSexo() {
		return rgSexo;
	}


	public void setRgSexo(RadioGroup rgSexo) {
		this.rgSexo = rgSexo;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		if(dataNascimento!=null){
			this.dataNascimento = dataNascimento;
			updateLabel();
		}
	}

	
}