package br.fucapi.monitori.model.dao;

import javax.persistence.Query;

import br.fucapi.monitori.model.bean.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	public Usuario efetuarLogin(String login, String senha) {
		String jpql = "select u from Usuario u where u.login=:pLogin and u.senha=:pSenha and u.ativo=true";
		Query query = getEm().createQuery(jpql);
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", senha);
		if (!query.getResultList().isEmpty()) {
			return (Usuario) query.getResultList().get(0);
		}
		return null;
	}

	public boolean isLoginDisponivel(Usuario usuario) {
		String jpql = "select count(u.id) from Usuario u where u.login = :login ";
		if (usuario.getId() != null) {
			jpql += " and u.id != :id";
		}
		Query query = getEm().createQuery(jpql);
		query.setParameter("login", usuario.getLogin());
		if (usuario.getId() != null) {
			query.setParameter("id", usuario.getId());
		}

		long count = (Long) query.getSingleResult();
		
		return count == 0;
	}
}