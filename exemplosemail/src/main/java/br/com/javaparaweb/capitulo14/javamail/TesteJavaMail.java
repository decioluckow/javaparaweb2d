package br.com.javaparaweb.capitulo14.javamail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import br.com.javaparaweb.capitulo14.javamail.Autenticacao;

public class TesteJavaMail {

	private static final String CONTA = "adm@localhost";

	public static void main(String[] args) throws AddressException, MessagingException {

		Properties config = new Properties(); 
		config.setProperty("mail.smtp.host", "localhost");
		config.setProperty("mail.smtp.port", "25");
		config.setProperty("mail.smtp.auth", "true");

		Session sessao = Session.getInstance(config, new Autenticacao(CONTA, "123")); 

		MimeMessage email = new MimeMessage(sessao);
		email.setFrom(new InternetAddress(CONTA));
		email.setRecipient(Message.RecipientType.TO, new InternetAddress("livrojava@localhost")); 
		email.setSubject("Teste de e-mail usando JavaMail");
		email.setSentDate(new Date());
		email.setText("Corpo da mensagem");
		Transport.send(email); 

		System.out.println("E-mail enviado com sucesso");
	}
}
