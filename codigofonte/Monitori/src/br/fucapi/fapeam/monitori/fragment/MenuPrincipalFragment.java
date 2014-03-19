package br.fucapi.fapeam.monitori.fragment;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.ColetarDadosActivity;
import br.fucapi.fapeam.monitori.activity.agente.AgenteActivity;
import br.fucapi.fapeam.monitori.activity.medico.MedicoActivity;
import br.fucapi.fapeam.monitori.activity.paciente.PacienteActivity;
import br.fucapi.fapeam.monitori.activity.paciente.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.activity.unidadeSaude.UnidadeSaudeActivity;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MenuPrincipalFragment extends Fragment implements OnClickListener {

	private Button btMedico;
	private Button btAgente;
	private Button btPaciente;
	private Button btcoletadados;
	private Button btUbs;
	private TipoUsuario usuario;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); //adicionar itens ao OptionsMenu 								
	}
	    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		//Log.d("FragmentLifecycle", "onCreateView savedInstanceState is " + (savedInstanceState == null?"":"not ") + "null");
		View layout = inflater.inflate(R.layout.menuprincipal, container, false);
		
				

		btMedico = (Button) layout.findViewById(R.id.btMedico);
		btMedico.setOnClickListener(this);
		btAgente = (Button) layout.findViewById(R.id.btAgente);
		btAgente.setOnClickListener(this);
		btPaciente = (Button) layout.findViewById(R.id.btPaciente);
		btPaciente.setOnClickListener(this);
		btcoletadados = (Button) layout.findViewById(R.id.btcoletadados);
		btcoletadados.setOnClickListener(this);
		
		return layout;		
	}
		

	public void onSaveInstanceState(Bundle outState){
		//Inclusao da lista paciente no objeto Bundle
	//	outState.putStringArrayList(PACIENTES_KEY, (ArrayList<Usuario>) listaPaciente);
		//Persistencia do objeto bundle
		super.onSaveInstanceState(outState);		
	}
	
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		  inflater.inflate(R.menu.menu_principal, menu);
	}
			

	
	@Override
	public void onClick(View v) {
        	//Intent intent = new Intent(this, AgenteActivity.class);
			Intent intent = new Intent(getActivity(), UnidadeSaudeActivity.class);
    		if(v == btAgente) {
    			startActivity(intent);
    		}
    		
    		Intent intent2 = new Intent(getActivity(), PacienteActivity.class);
    		if(v == btPaciente) {
    			startActivity(intent2);
    		}
    		
    		Intent intent3 = new Intent(getActivity(), MedicoActivity.class);
    		if(v == btMedico) {
    			startActivity(intent3);
    		} 
    		
    		Intent intent4 = new Intent(getActivity(), ColetarDadosActivity.class);
    		if(v == btcoletadados){
    			startActivity(intent4);
    		}
	}
}