package br.fucapi.fapeam.monitori.fragment;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.ColetarDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.model.dao.ColetarDadosDAO;

public class ColetarFragment extends Fragment {
	//definicao das constantes
		private final String TAG = "CADASTRO_COLETARDADOS";
		
		//atributos de tela
		private ListView lvListagem;
		
		//colecao de coletas a serem exibidas na tela
		private List<ColetarDados> listaColetar;
		
		//ArrayAdapter para adaptar lista em view
		private ArrayAdapter<ColetarDados> adapter;
		
		//definicao do layout de exibicao da lista
		private int adapterLayout = android.R.layout.simple_list_item_1;
		
		//selecao com o click longo
		private ColetarDados coletaSelecionada = null;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);//adiciona itens ao OptionsMenu
		}
		
		public View onCreateView(LayoutInflater inflater, ViewGroup
				container, Bundle savedInstanceState){
				
			View layout = inflater.inflate(R.layout.coletar, container, false);
			
			//ligacao dos componentes de TELA aos atributos da activity
			lvListagem = (ListView) layout.findViewById(R.id.lvListagem);
			
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
					
					Intent form = new Intent(getActivity(), ColetarDadosActivity.class);
					
					coletaSelecionada = (ColetarDados) lvListagem.getItemAtPosition(posicao);
					form.putExtra("COLETA_SELECIONADA", coletaSelecionada);
					startActivity(form);
				}			
			});
			return layout;
		}
		
		public void onInflate(Activity activity, AttributeSet attrs, Bundle
				savedInstanceState){
			super.onInflate(activity, attrs, savedInstanceState);
			Log.d("FragmentLifecycle", "onInflate");
		}

		public void onSaveInstanceState(Bundle outState){
			super.onSaveInstanceState(outState);
			Log.i(TAG, "onSaveRestoreState(): "+listaColetar);
		}
		
		public boolean onOptionsItemSelected(MenuItem item) {
			//verifica o item  do menu selecionado
			switch(item.getItemId()){
			//verifica se foi selecionado um item novo
			case R.id.menu_novo:
				//especialista em mudança de tela
				Intent intent = new Intent(getActivity(), ColetarDadosActivity.class);
				//carrega a nova tela
				startActivity(intent);
				return false;
			default:
				return super.onOptionsItemSelected(item);
			}
		}
		
		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {	
			// inflar um XML
			inflater.inflate(R.menu.coletar, menu);
		}
		
		public void onCreateContextMenu(ContextMenu menu, View view,
				ContextMenuInfo menuInfo) {
			super.onCreateContextMenu(menu, view, menuInfo);

			getActivity().getMenuInflater().inflate(R.menu.menu_contexto, menu);
		}
		
		@Override
		public boolean onContextItemSelected(MenuItem item) {
			switch (item.getItemId()) {
				case R.id.menu_editar:
					
					Intent form = new Intent(getActivity(),
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
		
		public void onResume(){
			super.onResume();		
			this.carregarLista();
		}
		
		public void carregarLista() {
			//criacao do objeto DAO
			ColetarDadosDAO dao = new ColetarDadosDAO(getActivity());
			Log.i(TAG, "chamou o listar: ");
			//chamado do metodo listar
			this.listaColetar = dao.listar();
			
			//fim de conexao com o DB
			dao.close();
			
			//objeto arrayAdapter converte array em view
			this.adapter = new ArrayAdapter<ColetarDados>(getActivity(),
					adapterLayout, listaColetar);
			//associacao do adapter ao listView
			this.lvListagem.setAdapter(adapter);
		}
		
		private void excluirUsuario() {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Confirma a exclusao de: "
					+ coletaSelecionada.getSis());

			builder.setPositiveButton("Sim", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int witch) {
					ColetarDadosDAO dao = new ColetarDadosDAO(getActivity());
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
		
		

		
	}
