package br.com.javaparaweb.financeiro.lancamento;
import java.util.*;
import br.com.javaparaweb.financeiro.conta.Conta;
import br.com.javaparaweb.financeiro.usuario.Usuario;
import br.com.javaparaweb.financeiro.util.DAOFactory;
public class LancamentoRN {
	private LancamentoDAO	lancamentoDAO;
	public LancamentoRN() {
		this.lancamentoDAO = DAOFactory.criarLancamentoDAO();
	}
	public void salvar(Lancamento lancamento) {
		this.lancamentoDAO.salvar(lancamento);
	}
	public void excluir(Lancamento lancamento) {
		this.lancamentoDAO.excluir(lancamento);
	}
	public Lancamento carregar(Integer lancamento) {
		return this.lancamentoDAO.carregar(lancamento);
	}
	public float saldo(Conta conta, Date data) { 
		float saldoInicial = conta.getSaldoInicial();
		float saldoNaData = this.lancamentoDAO.saldo(conta, data);
		return saldoInicial + saldoNaData;
	}
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) { 
		return this.lancamentoDAO.listar(conta, dataInicio, dataFim);
	}
}
