package br.com.javaparaweb.financeiro.web.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class MensagemUtil {

	private static final String PACOTE_MENSAGENS_IDIOMAS = "br.com.javaparaweb.financeiro.idioma.mensagens";

	public static String getMensagem(String propriedade) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		return bundle.getString(propriedade);
	}

	public static String getMensagem(String propriedade, Object... parametros) {
		String mensagem = getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}

	public static String getMensagem(Locale locale, String propriedade) {
		ResourceBundle bundle = ResourceBundle.getBundle(PACOTE_MENSAGENS_IDIOMAS, locale);
		return bundle.getString(propriedade);
	}

	public static String getMensagem(Locale locale, String propriedade, Object... parametros) {
		String mensagem = getMensagem(locale, propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}

}
