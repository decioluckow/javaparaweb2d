package br.com.javaparaweb.financeiro.usuario;

import java.util.List;

public interface UsuarioDAO {
	public void salvar(Usuario usuario);

	public void atualizar(Usuario usuario);

	public void excluir(Usuario usuario);

	public Usuario carregar(Integer codigo);

	public Usuario buscarPorLogin(String login);

	public List<Usuario> listar();
}
