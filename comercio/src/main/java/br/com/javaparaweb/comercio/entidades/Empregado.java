package br.com.javaparaweb.comercio.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "empregado")
public class Empregado implements Serializable {

	private static final long serialVersionUID	= -2417223544507074940L;
	
	@Id
	@GeneratedValue
	@Column(name = "cod_empregado")
	private Integer	empregado;
	
	private String nome;

	@OneToOne 
	@JoinColumn(name = "cod_chefe") 
	private Empregado	chefe;

	public Integer getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Integer empregado) {
		this.empregado = empregado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Empregado getChefe() {
		return chefe;
	}

	public void setChefe(Empregado chefe) {
		this.chefe = chefe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chefe == null) ? 0 : chefe.hashCode());
		result = prime * result
				+ ((empregado == null) ? 0 : empregado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Empregado other = (Empregado) obj;
		if (chefe == null) {
			if (other.chefe != null)
				return false;
		} else if (!chefe.equals(other.chefe))
			return false;
		if (empregado == null) {
			if (other.empregado != null)
				return false;
		} else if (!empregado.equals(other.empregado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
