package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity implements OnClickListener {
	private Button btmedico;
	private Button btAgente;
	private Button btpaciente;
	private Button btcoletadados;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Layout do Dashboard
		setContentView(R.layout.menuprincipal);
		btmedico = (Button) findViewById(R.id.btmedico);
		btmedico.setOnClickListener(this);
		btAgente = (Button) findViewById(R.id.btAgente);
		btAgente.setOnClickListener(this);
		btpaciente = (Button) findViewById(R.id.btpaciente);
		btpaciente.setOnClickListener(this);
		btcoletadados = (Button) findViewById(R.id.btcoletadados);
		btcoletadados.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		
      
        	Intent intent = new Intent(this, PacienteActivity.class);
    		if(v == btAgente) {
    			startActivity(intent);
    		}
      
	}
}
