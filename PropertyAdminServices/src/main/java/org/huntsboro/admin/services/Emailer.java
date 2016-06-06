package org.huntsboro.admin.services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class Emailer {
	
	public static void sendMail(String mailTo, String mailFrom, String body, String subject) throws Exception{
		
		Email email = new SimpleEmail();
		email.setHostName("server165.web-hosting.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("info@huntsboro-properties.com", "huntsboroInfo2016"));
		email.setSSLOnConnect(true);
		email.setFrom(mailFrom);
		email.setSubject(subject);
		email.setMsg(body);
		email.addTo(mailTo);
		email.send();
		
	}
	
}
