package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.R.layout;
import br.fucapi.fapeam.monitori.R.menu;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.model.helper.UsuarioHelper;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PacienteDadosActivity extends Activity {

	//Atributos para manipulacao de tela
	private Button botao;
	private UsuarioHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pacientedados);
		//Criacao do objeto helper
		helper = new UsuarioHelper(this);
		botao = (Button) findViewById(R.id.sbSalvar);
		botao.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Utilizando o helper
				Paciente paciente = (Paciente) helper.getUsuario();
				//Criacao do objeto DAO
				UsuarioDAO dao = new UsuarioDAO(PacienteDadosActivity.this);
				//Chamado do metodo cadastrar
				dao.cadastrar(paciente);
				//Fechando a conexao com o BD
				dao.close();
				//Encerrando a activity
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paciente_dados, menu);
		return true;
	}

}
