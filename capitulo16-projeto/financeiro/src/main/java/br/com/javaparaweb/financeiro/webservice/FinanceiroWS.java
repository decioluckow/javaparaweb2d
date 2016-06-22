package br.com.javaparaweb.financeiro.webservice;

import java.util.*;
import javax.jws.*;
import br.com.javaparaweb.financeiro.conta.*;
import br.com.javaparaweb.financeiro.lancamento.*;

@WebService
public class FinanceiroWS {

	@WebMethod
	public float saldo(@WebParam(name="conta") int conta, @WebParam(name="dataSaldo") Date data) { 
		LancamentoRN lancamentoRN = new LancamentoRN();
		ContaRN contaRN = new ContaRN();

		Conta contaPesquisada = contaRN.carregar(new Integer(conta));
		Float saldo = lancamentoRN.saldo(contaPesquisada, data);
		return saldo.floatValue();
	}

	@WebMethod
	public List<LancamentoItem> extrato(@WebParam(name="conta") int conta, @WebParam(name="de")
		Date de, @WebParam(name="ate") Date ate) { 
		LancamentoRN lancamentoRN = new LancamentoRN();
		ContaRN contaRN = new ContaRN();
		List<LancamentoItem> retorno = new ArrayList<LancamentoItem>();
		LancamentoItem lancamentoItem = null;

		Conta contaPesquisada = contaRN.carregar(new Integer(conta));
		List<Lancamento> listaLancamentos = lancamentoRN.listar(contaPesquisada, de, ate);
		for (Lancamento lancamento : listaLancamentos) {
			lancamentoItem = new LancamentoItem();
			lancamentoItem.setData(lancamento.getData());
			lancamentoItem.setDescricao(lancamento.getDescricao());
			lancamentoItem.setValor(lancamento.getValor().floatValue());
			retorno.add(lancamentoItem);
		}
		return retorno;
	}
}
