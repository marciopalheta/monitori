package br.fucapi.fapeam.monitori.adapter;

import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Usuario;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaAdapter extends BaseAdapter{

	private final  List<Usuario> listaAgente;
	private final Activity activity;
	
	public ListaAdapter(Activity activity, List<Usuario> listaAgente) {
		this.listaAgente = listaAgente;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return listaAgente.size();
	}

	@Override
	public long getItemId(int posicao) {
		return listaAgente.get(posicao).getId();
	}
	
	@Override
	public Object getItem(int posicao) {
		return listaAgente.get(posicao);
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		//Infla o layout na view
		View view = activity.getLayoutInflater().inflate(R.layout.item, null);
		
		Usuario agente = listaAgente.get(posicao);
		
		//configuração do nome
		TextView nome = (TextView) view.findViewById(R.id.itemNome);
		nome.setText(agente.getNome());
		
		//configuracao da foto
		Bitmap bmp;
		if(agente.getFoto() != null){
			bmp = BitmapFactory.decodeFile(agente.getFoto());
		}else{
			bmp = BitmapFactory.decodeResource(activity.getResources(),
					R.drawable.ic_no_image);
		}
		bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);
		ImageView foto = (ImageView) view.findViewById(R.id.itemFoto);
		foto.setImageBitmap(bmp);
		return view;
	}
}
