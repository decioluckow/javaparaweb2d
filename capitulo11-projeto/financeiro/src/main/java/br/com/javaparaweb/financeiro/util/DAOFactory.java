package br.com.javaparaweb.financeiro.util;

import br.com.javaparaweb.financeiro.categoria.CategoriaDAO;
import br.com.javaparaweb.financeiro.categoria.CategoriaDAOHibernate;
import br.com.javaparaweb.financeiro.cheque.ChequeDAO;
import br.com.javaparaweb.financeiro.cheque.ChequeDAOHibernate;
import br.com.javaparaweb.financeiro.conta.ContaDAO;
import br.com.javaparaweb.financeiro.conta.ContaDAOHibernate;
import br.com.javaparaweb.financeiro.lancamento.LancamentoDAO;
import br.com.javaparaweb.financeiro.lancamento.LancamentoDAOHibernate;
import br.com.javaparaweb.financeiro.usuario.UsuarioDAO;
import br.com.javaparaweb.financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}

	public static ContaDAO criarContaDAO() {
		ContaDAOHibernate contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contaDAO;
	}

	public static CategoriaDAO criarCategoriaDAO() {
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}

	public static LancamentoDAO criarLancamentoDAO() {
		LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return lancamentoDAO;
	}

	public static ChequeDAO criarChequeDAO() {
		ChequeDAOHibernate chequeDAO = new ChequeDAOHibernate();
		chequeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return chequeDAO;
	}

}
