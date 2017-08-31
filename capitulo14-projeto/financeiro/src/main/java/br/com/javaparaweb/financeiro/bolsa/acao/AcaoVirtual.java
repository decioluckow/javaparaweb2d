package br.com.javaparaweb.financeiro.bolsa.acao;

public class AcaoVirtual {
	private Acao acao = new Acao();
	private float ultimoPreco;
	private float total;

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public float getUltimoPreco() {
		return ultimoPreco;
	}

	public void setUltimoPreco(float ultimoPreco) {
		this.ultimoPreco = ultimoPreco;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
