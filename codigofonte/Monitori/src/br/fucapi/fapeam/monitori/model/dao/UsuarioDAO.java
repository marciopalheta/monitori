package br.fucapi.fapeam.monitori.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.fucapi.fapeam.monitori.activity.PacienteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.AbstractEntityBean;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
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

public class UsuarioDAO extends AbstractDataBase{
		
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_USUARIO";
	
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	
	private static final String FIELDS_TABLE_USUARIO = ""+AbstractDataBase.TABLE_USUARIO+".id as idUsuario,"+
					""+AbstractDataBase.TABLE_USUARIO+".nome as nomeUsuario,"+		
					""+AbstractDataBase.TABLE_USUARIO+".dataMascimento,"+			
					""+AbstractDataBase.TABLE_USUARIO+".endereco,"+
					""+AbstractDataBase.TABLE_USUARIO+".cep,"+
					""+AbstractDataBase.TABLE_USUARIO+".celular,"+
					""+AbstractDataBase.TABLE_USUARIO+".telefone,"+
					""+AbstractDataBase.TABLE_USUARIO+".login,"+
					""+AbstractDataBase.TABLE_USUARIO+".senha,"+
					""+AbstractDataBase.TABLE_USUARIO+".sexo,"+
					""+AbstractDataBase.TABLE_USUARIO+".nomeMae,"+
					""+AbstractDataBase.TABLE_USUARIO+".numSus,"+
					""+AbstractDataBase.TABLE_USUARIO+".idBairro as bairroId,"+		
					""+AbstractDataBase.TABLE_USUARIO+".tipoUsuario,"+
					""+AbstractDataBase.TABLE_USUARIO+".hipertenso,"+
					""+AbstractDataBase.TABLE_USUARIO+".diabetico1,"+
					""+AbstractDataBase.TABLE_USUARIO+".diabetico2,"+			
					""+AbstractDataBase.TABLE_USUARIO+".crm,"+		
					""+AbstractDataBase.TABLE_USUARIO+".matricula ";
	
