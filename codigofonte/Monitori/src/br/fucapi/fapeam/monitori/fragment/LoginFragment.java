package br.fucapi.fapeam.monitori.fragment;

import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.ColetarDadosActivity;
import br.fucapi.fapeam.monitori.activity.LoginActivity;
import br.fucapi.fapeam.monitori.activity.MenuPrincipalActivity;
import br.fucapi.fapeam.monitori.activity.agente.AgenteActivity;
import br.fucapi.fapeam.monitori.activity.agente.AgenteDadosActivity;
import br.fucapi.fapeam.monitori.activity.medico.MedicoActivity;
import br.fucapi.fapeam.monitori.activity.paciente.PacienteActivity;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class LoginFragment extends Fragment {

	//Atributos de Tela
	private EditText login;
	private EditText senha;
	private Button bt_Logar;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true); //adicionar itens ao OptionsMenu 								
		}
		    
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
			//Log.d("FragmentLifecycle", "onCreateView savedInstanceState is " + (savedInstanceState == null?"":"not ") + "null");

			View layout = inflater.inflate(R.layout.login, container, false);
			
			
			
			login = (EditText) layout.findViewById(R.id.edt_login);
			senha = (EditText) layout.findViewById(R.id.edt_senha);
			bt_Logar = (Button) layout.findViewById(R.id.btValidar);
			
			bt_Logar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//mudarTela();
					validarUsuario();
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
		}
		
		public boolean onOptionsItemSelected(MenuItem item){
			Intent intent;
			//Verifica o item do menu selecionado
			switch(item.getItemId()){
				//Verifica se foi selecionado um item novo
									
				case R.id.menu_agente:
					//Especialista em mudanca de tela
					intent = new Intent(getActivity(),
							AgenteActivity.class);
					//Carrega a nova tela
					startActivity(intent);
				
					return false;
					
				case R.id.menu_paciente:
					//Especialista em mudanca de tela
					intent = new Intent(getActivity(),
							PacienteActivity.class);
					//Carrega a nova tela
					startActivity(intent);
					
					return false;
					
				case R.id.menu_medico:
					//Especialista em mudanca de tela
					intent = new Intent(getActivity(),
							MedicoActivity.class);
					//Carrega a nova tela
					startActivity(intent);
					
					return false;
					
				case R.id.menu_coletar_dados:
					//Especialista em mudanca de tela
					intent = new Intent(getActivity(),
							ColetarDadosActivity.class);
					//Carrega a nova tela
					startActivity(intent);
					
					return false;
			
					
				case R.id.menu_principal:
					//Especialista em mudanca de tela
					intent = new Intent(getActivity(),
							MenuPrincipalActivity.class);
					//Carrega a nova tela
					startActivity(intent);
					
					return false;

				default:
					return super.onOptionsItemSelected(item);
			}			
			
		}
		
		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			  inflater.inflate(R.menu.login, menu);		
		
		}
					
							
		
		private void mudarTela(){
			//mudando de activity caso usuario logado
					//Especialista em mudanca de tela
						Intent intent = new Intent(getActivity(),
						MenuPrincipalActivity.class);
						//Carrega a nova tela
						startActivity(intent);
		}
		
		public void validarUsuario(){
			//Criacao do objeto DAO
			UsuarioDAO dao = new UsuarioDAO(getActivity());
			Usuario usuario = new Usuario();
			//Chamado do metodo listar
			usuario = dao.getUsuario(login.getText().toString(), senha.getText().toString());
			//fim da conexao do DB
			dao.close();
					
			if(usuario != null){
				Toast.makeText(getActivity(), "usuario: "+usuario.getNome()+" autenticado", Toast.LENGTH_LONG).show();
				mudarTela();
			}else{
				Toast.makeText(getActivity(), "Login Falhou", Toast.LENGTH_LONG).show();
			}
		}
		
}