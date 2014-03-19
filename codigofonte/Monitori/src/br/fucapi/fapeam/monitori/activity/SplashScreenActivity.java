package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.init.AppInit;


import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;


public class SplashScreenActivity extends FragmentActivity {

	/**
	 * 
	 */
	protected MyStateSaver data;

	/**
	 * {@inheritDoc}
	 * @see roboguice.activity.RoboFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splashscreen);
		
		this.data = (MyStateSaver) getLastCustomNonConfigurationInstance();
		if (this.data == null) {
			this.data = new MyStateSaver();
		}
		if (this.data.doInit) {
			doInit();
		}
	}

	/**
	 * {@inheritDoc}
	 * @see android.support.v4.app.FragmentActivity#onRetainCustomNonConfigurationInstance()
	 */
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return this.data;
	}

	/**
	 * 
	 */
	protected void startNextActivity() {
		//Intent intent = new Intent(this, AppMainActivity.class);
		Intent intent = new Intent(this, LoginActivity.class);
		this.startActivity(intent);		
		this.finish();
	}

	/**
	 * 
	 */
	protected void doInit() {
		this.data.doInit = false;
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				try {
					AppInit.getInstance().init(getApplicationContext());
					startNextActivity();
				} catch( NameNotFoundException e ) {
					throw new RuntimeException(e);
				}
			}
		}, 2000);
	}

	
	private class MyStateSaver {
		public boolean doInit = true;
	}
}
