package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String toEmail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ndawlimale@gmail.com");
		message.setTo(toEmail);
		message.setText("Cher client! votre commande est traitée!!");
		message.setSubject("Commande traitée");
		
		mailSender.send(message);
		System.out.println("Mail send successfully....");
		
	}

}
	