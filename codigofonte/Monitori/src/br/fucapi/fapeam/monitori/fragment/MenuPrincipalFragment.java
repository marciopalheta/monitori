package br.fucapi.fapeam.monitori.fragment;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.ColetarActivity;
import br.fucapi.fapeam.monitori.activity.agente.AgenteActivity;
import br.fucapi.fapeam.monitori.activity.medico.MedicoActivity;
import br.fucapi.fapeam.monitori.activity.paciente.PacienteActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalFragment extends Fragment implements OnClickListener {

	private Button btMedico;
	private Button btAgente;
	private Button btPaciente;
	private Button btcoletadados;
	
	
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
        	Intent intent = new Intent(getActivity(), AgenteActivity.class);
			//Intent intent = new Intent(getActivity(), UnidadeSaudeActivity.class);
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
    		
    		Intent intent4 = new Intent(getActivity(), ColetarActivity.class);
    		if(v == btcoletadados){
    			startActivity(intent4);
    		}
	}
}