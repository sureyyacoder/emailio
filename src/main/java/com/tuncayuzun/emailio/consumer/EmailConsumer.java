package com.tuncayuzun.emailio.consumer;

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

import com.tuncayuzun.emailio.model.ForgotPasswordEmail;
import com.tuncayuzun.emailio.model.NewsletterEmail;
import com.tuncayuzun.emailio.model.WelcomeEmail;
import com.tuncayuzun.emailio.service.EmailService;
import com.tuncayuzun.emailio.utility.DummyMailException;
import com.tuncayuzun.emailio.utility.Helper;

@Component
public class EmailConsumer {

	@Autowired
	private RetryTemplate retryTemplate;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailService emailService;

	@JmsListener(destination = "welcome-password-queue", containerFactory = "jsaFactory", id = "2")
	public void welcomeListener(WelcomeEmail email) throws DummyMailException {
		System.out.println("Received Welcome Message; " + email);
		sendWelcomeEmail(email);
	}

	@JmsListener(destination = "welcome-password-queue", containerFactory = "jsaFactory", id = "1sadfasdfa")
	public void passwordListener(ForgotPasswordEmail email) throws DummyMailException {

		System.out.println("Received Welcome Message; " + email);
		sendForgotPasswordEmail(email);
	}

	@JmsListener(destination = "newsletter-queue", containerFactory = "jsaFactory")
	public void newsletterlListener(NewsletterEmail email) throws DummyMailException {
		System.out.println("Received Newsletter Message; " + email);
		sendNewsLetterEmail(email);
	}

	public void sendWelcomeEmail(WelcomeEmail email) throws DummyMailException {

		retryTemplate.execute(new RetryCallback<Void, DummyMailException>() {
			@Override
			public Void doWithRetry(RetryContext context) throws DummyMailException {

				boolean isMailSent = Helper.getRandomBoolean();
				System.out.println("Trying to send...");
				if (isMailSent) {
					SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
					simpleMailMessage.setTo(email.getTo());
					simpleMailMessage.setSubject(email.getSubject());
					simpleMailMessage.setText(email.getBody());
					javaMailSender.send(simpleMailMessage);
				} else {
					throw new DummyMailException("An error occured while mail sending...!");
				}
				return null;
			}
		}, new RecoveryCallback<Void>() {
			@Override
			public Void recover(RetryContext context) {
				System.out.println("Recovering...");
				emailService.updateWelcomeEmailStatus(email);
				return null;
			}
		});
	}

	public void sendForgotPasswordEmail(ForgotPasswordEmail email) throws DummyMailException {

		retryTemplate.execute(new RetryCallback<Void, DummyMailException>() {
			@Override
			public Void doWithRetry(RetryContext context) throws DummyMailException {
				boolean isMailSent = Helper.getRandomBoolean();
				System.out.println("Trying to send...");
				if (isMailSent) {
					SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
					simpleMailMessage.setTo(email.getTo());
					simpleMailMessage.setSubject(email.getSubject());
					simpleMailMessage.setText(email.getBody());
					javaMailSender.send(simpleMailMessage);
				} else {
					throw new DummyMailException("An error occured while mail sending...!");
				}
				return null;
			}
		}, new RecoveryCallback<Void>() {
			@Override
			public Void recover(RetryContext context) {
				System.out.println("Recovering...");
				emailService.updatePasswordEmailStatus(email);
				return null;
			}
		});
	}

	public void sendNewsLetterEmail(NewsletterEmail email) throws DummyMailException {

		retryTemplate.execute(new RetryCallback<Void, DummyMailException>() {
			@Override
			public Void doWithRetry(RetryContext context) throws DummyMailException {
				boolean isMailSent = Helper.getRandomBoolean();
				System.out.println("Trying to send...");
				if (isMailSent) {
					SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
					simpleMailMessage.setTo(email.getTo());
					simpleMailMessage.setSubject(email.getSubject());
					simpleMailMessage.setText(email.getBody());
					javaMailSender.send(simpleMailMessage);
				} else {
					throw new DummyMailException("An error occured while mail sending...!");
				}
				return null;
			}
		}, new RecoveryCallback<Void>() {
			@Override
			public Void recover(RetryContext context) {
				System.out.println("Recovering...");
				emailService.updateNewsletterEmailStatus(email);
				return null;
			}
		});
	}

	@Recover
	public void recover(DummyMailException exception) {
		System.out.println("Recovered...");
	}

}