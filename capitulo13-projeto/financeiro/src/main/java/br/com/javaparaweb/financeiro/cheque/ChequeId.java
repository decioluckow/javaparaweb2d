package br.com.javaparaweb.financeiro.cheque;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChequeId implements Serializable {

	private static final long serialVersionUID = 1284348059531750623L;

	@Basic(optional = false)
	@Column(name = "cheque", nullable = false)
	private Integer cheque;

	@Basic(optional = false)
	@Column(name = "conta", nullable = false)
	private Integer conta;

	public ChequeId(Integer conta, Integer cheque) {
		this.conta = conta;
		this.cheque = cheque;
	}

	public ChequeId() {
	}

	public Integer getCheque() {
		return cheque;
	}

	public void setCheque(Integer cheque) {
		this.cheque = cheque;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cheque == null) ? 0 : cheque.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
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
		ChequeId other = (ChequeId) obj;
		if (cheque == null) {
			if (other.cheque != null)
				return false;
		} else if (!cheque.equals(other.cheque))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		return true;
	}

}
