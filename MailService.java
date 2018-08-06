package org.blackjack.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	private JavaMailSender mailSender;
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String emailId, String emailhash, String username)
	{
		MimeMessage message =  this.mailSender.createMimeMessage();
		MimeMessageHelper mimeHelper;
		try {
			mimeHelper = new MimeMessageHelper(message,true);
			mimeHelper.setTo(emailId);
			
			
			mimeHelper.setFrom("srinivaschepuri2012@gmail.com");
			mimeHelper.setSubject("Password Reset for Blackjack App");
			mimeHelper.setText("<html><body>Hi " +username+ ",<br/><br/><a href='https://localhost:8443/newPassword/"+emailhash+"/'> Click here</a> to reset password. <br/><br/><br/> Thanks,<br/>Blackjack Team</body></html>",true);
			mailSender.send(message);
		} catch (MessagingException e) {
			System.out.println("Error Sending email "+ e.getMessage());
		}
		
	}
}
