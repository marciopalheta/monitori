package br.fucapi.fapeam.monitori.utils;

import java.util.GregorianCalendar;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

public class CustomDialog extends DialogFragment {
	private CharSequence creationTime;
	
	Activity activity;
	
	public CustomDialog() {
		creationTime = DateFormat.format("hh:mm:ss",
				new GregorianCalendar());
	}
	
	public void setAlgo(Activity activity){
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setMessage("Creation time: " + creationTime)
				.setPositiveButton("Close", null).create();
	}

	/**
	 * workaround for issue #17423
	 */
	@Override
	public void onDestroyView() {
		if (getDialog() != null && getRetainInstance())
			getDialog().setOnDismissListener(null);

		super.onDestroyView();
	}
}