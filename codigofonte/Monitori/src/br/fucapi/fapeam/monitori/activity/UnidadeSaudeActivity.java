package br.fucapi.fapeam.monitori.activity;

import java.util.List;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.dao.UnidadeSaudeDAO;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UnidadeSaudeActivity extends Activity {

	//Definicao das constantes
	private final String TAG = "CADASTRO_UBS";
	
	//Atributos de Tela
	private ListView lvListagem;
	
	//Colecao de pacientes a serem exibidos na tela
	private List<UnidadeSaude> listaUbs;
	
	//ArrayAdapter para adaptar lista em View
	private ArrayAdapter<UnidadeSaude> adapter;
	
	//Definicao do Layout de exibicao da lista
	private int adapterLayout = android.R.layout.simple_list_item_1;
	
	//UnidadeSaude selecionando com o click longo
	private UnidadeSaude ubsSelecionado = null;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unidadesaude);
		
		//Ligacao dos componentes de TELA aos atributos da Activity
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		//Informa que a ListView tem um menu de contexto
		registerForContextMenu(lvListagem);
		
		//Metodo do click longo
		lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				//Marca o usuario selecionado
				ubsSelecionado = (UnidadeSaude) adapter.getItemAtPosition(posicao);
				Log.i(TAG, "UBS Selecionado ListView.LongClick()"
						+ ubsSelecionado.getNome());
				return false;
			}
		});

		// Metodo que "escuta" o evento de Click SIMPLES
				lvListagem.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapter, View view,
							int posicao, long id) {

						Intent form = new Intent(UnidadeSaudeActivity.this,
								UnidadeSaudeDadosActivity.class);

						ubsSelecionado = (UnidadeSaude) lvListagem
								.getItemAtPosition(posicao);

						form.putExtra("UBS_SELECIONADO", ubsSelecionado);

						startActivity(form);
					}
				});	
	}

	protected void onSaveInstanceState(Bundle outState){
		//Inclusao da lista paciente no objeto Bundle
	//	outState.putStringArrayList(PACIENTES_KEY, (ArrayList<Usuario>) listaPaciente);
		//Persistencia do objeto bundle
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onsaveRestoreState(): "	+ listaUbs);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		//Verifica o item do menu selecionado
		switch(item.getItemId()){
			//Verifica se foi selecionado um item novo
			case R.id.menu_novo:
				//Especialista em mudanca de tela
				Intent intent = new Intent(UnidadeSaudeActivity.this,
						UnidadeSaudeDadosActivity.class);
				//Carrega a nova tela
				startActivity(intent);
				
				return false;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Definicao do menu inflater
		MenuInflater inflater = this.getMenuInflater();
		
		//Inflar um XML
		inflater.inflate(R.menu.unidadesaude, menu);
		
		return true;
	}
	
	public void carregarLista(){
		//Criacao do objeto DAO
		UnidadeSaudeDAO dao = new UnidadeSaudeDAO(this);
		
		Log.i(TAG, "chamou listar "	);
		//Chamado do metodo listar
		this.listaUbs = dao.listar();
		
		//fim da conexao do DB
		dao.close();
		
		//O objeto arrayadapter converte lista em view
		this.adapter = new ArrayAdapter<UnidadeSaude>(this, adapterLayout, listaUbs);
		//associacao do adapter ao listView
		this.lvListagem.setAdapter(adapter);
	}
	
	private void excluirUsuario() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Confirma a exclusao de: "
				+ ubsSelecionado.getNome());

		builder.setPositiveButton("Sim", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int witch) {
				UnidadeSaudeDAO dao = new UnidadeSaudeDAO(UnidadeSaudeActivity.this);
				dao.deletar(ubsSelecionado);
				dao.close();
				carregarLista();
				ubsSelecionado = null;
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
				
				Intent form = new Intent(UnidadeSaudeActivity.this,
						UnidadeSaudeDadosActivity.class);				
				form.putExtra("UBS_SELECIONADO", ubsSelecionado);

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
