package br.com.javaparaweb.comercio.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import br.com.javaparaweb.comercio.entidades.Endereco;
import br.com.javaparaweb.comercio.entidades.Pedido;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long	serialVersionUID	= -1707591652638708533L;

	@Id
	@GeneratedValue
	@Column(name = "cod_cliente")
	private Integer		cliente;

	@OneToOne 
	@PrimaryKeyJoinColumn(name = "cod_cliente")
	private Endereco		endereco;

	@OneToMany(mappedBy="cliente", fetch = FetchType.LAZY)
	private List<Pedido>	pedidos;

	@Column(length = 45)	
	private String			nome;

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
