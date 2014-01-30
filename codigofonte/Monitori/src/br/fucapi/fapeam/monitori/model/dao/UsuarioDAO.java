package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.AbstractEntityBean;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuarioDAO extends SQLiteOpenHelper{
	
	//Constantes para auxiliar o controle de versoes
	private static final int VERSAO = 2;
	private static final String TABELA = "Usuario";
	private static final String DATABASE = "Pacientes";
	
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_USUARIO";
	
	public UsuarioDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context,DATABASE,null,VERSAO);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		//Definicao do comando DDL a ser executado
		String ddl = "CREATE TABLE " + TABELA + "( "
				+ "id INTEGER PRIMARY KEY, "
				+ "nome TEXT, endereco TEXT, bairro TEXT, "
				+ "cep TEXT, unidadeSaude TEXT, celular TEXT, "
				+ "telefone TEXT, dataMascimento TEXT, login TEXT, "
				+ "senha TEXT, foto TEXT, hipertenso TEXT, sexo TEXT, "
				+ "observacao TEXT, diabetico1 TEXT, diabetico2 TEXT)";
		
		//Execucao do comando no SQLite
		database.execSQL(ddl);		
	}

	/** 
	 * metodo responsavel pela atualizacao das estruturas das tabelas
	 * */
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, 
			int newVersion) {
		
		//Definindo o comando para destruir a tabela Usuario
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		
		//Executando o comando de destruicao
		database.execSQL(sql);
		
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
	}
	/** 
	 * metodo responsavel pelo cadastro do usuario
	 * */
	
	public void cadastrar (Usuario usuario){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();
		//Definicao dos valores dos campos
		values.put("nome", usuario.getNome());
		values.put("endereco", usuario.getEndereco());
		values.put("cep", usuario.getCep());
		values.put("celular", usuario.getCelular());
		values.put("telefone", usuario.getTelefone());
		values.put("login", usuario.getLogin());
		values.put("senha", usuario.getNome());
		if(usuario instanceof Paciente){
			Paciente paciente = (Paciente)usuario;
			values.put("hipertenso", paciente.isHipertenso());	
		}
		//Inserir dados do usuario
		getWritableDatabase().insert(TABELA, null, values);
		Log.i(TAG, "Usuario Cadastrado: "+ usuario.getNome() );
		Log.i(TAG, "Login: "+ usuario.getLogin() );
		Log.i(TAG, "Senha: "+ usuario.getSenha() );
		Log.i(TAG, "endereco: "+ usuario.getEndereco() );
		Log.i(TAG, "cep: "+ usuario.getCep() );
		Log.i(TAG, "celular: "+ usuario.getCelular() );
		Log.i(TAG, "telefone: "+ usuario.getTelefone() );
		
						
		
		
		
	}
	
	/** 
	 * metodo responsavel pela listagem dos usuarios na tela
	 * */
	public List<Usuario> listar(){
		//Colecao de usuarios
		List<Usuario> lista = new ArrayList<Usuario>();
		TipoUsuario tipo;
		//Definicao da instrucao SQL
		String sql = "Select * from Usuario order by nome";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				Usuario paciente = new Usuario();
				//Carregar os atributos dos usuarios
				paciente.setId(cursor.getLong(0));
				paciente.setNome(cursor.getString(1));
				paciente.setEndereco(cursor.getString(2));
				paciente.setCep(cursor.getString(3));
				paciente.setCelular(cursor.getString(4));
				paciente.setTelefone(cursor.getString(5));
				//Adiciona um novo usuario a lista
				lista.add(paciente);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return lista;
	}
	
	/** 
	 * metodo responsavel pela exclusao de usuarios
	 * */
	public void deletar(Usuario usuario) {
		//Array de parametros
		String[] args = {usuario.getId().toString()};
		
		//Exclusao do usuario
		getWritableDatabase().delete(TABELA, "id=?", args);
		Log.i(TAG, "Usuario Deletado: " +usuario.getNome());
	}
	public Object getUsuario(String login, String senha){
		//Colecao de usuarios
		Object usuario = null;
		TipoUsuario tipo;
		//Definicao da instrucao SQL
		String sql = "Select * from Usuario where login='"+login+"' and senha='"+senha+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				
				//if( cursor.getString(13) == null ){
					usuario = new Usuario();
				//}else{
					//usuario = new Medico();
					//((Medico) usuario).setCrm(crm);
				//}
				//Carregar os atributos dos usuarios
				((Usuario) usuario).setId(cursor.getLong(0));
				((Usuario) usuario).setNome(cursor.getString(1));
				((Usuario) usuario).setEndereco(cursor.getString(2));
				((Usuario) usuario).setCep(cursor.getString(3));
				((Usuario) usuario).setCelular(cursor.getString(4));
				((Usuario) usuario).setTelefone(cursor.getString(5));
				
				//Adiciona um novo usuario a lista
				
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return usuario;
	}
	
	
}
