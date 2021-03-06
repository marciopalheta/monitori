package br.fucapi.fapeam.monitori.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Diagnosticar;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class DiagnosticarDAO extends AbstractDataBase{
	
	//constante para o logCat
	private static final String TAG = "DIAGNOSTICAR";
	
	private Context context;
	
	private String DATE_FORMAT;	
	
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfTime;
	String dateFormated;
	String timeFormated;
	
	public DiagnosticarDAO(Context context){
		//chamando o construtor que sabe acessar o BD
		super(context);
		this.context = context;
		
		sdfDate = new SimpleDateFormat(context.getString(R.string.DATE_FORMAT_DATABASE));
		sdfTime = new SimpleDateFormat(context.getString(R.string.TIME_FORMAT_APLICATION));						
		
	}
	
	/**
	 * metodo responsavel pelo cadastro
	 */
	public void cadastrar(Diagnosticar diagnosticar){
			//objeto para armazenar os valores dos camopos
			ContentValues values = new ContentValues();	
			
			getWritableDatabase().beginTransaction();
			
			try{
				
				
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.descrever, diagnosticar.getDescrever());
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.idPaciente, diagnosticar.getUsuario().getId());
				
				dateFormated = sdfDate.format(diagnosticar.getDataHoraDiagnostico().getTime());
				timeFormated = sdfTime.format(diagnosticar.getDataHoraDiagnostico().getTime());
				
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.datadiagnostico, 
						dateFormated );
				values.put(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.horadiagnostico, 
						timeFormated );
				
				//Inserir dados do usuario
				getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_DIAGNOSTICAR_NAME, null, values);
				
				getWritableDatabase().setTransactionSuccessful();// marks a commit
										
				
			}catch(SQLException sqlE) {	
				Log.e(TAG, "SqlException: "+sqlE.getMessage() );	
			}finally{
				getWritableDatabase().endTransaction();
				
				Log.i(TAG, "Paciente: "+ diagnosticar.getUsuario().getNome());
				Log.i(TAG, "diagnostico: "+ diagnosticar.getDescrever());
				Log.i(TAG, "DataDiagnostico: "+ dateFormated );
				Log.i(TAG, "HoraDiagnostico: "+ timeFormated );	
			}
		}
		
		/** 
		 * metodo responsavel pela listagem dos usuarios na tela
		 * */
		public List<Diagnosticar> listar(){
			//Colecao de usuarios
			List<Diagnosticar> lista = new ArrayList<Diagnosticar>();
			Diagnosticar diagnosticar;
			//Definicao da instrucao SQL
			String sql = null;
							
				sql = "Select " +
						SQLiteDatabaseHelper.ALL_FIELDS_TABLE_DIAGNOSTICAR+ " "+					
						" from "+SQLiteDatabaseHelper.TABLE_DIAGNOSTICAR_NAME +" " +					
						"order by "+SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.descrever+" ";		
			
			//Objeto que reebe os registros do banco de dados
			Cursor cursor = getReadableDatabase().rawQuery(sql, null);
			try{
				while(cursor.moveToNext()){
					
					diagnosticar = new Diagnosticar();
																					 				
					//Carregar os atributos dos usuarios
					Log.i("Id Diagnostico ", "id "+ cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.id))  );
					Log.i("Id Paciente diagnostico ","id Paciente "+ cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.idPaciente))  );
					
					diagnosticar.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.id) )); 
					
					diagnosticar.setDescrever(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.descrever)));
									
					UsuarioDAO usuarioDao = new UsuarioDAO(context);				
					Usuario usuario = usuarioDao.getUsuario( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.idPaciente) )) ;
					diagnosticar.setUsuario(usuario); 	
					
					String dataDiagnostico = cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.datadiagnostico));								
					String horaDiagnostico = cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.horadiagnostico));
					
					String dataHora = dataDiagnostico + " " +horaDiagnostico;
					
					SimpleDateFormat sdfDateTime = new SimpleDateFormat( context.getString(R.string.DATE_FORMAT_DATABASE) +" " + context.getString(R.string.TIME_FORMAT_APLICATION) );
									
								
					Calendar cal = Calendar.getInstance();
					//cal.setTime(sdfDateTime.parse(dataHora));
					diagnosticar.setDataHoraDiagnostico(cal);
																								
					lista.add(diagnosticar);
				}
			}catch(SQLException e){
				Log.e(TAG, e.getMessage());
			}finally{
				cursor.close();
			}
			return lista;
		}

		public List<Diagnosticar> listar(Usuario paciente){
			//Colecao de usuarios
			List<Diagnosticar> lista = new ArrayList<Diagnosticar>();
			Diagnosticar diagnosticar;
			//Definicao da instrucao SQL
			String sql = null;
							
				sql = "Select " +
						SQLiteDatabaseHelper.ALL_FIELDS_TABLE_DIAGNOSTICAR+ " "+					
						" from "+SQLiteDatabaseHelper.TABLE_DIAGNOSTICAR_NAME +" " +					
						" where "+SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.idPaciente+"='"+paciente.getId()+"' " +
						" order by "+SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.descrever+" ";		
			
			//Objeto que reebe os registros do banco de dados
			Cursor cursor = getReadableDatabase().rawQuery(sql, null);
			try{
				while(cursor.moveToNext()){
					
					diagnosticar = new Diagnosticar();
																					 				
					//Carregar os atributos dos usuarios
					diagnosticar.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.id) )); 
					diagnosticar.setDescrever(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.descrever)));
					
					//diagnosticar.setDataHoraDiagnostico(dataHoraDiagnostico)''		
					
					String dataDiagnostico = cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.datadiagnostico));								
					String horaDiagnostico = cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.horadiagnostico));
				
					String dataHora = dataDiagnostico + " " +horaDiagnostico;
				
					SimpleDateFormat sdfDateTime = new SimpleDateFormat( context.getString(R.string.DATE_FORMAT_DATABASE) +" " + context.getString(R.string.TIME_FORMAT_APLICATION) );
															
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdfDateTime.parse(dataHora));
					diagnosticar.setDataHoraDiagnostico(cal);
					
					diagnosticar.setUsuario(paciente); 					
																				
					lista.add(diagnosticar);
				}
			}catch(SQLException e){
				Log.e(TAG, e.getMessage());
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}finally{
				cursor.close();
			}
			return lista;
		}
		
		/** 
		 * metodo responsavel pela exclusao de usuarios
		 * */
		public void deletar(Diagnosticar diagnosticar) {
			//Array de parametros
			String[] args = {diagnosticar.getId().toString()};
			
			//Exclusao do usuario
			getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_DIAGNOSTICAR_NAME, "id=?", args);
			Log.i(TAG, "Diagnostico Deletado: " +diagnosticar.getDescrever());
		}
		
		/** 
		 * metodo responsavel pela atualizacao de usuarios
		 * */
		public void alterar(Diagnosticar diagnosticar) {
			ContentValues values = new ContentValues();
							
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.descrever, diagnosticar.getDescrever());
			values.put(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.idPaciente, diagnosticar.getUsuario().getId());
			
			// Colecao de valores de parametros do SQL
			String[] args = { diagnosticar.getId().toString() };
			
			// Altera dados
			getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_DIAGNOSTICAR_NAME, values, "id=?", args);
			Log.i(TAG, "Diagnostico alterado: " + diagnosticar.getDescrever());
		}
				
		
		public Diagnosticar getDiagnosticar(long id){
			//Colecao de usuarios
			Diagnosticar diagnosticar = null;
			
			//Definicao da instrucao SQL
			String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_DIAGNOSTICAR_NAME+
					" where "+SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.id+"='"+id+"' ";
			
			//Objeto que reebe os registros do banco de dados
			Cursor cursor = getReadableDatabase().rawQuery(sql, null);
			try{
				if(cursor.moveToNext()){				
					
					diagnosticar = new Diagnosticar();
					
					diagnosticar.setId(cursor.getLong(cursor.getColumnIndex
							(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.id) ));							
					diagnosticar.setDescrever(cursor.getString(cursor.getColumnIndex
							(SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.descrever)));				
					
					UsuarioDAO usuarioDao = new UsuarioDAO(context);				
					Usuario usuario = usuarioDao.getUsuario( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.FIELDS_TABLE_DIAGNOSTICAR.idPaciente) )) ;
					diagnosticar.setUsuario(usuario);
					
				}
			}catch(SQLException e){
				Log.e(TAG, e.getMessage());
			}finally{
				cursor.close();
			}
			return diagnosticar;
		}
		
		
	}
