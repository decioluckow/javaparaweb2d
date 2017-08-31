package br.com.javaparaweb.financeiro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.StreamedContent;

import br.com.javaparaweb.financeiro.categoria.Categoria;
import br.com.javaparaweb.financeiro.cheque.Cheque;
import br.com.javaparaweb.financeiro.cheque.ChequeId;
import br.com.javaparaweb.financeiro.cheque.ChequeRN;
import br.com.javaparaweb.financeiro.conta.Conta;
import br.com.javaparaweb.financeiro.lancamento.Lancamento;
import br.com.javaparaweb.financeiro.lancamento.LancamentoRN;
import br.com.javaparaweb.financeiro.util.RNException;
import br.com.javaparaweb.financeiro.util.UtilException;
import br.com.javaparaweb.financeiro.web.util.RelatorioUtil;

@ManagedBean(name = "lancamentoBean")
@ViewScoped
public class LancamentoBean implements Serializable {
	private static final long serialVersionUID = -3050807461213326560L;
	private List<Lancamento> lista;
	private Conta conta;
	private List<Double> saldos;
	private float saldoGeral;
	private Lancamento editado = new Lancamento();
	private Integer numeroCheque;

	private java.util.Date dataInicialRelatorio;
	private java.util.Date dataFinalRelatorio;
	private StreamedContent arquivoRetorno;

	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;

	public LancamentoBean() {
		this.novo();
	}

	public String novo() {
		this.editado = new Lancamento();
		this.editado.setData(new Date());
		this.numeroCheque = null;
		return null;
	}

	public void editar() {
		Cheque cheque = this.editado.getCheque();
		if (cheque != null) {
			this.numeroCheque = cheque.getChequeId().getCheque();
		}

	}

	public void salvar() {
		this.editado.setUsuario(this.contextoBean.getUsuarioLogado());
		this.editado.setConta(this.contextoBean.getContaAtiva());

		ChequeRN chequeRN = new ChequeRN();
		ChequeId chequeId = null;
		if (this.numeroCheque != null) {
			chequeId = new ChequeId();
			chequeId.setConta(this.contextoBean.getContaAtiva().getConta());
			chequeId.setCheque(this.numeroCheque);
			Cheque cheque = chequeRN.carregar(chequeId);
			FacesContext context = FacesContext.getCurrentInstance();
			if (cheque == null) {
				context.addMessage(null, new FacesMessage("Cheque não cadastrado"));
				return;
			} else if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
				context.addMessage(null, new FacesMessage("Cheque já cancelado"));
				return;
			} else {
				this.editado.setCheque(cheque);
				chequeRN.baixarCheque(chequeId, this.editado);
			}
		}

		LancamentoRN lancamentoRN = new LancamentoRN();
		lancamentoRN.salvar(this.editado);

		this.novo();
		this.lista = null;
	}

	public void mudouCheque(ValueChangeEvent event) {
		Integer chequeAnterior = (Integer) event.getOldValue();
		if (chequeAnterior != null) {
			ChequeRN chequeRN = new ChequeRN();
			try {
				chequeRN.desvinculaLancamento(contextoBean.getContaAtiva(), chequeAnterior);
			} catch (RNException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(e.getMessage()));
				return;
			}
		}
	}

	public void excluir() {
		LancamentoRN lancamentoRN = new LancamentoRN();
		lancamentoRN.excluir(this.editado);
		this.lista = null;
	}

	public StreamedContent getArquivoRetorno() {
		FacesContext context = FacesContext.getCurrentInstance();
		String usuario = contextoBean.getUsuarioLogado().getLogin();
		String nomeRelatorioJasper = "extrato";
		String nomeRelatorioSaida = usuario + "_extrato";
		LancamentoRN lancamentoRN = new LancamentoRN();
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.setTime(this.getDataInicialRelatorio());
		calendario.add(Calendar.DAY_OF_MONTH, -1);
		Date dataSaldo = new Date(calendario.getTimeInMillis());
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap();
		parametrosRelatorio.put("codigoUsuario", contextoBean.getUsuarioLogado().getCodigo());
		parametrosRelatorio.put("numeroConta", contextoBean.getContaAtiva().getConta());
		parametrosRelatorio.put("dataInicial", this.getDataInicialRelatorio());
		parametrosRelatorio.put("dataFinal", this.getDataFinalRelatorio());
		parametrosRelatorio.put("saldoAnterior", lancamentoRN.saldo(contextoBean.getContaAtiva(), dataSaldo));
		try {
			this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, RelatorioUtil.RELATORIO_PDF);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		return this.arquivoRetorno;
	}

	public java.util.Date getDataInicialRelatorio() {
		return dataInicialRelatorio;
	}

	public void setDataInicialRelatorio(java.util.Date dataInicialRelatorio) {
		this.dataInicialRelatorio = dataInicialRelatorio;
	}

	public java.util.Date getDataFinalRelatorio() {
		return dataFinalRelatorio;
	}

	public void setDataFinalRelatorio(java.util.Date dataFinalRelatorio) {
		this.dataFinalRelatorio = dataFinalRelatorio;
	}

	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

	public List<Lancamento> getLista() {
		if (this.lista == null || this.conta != this.contextoBean.getContaAtiva()) {
			this.conta = this.contextoBean.getContaAtiva();

			Calendar dataSaldo = new GregorianCalendar();
			dataSaldo.add(Calendar.MONTH, -1);
			dataSaldo.add(Calendar.DAY_OF_MONTH, -1);

			Calendar inicio = new GregorianCalendar();
			inicio.add(Calendar.MONTH, -1);

			LancamentoRN lancamentoRN = new LancamentoRN();
			this.saldoGeral = lancamentoRN.saldo(this.conta, dataSaldo.getTime());
			this.lista = lancamentoRN.listar(this.conta, inicio.getTime(), null);

			Categoria categoria = null;
			double saldo = this.saldoGeral;
			this.saldos = new ArrayList<Double>();
			for (Lancamento lancamento : this.lista) {
				categoria = lancamento.getCategoria();
				saldo = saldo + (lancamento.getValor().floatValue() * categoria.getFator());
				this.saldos.add(saldo);
			}
		}
		return this.lista;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Double> getSaldos() {
		return saldos;
	}

	public void setSaldos(List<Double> saldos) {
		this.saldos = saldos;
	}

	public float getSaldoGeral() {
		return saldoGeral;
	}

	public void setSaldoGeral(float saldoGeral) {
		this.saldoGeral = saldoGeral;
	}

	public Lancamento getEditado() {
		return editado;
	}

	public void setEditado(Lancamento editado) {
		this.editado = editado;
	}

	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

	public void setLista(List<Lancamento> lista) {
		this.lista = lista;
	}

	public void setNumeroCheque(Integer numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Integer getNumeroCheque() {
		return numeroCheque;
	}
}
