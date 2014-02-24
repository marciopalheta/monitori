package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.model.helper.MedicoHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MedicoDadosActivity extends UsuarioDadosActivity {

	//Atributos para manipulacao de tela	
		private MedicoHelper helper;
		private Medico medicoParaSerAlterado = null;	

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.setContentView(R.layout.medicodados);
			super.onCreate(savedInstanceState);			
					
			//Criacao do objeto helper
			helper = new MedicoHelper(this);		
							
			// Busca o paciente a ser alterado
			medicoParaSerAlterado = (Medico) getIntent().getSerializableExtra(
							"MEDICO_SELECIONADO");

					if (medicoParaSerAlterado != null) {
						// Atualiza a tela com dados do Aluno
						helper.setMedico(medicoParaSerAlterado);
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
					Medico medico = (Medico) helper.getMedico();
					//Criacao do objeto DAO
					UsuarioDAO dao = new UsuarioDAO(MedicoDadosActivity.this);
					
					//Validando os campos
					if(helper.validar()==true){		
						// Verificacao para salvar ou cadastrar o aluno
						if (medico.getId() == null) {
							dao.cadastrar(medico);
						} else {
							dao.alterar(medico);
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
