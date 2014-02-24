package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import android.app.Activity;
import android.widget.EditText;

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