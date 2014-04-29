package br.fucapi.fapeam.monitori.fragment;

import java.util.List;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.dao.BairroDAO;
import br.fucapi.fapeam.monitori.utils.BairroDialog;
import br.fucapi.fapeam.monitori.utils.PutExtras;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BairroFragment extends Fragment {

	//Definicao das constantes
	private final String TAG = "CADASTRO_BAIRRO";
	
	//Atributos de Tela
	private ListView lvListagem;
	
	//Colecao de pacientes a serem exibidos na tela
	private List<Bairro> listaBairro;
	
	//ArrayAdapter para adaptar lista em View
	private ArrayAdapter<Bairro> adapter;
	
	//Definicao do Layout de exibicao da lista
	private int adapterLayout = android.R.layout.simple_list_item_1;
	
	//UnidadeSaude selecionando com o click longo
	private Bairro bairroSelecionado = null;	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); //adicionar itens ao OptionsMenu 								
	}
	    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		//Log.d("FragmentLifecycle", "onCreateView savedInstanceState is " + (savedInstanceState == null?"":"not ") + "null");

		View layout = inflater.inflate(R.layout.bairro, container, false);
						
		//Ligacao dos componentes de TELA aos atributos da Activity
		lvListagem = (ListView) layout.findViewById(R.id.lvListagem);
		//Informa que a ListView tem um menu de contexto
		registerForContextMenu(lvListagem);
		
		//Metodo do click longo
		lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				//Marca o usuario selecionado
				bairroSelecionado = (Bairro) adapter.getItemAtPosition(posicao);
				Log.i(TAG, "UBS Selecionado ListView.LongClick()"
						+ bairroSelecionado.getNome());
				return false;
			}
		});

		// Metodo que "escuta" o evento de Click SIMPLES
				lvListagem.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapter, View view,
							int posicao, long id) {

						bairroSelecionado = (Bairro) lvListagem
								.getItemAtPosition(posicao);
						
						editBairro(bairroSelecionado);
												
					}
				});
		
		return layout;		
	}
		
    @Override
	public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
		super.onInflate(activity, attrs, savedInstanceState);
		Log.d("FragmentLifecycle", "onInflate");
	}
			

	public void onSaveInstanceState(Bundle outState){
		//Persistencia do objeto bundle
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onsaveRestoreState(): "	+ listaBairro);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		//Verifica o item do menu selecionado
		switch(item.getItemId()){
			//Verifica se foi selecionado um item novo
			case R.id.menu_novo:
				getNovoBairro();
				//chamar metodo para criar novoBairro
				return false;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		  inflater.inflate(R.menu.bairro, menu);
	}
		
				
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);
		
		getActivity().getMenuInflater().inflate(R.menu.menu_contexto, menu);
		//getMenuInflater().inflate(R.menu.menu_contexto, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_editar:
				
				editBairro(bairroSelecionado);
				
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

		
	public void carregarLista(){
		//Criacao do objeto DAO
		BairroDAO dao = new BairroDAO(getActivity());
				
		//Chamado do metodo listar
		this.listaBairro = dao.listar();
		
		//fim da conexao do DB
		dao.close();
		
		//O objeto arrayadapter converte lista em view
		this.adapter = new ArrayAdapter<Bairro>(getActivity(), adapterLayout, listaBairro);
		//associacao do adapter ao listView
		this.lvListagem.setAdapter(adapter);
	}
	
	private void excluirUsuario() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Confirma a exclusao de: "
				+ bairroSelecionado.getNome());

		builder.setPositiveButton("Sim", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int witch) {
				BairroDAO dao = new BairroDAO(getActivity());
				dao.deletar(bairroSelecionado);
				dao.close();
				carregarLista();
				bairroSelecionado = null;
			}
		});
		builder.setNegativeButton("Nao", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Confirmacao de exclusao");
		dialog.show();
	}
	

	private void getNovoBairro() {
		
		final BairroDialog diag = BairroDialog.newInstance();		
		diag.show( this.getFragmentManager() , "dialg");		
				
	}
	
	private void editBairro(Bairro bairroSelecionado) {
		
		Bundle args = new Bundle();			
		args.putSerializable(PutExtras.BAIRRO_SELECIONADO, bairroSelecionado);				
		
		final BairroDialog diag = BairroDialog.newInstance();
		diag.setArguments(args);
		diag.show( this.getFragmentManager() , "dialg");		
				
	}
	
	
}
