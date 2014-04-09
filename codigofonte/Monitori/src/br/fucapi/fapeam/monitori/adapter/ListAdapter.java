package br.fucapi.fapeam.monitori.adapter;

import java.util.List;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.BitmapManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Usuario> {
	 private List<Usuario> usuarios;	
	 private final LayoutInflater inflator;
	 
	 
	 private static class ViewHolder {
	  public ImageView iconView;
	  public TextView nameTextView;	
	  public ProgressBar progressBar;
	 }
	 
	 public ListAdapter(Context context, int textViewResourceId,
	   List<Usuario> usuarios) {
	  super(context, textViewResourceId, usuarios);
	  this.usuarios = usuarios;	  
	  inflator = (LayoutInflater) context
	    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
	  BitmapManager.INSTANCE.setPlaceholder(BitmapFactory.decodeResource(
	    context.getResources(), R.drawable.ic_no_image));
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	 
	  if (convertView == null) {
	   convertView = inflator.inflate(R.layout.row_list_layout, null);
	 
	   TextView nameTextView = (TextView) convertView
	     .findViewById(R.id.label);
	   
	   ImageView iconView = (ImageView) convertView
	     .findViewById(R.id.image);
	   
	   ProgressBar progressBar = (ProgressBar) convertView
		 .findViewById(R.id.progressBar1);
	   
	   iconView.setVisibility(View.GONE);
       	     	   
	   holder = new ViewHolder();
	   holder.nameTextView = nameTextView;	   
	   holder.iconView = iconView;
	   holder.progressBar = progressBar;
	   
	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	 
	  Usuario usuario = usuarios.get(position);
	  holder.nameTextView.setText(usuario.getNome());
	  	 
	  //holder.iconView.setTag(URLS[position]);
	  //BitmapManager.INSTANCE.loadBitmap(URLS[position], holder.iconView,holder.progressBar, 32,32);
	  	  	  	 
	  holder.iconView.setTag(usuario.getFoto());
	  /*
	  if(url!=null){
		  BitmapManager.INSTANCE.loadBitmap(usuario,url, holder.iconView,holder.progressBar, 32,
				    32);  
	  }else{
		  BitmapManager.INSTANCE.loadBitmap(usuario.getFoto(), holder.iconView,holder.progressBar, 32,
				    32);
	  }*/
	  
	  BitmapManager.INSTANCE.loadBitmap(usuario.getFoto(), holder.iconView,holder.progressBar, 100,
			    100);
	  
	 
	  return convertView;
	 }
	}