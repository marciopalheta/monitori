package br.fucapi.fapeam.monitori.fragment;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.AppMainActivity;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.DashboardLayout;
import br.fucapi.fapeam.monitori.utils.RequestCodes;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
		LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		/*Botao Paciente*/
		btPaciente = new Button(this.getActivity());		
		btPaciente.setText( getResources().getString(R.string.menu_paciente) );
		Drawable drawableTopPaciente = getResources().getDrawable(R.drawable.ic_paciente);
		btPaciente.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopPaciente , null, null);
		btPaciente.setOnClickListener(this);		
		btPaciente.setLayoutParams(layoutParams);								
		btPaciente.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		
		
		/*Botao Agente Saude*/
		btAgente = new Button(this.getActivity());		
		btAgente.setText( getResources().getString(R.string.menu_agente) );
		Drawable drawableTopAgente = getResources().getDrawable(R.drawable.ic_agente);
		btAgente.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopAgente , null, null);
		btAgente.setOnClickListener(this);					
		btAgente.setLayoutParams(layoutParams);						
		btAgente.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		//btAgente.setWidth(30);
		
		
		/*Botao Medico*/
		btMedico = new Button(this.getActivity());		
		btMedico.setText( getResources().getString(R.string.menu_medico) );
		Drawable drawableTopMedico = getResources().getDrawable(R.drawable.ic_medico);
		btMedico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopMedico , null, null);
		btMedico.setOnClickListener(this);		
		btMedico.setLayoutParams(layoutParams);
		btMedico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		
		
		/*Botao Bairro*/
		btBairro = new Button(this.getActivity());		
		btBairro.setText( getResources().getString(R.string.menu_bairro) );
		Drawable drawableTopBairro = getResources().getDrawable(R.drawable.ic_bairro);						
		btBairro.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopBairro , null, null);
		btBairro.setOnClickListener(this);		
		btBairro.setLayoutParams(layoutParams);									
		btBairro.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		
		
		/*Botao Ubs*/
		btUbs = new Button(this.getActivity());		
		btUbs.setText( getResources().getString(R.string.menu_ubs) );
		Drawable drawableTopUbs = getResources().getDrawable(R.drawable.ic_ubs);
		btUbs.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopUbs, null, null);
		btUbs.setOnClickListener(this);		
		btUbs.setLayoutParams(layoutParams);
		btUbs.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		
		
		
		/*Botao ColetarDados*/
		btColetaDados = new Button(this.getActivity());		
		btColetaDados.setText( getResources().getString(R.string.menu_coletadados) );
		Drawable drawableTopColetaDados = getResources().getDrawable(R.drawable.ic_coleta);
		btColetaDados.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopColetaDados , null, null);
		btColetaDados.setOnClickListener(this);		
		btColetaDados.setLayoutParams(layoutParams);									
		btColetaDados.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );			
								
		/*Botao HistoricoColeta*/
		btHistoricoColeta = new Button(this.getActivity());		
		btHistoricoColeta.setText( getResources().getString(R.string.menu_historico_coleta) );
		Drawable drawableTopHistoricoColeta = getResources().getDrawable(R.drawable.historico);
		btHistoricoColeta.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopHistoricoColeta , null, null);
		btHistoricoColeta.setOnClickListener(this);		
		btHistoricoColeta.setLayoutParams(layoutParams);									
		btHistoricoColeta.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		btHistoricoColeta.setWidth(30);
		
		
		/*Botao Diagnostico*/
		btDiagnostico = new Button(this.getActivity());		
		btDiagnostico.setText( getResources().getString(R.string.menu_diagnosticar) );
		Drawable drawableTopDiagnostico= getResources().getDrawable(R.drawable.ic_diagnostico);
		btDiagnostico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopDiagnostico , null, null);
		btDiagnostico.setOnClickListener(this);		
		btDiagnostico.setLayoutParams(layoutParams);									
		btDiagnostico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		
		/*Botao HistoricoDiagnostico*/
		btHistoricoDiagnostico = new Button(this.getActivity());		
		btHistoricoDiagnostico.setText( getResources().getString(R.string.menu_historico_diagnostico) );
		Drawable drawableTopHistoricoDiagnostico = getResources().getDrawable(R.drawable.ic_historico);
		btHistoricoDiagnostico.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopHistoricoDiagnostico , null, null);
		btHistoricoDiagnostico.setOnClickListener(this);		
		btHistoricoDiagnostico.setLayoutParams(layoutParams);									
		btHistoricoDiagnostico.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		btHistoricoDiagnostico.setWidth(30);
		
		if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
					
															
			dash.addView(btPaciente);
			dash.addView(btAgente);		
			dash.addView(btMedico);	
			dash.addView(btBairro);
			dash.addView(btUbs);
			
			
		}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.PACIENTE)){
																					
			dash.addView(btColetaDados);
			dash.addView(btHistoricoColeta);		
			//dash.addView(btHistoricoDiagnostico);							
			
		}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.AGENTE)){
			
														
			
			dash.addView(btPaciente);					
			dash.addView(btMedico);
			dash.addView(btBairro);
			dash.addView(btUbs);
			dash.addView(btColetaDados);
			dash.addView(btHistoricoColeta);
			
		}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.MEDICO)){
			
													
			
			
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
	public void onClick(View view) {
			 
			if(view.equals(btPaciente)) {				    			
				((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_PACIENTE );    			
			}else if(view.equals(btMedico)) {    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_MEDICO );
    		}else if(view.equals(btAgente)) {    			    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_AGENTE );    			    			
    		}else if(view.equals(btBairro)) {    			    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_BAIRRO );
    		}else if(view.equals(btUbs)) {    			    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_UBS );
    		}else if(view.equals(btColetaDados)){    	
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem(RequestCodes.MENU_COLETA_DADOS);
    		}else if(view.equals(btHistoricoColeta)){    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_HISTORICO_COLETA );
    		}else if(view.equals(btDiagnostico)){    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_DIAGNOSTICAR );
    		}else if(view.equals(btHistoricoDiagnostico)){    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_HISTORICO_DIAGNOSTICO );
    		}    				
    		
	}
}