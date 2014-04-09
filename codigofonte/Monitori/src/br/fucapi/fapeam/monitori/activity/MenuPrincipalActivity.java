package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.agente.AgenteActivity;
import br.fucapi.fapeam.monitori.activity.medico.MedicoActivity;
import br.fucapi.fapeam.monitori.activity.paciente.PacienteActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity implements OnClickListener {

	private Button btMedico;
	private Button btAgente;
	private Button btPaciente;
	private Button btcoletadados;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Layout do Dashboard
		setContentView(R.layout.menuprincipal);

		btMedico = (Button) findViewById(R.id.btMedico);
		btMedico.setOnClickListener(this);
		btAgente = (Button) findViewById(R.id.btAgente);
		btAgente.setOnClickListener(this);
		btPaciente = (Button) findViewById(R.id.btPaciente);
		btPaciente.setOnClickListener(this);
		btcoletadados = (Button) findViewById(R.id.btcoletadados);
		btcoletadados.setOnClickListener(this);
				
	}

	@Override
	public void onClick(View v) {
        	//Intent intent = new Intent(this, AgenteActivity.class);
			Intent intent = new Intent(this, AgenteActivity.class);
    		if(v == btAgente) {
    			startActivity(intent);
    		}
    		
    		Intent intent2 = new Intent(this, PacienteActivity.class);
    		if(v == btPaciente) {
    			startActivity(intent2);
    		}
    		
    		Intent intent3 = new Intent(this, MedicoActivity.class);
    		if(v == btMedico) {
    			startActivity(intent3);
    		} 
    		
    		Intent intent4 = new Intent(this, ColetarActivity.class);
    		if(v == btcoletadados){
    			startActivity(intent4);
    		}
	}
}