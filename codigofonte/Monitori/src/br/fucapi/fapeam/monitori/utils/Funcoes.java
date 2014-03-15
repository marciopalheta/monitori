package br.fucapi.fapeam.monitori.utils;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Funcoes {

	public static boolean validarDados(View view, String mensagem) {
		  if (view instanceof EditText) {
		   EditText edTexto = (EditText) view;
		   Editable texto = edTexto.getText();
		   if (texto != null) {
		    String strTexto = texto.toString();
		    if (!TextUtils.isEmpty(strTexto)) {
		     return true;
		    }
		   }
		   // em qualquer outra condição é gerado um erro
		   edTexto.setError(mensagem);
		   edTexto.setFocusable(true);
		   edTexto.requestFocus();
		   return false;
		  }
		  return false;
		 }
		
	public static void hideKeyboard(Activity activity) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	
	public static void hideKeyboard(View view) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	
	public static void showKeyboard(View view) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
	        inputMethodManager.showSoftInput( view , InputMethodManager.SHOW_IMPLICIT );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	
	public static void showKeyboard(Activity activity) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
	        inputMethodManager.showSoftInputFromInputMethod( activity.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_IMPLICIT );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	
	
}
