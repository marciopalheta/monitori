package br.fucapi.monitori.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fucapi.monitori.interceptor.Transactional;
import br.fucapi.monitori.model.bean.Usuario;
import br.fucapi.monitori.model.dao.UsuarioDAO;
import br.fucapi.monitori.model.enums.ChaveMensagemEnum;
import br.fucapi.monitori.utilitarios.MonitoriUtil;

@SuppressWarnings("serial")
@ConversationScoped
@Named
public class UsuarioMB extends GenericMB<Usuario, UsuarioDAO> {

	private String senhaAtual;
	private String novaSenha;
	private boolean alterarPerfil;
	@Inject
	private UsuarioSessionMB usuarioSession;

	@Override
	@PostConstruct
	public void carregarLista() {
		setPaginaDados("usuariodados");
		setPaginaListagem("usuario");
		carregarListaPaginada();
	}
	
	public String exibirAlterarPerfil(){
		setEntidade(usuarioSession.getUsuario());
		alterarPerfil = true;
		return getPaginaDados();
	}

	@Transactional
	public String salvar() {
		getEntidade().limparCPF();
		if (getEntidade().isAlterarSenha()) {
			getEntidade().recriarSenha();
		}
		
		//Validar login unico
		if(!getDao().isLoginDisponivel(getEntidade())){
			MonitoriUtil.setMessageView(FacesContext.getCurrentInstance(),
					ChaveMensagemEnum.ALERTA_ERRO_LOGIN_INVALIDO, null,
					FacesMessage.SEVERITY_ERROR);
			return getPaginaDados();
		}
		
		return super.salvar();
	}
	
	public String alterarSenhaCancelar(){
		cancelar();
		return "index?faces-redirect=true"; 
	}

	@Transactional
	public String alterarSenha() {
		if (!senhaAtual.equals(usuarioSession.getUsuario().getSenha())) {
			MonitoriUtil.setMessageView(FacesContext.getCurrentInstance(),
					ChaveMensagemEnum.ALERTA_ERRO_SENHA_ATUAL, null,
					FacesMessage.SEVERITY_ERROR);
			return "usuariosenha";
		}
		if (novaSenha.equals(usuarioSession.getUsuario().getSenha())) {
			MonitoriUtil.setMessageView(FacesContext.getCurrentInstance(),
					ChaveMensagemEnum.ALERTA_ERRO_SENHA_REPETIDA, null,
					FacesMessage.SEVERITY_ERROR);
			return "usuariosenha";
		}

		usuarioSession.getUsuario().setSenha(novaSenha);
		usuarioSession.getUsuario().setAlterarSenha(false);
		setEntidade(usuarioSession.getUsuario());
		if (getEntidade().isAlterarSenha()) {
			getEntidade().recriarSenha();
		}

		super.salvar();
		
		return "index?faces-redirect=true"; 
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public boolean isAlterarPerfil() {
		return alterarPerfil;
	}

	public void setAlterarPerfil(boolean alterarPerfil) {
		this.alterarPerfil = alterarPerfil;
	}

}
