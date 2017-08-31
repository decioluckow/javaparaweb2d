package br.com.javaparaweb.financeiro.cheque;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.javaparaweb.financeiro.conta.Conta;
import br.com.javaparaweb.financeiro.lancamento.Lancamento;

@Entity
@Table(name = "cheque")
public class Cheque implements Serializable {

	private static final long serialVersionUID = -7391845936828023963L;
	public static final char SITUACAO_CHEQUE_BAIXADO = 'B';
	public static final char SITUACAO_CHEQUE_CANCELADO = 'C';
	public static final char SITUACAO_CHEQUE_NAO_EMITIDO = 'N';

	@EmbeddedId
	private ChequeId chequeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "conta", referencedColumnName = "conta", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_cheque_conta"))
	private Conta conta;

	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;

	@Column(nullable = false, precision = 1)
	private Character situacao;

	@OneToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "lancamento", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "fk_cheque_lancamento"))
	private Lancamento lancamento;

	public ChequeId getChequeId() {
		return chequeId;
	}

	public void setChequeId(ChequeId chequeId) {
		this.chequeId = chequeId;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Character getSituacao() {
		return situacao;
	}

	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chequeId == null) ? 0 : chequeId.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((lancamento == null) ? 0 : lancamento.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cheque other = (Cheque) obj;
		if (chequeId == null) {
			if (other.chequeId != null)
				return false;
		} else if (!chequeId.equals(other.chequeId))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (lancamento == null) {
			if (other.lancamento != null)
				return false;
		} else if (!lancamento.equals(other.lancamento))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}

}
