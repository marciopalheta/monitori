package br.fucapi.fapeam.monitori.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AbstractDataBase extends SQLiteOpenHelper{
		
	// Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "Monitori";
 
    // Table Names
    public static final String TABLE_USUARIO = "Usuario";
    public static final String TABLE_UBS = "UnidadeSaude";
    public static final String TABLE_BAIRRO = "Bairro";
    
	//Constantes para auxiliar o controle de versoes
	
	 // Common column names
    private static final String KEY_ID = "id";    
 
    // Table Create Statements
    // Usuario table create statement
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
            + "nome TEXT, endereco TEXT, numero TEXT, idBairro INTEGER, "
			+ "cep TEXT, idUnidadeSaude INTEGER, celular TEXT, "
			+ "telefone TEXT, dataMascimento TEXT, login TEXT, "
			+ "nomeMae TEXT, numSus TEXT, "
			+ "senha TEXT, foto TEXT, hipertenso TEXT, sexo TEXT, "
			+ "observacao TEXT, diabetico1 TEXT, diabetico2 TEXT, crm TEXT, matricula TEXT, tipoUsuario TEXT)";
    
    // UnidadeBasicaSaude table create statement
    private static final String CREATE_TABLE_UBS = "CREATE TABLE " + TABLE_UBS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
            + "nome TEXT, endereco TEXT, numero TEXT, "				
			+ "cep TEXT, fone TEXT, bairro_id INTEGER )";
 	
    private static final String CREATE_TABLE_BAIRRO = "CREATE TABLE " + TABLE_BAIRRO
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
            + "nome TEXT )";
		
	public AbstractDataBase (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		//Definicao do comando DDL a ser executado		
		database.execSQL(CREATE_TABLE_USUARIO);
		database.execSQL(CREATE_TABLE_UBS);
		database.execSQL(CREATE_TABLE_BAIRRO);
	}

	/** 
	 * metodo responsavel pela atualizacao das estruturas das tabelas
	 * */
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, 
			int newVersion) {		
				
		//Executando o comando de destruicao		
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_UBS);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIRRO);
		
		
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
	}	
}
