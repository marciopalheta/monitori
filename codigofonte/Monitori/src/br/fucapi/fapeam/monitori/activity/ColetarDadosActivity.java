package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ColetarDadosActivity extends Activity {

	//atributos para manipulacao de telas
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coletardados);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coletar_dados, menu);
		return true;
	}

}
