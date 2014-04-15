package br.fucapi.fapeam.monitori.activity;

import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.adapter.ColetaAdapter;
import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.dao.ColetarDadosDAO;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ColetarActivity extends Activity {

	//definicao das constantes
	private final String TAG = "CADASTRO_COLETARDADOS";
	
	//atributos de tela
	private ListView lvListagem;
	
	//colecao de coletas a serem exibidas na tela
	private List<ColetarDados> listaColetar;
	
	//ArrayAdapter para adaptar lista em view
	private ColetaAdapter adapter;
	
	//definicao do layout de exibicao da lista
	
	//selecao com o click longo
	private ColetarDados coletaSelecionada = null;
	
	private Paciente pacienteSelecionado = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coletar);
		
		pacienteSelecionado = (Paciente) getIntent().getSerializableExtra(
				"PACIENTE_SELECIONADO");
		
		
		if (pacienteSelecionado == null) {
			// Atualiza a tela com dados do Aluno			
			Toast.makeText(this, "PACIENTE NAO INFORMADO", Toast.LENGTH_LONG).show();
			//finish();
		}
		
		//ligacao dos componentes de TELA aos atributos da activity
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		
		//informa que a listView tem um menu de contexto
		registerForContextMenu(lvListagem);
		
		//metodo do click longo
		lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				// marca o usuario selecionado
				coletaSelecionada = (ColetarDados) adapter.getItemAtPosition(posicao);
				Log.i(TAG, "Coleta de dados selecionado ListView.LongClick()"
						+ coletaSelecionada.getSis());

				return false;
			}		
		});
		
		//metodo do click simples
		lvListagem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				
				Intent form = new Intent(ColetarActivity.this, ColetarDadosActivity.class);
				
				coletaSelecionada = (ColetarDados) lvListagem.getItemAtPosition(posicao);
				form.putExtra("COLETA_SELECIONADA", coletaSelecionada);
				startActivity(form);
			}			
		});
	}

	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onSaveRestoreState(): "+listaColetar);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		//verifica o item do menu selecionado
		switch(item.getItemId()){
		//verifica se foi selecionado um item novo
		case R.id.menu_novo:
			//especialista em mudan�a de tela
			Intent intent = new Intent(ColetarActivity.this, ColetarDadosActivity.class);
			//carrega a nova tela
			startActivity(intent);
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//definicao do menu inflater
		MenuInflater inflater = this.getMenuInflater();
		
		// inflar um XML
		inflater.inflate(R.menu.coletar, menu);
		return true;
	}
	
	public void carregarLista() {
		//criacao do objeto DAO
		ColetarDadosDAO dao = new ColetarDadosDAO(this);
		Log.i(TAG, "chamou o listar: ");
		//chamado do metodo listar
		if(pacienteSelecionado!=null){
			this.listaColetar = dao.listar(pacienteSelecionado);
		}else{
			this.listaColetar = dao.listar();	
		}
		//fim de conexao com o DB
		dao.close();
		
		//objeto arrayAdapter converte array em view
		this.adapter = new ColetaAdapter(this,
				listaColetar);
		//associacao do adapter ao listView
		this.lvListagem.setAdapter(adapter);
	}
	
	private void excluirUsuario() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Confirma a exclusao de: "
				+ coletaSelecionada.getSis());

		builder.setPositiveButton("Sim", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int witch) {
				ColetarDadosDAO dao = new ColetarDadosDAO(ColetarActivity.this);
				dao.deletar(coletaSelecionada);
				dao.close();
				carregarLista();
				coletaSelecionada = null;
			}
		});
		builder.setNegativeButton("Nao", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Confirmacao de exclusao");
		dialog.show();
	}
	
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);

		getMenuInflater().inflate(R.menu.menu_contexto, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_editar:
				
				Intent form = new Intent(ColetarActivity.this,
						ColetarDadosActivity.class);				
				form.putExtra("COLETAR_SELECIONADO", coletaSelecionada);

				startActivity(form);
				break;
			case R.id.menu_deletar:
				excluirUsuario();
				break;
			default:
				break;
		}
		return super.onContextItemSelected(item);
	}
	protected void onResume(){
		super.onResume();		
		this.carregarLista();
	}
}
