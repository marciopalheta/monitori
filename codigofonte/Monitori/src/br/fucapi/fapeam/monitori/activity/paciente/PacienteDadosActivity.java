package br.fucapi.fapeam.monitori.activity.paciente;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.UsuarioDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.model.helper.PacienteHelper;
import br.fucapi.fapeam.monitori.model.helper.UsuarioHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PacienteDadosActivity extends UsuarioDadosActivity {

	//Atributos para manipulacao de tela
	private PacienteHelper helper;
	private Paciente pacienteParaSerAlterado = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setContentView(R.layout.pacientedados);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.pacientedados);
				
		//Criacao do objeto helper
		 helper = (PacienteHelper) getHelper();				
		// Busca o paciente a ser alterado
		pacienteParaSerAlterado = (Paciente) getIntent().getSerializableExtra(
						"PACIENTE_SELECIONADO");

				if (pacienteParaSerAlterado != null) {
					// Atualiza a tela com dados do Aluno
					helper.setPaciente(pacienteParaSerAlterado);
				}
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
				long id;
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

	@Override
	public UsuarioHelper abstractHelper() { 
		return new PacienteHelper(this);
	}
	
}
