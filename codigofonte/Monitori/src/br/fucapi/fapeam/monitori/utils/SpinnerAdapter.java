package br.fucapi.fapeam.monitori.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.fucapi.fapeam.monitori.R;

public class SpinnerAdapter extends ArrayAdapter<String> {

	private Context context;
	private String []textArray = null;
	private Integer[]intArray = null;
	private OnClickListener clickListener = null;
	
	public OnClickListener getClickListener() {
		return clickListener;
	}
	public void setClickListener(OnClickListener clickListener) {
		this.clickListener = clickListener;
	}

	private int textViewResourceId;	
	public SpinnerAdapter(Context context, int textViewResourceId,
            String[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textArray = objects;
		this.textViewResourceId = textViewResourceId;
	}    
		public SpinnerAdapter(Context context, int textViewResourceId,
                        String[] objects, Integer[] intArray) {
                  super(context, textViewResourceId, objects);
                  this.context = context;
                  this.textArray = objects;
                  this.intArray = intArray;
                  this.textViewResourceId = textViewResourceId;
            }
        
			@Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=((Activity) context).getLayoutInflater();
            View row=inflater.inflate(textViewResourceId, parent, false);
            		                        
	            if(textViewResourceId == R.layout.spinner_sexo ){
	            	TextView label=(TextView)row.findViewById(R.id.textView1);
	            	label.setText(textArray[position]);            
	            	ImageView icon=(ImageView)row.findViewById(R.id.imageView1);
	            	icon.setImageResource(intArray[position]);
	            }else if(textViewResourceId == R.layout.spinner_generic){
	            	TextView id=(TextView)row.findViewById(R.id.textID);
	            	id.setText( String.valueOf( intArray[position] ) );            
	            	
	            	TextView label=(TextView)row.findViewById(R.id.textView1);
	            	label.setText(textArray[position]);
	            	
	            	if(intArray[position] == 0){
	            		label.setOnClickListener(clickListener);
	            	}   		            	
	            }	            
	            return row;
            }
   }