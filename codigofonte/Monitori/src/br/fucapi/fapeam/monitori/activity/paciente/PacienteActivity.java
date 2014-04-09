package br.fucapi.fapeam.monitori.activity.paciente;

import java.util.List;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.adapter.ListaAdapter;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
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
import android.widget.ListView;

public class PacienteActivity extends Activity {

	//Definicao das constantes
	private final String TAG = "CADASTRO_PACIENTE";
	
	//Atributos de Tela
	private ListView lvListagem;
	
	//Colecao de pacientes a serem exibidos na tela
	private List<Usuario> listaPaciente;
	
	//ArrayAdapter para adaptar lista em View
	private ListaAdapter adapter;
	
	//Definicao do Layout de exibicao da lista
	//private int adapterLayout = android.R.layout.simple_list_item_1;
	
	//Usuario selecionando com o click longo
	private Paciente pacienteSelecionado = null;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paciente);
		
		//getActionBar().setHomeButtonEnabled(true);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
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
				pacienteSelecionado = (Paciente) adapter.getItemAtPosition(posicao);
				Log.i(TAG, "Usuario Selecionado ListView.LongClick()"
						+ pacienteSelecionado.getNome());
				return false;
			}
		});

		// Metodo que "escuta" o evento de Click SIMPLES
		lvListagem.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				Intent form = new Intent(PacienteActivity.this,
						PacienteDadosActivity.class);

				pacienteSelecionado = (Paciente) lvListagem
						.getItemAtPosition(posicao);

				form.putExtra("PACIENTE_SELECIONADO", pacienteSelecionado);

				startActivity(form);
			}
		});	
	}

	protected void onSaveInstanceState(Bundle outState){
		//Inclusao da lista paciente no objeto Bundle
	//	outState.putStringArrayList(PACIENTES_KEY, (ArrayList<Usuario>) listaPaciente);
		//Persistencia do objeto bundle
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onsaveRestoreState(): "	+ listaPaciente);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		//Verifica o item do menu selecionado
		switch(item.getItemId()){
			//Verifica se foi selecionado um item novo
			case R.id.menu_novo:
				//Especialista em mudanca de tela
				Intent intent = new Intent(PacienteActivity.this,
						PacienteDadosActivity.class);
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
		inflater.inflate(R.menu.paciente, menu);
		
		return true;
	}
	
	public void carregarLista(){
		//Criacao do objeto DAO
		UsuarioDAO dao = new UsuarioDAO(this);
		//Chamado do metodo listar
		this.listaPaciente = dao.listar(TipoUsuario.PACIENTE);
		//fim da conexao do DB
		dao.close();
		
		//O objeto arrayadapter converte lista em view
		this.adapter = new ListaAdapter(this, listaPaciente);
		//associacao do adapter ao listView
		this.lvListagem.setAdapter(adapter);
	}
	
	private void excluirUsuario() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Confirma a exclusao de: "
				+ pacienteSelecionado.getNome());

		builder.setPositiveButton("Sim", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int witch) {
				UsuarioDAO dao = new UsuarioDAO(PacienteActivity.this);
				dao.deletar(pacienteSelecionado);
				dao.close();
				carregarLista();
				pacienteSelecionado = null;
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
				
				Intent form = new Intent(PacienteActivity.this,
						PacienteDadosActivity.class);				
				form.putExtra("PACIENTE_SELECIONADO", pacienteSelecionado);

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
