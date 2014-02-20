package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity implements OnClickListener {
	private Button btEsportivos;
	private Button btAgente;
	private Button btLuxo;
	private Button btSobre;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Layout do Dashboard
		setContentView(R.layout.menuprincipal);
		btEsportivos = (Button) findViewById(R.id.btEsportivos);
		btEsportivos.setOnClickListener(this);
		btAgente = (Button) findViewById(R.id.btAgente);
		btAgente.setOnClickListener(this);
		btLuxo = (Button) findViewById(R.id.btLuxo);
		btLuxo.setOnClickListener(this);
		btSobre = (Button) findViewById(R.id.btSobre);
		btSobre.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		
      
        	Intent intent = new Intent(this, PacienteDadosActivity.class);
    		if(v == btAgente) {
    			startActivity(intent);
    		}
      
	}
}
