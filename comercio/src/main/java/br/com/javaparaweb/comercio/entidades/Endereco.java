package br.com.javaparaweb.comercio.entidades;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Parameter;

import br.com.javaparaweb.comercio.entidades.Cliente;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
	private static final long	serialVersionUID	= 1280791770249284855L;

	@Id
	@GeneratedValue(generator = "fk_endereco_cod_cliente") 
	@org.hibernate.annotations.GenericGenerator(name = "fk_endereco_cod_cliente", 
		strategy = "foreign", parameters = @Parameter(name = "property", value = "cliente")) 
	@Column(name = "cod_cliente")
	private Integer	endereco;

	@OneToOne(mappedBy="endereco")
	private Cliente	cliente;

	private String	rua;
	private String	cidade;
	public Integer getEndereco() {
		return endereco;
	}
	public void setEndereco(Integer endereco) {
		this.endereco = endereco;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
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
		Endereco other = (Endereco) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}
}
