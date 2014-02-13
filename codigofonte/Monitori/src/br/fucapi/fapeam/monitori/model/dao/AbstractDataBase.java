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

public class AbstractDataBase extends SQLiteOpenHelper{
		
	//Constante para log no LogCat
    private static final String LOG = AbstractDataBase.class.getName();
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Monitori";
 
    // Table Names
    public static final String TABLE_USUARIO = "Usuario";
    public static final String TABLE_UBS = "UnidadeSaude";
    public static final String TABLE_BAIRRO = "Bairro";
	
	//Constantes para auxiliar o controle de versoes
	private static final int VERSAO = 9;
	private static final String TABELA = "Usuario";
	private static final String DATABASE = "Pacientes";
	
	 // Common column names
    private static final String KEY_ID = "id";
    
 
    // NOTE_TAGS Table - column names
    private static final String KEY_TODO_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";
 
    // Table Create Statements
    // Usuario table create statement
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
            + "nome TEXT, endereco TEXT, bairro TEXT, "
			+ "cep TEXT, unidadeSaude TEXT, celular TEXT, "
			+ "telefone TEXT, dataMascimento TEXT, login TEXT, "
			+ "nomeMae TEXT, numSus TEXT, "
			+ "senha TEXT, foto TEXT, hipertenso TEXT, sexo TEXT, "
			+ "observacao TEXT, diabetico1 TEXT, diabetico2 TEXT, crm TEXT, matricula TEXT, tipoUsuario TEXT)";
    
    // UnidadeBasicaSaude table create statement
    private static final String CREATE_TABLE_UBS = "CREATE TABLE " + TABLE_UBS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," 
            + "nome TEXT, endereco TEXT, numero TEXT, "				
			+ "cep TEXT, fone TEXT, bairro_id INTEGER )";
 	    		
		
	public AbstractDataBase (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context,DATABASE,null,VERSAO);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		//Definicao do comando DDL a ser executado		
		database.execSQL(CREATE_TABLE_USUARIO);
		database.execSQL(CREATE_TABLE_UBS);
		//database.execSQL(CREATE_TABLE_BAIRRO);
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
		//database.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIRRO);
		
		
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
	}
			
		
}
