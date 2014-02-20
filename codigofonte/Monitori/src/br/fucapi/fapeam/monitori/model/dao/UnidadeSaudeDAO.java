package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;

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
		values.put("nome", ubs.getNome());			
		values.put("endereco", ubs.getEndereco());
		values.put("numero", ubs.getNumeroUBS() );
		values.put("cep", ubs.getCep() );		
		//values.put("bairro", ubs.getBairro().getId());	
		values.put("fone", ubs.getFone());

		//Inserir dados da UBS
		getWritableDatabase().insert(AbstractDataBase.TABLE_UBS, null, values);
						
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
		String sql = "Select * from "+AbstractDataBase.TABLE_UBS +" ";
		
		Log.i(TAG, "chamou listar2 = "+sql );
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				ubs = new UnidadeSaude();
				Log.i(TAG, "chamou listar2" );		
				Log.e(TAG, "nome ubs = "+ cursor.getString(cursor.getColumnIndex("nome")) );
				//Carregar os atributos das UBS
				ubs.setId(cursor.getLong(cursor.getColumnIndex("id") )); 
				ubs.setNome(cursor.getString(cursor.getColumnIndex("nome")));
				ubs.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));				
				ubs.setCep(cursor.getString(cursor.getColumnIndex("cep")));
				ubs.setFone(cursor.getString(cursor.getColumnIndex("fone")));
				ubs.setNumeroUBS(cursor.getString(cursor.getColumnIndex("numero")));
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
		getWritableDatabase().delete(AbstractDataBase.TABLE_UBS, "id=?", args);
		Log.i(TAG, "UBS Deletado: " +ubs.getNome());
	}
	
	/** 
	 * metodo responsavel pela atualizacao de UBS
	 * */
	public void alterar(UnidadeSaude ubs) {
		ContentValues values = new ContentValues();
		values.put("nome", ubs.getNome());
		
		values.put("nome", ubs.getNome());			
		values.put("endereco", ubs.getEndereco());
		values.put("numero", ubs.getNumeroUBS() );
		values.put("cep", ubs.getCep() );		
		//values.put("bairro", ubs.getBairro().getId());	
		values.put("fone", ubs.getFone());
								
		Log.i(TAG, "nome: "+ ubs.getNome() );
		Log.i(TAG, "endereco: "+ ubs.getEndereco() );
		Log.i(TAG, "numero: "+ ubs.getNumeroUBS() );
		Log.i(TAG, "cep: "+ ubs.getCep() );
		//Log.i(TAG, "bairro: "+ ubs.getBairro().getNome() );
		Log.i(TAG, "fone: "+ ubs.getFone() );
		
		
		// Colecao de valores de parametros do SQL
		String[] args = { ubs.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(AbstractDataBase.TABLE_UBS, values, "id=?", args);
		Log.i(TAG, "UBS alterado: " + ubs.getNome());
	}
	
	public UnidadeSaude getUnidadeSaude(String nome){
		//Colecao de usuarios
		UnidadeSaude ubs = null;
		
		//Definicao da instrucao SQL
		String sql = "Select * from "+AbstractDataBase.TABLE_UBS+" where nome='"+nome+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				ubs = new UnidadeSaude();
				
				ubs.setId(cursor.getLong(cursor.getColumnIndex("id") ));							
				ubs.setNome(cursor.getString(cursor.getColumnIndex("nome") ));				
				ubs.setEndereco(cursor.getString(cursor.getColumnIndex("endereco") ));								
				ubs.setNumeroUBS(cursor.getString(cursor.getColumnIndex("numero") ));								
				ubs.setCep(cursor.getString(cursor.getColumnIndex("cep") ));				
				
				ubs.setFone(cursor.getString(cursor.getColumnIndex("fone") ));
				
				//ubs.setBairro(bairro);																		
				
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return ubs;
	}
	
	public UnidadeSaude getUnidadeSaude(long id){
		//Colecao de usuarios
		UnidadeSaude ubs = null;
		
		//Definicao da instrucao SQL
		String sql = "Select * from "+AbstractDataBase.TABLE_UBS+" where id='"+id+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				ubs = new UnidadeSaude();
				
				ubs.setId(cursor.getLong(cursor.getColumnIndex("id") ));							
				ubs.setNome(cursor.getString(cursor.getColumnIndex("nome") ));				
				ubs.setEndereco(cursor.getString(cursor.getColumnIndex("endereco") ));								
				ubs.setNumeroUBS(cursor.getString(cursor.getColumnIndex("numero") ));								
				ubs.setCep(cursor.getString(cursor.getColumnIndex("cep") ));				
				
				ubs.setFone(cursor.getString(cursor.getColumnIndex("fone") ));
				
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