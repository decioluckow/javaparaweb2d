package br.com.javaparaweb.comercio;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction; //ERRATA de 18/01/2016
import br.com.javaparaweb.comercio.entidades.Categoria;
import br.com.javaparaweb.comercio.entidades.Cliente;
import br.com.javaparaweb.comercio.entidades.Empregado;
import br.com.javaparaweb.comercio.entidades.Endereco;
import br.com.javaparaweb.comercio.entidades.Pedido;
import br.com.javaparaweb.comercio.entidades.Produto;
import br.com.javaparaweb.comercio.util.HibernateUtil;
public class Comercio {
	private Session sessao = null;
	public Comercio(Session sessao) {
		this.sessao = sessao;
	}
	private Produto criarProdutoFilmeHobbit() {
		Categoria categoriaFilmes = new Categoria("Filmes",	"Categoria de filmes");
		sessao.save(categoriaFilmes);
		Produto produto = new Produto();
		produto.setDescricao("O Hobbit");
		produto.setPreco(29.99f);
		produto.setCategoria(categoriaFilmes);
		sessao.save(produto);
		return produto;
	}
	private Produto criarProdutoLivroPeregrino() {
		Categoria categoriaLivro = new Categoria("Livro", "Categoria de livros");
		sessao.save(categoriaLivro);
		Produto produto = new Produto();
		produto.setDescricao("O Peregrino");
		produto.setPreco(15.75f);
		produto.setCategoria(categoriaLivro);
		sessao.save(produto);
		return produto;
	}
	private Produto criarProdutoAlimentoMistoQuente() {
		Categoria categoriaAlimentos = new Categoria("Alimento", "Categoria de alimentos");
		sessao.save(categoriaAlimentos);
		Produto produto = new Produto();
		produto.setDescricao("Misto Quente");
		produto.setPreco(1.99f);
		produto.setCategoria(categoriaAlimentos);
		sessao.save(produto);
		return produto;
	}
	private Cliente criarClienteBeltrano() {
		Cliente cliente = new Cliente();
		cliente.setNome("Beltrano");
		Endereco endereco = new Endereco();
		endereco.setRua("Rua dos Beltranos, 60");
		endereco.setCidade("Blumenau");
		endereco.setCliente(cliente);
		sessao.save(endereco);
		cliente.setEndereco(endereco);
		sessao.save(cliente);
		return cliente;
	}
	private Cliente criarClienteFulano() {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		cliente.setNome("Fulano");
		endereco.setRua("Rua dos Fulanos, 6");
		endereco.setCidade("Florianópolis");
		endereco.setCliente(cliente);
		sessao.save(endereco);
		cliente.setEndereco(endereco);
		sessao.save(cliente);
		return cliente;
	}

	public Empregado criarEmpregadoMelo() {
		Empregado chefe = new Empregado();
		chefe.setNome("Chefe");
		sessao.save(chefe);
		Empregado empregado1 = new Empregado();
		empregado1.setNome("Melo");
		empregado1.setChefe(chefe);
		sessao.save(empregado1);
		return empregado1;
	}
	public Empregado criarEmpregadoLuckow() {
		Empregado empregado = new Empregado();
		empregado.setNome("Luckow");
		sessao.save(empregado);
		return empregado;
	}
	public void criarPedidos() {
		Produto filmeHobbit = criarProdutoFilmeHobbit();
		Produto livroPeregrino = criarProdutoLivroPeregrino();
		Produto alimentoMistoQuente = criarProdutoAlimentoMistoQuente();
		Empregado empregadoLuckow = criarEmpregadoLuckow();
		Empregado empregadoMelo = criarEmpregadoMelo();
		/*
		 * Pedido do Sr. Fulano
		 */
		Cliente clienteFulano = criarClienteFulano();
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteFulano);
		pedido.setEmpregado(empregadoLuckow);
		pedido.setDescricao("Pedido do Sr Fulano");
		pedido.setDataPedido(new Date(System.currentTimeMillis()));
		pedido.setHoraPedido(new Time(System.currentTimeMillis()));
		Set<Produto> produtos = new HashSet<Produto>();
		pedido.setProduto(produtos);
		produtos.add(filmeHobbit);
		produtos.add(livroPeregrino);
		sessao.save(pedido);
		/*
		 * Pedido do Sr. Beltrano
		 */
		Cliente clienteBeltrano = criarClienteBeltrano();
		pedido = new Pedido();
		pedido.setCliente(clienteBeltrano);
		pedido.setEmpregado(empregadoMelo);
		pedido.setDescricao("Pedido do Sr Beltrano");
		pedido.setDataPedido(new Date(System.currentTimeMillis()));
		pedido.setHoraPedido(new Time(System.currentTimeMillis()));
		produtos = new HashSet<Produto>();
		pedido.setProduto(produtos);
		produtos.add(filmeHobbit);
		produtos.add(alimentoMistoQuente);
		sessao.save(pedido);
	}
	public static void main(String[] args) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Comercio comercio = new Comercio(sessao);
		Transaction transacao = sessao.beginTransaction(); //ERRATA de 18/01/2016
		comercio.criarPedidos();
		transacao.commit(); //ERRATA de 18/01/2016
		System.out.println("Cadastrou!");
	}
}
