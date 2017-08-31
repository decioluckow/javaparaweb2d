package br.com.javaparaweb.financeiro.webservice;

import java.util.Date;

public class LancamentoItem {

	private Date data;
	private String descricao;
	private float valor;

	public LancamentoItem() {
	}

	public LancamentoItem(Date data, String descricao, float valor, int categoria) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
