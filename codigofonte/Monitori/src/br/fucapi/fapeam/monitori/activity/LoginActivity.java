package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.R.layout;
import br.fucapi.fapeam.monitori.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
