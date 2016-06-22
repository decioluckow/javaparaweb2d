package br.com.javaparaweb.financeiro.util;
import br.com.javaparaweb.financeiro.usuario.*;
public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO; 
	}
}
