package eventBrite.UH.EventTools;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailNotifier 
{
	private String fromEmail;
	private String password;
	private String toEmail;
	private String emailSubject;	
	private String emailMessage;
	private boolean senderInfoSet;
	private boolean emailInfoSet;

	public MailNotifier()
	{
		senderInfoSet 	= false;
		emailInfoSet		= false;
	}

	public boolean isReadyToSend() {return (senderInfoSet & emailInfoSet);}

	public int setSenderInfo(String senderGmail, String password)
	{
		if(checkGmailFormat(senderGmail))
		{
			fromEmail 		= senderGmail;
			this.password 	= password;
			senderInfoSet 	= true;
			return 0;
		}
		return -1;
	}

	public int setEmailInfo(String receiverGmail, String emailSubject, String emailMessage)
	{
		if(checkGmailFormat(receiverGmail))
		{
			toEmail	= receiverGmail;
			this.emailSubject = emailSubject;
			this.emailMessage = emailMessage;
			emailInfoSet = true;
			return 0;
		}
		return -1;
	}

	public void send()
	{  
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication() 
					{
						return new PasswordAuthentication(fromEmail,password);
					}
			}
		);

		try 
		{
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
			message.setSubject(emailSubject);
			message.setText(emailMessage);

			Transport.send(message);
		} 
		catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public static boolean checkEmailAddressFormat(String emailAddress)
	{
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (emailAddress.matches(EMAIL_REGEX)) 
			return true;
		return false;
	}

	public static boolean checkGmailFormat(String gmailAddress)
	{
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@gmail.com";
		if (gmailAddress.matches(EMAIL_REGEX)) 
			return true;
		return false;
	}
}