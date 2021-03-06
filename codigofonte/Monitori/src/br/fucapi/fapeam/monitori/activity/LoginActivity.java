package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.model.dao.UsuarioDAO;
import br.fucapi.fapeam.monitori.utils.Mask;
import br.fucapi.fapeam.monitori.utils.PutExtras;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	//Atributos de Tela
	private EditText login;
	private EditText senha;
	private Button bt_Logar;
	
	private Usuario usuarioLogado = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		login = (EditText) findViewById(R.id.edt_login);
		senha = (EditText) findViewById(R.id.edt_senha);
		bt_Logar = (Button) findViewById(R.id.btValidar);
		login.setText("123.456.789-00");				
		senha.setText("123.456.789-00");
		
		login.addTextChangedListener(Mask.insert("###.###.###-##", login));
		senha.addTextChangedListener(Mask.insert("###.###.###-##", senha));
		
		bt_Logar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//mudarTela();
				validarUsuario();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	/*
	public boolean onOptionsItemSelected(MenuItem item){
		Intent intent;
		//Verifica o item do menu selecionado
		switch(item.getItemId()){
			//Verifica se foi selecionado um item novo
								
			case R.id.menu_agente:
				//Especialista em mudanca de tela
				intent = new Intent(LoginActivity.this,
						AgenteActivity.class);
				//Carrega a nova tela
				startActivity(intent);
			
				return false;
				
			case R.id.menu_paciente:
				//Especialista em mudanca de tela
				intent = new Intent(LoginActivity.this,
						PacienteActivity.class);
				//Carrega a nova tela
				startActivity(intent);
				
				return false;
				
			case R.id.menu_medico:
				//Especialista em mudanca de tela
				intent = new Intent(LoginActivity.this,
						MedicoActivity.class);
				//Carrega a nova tela
				startActivity(intent);
				
				return false;
				
			case R.id.menu_coletar_dados:
				//Especialista em mudanca de tela
				intent = new Intent(LoginActivity.this,
						ColetarActivity.class);
				//Carrega a nova tela
				startActivity(intent);
				
				return false;
				
			
			
			case R.id.menu_principal:
				//Especialista em mudanca de tela				
				intent = new Intent(LoginActivity.this,MenuPrincipalActivity.class);
				//Carrega a nova tela
				startActivity(intent);
				
				return false;	
				
			
				
			case R.id.app_principal:
				//Especialista em mudanca de tela
				intent = new Intent(this, AppMainActivity.class);
				//intent = new Intent(this,MenuPrincipalActivity.class);
				//Carrega a nova tela
				this.startActivity(intent);
				//this.finish();				
				
				return false;

			default:
				return super.onOptionsItemSelected(item);
		}
	}
	*/
	
	private void mudarTela(){		
		
		//Intent intent = new Intent(LoginActivity.this,MenuPrincipalActivity.class);
		Intent intent = new Intent(this, AppMainActivity.class);
		
		intent.putExtra(PutExtras.USUARIO_LOGADO, usuarioLogado);
		startActivity(intent);//Carrega a nova tela		
				
		this.finish();
	}
	
	public void validarUsuario(){
		//Criacao do objeto DAO
		UsuarioDAO dao = new UsuarioDAO(this);
		usuarioLogado = new Usuario();
		//Chamado do metodo listar
		
		//Toast.makeText(LoginActivity.this, "login: "+login.getText().toString() , Toast.LENGTH_LONG).show();
		//Toast.makeText(LoginActivity.this, "senha: "+senha.getText().toString() , Toast.LENGTH_LONG).show();
		
		
		usuarioLogado = dao.getUsuario(login.getText().toString(), senha.getText().toString());
		//fim da conexao do DB
		dao.close();
				
		if(usuarioLogado != null){
			Toast.makeText(LoginActivity.this, "usuario: "+usuarioLogado.getNome()+" autenticado", Toast.LENGTH_LONG).show();
			mudarTela();
		}else{
			Toast.makeText(LoginActivity.this, "Login Falhou", Toast.LENGTH_LONG).show();
		}
	}	
}
