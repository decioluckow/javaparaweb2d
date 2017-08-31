package br.com.javaparaweb.financeiro.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import br.com.javaparaweb.financeiro.bolsa.acao.Acao;
import br.com.javaparaweb.financeiro.bolsa.acao.AcaoRN;
import br.com.javaparaweb.financeiro.bolsa.acao.AcaoVirtual;
import br.com.javaparaweb.financeiro.web.util.YahooFinanceUtil;

@ManagedBean(name = "acaoBean")
@RequestScoped
public class AcaoBean {
	private AcaoVirtual selecionada = new AcaoVirtual();
	private List<AcaoVirtual> lista = null;
	private String linkCodigoAcao = null;
	private PieChartModel percentualQuantidade = new PieChartModel();
	private PieChartModel percentualValor = new PieChartModel();

	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;

	public void salvar() {
		AcaoRN acaoRN = new AcaoRN();
		Acao acao = this.selecionada.getAcao();
		acao.setSigla(acao.getSigla().toUpperCase());
		acao.setUsuario(contextoBean.getUsuarioLogado());
		acaoRN.salvar(acao);
		this.selecionada = new AcaoVirtual();
		this.lista = null;
	}

	public void excluir() {
		AcaoRN acaoRN = new AcaoRN();
		acaoRN.excluir(this.selecionada.getAcao());
		this.selecionada = new AcaoVirtual();
		this.lista = null;
	}

	public List<AcaoVirtual> getLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		AcaoRN acaoRN = new AcaoRN();
		try {
			if (this.lista == null) {
				this.lista = acaoRN.listarAcaoVirtual(contextoBean.getUsuarioLogado());
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return this.lista;
	}

	public String getLinkCodigoAcao() {
		this.linkCodigoAcao = YahooFinanceUtil.getSiglaLink(this.selecionada.getAcao());
		return this.linkCodigoAcao;
	}

	public PieChartModel getPercentualQuantidade() {
		List<AcaoVirtual> acoes = this.getLista();
		if (acoes.size() > 0) {
			this.percentualQuantidade.setLegendPosition("e");
			this.percentualQuantidade.setShowDataLabels(true);
			this.percentualQuantidade.setDataFormat("percent");
			for (AcaoVirtual acaoVirtual : acoes) {
				Acao acao = acaoVirtual.getAcao();
				this.percentualQuantidade.set(acao.getSigla(), acao.getQuantidade());
			}
		}
		return this.percentualQuantidade;
	}

	public PieChartModel getPercentualValor() {
		List<AcaoVirtual> acoes = this.getLista();
		if (acoes.size() > 0) {
			this.percentualValor.setLegendPosition("e");
			this.percentualValor.setShowDataLabels(true);
			this.percentualValor.setDataFormat("percent");
			for (AcaoVirtual acaoVirtual : acoes) {
				Acao acao = acaoVirtual.getAcao();
				this.percentualValor.set(acao.getSigla(), acaoVirtual.getTotal());
			}
		}
		return this.percentualValor;
	}

	public AcaoVirtual getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(AcaoVirtual selecionada) {
		this.selecionada = selecionada;
	}

	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

	public void setLista(List<AcaoVirtual> lista) {
		this.lista = lista;
	}

	public void setLinkCodigoAcao(String linkCodigoAcao) {
		this.linkCodigoAcao = linkCodigoAcao;
	}

	public void setPercentualQuantidade(PieChartModel percentualQuantidade) {
		this.percentualQuantidade = percentualQuantidade;
	}

	public void setPercentualValor(PieChartModel percentualValor) {
		this.percentualValor = percentualValor;
	}
}