	private static final String FIELDS_TABLE_BAIRRO = ""+AbstractDataBase.TABLE_BAIRRO+".id as idBairro,"+
			""+AbstractDataBase.TABLE_BAIRRO+".nome as nomeBairro ";

	
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private Context context;
	public UsuarioDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
		this.context = context;
	}
	
	
	 /** 
	 * metodo responsavel pelo cadastro do usuario
	 * */
	
	public void cadastrar (Usuario usuario){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();
		//Definicao dos valores dos campos
		values.put("nome", usuario.getNome());
		
		String dataForDB=null;	
		Calendar cal =  usuario.getDataNascimento();
		if(cal!=null){
			dataForDB = dateFormat.format(usuario.getDataNascimento().getTime());
		}
		values.put("dataMascimento", dataForDB);
				
		values.put("endereco", usuario.getEndereco());
		values.put("cep", usuario.getCep());
		values.put("celular", usuario.getCelular());
		values.put("telefone", usuario.getTelefone());
		values.put("login", usuario.getLogin());
		values.put("senha", usuario.getNome());
		values.put("sexo", usuario.getSexo() );
		values.put("nomeMae", usuario.getNomeMae());
		values.put("numSus", usuario.getNumSus());
		
		values.put("idBairro", usuario.getBairro().getId());		
		
		values.put("tipoUsuario", usuario.getTipoUsuario().toString() );
		
		if(usuario instanceof Paciente){			
			values.put("hipertenso", String.valueOf( ((Paciente)usuario).isHipertenso() ) );
			values.put("diabetico1", String.valueOf( ((Paciente)usuario).isDiabetico1() ) );
			values.put("diabetico2", String.valueOf( ((Paciente)usuario).isDiabetico2() ) );
			
			
		}
		
		if(usuario instanceof Medico){			
			values.put("crm", ((Medico)usuario).getCrm() );	
		}
		
		if(usuario instanceof Agente){			
			values.put("matricula", ((Agente)usuario).getMatricula() );	
		}
		
		//Inserir dados do usuario
		getWritableDatabase().insert(AbstractDataBase.TABLE_USUARIO, null, values);
		Log.i(TAG, "Usuario Cadastrado: "+ usuario.getNome() );
		Log.i(TAG, "dataMascimento: "+ dataForDB );		
		
		Log.i(TAG, "Login: "+ usuario.getLogin() );
		Log.i(TAG, "Senha: "+ usuario.getSenha() );
		Log.i(TAG, "endereco: "+ usuario.getEndereco() );
		Log.i(TAG, "cep: "+ usuario.getCep() );
		Log.i(TAG, "idBairro: "+ usuario.getBairro().getId() );
				
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
	
	/** 
	 * metodo responsavel pela listagem dos usuarios na tela
	 * */
	public List<Usuario> listar(TipoUsuario tipoUsuario){
		//Colecao de usuarios
		List<Usuario> lista = new ArrayList<Usuario>();
		TipoUsuario tipo;
		//Definicao da instrucao SQL
		String sql = null;
						
			sql = "Select " +
					FIELDS_TABLE_USUARIO + ","+
					FIELDS_TABLE_BAIRRO +
					" from "+AbstractDataBase.TABLE_USUARIO +", " +
					" "+AbstractDataBase.TABLE_BAIRRO +" " +
					//"on  bairroId = idBairro " +
					"where "+AbstractDataBase.TABLE_USUARIO+".idBairro = "+AbstractDataBase.TABLE_BAIRRO+".id and tipoUsuario = '"+tipoUsuario+"' order by "+AbstractDataBase.TABLE_USUARIO+".nome";		
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				Usuario usuario = null;
				
				if(tipoUsuario == TipoUsuario.PACIENTE){
					usuario = new Paciente();					
					((Paciente)usuario).setHipertenso( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex("hipertenso"))  ) );
					((Paciente)usuario).setDiabetico1( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex("diabetico1"))  ) );
					((Paciente)usuario).setDiabetico2( Boolean.parseBoolean( cursor.getString(cursor.getColumnIndex("diabetico2"))  ) );
				}else if(tipoUsuario == TipoUsuario.AGENTE){
					usuario = new Agente();					
				}else if(tipoUsuario == TipoUsuario.MEDICO){
					usuario = new Medico();					
				}
																				 
				
				//Carregar os atributos dos usuarios
				usuario.setId( cursor.getLong(cursor.getColumnIndex("idUsuario") )); 
				usuario.setNome(cursor.getString(cursor.getColumnIndex("nomeUsuario")));
				
				usuario.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
				usuario.setNomeMae(cursor.getString(cursor.getColumnIndex("nomeMae")));
				usuario.setNumSus(cursor.getString(cursor.getColumnIndex("numSus")));
	
				/*
				BairroDAO bDao = new BairroDAO(context);				
				Bairro bairro = bDao.getBairroPorId( cursor.getLong(cursor.getColumnIndex("idBairro") ) );
				usuario.setBairro(bairro); //BAIRRO
				*/
				Bairro bairro = new Bairro();
				bairro.setId(cursor.getLong(cursor.getColumnIndex("idBairro") ));
				bairro.setNome(cursor.getString(cursor.getColumnIndex("nomeBairro")));				
				usuario.setBairro(bairro); //BAIRRO
				
				usuario.setCep(cursor.getString(cursor.getColumnIndex("cep"))); 
				//usuario.setUnidadeSaude(cursor.getString(5) );
				
				usuario.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
				String dtNascto=null;
				
				dtNascto = cursor.getString(cursor.getColumnIndex("dataMascimento"));								
					
					if(dtNascto !=null){
						Calendar cal = Calendar.getInstance();
						cal.setTime(dateFormat.parse(dtNascto));
						usuario.setDataNascimento(cal);
					}else{
						usuario.setDataNascimento(null);
					}
								
				usuario.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
				usuario.setSexo( cursor.getString(cursor.getColumnIndex("sexo")) );
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
		getWritableDatabase().delete(AbstractDataBase.TABLE_USUARIO, "id=?", args);
		Log.i(TAG, "Usuario Deletado: " +usuario.getNome());
	}
	
	/** 
	 * metodo responsavel pela atualizacao de usuarios
	 * */
	
	public void alterar(Usuario usuario) {
		ContentValues values = new ContentValues();
		values.put("nome", usuario.getNome());
		
		
		String dataForDB=null;	
		Calendar cal =  usuario.getDataNascimento();
		if(cal!=null){
			dataForDB = dateFormat.format(usuario.getDataNascimento().getTime());
		}
		values.put("dataMascimento", dataForDB);		
		
		values.put("endereco", usuario.getEndereco());
		values.put("cep", usuario.getCep());
		values.put("celular", usuario.getCelular());
		values.put("telefone", usuario.getTelefone());
		values.put("nomeMae", usuario.getNomeMae());
		values.put("numSus", usuario.getNumSus());
		//values.put("login", usuario.getLogin());
		//values.put("senha", usuario.getNome());
		values.put("sexo", usuario.getSexo() );
		values.put("idBairro", usuario.getBairro().getId());
		Log.i(TAG, "idBairro: "+ usuario.getBairro().getId() );
		
		values.put("tipoUsuario", usuario.getTipoUsuario().toString() );
		
		if(usuario instanceof Paciente){									
			values.put("hipertenso", String.valueOf( ((Paciente)usuario).isHipertenso() ) );
			values.put("diabetico1", String.valueOf( ((Paciente)usuario).isDiabetico1() ) );
			values.put("diabetico2", String.valueOf( ((Paciente)usuario).isDiabetico2() ) );

		}
		
		if(usuario instanceof Medico){			
			values.put("crm", ((Medico)usuario).getCrm() );	
		}
		
		if(usuario instanceof Agente){			
			values.put("matricula", ((Agente)usuario).getMatricula() );	
		}

		// Colecao de valores de parametros do SQL
		String[] args = { usuario.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(AbstractDataBase.TABLE_USUARIO, values, "id=?", args);
		Log.i(TAG, "Usuario alterado: " + usuario.getNome());
	}
	
	public Usuario getUsuario(String login, String senha){
		//Colecao de usuarios
		Usuario usuario = null;
		TipoUsuario tipo;
		//Definicao da instrucao SQL
		String sql = "Select * from Usuario where login='"+login+"' and senha='"+senha+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
				usuario = new Usuario();
				
				usuario.setId(cursor.getLong(cursor.getColumnIndex("id") ));							
				usuario.setNome(cursor.getString(cursor.getColumnIndex("nome") ));				
				
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
