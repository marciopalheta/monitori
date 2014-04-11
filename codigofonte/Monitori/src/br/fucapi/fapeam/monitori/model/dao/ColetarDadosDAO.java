package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class ColetarDadosDAO extends AbstractDataBase{
		
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_COLETAR_DADOS";
		
    private Context context;    
	public ColetarDadosDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
		this.context = context;
	}
	
	 /** 
	 * metodo responsavel pelo cadastro do usuario
	 * */
	public void cadastrar (ColetarDados coletaDados){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();	
		
		getWritableDatabase().beginTransaction();
		
		try{
			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis, coletaDados.getSis());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente, coletaDados.getUsuario().getId());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sistole, coletaDados.getSistole());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose, coletaDados.getGlicose());
			//sao checkbox
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum, 
					String.valueOf( coletaDados.isJejum() ));
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial, 
					String.valueOf(coletaDados.isPos_pandrial() ));
																	
			//Inserir dados do usuario
			getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME, null, values);
			
			getWritableDatabase().setTransactionSuccessful();// marks a commit
									
			
		}catch(SQLException sqlE) {	
			Log.e(TAG, "SqlException: "+sqlE.getMessage() );	
		}finally{
			getWritableDatabase().endTransaction();
			
			Log.i(TAG, "Paciente: "+ coletaDados.getUsuario().getNome());
			Log.i(TAG, "sis: "+ coletaDados.getSis());
			Log.i(TAG, "glicose: "+ coletaDados.getGlicose());
			Log.i(TAG, "jejum: "+ coletaDados.isJejum());
			Log.i(TAG, "pos_pandrial"+ coletaDados.isPos_pandrial());						
		}
	}
	
	/** 
	 * metodo responsavel pela listagem dos usuarios na tela
	 * */
	public List<ColetarDados> listar(){
		//Colecao de usuarios
		List<ColetarDados> lista = new ArrayList<ColetarDados>();
		ColetarDados coletaDados;
		//Definicao da instrucao SQL
		String sql = null;
						
			sql = "Select " +
					SQLiteDatabaseHelper.ALL_FIELDS_TABLE_COLETAR_DADOS+ " "+					
					" from "+SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME +" " +					
					"order by "+SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis+" ";		
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				
				coletaDados = new ColetarDados();
																				 				
				//Carregar os atributos dos usuarios
				Log.i("Id Coleta ", "id "+ cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id))  );
				Log.i("Id Paciente Coleta ","id Paciente "+ cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente))  );
				
				coletaDados.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id) )); 
				
				coletaDados.setGlicose(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose)));
				coletaDados.setSis(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis)));
				coletaDados.setSistole(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sistole)));
				
				UsuarioDAO usuarioDao = new UsuarioDAO(context);				
				Usuario usuario = usuarioDao.getUsuario( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente) )) ;
				coletaDados.setUsuario(usuario); 				
				
				coletaDados.setJejum( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum)) ) );
				coletaDados.setPos_pandrial( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial)) ) );
				
				lista.add(coletaDados);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return lista;
	}

	public List<ColetarDados> listar(Usuario paciente){
		//Colecao de usuarios
		List<ColetarDados> lista = new ArrayList<ColetarDados>();
		ColetarDados coletaDados;
		//Definicao da instrucao SQL
		String sql = null;
						
			sql = "Select " +
					SQLiteDatabaseHelper.ALL_FIELDS_TABLE_COLETAR_DADOS+ " "+					
					" from "+SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME +" " +					
					" where "+SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente+" = '"+paciente.getId()+" " +
					" order by "+SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis+" ";		
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				
				coletaDados = new ColetarDados();
																				 				
				//Carregar os atributos dos usuarios
				coletaDados.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id) )); 
				coletaDados.setGlicose(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose)));
				coletaDados.setSis(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis)));
				coletaDados.setSistole(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sistole)));
				
				coletaDados.setUsuario(paciente); 				
								
				coletaDados.setJejum( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum)) ) );
				coletaDados.setPos_pandrial( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial)) ) );
				
				lista.add(coletaDados);
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
	public void deletar(ColetarDados coletaDados) {
		//Array de parametros
		String[] args = {coletaDados.getId().toString()};
		
		//Exclusao do usuario
		getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME, "id=?", args);
		Log.i(TAG, "Coleta Deletado: " +coletaDados.getSis());
	}
	
	/** 
	 * metodo responsavel pela atualizacao de usuarios
	 * */
	public void alterar(ColetarDados coletaDados) {
		ContentValues values = new ContentValues();
						
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis, coletaDados.getSis());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente, coletaDados.getUsuario().getId());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose, coletaDados.getGlicose());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sistole, coletaDados.getSistole());
		//sao checkbox
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum, 
				String.valueOf( coletaDados.isJejum() ));
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial, 
				String.valueOf(coletaDados.isPos_pandrial() ));
		
		// Colecao de valores de parametros do SQL
		String[] args = { coletaDados.getId().toString() };
		
		// Altera dados
		getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME, values, "id=?", args);
		Log.i(TAG, "Coleta alterado: " + coletaDados.getSis());
	}
			
	public ColetarDados getColetarDados(long id){
		//Colecao de usuarios
		ColetarDados coletaDados = null;
		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME+
				" where "+SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id+"='"+id+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				coletaDados = new ColetarDados();
				
				coletaDados.setId(cursor.getLong(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id) ));							
				coletaDados.setSis(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis)));
				coletaDados.setSistole(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sistole)));
				coletaDados.setPos_pandrial(Boolean.parseBoolean(cursor.getString
						(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial))));
				coletaDados.setJejum(Boolean.parseBoolean(cursor.getString
						(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum))));
				
				UsuarioDAO usuarioDao = new UsuarioDAO(context);				
				Usuario usuario = usuarioDao.getUsuario( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente) )) ;
				coletaDados.setUsuario(usuario);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return coletaDados;
	}
}
