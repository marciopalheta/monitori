package br.fucapi.fapeam.monitori.activity.agente;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.UsuarioDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.model.helper.AgenteHelper;
import br.fucapi.fapeam.monitori.model.helper.UsuarioHelper;
import br.fucapi.fapeam.monitori.utils.PutExtras;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AgenteDadosActivity extends UsuarioDadosActivity {

	//Atributos para manipulacao de tela	
	private AgenteHelper helper;
	private Agente agenteParaSerAlterado = null;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setContentView(R.layout.agentesdados);
		super.onCreate(savedInstanceState);									
		helper = (AgenteHelper) getHelper();				
		// Busca o paciente a ser alterado
		agenteParaSerAlterado = (Agente) getIntent().getSerializableExtra(
						PutExtras.AGENTE_SELECIONADO);

				if (agenteParaSerAlterado != null) {
					// Atualiza a tela com dados do Aluno
					helper.setAgente(agenteParaSerAlterado);
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
				Agente agente = (Agente) helper.getAgente();
				//Criacao do objeto DAO
				UsuarioDAO dao = new UsuarioDAO(AgenteDadosActivity.this);
				
				//Validando os campos
				if(helper.validar()==true){		
					// Verificacao para salvar ou cadastrar o aluno
					if (agente.getId() == null) {
						dao.cadastrar(agente);
					} else {
						dao.alterar(agente);
						Intent result = new Intent();
		                result.putExtra( PutExtras.ALTERAR_USUARIO ,agente);
		                setResult(Activity.RESULT_OK, result);
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
		return new AgenteHelper(this);
	}	
}
