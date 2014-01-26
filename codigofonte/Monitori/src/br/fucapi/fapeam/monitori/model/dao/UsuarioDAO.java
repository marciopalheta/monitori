package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
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
	private static final int VERSAO = 1;
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
				+ "senha TEXT, foto TEXT)";
		
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
	
	public void cadastrar (Paciente paciente){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();
		//Definicao dos valores dos campos
		values.put("nome", paciente.getNome());
		values.put("endereco", paciente.getEndereco());
		values.put("cep", paciente.getCep());
		values.put("celular", paciente.getCelular());
		values.put("telefone", paciente.getTelefone());
		values.put("login", paciente.getLogin());
		values.put("senha", paciente.getSenha());
		
		//Inserir dados do usuario
		getWritableDatabase().insert(TABELA, null, values);
		Log.i(TAG, "Usuario Cadastrado: "+ paciente.getNome());
	}
	
	public List<Paciente> listar(){
		//Colecao de usuarios
		List<Paciente> lista = new ArrayList<Paciente>();
		
		//Definicao da instrucao SQL
		String sql = "Select * from Usuario order by nome";
		
		//Objeto que recebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				Paciente paciente = new Paciente();
				//Carregar os atributos dos usuarios
				paciente.setId(cursor.getLong(0));
				paciente.setNome(cursor.getString(1));
				paciente.setEndereco(cursor.getString(2));
				paciente.setCep(cursor.getString(3));
				paciente.setCelular(cursor.getString(4));
				paciente.setTelefone(cursor.getString(5));
				paciente.setMasculino(cursor.getString(6));
				paciente.setFeminino(cursor.getString(7));
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
}
