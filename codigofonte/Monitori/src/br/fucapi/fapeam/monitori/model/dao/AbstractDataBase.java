package br.fucapi.fapeam.monitori.model.dao;

import br.fucapi.fapeam.monitori.sqlite.DatabaseInfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AbstractDataBase extends SQLiteOpenHelper{
		
		
	public AbstractDataBase (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context,DatabaseInfo.DATABASE_NAME,null,DatabaseInfo.DATABASE_VERSION);				
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		
	}

	/** 
	 * metodo responsavel pela atualizacao das estruturas das tabelas
	 * */
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, 
			int newVersion) {		
		
	}	
}
