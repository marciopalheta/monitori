package br.fucapi.fapeam.monitori.fragment;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.AppMainActivity;
import br.fucapi.fapeam.monitori.activity.Dashboard;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.DashboardLayout;
import br.fucapi.fapeam.monitori.utils.RequestCodes;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.AndroidCharacter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuPrincipalFragment extends Fragment implements OnClickListener {

	private Button btPaciente;
	private Button btAgente;
	private Button btMedico;
			
	private Button btBairro;
	private Button btUbs;
	
	private Button btColetaDados;
	private Button btHistoricoColeta;
	
	private Button btDiagnostico;
	private Button btHistoricoDiagnostico;
		
	
	private Usuario usuarioLogado = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(this.getArguments() !=null){
			usuarioLogado = (Usuario) this.getArguments().getSerializable("USUARIO_LOGADO");
		}
		
		setHasOptionsMenu(true); //adicionar itens ao OptionsMenu
				
		
	}
	    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		//Log.d("FragmentLifecycle", "onCreateView savedInstanceState is " + (savedInstanceState == null?"":"not ") + "null");
		View layout = inflater.inflate(R.layout.dinamic_menuprincipal, container, false);								
		
		/*
		btMedico = (Button) layout.findViewById(R.id.btMedico);
		btMedico.setOnClickListener(this);
		btAgente = (Button) layout.findViewById(R.id.btAgente);
		btAgente.setOnClickListener(this);
		btPaciente = (Button) layout.findViewById(R.id.btPaciente);
		btPaciente.setOnClickListener(this);
		*/
		
		DashboardLayout dash = (DashboardLayout) layout.findViewById(R.id.dashboard);
		
		if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
			
			/*Botao Paciente*/
			btPaciente = new Button(this.getActivity());
			
			btPaciente.setText( getResources().getString(R.string.menu_paciente) );
			btPaciente.setOnClickListener(this);		
			btPaciente.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
					
			Drawable drawableTopPaciente = getResources().getDrawable(R.drawable.ic_paciente);
			btPaciente.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopPaciente , null, null);
			btPaciente.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			//btPaciente.setWidth(50);
			
			
			/*Botao Agente Saude*/
			btAgente = new Button(this.getActivity());
			
			btAgente.setText( getResources().getString(R.string.menu_agente) );
			btAgente.setOnClickListener(this);
						
			btAgente.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)
					);
					
			Drawable drawableTopAgente = getResources().getDrawable(R.drawable.ic_agente);
			btAgente.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopAgente , null, null);
			btAgente.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			btAgente.setWidth(30);
			
			
			/*Botao Medico*/
			btMedico = new Button(this.getActivity());
			
			btMedico.setText( getResources().getString(R.string.menu_medico) );
			Drawable drawableTopMedico = getResources().getDrawable(R.drawable.ic_medico);
			
			btMedico.setOnClickListener(this);		
			btMedico.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btMedico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopMedico , null, null);
			btMedico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			//btMedico.setWidth(50);
			
			/*Botao Ubs*/
			btUbs = new Button(this.getActivity());
			
			btUbs.setText( getResources().getString(R.string.menu_ubs_long) );
			Drawable drawableTopUbs = getResources().getDrawable(R.drawable.ic_ubs);
			
			btUbs.setOnClickListener(this);		
			btUbs.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btUbs.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopUbs , null, null);
			btUbs.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			btUbs.setWidth(30);
			
			/*Botao Bairro*/
			btBairro = new Button(this.getActivity());
			
			btBairro.setText( getResources().getString(R.string.menu_bairro) );
			Drawable drawableTopBairro = getResources().getDrawable(R.drawable.ic_bairro);
			
			btBairro.setOnClickListener(this);		
			btBairro.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btBairro.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopBairro, null, null);
			btBairro.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			//btBairro.setWidth(50);			
									
			dash.addView(btPaciente);
			dash.addView(btAgente);		
			dash.addView(btMedico);	
			dash.addView(btUbs);
			dash.addView(btBairro);
			
			
		}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.PACIENTE)){
			
			/*Botao ColetarDados*/
			btColetaDados = new Button(this.getActivity());
			
			btColetaDados.setText( getResources().getString(R.string.menu_coletadados) );
			Drawable drawableTopColetaDados = getResources().getDrawable(R.drawable.ic_coleta);
			
			btColetaDados.setOnClickListener(this);		
			btColetaDados.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btColetaDados.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopColetaDados , null, null);
			btColetaDados.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );			
									
			/*Botao HistoricoColeta*/
			btHistoricoColeta = new Button(this.getActivity());
			
			btHistoricoColeta.setText( getResources().getString(R.string.menu_historico_coleta) );
			Drawable drawableTopHistoricoColeta = getResources().getDrawable(R.drawable.historico);
			
			btHistoricoColeta.setOnClickListener(this);		
			btHistoricoColeta.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btHistoricoColeta.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopHistoricoColeta , null, null);
			btHistoricoColeta.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			btHistoricoColeta.setWidth(30);
			
			/*Botao HistoricoDiagnostico
			btHistoricoDiagnostico = new Button(this.getActivity());
			
			btHistoricoDiagnostico.setText( getResources().getString(R.string.menu_historico_diagnostico) );
			Drawable drawableTopHistoricoDiagnostico = getResources().getDrawable(R.drawable.historico);
			
			btHistoricoDiagnostico.setOnClickListener(this);
			
			btHistoricoDiagnostico.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btHistoricoDiagnostico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopHistoricoDiagnostico , null, null);
			btHistoricoDiagnostico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			btHistoricoDiagnostico.setWidth(30);*/
												
			
			
			dash.addView(btColetaDados);
			dash.addView(btHistoricoColeta);		
			//dash.addView(btHistoricoDiagnostico);							
			
		}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.AGENTE)){
			
			
			/*Botao Paciente*/
			btPaciente = new Button(this.getActivity());
			
			btPaciente.setText( getResources().getString(R.string.menu_paciente) );
			btPaciente.setOnClickListener(this);		
			btPaciente.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
					
			Drawable drawableTopPaciente = getResources().getDrawable(R.drawable.ic_paciente);
			btPaciente.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopPaciente , null, null);
			btPaciente.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			//btPaciente.setWidth(50);
									
			
			/*Botao Medico*/
			btMedico = new Button(this.getActivity());
			
			btMedico.setText( getResources().getString(R.string.menu_medico) );
			Drawable drawableTopMedico = getResources().getDrawable(R.drawable.ic_medico);
			
			btMedico.setOnClickListener(this);		
			btMedico.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btMedico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopMedico , null, null);
			btMedico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			//btMedico.setWidth(50);
			
			/*Botao Bairro*/
			btBairro = new Button(this.getActivity());
			
			btBairro.setText( getResources().getString(R.string.menu_bairro) );
			Drawable drawableTopBairro = getResources().getDrawable(R.drawable.ic_bairro);
			
			btBairro.setOnClickListener(this);		
			btBairro.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btBairro.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopBairro, null, null);
			btBairro.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			//btBairro.setWidth(50);			
			/*Botao Ubs*/
			btUbs = new Button(this.getActivity());
			
			btUbs.setText( getResources().getString(R.string.menu_ubs) );
			Drawable drawableTopUbs = getResources().getDrawable(R.drawable.ic_ubs);
			
			btUbs.setOnClickListener(this);		
			btUbs.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btUbs.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopUbs , null, null);
			btUbs.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			//btUbs.setWidth(30);
						
			
			/*Botao ColetarDados*/
			btColetaDados = new Button(this.getActivity());
			
			btColetaDados.setText( getResources().getString(R.string.menu_coletadados) );
			Drawable drawableTopColetaDados = getResources().getDrawable(R.drawable.ic_coleta);
			
			btColetaDados.setOnClickListener(this);		
			btColetaDados.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btColetaDados.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopColetaDados , null, null);
			btColetaDados.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			
			
			dash.addView(btPaciente);					
			dash.addView(btMedico);
			dash.addView(btBairro);
			dash.addView(btUbs);
			dash.addView(btColetaDados);
			
		}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.MEDICO)){
			
			/*Botao ColetarDados*/
			btDiagnostico = new Button(this.getActivity());
			
			btDiagnostico.setText( getResources().getString(R.string.menu_diagnosticar) );
			Drawable drawableTopColetaDados = getResources().getDrawable(R.drawable.ic_drawer);
			
			btDiagnostico.setOnClickListener(this);		
			btDiagnostico.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btDiagnostico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopColetaDados , null, null);
			btDiagnostico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );			
									
			/*Botao HistoricoColeta*/
			btHistoricoColeta = new Button(this.getActivity());
			
			btHistoricoColeta.setText( getResources().getString(R.string.menu_historico_coleta) );
			Drawable drawableTopHistoricoColeta = getResources().getDrawable(R.drawable.ic_coleta);
			
			btHistoricoColeta.setOnClickListener(this);		
			btHistoricoColeta.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btHistoricoColeta.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopHistoricoColeta , null, null);
			btHistoricoColeta.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			btHistoricoColeta.setWidth(30);
			
			/*Botao HistoricoDiagnostico*/
			btHistoricoDiagnostico = new Button(this.getActivity());
			
			btHistoricoDiagnostico.setText( getResources().getString(R.string.menu_historico_diagnostico) );
			Drawable drawableTopHistoricoDiagnostico = getResources().getDrawable(R.drawable.ic_coleta);
			
			btHistoricoDiagnostico.setOnClickListener(this);
			
			btHistoricoDiagnostico.setLayoutParams(
					new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)		
					);
								
			btHistoricoDiagnostico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopHistoricoDiagnostico , null, null);
			btHistoricoDiagnostico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
			btHistoricoDiagnostico.setWidth(30);
												
			
			
			dash.addView(btDiagnostico);					
			dash.addView(btHistoricoDiagnostico);							
			dash.addView(btHistoricoColeta);
			
			
		}
			
				
		//((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_AGENTE );
		
		/*
		btcoletadados = (Button) layout.findViewById(R.id.btcoletadados);
		btcoletadados.setOnClickListener(this);
		*/
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
        	
			
    		if(v == btAgente) {    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_AGENTE );    			
    			
    		}
    		
    		if(v == btPaciente) {
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_PACIENTE );    			
    		}
    		    		
    		if(v == btMedico) {    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_MEDICO );
    		} 
    		
    		if(v == btBairro) {    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_UBS );
    		}
    		
    		if(v == btUbs) {    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_BAIRRO );
    		}
    		
    		
    		if(v == btColetaDados){    	
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem(RequestCodes.MENU_PACIENTE);
    			}
 
    		if(v == btHistoricoColeta){    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_AGENTE );
    		}
    		
    		if(v == btDiagnostico){    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_DIAGNOSTICAR );
    		}
    		
    		if(v == btHistoricoDiagnostico){    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_HISTORICO_DIAGNOSTICO );
    		}    				
    		
	}
}