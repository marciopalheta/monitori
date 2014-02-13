package br.fucapi.fapeam.monitori.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.xml.sax.DTDHandler;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.R.layout;
import br.fucapi.fapeam.monitori.R.menu;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UnidadeSaudeDAO;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.model.helper.PacienteHelper;
import br.fucapi.fapeam.monitori.model.helper.UnidadeSaudeHelper;
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

public class UnidadeSaudeDadosActivity extends Activity {

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
				
				//Validando os campos
				if(helper.validar()==true){		
					// Verificacao para salvar ou cadastrar o aluno
					if (ubs.getId() == null) {
						dao.cadastrar(ubs);
					} else {
						dao.alterar(ubs);
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
