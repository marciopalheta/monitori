package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;

public class UnidadeSaudeDAO extends AbstractDataBase{
		
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_UBS";
		
	public UnidadeSaudeDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
	}	

	/** 
	 * metodo responsavel pelo cadastro do usuario
	 * */
	public void cadastrar (UnidadeSaude ubs){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();
		//Definicao dos valores dos campos
		values.put( SQLiteDatabaseHelper.FIELDS_TABLE_UBS.nome , ubs.getNome());			
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.endereco, ubs.getEndereco());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.numero, ubs.getNumeroUBS() );
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.cep, ubs.getCep() );		
		//values.put("bairro", ubs.getBairro().getId());	
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.telefone, ubs.getFone());

		//Inserir dados da UBS
		getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_UBS_NAME, null, values);
						
		Log.i(TAG, "nome: "+ ubs.getNome() );
		Log.i(TAG, "endereco: "+ ubs.getEndereco() );
		Log.i(TAG, "numero: "+ ubs.getNumeroUBS() );
		Log.i(TAG, "cep: "+ ubs.getCep() );
		//Log.i(TAG, "bairro: "+ ubs.getBairro().getNome() );
		Log.i(TAG, "fone: "+ ubs.getFone() );
							
	}
	
	/** 
	 * metodo responsavel pela listagem dos usuarios na tela
	 * */
	public List<UnidadeSaude> listar(){
		//Colecao de usuarios
		List<UnidadeSaude> lista = new ArrayList<UnidadeSaude>();
		UnidadeSaude ubs;
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_UBS_NAME +" ";
				
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				ubs = new UnidadeSaude();						
				Log.e(TAG, "nome ubs = "+ cursor.getString(cursor.getColumnIndex("nome")) );
				//Carregar os atributos das UBS
				ubs.setId(cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.id) )); 
				ubs.setNome(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.nome)));
				ubs.setEndereco(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.endereco)));				
				ubs.setCep(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.cep)));
				ubs.setFone(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.telefone)));
				ubs.setNumeroUBS(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.numero)));
				/*
				BairroDAO bairroDao = new BairroDAO();
				bairroDao.
				ubs.setBairro( cursor.getString(cursor.getColumnIndex("fone")));
				*/																		
				
				//Adiciona um novo usuario a lista
				lista.add(ubs);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return lista;
	}
	
	/** 
	 * metodo responsavel pela exclusao de UBS
	 * */
	public void deletar(UnidadeSaude ubs) {
		//Array de parametros
		String[] args = {ubs.getId().toString()};
		
		//Exclusao do usuario
		getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_UBS_NAME, "id=?", args);
		Log.i(TAG, "UBS Deletado: " +ubs.getNome());
	}
	
	/** 
	 * metodo responsavel pela atualizacao de UBS
	 * */
	public void alterar(UnidadeSaude ubs) {
		ContentValues values = new ContentValues();
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.nome, ubs.getNome());
				
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.endereco, ubs.getEndereco());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.numero, ubs.getNumeroUBS() );
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.cep, ubs.getCep() );		
		//values.put("bairro", ubs.getBairro().getId());	
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.telefone, ubs.getFone());
								
		Log.i(TAG, "nome: "+ ubs.getNome() );
		Log.i(TAG, "endereco: "+ ubs.getEndereco() );
		Log.i(TAG, "numero: "+ ubs.getNumeroUBS() );
		Log.i(TAG, "cep: "+ ubs.getCep() );
		//Log.i(TAG, "bairro: "+ ubs.getBairro().getNome() );
		Log.i(TAG, "fone: "+ ubs.getFone() );
		
		// Colecao de valores de parametros do SQL
		String[] args = { ubs.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_UBS_NAME, values, "id=?", args);
		Log.i(TAG, "UBS alterado: " + ubs.getNome());
	}
			
	public UnidadeSaude getUnidadeSaude(long id){
		//Colecao de usuarios
		UnidadeSaude ubs = null;
		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_UBS_NAME+" where "+SQLiteDatabaseHelper.FIELDS_TABLE_UBS.id+"='"+id+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				ubs = new UnidadeSaude();
				
				ubs.setId(cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.id) ));							
				ubs.setNome(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.nome) ));				
				ubs.setEndereco(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.endereco) ));								
				ubs.setNumeroUBS(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.numero) ));								
				ubs.setCep(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.cep) ));				
				
				ubs.setFone(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.telefone) ));
				
				//ubs.setBairro(bairro);			
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return ubs;
	}
}