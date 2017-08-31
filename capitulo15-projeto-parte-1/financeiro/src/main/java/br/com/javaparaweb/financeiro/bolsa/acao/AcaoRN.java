package br.com.javaparaweb.financeiro.bolsa.acao;

import java.util.ArrayList;
import java.util.List;

import br.com.javaparaweb.financeiro.usuario.Usuario;
import br.com.javaparaweb.financeiro.util.DAOFactory;
import br.com.javaparaweb.financeiro.util.UtilException;
import br.com.javaparaweb.financeiro.web.util.YahooFinanceUtil;

public class AcaoRN {
	private AcaoDAO acaoDAO;

	public AcaoRN() {
		this.acaoDAO = DAOFactory.criarAcaoDAO();
	}

	public void salvar(Acao acao) {
		this.acaoDAO.salvar(acao);
	}

	public void excluir(Acao acao) {
		this.acaoDAO.excluir(acao);
	}

	public List<Acao> listar(Usuario usuario) {
		return this.acaoDAO.listar(usuario);
	}

	public List<AcaoVirtual> listarAcaoVirtual(Usuario usuario) throws UtilException {
		List<AcaoVirtual> listaAcaoVirtual = new ArrayList<AcaoVirtual>();
		AcaoVirtual acaoVirtual = null;
		String cotacao = null;
		float ultimoPreco = 0.0f;
		for (Acao acao : this.listar(usuario)) {
			acaoVirtual = new AcaoVirtual();
			acaoVirtual.setAcao(acao);
			cotacao = YahooFinanceUtil.getInfoCotacao(YahooFinanceUtil.INDICE_ULTIMO_PRECO_DIA_ACAO, acao);
			if (cotacao != null) {
				ultimoPreco = new Float(cotacao).floatValue();
				acaoVirtual.setUltimoPreco(ultimoPreco);
				acaoVirtual.setTotal(ultimoPreco * acao.getQuantidade());
				listaAcaoVirtual.add(acaoVirtual);
			}
		}
		return listaAcaoVirtual;
	}
}
