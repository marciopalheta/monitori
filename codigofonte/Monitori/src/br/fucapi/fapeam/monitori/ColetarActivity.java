package br.fucapi.fapeam.monitori;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ColetarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coletar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coletar, menu);
		return true;
	}

}
