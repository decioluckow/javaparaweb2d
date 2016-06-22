package br.com.javaparaweb.financeiro.bolsa.acao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.javaparaweb.financeiro.usuario.Usuario;

public class AcaoDAOHibernate implements AcaoDAO {
	private Session	session;
	public void setSession(Session session) {
		this.session = session;
	}
	public void salvar(Acao acao) {
		this.session.saveOrUpdate(acao);
	}
	public void excluir(Acao acao) {
		this.session.delete(acao);
	}
	public List<Acao> listar(Usuario usuario) {
		Criteria criteria = this.session.createCriteria(Acao.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		return criteria.list();
	}
}
