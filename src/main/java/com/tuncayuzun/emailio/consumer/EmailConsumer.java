package com.tuncayuzun.emailio.consumer;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import com.tuncayuzun.emailio.model.WelcomeEmail;

@Component
public class EmailConsumer {

	boolean isMailSent;

	@Autowired
	private RetryTemplate retryTemplate;

	@Autowired
	private JavaMailSender javaMailSender;

	@JmsListener(destination = "welcome-password-queue")
	public void welcomeAndPasswordListener(WelcomeEmail email) throws DummyMailException {
		System.out.println("Received Welcome Message; " + email);

		mailSender(email);

	}

	@JmsListener(destination = "newsletter-queue")
	public void newsletterlListener(String msg) {
		System.out.println("Received Newsletter Message; " + msg);
	}

	
	public void mailSender(WelcomeEmail email) throws DummyMailException {
		
		  retryTemplate.execute(
		            new RetryCallback<Void, DummyMailException>() {
		                @Override
		                public Void doWithRetry(RetryContext context) throws DummyMailException {
		                	isMailSent = false;
		                	System.out.println("Trying to send...");
		            		if (isMailSent) {
		            			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		            			simpleMailMessage.setTo(email.getTo());
		            			simpleMailMessage.setSubject(email.getSubject());
		            			simpleMailMessage.setText(email.getBody());
		            			javaMailSender.send(simpleMailMessage);
		            		} 
		                    throw new DummyMailException("An erro occured while mail sending...!");
		                }
		            },
		            new RecoveryCallback<Void>() {
		                @Override
		                public Void recover(RetryContext context){
		                    System.out.println("Recovering...");
		                    return null;
		                }
		            }
		        );
	}

	@Recover
	public void recover(DummyMailException exception) {
		System.out.println("dgfasdfas");
	}

	public boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

}