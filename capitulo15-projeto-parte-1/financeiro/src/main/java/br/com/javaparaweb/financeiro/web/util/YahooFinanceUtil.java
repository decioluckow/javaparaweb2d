package br.com.javaparaweb.financeiro.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import br.com.javaparaweb.financeiro.bolsa.acao.Acao;
import br.com.javaparaweb.financeiro.util.UtilException;

public class YahooFinanceUtil {
	public static final char ORIGEM_BOVESPA = 'B';

	public static final String SUFIXO_ACAO_BOVESPA = ".SA";
	public static final String SIGLA_BOVESPA = "^BVSP";

	public static final int INDICE_SIGLA_ACAO = 0;
	public static final int INDICE_ULTIMO_PRECO_DIA_ACAO = 1;
	public static final int INDICE_DATA_NEGOCIACAO_ACAO = 2;
	public static final int INDICE_HORA_NEGOCIACAO_ACAO = 3;
	public static final int INDICE_VARIACAO_DIA_ACAO = 4;
	public static final int INDICE_PRECO_ABERTURA_DIA_ACAO = 5;
	public static final int INDICE_MAIOR_PRECO_DIA_ACAO = 6;
	public static final int INDICE_MENOR_PRECO_DIA_ACAO = 7;
	public static final int INDICE_VOLUME_NEGOCIADO_DIA_ACAO = 8;

	public static String getSiglaLink(Acao acao) {
		if (acao == null || acao.getSigla() == null) {
			return SIGLA_BOVESPA;
		} else if (acao.getOrigem() == ORIGEM_BOVESPA) {
			return acao.getSigla() + SUFIXO_ACAO_BOVESPA;
		}
		return acao.getSigla();
	}

	public static String getInfoCotacao(int indiceInformacao, Acao acao) throws UtilException {
		String sigla = getSiglaLink(acao);
		try {
			String endereco = "http://download.finance.yahoo.com/d/quotes.csv?s=" + sigla + "&f=sl1d1t1c1ohgv&e=.csv";
			URL url = new URL(endereco);
			URLConnection conexao = url.openConnection();
			InputStreamReader conteudo = new InputStreamReader(conexao.getInputStream());
			BufferedReader arquivo = new BufferedReader(conteudo);

			String linha = null;
			String[] informacoesCotacao = null;
			if ((linha = arquivo.readLine()) != null) {
				arquivo.close();
				conteudo.close();
				linha = linha.replace("\"", "");
				informacoesCotacao = linha.split("[;,]");
				String valor = informacoesCotacao[indiceInformacao];
				if (!"N/A".equals(valor)) {
					return valor;
				}
			}
			return "0";
		} catch (IOException e) {
			throw new UtilException("N�o foi poss�vel recuperar cota��o. Erro: " + e.getMessage());
		}
	}
}
