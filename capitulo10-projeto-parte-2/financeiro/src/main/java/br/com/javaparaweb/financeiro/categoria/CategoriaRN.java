package br.com.javaparaweb.financeiro.categoria;

import java.util.List;

import br.com.javaparaweb.financeiro.usuario.Usuario;
import br.com.javaparaweb.financeiro.util.DAOFactory;

public class CategoriaRN {

	private CategoriaDAO categoriaDAO;

	public CategoriaRN() {
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
	}

	public List<Categoria> listar(Usuario usuario) {
		return this.categoriaDAO.listar(usuario);
	}

	public Categoria salvar(Categoria categoria) {
		Categoria pai = categoria.getPai();

		if (pai == null) {
			String msg = "A Categoria " + categoria.getDescricao() + " deve ter um pai definido";
			throw new IllegalArgumentException(msg);
		}

		boolean mudouFator = pai.getFator() != categoria.getFator();

		categoria.setFator(pai.getFator());
		categoria = this.categoriaDAO.salvar(categoria);

		if (mudouFator) {
			categoria = this.carregar(categoria.getCodigo());
			this.replicarFator(categoria, categoria.getFator());
		}
		return categoria;
	}

	private void replicarFator(Categoria categoria, int fator) {
		if (categoria.getFilhos() != null) {
			for (Categoria filho : categoria.getFilhos()) {
				filho.setFator(fator);
				this.categoriaDAO.salvar(filho);
				this.replicarFator(filho, fator);
			}
		}
	}

	public void excluir(Categoria categoria) {
		this.categoriaDAO.excluir(categoria);
	}

	public void excluir(Usuario usuario) {
		List<Categoria> lista = this.listar(usuario);
		for (Categoria categoria : lista) {
			this.categoriaDAO.excluir(categoria);
		}
	}

	public Categoria carregar(Integer categoria) {
		return this.categoriaDAO.carregar(categoria);
	}

	public void salvaEstruturaPadrao(Usuario usuario) {

		Categoria despesas = new Categoria(null, usuario, "DESPESAS", -1);
		despesas = this.categoriaDAO.salvar(despesas);
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Moradia", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Alimenta��o", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Vestu�rio", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Deslocamento", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Cuidados Pessoais", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Educa��o", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Sa�de", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Lazer", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Despesas Financeiras", -1));

		Categoria receitas = new Categoria(null, usuario, "RECEITAS", 1);
		receitas = this.categoriaDAO.salvar(receitas);
		this.categoriaDAO.salvar(new Categoria(receitas, usuario, "Sal�rio", 1));
		this.categoriaDAO.salvar(new Categoria(receitas, usuario, "Restitui��es", 1));
		this.categoriaDAO.salvar(new Categoria(receitas, usuario, "Rendimento", 1));
	}
}
