package br.com.javaparaweb.capitulo3.crudannotations;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "contato")
public class Contato {

	@Id
	@GeneratedValue
	@Column(name = "codigo")
	private Integer	codigo;

	@Column(name = "nome", length = 50, nullable = true)
	private String	nome;

	@Column(name = "telefone", length = 50, nullable = true)
	private String	telefone;

	@Column(name = "email", length = 50, nullable = true)
	private String	email;

	@Column(name = "dt_cad", nullable = true)
	private Date		dataCadastro;

	@Column(name = "obs", nullable = true)
	private String	observacao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

} 
