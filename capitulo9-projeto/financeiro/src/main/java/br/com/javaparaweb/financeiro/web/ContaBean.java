package br.com.javaparaweb.financeiro.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.javaparaweb.financeiro.conta.Conta;
import br.com.javaparaweb.financeiro.conta.ContaRN;

@ManagedBean
@RequestScoped
public class ContaBean {
	private Conta selecionada = new Conta();
	private List<Conta> lista = null;
	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;

	public String salvar() {
		this.selecionada.setUsuario(this.contextoBean.getUsuarioLogado());
		ContaRN contaRN = new ContaRN();
		contaRN.salvar(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
		return null;
	}

	public String excluir() {
		ContaRN contaRN = new ContaRN();
		contaRN.excluir(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
		return null;
	}

	public String tornarFavorita() {
		ContaRN contaRN = new ContaRN();
		contaRN.tornarFavorita(this.selecionada);
		this.selecionada = new Conta();
		return null;
	}

	public Conta getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Conta selecionada) {
		this.selecionada = selecionada;
	}

	public List<Conta> getLista() {
		if (this.lista == null) {
			ContaRN contaRN = new ContaRN();
			this.lista = contaRN.listar(this.contextoBean.getUsuarioLogado());
		}
		return this.lista;

	}

	public void setLista(List<Conta> lista) {
		this.lista = lista;
	}

	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

}
