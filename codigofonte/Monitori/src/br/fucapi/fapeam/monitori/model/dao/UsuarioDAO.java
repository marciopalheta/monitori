package br.fucapi.fapeam.monitori.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.Paciente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.widget.Toast;

public class UsuarioDAO extends AbstractDataBase{
		
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_USUARIO";
	
	private String DATE_FORMAT;
		
    private SimpleDateFormat dateFormat;
    private Context context;
    private String dataForDB=null;
	public UsuarioDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
		this.context = context;
		DATE_FORMAT = context.getString(R.string.DATE_FORMAT_DATABASE);
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
	}
	
	 /** 
	 * metodo responsavel pelo cadastro do usuario
	 * */
	public void cadastrar (Usuario usuario){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();	
		
		getWritableDatabase().beginTransaction();
		
		try{
			
			//Definicao dos valores dos campos
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nome, usuario.getNome());
			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.foto, usuario.getFoto());						
			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cpf, usuario.getCpf());
			
			dataForDB=null;	
			Calendar cal =  usuario.getDataNascimento();
			if(cal!=null){
				dataForDB = dateFormat.format(usuario.getDataNascimento().getTime());
			}
			values.put( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.dataNascimento, dataForDB);
					
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.endereco , usuario.getEndereco());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numero, usuario.getNumero());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cep, usuario.getCep());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.celular, usuario.getCelular());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.telefone, usuario.getTelefone());		
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.sexo, usuario.getSexo() );
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nomeMae, usuario.getNomeMae());		
			
			if(usuario.getBairro() != null){
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idBairro, usuario.getBairro().getId());
			}
			if(usuario.getUnidadeSaude() != null){
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idUnidadeSaude, usuario.getUnidadeSaude().getId());
			}
			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario, usuario.getTipoUsuario().toString() );
			
			if(usuario instanceof Paciente){									
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numeroSus, usuario.getNumSus());
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.login, usuario.getNumSus());
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.senha, usuario.getNumSus());
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.hipertenso, String.valueOf( ((Paciente)usuario).isHipertenso() ) );
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico1, String.valueOf( ((Paciente)usuario).isDiabetico1() ) );
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico2, String.valueOf( ((Paciente)usuario).isDiabetico2() ) );			
			}
			
			if(usuario instanceof Medico){			
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.crm, ((Medico)usuario).getCrm() );
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.login, ((Medico) usuario).getCrm());
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.senha, ((Medico) usuario).getCrm());
			}
			
			if(usuario instanceof Agente){			
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.matricula, ((Agente)usuario).getMatricula() );
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.login, ((Agente) usuario).getMatricula());
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.senha, ((Agente) usuario).getMatricula());
					
			}
			
			
			//Inserir dados do usuario
			getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_USUARIO_NAME, null, values);
			
			getWritableDatabase().setTransactionSuccessful();// marks a commit
									
			
		}catch(SQLiteConstraintException e) {
			if(usuario instanceof Paciente){										
				Toast.makeText(context, "Numero do Sus: "+usuario.getNumSus() +" ja existente", Toast.LENGTH_LONG).show();
				Log.e(TAG, "Numero do Sus: "+usuario.getNumSus() +" ja existente" );
			}				
			if(usuario instanceof Medico){										
				Toast.makeText(context, "Crm: "+((Medico)usuario).getCrm() +" ja existente", Toast.LENGTH_LONG).show();
			}
			if(usuario instanceof Agente){						
				Toast.makeText(context, "Matricula: "+((Agente) usuario).getMatricula() +" ja existente", Toast.LENGTH_LONG).show();
			}
			
			
		}catch(SQLException sqlE) {	
			Log.e(TAG, "SqlException: "+sqlE.getMessage() );	
		}finally{
			getWritableDatabase().endTransaction();
			
			
			Log.i(TAG, "Usuario Cadastrado: "+ usuario.getNome() );
			Log.i(TAG, "cpf: "+ usuario.getCpf() );
			Log.i(TAG, "dataMascimento: "+ dataForDB );		
			
			Log.i(TAG, "Login: "+ usuario.getLogin() );
			Log.i(TAG, "Senha: "+ usuario.getSenha() );
			Log.i(TAG, "endereco: "+ usuario.getEndereco() );
			Log.i(TAG, "numero: "+ usuario.getNumero());
			Log.i(TAG, "cep: "+ usuario.getCep() );

			if(usuario.getBairro() != null){
				Log.i(TAG, "idBairro: "+ usuario.getBairro().getId() );
			}
			if(usuario.getUnidadeSaude() != null){
				Log.i(TAG, "idUbs: "+ usuario.getUnidadeSaude().getId() );
			}
								
			Log.i(TAG, "celular: "+ usuario.getCelular() );
			Log.i(TAG, "telefone: "+ usuario.getTelefone() );
			Log.i(TAG, "sexo: "+ usuario.getSexo() );		
			Log.i(TAG, "tipoUsuario: "+ usuario.getTipoUsuario().toString() );
			
			if(usuario instanceof Paciente){						
				Log.i(TAG, "hipertenso: "+ ((Paciente)usuario).isHipertenso() );
				Log.i(TAG, "diabetico1: "+ ((Paciente)usuario).isDiabetico1() );
				Log.i(TAG, "diabetico2: "+ ((Paciente)usuario).isDiabetico2() );
			}				
			if(usuario instanceof Medico){						
				Log.i(TAG, "crm: "+ ((Medico)usuario).getCrm() );
			}
			if(usuario instanceof Agente){						
				Log.i(TAG, "matricula: "+ ((Agente)usuario).getMatricula() );
			}	
		}
	}
	
	/** 
	 * metodo responsavel pela listagem dos usuarios na tela
	 * */
	public List<Usuario> listar(TipoUsuario tipoUsuario){
		//Colecao de usuarios
		List<Usuario> lista = new ArrayList<Usuario>();
		//Definicao da instrucao SQL
		String sql = null;
						
			sql = "Select " +
					SQLiteDatabaseHelper.ALL_FIELDS_TABLE_USUARIO + " "+					
					" from "+SQLiteDatabaseHelper.TABLE_USUARIO_NAME +" " +					
					"where "+SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario+" = '"+tipoUsuario+"' order by "+SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nome +" ";		
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				Usuario usuario = null;
				
				if(tipoUsuario == TipoUsuario.PACIENTE){
					usuario = new Paciente();					
					((Paciente)usuario).setHipertenso( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.hipertenso ))  ) );
					((Paciente)usuario).setDiabetico1( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico1 ))  ) );
					((Paciente)usuario).setDiabetico2( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico2 ))  ) );
				}else if(tipoUsuario == TipoUsuario.AGENTE){
					usuario = new Agente();				
					((Agente)usuario).setMatricula( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.matricula ))   );
				}else if(tipoUsuario == TipoUsuario.MEDICO){
					usuario = new Medico();					
					((Medico)usuario).setCrm( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.crm ))   );
				}
				//Carregar os atributos dos usuarios
				usuario.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.id) )); 
								
				usuario.setNome(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nome)));
				
				usuario.setFoto(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.foto)));
				
				usuario.setCpf(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cpf)));
													
				usuario.setEndereco(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.endereco )));
				usuario.setNumero(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numero )));
				usuario.setNomeMae(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nomeMae )));
				usuario.setNumSus(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numeroSus )));
	
				BairroDAO bairroDao = new BairroDAO(context);				
				Bairro bairro = bairroDao.getBairro( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idBairro ) ) );
				usuario.setBairro(bairro); 
				
				UnidadeSaudeDAO ubsDao = new UnidadeSaudeDAO(context);				
				UnidadeSaude ubs = ubsDao.getUnidadeSaude( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idUnidadeSaude ) ) );
				usuario.setUnidadeSaude(ubs);
				
				
				usuario.setCep(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cep ))); 
				//usuario.setUnidadeSaude(cursor.getString(5) );
				
				usuario.setCelular(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.celular )));
				String dtNascto=null;
				
				dtNascto = cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.dataNascimento ));								
					
					if(dtNascto !=null){
						Calendar cal = Calendar.getInstance();
						cal.setTime(dateFormat.parse(dtNascto));
						usuario.setDataNascimento(cal);
					}else{
						usuario.setDataNascimento(null);
					}
								
				usuario.setTelefone(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.telefone )));
				usuario.setSexo( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.sexo )) );
				//usuario.setObservacao( cursor.getString(cursor.getColumnIndex("observacao")) );
										
				//Adiciona um novo usuario a lista
				lista.add(usuario);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return lista;
	}
	
	/** 
	 * metodo responsavel pela exclusao de usuarios
	 * */
	public void deletar(Usuario usuario) {
		//Array de parametros
		String[] args = {usuario.getId().toString()};
		
		//Exclusao do usuario
		getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_USUARIO_NAME, "id=?", args);
		Log.i(TAG, "Usuario Deletado: " +usuario.getNome());
	}
	
	/** 
	 * metodo responsavel pela atualizacao de usuarios
	 * */
	public void alterar(Usuario usuario) {
		ContentValues values = new ContentValues();
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nome , usuario.getNome());
		
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.foto, usuario.getFoto());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cpf, usuario.getCpf());
		
		Calendar cal =  usuario.getDataNascimento();
		if(cal!=null){
			dataForDB = dateFormat.format(usuario.getDataNascimento().getTime());
		}
		values.put( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.dataNascimento , dataForDB);		
		
		values.put( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.endereco , usuario.getEndereco());
		values.put( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numero , usuario.getNumero());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cep , usuario.getCep());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.celular , usuario.getCelular());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.telefone, usuario.getTelefone());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nomeMae, usuario.getNomeMae());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numeroSus, usuario.getNumSus());
		//values.put("login", usuario.getLogin());
		//values.put("senha", usuario.getNome());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.sexo , usuario.getSexo() );
		
		if(usuario.getBairro() != null){
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idBairro, usuario.getBairro().getId());
		}else{
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idBairro, "");
		}
		if(usuario.getUnidadeSaude() != null){
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idUnidadeSaude, usuario.getUnidadeSaude().getId());
		}else{
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idUnidadeSaude, "null");
		}
		
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario, usuario.getTipoUsuario().toString() );
		
		if(usuario instanceof Paciente){									
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.hipertenso, String.valueOf( ((Paciente)usuario).isHipertenso() ) );
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico1, String.valueOf( ((Paciente)usuario).isDiabetico1() ) );
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico2, String.valueOf( ((Paciente)usuario).isDiabetico2() ) );
		}
		
		if(usuario instanceof Medico){			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.crm, ((Medico)usuario).getCrm() );	
		}
		
		if(usuario instanceof Agente){			
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.matricula, ((Agente)usuario).getMatricula() );	
		}

		// Colecao de valores de parametros do SQL
		String[] args = { usuario.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_USUARIO_NAME, values, "id=?", args);
		Log.i(TAG, "Usuario alterado: " + usuario.getNome());
	}
	
	public Usuario getUsuario(String login, String senha){
		//Colecao de usuarios
		Usuario usuario = null;
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_USUARIO_NAME+" where "+SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.login+"='"+login+"' and "+SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.senha+"='"+senha+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario )).equals(TipoUsuario.ADMINISTRADOR.toString()) ){
					usuario = new Usuario();
					usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR );
					Log.i(TAG, "Tipo usuario = " + cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario.toString()) ));
				}else if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario )).equals(TipoUsuario.PACIENTE.toString()) ){
					usuario = new Paciente();					
					usuario.setTipoUsuario(TipoUsuario.PACIENTE );
					((Paciente)usuario).setHipertenso( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.hipertenso))  ) );
					((Paciente)usuario).setDiabetico1( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico1))  ) );
					((Paciente)usuario).setDiabetico2( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico2))  ) );
				}else if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario)).equals(TipoUsuario.AGENTE.toString()) ){
					usuario = new Agente();					
					usuario.setTipoUsuario(TipoUsuario.AGENTE );
					((Agente)usuario).setMatricula( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.matricula))   );
					
				}else if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario)).equals(TipoUsuario.MEDICO.toString()) ){
					usuario = new Medico();					
					usuario.setTipoUsuario(TipoUsuario.MEDICO );
					((Medico)usuario).setCrm( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.crm))   );
				}																 
				
				//Log.i(TAG, "Id usuario = " + cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.id) ));				
				
				//Carregar os atributos dos usuarios
				usuario.setId( cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.id) )); 
				usuario.setNome(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nome)));
				
				usuario.setFoto(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.foto)));
				
				usuario.setCpf(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cpf)));
				//Log.i(TAG, "cpf: "+ usuario.getCpf() );		
				usuario.setEndereco(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.endereco)));
				usuario.setNumero(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numero)));
				usuario.setNomeMae(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nomeMae)));
				usuario.setNumSus(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numeroSus)));
	
				
				BairroDAO bairroDao = new BairroDAO(context);				
				Bairro bairro = bairroDao.getBairro( cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idBairro) ) );
				usuario.setBairro(bairro); 
				
				UnidadeSaudeDAO ubsDao = new UnidadeSaudeDAO(context);				
				UnidadeSaude ubs = ubsDao.getUnidadeSaude( cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idUnidadeSaude) ) );
				usuario.setUnidadeSaude(ubs);
				
				usuario.setCep(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cep))); 
				//usuario.setUnidadeSaude(cursor.getString(5) );
				
				usuario.setCelular(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.celular)));
				String dtNascto=null;
				
				dtNascto = cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.dataNascimento));								
					
					if(dtNascto !=null){
						Calendar cal = Calendar.getInstance();
						try {
							cal.setTime(dateFormat.parse(dtNascto));
							usuario.setDataNascimento(cal);
						} catch (ParseException e) {
							usuario.setDataNascimento(null);	
							Log.e(TAG, e.getMessage());							
						}						
					}else{
						usuario.setDataNascimento(null);
					}
								
				usuario.setTelefone(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.telefone)));
				usuario.setSexo( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.sexo)) );
				//usuario.setObservacao( cursor.getString(cursor.getColumnIndex("observacao")) );
	
				//Adiciona um novo usuario a lista
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return usuario;
	}
	
	public Usuario getUsuario(long id){
		//Colecao de usuarios
		Usuario usuario = null;		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_USUARIO_NAME+" where "+SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.id+"='"+id+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario )).equals(TipoUsuario.ADMINISTRADOR.toString()) ){
					usuario = new Usuario();
					usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR );
					Log.i(TAG, "Tipo usuario = " + cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario.toString()) ));
				}else if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario )).equals(TipoUsuario.PACIENTE.toString()) ){
					usuario = new Paciente();					
					usuario.setTipoUsuario(TipoUsuario.PACIENTE );
					((Paciente)usuario).setHipertenso( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.hipertenso))  ) );
					((Paciente)usuario).setDiabetico1( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico1))  ) );
					((Paciente)usuario).setDiabetico2( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.diabetico2))  ) );
				}else if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario)).equals(TipoUsuario.AGENTE.toString()) ){
					usuario = new Agente();					
					usuario.setTipoUsuario(TipoUsuario.AGENTE );
					((Agente)usuario).setMatricula( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.matricula))   );
					
				}else if(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.tipoUsuario)).equals(TipoUsuario.MEDICO.toString()) ){
					usuario = new Medico();					
					usuario.setTipoUsuario(TipoUsuario.MEDICO );
					((Medico)usuario).setCrm( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.crm))   );
				}																 
				
				//Log.i(TAG, "Id usuario = " + cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.id) ));				
				
				//Carregar os atributos dos usuarios
				usuario.setId( cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.id) )); 
				usuario.setNome(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nome)));
				usuario.setCpf(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cpf)));
				
				usuario.setEndereco(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.endereco)));
				usuario.setNumero(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numero)));
				usuario.setNomeMae(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.nomeMae)));
				usuario.setNumSus(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.numeroSus)));
	
				
				BairroDAO bairroDao = new BairroDAO(context);				
				Bairro bairro = bairroDao.getBairro( cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idBairro) ) );
				usuario.setBairro(bairro); 
				
				UnidadeSaudeDAO ubsDao = new UnidadeSaudeDAO(context);				
				UnidadeSaude ubs = ubsDao.getUnidadeSaude( cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.idUnidadeSaude) ) );
				usuario.setUnidadeSaude(ubs);
				
				usuario.setCep(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.cep))); 
				//usuario.setUnidadeSaude(cursor.getString(5) );
				
				usuario.setCelular(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.celular)));
				String dtNascto=null;
				
				dtNascto = cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.dataNascimento));								
					
					if(dtNascto !=null){
						Calendar cal = Calendar.getInstance();
						try {
							cal.setTime(dateFormat.parse(dtNascto));
							usuario.setDataNascimento(cal);
						} catch (ParseException e) {
							usuario.setDataNascimento(null);	
							Log.e(TAG, e.getMessage());							
						}						
					}else{
						usuario.setDataNascimento(null);
					}
								
				usuario.setTelefone(cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.telefone)));
				usuario.setSexo( cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.FIELDS_TABLE_USUARIO.sexo)) );
				//usuario.setObservacao( cursor.getString(cursor.getColumnIndex("observacao")) );
	
				//Adiciona um novo usuario a lista
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return usuario;
	}
	
}
