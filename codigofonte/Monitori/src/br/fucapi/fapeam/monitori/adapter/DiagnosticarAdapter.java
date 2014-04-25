package br.fucapi.fapeam.monitori.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.model.bean.Diagnosticar;

public class DiagnosticarAdapter extends BaseAdapter {
	
	private final  List<Diagnosticar> listaDiagnostico;
	private final Activity activity;
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfTime;
	
	public DiagnosticarAdapter(Activity activity, List<Diagnosticar> listaDiagnostico) {
		this.listaDiagnostico = listaDiagnostico;
		this.activity = activity;
	}
	
	public int getCount() {
		return listaDiagnostico.size();
	}

	public long getItemId(int posicao) {
		return listaDiagnostico.get(posicao).getId();
	}
	
	public Object getItem(int posicao) {
		return listaDiagnostico.get(posicao);
	}

	public View getView(int posicao, View convertView, ViewGroup parent) {
		//Infla o layout na view
		View view = activity.getLayoutInflater().inflate(R.layout.itemdiagnostico, null);
		
		Diagnosticar diagnostico = listaDiagnostico.get(posicao);
		
		//configuracao do nome
		TextView descrever = (TextView) view.findViewById(R.id.itemDescrever);
		descrever.setText(diagnostico.getDescrever());
	
		sdfDate = new SimpleDateFormat(activity.getString(R.string.DATE_FORMAT_APLICATION));
		sdfTime = new SimpleDateFormat(activity.getString(R.string.TIME_FORMAT_APLICATION));
		String dateFormated = sdfDate.format(diagnostico.getDataHoraDiagnostico().getTime());
		String timeFormated = sdfTime.format(diagnostico.getDataHoraDiagnostico().getTime());
		

		TextView data = (TextView) view.findViewById(R.id.itemData);

		TextView hora = (TextView) view.findViewById(R.id.itemHora);
		
		data.setText(dateFormated);
		hora.setText(timeFormated);
		
		return view;
	}
}
