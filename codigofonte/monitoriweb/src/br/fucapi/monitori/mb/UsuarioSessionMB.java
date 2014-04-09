package br.fucapi.monitori.mb;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fucapi.monitori.model.bean.Usuario;

@SuppressWarnings("serial")
@SessionScoped
@Named
public class UsuarioSessionMB implements Serializable {

	@Inject
	private Conversation conversa;

	private Usuario usuario;

	public static final String SESSION_HOME_LABEL = "paginaInicial";

	private Locale currentLocale = new Locale("pt", "BR");

	public String exibirHome() {
		if (!conversa.isTransient()) {
			conversa.end();
		}
		return "index?faces-redirect=true";
	}
	
	public String getLocale(){
		return currentLocale.getLanguage()+"-" + currentLocale.getCountry();
	}

	public boolean isLogado() {
		return usuario != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getLogin(){
		if (usuario == null){
			return "";
		}
		return getUsuario().getLogin();
	}
	
	public String getSenha(){
		if (usuario == null){
			return "";
		}
		return getUsuario().getSenha();
	}

	public void englishLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = Locale.US;
		viewRoot.setLocale(currentLocale);
	}

	public void portugueseLocale() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		currentLocale = new Locale("pt", "BR");
		viewRoot.setLocale(currentLocale);
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}

}
