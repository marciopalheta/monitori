package br.fucapi.fapeam.monitori.activity;


import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.controller.NavigationController;
import br.fucapi.fapeam.monitori.eula.Eula;
import br.fucapi.fapeam.monitori.fragment.AgenteFragment;
import br.fucapi.fapeam.monitori.fragment.ColetarFragment;
import br.fucapi.fapeam.monitori.fragment.DiagnosticarFragment;
import br.fucapi.fapeam.monitori.fragment.LoginFragment;
import br.fucapi.fapeam.monitori.fragment.MainFragment;
import br.fucapi.fapeam.monitori.fragment.MedicoFragment;
import br.fucapi.fapeam.monitori.fragment.MenuPrincipalFragment;
import br.fucapi.fapeam.monitori.fragment.PacienteFragment;
import br.fucapi.fapeam.monitori.fragment.UnidadeSaudeFragment;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.navdrawer.AbstractNavDrawerActivity;
import br.fucapi.fapeam.monitori.navdrawer.NavDrawerActivityConfiguration;
import br.fucapi.fapeam.monitori.navdrawer.NavDrawerAdapter;
import br.fucapi.fapeam.monitori.navdrawer.NavDrawerItem;
import br.fucapi.fapeam.monitori.navdrawer.NavMenuItem;
import br.fucapi.fapeam.monitori.navdrawer.NavMenuSection;
import br.fucapi.fapeam.monitori.utils.PutExtras;
import br.fucapi.fapeam.monitori.utils.RequestCodes;


