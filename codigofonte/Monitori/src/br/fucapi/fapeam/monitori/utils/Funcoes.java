package br.fucapi.fapeam.monitori.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
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
		   // em qualquer outra condi��o � gerado um erro
		   edTexto.setError(mensagem);
		   edTexto.setFocusable(true);
		   edTexto.requestFocus();
		   return false;
		  }
		  return false;
		 }
	
	
}
