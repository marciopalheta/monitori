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

public class PacienteDadosActivity extends Activity {

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
	
	public boolean onOptionsItemSelected(MenuItem item){
		//Verifica o item do menu selecionado
		switch(item.getItemId()){
			//Verifica se foi selecionado um item novo			
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
				return false;
			case R.id.menu_cancelar:			
				finish();
				return false;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
}