//import com.myappnavdrower.friends.FriendMainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class AppMainActivity extends AbstractNavDrawerActivity {
	
	private Usuario usuarioLogado = null;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ( savedInstanceState == null ) {
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
			Eula.show(this, R.string.eula_title, R.string.eula_accept, R.string.eula_refuse);
		}
	}
	
	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
		
		NavDrawerItem[] menu = null;
		usuarioLogado = super.getUsuarioLogado();
		
		if(usuarioLogado!=null){
			if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
				menu = new NavDrawerItem[] {
						NavMenuSection.create( 100, "Demos"),
						NavMenuItem.create( RequestCodes.MENU_PACIENTE , getString(R.string.title_activity_paciente), R.drawable.navdrawer_friends, true, this),
						NavMenuItem.create(RequestCodes.MENU_AGENTE , getString(R.string.title_activity_agente) , R.drawable.ic_agente, true, this),
						NavMenuItem.create(RequestCodes.MENU_MEDICO,getString(R.string.title_activity_medico), R.drawable.ic_medico, true, this),
						NavMenuSection.create(300, "Testes"),
						NavMenuItem.create(301,getString(R.string.title_activity_menu_principal), android.R.drawable.ic_popup_sync, true, this),
						NavMenuItem.create(RequestCodes.MENU_LOGIN,getString(R.string.title_activity_login), android.R.drawable.btn_star, true, this),
						NavMenuItem.create(RequestCodes.MENU_HISTORICO,getString(R.string.title_activity_coletar_dados), android.R.drawable.sym_def_app_icon, true, this),
						NavMenuItem.create(RequestCodes.MENU_COLETA_DADOS,getString(R.string.title_activity_coletar_dados), android.R.drawable.sym_def_app_icon, true, this),
						NavMenuItem.create(RequestCodes.MENU_UBS,getString(R.string.title_activity_ubs), "ic_medico", true, this),
						NavMenuItem.create(RequestCodes.MENU_DIAGNOSTICAR, getString(R.string.title_activity_diagnosticar_dados), android.R.drawable.ic_popup_sync, true, this),
						//NavMenuItem.create(103,getString(R.string.title_activity_), "ic_medico", true, this),
						
						NavMenuSection.create(200, "General"),
						NavMenuItem.create(202, "Rate this app", "navdrawer_rating", false, this),
						NavMenuItem.create(203, "Eula", "navdrawer_eula", false, this), 
						NavMenuItem.create(204, "Quit", "navdrawer_quit", false, this)};	
			}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.PACIENTE)){
				
				menu = new NavDrawerItem[] {
						NavMenuSection.create( 100, "Demos"),
						NavMenuItem.create(RequestCodes.MENU_COLETA_DADOS,getString(R.string.title_activity_coletar_dados), android.R.drawable.sym_def_app_icon, false, this),
						
						NavMenuItem.create( RequestCodes.MENU_HISTORICO , getString(R.string.title_activity_historico), R.drawable.navdrawer_friends, true, this),
																		
						NavMenuSection.create(200, "General"),
						NavMenuItem.create(202, "Rate this app", "navdrawer_rating", false, this),
						NavMenuItem.create(203, "Eula", "navdrawer_eula", false, this), 
						NavMenuItem.create(204, "Quit", "navdrawer_quit", false, this)};
				
			}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.MEDICO)){
				
				menu = new NavDrawerItem[] {
						NavMenuSection.create( 100, "Demos"),
						NavMenuItem.create( RequestCodes.MENU_PACIENTE , getString(R.string.title_activity_paciente), R.drawable.navdrawer_friends, true, this),
						NavMenuItem.create(RequestCodes.MENU_DIAGNOSTICAR, getString(R.string.title_activity_diagnosticar), R.drawable.ic_menu_preferences, true, this),
																		
						NavMenuSection.create(200, "General"),
						NavMenuItem.create(202, "Rate this app", "navdrawer_rating", false, this),
						NavMenuItem.create(203, "Eula", "navdrawer_eula", false, this), 
						NavMenuItem.create(204, "Quit", "navdrawer_quit", false, this)};
				
			}if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.AGENTE)){
				
				menu = new NavDrawerItem[] {
						NavMenuSection.create( 100, "Demos"),
						NavMenuItem.create( RequestCodes.MENU_PACIENTE , getString(R.string.title_activity_paciente), R.drawable.navdrawer_friends, true, this),						
						NavMenuItem.create(RequestCodes.MENU_MEDICO,getString(R.string.title_activity_medico), R.drawable.ic_medico, true, this),
						NavMenuItem.create(RequestCodes.MENU_COLETA_DADOS,getString(R.string.title_activity_coletar_dados), android.R.drawable.sym_def_app_icon, true, this),
						NavMenuItem.create( 500 , getString(R.string.title_activity_historico), R.drawable.navdrawer_friends, true, this),
																														
						NavMenuSection.create(200, "General"),
						NavMenuItem.create(202, "Rate this app", "navdrawer_rating", false, this),
						NavMenuItem.create(203, "Eula", "navdrawer_eula", false, this), 
						NavMenuItem.create(204, "Quit", "navdrawer_quit", false, this)};
				
			}
						
		}else{
			
			menu = new NavDrawerItem[] {
					NavMenuSection.create( 100, "Demos"),
					NavMenuItem.create( RequestCodes.MENU_PACIENTE , getString(R.string.title_activity_paciente), R.drawable.navdrawer_friends, true, this),
					NavMenuItem.create(RequestCodes.MENU_AGENTE , getString(R.string.title_activity_agente) , R.drawable.ic_agente, true, this),
					NavMenuItem.create(RequestCodes.MENU_MEDICO,getString(R.string.title_activity_medico), R.drawable.ic_medico, true, this),
					NavMenuSection.create(300, "Testes"),
					NavMenuItem.create(301,getString(R.string.title_activity_menu_principal), android.R.drawable.ic_popup_sync, true, this),
					NavMenuItem.create(RequestCodes.MENU_LOGIN,getString(R.string.title_activity_login), android.R.drawable.btn_star, true, this),
					NavMenuItem.create(RequestCodes.MENU_HISTORICO,getString(R.string.title_activity_historico), android.R.drawable.sym_def_app_icon, true, this),
					NavMenuItem.create(RequestCodes.MENU_COLETA_DADOS,getString(R.string.title_activity_coletar_dados), android.R.drawable.sym_def_app_icon, true, this),
					NavMenuItem.create(RequestCodes.MENU_UBS,getString(R.string.title_activity_ubs), "ic_medico", true, this),
					NavMenuItem.create(RequestCodes.MENU_DIAGNOSTICAR, getString(R.string.title_activity_diagnosticar_dados), android.R.drawable.ic_popup_sync, true, this),
					//NavMenuItem.create(103,getString(R.string.title_activity_), "ic_medico", true, this),
					
					NavMenuSection.create(200, "General"),
					NavMenuItem.create(202, "Rate this app", "navdrawer_rating", false, this),
					NavMenuItem.create(203, "Eula", "navdrawer_eula", false, this), 
					NavMenuItem.create(204, "Quit", "navdrawer_quit", false, this)};
		}
				
		
		NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
		navDrawerActivityConfiguration.setMainLayout(R.layout.main);
		navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
		navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
		navDrawerActivityConfiguration.setNavItems(menu);
		navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);		
		navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
		navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
		navDrawerActivityConfiguration.setBaseAdapter(
			new NavDrawerAdapter(this, R.layout.navdrawer_item, menu ));
		return navDrawerActivityConfiguration;
	}
	
	@Override
	protected void onNavItemSelected(int id) {
		Intent intent;
		Fragment frag;
		Bundle args;
		switch ((int)id) {
		case RequestCodes.MENU_PACIENTE:		
			frag = new PacienteFragment();
			args = new Bundle();			
			args.putSerializable(PutExtras.USUARIO_LOGADO, usuarioLogado);
			frag.setArguments(args);
			
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frag ).commit();			
			break;
		case RequestCodes.MENU_AGENTE:
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AgenteFragment()).commit();			//
			break;
		case RequestCodes.MENU_MEDICO:
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MedicoFragment()).commit();			
			break;
			
		case RequestCodes.MENU_DIAGNOSTICAR:
			frag = new DiagnosticarFragment();
			args = new Bundle();			
			args.putSerializable(PutExtras.USUARIO_LOGADO, usuarioLogado);
			frag.setArguments(args);
			
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frag ).commit();			
			break;
		case 201:
			//NavigationController.getInstance().showSettings(this);
			break;
		case 202:
			NavigationController.getInstance().startAppRating(this);
			break;
		case 203:
			NavigationController.getInstance().showEula(this);
			break;
		case 204:
			NavigationController.getInstance().showExitDialog(this);
			break;
			
		case 301:
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MenuPrincipalFragment()).commit();		
			break;
		case RequestCodes.MENU_LOGIN:			
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();								
			break;
		case RequestCodes.MENU_COLETA_DADOS:
			
			intent = new Intent(this, ColetarDadosActivity.class);
			if(usuarioLogado!=null){
				if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.PACIENTE)){
					intent.putExtra(PutExtras.PACIENTE_SELECIONADO, usuarioLogado);
				}
			}
			//Carrega a nova tela
			this.startActivity(intent);
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AgenteFragment()).commit();
			
			break;
		case RequestCodes.MENU_HISTORICO:
			if(usuarioLogado!=null){
							
				if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.PACIENTE)){
					frag = new ColetarFragment();
					args = new Bundle();			
					args.putSerializable(PutExtras.PACIENTE_SELECIONADO, usuarioLogado);
					frag.setArguments(args);
					getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frag ).commit();
				}else{
					
					intent = new Intent(this, ColetarActivity.class);
					//intent.putExtra(PutExtras.PACIENTE_SELECIONADO, usuarioLogado);
					//intent = new Intent(this,MenuPrincipalActivity.class);
					//Carrega a nova tela
					this.startActivity(intent);
					
				}
			}
		
						
			
			break;
		case RequestCodes.MENU_UBS:			
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new UnidadeSaudeFragment()).commit();
			break;
			
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RequestCodes.PREFERENCES_RESULTCODE) {
			Toast.makeText(this, "Back from preferences", Toast.LENGTH_SHORT)
					.show();

		}
	}
}