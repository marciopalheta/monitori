package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class BairroHelper {
		
	private Bairro bairro;
	private EditText nome;
	
	public BairroHelper(Activity activity){
						
		//criacao do objeto paciente
		bairro = new Bairro();
		
		nome =(EditText) activity.findViewById(R.id.edNome);
		
	}

	public Bairro getBairro(){
		
		bairro.setNome(nome.getText().toString());
		
		return bairro;		
	}
	
	public void setBairro(Bairro bairro){
						
		nome.setText(bairro.getNome());		
		
		this.bairro = bairro;
					
	}
}