package com.shaip.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.shaip.base.FrameworkContstants;
import com.shaip.factories.DriverBase;

public class EmailUtility extends getHtmlCode {

	public void doSendEmail() throws Exception {

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication("bhavin.s@shaip.com", "znxclfgqmemkpdsp");

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
			// Set the from address
			message.setFrom(new InternetAddress("bhavin.s@shaip.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("bhavin.s@shaip.com"));
            
                        // Add the subject link
			message.setSubject("ShaipCloud 2.0 Automation QA Report");

			
			

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText(getHtml());
			messageBodyPart1.setHeader("Content-Type", "text/html");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = FrameworkContstants.getEmailAttachmentPath();
			String fileNameActual="ShaipCloudQA_AutomationReport.html";
			
		
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(fileNameActual);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);
			//message.setContent(message, "text/html");

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}
	
	
		
		
	

}