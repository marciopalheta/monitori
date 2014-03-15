package br.fucapi.fapeam.monitori.utils;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.fucapi.fapeam.monitori.R;

public class SpinnerAdapter extends ArrayAdapter {

	private Context context;	
	private List<SpinnerObject> spinnerObjects;		

	private int textViewResourceId;	
	public SpinnerAdapter(Context context, int textViewResourceId,
            String[] objects) {
		super(context, textViewResourceId,objects);
		this.context = context;		
		this.textViewResourceId = textViewResourceId;
	}    		
	
	@Override
	public SpinnerObject getItem(int position) {
		return spinnerObjects.get(position);
	}
	
	public SpinnerAdapter(Context context, int textViewResourceId, String [] objects,
				List<SpinnerObject> spinnerObjects) {
			super(context, textViewResourceId,objects);
						
          this.context = context;          
          this.spinnerObjects = spinnerObjects;          
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
	            	if(spinnerObjects!=null){	            	
		            	TextView label=(TextView)row.findViewById(R.id.textView1);
		            	label.setText( spinnerObjects.get(position).getValue() );            
		            	ImageView icon=(ImageView)row.findViewById(R.id.imageView1);
		            	icon.setImageResource( spinnerObjects.get(position).getImageResourse() );
	            	}
	            	
	            }else if(textViewResourceId == R.layout.spinner_generic){
	            	if(spinnerObjects!=null){	            	
		            	TextView id=(TextView)row.findViewById(R.id.textID);
		            	id.setText( String.valueOf( spinnerObjects.get(position).getId() ) );            
		            	
		            	TextView label=(TextView)row.findViewById(R.id.textView1);
		            	label.setText( spinnerObjects.get(position).getValue() );
		            	
	            	}
	            }	            
	            return row;
            }
        
        
    	public List<SpinnerObject> getSpinnerObjects() {
    		return spinnerObjects;
    	}

    	public void setSpinnerObjects(List<SpinnerObject> spinnerObjects) {
    		this.spinnerObjects = spinnerObjects;
    	}
        
   }