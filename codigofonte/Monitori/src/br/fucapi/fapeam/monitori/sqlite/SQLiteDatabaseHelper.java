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
    public static final String TABLE_DIAGNOSTICAR_NAME = "diagnosticar";
    
	//Constantes para auxiliar o controle de versoes
	
	 // Common column names
    private static final String KEY_ID = "id";    
    
    public static interface FIELDS_TABLE_USUARIO {
        String id = KEY_ID;
        String nome = "nome";
        String cpf = "cpf";
        String endereco = "endereco";
        String numero = "numero";
        String cep = "cep";        
        String idBairro = "idBairro";
        String idUnidadeSaude = "idUnidadeSaude";
        String idMedico = "idMedico";
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
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.cpf+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.endereco+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.numero+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.cep+","+    		
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.idBairro+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.idUnidadeSaude+","+
    		TABLE_USUARIO_NAME +"."+FIELDS_TABLE_USUARIO.idMedico+","+
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
            + FIELDS_TABLE_USUARIO.nome+ "  TEXT, "+ FIELDS_TABLE_USUARIO.cpf+ " TEXT, " +FIELDS_TABLE_USUARIO.endereco+" TEXT, "+FIELDS_TABLE_USUARIO.numero+" TEXT, "+FIELDS_TABLE_USUARIO.idBairro+" INTEGER, "
            + FIELDS_TABLE_USUARIO.cep+ " TEXT, "+FIELDS_TABLE_USUARIO.idUnidadeSaude+" INTEGER, "+FIELDS_TABLE_USUARIO.idMedico+" INTEGER, "+FIELDS_TABLE_USUARIO.celular+" TEXT, "
			+ FIELDS_TABLE_USUARIO.telefone+" TEXT, "+FIELDS_TABLE_USUARIO.dataNascimento+" TEXT, "+FIELDS_TABLE_USUARIO.login+" TEXT, "
			+ FIELDS_TABLE_USUARIO.senha+" TEXT, "+FIELDS_TABLE_USUARIO.numeroSus+" TEXT, "
			+ FIELDS_TABLE_USUARIO.nomeMae+" TEXT, "+FIELDS_TABLE_USUARIO.foto+" TEXT, "+FIELDS_TABLE_USUARIO.hipertenso+" TEXT, "+FIELDS_TABLE_USUARIO.sexo+" TEXT, "
			+ FIELDS_TABLE_USUARIO.observacao+" TEXT, "+FIELDS_TABLE_USUARIO.diabetico1+" TEXT, "+FIELDS_TABLE_USUARIO.diabetico2+" TEXT, " 
			+ FIELDS_TABLE_USUARIO.crm+" TEXT, "+FIELDS_TABLE_USUARIO.matricula+" TEXT, "+FIELDS_TABLE_USUARIO.tipoUsuario+" TEXT)";
    
    private static final String CREATE_INDEX_TABLE_USUARIO = 
    		"CREATE UNIQUE INDEX index_"+FIELDS_TABLE_USUARIO.numeroSus+" on "+TABLE_USUARIO_NAME+" ("+FIELDS_TABLE_USUARIO.numeroSus+"); " +
    		"CREATE UNIQUE INDEX index_"+FIELDS_TABLE_USUARIO.cpf+" on "+TABLE_USUARIO_NAME+" ("+FIELDS_TABLE_USUARIO.cpf+"); " +
    		"CREATE UNIQUE INDEX index_"+FIELDS_TABLE_USUARIO.matricula+" on "+TABLE_USUARIO_NAME+" ("+FIELDS_TABLE_USUARIO.matricula+"); " +
    		"CREATE UNIQUE INDEX index_"+FIELDS_TABLE_USUARIO.crm+" on "+TABLE_USUARIO_NAME+" ("+FIELDS_TABLE_USUARIO.crm+"); ";     
    
    
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
    	String sistole = "sistole";
    	String glicose = "glicose";
    	String jejum = "jejum";
    	String pos_pandrial = "pos_pandrial";
    	String datacoleta = "dataColeta";
    	String horacoleta = "horaColeta";
    }
    
    public static String ALL_FIELDS_TABLE_COLETAR_DADOS = 
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.id+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.idPaciente+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.sis+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.sistole+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.glicose+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.jejum+","+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.pos_pandrial+","+    		
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.datacoleta+", "+
    		TABLE_COLETAR_DADOS_NAME +"."+FIELDS_TABLE_COLETAR_DADOS.horacoleta+" ";
    
   private static final String CREATE_TABLE_COLETAR_DADOS = "CREATE TABLE "
            + TABLE_COLETAR_DADOS_NAME + "(" + FIELDS_TABLE_COLETAR_DADOS.id + " INTEGER PRIMARY KEY," 
            + FIELDS_TABLE_COLETAR_DADOS.idPaciente+" INTEGER, "+ FIELDS_TABLE_COLETAR_DADOS.sis+ "  TEXT, "
            + FIELDS_TABLE_COLETAR_DADOS.sistole+" TEXT, "+ FIELDS_TABLE_COLETAR_DADOS.glicose+ " TEXT, "
            + FIELDS_TABLE_COLETAR_DADOS.jejum+" TEXT, "+ FIELDS_TABLE_COLETAR_DADOS.pos_pandrial+" TEXT, " 
            + FIELDS_TABLE_COLETAR_DADOS.datacoleta+" TEXT, "+ FIELDS_TABLE_COLETAR_DADOS.horacoleta+" TEXT )";			
    
    
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
	                
   public static interface FIELDS_TABLE_DIAGNOSTICAR {
	   String id = KEY_ID;
	   String idPaciente = "idPaciente";
	   String descrever = "descrever";
	   String datadiagnostico = "dataDiagnostico";
   	   String horadiagnostico = "horaDiagnostico";
   }
   
   public static String ALL_FIELDS_TABLE_DIAGNOSTICAR =
		   TABLE_DIAGNOSTICAR_NAME +"."+FIELDS_TABLE_DIAGNOSTICAR.id+","+
		   TABLE_DIAGNOSTICAR_NAME +"."+FIELDS_TABLE_DIAGNOSTICAR.idPaciente+","+
		   TABLE_DIAGNOSTICAR_NAME +"."+FIELDS_TABLE_DIAGNOSTICAR.descrever+", "+
		   TABLE_DIAGNOSTICAR_NAME +"."+FIELDS_TABLE_DIAGNOSTICAR.datadiagnostico+", "+
   		   TABLE_DIAGNOSTICAR_NAME +"."+FIELDS_TABLE_DIAGNOSTICAR.horadiagnostico+" ";
   
   private static final String CREATE_TABLE_DIAGNOSTICAR = "CREATE TABLE " + TABLE_DIAGNOSTICAR_NAME
		   + "(" + FIELDS_TABLE_DIAGNOSTICAR.id + " INTEGER PRIMARY KEY,"
		   + FIELDS_TABLE_DIAGNOSTICAR.idPaciente + " INTEGER, "
		   + FIELDS_TABLE_DIAGNOSTICAR.descrever+" TEXT, "
		   + FIELDS_TABLE_DIAGNOSTICAR.datadiagnostico+" TEXT, " 
		   + FIELDS_TABLE_DIAGNOSTICAR.horadiagnostico+" TEXT )";
    
	private static final String TAG = "SqlLiteDataBase" ;

	private static final String CREATE_ADMIN_USER = 
    		"INSERT INTO "+TABLE_USUARIO_NAME+
    		"("+FIELDS_TABLE_USUARIO.id+","+FIELDS_TABLE_USUARIO.nome+","+FIELDS_TABLE_USUARIO.tipoUsuario+","+FIELDS_TABLE_USUARIO.login+","+FIELDS_TABLE_USUARIO.senha+") " +
    		"VALUES(1,'admin','"+TipoUsuario.ADMINISTRADOR+"','123.456.789-00','123.456.789-00');";	
    
	
	private static final String CREATE_DUMP_BAIRROS = 
    		"INSERT INTO "+TABLE_BAIRRO_NAME+
    		"("+FIELDS_TABLE_BAIRRO.id+","+FIELDS_TABLE_BAIRRO.nome+") " +
    		"VALUES (1,'Centro'),(2,'Santa Luzia'),(3,'Planalto'),(4,'Adrianópolis')  ;";		
	
	private static final String CREATE_DUMP_UBS = 
    		"INSERT INTO "+TABLE_UBS_NAME+
    		"("+FIELDS_TABLE_UBS.nome+","+FIELDS_TABLE_UBS.endereco+","+FIELDS_TABLE_UBS.numero+","+FIELDS_TABLE_UBS.cep+","+FIELDS_TABLE_UBS.telefone+","+FIELDS_TABLE_UBS.idBairro+") " +
    		"VALUES ('Centro','xxx','123','69000-000','(92)12345678',1), " +
    		"('Santa Luzia','xxx','123','69000-000','(92)12345678',2);";
    		
	private static final String CREATE_DUMP_USUARIOS = 
    		"INSERT INTO "+TABLE_USUARIO_NAME+
    		"("+FIELDS_TABLE_USUARIO.id+","+FIELDS_TABLE_USUARIO.tipoUsuario+","+FIELDS_TABLE_USUARIO.nome+","+FIELDS_TABLE_USUARIO.cpf+","+FIELDS_TABLE_USUARIO.crm+", " +
    		""+FIELDS_TABLE_USUARIO.dataNascimento+","+FIELDS_TABLE_USUARIO.telefone+","+FIELDS_TABLE_USUARIO.celular+","+FIELDS_TABLE_USUARIO.endereco+"," +FIELDS_TABLE_USUARIO.cep+","+
    		""+FIELDS_TABLE_USUARIO.numero+","+FIELDS_TABLE_USUARIO.idBairro+","+FIELDS_TABLE_USUARIO.sexo+","+FIELDS_TABLE_USUARIO.idUnidadeSaude+"," +
    		""+FIELDS_TABLE_USUARIO.login+","+FIELDS_TABLE_USUARIO.senha+", " +
    		""+FIELDS_TABLE_USUARIO.hipertenso+","+FIELDS_TABLE_USUARIO.diabetico1+", " +
    		""+FIELDS_TABLE_USUARIO.diabetico2+","+FIELDS_TABLE_USUARIO.nomeMae+", " +
    		""+FIELDS_TABLE_USUARIO.numeroSus+","+FIELDS_TABLE_USUARIO.idMedico+","+FIELDS_TABLE_USUARIO.matricula+") " +
    		"VALUES  (2,'"+TipoUsuario.MEDICO+"','Medico 1','222.222.222-22','222','1976-02-02','(92)12345678','(92)12345678','xxx','123','69000-000',4,'FEMENINO',1,'222.222.222-22','222.222.222-22',null,null,null,null,null,null,null)," +
    				"(3,'"+TipoUsuario.MEDICO+"','Medico 2','333.333.333-33','333','1968-03-03','(92)87654321','(92)99990000','xxx','123','69000-000',3,'MASCULINO',2,'333.333.333-33','333.333.333-33',null,null,null,null,null,null,null)," +
    				"(4,'"+TipoUsuario.PACIENTE+"','Paciente 1','111.111.111-11',null,'1987-01-01','(92)12345678','(92)999999','xxx','123','69000-000',1,'MASCULINO',1,'111.111.111-11','111.111.111-11','true','true','false','mae',111,2,null)," +
    				"(5,'"+TipoUsuario.PACIENTE+"','Paciente 2','444.444.444-44',null,'1984-04-04','(92)44444444','(92)449999','xxx','123','69000-000',3,'MASCULINO',2,'444.444.444-44','444.444.444-44','true','true','false','mae',444,3,null)," +
    				"(6,'"+TipoUsuario.AGENTE+"','Agente 1','555.555.555-55',null,'1975-05-05','(92)555','(92)559999','xxx','123','69000-000',4,'MASCULINO',1,'555.555.555-55','555.555.555-55',null,null,null,null,null,null,555)," +
    				"(7,'"+TipoUsuario.AGENTE+"','Agente 2','666.666.666-66',null,'1976-06-06','(92)555','(92)559999','xxx','123','69000-000',3,'FEMENINO',2,'666.666.666-66','666.666.666-66',null,null,null,null,null,null,666);" ;
				
	
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
		database.execSQL(CREATE_TABLE_DIAGNOSTICAR);
		
		database.execSQL(CREATE_DUMP_BAIRROS);
		database.execSQL(CREATE_DUMP_UBS);
		database.execSQL(CREATE_DUMP_USUARIOS);
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
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAGNOSTICAR_NAME);
		
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
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAGNOSTICAR_NAME);
				
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
	}
}