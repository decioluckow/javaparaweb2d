package br.com.javaparaweb.comercio.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import br.com.javaparaweb.comercio.entidades.Cliente;
import br.com.javaparaweb.comercio.entidades.Empregado;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -3219882293977763487L;

	@Id
	@GeneratedValue
	@Column(name = "cod_pedido")
	private Integer pedido;

	@Column(name = "data_pedido", updatable = false)
	private Date dataPedido;

	@Column(name = "hora_pedido", updatable = false)
	private Time horaPedido;

	private String descricao;

	@ManyToOne
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "cod_empregado")
	private Empregado empregado;

	@ManyToMany
	@JoinTable(name = "item", joinColumns = { @JoinColumn(name = "cod_pedido", 
	referencedColumnName = "cod_pedido") }, inverseJoinColumns = { @JoinColumn(name = "cod_produto") })
	private Set<Produto> produto = new HashSet<Produto>();

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Time getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Time horaPedido) {
		this.horaPedido = horaPedido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public Set<Produto> getProduto() {
		return produto;
	}

	public void setProduto(Set<Produto> produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((empregado == null) ? 0 : empregado.hashCode());
		result = prime * result
				+ ((horaPedido == null) ? 0 : horaPedido.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (empregado == null) {
			if (other.empregado != null)
				return false;
		} else if (!empregado.equals(other.empregado))
			return false;
		if (horaPedido == null) {
			if (other.horaPedido != null)
				return false;
		} else if (!horaPedido.equals(other.horaPedido))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

}
