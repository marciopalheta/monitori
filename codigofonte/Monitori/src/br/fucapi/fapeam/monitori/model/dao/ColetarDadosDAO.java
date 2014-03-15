package br.fucapi.fapeam.monitori.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.sqlite.SQLiteDatabaseHelper;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class ColetarDadosDAO extends AbstractDataBase{
	
	//constante para Log no LogCat
	private static final String TAG = "CADASTRO_COLETAR_DADOS";

	public ColetarDadosDAO(Context context) {
		
		//Chamando o construtor que sabe acessar o BD
				super(context);
	}
	
	public void cadastrar (ColetarDados coletaDados){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();
		//Definicao dos valores dos campos
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis, coletaDados.getSis());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose, coletaDados.getGlicose());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum, coletaDados.getJejum());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial, coletaDados.getPosPandrial());
	
		//inserindo os dados
		getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME, null, values);
		
		Log.i(TAG, "sis: "+ coletaDados.getSis());
		Log.i(TAG, "glicose: "+ coletaDados.getGlicose());
		Log.i(TAG, "jejum: "+ coletaDados.getJejum());
		Log.i(TAG, "pos_pandrial"+ coletaDados.getPosPandrial());
	}
	
	public List<ColetarDados> listar(){
		//colecao de coletagem de dados
		List<ColetarDados> lista = new ArrayList<ColetarDados>();
		ColetarDados coletaDados;
		
		//definicao da instrucao sql
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME +" ";
		
		//objeto que recebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		
		try{
			while (cursor.moveToNext()) {
				coletaDados = new ColetarDados();
				
				//carregar os atributos de coletar dados
				coletaDados.setId(cursor.getLong(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.id)));
				coletaDados.setSis(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis)));
				coletaDados.setPosPandrial(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial)));
				coletaDados.setJejum(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum)));
				coletaDados.setGlicose(cursor.getString(cursor.getColumnIndex
						(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose)));
		
				//adiciona na lista
				lista.add(coletaDados);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return lista;
	}
	
	/** 
	 * metodo responsavel pela exclusao
	 * */
	public void deletar(ColetarDados coletaDados){
		//Array de parametros
		String[] args = {coletaDados.getId().toString()};
		
		//exclusao
		getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME,
				"id=?", args);
		Log.i(TAG, "Dados deletados: "+ coletaDados.getSis());
	}
	
	/** 
	 * metodo responsavel pela atualizacao
	 * */
	public void alterar(ColetarDados coletaDados){
		ContentValues values = new ContentValues();
		
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.sis, 
				coletaDados.getSis());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.pos_pandrial,
				coletaDados.getPosPandrial());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.glicose,
				coletaDados.getGlicose());
		values.put(SQLiteDatabaseHelper.FIELDS_TABLE_COLETAR_DADOS.jejum,
				coletaDados.getJejum());
		
		Log.i(TAG, "sis: " +coletaDados.getSis());
		Log.i(TAG, "pos_pandiral: " +coletaDados.getPosPandrial());
		Log.i(TAG, "glicose: " +coletaDados.getGlicose());
		Log.i(TAG, "jejum: " +coletaDados.getJejum());
		
		// Colecao de valores de parametros do SQL
		String[] args = { coletaDados.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_COLETAR_DADOS_NAME, 
				values, "id=?", args);
		Log.i(TAG, "Coleta de Dados Alterado: " +coletaDados.getSis());
		
	}
}
