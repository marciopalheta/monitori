package br.fucapi.fapeam.monitori.fragment;

import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.DiagnosticarDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Diagnosticar;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.dao.DiagnosticarDAO;
import br.fucapi.fapeam.monitori.utils.PutExtras;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class DiagnosticarFragment extends Fragment{
	//definicao das constantes
			private final String TAG = "CADASTRO_DIAGNOSTICO";
			
			//atributos de tela
			private ListView lvListagem;
			
			//colecao de coletas a serem exibidas na tela
			private List<Diagnosticar> listaDiagnosticar;
			
			//ArrayAdapter para adaptar lista em view
			private ArrayAdapter<Diagnosticar> adapter;
			
			//definicao do layout de exibicao da lista
			//private int adapterLayout = android.R.layout.simple_list_item_1;
			
			//selecao com o click longo
			private Diagnosticar diagnosticoSelecionado = null;
			private Paciente pacienteSelecionado = null;
			
			@Override
			public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setHasOptionsMenu(true);//adiciona itens ao OptionsMenu
				
				
				pacienteSelecionado = (Paciente) this.getArguments().getSerializable(PutExtras.PACIENTE_SELECIONADO);									
				
				if (pacienteSelecionado == null) {
					// Atualiza a tela com dados do Aluno			
					Toast.makeText(getActivity(), "PACIENTE NAO INFORMADO", Toast.LENGTH_LONG).show();
									
				}

				
			}
			
			public View onCreateView(LayoutInflater inflater, ViewGroup
					container, Bundle savedInstanceState){
					
				View layout = inflater.inflate(R.layout.diagnosticar, container, false);
				
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
						diagnosticoSelecionado = (Diagnosticar) adapter.getItemAtPosition(posicao);
						Log.i(TAG, "Coleta de dados selecionado ListView.LongClick()"
								+ diagnosticoSelecionado.getDescrever());

						return false;
					}		
				});
				
				//metodo do click simples
				lvListagem.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> adapter, View view, int posicao,
							long id) {
						
						Intent form = new Intent(getActivity(), DiagnosticarDadosActivity.class);
						
						diagnosticoSelecionado = (Diagnosticar) lvListagem.getItemAtPosition(posicao);
						form.putExtra(PutExtras.DIAGNOSTICO_SELECIONADO, diagnosticoSelecionado);
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
				Log.i(TAG, "onSaveRestoreState(): "+listaDiagnosticar);
			}
			
			public boolean onOptionsItemSelected(MenuItem item) {
				//verifica o item  do menu selecionado
				switch(item.getItemId()){
				//verifica se foi selecionado um item novo
				case R.id.menu_novo:
					//especialista em mudan�a de tela
					Intent intent = new Intent(getActivity(), DiagnosticarDadosActivity.class);
					
					intent.putExtra(PutExtras.PACIENTE_SELECIONADO, pacienteSelecionado);
					//intent.putExtra(PutExtras.COLETA_SELECIONADA, coletaSelecionada);								

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
				inflater.inflate(R.menu.diagnosticar, menu);
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
								DiagnosticarDadosActivity.class);				
						form.putExtra(PutExtras.PACIENTE_SELECIONADO, pacienteSelecionado);
						form.putExtra(PutExtras.DIAGNOSTICO_SELECIONADO, diagnosticoSelecionado);

						startActivity(form);
						break;
					case R.id.menu_deletar:
						excluirColeta();
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
				DiagnosticarDAO dao = new DiagnosticarDAO(getActivity());
				Log.i(TAG, "chamou o listar: ");
				//chamado do metodo listar
							
				this.listaDiagnosticar = dao.listar();
				if(pacienteSelecionado!=null){
					//this.listaColetar = dao.listar(pacienteSelecionado);
				}else{
					//this.listaColetar = dao.listar();	
				}
				
				//fim de conexao com o DB
				dao.close();
				
				//objeto arrayAdapter converte array em view
				/*
				this.adapter = new ArrayAdapter<Diagnosticar>(getActivity(),
						adapterLayout, listaDiagnosticar);
				*/
				//associacao do adapter ao listView
				this.lvListagem.setAdapter(adapter);
			}
			
			private void excluirColeta() {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage("Confirma a exclusao de: "
						+ diagnosticoSelecionado.getDescrever());

				builder.setPositiveButton("Sim", new OnClickListener() {
					
					public void onClick(DialogInterface dialog, int witch) {
						DiagnosticarDAO dao = new DiagnosticarDAO(getActivity());
						dao.deletar(diagnosticoSelecionado);
						dao.close();
						carregarLista();
						diagnosticoSelecionado = null;
					}
				});
				builder.setNegativeButton("Nao", null);
				AlertDialog dialog = builder.create();
				dialog.setTitle("Confirmacao de exclusao");
				dialog.show();
			}
			
			

			
		}
