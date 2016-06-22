package br.com.javaparaweb.financeiro.web.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

public class MensagemUtil {

	public static String getMensagem(String propriedade) { 
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		return bundle.getString(propriedade);
	}

	public static String getMensagem(String propriedade, Object...parametros) { 
		String mensagem = getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}
}
