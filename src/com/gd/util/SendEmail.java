package com.gd.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static void main(String[] args) {
		// Mention the Recipient's email address
		String to = "from@gmail.com";
		// Mention the Sender's email address
		String from = "to@gmail.com";
		// Mention the SMTP server address. Below Gmail's SMTP server is being used to
		// send email
		String host = "smtp.gmail.com";
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("from@gmail.com", "******");
			}
		});
		// Used to debug SMTP issues
		session.setDebug(true);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set Subject: header field
			message.setSubject("This is the Subject Line!");
			// Now set the actual message
			message.setText("This is actual message");
			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
