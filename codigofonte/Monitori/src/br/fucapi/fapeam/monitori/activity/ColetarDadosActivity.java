package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.dao.ColetarDadosDAO;
import br.fucapi.fapeam.monitori.model.helper.ColetarDadosHelper;
import br.fucapi.fapeam.monitori.utils.PutExtras;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ColetarDadosActivity extends FragmentActivity {

	//atributos para manipulacao de telas
	private ColetarDadosHelper helper;
	private ColetarDados coletaDadosParaSerAlterada=null;
	private Paciente pacienteSelecionado = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coletardados);
		
		pacienteSelecionado = (Paciente) getIntent().getSerializableExtra(
				PutExtras.PACIENTE_SELECIONADO);
		coletaDadosParaSerAlterada = (ColetarDados)getIntent().getSerializableExtra
				(PutExtras.COLETA_SELECIONADA);
		
		if(coletaDadosParaSerAlterada != null){
			pacienteSelecionado = (Paciente) coletaDadosParaSerAlterada.getUsuario(); 		
		}
		
		if (pacienteSelecionado == null) {
			// Atualiza a tela com dados do Aluno			
			//Toast.makeText(this, "PACIENTE NAO INFORMADO", Toast.LENGTH_LONG).show();
			finish();
		}else{
			//criacao do objeto helper
			helper = new ColetarDadosHelper(this,pacienteSelecionado);
			//busca para ser alterado					
						
			if(coletaDadosParaSerAlterada != null){
				//atualiza a tela				
				helper.setColetarDados(coletaDadosParaSerAlterada);
			}
			
		}
		
		
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//definicao do menu inflater
		MenuInflater inflater = this.getMenuInflater();
		
		// Inflata um XML 
		inflater.inflate(R.menu.menu_formularios, menu);
		
		//getMenuInflater().inflate(R.menu.coletar_dados, menu);
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
				ColetarDados coletaDados = helper.getColetarDados();
				//Criacao do objeto DAO
				ColetarDadosDAO dao = new ColetarDadosDAO(ColetarDadosActivity.this);
				
				//Validando os campos
				if(helper.validar()==true){		
					// Verificacao para salvar ou cadastrar o aluno
					if (coletaDados.getId() == null) {
						dao.cadastrar(coletaDados);						
					} else {
						dao.alterar(coletaDados);
						
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

