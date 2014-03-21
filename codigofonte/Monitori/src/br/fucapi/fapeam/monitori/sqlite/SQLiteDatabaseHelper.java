package br.fucapi.fapeam.monitori.sqlite;


import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author
 *
 */
public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

	/**
	 * 
	 */	
	
    // Table Names
    public static final String TABLE_USUARIO_NAME = "Usuario";
    public static final String TABLE_UBS_NAME = "UnidadeSaude";
    public static final String TABLE_BAIRRO_NAME = "Bairro";
    public static final String TABLE_COLETAR_DADOS_NAME = "ColetarDados";
    
	//Constantes para auxiliar o controle de versoes
	
	 // Common column names
    private static final String KEY_ID = "id";    
    
    public static interface FIELDS_TABLE_USUARIO {
        String id = KEY_ID;
        String nome = "nome";
        String endereco = "endereco";
        String numero = "numero";
        String cep = "cep";        
        String idBairro = "idBairro";
        String idUnidadeSaude = "idUnidadeSaude";
        String celular = "celular";
        String telefone = "telefone";
        String dataNascimento = "dataNascimento";
        
        String login = "login";
        String nomeMae = "nomeMae";
        String numeroSus = "numSus";
        String senha = "senha";
        String foto = "foto";
        String hipertenso = "hipertenso";
        String sexo = "sexo";
        String observacao = "observacao";
        String diabetico1 = "diabetico1";
        String diabetico2 = "diabetico2";
        String crm = "crm";
        String matricula = "matricula";
        String tipoUsuario = "tipoUsuario";				        
    }
    
    public static String ALL_FIELDS_TABLE_USUARIO =
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.id+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.nome+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.endereco+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.numero+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.cep+","+    		
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.idBairro+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.idUnidadeSaude+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.celular+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.telefone+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.dataNascimento+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.login+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.senha+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.nomeMae+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.numeroSus+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.foto+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.hipertenso+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.sexo+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.observacao+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.diabetico1+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.diabetico2+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.crm+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.matricula+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.tipoUsuario+" ";    		                                                                                                           
        
    
    // Table Create Statements
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO_NAME + "(" + FIELDS_TABLE_USUARIO.id + " INTEGER PRIMARY KEY," 
            + FIELDS_TABLE_USUARIO.nome+ "  TEXT, "+FIELDS_TABLE_USUARIO.endereco+" TEXT, "+FIELDS_TABLE_USUARIO.numero+" TEXT, "+FIELDS_TABLE_USUARIO.idBairro+" INTEGER, "
			+ FIELDS_TABLE_USUARIO.cep+ " TEXT, "+FIELDS_TABLE_USUARIO.idUnidadeSaude+" INTEGER, "+FIELDS_TABLE_USUARIO.celular+" TEXT, "
			+ FIELDS_TABLE_USUARIO.telefone+" TEXT, "+FIELDS_TABLE_USUARIO.dataNascimento+" TEXT, "+FIELDS_TABLE_USUARIO.login+" TEXT, "
			+ FIELDS_TABLE_USUARIO.senha+" TEXT, "+FIELDS_TABLE_USUARIO.numeroSus+" TEXT, "
			+ FIELDS_TABLE_USUARIO.nomeMae+" TEXT, "+FIELDS_TABLE_USUARIO.foto+" TEXT, "+FIELDS_TABLE_USUARIO.hipertenso+" TEXT, "+FIELDS_TABLE_USUARIO.sexo+" TEXT, "
			+ FIELDS_TABLE_USUARIO.observacao+" TEXT, "+FIELDS_TABLE_USUARIO.diabetico1+" TEXT, "+FIELDS_TABLE_USUARIO.diabetico2+" TEXT, " 
			+ FIELDS_TABLE_USUARIO.crm+" TEXT, "+FIELDS_TABLE_USUARIO.matricula+" TEXT, "+FIELDS_TABLE_USUARIO.tipoUsuario+" TEXT)";
    
    private static final String CREATE_INDEX_TABLE_USUARIO = 
    		"CREATE UNIQUE INDEX index_"+FIELDS_TABLE_USUARIO.numeroSus+" on "+TABLE_USUARIO_NAME+" ("+FIELDS_TABLE_USUARIO.numeroSus+"); " +
    		"CREATE UNIQUE INDEX index_"+FIELDS_TABLE_USUARIO.matricula+" on "+TABLE_USUARIO_NAME+" ("+FIELDS_TABLE_USUARIO.matricula+"); " +
    		"CREATE UNIQUE INDEX index_"+FIELDS_TABLE_USUARIO.crm+" on "+TABLE_USUARIO_NAME+" ("+FIELDS_TABLE_USUARIO.crm+"); "; 
    private static final String CREATE_ADMIN_USER = 
    		"INSERT INTO "+TABLE_USUARIO_NAME+
    		"("+FIELDS_TABLE_USUARIO.nome+","+FIELDS_TABLE_USUARIO.tipoUsuario+","+FIELDS_TABLE_USUARIO.login+","+FIELDS_TABLE_USUARIO.senha+") " +
    		"VALUES('admin','"+TipoUsuario.ADMINISTRADOR+"','admin','admin');";	
    
    
    
    public static interface FIELDS_TABLE_UBS {
        String id = KEY_ID;
        String nome = "nome";
        String endereco = "endereco";
        String numero = "numero";
        String cep = "cep";        
        String idBairro = "idBairro";        
        String telefone = "telefone";        
        
    }
    public static String ALL_FIELDS_TABLE_UBS =
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_USUARIO.id+","+
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_USUARIO.nome+","+
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_USUARIO.endereco+","+
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_USUARIO.numero+","+
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_USUARIO.cep+","+    		
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_USUARIO.idBairro+","+    		    		
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_USUARIO.telefone+" ";
    		
    
    
    // UnidadeBasicaSaude table create statement
    private static final String CREATE_TABLE_UBS = "CREATE TABLE " + TABLE_UBS_NAME
            + "(" + FIELDS_TABLE_UBS.id + " INTEGER PRIMARY KEY, " 
            + FIELDS_TABLE_UBS.nome+" TEXT, "+FIELDS_TABLE_UBS.endereco+" TEXT, " 
            + FIELDS_TABLE_UBS.numero+" TEXT, "+ FIELDS_TABLE_UBS.cep+" TEXT, " 
            + FIELDS_TABLE_UBS.telefone+" TEXT, "+FIELDS_TABLE_UBS.idBairro+" INTEGER )";
 	
    public static interface FIELDS_TABLE_COLETAR_DADOS{
    	String id = KEY_ID;
    	String idPaciente = "idPaciente";
    	String sis = "sis";
    	String glicose = "glicose";
    	String jejum = "jejum";
    	String pos_pandrial = "pos_pandrial";
    	//String datacoleta = "dataColeta";
    	
    }
    
    public static String ALL_FIELDS_TABLE_COLETAR_DADOS = 
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.id+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.idPaciente+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.sis+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.glicose+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.jejum+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.pos_pandrial+" ";    		
    		//TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.datacoleta+",";
    
    private static final String CREATE_TABLE_COLETAR_DADOS = "CREATE TABLE " + TABLE_COLETAR_DADOS_NAME
    		+ "(" + FIELDS_TABLE_COLETAR_DADOS.id + " INTERGER PRIMARY KEY, "
    		+FIELDS_TABLE_COLETAR_DADOS.idPaciente+" INTEGER ," + FIELDS_TABLE_COLETAR_DADOS.sis+" TEXT, "
    		+FIELDS_TABLE_COLETAR_DADOS.glicose+" TEXT, "+ FIELDS_TABLE_COLETAR_DADOS.jejum+" TEXT, "
    		+FIELDS_TABLE_COLETAR_DADOS.pos_pandrial+" TEXT )";

    public static interface FIELDS_TABLE_BAIRRO {
        String id = KEY_ID;
        String nome = "nome";                        
    }
    public static String ALL_FIELDS_TABLE_BAIRRO =
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_BAIRRO.id+","+
    		TABLE_UBS_NAME +"."+FIELDS_TABLE_BAIRRO.nome+" ";    		
    
    
    private static final String CREATE_TABLE_BAIRRO = "CREATE TABLE " + TABLE_BAIRRO_NAME
            + "(" + FIELDS_TABLE_BAIRRO.id + " INTEGER PRIMARY KEY," 
            + FIELDS_TABLE_BAIRRO.nome+" TEXT )";
	                
        
	private static final String TAG = "SqlLiteDataBase" ;
	
	
	/**
	 * @param context androdi context
	 * @param name database name
	 * @param factory cursor factory
	 * @param version database version
	 */
	public SQLiteDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		//super(context,DatabaseInfo.DATABASE_NAME,null,DatabaseInfo.DATABASE_VERSION);
	}

	/** 
	 * {@inheritDoc}
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.i(TAG, "create database");
					
		database.execSQL(CREATE_TABLE_USUARIO);
		database.execSQL(CREATE_INDEX_TABLE_USUARIO);
		
		database.execSQL(CREATE_ADMIN_USER);		
		
		database.execSQL(CREATE_TABLE_UBS);
		database.execSQL(CREATE_TABLE_COLETAR_DADOS);
		database.execSQL(CREATE_TABLE_BAIRRO);
		
	}

	/**
	 * {@inheritDoc}
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.i(TAG, "upgrade database from {"+oldVersion+"} to {"+newVersion+"}");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_UBS_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_COLETAR_DADOS_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIRRO_NAME);
		
		
				
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
	}
	
	
	
	@Override
	public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub		
		Log.i(TAG, "Downgrade database from {"+oldVersion+"} to {"+newVersion+"}");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_UBS_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_COLETAR_DADOS_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_BAIRRO_NAME);
		
				
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
				
	}
}
