package br.fucapi.monitori.mb;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.fucapi.monitori.model.bean.Usuario;
import br.fucapi.monitori.model.dao.UsuarioDAO;
import br.fucapi.monitori.model.enums.ChaveMensagemEnum;
import br.fucapi.monitori.utilitarios.MonitoriUtil;

@Named
@RequestScoped
public class LoginMB {

	@Inject
	private UsuarioSessionMB usuarioSession;

	@Inject
	private UsuarioDAO dao;

	@Inject
	private transient Logger logger;
	
	@Inject 
	private Conversation conversa;

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String efetuarLogin() {
		String pagina = "index?faces-redirect=true";
		String login = usuario.getLogin();
		usuario = dao.efetuarLogin(login, usuario.getSenha());

		if (usuario != null) {
			// Captura da pagina alvo
			logger.info("Efetuando login do usuario: " + usuario.getLogin());
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(false);
			pagina = (String) session .getAttribute(UsuarioSessionMB.SESSION_HOME_LABEL);
			if(pagina == null || pagina.startsWith("/login")){
				pagina = "index";
			}
			if (pagina != null) {
				pagina = pagina.replaceAll("/", "").replaceAll(".xhtml", "")
						+ "?faces-redirect=true";
			}
			session.setAttribute(UsuarioSessionMB.SESSION_HOME_LABEL, null);
			usuarioSession.setUsuario(usuario);
		} else {
			pagina = "login";
			usuarioSession.setUsuario(null);
			
			MonitoriUtil.setMessageView(FacesContext.getCurrentInstance(),
					ChaveMensagemEnum.ALERTA_OPERACAO_RESULTADO,
					ChaveMensagemEnum.ALERTA_FALHA_LOGIN,
					FacesMessage.SEVERITY_ERROR);

			logger.info("Falha de login: " + login);
		}
		return pagina;
	}
	
	public String efetuarLogout() {
		if(!conversa.isTransient()){
			conversa.end();
		}
		usuarioSession.setUsuario(null);
		return "index?faces-redirect=true";
	}
}
