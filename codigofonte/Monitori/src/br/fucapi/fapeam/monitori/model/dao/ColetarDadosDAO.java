package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class ColetarDadosDAO extends AbstractDataBase{
	
	//constante para Log no LogCat
	private static final String TAG = "CADASTRO_COLETAR_DADOS";
	private Context context;
	public ColetarDadosDAO(Context context) {
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
		this.context=context;
	}
	
	public long getLastInsertId() {
	    long lastId = 0;
	    	    
	    String sql = "Select ROWID from "+SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME +" order by ROWID DESC limit 1 ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
	    
	    if (cursor != null && cursor.moveToFirst()) {
	        lastId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
	    }
	    
	    cursor.close();
	    return lastId;
	}
	
	public void cadastrar (ColetarDados coletaDados){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();
		//Definicao dos valores dos campos
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis, coletaDados.getSis());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente, coletaDados.getUsuario().getId());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose, coletaDados.getGlicose());
		//sao checkbox
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum, String.valueOf
				(((ColetarDados)coletaDados).isJejum() ));
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial, String.valueOf
				(((ColetarDados)coletaDados).isJejum() ));
		
		//inserindo os dados
		getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME, null, values);
		
		Log.i(TAG, "sis: "+ coletaDados.getSis());
		Log.i(TAG, "glicose: "+ coletaDados.getGlicose());
		Log.i(TAG, "jejum: "+ coletaDados.isJejum());
		Log.i(TAG, "pos_pandrial"+ coletaDados.isPos_pandrial());
	}
	
	public List<ColetarDados> listar(){
		//colecao de coletagem de dados
		List<ColetarDados> lista = new ArrayList<ColetarDados>();
		ColetarDados coletaDados;
		
		//definicao da instrucao sql
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME +" ";
		
		//objeto que recebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		
		try{
			while (cursor.moveToNext()) {
				coletaDados = new ColetarDados();
				
				//carregar os atributos de coletar dados
				coletaDados.setId(cursor.getLong(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id)));
				coletaDados.setSis(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis)));
				coletaDados.setGlicose(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose)));
				coletaDados.setJejum(Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex
						( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum))));
				coletaDados.setJejum(Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex
						( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial))));
				
				UsuarioDAO usuarioDao = new UsuarioDAO(context);				
				Usuario usuario = usuarioDao.getUsuario( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente) )) ;
				coletaDados.setUsuario(usuario); 				
				
				
				//adiciona na lista
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
		//colecao de coletagem de dados
		List<ColetarDados> lista = new ArrayList<ColetarDados>();
		ColetarDados coletaDados;
		
		//definicao da instrucao sql
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME +" where "+SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente+"='"+paciente.getId()+"' " ;
		
		//objeto que recebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		
		try{
			while (cursor.moveToNext()) {
				coletaDados = new ColetarDados();
				
				//carregar os atributos de coletar dados
				coletaDados.setId(cursor.getLong(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id)));
				coletaDados.setSis(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis)));
				coletaDados.setGlicose(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose)));
				coletaDados.setJejum(Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex
						( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum))));
				coletaDados.setJejum(Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex
						( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial))));
				
				UsuarioDAO usuarioDao = new UsuarioDAO(context);				
				Usuario usuario = usuarioDao.getUsuario( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.idPaciente) )) ;
				coletaDados.setUsuario(usuario); 				
				
				
				//adiciona na lista
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
	 * metodo responsavel pela exclusao
	 * */
	public void deletar(ColetarDados coletaDados){
		//Array de parametros
		String[] args = {coletaDados.getId().toString()};
		
		//exclusao
		getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME,
				"id=?", args);
		Log.i(TAG, "Dados deletados: "+ coletaDados.getSis());
	}
	
	/** 
	 * metodo responsavel pela atualizacao
	 * */
	public void alterar(ColetarDados coletaDados){
		ContentValues values = new ContentValues();
		
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis, 
				coletaDados.getSis());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose,
				coletaDados.getGlicose());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial, 
				String.valueOf( coletaDados.isPos_pandrial()));
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum, 
				String.valueOf( coletaDados.isJejum()));
		
		Log.i(TAG, "sis: " +coletaDados.getSis());
		Log.i(TAG, "glicose: " +coletaDados.getGlicose());
		
		// Colecao de valores de parametros do SQL
		String[] args = { coletaDados.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME, 
				values, "id=?", args);
		Log.i(TAG, "Coleta de Dados Alterado: " +coletaDados.getSis());
		
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
