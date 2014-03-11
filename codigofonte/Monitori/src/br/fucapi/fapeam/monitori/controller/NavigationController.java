package br.fucapi.fapeam.monitori.controller;

import br.fucapi.fapeam.monitori.R;
//import br.fucapi.fapeam.monitori.activity2.MyPreferences;
import br.fucapi.fapeam.monitori.eula.Eula;
import br.fucapi.fapeam.monitori.utils.ConfirmDialog;
import br.fucapi.fapeam.monitori.utils.RequestCodes;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

public class NavigationController {

	public static NavigationController instance = new NavigationController();

	public static NavigationController getInstance() {
		return instance;
	}

	private NavigationController() {

	}

	public void startAppRating(Context context) {
		context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
				.parse("market://details?id=" + context.getPackageName())));
	}

	public void confirmEula(FragmentActivity activity) {
		Eula.show(activity, R.string.eula_title, R.string.eula_accept,
				R.string.eula_refuse);
	}

	public void showEula(FragmentActivity activity) {
		Eula.show(activity, R.string.eula_title, R.string.eula_close);
	}

	public void showExitDialog( final FragmentActivity activity) {
		ConfirmDialog newFragment = ConfirmDialog.newInstance(
				R.string.confirm_quit, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						activity.finish();
					}
				}, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		newFragment.show(activity.getSupportFragmentManager(), "dialog");
	}

	public void exitApplication() {

	}

	/*
	public void showSettings(FragmentActivity activity) {
		activity.startActivityForResult(new Intent(activity,
				MyPreferences.class), RequestCodes.PREFERENCES_RESULTCODE);
	}*/
}
