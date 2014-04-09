package br.fucapi.monitori.utilitarios;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.fucapi.monitori.model.enums.ChaveMensagemEnum;

public abstract class MonitoriUtil {

	public static void setMessageView(FacesContext context,
			ChaveMensagemEnum titulo, ChaveMensagemEnum mensagem,
			Severity severity) {

		String valorTitulo = "", valorMensagem = "";
		if (titulo != null) {
			valorTitulo = getMessageBundle(context, titulo.getChave());
		}

		if (mensagem != null) {
			valorMensagem = getMessageBundle(context, mensagem.getChave());
		}

		FacesMessage msg = new FacesMessage(severity, valorTitulo,
				valorMensagem);
		context.addMessage(null, msg);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public static void setMessageView(FacesContext context,
			ChaveMensagemEnum titulo, ChaveMensagemEnum mensagem) {

		setMessageView(context, titulo, mensagem, FacesMessage.SEVERITY_INFO);
	}

	protected String getMessage(String key) {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		Locale locale = viewRoot.getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(
				"br.com.jsfcdi.treinamento.messages.Mensagens", locale);
		return bundle.getString(key);
	}

	public static String getMessageBundle(FacesContext facesContext,
			String chave) {
		ResourceBundle bundle = facesContext.getApplication()
				.getResourceBundle(facesContext, "msg");
		return bundle.getString(chave);
	}
	
	/**
	 * @param String input
	 * @return String output
	 */
	public static String toMd5(String input) {
		String output = input;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(input.getBytes(), 0, input.length());
			output = new BigInteger(1, md5.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return output;
	}

	
	

}
