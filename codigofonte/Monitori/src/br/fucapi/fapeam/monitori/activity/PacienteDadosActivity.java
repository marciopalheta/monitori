package br.fucapi.fapeam.monitori.activity;

import java.util.Calendar;

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
import android.util.Log;
import android.view.Menu;
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
	
	static final int DATE_DIALOG_ID = 0;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pacientedados);
		//Criacao do objeto helper
		helper = new PacienteHelper(this);
		botao = (Button) findViewById(R.id.sbSalvar);
		
		btDataNascimento = (Button) findViewById(R.id.btDataNascimento);
		btDataNascimento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v == btDataNascimento)
					
					showDialog(DATE_DIALOG_ID);
			}
		});
		
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
				
				
				// Verificacao para salvar ou cadastrar o aluno
				if (paciente.getId() == null) {
					dao.cadastrar(paciente);
				} else {
					dao.alterar(paciente);
				}

				//Fechando a conexao com o BD
				dao.close();
				//Encerrando a activity
				finish();
			}
		});
	}
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar calendario = Calendar.getInstance();
		
		int ano = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, ano, mes,
					dia);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
				
			String data = String.valueOf(dayOfMonth) + " /"
					+ String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
			btDataNascimento.setText(data);
			
			Toast.makeText(PacienteDadosActivity.this,
					"DATA = " + data, Toast.LENGTH_SHORT)
					.show();
			
		}
	};



	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paciente_dados, menu);
		return true;
	}

}
