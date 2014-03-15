package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;

public class UnidadeSaudeDAO extends AbstractDataBase{
		
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_UBS";
	private	Context context;
	public UnidadeSaudeDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
		this.context = context;
	}	

	
	public long getLastInsertId() {
	    long lastId = 0;
	    	    
	    String sql = "Select ROWID from "+SQLiteDatabaseHelper.TABLE_UBS_NAME +" order by ROWID DESC limit 1 ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
	    
	    if (cursor != null && cursor.moveToFirst()) {
	        lastId = cursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
	    }
	    
	    cursor.close();
	    return lastId;
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
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.telefone, ubs.getFone());
		
		if(ubs.getBairro() != null){			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.idBairro, ubs.getBairro().getId());
		}
				

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
				
				BairroDAO bairroDao = new BairroDAO(context);				
				Bairro bairro = bairroDao.getBairro( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_UBS.idBairro ) ) );
				ubs.setBairro(bairro);
																						
				
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
	
	
public List <SpinnerObject> getUbsForSpinner(){
	    
		//Colecao de bairros
		List<SpinnerObject> list_ubs = new ArrayList<SpinnerObject>();		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_UBS_NAME +" ";
				
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){				
				//Adiciona um novo bairro a lista								
				list_ubs.add ( new SpinnerObject ( 
						cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.id) ) , 
						cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.nome) )
						) );
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return list_ubs;
	    	    
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
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.telefone, ubs.getFone());
		
		if(ubs.getBairro() != null){			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_UBS.idBairro, ubs.getBairro().getId());
		}
								
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