package br.fucapi.fapeam.monitori.activity;


import java.util.ArrayList;
import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.controller.NavigationController;
import br.fucapi.fapeam.monitori.eula.Eula;
import br.fucapi.fapeam.monitori.fragment.AgenteFragment;
import br.fucapi.fapeam.monitori.fragment.BairroFragment;
import br.fucapi.fapeam.monitori.fragment.ColetarFragment;
import br.fucapi.fapeam.monitori.fragment.DiagnosticarFragment;
import br.fucapi.fapeam.monitori.fragment.LoginFragment;
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
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MenuPrincipalFragment()).commit();
			Eula.show(this, R.string.eula_title, R.string.eula_accept, R.string.eula_refuse);
		}
	}
	
	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
		
		NavDrawerItem[] menu = null;
		
		List<NavDrawerItem> listMenu = new ArrayList<NavDrawerItem>();
		
		usuarioLogado = super.getUsuarioLogado();
		
		NavDrawerItem secaoCadastro = NavMenuSection.create( 100, "Cadastros");
		NavDrawerItem menuPaciente = NavMenuItem.create( RequestCodes.MENU_PACIENTE , getString(R.string.menu_paciente), R.drawable.ic_paciente, true, this);
		NavDrawerItem menuAgente = NavMenuItem.create(RequestCodes.MENU_AGENTE , getString(R.string.menu_agente) , R.drawable.ic_agente, true, this);
		NavDrawerItem menuMedico = NavMenuItem.create(RequestCodes.MENU_MEDICO,getString(R.string.menu_medico), R.drawable.ic_medico, true, this);						
		NavDrawerItem menuBairro = NavMenuItem.create(RequestCodes.MENU_BAIRRO,getString(R.string.menu_bairro), R.drawable.ic_bairro, true, this);
		NavDrawerItem menuUbs = NavMenuItem.create(RequestCodes.MENU_UBS,getString(R.string.menu_ubs), R.drawable.ic_ubs, true, this);
		NavDrawerItem secaoFuncionalidades = NavMenuSection.create(300, "Funcionalidades");
		
		NavDrawerItem menuHistorico = NavMenuItem.create(RequestCodes.MENU_HISTORICO,getString(R.string.title_activity_historico), android.R.drawable.sym_def_app_icon, true, this);
		NavDrawerItem menuColetaDados = NavMenuItem.create(RequestCodes.MENU_COLETA_DADOS,getString(R.string.title_activity_coletar_dados), R.drawable.ic_coleta, true, this);						
		NavDrawerItem menuDiagnosticar = NavMenuItem.create(RequestCodes.MENU_DIAGNOSTICAR, getString(R.string.title_activity_diagnosticar_dados), android.R.drawable.ic_popup_sync, true, this);
		NavDrawerItem menuHistDiagnostico = NavMenuItem.create(RequestCodes.MENU_HISTORICO_DIAGNOSTICO, getString(R.string.title_activity_diagnosticar), android.R.drawable.ic_popup_sync, true, this);
		
		NavDrawerItem secaoApp = NavMenuSection.create(200, "Aplicação");
		
		NavDrawerItem menuLogin = NavMenuItem.create(RequestCodes.MENU_LOGIN, getString(R.string.menu_login), R.drawable.navdrawer_friends, true, this);
		
		NavDrawerItem menuAvaliar = NavMenuItem.create(202, "Avaliar este app", "navdrawer_rating", false, this);
		NavDrawerItem menuEULA = NavMenuItem.create(203, "Licença de Uso", "navdrawer_eula", false, this);
		NavDrawerItem menuSair = NavMenuItem.create(204, "Quit", "navdrawer_quit", false, this);
		
		
		
		if(usuarioLogado!=null){
			if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
				
				listMenu.add(secaoCadastro);
				listMenu.add(menuPaciente);
				listMenu.add(menuAgente);
				listMenu.add(menuMedico);
				listMenu.add(menuBairro);
				listMenu.add(menuUbs);
				/*
				listMenu.add(secaoFuncionalidades);
				
				listMenu.add(menuHistorico);
				listMenu.add(menuColetaDados);
				listMenu.add(menuDiagnosticar);
				*/
				listMenu.add(secaoApp);
				listMenu.add(menuLogin);
				listMenu.add(menuAvaliar);
				listMenu.add(menuEULA);
				listMenu.add(menuSair);				
				
				
			}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.PACIENTE)){
								
				listMenu.add(secaoFuncionalidades);				
								
				listMenu.add(menuColetaDados);
				listMenu.add(menuHistorico);
				listMenu.add(menuHistDiagnostico);
				
				listMenu.add(secaoApp);
				listMenu.add(menuLogin);
				listMenu.add(menuAvaliar);
				listMenu.add(menuEULA);
				listMenu.add(menuSair);
				
				
			}else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.MEDICO)){
								
				listMenu.add(secaoCadastro);
				listMenu.add(menuPaciente);
				listMenu.add(secaoFuncionalidades);
								
				listMenu.add(menuDiagnosticar);
				
				listMenu.add(secaoApp);
				listMenu.add(menuLogin);
				listMenu.add(menuAvaliar);
				listMenu.add(menuEULA);
				listMenu.add(menuSair);
								
			}if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.AGENTE)){
				
				listMenu.add(secaoCadastro);
				listMenu.add(menuPaciente);				
				listMenu.add(menuMedico);
				listMenu.add(menuBairro);
				listMenu.add(menuUbs);
				listMenu.add(secaoFuncionalidades);
				
				listMenu.add(menuHistorico);
				listMenu.add(menuColetaDados);				
				
				listMenu.add(secaoApp);
				listMenu.add(menuLogin);
				listMenu.add(menuAvaliar);
				listMenu.add(menuEULA);
				listMenu.add(menuSair);
								
				
			}
						
		}else{
			
		}
				
		
		NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
		navDrawerActivityConfiguration.setMainLayout(R.layout.main);
		navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
		navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
		navDrawerActivityConfiguration.setNavItems(listMenu);
		navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);		
		navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
		navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
		navDrawerActivityConfiguration.setBaseAdapter(
			new NavDrawerAdapter(this, R.layout.navdrawer_item, listMenu ));
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
		
		case RequestCodes.MENU_BAIRRO:			
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new BairroFragment()).commit();
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
