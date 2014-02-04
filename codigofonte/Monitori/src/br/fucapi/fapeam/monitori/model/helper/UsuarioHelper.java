package br.fucapi.fapeam.monitori.model.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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
	private Button dataNascimento;
	
	private ImageView foto;
	private RadioButton masculino;
	private RadioButton feminino;
	private RadioButton sexo;	
	private RadioGroup rgSexo;
	private Usuario usuario;
	private Context context;
	
	private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String TIME_FORMAT = "kk:mm";
	
	private Calendar myCalendar = Calendar.getInstance();

	private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	            int dayOfMonth) {
	        // TODO Auto-generated method stub
	        myCalendar.set(Calendar.YEAR, year);
	        myCalendar.set(Calendar.MONTH, monthOfYear);
	        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	        updateLabel();
	    }

	};

	
	public UsuarioHelper(final Activity activity){
		//Associacao de campos de tela ao controller
		this.context = context;
		
		nome = (EditText) activity.findViewById(R.id.edNome);
		endereco = (EditText) activity.findViewById(R.id.edEndereco);			
		bairro = (EditText) activity.findViewById(R.id.chbBairro);
		cep = (EditText) activity.findViewById(R.id.edCep);
		unidadeSaude = (EditText) activity.findViewById(R.id.chbUnidadeSaude);
		celular = (EditText) activity.findViewById(R.id.edCelular);
		telefone = (EditText) activity.findViewById(R.id.edTefone);
		dataNascimento = (Button) activity.findViewById(R.id.btDataNascimento);
		masculino = (RadioButton) activity.findViewById(R.id.rbMasc);
		feminino = (RadioButton) activity.findViewById(R.id.rbFeminino);
		rgSexo = (RadioGroup) activity.findViewById(R.id.radioSex);
		sexo = (RadioButton) activity.findViewById(rgSexo.getCheckedRadioButtonId());
		
		dataNascimento = (Button) activity.findViewById(R.id.btDataNascimento);
		dataNascimento.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            new DatePickerDialog( activity, date, myCalendar
	                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
	                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
	        }
	    });
		
		Log.i("TESTE", "Sexo: " +sexo.getText() );
		
		//foto = (ImageView) activity.findViewById(R.id.foto);				
		
	}

	 private void updateLabel() {

		 	SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	        String dateForButton = dateFormat.format(myCalendar.getTime());
	        dataNascimento.setText(dateForButton);	        	       
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
	
}