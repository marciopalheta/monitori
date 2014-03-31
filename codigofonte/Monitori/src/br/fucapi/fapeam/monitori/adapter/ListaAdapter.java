package br.fucapi.fapeam.monitori.adapter;

import java.io.IOException;
import java.util.List;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.Funcoes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaAdapter extends BaseAdapter{

	private final  List<Usuario> listaUsuario;
	private final Activity activity;
	
	public ListaAdapter(Activity activity, List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return listaUsuario.size();
	}

	@Override
	public long getItemId(int posicao) {
		return listaUsuario.get(posicao).getId();
	}
	
	@Override
	public Object getItem(int posicao) {
		return listaUsuario.get(posicao);
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		//Infla o layout na view
		View view = activity.getLayoutInflater().inflate(R.layout.item, null);
		
		Usuario usuario = listaUsuario.get(posicao);
		
		//configuracao do nome
		TextView nome = (TextView) view.findViewById(R.id.itemNome);
		nome.setText(usuario.getNome());
		
		//configuracao da foto
		BitmapFactory.Options options = new BitmapFactory.Options();									
		
		// downsizing image as it throws OutOfMemory Exception for larger
		// images
		
		options.inSampleSize = 16;			
		Bitmap bitmapFoto;			
		
		Bitmap bitmapFotoScale;		
		//19200;
		
		int escala;		
		ImageView foto = (ImageView) view.findViewById(R.id.itemFoto);
		
		if(usuario.getFoto() != null){			
			bitmapFoto = BitmapFactory.decodeFile(usuario.getFoto(),
						options);			
			
			escala = bitmapFoto.getWidth() * bitmapFoto.getHeight();
			
			if(escala<800){				
				options.inSampleSize = 4;
				if(escala<=400){
					options.inSampleSize = 2;	
				}
				bitmapFoto = BitmapFactory.decodeFile(usuario.getFoto(),options);
			}
			//Log.i("ESCALA", "w*h = "+ ( escala ) );
														
			try {
				bitmapFoto = Funcoes.applyOrientation(bitmapFoto,Funcoes.resolveBitmapOrientation(usuario.getFoto()) );				
			} catch (IOException e) {
				Log.d("IMAGE_NOT_FOUND", e.getMessage() );				
			}
			
		}else{						
			bitmapFoto = BitmapFactory.decodeResource(activity.getResources(),
					R.drawable.ic_no_image);			
			
		}
		//bitmapFotoScale = bitmapFoto; //Bitmap.createScaledBitmap(bitmapFoto, 100, 100, true);
		bitmapFotoScale = Bitmap.createScaledBitmap(bitmapFoto, 100, 100, true);
		
		
		foto.setImageBitmap(bitmapFotoScale);
		
		return view;
	}
	
	
	
	
	
}
