package br.fucapi.fapeam.monitori.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.AbstractEntityBean;
import br.fucapi.fapeam.monitori.model.bean.Agente;
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
	private static final int VERSAO = 9;
	private static final String TABELA = "Usuario";
	private static final String DATABASE = "Pacientes";
	
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_USUARIO";
	
	private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String TIME_FORMAT = "kk:mm";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
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
				+ "nomeMae TEXT, numSus TEXT, "
				+ "senha TEXT, foto TEXT, hipertenso TEXT, sexo TEXT, "
				+ "observacao TEXT, diabetico1 TEXT, diabetico2 TEXT, crm TEXT, matricula TEXT, tipoUsuario TEXT)";
		
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
		
		String dataForDB=null;		
		dataForDB = dateFormat.format(usuario.getDataNascimento().getTime());
		values.put("dataMascimento", dataForDB);
				
		values.put("endereco", usuario.getEndereco());
		values.put("cep", usuario.getCep());
		values.put("celular", usuario.getCelular());
		values.put("telefone", usuario.getTelefone());
		values.put("login", usuario.getLogin());
		values.put("senha", usuario.getNome());
		values.put("sexo", usuario.getSexo() );
		values.put("nomeMae", usuario.getNomeMae());
		values.put("numSus", usuario.getNumSus());
		
		values.put("tipoUsuario", usuario.getTipoUsuario().toString() );
		
		if(usuario instanceof Paciente){			
			values.put("hipertenso", ((Paciente)usuario).isHipertenso());			
			
		}
		
		if(usuario instanceof Medico){			
			values.put("crm", ((Medico)usuario).getCrm() );	
		}
		
		if(usuario instanceof Agente){			
			values.put("matricula", ((Agente)usuario).getMatricula() );	
		}
		
		//Inserir dados do usuario
		getWritableDatabase().insert(TABELA, null, values);
		Log.i(TAG, "Usuario Cadastrado: "+ usuario.getNome() );
		Log.i(TAG, "dataMascimento: "+ dataForDB );		
		
		Log.i(TAG, "Login: "+ usuario.getLogin() );
		Log.i(TAG, "Senha: "+ usuario.getSenha() );
		Log.i(TAG, "endereco: "+ usuario.getEndereco() );
		Log.i(TAG, "cep: "+ usuario.getCep() );
		Log.i(TAG, "celular: "+ usuario.getCelular() );
		Log.i(TAG, "telefone: "+ usuario.getTelefone() );
		Log.i(TAG, "sexo: "+ usuario.getSexo() );		
		Log.i(TAG, "tipoUsuario: "+ usuario.getTipoUsuario().toString() );
		
		
		if(usuario instanceof Paciente){						
			Log.i(TAG, "hipertenso: "+ ((Paciente)usuario).isHipertenso() );
		}				
		if(usuario instanceof Medico){						
			Log.i(TAG, "crm: "+ ((Medico)usuario).getCrm() );
		}
		if(usuario instanceof Agente){						
			Log.i(TAG, "matricula: "+ ((Agente)usuario).getMatricula() );
		}
		
	}
	
	/** 
	 * metodo responsavel pela listagem dos usuarios na tela
	 * */
	public List<Usuario> listar(TipoUsuario tipoUsuario){
		//Colecao de usuarios
		List<Usuario> lista = new ArrayList<Usuario>();
		TipoUsuario tipo;
		//Definicao da instrucao SQL
		String sql = null;
		if(tipoUsuario == TipoUsuario.PACIENTE){
			sql = "Select * from Usuario where tipoUsuario = '"+TipoUsuario.PACIENTE+"' order by nome";
		}else if(tipoUsuario == TipoUsuario.AGENTE){
			sql = "Select * from Usuario where tipoUsuario = '"+TipoUsuario.AGENTE+"' order by nome";					
		}else if(tipoUsuario == TipoUsuario.MEDICO){
			sql = "Select * from Usuario where tipoUsuario = '"+TipoUsuario.MEDICO+"' order by nome";					
		}
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				Usuario usuario = null;
				
				if(tipoUsuario == TipoUsuario.PACIENTE){
					usuario = new Paciente();
					
					((Paciente)usuario).setHipertenso( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex("hipertenso"))  ) );
					
				}else if(tipoUsuario == TipoUsuario.AGENTE){
					usuario = new Agente();					
				}else if(tipoUsuario == TipoUsuario.MEDICO){
					usuario = new Medico();					
				}
																				 
				
				//Carregar os atributos dos usuarios
				usuario.setId(cursor.getLong(cursor.getColumnIndex("id") )); 
				usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
				usuario.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
				usuario.setNomeMae(cursor.getString(cursor.getColumnIndex("nomeMae")));
				usuario.setNumSus(cursor.getString(cursor.getColumnIndex("numSus")));
				//usuario.setBairro(cursor.getString(3)); //BAIRRO	
				usuario.setCep(cursor.getString(cursor.getColumnIndex("cep"))); 
				//usuario.setUnidadeSaude(cursor.getString(5) );
				
				usuario.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
				String dtNascto=null;
				
				dtNascto = cursor.getString(cursor.getColumnIndex("dataMascimento"));
				
				//usuario.setDataNascimento(dataNascimento);
				Log.i(TAG, "dt Nascimento = "+ dtNascto);
					
					if(dtNascto !=null){
						Calendar cal = Calendar.getInstance();
						cal.setTime(dateFormat.parse(dtNascto));
						usuario.setDataNascimento(cal);
					}
				
				
				
				
				usuario.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
				//usuario.setDataNascimento(cursor.getString(8) );
				usuario.setSexo( cursor.getString(cursor.getColumnIndex("sexo")) );
				usuario.setObservacao( cursor.getString(cursor.getColumnIndex("observacao")) );
								
				
				
				
				//Adiciona um novo usuario a lista
				lista.add(usuario);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
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
	
	/** 
	 * metodo responsavel pela atualizacao de usuarios
	 * */
	public void alterar(Usuario usuario) {
		ContentValues values = new ContentValues();
		values.put("nome", usuario.getNome());
		
		String dataForDB=null;		
		dataForDB = dateFormat.format(usuario.getDataNascimento().getTime());
		values.put("dataMascimento", dataForDB);
		
		values.put("endereco", usuario.getEndereco());
		values.put("cep", usuario.getCep());
		values.put("celular", usuario.getCelular());
		values.put("telefone", usuario.getTelefone());
		values.put("nomeMae", usuario.getNomeMae());
		values.put("numSus", usuario.getNumSus());
		//values.put("login", usuario.getLogin());
		//values.put("senha", usuario.getNome());
		values.put("sexo", usuario.getSexo() );
		values.put("tipoUsuario", usuario.getTipoUsuario().toString() );
		
		if(usuario instanceof Paciente){			
			values.put("hipertenso", ((Paciente)usuario).isHipertenso());			
			
		}
		
		if(usuario instanceof Medico){			
			values.put("crm", ((Medico)usuario).getCrm() );	
		}
		
		if(usuario instanceof Agente){			
			values.put("matricula", ((Agente)usuario).getMatricula() );	
		}

		// Colecao de valores de parametros do SQL
		String[] args = { usuario.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(TABELA, values, "id=?", args);
		Log.i(TAG, "Usuario alterado: " + usuario.getNome());
	}
	
	public Usuario getUsuario(String login, String senha){
		//Colecao de usuarios
		Usuario usuario = null;
		TipoUsuario tipo;
		//Definicao da instrucao SQL
		String sql = "Select * from Usuario where login='"+login+"' and senha='"+senha+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				usuario = new Usuario();
				
				usuario.setId(cursor.getLong(cursor.getColumnIndex("id") ));							
				usuario.setNome(cursor.getString(cursor.getColumnIndex("nome") ));				
				
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
