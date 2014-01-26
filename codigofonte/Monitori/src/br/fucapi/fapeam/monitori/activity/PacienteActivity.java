package br.fucapi.fapeam.monitori.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.R.layout;
import br.fucapi.fapeam.monitori.R.menu;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PacienteActivity extends Activity {

	//Definicao das constantes
	private final String TAG = "CADASTRO_PACIENTE";
	
	//Atributos de Tela
	private ListView lvListagem;
	
	//Colecao de pacientes a serem exibidos na tela
	private List<Paciente> listaPaciente;
	
	//ArrayAdapter para adaptar lista em View
	private ArrayAdapter<Paciente> adapter;
	
	//Definicao do Layout de exibicao da lista
	private int adapterLayout = android.R.layout.simple_list_item_1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paciente);
		
		//Ligacao dos componentes de TELA aos atributos da Activity
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		
		//Metodo do click simples
		lvListagem.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
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
		this.listaPaciente = dao.listar();
		//fim da conexao do DB
		dao.close();
		
		//O objeto arrayadapter converte lista em view
		this.adapter = new ArrayAdapter<Paciente>(this, adapterLayout, listaPaciente);
		//associacao do adapter ao listView
		this.lvListagem.setAdapter(adapter);
	}
	
	protected void onResume(){
		super.onResume();
		this.carregarLista();
	}

}
