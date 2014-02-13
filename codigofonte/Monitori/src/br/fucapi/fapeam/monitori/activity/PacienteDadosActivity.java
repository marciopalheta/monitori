package br.fucapi.fapeam.monitori.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.xml.sax.DTDHandler;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.R.layout;
import br.fucapi.fapeam.monitori.R.menu;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.model.helper.PacienteHelper;
import br.fucapi.fapeam.monitori.model.helper.UsuarioHelper;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class PacienteDadosActivity extends FragmentActivity {

	//Atributos para manipulacao de tela
	private Button botao;
	private PacienteHelper helper;
	private Paciente pacienteParaSerAlterado = null;
	
	private Button btDataNascimento;
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pacientedados);
				
		//Criacao do objeto helper
		helper = new PacienteHelper(this);
		botao = (Button) findViewById(R.id.sbSalvar);
						
		// Busca o paciente a ser alterado
		pacienteParaSerAlterado = (Paciente) getIntent().getSerializableExtra(
						"PACIENTE_SELECIONADO");

				if (pacienteParaSerAlterado != null) {
					// Atualiza a tela com dados do Aluno
					helper.setPaciente(pacienteParaSerAlterado);
				}

		
		botao.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Utilizando o helper
				Paciente paciente = (Paciente) helper.getPaciente();
				//Criacao do objeto DAO
				UsuarioDAO dao = new UsuarioDAO(PacienteDadosActivity.this);
				
				//Validando os campos
				if(helper.validar()==true){		
					// Verificacao para salvar ou cadastrar o aluno
					if (paciente.getId() == null) {
						dao.cadastrar(paciente);
					} else {
						dao.alterar(paciente);
					}//Fechando a conexao com o BD
					dao.close();
					//Encerrando a activity
					finish();
				}}
		});
	}
			
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Definicao do menu inflater
		MenuInflater inflater = this.getMenuInflater();
		
		//Inflar um XML
		inflater.inflate(R.menu.menu_formularios, menu);
		
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // go home
	        	// ir Para o menu principal	            
	            /*
	        	Intent intent = new Intent(this, PacienteActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);*/
	            return true;
	        case R.id.menu_salvar:			
				//Utilizando o helper
				Paciente paciente = (Paciente) helper.getPaciente();
				//Criacao do objeto DAO
				UsuarioDAO dao = new UsuarioDAO(PacienteDadosActivity.this);
				
				//Validando os campos
				if(helper.validar()==true){		
					// Verificacao para salvar ou cadastrar o aluno
					if (paciente.getId() == null) {
						dao.cadastrar(paciente);
					} else {
						dao.alterar(paciente);
					}//Fechando a conexao com o BD
					dao.close();
					//Encerrando a activity
					finish();
				}
				return true;
			case R.id.menu_cancelar:			
				finish();
				return true;
	    }

	    return super.onOptionsItemSelected(item);
	}
		
}
