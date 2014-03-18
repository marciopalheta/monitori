package br.fucapi.fapeam.monitori.activity.unidadeSaude;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.dao.UnidadeSaudeDAO;
import br.fucapi.fapeam.monitori.model.helper.UnidadeSaudeHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class UnidadeSaudeDadosActivity extends FragmentActivity {

	//Atributos para manipulacao de tela
	
	private UnidadeSaudeHelper helper;
	private UnidadeSaude ubsParaSerAlterado = null;			
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unidadesaudedados);
				
		//Criacao do objeto helper
		helper = new UnidadeSaudeHelper(this);
						
		// Busca o paciente a ser alterado
		ubsParaSerAlterado = (UnidadeSaude) getIntent().getSerializableExtra(
						"UBS_SELECIONADO");

				if (ubsParaSerAlterado != null) {
					// Atualiza a tela com dados do Aluno
					helper.setUnidadeSaude(ubsParaSerAlterado);
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
				UnidadeSaude ubs = helper.getUnidadeSaude();
				//Criacao do objeto DAO
				UnidadeSaudeDAO dao = new UnidadeSaudeDAO(UnidadeSaudeDadosActivity.this);
				long id;
				//Validando os campos
				if(helper.validar()==true){		
					// Verificacao para salvar ou cadastrar o aluno
					if (ubs.getId() == null) {
						dao.cadastrar(ubs);
						id = dao.getLastInsertId();
					} else {
						dao.alterar(ubs);
						id = ubs.getId();
					}//Fechando a conexao com o BD
					dao.close();
					
					Intent result = new Intent();
	                //System.out.println("from second activity: \"" + txtStr + "\"");
	                result.putExtra("ID_UBS", id);
	                setResult(Activity.RESULT_OK, result);
					
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