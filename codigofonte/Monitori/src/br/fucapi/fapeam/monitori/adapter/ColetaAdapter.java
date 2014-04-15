package br.fucapi.fapeam.monitori.adapter;


import java.text.SimpleDateFormat;
import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ColetaAdapter extends BaseAdapter{
	private final  List<ColetarDados> listaColeta;
	private final Activity activity;
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfTime;
	
	public ColetaAdapter(Activity activity, List<ColetarDados> listaColeta) {
		this.listaColeta = listaColeta;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return listaColeta.size();
	}

	@Override
	public long getItemId(int posicao) {
		return listaColeta.get(posicao).getId();
	}
	
	@Override
	public Object getItem(int posicao) {
		return listaColeta.get(posicao);
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		//Infla o layout na view
		View view = activity.getLayoutInflater().inflate(R.layout.itemcoleta, null);
		
		ColetarDados coleta = listaColeta.get(posicao);
		
		//configuracao do nome
		TextView sis = (TextView) view.findViewById(R.id.itemSis);
		sis.setText(coleta.getSis());
		
		TextView sistole = (TextView) view.findViewById(R.id.itemSistole);
		sistole.setText(coleta.getSistole());
		
		TextView glicose = (TextView) view.findViewById(R.id.itemGlicose);
		glicose.setText(coleta.getGlicose());
		
		sdfDate = new SimpleDateFormat(activity.getString(R.string.DATE_FORMAT_APLICATION));
		sdfTime = new SimpleDateFormat(activity.getString(R.string.TIME_FORMAT_APLICATION));
		String dateFormated = sdfDate.format(coleta.getDataHoraColeta().getTime());
		String timeFormated = sdfTime.format(coleta.getDataHoraColeta().getTime());
		

		TextView data = (TextView) view.findViewById(R.id.itemData);

		TextView hora = (TextView) view.findViewById(R.id.itemHora);
		
		data.setText(dateFormated);
		hora.setText(timeFormated);
	 	
		
		
		
		return view;
	}
}
