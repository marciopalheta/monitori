package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;

public class BairroDAO extends AbstractDataBase{
		
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_BAIRRO";
	private Context context; 
	public BairroDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
		this.context=context;
	}	

	/** 
	 * metodo responsavel pelo cadastro do usuario
	 * */
	public void cadastrar (Bairro bairro){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();
		//Definicao dos valores dos campos
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.nome, bairro.getNome());			
		
		//Inserir dados do Bairro
		getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_BAIRRO_NAME, null, values);
						
		Log.i(TAG, "nome: "+ bairro.getNome() );						
	}
	
	/** 
	 * metodo responsavel pela listagem dos bairros na tela
	 * */
	public List<Bairro> listar(){
		//Colecao de usuarios
		List<Bairro> list_bairros = new ArrayList<Bairro>();
		Bairro bairro;
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_BAIRRO_NAME +" ORDER BY " + SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.nome;
				
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				bairro = new Bairro();
				
				//Log.e(TAG, "nome bairro = "+ cursor.getString(cursor.getColumnIndex("nome")) );
				//Carregar os atributos das UBS
				bairro.setId(cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.id ) )); 
				bairro.setNome(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.nome )));																												
				
				//Adiciona um novo usuario a lista
				list_bairros.add(bairro);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return list_bairros;
	}
	
	public List <SpinnerObject> getBairrosForSpinner(){
	    
		//Colecao de bairros
		List<SpinnerObject> list_bairros = new ArrayList<SpinnerObject>();		
		
		//primeiro elemento da lista
		String text = context.getString(R.string.bairro_prompt);
		list_bairros.add(new SpinnerObject(0, text));
		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_BAIRRO_NAME +" order by "+SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.nome;
						
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){				
				//Adiciona um novo bairro a lista								
				list_bairros.add ( new SpinnerObject ( 
						cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.id) ) , 
						cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.nome) )
						) );
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return list_bairros;
	    	    
	}
		
	
	/** 
	 * metodo responsavel pela exclusao de UBS
	 * */
	public void deletar(Bairro bairro) {
		//Array de parametros
		String[] args = {bairro.getId().toString()};
		
		//Exclusao do usuario
		getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_BAIRRO_NAME, "id=?", args);
		Log.i(TAG, "Bairro Deletado: " +bairro.getNome());
	}
	
	/** 
	 * metodo responsavel pela atualizacao de UBS
	 * */
	public void alterar(Bairro bairro) {
		ContentValues values = new ContentValues();
		
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.nome, bairro.getNome());			
								
		Log.i(TAG, "nome: "+ bairro.getNome() );
		
		// Colecao de valores de parametros do SQL
		String[] args = { bairro.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_BAIRRO_NAME, values, "id=?", args);
		Log.i(TAG, "bairro alterado: " + bairro.getNome());
	}

	public Bairro getBairro(long idBairro){

		Bairro bairro = null;
		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_BAIRRO_NAME+" where "+SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.id+"='"+idBairro+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				bairro = new Bairro();
				
				bairro.setId(cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.id) ));							
				bairro.setNome(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_BAIRRO.nome) ));		
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return bairro;
	}
	
}
