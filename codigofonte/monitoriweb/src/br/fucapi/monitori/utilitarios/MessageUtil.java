package br.fucapi.monitori.utilitarios;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import br.fucapi.monitori.mb.UsuarioSessionMB;

public final class MessageUtil {

	@Inject
	private static UsuarioSessionMB usuarioSession;
	public static ResourceBundle resourceBundle = null;

	public static void init() {
		Locale locale = usuarioSession.getCurrentLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}
		resourceBundle = ResourceBundle.getBundle(
				"br.com.ecodetech.cartorio.messages.Mensagens", locale);
	}

	/**
	 * recupera texto de acordo com linguagem utilizada.
	 * 
	 * @param key
	 */
	public static String getMessage(String key) {
		if (resourceBundle == null) {
			init();
		}
		return resourceBundle.getString(key);
	}
}