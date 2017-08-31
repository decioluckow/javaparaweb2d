package br.com.javaparaweb.financeiro.usuario;

import java.util.List;
import java.util.Locale;

import br.com.javaparaweb.financeiro.categoria.CategoriaRN;
import br.com.javaparaweb.financeiro.util.DAOFactory;
import br.com.javaparaweb.financeiro.util.RNException;
import br.com.javaparaweb.financeiro.util.UtilException;
import br.com.javaparaweb.financeiro.web.util.EmailUtil;
import br.com.javaparaweb.financeiro.web.util.MensagemUtil;

public class UsuarioRN {
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}

	public Usuario buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}

	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getCodigo();
		if (codigo == null || codigo == 0) {
			usuario.getPermissao().add("ROLE_USUARIO");
			this.usuarioDAO.salvar(usuario);
			CategoriaRN categoriaRN = new CategoriaRN();
			categoriaRN.salvaEstruturaPadrao(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public void enviarEmailPosCadastramento(Usuario usuario) throws RNException {
		// Enviando um e-mail conforme o idioma do usuário
		String[] info = usuario.getIdioma().split("_");
		Locale locale = new Locale(info[0], info[1]);
		String titulo = MensagemUtil.getMensagem(locale, "email_titulo");
		String mensagem = MensagemUtil.getMensagem(locale, "email_mensagem", usuario.getNome(), usuario.getLogin(),
				usuario.getSenha());
		try {
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.enviarEmail(null, usuario.getEmail(), titulo, mensagem);
		} catch (UtilException e) {
			throw new RNException(e);
		}
	}

	public void excluir(Usuario usuario) {
		CategoriaRN categoriaRN = new CategoriaRN();
		categoriaRN.excluir(usuario);
		this.usuarioDAO.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}
}
