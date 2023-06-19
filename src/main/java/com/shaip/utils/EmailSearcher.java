package com.shaip.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

/**
 * This function demonstrates how to search for e-mail messages which satisfy a
 * search criterion and extract the specific href from the email.
 */
public class EmailSearcher {

	/**
	 * Searches for e-mail messages containing the specified keyword in Subject
	 * field.
	 * 
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param keyword
	 * @throws IOException
	 */
	static String host = "imap.gmail.com";
	static String port = "993";
	static String userName = "automation.shaip@shaip.com";
	static String password = "Automation@43210";
	static String keyword = "Shaip Workforce - Email confirmation";
	static String keywordForgot = "Shaip Workforce - Password reset request";
	static String keywordInvite = "Shaip Cloud - Invitation";



	// System.out.println(searchEmail(host,port,userName,password,keyword));

	public static String doEmailVerification() throws IOException {

		Properties properties = new Properties();

		// server setting
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", port);

		// SSL setting
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
		String verificationLink = null;

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("imap");
			store.connect(userName, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// creates a search criterion
			SearchTerm searchCondition = new SearchTerm() {
				@Override
				public boolean match(Message message) {
					try {
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);

						if (message.getSubject().contains(keyword) && message.getSentDate().after(cal.getTime())) {
							return true;
						}
					} catch (MessagingException ex) {
						ex.printStackTrace();
					}
					return false;
				}
			};

			// performs search through the folder
			Message[] foundMessages = folderInbox.search(searchCondition);

			int length = foundMessages.length;
			System.out.println("*********" + length);

			for (int i = 0; i < length; i++) {
				ArrayList<String> links = new ArrayList<String>();
				Message message = foundMessages[i];
				String subject = message.getSubject();
				String content = message.getContent().toString();
				Pattern linkPattern = Pattern.compile(" <a\\b[^>]*href=\"([^\"]*)[^>]*>(.*?)</a>",
						Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				Matcher pageMatcher = linkPattern.matcher(content);
				while (pageMatcher.find()) {
					links.add(pageMatcher.group(1));
				}
				System.out.println("Found message #" + i + ": " + subject);
				// System.out.println("Found message #" + i + ": " + content);
				for (String temp : links) {
					if (temp.contains("verify-email-address")) {
						verificationLink = temp;
//                    	System.out.println("========================================");
//                        System.out.println(temp);
					}
				}

			}

			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store.");
			ex.printStackTrace();
		}

		return verificationLink;
	}
	
	
	public static String doFortgotPasswordEmailVerification() throws IOException {

		
		Properties properties = new Properties();

		// server setting
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", port);

		// SSL setting
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
		String verificationLink = null;

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("imap");
			store.connect(userName, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// creates a search criterion
			SearchTerm searchCondition = new SearchTerm() {
				@Override
				public boolean match(Message message) {
					try {
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);

						if (message.getSubject().contains(keywordForgot) && message.getSentDate().after(cal.getTime())) {
							return true;
						}
					} catch (MessagingException ex) {
						ex.printStackTrace();
					}
					return false;
				}
			};

			// performs search through the folder
			Message[] foundMessages = folderInbox.search(searchCondition);

			int length = foundMessages.length;
			System.out.println("*********" + length);

			for (int i = 0; i < length; i++) {
				ArrayList<String> links = new ArrayList<String>();
				Message message = foundMessages[i];
				String subject = message.getSubject();
				String content = message.getContent().toString();
				Pattern linkPattern = Pattern.compile(" <a\\b[^>]*href=\"([^\"]*)[^>]*>(.*?)</a>",
						Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
				Matcher pageMatcher = linkPattern.matcher(content);
				while (pageMatcher.find()) {
					links.add(pageMatcher.group(1));
				}
				System.out.println("Found message #" + i + ": " + subject);
				// System.out.println("Found message #" + i + ": " + content);
				for (String temp : links) {
					if (temp.contains("reset-password")) {
						verificationLink = temp;
//                    	System.out.println("========================================");
//                        System.out.println(temp);
					}
				}

			}

			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store.");
			ex.printStackTrace();
		}

		return verificationLink;
	}
	
	
	public static String checkForInvitationMail() throws IOException {

		Properties properties = new Properties();

		// server setting
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", port);

		// SSL setting
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
		String verificationLink = null;

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("imap");
			store.connect(userName, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// creates a search criterion
			SearchTerm searchCondition = new SearchTerm() {
				@Override
				public boolean match(Message message) {
					try {
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);

						if (message.getSubject().contains(keywordInvite) && message.getSentDate().after(cal.getTime())) {
							return true;
						}
					} catch (MessagingException ex) {
						ex.printStackTrace();
					}
					return false;
				}
			};

			// performs search through the folder
			Message[] foundMessages = folderInbox.search(searchCondition);

			int length = foundMessages.length;
			System.out.println("*********" + length);

			for (int i = 0; i < length; i++) {
				ArrayList<String> links = new ArrayList<String>();
				Message message = foundMessages[i];
				String subject = message.getSubject();
				String content = message.getContent().toString();
				System.out.println("Email Content---->"+content);

				Pattern linkPattern = Pattern.compile("\\bPassword[\\s:]<\\/span>*(\\w+@\\d+)",
						Pattern.CASE_INSENSITIVE);
				Matcher pageMatcher = linkPattern.matcher(content);
				while (pageMatcher.find()) {
					verificationLink=pageMatcher.group(1);
					break;
				}
				System.out.println("Found message #" + i + ": " + subject);
				// System.out.println("Found message #" + i + ": " + content);
				
					
			

			}

			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store.");
			ex.printStackTrace();
		}

		return verificationLink;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Test this program with a Gmail's account
	 * 
	 * @throws IOException
	 * 
	 *                     public static void main(String[] args) throws IOException
	 *                     { String host = "imap.gmail.com"; String port = "993";
	 *                     String userName = "automation.shaip@shaip.com"; String
	 *                     password = "Automation@123"; EmailSearcher searcher = new
	 *                     EmailSearcher(); String keyword = "Shaip Workforce -
	 *                     Email confirmation";
	 *                     System.out.println("==============================");
	 *                     System.out.println(searcher.searchEmail(host, port,
	 *                     userName, password, keyword)); }
	 */

}
